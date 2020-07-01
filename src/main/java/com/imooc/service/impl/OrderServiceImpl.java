package com.imooc.service.impl;

import com.imooc.bean.OrderDetail;
import com.imooc.bean.OrderMaster;
import com.imooc.bean.ProductInfo;
import com.imooc.converter.OrderMaster2OrderDTOConverter;
import com.imooc.dto.CartDTO;
import com.imooc.dto.OrderDTO;
import com.imooc.enums.OrderStatusEnum;
import com.imooc.enums.PayStatusEnum;
import com.imooc.enums.ResultEnum;
import com.imooc.exception.SellException;
import com.imooc.repository.OrderDetailRepository;
import com.imooc.repository.OrderMasterRepository;
import com.imooc.service.MessagePushService;
import com.imooc.service.OrderService;
import com.imooc.service.PayService;
import com.imooc.service.ProductService;
import com.imooc.util.KeyUtil;
import com.imooc.websocket.WebSocket;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 订单服务实现类
 *
 * @author yangxin
 * 2019/06/17 22:46
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final ProductService productService;
    private final MessagePushService messagePushService;
    private final OrderDetailRepository orderDetailRepository;
    private final OrderMasterRepository orderMasterRepository;
    private final WebSocket webSocket;

    /**
     * 为了避免环形自动装配，而不采用private final的方式
     */
    @Autowired
    private PayService payService;

    @Autowired
    public OrderServiceImpl(ProductService productService,
                            MessagePushService messagePushService, OrderDetailRepository orderDetailRepository,
                            OrderMasterRepository orderMasterRepository, WebSocket webSocket) {
        this.productService = productService;
        this.messagePushService = messagePushService;
        this.orderDetailRepository = orderDetailRepository;
        this.orderMasterRepository = orderMasterRepository;
        this.webSocket = webSocket;
    }

    /**
     * 创建订单
     */
    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {
        // 订单唯一编号
        String orderId = KeyUtil.generateUniqueKey();

        // 订单总金额，需要根据后台数据计算
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);

        // 1. 查询商品（数量，价格）
        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
            ProductInfo productInfo = productService.findOne(orderDetail.getProductId());
            if (productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }

            // 2. 计算订单总价
            orderAmount = productInfo.getProductPrice()
                    .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                    .add(orderAmount);

            // 订单详情入库
            orderDetail.setDetailId(KeyUtil.generateUniqueKey());
            orderDetail.setOrderId(orderId);
            BeanUtils.copyProperties(productInfo, orderDetail);
            orderDetailRepository.save(orderDetail);
        }

        // 3. 写入订单数据库(order_master和order_detail)
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMasterRepository.save(orderMaster);

        // 4. 扣库存
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream()
                .map(orderDetail -> new CartDTO(orderDetail.getProductId(), orderDetail.getProductQuantity()))
                .collect(Collectors.toList());
        productService.decreaseStock(cartDTOList);

        // 发送websocket消息
        webSocket.onMessage("您有新的订单！");

        return orderDTO;
    }

    @Override
    public OrderDTO findOne(String orderId) {
        // 获得主订单记录
        OrderMaster orderMaster = orderMasterRepository.findOne(orderId);
        if (orderMaster == null) {
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }

        // 获得订单详情记录
        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderId);
        if (CollectionUtils.isEmpty(orderDetailList)) {
            throw new SellException(ResultEnum.ORDER_DETAIL_NOT_EXIST);
        }

        // 设置订单dto
        OrderDTO orderDTO = OrderDTO.builder().build();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        orderDTO.setOrderDetailList(orderDetailList);

        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {
        // 分页查询订单列表
        Page<OrderMaster> orderMasterPage = orderMasterRepository.findByBuyerOpenid(buyerOpenid, pageable);
        // 将订单对象集合转换为OrderDTO对象集合
        List<OrderDTO> orderDTOList = OrderMaster2OrderDTOConverter.convert(orderMasterPage.getContent());

        return new PageImpl<>(orderDTOList, pageable, orderMasterPage.getTotalElements());
    }

    @Override
    public Page<OrderDTO> findList(Pageable pageable) {
        // 分页查询订单列表
        Page<OrderMaster> orderMasterPage = orderMasterRepository.findAll(pageable);
        // 将订单对象集合转换为OrderDTO对象集合
        List<OrderDTO> orderDTOList = OrderMaster2OrderDTOConverter.convert(orderMasterPage.getContent());

        return new PageImpl<>(orderDTOList, pageable, orderMasterPage.getTotalElements());
    }

    @Override
    @Transactional
    public OrderDTO cancel(OrderDTO orderDTO) {
        OrderMaster orderMaster = new OrderMaster();

        // 判断订单状态
        if (!Objects.equals(orderDTO.getOrderStatus(), OrderStatusEnum.NEW.getCode())) {
            log.error("【取消订单】订单状态不正确，orderId=[{}], orderStatus=[{}]",
                    orderDTO.getOrderId(),
                    orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }

        // 修改订单状态
        orderDTO.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        BeanUtils.copyProperties(orderDTO, orderMaster);
        OrderMaster updateResult = orderMasterRepository.save(orderMaster);
        if (updateResult == null) {
            log.error("【取消订单】更新失败，orderMaster=[{}]", orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }

        // 返回库存
        List<OrderDetail> orderDetailList = orderDTO.getOrderDetailList();
        if (CollectionUtils.isEmpty(orderDetailList)) {
            log.error("【取消订单】订单中无商品详情，orderDTO=[{}]", orderDTO);
            throw new SellException(ResultEnum.ORDER_DETAIL_EMPTY);
        }
        List<CartDTO> cartDTOList = orderDetailList.stream()
                .map(orderDetail -> new CartDTO(orderDetail.getProductId(), orderDetail.getProductQuantity()))
                .collect(Collectors.toList());
        productService.increaseStock(cartDTOList);

        // 如果已支付，需要退款
        if (Objects.equals(orderDTO.getPayStatus(), PayStatusEnum.SUCCESS.getCode())) {
            payService.refund(orderDTO);
        }

        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO finish(OrderDTO orderDTO) {
        // 判断订单状态
        if (!Objects.equals(orderDTO.getOrderStatus(), OrderStatusEnum.NEW.getCode())) {
            log.error("【完结订单】订单状态不正确，orderId=[{}], orderStatus=[{}]",
                    orderDTO.getOrderId(),
                    orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }

        // 修改订单状态
        orderDTO.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        OrderMaster updateResult = orderMasterRepository.save(orderMaster);
        if (updateResult == null) {
            log.error("【完结订单】更新失败，orderMaster=[{}]", orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }

        // 推送微信模板消息
        messagePushService.pushOrderStatus(orderDTO);

        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO paid(OrderDTO orderDTO) {
        // 判断订单状态
        if (!Objects.equals(orderDTO.getOrderStatus(), OrderStatusEnum.NEW.getCode())) {
            log.error("【订单支付完成】订单状态不正确，orderId=[{}]，orderStatus=[{}]",
                    orderDTO.getOrderId(),
                    orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }

        // 判断支付状态
        if (!Objects.equals(orderDTO.getPayStatus(), PayStatusEnum.WAIT.getCode())) {
            log.error("【订单支付完成】订单支付状态不正确，orderDTO=[{}]", orderDTO);
            throw new SellException(ResultEnum.ORDER_PAY_STATUS_ERROR);
        }

        // 修改支付状态
        orderDTO.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        OrderMaster updateResult = orderMasterRepository.save(orderMaster);
        if (updateResult == null) {
            log.error("【订单支付完成】更新失败，orderMaster=[{}]", orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }

        return orderDTO;
    }
}
