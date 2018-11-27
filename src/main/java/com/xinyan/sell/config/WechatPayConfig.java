package com.xinyan.sell.config;

import com.lly835.bestpay.config.WxPayH5Config;
import com.lly835.bestpay.service.BestPayService;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 支付配置
 *
 * @author 谢老师
 */
@Configuration
public class WechatPayConfig {

    /**
     * 微信公众账号配置.
     */
    @Autowired
    private WechatAccountConfig wechatAccountConfig;

    /**
     * <pre>
     * //发起支付
     * bestPayService.pay();
     *
     * //异步回调
     * bestPayService.asyncNotify();.
     * 把你要实例化的对象转化成一个Bean，放在IoC容器中
     * </pre>
     *
     * @return the bestPayService
     */
    @Bean
    public BestPayService bestPayService(){

        //微信公众账号支付配置
        WxPayH5Config wxPayH5Config = new WxPayH5Config();
        //设置微信公众号的appid
        wxPayH5Config.setAppId(wechatAccountConfig.getMpAppId());
        //设置微信公众号的app corpSecret
        wxPayH5Config.setAppSecret(wechatAccountConfig.getMpAppSecret());
        //设置商户号
        wxPayH5Config.setMchId(wechatAccountConfig.getMchId());
        //设置商户密钥
        wxPayH5Config.setMchKey(wechatAccountConfig.getMchKey());
        //设置商户证书路径
        wxPayH5Config.setKeyPath(wechatAccountConfig.getKeyPath());
        //设置支付后异步通知url
        wxPayH5Config.setNotifyUrl(wechatAccountConfig.getNotifyUrl());


        //支付类,所有的方法都在这个类里
        BestPayServiceImpl bestPayService = new BestPayServiceImpl();
        //微信公众号账号配置
        bestPayService.setWxPayH5Config(wxPayH5Config);

        return bestPayService;
    }
}
