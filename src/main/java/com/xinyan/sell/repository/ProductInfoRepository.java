package com.xinyan.sell.repository;

import com.xinyan.sell.po.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 商品 DAO 接口
 */
public interface ProductInfoRepository extends JpaRepository<ProductInfo, String> {

    /**
     * 查询上线商品
     * @param productStatus
     * @return
     */
    public List<ProductInfo> findByproductStatus(Integer productStatus);
}
