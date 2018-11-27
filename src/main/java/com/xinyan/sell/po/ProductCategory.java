package com.xinyan.sell.po;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * 类目 PO 对象
 */
@Entity //和数据库表映射
@DynamicUpdate //动态更新
@Data //Getter, Setter, toString
public class ProductCategory {

    @Id //映射主键
    @GeneratedValue //主键自动生成
    /** 类目id */
    private Integer categoryId;

    /** 类目名称 */
    private String categoryName;

    /** 类目编号 */
    private Integer categoryType;

    /** 创建时间 */
    private Date createTime;

    /** 修改时间 */
    private Date updateTime;
}
