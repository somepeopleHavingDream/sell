package com.imooc.bean;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * 订单详情
 *
 * @author yangxin
 * 2019/06/16 19:51
 */
@Entity
@Data
public class OrderDetail {
    @Id
    private String detailId;

    // 订单Id
    private String orderId;

    // 商品Id
    private String productId;

    // 商品名称
    private String productName;

    // 商品单价
    private BigDecimal productPrice;

    // 商品数量
    private Integer productQuantity;

    // 商品小图
    private String productIcon;
}
