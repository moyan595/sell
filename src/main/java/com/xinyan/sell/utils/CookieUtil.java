package com.xinyan.sell.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Cookie 工具类.
 *
 * @author 谢老师
 */
public class CookieUtil {

    /**
     * 写入 Cookie.
     *
     * @param response the response
     * @param name     the name
     * @param value    the value
     * @param maxAge   the max age
     */
    public static void set(HttpServletResponse response,
                           String name, String value,
                           int maxAge){
        //Cookie对象
        Cookie cookie = new Cookie(name, value);
        //Cookie路径
        cookie.setPath("/");
        //Cookie存放时间
        cookie.setMaxAge(maxAge);
        //把Cookie存放请求域
        response.addCookie(cookie);
    }

    /**
     * 获取 Cookie.
     *
     * @param request the request
     * @param name    the name
     * @return cookie
     */
    public static Cookie get(HttpServletRequest request, String name){
        //Cookie数组
        Cookie[] cookies = request.getCookies();
        //判断数组是由为空并且长度不为0
        if (cookies != null && cookies.length > 0){
            //forEach遍历集合
            for (Cookie cookie : cookies){
                //如果获取到的Cookie名相同则返回该Cookie
                if (cookie.getName().equals(name)){
                    return cookie;
                }
            }
        }
        return null;
    }
}
