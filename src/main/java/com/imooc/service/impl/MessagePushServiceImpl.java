package com.imooc.service.impl;

import com.imooc.config.WechatAccountConfig;
import com.imooc.dto.OrderDTO;
import com.imooc.enums.ResultEnum;
import com.imooc.service.MessagePushService;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 消息推送
 *
 * @author yangxin
 * 2019/12/31 10:39
 */
@SuppressWarnings("AlibabaRemoveCommentedCode")
@Service
@Slf4j
public class MessagePushServiceImpl implements MessagePushService {

    private final WxMpService wxMpService;
    private final WechatAccountConfig wechatAccountConfig;

    @Autowired
    public MessagePushServiceImpl(WxMpService wxMpService, WechatAccountConfig wechatAccountConfig) {
        this.wxMpService = wxMpService;
        this.wechatAccountConfig = wechatAccountConfig;
    }

    @Override
    public void pushOrderStatus(OrderDTO orderDTO) {
        WxMpTemplateMessage wxMpTemplateMessage = new WxMpTemplateMessage();
        wxMpTemplateMessage.setTemplateId(wechatAccountConfig.getTemplateId().get("orderStatus"));
        wxMpTemplateMessage.setToUser("oBmg_w3QcCeZV6tSBpHTsrQ_7_Yk");
//        wxMpTemplateMessage.setToUser(orderDTO.getBuyerOpenid());

        List<WxMpTemplateData> wxMpTemplateDataList = Arrays.asList(
                new WxMpTemplateData("first", "亲，记得收货。"),
                new WxMpTemplateData("keyword1", "微信点餐"),
                new WxMpTemplateData("keyword2", "18807402090"),
                new WxMpTemplateData("keyword3", orderDTO.getOrderId()),
                new WxMpTemplateData("keyword4", orderDTO.getOrderStatusEnum().getMessage()),
                new WxMpTemplateData("keyword5", "￥" + orderDTO.getOrderAmount()),
                new WxMpTemplateData("remark", "欢迎再次光临！")
        );
        wxMpTemplateMessage.setData(wxMpTemplateDataList);

        try {
            wxMpService.getTemplateMsgService().sendTemplateMsg(wxMpTemplateMessage);
        } catch (WxErrorException e) {
            log.error(ResultEnum.WX_TEMPLATE_MESSAGE_SEND_FAIL.getMessage() + "[{}]", e);
        }
    }
}
