package com.xinyan.sell.utils;

import java.math.BigDecimal;

/**
 * 数字、金额 处理工具类
 */
public class MathUtil {

    private static final Double MONEY_RANGE = 0.01;

    /**
     * 比较两个金额是否相等
     * @param val1
     * @param val2
     * @return
     */
    public static Boolean compareTo(Double val1, Double val2){
        Double result = Math.abs(val1 - val2);
        if (result < MONEY_RANGE){
            return true;
        }else {
            return false;
        }
    }

    public static void main(String[] args) {
        Double a = 0.1;
        Double b = 0.1;
        Double c = 0.10;
        BigDecimal d = new BigDecimal("0.1");
        System.out.println(a == b); //false
        System.out.println(a.compareTo(b) == 0); //true
        System.out.println(a.compareTo(c) == 0); //true
        System.out.println(d.compareTo(new BigDecimal(a)) == 0); //false
    }
}
