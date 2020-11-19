
/*
 * @Author: Dreamer
 * @Site: https://www.geekfanfan.com
 * @Date: 2020-11-19 16:48:43
 * @Email: wuhuanhost@163.com
 * @LastEditors: Dreamer
 * @LastEditTime: 2020-11-19 16:57:01
 */
package com.geekfanfan.think.mapper;

import com.geekfanfan.think.bean.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * created with IntelliJ IDEA. author: fxbin date: 2018/10/21 time: 5:55
 * version: 1.0 description:
 */
@Mapper
public interface UserMapper {

	@Select({ "select * from user" })
	List<User> listAll();

	@Insert({ "insert into user(`username`, `password`) values(#{username}, #{password})" })
	int insert(User user);

	@Delete({ "delete from user where id = #{userId}" })
	int remove(Integer userId);

	@Update({ "update user set username = #{username}, password = #{password} where id = #{id}" })
	int update(User user);
}