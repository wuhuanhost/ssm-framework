package com.geekfanfan.think.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
 * @Author: Dreamer
 * 
 * @Site: https://www.geekfanfan.com
 * 
 * @Date: 2021-12-03 11:44:47
 * 
 * @Email: wuhuanhost@163.com
 * 
 * @LastEditors: Dreamer
 * 
 * @LastEditTime: 2021-12-03 11:44:48
 */
@Configuration
@MapperScan("com.geekfanfan.think.*.mapper*")
public class MyBatisPlusConfig {
	// 最新版分页插件的配置
	@Bean
	public MybatisPlusInterceptor mybatisPlusInterceptor() {
		MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
		interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.H2));
		return interceptor;
	}
}
