package com.imooc.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 微信帐号配置
 *
 * @author yangxin
 * 2019/10/16 09:44
 */
@Data
@Component
@ConfigurationProperties(prefix = "wechat")
public class WechatAccountConfig {
    /**
     * 公众平台appId
     */
    private String mpAppId;

    /**
     * 公众平台appsecret
     */
    private String mpAppSecret;
}
