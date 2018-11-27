package com.xinyan.sell.service;

import com.xinyan.sell.dto.OrderDTO;
import com.xinyan.sell.enums.OrderStatus;
import com.xinyan.sell.po.OrderDetail;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Order 订单业务接口单元测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Test
    public void createOrder() {
        OrderDTO orderDTO = new OrderDTO();

        //买家信息
        orderDTO.setBuyerName("李四");
        orderDTO.setBuyerPhone("18868822222");
        orderDTO.setBuyerAddress("新研科技");
        orderDTO.setBuyerOpenid("ew3euwhd7sjw9diwkr");

        //购物车信息
        List<OrderDetail> orderDetailList = new ArrayList<>();
        //商品1
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setProductId("8fce681828a344cc8a516b4e2422a84e");
        orderDetail.setProductQuantity(2);
        orderDetailList.add(orderDetail);
        //商品2
        orderDetail = new OrderDetail();
        orderDetail.setProductId("30a019761a9f49d0a5215884b5cfe2e7");
        orderDetail.setProductQuantity(1);
        orderDetailList.add(orderDetail);

        orderDTO.setOrderDetailList(orderDetailList);

        OrderDTO result = orderService.createOrder(orderDTO);

        Assert.assertNotNull(result);
    }

    @Test
    public void findOne() {
        String orderId = "f9e32a945e9546328a389cfbbeacf5fc";
        OrderDTO orderDTO = orderService.findOne(orderId);
        log.info("【查询单个订单】 result = {}", orderDTO);
        Assert.assertEquals(orderId, orderDTO.getOrderId());
    }

    @Test
    public void findList() {
        String buyerOpenid = "ew3euwhd7sjw9diwkr";
        PageRequest pageRequest = new PageRequest(0, 1);
        Page<OrderDTO> orderDTOPage = orderService.findList(buyerOpenid, pageRequest);
        log.info("【订单列表】 result = {}", orderDTOPage);
        Assert.assertNotEquals(0, orderDTOPage.getTotalElements());
    }

    @Test
    public void cancel() {
        String orderId = "d6164cb788c0405c920a8b798cab5db9";
        OrderDTO orderDTO = orderService.findOne(orderId);

        OrderDTO result = orderService.cancel(orderDTO);
        Assert.assertEquals(OrderStatus.CANCEL.getCode(), result.getOrderStatus());
    }

    @Test
    public void finish() {
        String orderId = "d6164cb788c0405c920a8b798cab5db9";
        OrderDTO orderDTO = orderService.findOne(orderId);

        OrderDTO result = orderService.finish(orderDTO);
        Assert.assertEquals(OrderStatus.FINISHED.getCode(), result.getOrderStatus());
    }

    @Test
    public void paid() {
    }

    @Test
    public void list(){
        PageRequest pageRequest = new PageRequest(0, 2);
        Page<OrderDTO> orderDTOPage = orderService.findList(pageRequest);
        log.info("【订单列表】 result = {}", orderDTOPage);
        Assert.assertTrue("查询订单列表", orderDTOPage.getTotalElements() > 0);
    }
}