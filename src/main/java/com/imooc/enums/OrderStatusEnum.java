package com.imooc.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 订单状态枚举类
 *
 * @author yangxin
 * 2019/06/16 19:40
 */
@AllArgsConstructor
@Getter
public enum OrderStatusEnum implements CodeEnum {

    NEW(0, "新订单"),
    FINISHED(1, "完结"),
    CANCEL(2, "已取消");

    private final Integer code;
    private final String message;
}
