package com.imooc.config;

import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * 微信开发平台配置
 *
 * @author yangxin
 * 2019/11/23 20:50
 */
@Component
public class WechatOpenConfig {

    private final WechatAccountConfig accountConfig;

    @Autowired
    public WechatOpenConfig(WechatAccountConfig accountConfig) {
        this.accountConfig = accountConfig;
    }

    @Bean
    public WxMpService wxOpenService() {
        WxMpServiceImpl wxOpenService = new WxMpServiceImpl();
        wxOpenService.setWxMpConfigStorage(wxOpenConfigStorage());
        return wxOpenService;
    }

    @Bean
    public WxMpConfigStorage wxOpenConfigStorage() {
        WxMpInMemoryConfigStorage wxOpenConfigStorage = new WxMpInMemoryConfigStorage();
        wxOpenConfigStorage.setAppId(accountConfig.getOpenAppId());
        wxOpenConfigStorage.setSecret(accountConfig.getOpenAppSecret());

        return wxOpenConfigStorage;
    }
}
