package com.imooc.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.imooc.bean.OrderDetail;
import com.imooc.util.serializer.Date2LongSerializer;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 订单传输类
 *
 * @author yangxin
 * 2019/06/17 22:38
 */
@Data
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDTO {
    // 订单Id
    private String orderId;

    // 买家名字
    private String buyerName;

    // 买家手机号
    private String buyerPhone;

    // 买家地址
    private String buyerAddress;

    // 买家微信Openid
    private String buyerOpenid;

    // 订单总金额
    private BigDecimal orderAmount;

    // 订单状态，默认为新下单
    private Integer orderStatus;

    // 支付状态，默认为0，未支付
    private Integer payStatus;

    // 创建时间
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;

    // 更新时间
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTime;

    List<OrderDetail> orderDetailList;
}
