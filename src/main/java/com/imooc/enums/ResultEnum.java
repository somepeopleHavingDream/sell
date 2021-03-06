package com.imooc.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 返回给前端的信息枚举类
 *
 * @author yangxin
 * 2019/06/18 13:53
 */
@SuppressWarnings("AlibabaEnumConstantsMustHaveComment")
@AllArgsConstructor
@Getter
public enum ResultEnum {

    SUCCESS(0, "成功"),
    PARAM_ERROR(1, "参数不正确"),
    PRODUCT_NOT_EXIST(10, "商品不存在"),
    PRODUCT_STOCK_ERROR(11, "商品库存不正确"),
    ORDER_NOT_EXIST(12, "订单不存在"),
    ORDER_DETAIL_NOT_EXIST(13, "订单详情不存在"),
    ORDER_STATUS_ERROR(14, "订单状态不正确"),
    ORDER_UPDATE_FAIL(15, "订单更新失败"),
    ORDER_DETAIL_EMPTY(16, "订单详情为空"),
    ORDER_PAY_STATUS_ERROR(17, "订单支付状态不正确"),
    CART_EMPTY(18, "购物车为空"),
    ORDER_OWNER_ERROR(19, "该订单不属于当前用户"),
    WECHAT_MP_ERROR(20, "微信公众帐号方面错误"),
    WXPAY_NOTIFY_MONEY_VERIFY_ERROR(21, "微信支付异步通知金额校验不通过"),
    ORDER_CANCEL_SUCCESS(22, "订单取消成功"),
    ORDER_FINISH_SUCCESS(23, "订单完结成功"),
    PRODUCT_STATUS_ERROR(24, "商品状态不正确"),
    LOGIN_FAIL(25, "登录失败，登录信息不正确"),
    LOGOUT_SUCCESS(26, "登出成功"),
    CANT_FIND_TOKEN_IN_COOKIE(27, "Cookie中查不到token"),
    CANT_FIND_TOKEN_IN_REDIS(28, "Redis中查不到token"),
    WX_TEMPLATE_MESSAGE_SEND_FAIL(29, "微信模板消息发送失败");

    /**
     * 状态码
     */
    private final Integer code;

    /**
     * 提示信息
     */
    private final String message;
}
