package com.imooc.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

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

        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wxb9b8ef133831adbe&secret=22d95c1f0751d30e9f1919e674e6173c&code=" + code + "&grant_type=authorization_code";
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url, String.class);
        log.info("response: [{}]", response);
    }
}
