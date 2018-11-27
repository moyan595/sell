package com.xinyan.sell.utils;

import com.xinyan.sell.enums.EnumCode;

/**
 * 枚举工具类.
 *
 * @author 谢老师
 */
public class EnumUtil {

    /**
     * //TODO
     * 返回一个枚举类实例.
     *
     * @param <T>       the type parameter
     * @param code      枚举类code
     * @param enumClass 枚举类 Class
     * @return t
     */
    public static <T extends EnumCode> T getInstance(Integer code, Class<T> enumClass){
        for(T instance : enumClass.getEnumConstants()){
            if(code.equals(instance.getCode())){
                return instance;
            }
        }
        return null;
    }
}
