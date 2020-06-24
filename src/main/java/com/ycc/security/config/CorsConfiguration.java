package com.ycc.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * 跨域配置
 * @author ccc
 * @date 2020/06/24
 * **/
@Configuration
public class CorsConfiguration implements WebMvcConfigurer {

    /**
     * ms
     **/
    private static final Integer INTERVALS = 168000;

    /**
     * 允许跨域的请求方法
     **/
    private final String[] METHODS = {"POST", "GET", "PUT", "OPTIONS", "DELETE"};

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        /**
         * 1.允许跨域访问的路径
         * 2.允许跨域访问的源
         * 3.允许请求方法
         * 4.预检间隔时间
         * 5.允许头部设置
         * 6.是否发送cookie
         **/
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods(METHODS)
                .maxAge(INTERVALS)
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}