package com.xinyan.sell.controller;

import com.lly835.bestpay.model.PayResponse;
import com.xinyan.sell.dto.OrderDTO;
import com.xinyan.sell.enums.ResultStatus;
import com.xinyan.sell.exception.SellException;
import com.xinyan.sell.service.OrderService;
import com.xinyan.sell.service.PayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * 微信支付
 */
@Slf4j
@RequestMapping("/pay")
@Controller
public class PayController {

    @Autowired
    private OrderService orderService;
    
    @Autowired
    private PayService payService;

    /**
     * 发起微信支付
     * @param orderId
     * @param returnUrl
     * @return
     */
    @GetMapping("/create")
    public ModelAndView create(@RequestParam("orderId") String orderId,
                               @RequestParam("returnUrl") String returnUrl){
        //查询订单总金额
        OrderDTO orderDTO = orderService.findOne(orderId);
        if (orderDTO == null){
            log.error("【支付订单】订单不存在, orderId:{}", orderId);
            throw new SellException(ResultStatus.ORDER_NOT_EXIST);
        }

        //支付订单
        PayResponse payResponse = payService.create(orderDTO);

        //Freemarker: templates/pay/create.ftl
        ModelAndView modelAndView = new ModelAndView("pay/create");
        modelAndView.addObject("payResponse", payResponse);
        modelAndView.addObject("returnUrl",returnUrl);

        return modelAndView;
    }

    /**
     * 微信支付异步通知
     * @param notifyData
     * @return 支付成功需按照指定格式（xml）返回参数给微信支付
     */
    @PostMapping("/notify")
    public String notify(@RequestBody String notifyData){
        payService.notify(notifyData);

        return "pay/success";
    }
}
