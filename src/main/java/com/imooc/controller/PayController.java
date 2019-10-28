package com.imooc.controller;

import com.imooc.dto.OrderDTO;
import com.imooc.enums.ResultEnum;
import com.imooc.exception.SellException;
import com.imooc.service.OrderService;
import com.imooc.service.PayService;
import com.lly835.bestpay.model.PayResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

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
    private final PayService payService;

    @Autowired
    public PayController(OrderService orderService, PayService payService) {
        this.orderService = orderService;
        this.payService = payService;
    }

    /**
     * 发起支付
     * http://proxy.springboot.cn/pay?openid=oTgZpwQGQoHHb-opLH0u6EnxRD9w&orderId=1561213773204650786&returnUrl=sell.com
     *
     * @param orderId 订单id
     * @param returnUrl 重定向url
     */
    @GetMapping("create")
    public ModelAndView create (@RequestParam("orderId") String orderId,
                                @RequestParam("returnUrl") String returnUrl,
                                Map<String, Object> map) {
        log.info("orderId: [{}], returnUrl: [{}]", orderId, returnUrl);

        // 1. 查询订单
        OrderDTO orderDTO = orderService.findOne(orderId);
        if (orderDTO == null) {
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }

        // 2. 发起支付
        PayResponse payResponse = payService.create(orderDTO);
        map.put("payResponse", payResponse);
        map.put("returnUrl", returnUrl);
        return new ModelAndView("pay/create", map);
    }
}
