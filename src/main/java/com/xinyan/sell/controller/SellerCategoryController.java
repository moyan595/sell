package com.xinyan.sell.controller;

import com.xinyan.sell.exception.SellException;
import com.xinyan.sell.form.CategoryForm;
import com.xinyan.sell.po.ProductCategory;
import com.xinyan.sell.service.ProductCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * 类目 Controller
 */
@Slf4j
@RequestMapping("/seller/category")
@Controller
public class SellerCategoryController {

    @Autowired
    private ProductCategoryService productCategoryService;

    /**
     * 列表
     * @param map
     * @return
     */
    @GetMapping("/list")
    public String list(Map<String, Object> map){
        List<ProductCategory> productCategoryList = productCategoryService.findAll();
        map.put("productCategoryList", productCategoryList);
        return "category/list";
    }

    /**
     * 新增 / 修改类目页面
     * @param categoryId
     * @param map
     * @return
     */
    @GetMapping("/index")
    public String index(@RequestParam(value = "categoryId", required = false) Integer categoryId,
                        Map<String, Object> map){
        if (categoryId != null){
            //修改
            ProductCategory productCategory = productCategoryService.findOne(categoryId);
            map.put("productCategory", productCategory);
        }

        return "category/index";
    }

    /**
     * 保存类目
     * @param categoryForm
     * @param bindingResult
     * @param map
     * @return
     */
    @PostMapping("/save")
    public String save(@Valid CategoryForm categoryForm,
                       BindingResult bindingResult,
                       Map<String, Object> map){
        if (bindingResult.hasErrors()){
            log.error("【保存商品类目】商品类目表单参数错误, categoryForm : {}", categoryForm);
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url", "seller/category/index");
            return "common/error";
        }

        ProductCategory productCategory = new ProductCategory();
        try {
            if (categoryForm.getCategoryId() != null){
                productCategory = productCategoryService.findOne(categoryForm.getCategoryId());
            }
            BeanUtils.copyProperties(categoryForm, productCategory);
            productCategoryService.save(productCategory);
        } catch (SellException e){
            log.error("【保存商品类目】保存商品类目错误, productCategory : {}", productCategory);
            map.put("msg", e.getMessage());
            map.put("url", "seller/category/index");
            return "common/error";
        }

        map.put("msg", "保存商品类目成功");
        map.put("url", "seller/category/list");

        return "common/success";
    }
}
