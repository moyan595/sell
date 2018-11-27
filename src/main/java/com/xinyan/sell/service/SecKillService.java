package com.xinyan.sell.service;

/**
 * 秒杀业务接口
 */
public interface SecKillService {

    /**
     * 查询秒杀活动特价商品信息
     * @param productId
     * @return
     */
    String querySecKillProductInfo(String productId);

    /**
     * 秒杀
     * @param productId
     */
    void orderProductMockDiffUser(String productId);
}
