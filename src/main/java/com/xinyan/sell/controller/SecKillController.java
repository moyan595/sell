package com.xinyan.sell.controller;

import com.xinyan.sell.service.SecKillService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 秒杀 API
 */
@Slf4j
@RequestMapping("/skill")
@RestController
public class SecKillController {

    @Autowired
    private SecKillService secKillService;

    /**
     * 查询秒杀活动特价商品信息
     * @param productId
     * @return
     */
    @GetMapping("/query/{productId}")
    public String query(@PathVariable String productId){
        return secKillService.querySecKillProductInfo(productId);
    }

    /**
     * 秒杀
     * @param productId
     * @return
     */
    @GetMapping("/order/{productId}")
    public String kill(@PathVariable String productId){
        log.info("【秒杀】商品信息, productId : {}", productId);
        //秒杀业务方法
        secKillService.orderProductMockDiffUser(productId);
        //返回特价商品信息
        return secKillService.querySecKillProductInfo(productId);
    }
}
