package com.xinyan.sell.po;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * 卖家 PO 对象
 */
@Data
@Entity
public class SellerInfo {

    @Id
    /** 卖家id */
    private String sellerId;

    /** 姓名 */
    private String username;

    /** 密码 */
    private String password;

    /** openid */
    private String openid;

    /** 创建时间 */
    private Date createTime;
}
