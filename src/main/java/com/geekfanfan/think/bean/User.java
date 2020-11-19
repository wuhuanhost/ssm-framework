package com.geekfanfan.think.bean;

/*
 * @Author: Dreamer
 * @Site: https://www.geekfanfan.com
 * @Date: 2020-11-19 16:50:54
 * @Email: wuhuanhost@163.com
 * @LastEditors: Dreamer
 * @LastEditTime: 2020-11-19 16:57:14
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
	private Integer id;

	/**
	 * 用户名
	 */
	private String username;

	/**
	 * 密码
	 */
	private String password;

}