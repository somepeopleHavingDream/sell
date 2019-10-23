package com.imooc.controller;

import com.imooc.dto.OrderDTO;
import com.imooc.enums.ResultEnum;
import com.imooc.exception.SellException;
import com.imooc.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 支付Controller
 *
 * @author yangxin
 * 2019/10/23 11:47
 */
@Controller
@RequestMapping("/pay")
@Slf4j
public class PayController {
    private final OrderService orderService;

    @Autowired
    public PayController(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * 创建订单
     *
     * @param orderId 订单id
     * @param returnUrl 重定向url
     */
    @GetMapping("create")
    public void create (@RequestParam("orderId") String orderId,
                        @RequestParam("returnUrl") String returnUrl) {
        log.info("orderId: [{}], returnUrl: [{}]", orderId, returnUrl);

        // 1. 查询订单
        OrderDTO orderDTO = orderService.findOne(orderId);
        if (orderDTO == null) {
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }

        // 2. 发起支付
    }
}
