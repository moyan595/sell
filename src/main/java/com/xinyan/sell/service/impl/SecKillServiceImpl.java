package com.xinyan.sell.service.impl;

import com.xinyan.sell.exception.SellException;
import com.xinyan.sell.redis.RedisLock;
import com.xinyan.sell.service.SecKillService;
import com.xinyan.sell.utils.KeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 秒杀业务接口实现
 */
@Service
public class SecKillServiceImpl implements SecKillService {

    //超时时间：10秒
    private static final int TIMEOUT = 10000;

    @Autowired
    private RedisLock redisLock;

    /**
     * 双11，皮蛋粥特价，限量100000份
     */
    //销售量
    static Map<String, Integer> products;
    //库存
    static Map<String, Integer> stock;
    //订单
    static Map<String, String> orders;
    static {
        /**
         * 模拟多个表：商品信息表、库存表、秒杀成功订单表
         */
        products = new HashMap<>();
        stock = new HashMap<>();
        orders = new HashMap<>();
        products.put("123456", 100000);
        stock.put("123456", 100000);
    }

    private String queryMap(String productId){
        return "双11活动，皮蛋粥特价，限量份"
                + products.get(productId)
                + "，还剩：" + stock.get(productId) + " 份"
                + "，该商品成功下单用户数目："
                + orders.size() + "人";
    }

    /**
     * 秒杀活动特价商品信息
     * @param productId
     * @return
     */
    @Override
    public String querySecKillProductInfo(String productId) {
        return queryMap(productId);
    }

    /**
     * 秒杀
     * @param productId
     */
    @Override
    public void orderProductMockDiffUser(String productId) {
        //redis加锁
        long time = System.currentTimeMillis() + TIMEOUT;
        if (!redisLock.lock(productId, String.valueOf(time))) {
            throw new SellException(101, "哎呦喂，人数过多，换个姿势再试试吧~~");
        }

        //1. 查询商品库存，为0则活动结束
        int stockNum = stock.get(productId);
        if (stockNum == 0) {
            throw new SellException(100, "秒杀活动结束");
        } else {
            //2. 下单(模拟不同用户openid不同)
            orders.put(KeyUtil.generateUniqueKey(), productId);
            //3. 减库存
            stockNum--;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            stock.put(productId, stockNum);
        }

        //解锁
        redisLock.unlock(productId, String.valueOf(time));
    }
}
