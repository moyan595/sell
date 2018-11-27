package com.xinyan.sell.service;

import com.xinyan.sell.po.ProductCategory;

import java.util.List;

/**
 * 类目 业务接口
 */
public interface ProductCategoryService {

    public ProductCategory findOne(Integer categoryId);

    public List<ProductCategory> findAll();

    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    public ProductCategory save(ProductCategory productCategory);
}
