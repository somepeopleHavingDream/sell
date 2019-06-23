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
    public static OrderDTO convert(OrderMaster orderMaster) {
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        return orderDTO;
    }

    public static List<OrderDTO> convert(List<OrderMaster> orderMasterList) {
        return orderMasterList.stream()
                    .map(OrderMaster2OrderDTOConverter::convert)
                    .collect(Collectors.toList());
    }
}
