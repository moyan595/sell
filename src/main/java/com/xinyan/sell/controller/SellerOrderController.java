package com.xinyan.sell.controller;

import com.xinyan.sell.dto.OrderDTO;
import com.xinyan.sell.enums.ResultStatus;
import com.xinyan.sell.exception.SellException;
import com.xinyan.sell.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * 卖家订单 Controller
 */
@Slf4j
@RequestMapping("/seller/order")
@Controller
public class SellerOrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 订单列表
     * @param page 页码
     * @param size 每页记录数
     * @param map
     * @return
     */
    @GetMapping("/list")
    public String list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                       @RequestParam(value = "size", defaultValue = "10") Integer size,
                       Map<String, Object> map) {
        PageRequest pageRequest = new PageRequest(page - 1, size);
        Page<OrderDTO> orderDTOPage = orderService.findList(pageRequest);

        map.put("orderDTOPage", orderDTOPage);

        return "order/list";
    }

    /**
     * 取消订单
     * @param orderId
     * @return
     */
    @GetMapping("/cancel")
    public String cancel(@RequestParam("orderId") String orderId,
                         Map<String, Object> map){
        map.put("url", "seller/order/list");

        try {
            OrderDTO orderDTO = orderService.findOne(orderId);
            orderService.cancel(orderDTO);
        }catch (SellException e){
            log.error("【卖家端取消订单】发生异常 {}", e);

            map.put("msg", e.getMessage());
            return "common/error";
        }

        map.put("msg", ResultStatus.ORDER_CANCEL_SUCCESS.getMessage());

        return "common/success";
    }

    /**
     * 订单详情
     * @param orderId
     * @param map
     * @return
     */
    @GetMapping("/detail")
    public String detail(@RequestParam("orderId") String orderId,
                       Map<String, Object> map){
        try {
            OrderDTO orderDTO = orderService.findOne(orderId);
            map.put("orderDTO", orderDTO);
        } catch (SellException e){
            log.error("【卖家端查询订单详情】发生异常 {}", e);

            map.put("msg", e.getMessage());
            map.put("url", "seller/order/list");
            return "common/error";
        }

        return "order/detail";
    }

    /**
     * 完结订单
     * @param orderId
     * @param map
     * @return
     */
    @GetMapping("/finish")
    public String finish(@RequestParam("orderId") String orderId,
                         Map<String, Object> map){
        map.put("url", "seller/order/list");

        try {
            OrderDTO orderDTO = orderService.findOne(orderId);
            orderService.finish(orderDTO);
        } catch (SellException e){
            log.error("【卖家端完结订单】发生异常 {}", e);

            map.put("msg", e.getMessage());
            return "common/error";
        }

        map.put("msg", ResultStatus.ORDER_FINISH_SUCCESS.getMessage());

        return "common/success";
    }
}
