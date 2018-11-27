package com.xinyan.sell.service.impl;

import com.xinyan.sell.dto.CartDTO;
import com.xinyan.sell.enums.ProductStatus;
import com.xinyan.sell.enums.ResultStatus;
import com.xinyan.sell.exception.SellException;
import com.xinyan.sell.po.ProductInfo;
import com.xinyan.sell.repository.ProductInfoRepository;
import com.xinyan.sell.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 商品 业务接口 实现类
 */
@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    /**
     * 查询单个商品
     * @param productId
     * @return
     */
    @Transactional(readOnly = true)
    @Override
    public ProductInfo findOne(String productId) {
        return productInfoRepository.findOne(productId);
    }

    /**
     * 查询所有商品
     * @return
     */
    @Transactional(readOnly = true)
    @Override
    public List<ProductInfo> findAll() {
        return productInfoRepository.findAll();
    }

    /**
     * 分页查询所有商品
     * @param pageable
     * @return
     */
    @Transactional(readOnly = true)
    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return productInfoRepository.findAll(pageable);
    }

    /**
     * 保存商品
     * @param productInfo
     * @return
     */
    @Transactional
    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return productInfoRepository.save(productInfo);
    }

    /**
     * 减少商品库存
     * @param cartDTOList
     */
    @Transactional
    @Override
    public void decreaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO : cartDTOList){
            ProductInfo productInfo = productInfoRepository.findOne(cartDTO.getProductId());
            if(productInfo == null){
                throw new SellException(ResultStatus.PRODUCT_NOT_EXIST);
            }

            Integer result = productInfo.getProductStock() - cartDTO.getProductQuantity();
            if(result < 0){
                throw new SellException(ResultStatus.PRODUCT_STOCK_ERROR);
            }

            productInfo.setProductStock(result);
            productInfoRepository.save(productInfo);
        }
    }

    /**
     * 增加库存
     * @param cartDTOList
     */
    @Transactional
    @Override
    public void increaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO : cartDTOList){
            ProductInfo productInfo = productInfoRepository.findOne(cartDTO.getProductId());
            if(productInfo == null){
                throw new SellException(ResultStatus.PRODUCT_NOT_EXIST);
            }

            Integer result = productInfo.getProductStock() + cartDTO.getProductQuantity();
            productInfo.setProductStock(result);

            productInfoRepository.save(productInfo);
        }
    }

    /**
     * 上架
     * @param productId
     * @return
     */
    @Override
    public ProductInfo onSale(String productId) {
        ProductInfo productInfo = productInfoRepository.findOne(productId);

        if (productInfo == null){
            log.error("【商品上架】商品不存在, productId : {}", productId);
            throw new SellException(ResultStatus.PRODUCT_NOT_EXIST);
        }

        if (productInfo.getProductStatus().equals(ProductStatus.UP.getCode())){
            log.error("【商品上架】商品状态错误, productInfo : {}", productInfo);
            throw new SellException(ResultStatus.PRODUCT_STATUS_ERROR);
        }

        productInfo.setProductStatus(ProductStatus.UP.getCode());
        ProductInfo result = productInfoRepository.save(productInfo);

        return result;
    }

    /**
     * 下架
     * @param productId
     * @return
     */
    @Override
    public ProductInfo offSale(String productId) {
        ProductInfo productInfo = productInfoRepository.findOne(productId);

        if (productInfo == null){
            log.error("【商品上架】商品不存在, productId : {}", productId);
            throw new SellException(ResultStatus.PRODUCT_NOT_EXIST);
        }

        if (productInfo.getProductStatus().equals(ProductStatus.DOWN.getCode())){
            log.error("【商品上架】商品状态错误, productInfo : {}", productInfo);
            throw new SellException(ResultStatus.PRODUCT_STATUS_ERROR);
        }

        productInfo.setProductStatus(ProductStatus.DOWN.getCode());
        ProductInfo result = productInfoRepository.save(productInfo);

        return result;
    }
}
