/*
 * @Author: Dreamer
 * @Site: https://www.geekfanfan.com
 * @Date: 2020-11-25 17:14:45
 * @Email: wuhuanhost@163.com
 * @LastEditors: Dreamer
 * @LastEditTime: 2021-12-02 14:08:06
 */
package com.geekfanfan.think.config;

import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * 普通模式（默认是这个模式）: 会校验完所有的属性，然后返回所有的验证失败信息 快速失败模式: 只要有一个验证失败，则返回
 * 如果想要配置第二种模式，需要添加如下配置类：
 */
@Configuration
public class ValidatorConfig {
	@Bean
	public Validator validator() {
		ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class).configure().failFast(true)
				.buildValidatorFactory();
		Validator validator = validatorFactory.getValidator();

		return validator;
	}
}