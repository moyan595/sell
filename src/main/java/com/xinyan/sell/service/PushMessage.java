package com.xinyan.sell.service;

import com.xinyan.sell.dto.OrderDTO;

/**
 * 消息推送 业务接口
 */
public interface PushMessage {

    void orderStatus(OrderDTO orderDTO);
}
