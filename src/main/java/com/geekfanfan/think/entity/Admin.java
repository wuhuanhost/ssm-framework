/*
 * @Author: Dreamer
 * @Site: https://www.geekfanfan.com
 * @Date: 2021-12-07 10:31:01
 * @Email: wuhuanhost@163.com
 * @LastEditors: Dreamer
 * @LastEditTime: 2021-12-08 09:54:06
 */
package com.geekfanfan.think.entity;

import java.util.List;

import javax.print.DocFlavor.STRING;

import org.springframework.security.core.GrantedAuthority;

import lombok.Data;

@Data
public class Admin {
	private Integer id;// id 主键
	private String username;// 用户名
	private String password;// 密码
	private String email;// 邮件地址
	private String phone;// 电话
	private Integer status;// 账号状态
	private String token;// token
	private String createdAt;// 创建时间
	private String updatedAt;// 修改时间
	public List<Role> roles;// 角色集合

}
