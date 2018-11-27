package com.xinyan.sell.enums;

import lombok.Getter;

/**
 * 订单状态
 */
@Getter
public enum  OrderStatus implements EnumCode {
    NEW(0, "新订单"),
    FINISHED(1, "已完结"),
    CANCEL(2, "已取消")
    ;

    private Integer code;
    private String message;

    OrderStatus(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
