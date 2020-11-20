package com.imooc.form;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 商品表单
 *
 * @author yangxin
 * 2019/11/08 10:37
 */
@Data
public class ProductFrom {

    /**
     * 商品Id
     */
    private String productId;

    /**
     * 名字
     */
    private String productName;

    /**
     * 单价
     */
    private BigDecimal productPrice;

    /**
     * 库存
     */
    private Integer productStock;

    /**
     * 描述
     */
    private String productDescription;

    /**
     * 小图
     */
    private String productIcon;

    /**
     * 类目编号
     */
    private Integer categoryType;
}
