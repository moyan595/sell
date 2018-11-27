package com.xinyan.sell.repository;

import com.xinyan.sell.enums.ProductStatus;
import com.xinyan.sell.po.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

/**
 * ProductInfoRepository 单元测试类
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoRepositoryTest {

    @Autowired
    private ProductInfoRepository repository;

    @Test
    public void saveTest(){
        ProductInfo productInfo = new ProductInfo();
        //使用 UUID 为商品id
        String productId = UUID.randomUUID().toString().replace("-", "").toLowerCase();
        productInfo.setProductId(productId);
        productInfo.setProductName("小炒肉");
        productInfo.setProductPrice(new BigDecimal("20"));
        productInfo.setProductStock(100);
        productInfo.setProductDescription("正宗湖南小炒肉");
        productInfo.setProductIcon("http://xxx.jpg");
        productInfo.setProductStatus(ProductStatus.UP.getCode());
        productInfo.setCategoryType(3);

        ProductInfo result = repository.save(productInfo);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByproductStatus() {
        List<ProductInfo> productInfoList = repository.findByproductStatus(0);
        Assert.assertNotEquals(0, productInfoList.size());
    }
}