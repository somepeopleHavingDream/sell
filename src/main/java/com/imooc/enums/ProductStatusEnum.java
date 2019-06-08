package com.imooc.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 商品状态枚举类
 *
 * @author yangxin
 * 2019/06/08 15:42
 */
@AllArgsConstructor
@Getter
public enum ProductStatusEnum {
    UP(0, "在架"),
    DOWN(1, "下架");

    private Integer code;
    private String message;
}
