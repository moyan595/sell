package com.xinyan.sell.service;

import com.xinyan.sell.po.SellerInfo;

/**
 * 卖家 业务接口
 */
public interface SellerService {

    /**
     * 卖家信息查询：openid
     * @param openid
     * @return
     */
    SellerInfo findSellerInfoByOpenid(String openid);
}
