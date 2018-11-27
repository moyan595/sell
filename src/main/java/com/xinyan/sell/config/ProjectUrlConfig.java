package com.xinyan.sell.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 *
 */
@Data
@ConfigurationProperties(prefix = "projectUrl")
@Configuration
public class ProjectUrlConfig {

    /**
     * 微信公众平台授权 url
     */
    private String wechatMpAuthorize;

    /**
     * 微信开放平台授权 url
     */
    private String wechatOpenAuthorize;

    /**
     * 系统 url
     */
    private String sell;
}
