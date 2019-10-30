package com.imooc.service;

import com.imooc.dto.OrderDTO;
import com.lly835.bestpay.model.PayResponse;

/**
 * 支付Service
 *
 * @author yangxin
 * 2019/10/23 13:50
 */
public interface PayService {
    /**
     * 发起支付
     */
    PayResponse create(OrderDTO orderDTO);

    /**
     * 微信异步通知
     */
    void notify(String notifyData);

    /**
     * 退款
     */
    void refund(OrderDTO orderDTO);
}
