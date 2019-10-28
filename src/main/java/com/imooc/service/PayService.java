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
    PayResponse create(OrderDTO orderDTO);
}
