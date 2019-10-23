package com.imooc.service;

import com.imooc.dto.OrderDTO;

/**
 * 支付Service
 *
 * @author yangxin
 * 2019/10/23 13:50
 */
public interface PayService {
    void create(OrderDTO orderDTO);
}
