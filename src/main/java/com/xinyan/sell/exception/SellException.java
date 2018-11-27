package com.xinyan.sell.exception;

import com.xinyan.sell.enums.ResultStatus;
import lombok.Getter;

/**
 * 系统自定义异常类
 */
@Getter
public class SellException extends RuntimeException {

    private Integer code;

    public SellException(ResultStatus resultStatus){
        super(resultStatus.getMessage());
        this.code = resultStatus.getCode();
    }

    public SellException(Integer code, String message){
        super(message);
        this.code = code;
    }
}
