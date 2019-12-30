package com.imooc.service.impl;

import com.imooc.dto.OrderDTO;
import com.imooc.service.MessagePushService;
import com.imooc.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 消息推送
 *
 * @author yangxin
 * 2019/12/31 11:18
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MessagePushServiceImplTest {
    @Autowired
    private MessagePushService messagePushService;
    @Autowired
    private OrderService orderService;

    /**
     * 推送订单状态
     */
    @Test
    public void orderStatus() {
        OrderDTO orderDTO = orderService.findOne("1561213773204650786");
        messagePushService.pushOrderStatus(orderDTO);
    }
}