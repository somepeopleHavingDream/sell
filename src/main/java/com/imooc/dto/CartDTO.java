package com.imooc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 购物车类
 *
 * @author yangxin
 * 2019/06/18 14:52
 */
@Data
@AllArgsConstructor
public class CartDTO {

    /**
     * 商品Id
     */
    private String productId;

    /**
     * 数量
     */
    private Integer productQuantity;
}
