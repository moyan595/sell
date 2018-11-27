package com.xinyan.sell.controller;

import com.xinyan.sell.config.ProjectUrlConfig;
import com.xinyan.sell.enums.ResultStatus;
import com.xinyan.sell.exception.SellException;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * 微信 Controller
 */
@Slf4j
@RequestMapping("/wechat")
@Controller
public class WechatController {

    @Autowired
    private WxMpService wxMpService;

    @Autowired
    private WxMpService wxOpenService;

    @Autowired
    private ProjectUrlConfig projectUrlConfig;

    //==================================微信支付获取openid(公众平台)==================================//

    /*
     * 第一步：用户同意授权，获取code
     * 第二步：通过code换取网页授权access_token
     * 第三步：刷新access_token（如果需要）
     * 第四步：拉取用户信息(需scope为 snsapi_userinfo)
     */

    /**
     * 返回带 code 和 state 的 url
     *  code说明 ： code作为换取access_token的票据，每次用户授权带上的code将不一样，
     *    code只能使用一次，5分钟未被使用自动过期。
     * @param returnUrl
     * @return
     */
    @GetMapping("/authorize")
    public String authorize(@RequestParam("returnUrl") String returnUrl){
        //构造网页授权url，然后构成超链接让用户点击重定向的url地址
        String url = projectUrlConfig.getWechatMpAuthorize() + "/sell/wechat/userInfo";

        //oauth2buildAuthorizationUrl 方法，用于返回一个
        //三个参数说明：
        //url：用户授权的url，点击后会重定向并带上 code 和 state 参数
        //scope: 应用授权作用域
        //  snsapi_base: 不弹出授权页面，直接跳转，只能获取用户openid
        //  snsapi_userinfo: 弹出授权页面，可通过openid拿到昵称、性别、所在地。
        //                   并且， 即使在未关注的情况下，只要用户授权，也能获取其信息
        //state: 重定向后会带上state参数

        //redirectUrl: 授权后重定向的回调链接地址
        //注意：跳转回调redirect_uri，应当使用https链接来确保授权code的安全性。
        String redirectUrl = null;
        try {
            redirectUrl = wxMpService.oauth2buildAuthorizationUrl(
                    url, WxConsts.OAuth2Scope.SNSAPI_USERINFO, URLEncoder.encode(returnUrl, "utf-8"));
        } catch (UnsupportedEncodingException e) {
            log.error("【微信网页授权】获取code, redirectUrl:{}", redirectUrl);
            throw new SellException(ResultStatus.WECHAT_MP_AUTHORIZE_ERROR);
        }

        return "redirect:" + redirectUrl;
    }

    /**
     * 返回带 openId 的 url
     * openId: 微信用户在商户对应appid下的唯一标识
     * @param code
     * @param returnUrl
     * @return
     */
    @GetMapping("/userInfo")
    public String userInfo(@RequestParam("code") String code,
                           @RequestParam("state") String returnUrl){
        WxMpUser wxMpUser = null;
        try {
            //当用户同意授权后，会回调所设置的url并把authorization code传过来，
            // 然后用这个code获得access token，其中也包含用户的openid等信息
            WxMpOAuth2AccessToken wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
            //获取用户基本信息
            wxMpUser = wxMpService.oauth2getUserInfo(wxMpOAuth2AccessToken, null);
        } catch (WxErrorException e) {
            log.error("【微信支付网页授权】{}", e);
            throw new SellException(ResultStatus.WECHAT_MP_AUTHORIZE_ERROR.getCode(),
                    e.getError().getErrorMsg());
        }
        String openId = wxMpUser.getOpenId();

        return  "redirect:" + returnUrl + "?openid=" + openId;
    }

    //==================================微信扫码登录获取openid(开放平台)==================================//

    @GetMapping("/qrAuthorize")
    public String qrAuthorize(@RequestParam("returnUrl") String returnUrl) {
        //构造网页授权url，然后构成超链接让用户点击重定向的url地址
        String url = projectUrlConfig.getWechatOpenAuthorize() + "/sell/wechat/qrUserInfo";

        String redirectUrl = null;
        try {
            //网站扫码登录scope：snsapi_login
            redirectUrl = wxOpenService.buildQrConnectUrl(url, WxConsts.QrConnectScope.SNSAPI_LOGIN,
                    URLEncoder.encode(returnUrl, "utf-8"));
        } catch (UnsupportedEncodingException e) {
            log.error("【微信网页授权】获取code, redirectUrl:{}", redirectUrl);
            throw new SellException(ResultStatus.WECHAT_MP_AUTHORIZE_ERROR);
        }

        return "redirect:" + redirectUrl;
    }

    @GetMapping("/qrUserInfo")
    public String qrUserInfo(@RequestParam("code") String code,
                             @RequestParam("state") String returnUrl){
        WxMpUser wxMpUser = null;
        try {
            WxMpOAuth2AccessToken wxMpOAuth2AccessToken = wxOpenService.oauth2getAccessToken(code);
            wxMpUser = wxOpenService.oauth2getUserInfo(wxMpOAuth2AccessToken, null);
        } catch (WxErrorException e) {
            log.error("【微信扫码登录网页授权】{}", e);
            throw new SellException(ResultStatus.WECHAT_MP_AUTHORIZE_ERROR.getCode(),
                    e.getError().getErrorMsg());
        }

        String openId = wxMpUser.getOpenId();

        return "redirect:" + returnUrl + "?openid=" + openId;
    }
}