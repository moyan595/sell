package com.xinyan.sell.form;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 商品表单对象
 */
@Data
public class ProductForm {

    private String productId;

    /** 商品名称 */
    private String productName;

    /** 商品单价 */
    private BigDecimal productPrice;

    /** 商品库存 */
    private Integer productStock;

    /** 描述 */
    private String productDescription;

    /** 商品小图 */
    private String productIcon;

    /** 类目编号 */
    private Integer categoryType;
}
