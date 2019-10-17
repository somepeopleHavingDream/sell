package com.imooc.config;

import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * 微信公众平台配置类
 *
 * @author yangxin
 * 2019/10/15 18:00
 */
@Component
public class WechatMpConfig {
    private final WechatAccountConfig accountConfig;

    @Autowired
    public WechatMpConfig(WechatAccountConfig accountConfig) {
        this.accountConfig = accountConfig;
    }

    /**
     * 获得wxMapService
     */
    @Bean
    public WxMpService wxMpService() {
        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxMpConfigStorage());
        return wxMpService;
    }

    /**
     * 获得公众平台的配置
     */
    @Bean
    public WxMpInMemoryConfigStorage wxMpConfigStorage() {
        WxMpInMemoryConfigStorage wxMpInMemoryConfigStorage = new WxMpInMemoryConfigStorage();
        wxMpInMemoryConfigStorage.setAppId(accountConfig.getMpAppId());
        wxMpInMemoryConfigStorage.setSecret(accountConfig.getMpAppSecret());
        return wxMpInMemoryConfigStorage;
    }
}
