package com.xinyan.sell.config;

import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

/**
 * 微信开放平台配置
 *
 * @author 谢老师
 */
public class WechatOpenConfig {

    /**
     * 微信公众账号配置.
     */
    @Autowired
    private WechatAccountConfig wechatAccountConfig;

    /**
     * <pre>
     * 微信工具类
     * 微信API的Service.
     *</pre>
     * @return the wxOpenService
     */
    @Bean
    public WxMpService wxOpenService(){
        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxOpenConfigStorage());

        return wxMpService;
    }

    /**
     * <pre>
     * 微信公众号存储配置
     * WxMpInMemoryConfigStorage 基于内存的存储
     * 注意：生产环境应该持久化 WxMpInRedisConfigStorage 基于 Redis 存储
     *</pre>
     * @return the wx mp config storage
     */
    @Bean
    public WxMpConfigStorage wxOpenConfigStorage(){
        WxMpInMemoryConfigStorage wxMpConfigStorage = new WxMpInMemoryConfigStorage();
        wxMpConfigStorage.setAppId(wechatAccountConfig.getOpenAppId());
        wxMpConfigStorage.setSecret(wechatAccountConfig.getOpenAppSecret());

        return wxMpConfigStorage;
    }
}
