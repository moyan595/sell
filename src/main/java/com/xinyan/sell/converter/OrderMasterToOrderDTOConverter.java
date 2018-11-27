package com.xinyan.sell.converter;

import com.xinyan.sell.dto.OrderDTO;
import com.xinyan.sell.po.OrderMaster;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class OrderMasterToOrderDTOConverter {

    private OrderMasterToOrderDTOConverter(){

    }

    public static OrderDTO converter(OrderMaster orderMaster){
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        return orderDTO;
    }

    public static List<OrderDTO> converter(List<OrderMaster> orderMasterList){
        List<OrderDTO> orderDTOList = orderMasterList.stream()
                .map(e -> converter(e)).collect(Collectors.toList());
        return orderDTOList;
    }
}
