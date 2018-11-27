package com.xinyan.sell.constant;

/**
 * Redis 常量
 * @author 谢老师
 */
public interface RedisConstant {

    /** session token 前缀 */
    String TOKEN_PREFIX = "token_%s";

    /** 失效时间 */
    Integer EXPRIE = 7200;
}
