package com.imooc.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 支付状态枚举类
 *
 * @author yangxin
 * 2019/06/16 19:44
 */
@Getter
@AllArgsConstructor
public enum PayStatusEnum {
    WAIT(0, "等待支付"),
    SUCCESS(1, "支付成功");

    private Integer code;
    private String message;
}
