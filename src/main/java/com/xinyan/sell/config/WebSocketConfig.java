package com.xinyan.sell.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * <pre>
 * 顺便说一句，springboot的高级组件会自动引用基础的组件，
 * 像spring-boot-starter-websocket就引入了spring-boot-starter-web和spring-boot-starter，所以不要重复引入。
 *
 * <h2>2、使用@ServerEndpoint创立websocket endpoint</h2
 *
 * 首先要注入ServerEndpointExporter，这个bean会自动注册使用了@ServerEndpoint注解声明的Websocket endpoint。
 * 要注意，如果使用独立的servlet容器，而不是直接使用springboot的内置容器，就不要注入ServerEndpointExporter，因为它将由容器自己提供和管理。
 * </pre>
 * WebSocket 配置
 *
 * @author 谢老师
 */
@Configuration
public class WebSocketConfig {


    /**
     *<pre>
     * <h2>Spring 初始化</h2>
     *
     * 如果使用一个单独的实例而不使用Servlet容器扫描，将EchoEndpoint类声明称一个bean，并增加一个ServerEndpointExporter的bean：
     * </pre>
     * Server endpoint exporter server endpoint exporter.
     *
     * @return server endpoint exporter
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter(){
        return new ServerEndpointExporter();
    }
}
