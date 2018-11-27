package com.xinyan.sell.utils;

import java.util.UUID;

/**
 * 生成 Key
 */
public class KeyUtil {

    private KeyUtil(){

    }

    /**
     * 生成唯一key：UUID
     * @return
     */
    public static synchronized String generateUniqueKey(){
        String key = UUID.randomUUID().toString().replace("-", "");
        return key;
    }
}
