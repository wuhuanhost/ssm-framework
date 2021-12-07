/*
 * @Author: Dreamer
 * @Site: https://www.geekfanfan.com
 * @Date: 2021-12-07 10:45:23
 * @Email: wuhuanhost@163.com
 * @LastEditors: Dreamer
 * @LastEditTime: 2021-12-07 13:51:53
 */
package com.geekfanfan.think.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.geekfanfan.think.entity.Admin;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminMapper extends BaseMapper<Admin> {
	// 查询所有的用户
	List<Admin> queryAdmins();

	// 分页查询所有的用户
	IPage<Admin> queryAdmins(Page<?> page);
}
