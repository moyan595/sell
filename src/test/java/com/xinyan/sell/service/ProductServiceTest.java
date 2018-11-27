package com.xinyan.sell.service;

import com.xinyan.sell.enums.ProductStatus;
import com.xinyan.sell.po.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

/**
 * ProductService 单元测试类
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Test
    public void findOne() {
        ProductInfo productInfo = productService.findOne("8fce681828a344cc8a516b4e2422a84e");
        Assert.assertNotNull(productInfo);
    }

    @Test
    public void findAll() {
        List<ProductInfo> productInfoList = productService.findAll();
        Assert.assertNotEquals(0, productInfoList.size());
    }

    @Test
    public void findAll1() {
        Pageable pageable = new PageRequest(0, 5);
        Page<ProductInfo> page = productService.findAll(pageable);
        Assert.assertNotEquals(0, page.getTotalElements());
    }

    @Test
    public void save() {
        ProductInfo productInfo = new ProductInfo();
        //使用 UUID 为商品id
        String productId = UUID.randomUUID().toString().replace("-", "").toLowerCase();
        productInfo.setProductId(productId);
        productInfo.setProductName("红烧鱼");
        productInfo.setProductPrice(new BigDecimal("30"));
        productInfo.setProductStock(50);
        productInfo.setProductDescription("红烧福寿鱼");
        productInfo.setProductIcon("http://xxx.jpg");
        productInfo.setProductStatus(ProductStatus.UP.getCode());
        productInfo.setCategoryType(3);

        ProductInfo result = productService.save(productInfo);
        Assert.assertNotNull(result);
    }
}