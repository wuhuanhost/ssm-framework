/*
 * @Author: Dreamer
 * @Site: https://www.geekfanfan.com
 * @Date: 2020-11-26 09:35:52
 * @Email: wuhuanhost@163.com
 * @LastEditors: Dreamer
 * @LastEditTime: 2020-11-26 11:14:30
 */
package com.geekfanfan.think.services.impl;

import java.util.List;

import com.geekfanfan.think.entity.User;
import com.geekfanfan.think.mapper.UserMapper;
import com.geekfanfan.think.utils.response.ResultCode;
import com.geekfanfan.think.services.UserService;
import com.geekfanfan.think.utils.exception.Asserts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;

	@Override
	public List<User> getAllUser() {
		// 获取所有的用户
		// 抛出异常

		// Asserts.error(ResultCode.SUCCESS);
		// System.out.println(10 / 0);

		return userMapper.listAll();
	}

}
