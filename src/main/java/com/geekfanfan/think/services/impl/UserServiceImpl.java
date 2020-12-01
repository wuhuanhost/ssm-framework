/*
 * @Author: Dreamer
 * @Site: https://www.geekfanfan.com
 * @Date: 2020-11-26 09:35:52
 * @Email: wuhuanhost@163.com
 * @LastEditors: Dreamer
 * @LastEditTime: 2020-12-01 16:48:27
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
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;

	@Override
	public List<User> getAllUser() {
		// 获取所有的用户
		// 通过此方法抛出异常，会被全局捕获返回给前端
		// Asserts.error(ResultCode.SUCCESS);
		// System.out.println(10 / 0);

		return userMapper.listAll();
	}

	// 事务测试
	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean transferMoney(int fromUserId, int toUserId, double money) throws Exception {

		// 转出
		boolean b1 = userMapper.transferOut(fromUserId, money);
		// 模拟失败

		// int b = 10 / 0;
		// Asserts.error("发生异常事务回滚,此次转账失败！");

		// System.out.println(b);

		// 转入
		boolean b2 = userMapper.transferIn(toUserId, money);

		return b1 && b2;
	}

}
