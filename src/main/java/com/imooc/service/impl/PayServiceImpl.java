package com.imooc.service.impl;

import com.imooc.dto.OrderDTO;
import com.imooc.service.PayService;
import com.imooc.util.JsonUtil;
import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PayServiceImpl implements PayService {
    private static final String ORDER_NAME = "微信点餐订单";

    private final BestPayServiceImpl bestPayService;

    @Autowired
    public PayServiceImpl(BestPayServiceImpl bestPayService) {
        this.bestPayService = bestPayService;
    }

    /**
     * 预支付
     */
    @Override
    public void create(OrderDTO orderDTO) {
        log.info("orderDTO: [{}]", JsonUtil.toJson(orderDTO));

        // 发起请求
        PayRequest payRequest = new PayRequest();
        payRequest.setOpenid(orderDTO.getBuyerOpenid());
        payRequest.setOrderAmount(orderDTO.getOrderAmount().doubleValue());
        payRequest.setOrderId(orderDTO.getOrderId());
        payRequest.setOrderName(ORDER_NAME);
        payRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        log.info("【微信支付】payRequest: [{}]", JsonUtil.toJson(payRequest));

        // 返回响应
        PayResponse payResponse = bestPayService.pay(payRequest);
        log.info("【微信支付】payResponse: [{}]", JsonUtil.toJson(payResponse));
    }
}
