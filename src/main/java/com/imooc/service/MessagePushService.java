package com.imooc.service;

import com.imooc.dto.OrderDTO;

/**
 * 消息推送
 *
 * @author yangxin
 * 2019/12/30 10:37
 */
public interface MessagePushService {
    /**
     * 订单状态变更消息
     */
    void pushOrderStatus(OrderDTO orderDTO);
}
