package com.xinyan.sell.enums;

import lombok.Getter;

/**
 * 支付状态
 */
@Getter
public enum PayStatus implements EnumCode {
    WAIT(0, "未支付"),
    SUCCESS(1, "支付成功")
    ;

    private Integer code;
    private String message;

    PayStatus(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
