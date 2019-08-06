package com.imooc.converter;

import com.imooc.bean.OrderMaster;
import com.imooc.dto.OrderDTO;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * OrderMaster对象转OrderDTO对象
 *
 * @author yangxin
 * 2019/06/23 21:36
 */
public final class OrderMaster2OrderDTOConverter {
    /**
     * 将单个订单对象转换成OrderDTO对象
     *
     * @param orderMaster 订单对象
     */
    private static OrderDTO convert(OrderMaster orderMaster) {
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        return orderDTO;
    }

    /**
     * 将订单对象集合转换为OrderDTO对象集合
     *
     * @param orderMasterList 订单对象集合
     */
    public static List<OrderDTO> convert(List<OrderMaster> orderMasterList) {
        return orderMasterList.stream()
                    .map(OrderMaster2OrderDTOConverter::convert)
                    .collect(Collectors.toList());
    }
}
