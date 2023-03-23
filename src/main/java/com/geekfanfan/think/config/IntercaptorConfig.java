/*
 * @Author: Dreamer
 * @Site: https://www.geekfanfan.com
 * @Date: 2021-12-02 14:04:07
 * @Email: wuhuanhost@163.com
 * @LastEditors: Dreamer
 * @LastEditTime: 2021-12-02 15:33:22
 */
package com.geekfanfan.think.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

import com.geekfanfan.think.controller.interceptor.JWTInterceptor;

/**
 * 拦截器配置，拦截所有的url请求，拦截器在过滤器之后执行
 * 
 * @author
 */
@Configuration
public class IntercaptorConfig implements WebMvcConfigurer {

    @Bean
    public JWTInterceptor JwtInterceptor() {
        return new JWTInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册拦截器
        registry.addInterceptor(
                JwtInterceptor())
                // 拦截的路径
                .addPathPatterns("/**")
                // 排除的接口和目录
                .excludePathPatterns("/doc.html", "/webjars/**", "/swagger-resources", "/login123", "/home");

    }
}