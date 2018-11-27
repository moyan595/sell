package com.xinyan.sell.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 *
 * JSON 与 Java 对象转换工具类
 *
 * @author 谢老师
 */
public final class JsonUtil {

    /**
     * //TODO
     * The constant objectMapper.
     */
    public static ObjectMapper objectMapper;

    static {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
            //ALLOW_UNQUOTED_FIELD_NAMES 允许属性名称没有引号
            objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        }
    }

    /**
     * son字符串转换为相应的JavaBean对象
     *
     * @param <T>       the type parameter
     * @param jsonStr   the json str
     * @param valueType the value type
     * @return t
     */
    public static <T> T readValue(String jsonStr, Class<T> valueType) {
        try {
            return objectMapper.readValue(jsonStr, valueType);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * json数组转List
     *
     * @param <T>          the type parameter
     * @param jsonStr      the json str
     * @param valueTypeRef the value type ref
     * @return t
     */
    public static <T> T readValue(String jsonStr, TypeReference<T> valueTypeRef){
        try {
            return objectMapper.readValue(jsonStr, valueTypeRef);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 把JavaBean转换为json字符串
     *
     * @param object the object
     * @return string
     */
    public static String toJSon(Object object) {
        try {
//            return objectMapper.writeValueAsString(object); //一行输出
            //格式化输出
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
