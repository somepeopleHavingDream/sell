package com.imooc.service;

import com.imooc.dto.OrderDTO;

/**
 * 买家
 *
 * @author yangxin
 * 2019/07/01 20:40
 */
@SuppressWarnings({"UnusedReturnValue", "AlibabaAbstractMethodOrInterfaceMethodMustUseJavadoc"})
public interface BuyerService {

    /**
     * 查询一个订单
     */
    OrderDTO findOrderOne(String openid, String orderId);

    /**
     * 取消订单
     */
    OrderDTO cancelOrder(String openid, String orderId);
}
