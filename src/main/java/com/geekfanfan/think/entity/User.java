package com.geekfanfan.think.entity;

import java.math.BigInteger;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import io.swagger.annotations.ApiModelProperty;
/*
 * @Author: Dreamer
 * @Site: https://www.geekfanfan.com
 * @Date: 2020-11-19 16:50:54
 * @Email: wuhuanhost@163.com
 * @LastEditors: Dreamer
 * @LastEditTime: 2020-12-01 09:37:40
 */
import lombok.Data;

/**
 * created with IntelliJ IDEA. author: fxbin date: 2018/10/21 time: 5:59
 * version: 1.0 description:
 */
@Data
public class User {

	/**
	 * 主键ID
	 */
	@ApiModelProperty(value = "id", required = false)
	private Integer id;

	/**
	 * 用户名
	 */
	@ApiModelProperty(value = "用户名", required = true)
	@NotBlank(message = "用户名不能为空")
	@Pattern(regexp = ".{6,}", message = "用户名长度必须大于6个字符")
	private String username;

	/**
	 * 密码
	 */
	@ApiModelProperty(value = "密码", required = true)
	@NotBlank(message = "密码不能为空")
	private String password;

	@ApiModelProperty(value = "余额", required = false)
	private double money;

}