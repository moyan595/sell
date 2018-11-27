package com.xinyan.sell.controller;

import com.xinyan.sell.constant.CookieConstant;
import com.xinyan.sell.constant.RedisConstant;
import com.xinyan.sell.enums.ResultStatus;
import com.xinyan.sell.po.SellerInfo;
import com.xinyan.sell.service.SellerService;
import com.xinyan.sell.utils.CookieUtil;
import com.xinyan.sell.utils.KeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 卖家用户 Controller
 */
@RequestMapping("/seller")
@Controller
public class SellerUserController {

    @Autowired
    private SellerService sellerService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 卖家登录页面
     * @return
     */
    @GetMapping("/index")
    public String index(){
        return "login";
    }

    /**
     * 账户登录
     * @param account
     * @param password
     * @return
     */
    @PostMapping("/accountLogin")
    public String accountLogin(@RequestParam("account") String account,
                               @RequestParam("password") String password) {
        //TODO
        //账户密码和数据库匹配
        //设置 token 至 redis
        //设置 token 至 cookie
        return "redirect:/seller/order/list";
    }

    /**
     * 微信登录
     * @param openid
     * @param response
     * @param map
     * @return
     */
    @GetMapping("/login")
    public String login(@RequestParam("openid") String openid,
                      HttpServletResponse response,
                      Map<String, Object> map){
        //openid 和数据库中的openid匹配
        SellerInfo sellerInfo = sellerService.findSellerInfoByOpenid(openid);
        if (sellerInfo == null){
            map.put("msg", ResultStatus.LOGIN_FAIL.getMessage());
            map.put("url", "seller/order/list");

            return "common/error";
        }

        //设置 token 至 redis
        String token = String.format(RedisConstant.TOKEN_PREFIX, KeyUtil.generateUniqueKey());

        redisTemplate.opsForValue().set(token, openid, RedisConstant.EXPRIE, TimeUnit.SECONDS);

        //设置 token 至 cookie
        CookieUtil.set(response, CookieConstant.TOKEN, token, CookieConstant.EXPRIE);

        return "redirect:/seller/order/list";
    }

    /**
     * 登出
     * @param request
     * @param response
     * @param map
     * @return
     */
    @GetMapping("/logout")
    public String logout(HttpServletRequest request,
                         HttpServletResponse response,
                         Map<String, Object> map){
        //从cookie中查询
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if (cookie != null){
            //删除 redis 中的token
            redisTemplate.opsForValue().getOperations().delete(cookie.getValue());

            //删除 cookie
           CookieUtil.set(response, CookieConstant.TOKEN, null, 0);
        }

        map.put("msg", ResultStatus.LOGOUT_SUCCESS.getMessage());
        map.put("url", "seller/order/list");

        return "common/success";
    }
}
