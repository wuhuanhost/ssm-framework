package com.geekfanfan.think.entity;

import io.swagger.annotations.ApiModelProperty;
/*
 * @Author: Dreamer
 * @Site: https://www.geekfanfan.com
 * @Date: 2020-11-19 16:50:54
 * @Email: wuhuanhost@163.com
 * @LastEditors: Dreamer
 * @LastEditTime: 2020-11-25 10:41:40
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
	@ApiModelProperty(value = "id", required = true)
	private Integer id;

	/**
	 * 用户名
	 */
	@ApiModelProperty(value = "用户名", required = true)
	private String username;

	/**
	 * 密码
	 */
	@ApiModelProperty(value = "密码", required = true)
	private String password;

}