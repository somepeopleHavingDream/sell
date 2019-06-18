package com.imooc.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 返回给前端的信息枚举类
 *
 * @author yangxin
 * 2019/06/18 13:53
 */
@AllArgsConstructor
@Getter
public enum ResultEnum {
    PRODUCT_NOT_EXIST(10, "商品不存在");

    private Integer code;
    private String message;
}
