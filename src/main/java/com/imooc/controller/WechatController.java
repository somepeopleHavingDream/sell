package com.imooc.controller;

import com.imooc.config.ProjectUrlConfig;
import com.imooc.enums.ResultEnum;
import com.imooc.exception.SellException;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * 微信Controller 正式
 *
 * @author yangxin
 * 2019/10/15 17:55
 */
@Controller
@RequestMapping("/wechat")
@Slf4j
public class WechatController {
    private final WxMpService wxMpService;
    private final WxMpService wxOpenService;

    private final ProjectUrlConfig projectUrlConfig;

    @Autowired
    public WechatController(WxMpService wxMpService, WxMpService wxOpenService, ProjectUrlConfig projectUrlConfig) {
        this.wxMpService = wxMpService;
        this.wxOpenService = wxOpenService;
        this.projectUrlConfig = projectUrlConfig;
    }

    /**
     * 微信公众平台网页授权
     */
    @GetMapping("/authorize")
    public String authorize(@RequestParam("returnUrl") String returnUrl) throws UnsupportedEncodingException {
        log.info("returnUrl: [{}]", returnUrl);

        // 构建访问微信网页授权的url
        String url = projectUrlConfig.getWechatMpAuthorize() + "/sell/wechat/userInfo";
        String redirectUrl = wxMpService.oauth2buildAuthorizationUrl(url,
                WxConsts.OAUTH2_SCOPE_BASE,
                URLEncoder.encode(returnUrl, "utf-8"));
        log.info("redirectUrl: [{}]", redirectUrl);

        return "redirect:" + redirectUrl;
    }

    /**
     * 获取用户信息
     */
    @GetMapping("/userInfo")
    public String userInfo(@RequestParam("code") String code, @RequestParam("state") String returnUrl) {
        log.info("code: [{}], returnUrl: [{}]", code, returnUrl);

        // 通过code换取网页授权access_token
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken;
        try {
            // 构建获取access_token的url
            wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
        } catch (WxErrorException e) {
            log.error("【微信网页授权】", e);
            throw new SellException(ResultEnum.WECHAT_MP_ERROR.getCode(), e.getError().getErrorMsg());
        }

        String openId = wxMpOAuth2AccessToken.getOpenId();
        log.info("openId: [{}]", openId);

        return "redirect:" + returnUrl + "?openid=" + openId;
    }

    /**
     * 二维码授权
     * <p>
     * https://open.weixin.qq.com/connect/qrconnect?appid=wx6ad144e54af67d87&redirect_uri=http%3A%2F%2Fsell.springboot.cn%2Fsell%2Fqr%2FoTgZpwQGQoHHb-opLH0u6EnxRD9w&response_type=code&scope=snsapi_login&state=http%3a%2f%2fabc.natapp.cc%2fsell%2fwechat%2fqrUserInfo
     */
    @GetMapping("/qrAuthorize")
    public String qrAuthorize(@RequestParam("returnUrl") String returnUrl) {
        log.info("returnUrl: [{}]", returnUrl);

        String url = projectUrlConfig.getWechatOpenAuthorize() + "/sell/wechat/qrUserInfo";
        String redirectUrl = wxOpenService.buildQrConnectUrl(url,
                WxConsts.QRCONNECT_SCOPE_SNSAPI_LOGIN,
                URLEncoder.encode(returnUrl));
        log.info("redirectUrl: [{}]", redirectUrl);

        return "redirect:" + redirectUrl;
    }

    /**
     * 二维码的用户信息
     */
    @GetMapping("/qrUserInfo")
    public String qrUserInfo(@RequestParam("code") String code) {
        String returnUrl = "http://www.imooc.com";
        log.info("code: [{}], returnUrl: [{}]", code, returnUrl);

        // 通过code换取网页授权access_token
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken;
        try {
            // 构建获取access_token的url
            wxMpOAuth2AccessToken = wxOpenService.oauth2getAccessToken(code);
        } catch (WxErrorException e) {
            log.error("【微信网页授权】", e);
            throw new SellException(ResultEnum.WECHAT_MP_ERROR.getCode(), e.getError().getErrorMsg());
        }

        String openId = wxMpOAuth2AccessToken.getOpenId();
        log.info("openId: [{}]", openId);

        return "redirect:" + returnUrl + "?openid=" + openId;
    }
}
