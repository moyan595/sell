package com.xinyan.sell.repository;

import com.xinyan.sell.po.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 卖家 Repository 接口
 */
public interface SellerInfoRepository extends JpaRepository<SellerInfo, String> {

    /**
     * 查询卖家：openid
     * @param openid
     * @return
     */
    SellerInfo findByOpenid(String openid);
}
