package com.geekfanfan.think.entity;

import lombok.Data;

/*
 * @Author: Dreamer
 * 
 * @Site: https://www.geekfanfan.com
 * 
 * @Date: 2021-12-07 10:41:15
 * 
 * @Email: wuhuanhost@163.com
 * 
 * @LastEditors: Dreamer
 * 
 * @LastEditTime: 2021-12-07 10:41:15
 */

@Data
public class AdminRole {
	private Integer id;// id 主键
	private Admin admin;// 用户
	private Role role;// 角色
	private String createdAt;// 创建时间
	private String updatedAt;// 修改时间
}
