package com.xinyan.sell.controller;

import com.xinyan.sell.exception.SellException;
import com.xinyan.sell.form.ProductForm;
import com.xinyan.sell.po.ProductCategory;
import com.xinyan.sell.po.ProductInfo;
import com.xinyan.sell.service.ProductCategoryService;
import com.xinyan.sell.service.ProductService;
import com.xinyan.sell.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * 商品管理 Controller
 */
@Slf4j
@RequestMapping("/seller/product")
@Controller
public class SellerProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductCategoryService productCategoryService;

    /**
     * 商品列表
     * @param page
     * @param size
     * @param map
     * @return
     */
    @GetMapping("/list")
    public String list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                       @RequestParam(value = "size", defaultValue = "5") Integer size,
                       Map<String, Object> map){
        PageRequest pageRequest = new PageRequest(page - 1, size);
        Page<ProductInfo> productInfoPage = productService.findAll(pageRequest);
        map.put("productInfoPage", productInfoPage);
        return "product/list";
    }

    /**
     * 上架
     * @param productId
     * @return
     */
    @GetMapping("/onSale")
    public String onSale(@RequestParam("productId") String productId,
                         Map<String, Object> map){
        map.put("url", "seller/product/list");
        try {
            productService.onSale(productId);
        } catch (SellException e){
            map.put("msg", e.getMessage());
            return "common/error";
        }

        map.put("msg", "商品上架成功");

        return "common/success";
    }

    /**
     * 下架
     * @param productId
     * @return
     */
    @GetMapping("/offSale")
    public String offSale(@RequestParam("productId") String productId,
                         Map<String, Object> map){
        map.put("url", "seller/product/list");
        try {
            productService.offSale(productId);
        } catch (SellException e){
            map.put("msg", e.getMessage());
            return "common/error";
        }

        map.put("msg", "商品下架成功");

        return "common/success";
    }

    /**
     * 添加 / 修改商品页面
     * @param productId
     * @param map
     * @return
     */
    @GetMapping("/index")
    public String index(@RequestParam(value = "productId", required = false) String productId,
                        Map<String, Object> map){
        List<ProductCategory> productCategoryList = productCategoryService.findAll();
        map.put("productCategoryList", productCategoryList);

        if (!StringUtils.isEmpty(productId)){
            ProductInfo productInfo = productService.findOne(productId);
            map.put("productInfo", productInfo);
        }

        return "product/index";
    }

    /**
     * 保存商品
     * @param productForm
     * @param bindingResult
     * @return
     */
    @CacheEvict(cacheNames = "product", key = "100")//处理方法每次调用完成后，删除redis中的缓存
    @PostMapping("/save")
    public String save(@Valid ProductForm productForm,
                       BindingResult bindingResult,
                       Map<String, Object> map){
        if (bindingResult.hasErrors()){
            log.error("【保存商品】商品表单参数错误, productForm : {}", productForm);
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url", "seller/product/index");
            return "common/error";
        }

        ProductInfo productInfo = new ProductInfo();
        try {
            //新增
            if (StringUtils.isEmpty(productForm.getProductId())){
                productForm.setProductId(KeyUtil.generateUniqueKey());
            } else {
                //修改
                productInfo = productService.findOne(productForm.getProductId());
            }
            BeanUtils.copyProperties(productForm, productInfo);
            productService.save(productInfo);
        } catch (SellException e){
            log.error("【保存商品】保存商品错误, productInfo : {}", productInfo);
            map.put("msg", e.getMessage());
            map.put("url", "seller/product/index");
            return "common/error";
        }

        map.put("msg", "保存商品成功");
        map.put("url", "seller/product/list");

        return "common/success";
    }
}
