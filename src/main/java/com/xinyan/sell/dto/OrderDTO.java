package com.xinyan.sell.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.xinyan.sell.enums.OrderStatus;
import com.xinyan.sell.enums.PayStatus;
import com.xinyan.sell.po.OrderDetail;
import com.xinyan.sell.utils.EnumUtil;
import com.xinyan.sell.utils.serial.SerialDateToSecond;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Order 订单数据传输对象
 */
@Data
public class OrderDTO {
    /** 订单id */
    private String orderId;

    /** 买家姓名 */
    private String buyerName;

    /** 买家电话 */
    private String buyerPhone;

    /** 买家地址 */
    private String buyerAddress;

    /** 买家微信 openid */
    private String buyerOpenid;

    /** 订单总金额 */
    private BigDecimal orderAmount;

    /** 订单状态， 默认为0 新订单 */
    private Integer orderStatus = OrderStatus.NEW.getCode();

    /** 支付状态，默认为0 未支付 */
    private Integer payStatus = PayStatus.WAIT.getCode();

    /** 创建时间 */
    @JsonSerialize(using = SerialDateToSecond.class)
//    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /** 修改时间 */
    @JsonSerialize(using = SerialDateToSecond.class)
    private Date updateTime;

    /** 订单详情 */
    private List<OrderDetail> orderDetailList;

    /**
     * 订单状态信息
     * @return
     */
    @JsonIgnore //JSON转Java对象忽略这个属性
    public String getOrderStatusMessage(){
        return EnumUtil.getInstance(orderStatus, OrderStatus.class).getMessage();
    }

    /**
     * 支付状态信息
     * @return
     */
    @JsonIgnore
    public String getPayStatusMessage(){
        return EnumUtil.getInstance(payStatus, PayStatus.class).getMessage();
    }
}
