/*
 * @Author: Dreamer
 * @Site: https://www.geekfanfan.com
 * @Date: 2021-12-07 10:37:47
 * @Email: wuhuanhost@163.com
 * @LastEditors: Dreamer
 * @LastEditTime: 2021-12-07 10:44:05
 */
package com.geekfanfan.think.entity;

import java.util.List;

import lombok.Data;

@Data
public class Role {
	private Integer id;// id 主键，自动增加
	private String name;// 角色名称
	private Integer status;// 角色状态
	private String remark;// 角色说明
	private Integer order;// 角色排序字段
	private String createdAt;// 创建时间
	private String updatedAt;// 修改时间

	public List<Admin> admins;// 系统管理员集合
}
