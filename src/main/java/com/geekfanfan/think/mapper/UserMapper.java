
/*
 * @Author: Dreamer
 * @Site: https://www.geekfanfan.com
 * @Date: 2020-11-19 16:48:43
 * @Email: wuhuanhost@163.com
 * @LastEditors: Dreamer
 * @LastEditTime: 2021-12-03 14:45:43
 */
package com.geekfanfan.think.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.geekfanfan.think.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * created with IntelliJ IDEA. author: fxbin date: 2018/10/21 time: 5:55
 * version: 1.0 description:
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

	// @Select({ "select * from user" })
	// List<User> listAll();

	IPage<User> listAll(Page<?> page);

	// 转入
	boolean transferIn(int id, double money);

	// 转出
	boolean transferOut(int id, double money);

	@Insert({ "insert into user(`username`, `password`,	`money`) values(#{username}, #{password}, #{money})" })
	int insert(User user);

	@Delete({ "delete from user where id = #{userId}" })
	int remove(Integer userId);

	@Update({ "update user set username = #{username}, password = #{password} where id = #{id}" })
	int update(User user);
}