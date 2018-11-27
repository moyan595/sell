package com.xinyan.sell.form;

import lombok.Data;

/**
 * 类目表单
 */
@Data
public class CategoryForm {

    /** 类目id */
    private Integer categoryId;

    /** 类目名称 */
    private String categoryName;

    /** 类目编号 */
    private Integer categoryType;
}
