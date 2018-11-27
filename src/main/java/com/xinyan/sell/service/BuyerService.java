package com.xinyan.sell.service;

import com.xinyan.sell.dto.OrderDTO;

/**
 * 买家 服务接口
 */
public interface BuyerService {

    /**
     * 查询单个订单
     * @param openid
     * @param orderId
     * @return
     */
    OrderDTO findOrderOne(String openid, String orderId);

    /**
     * 取消订单
     * @param openid
     * @param orderId
     * @return
     */
    OrderDTO cancelOrder(String openid, String orderId);
}
