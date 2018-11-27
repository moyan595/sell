package com.xinyan.sell.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 返回给客户端的VO对象
 */
@Data
public class ResultVO<T> implements Serializable {

    private static final long serialVersionUID = 2810747341097245891L;

    /** 错误码 */
    private Integer code;

    /** 消息 */
    private String msg;

    /** 页面数据 */
    private T data;
}
