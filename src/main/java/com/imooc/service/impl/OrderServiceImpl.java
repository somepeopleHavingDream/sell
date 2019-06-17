package com.imooc.service.impl;

import com.imooc.dto.OrderDTO;
import com.imooc.service.OrderService;
import com.imooc.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * 订单服务实现类
 *
 * @author yangxin
 * 2019/06/17 22:46
 */
@Service
public class OrderServiceImpl implements OrderService {
    private final ProductService productService;

    @Autowired
    public OrderServiceImpl(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public OrderDTO create(OrderDTO orderDTO) {
        // 1. 查询商品（数量，价格）
        orderDTO.getOrderDetailList().forEach(orderDetail -> {
            productService.findOne(orderDetail.getProductId());
        });
        // 2. 计算总价
        // 3. 写入订单数据库(order_master和order_detail)
        // 4. 扣库存
        return null;
    }

    @Override
    public OrderDTO findOne(String orderId) {
        return null;
    }

    @Override
    public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {
        return null;
    }

    @Override
    public OrderDTO cancel(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO finish(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO paid(OrderDTO orderDTO) {
        return null;
    }
}
