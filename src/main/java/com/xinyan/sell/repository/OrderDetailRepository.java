package com.xinyan.sell.repository;

import com.xinyan.sell.po.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 订单详情 DAO 接口
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {

    /**
     * 订单详情查询
     * @param orderId
     * @return
     */
    List<OrderDetail> findByOrderId(String orderId);
}
