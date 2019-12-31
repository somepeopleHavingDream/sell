package com.imooc.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 商品详情视图类
 *
 * @author yangxin
 * 2019/06/08 16:24
 */
@Data
public class ProductInfoVO implements Serializable {
    private static final long serialVersionUID = 219132828123341053L;

    /**
     * 商品id
     */
    @JsonProperty("id")
    private String productId;

    /**
     * 商品名称
     */
    @JsonProperty("name")
    private String productName;

    /**
     * 商品价格
     */
    @JsonProperty("price")
    private BigDecimal productPrice;

    /**
     * 商品描述
     */
    @JsonProperty("description")
    private String productDescription;

    /**
     * 商品小图（url）
     */
    @JsonProperty("icon")
    private String productIcon;
}
