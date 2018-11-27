package com.xinyan.sell.service.impl;

import com.xinyan.sell.po.SellerInfo;
import com.xinyan.sell.repository.SellerInfoRepository;
import com.xinyan.sell.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 卖家业务接口实现
 */
@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    private SellerInfoRepository sellerInfoRepository;

    /**
     * 卖家信息查询
     * @param openid
     * @return
     */
    @Override
    public SellerInfo findSellerInfoByOpenid(String openid) {
        return sellerInfoRepository.findByOpenid(openid);
    }
}
