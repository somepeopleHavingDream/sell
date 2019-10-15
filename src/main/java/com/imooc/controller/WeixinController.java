package com.imooc.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 微信Controller
 *
 * @author yangxin
 * 2019/10/15 17:01
 */
@RestController
@RequestMapping("/weixin")
@Slf4j
public class WeixinController {
    /**
     * 微信网页授权
     */
    @GetMapping("/auth")
    public void auth(@RequestParam("code") String code) {
        log.info("code: [{}]", code);
    }
}
