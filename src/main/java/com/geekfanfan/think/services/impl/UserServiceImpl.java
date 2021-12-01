/*
 * @Author: Dreamer
 * @Site: https://www.geekfanfan.com
 * @Date: 2020-11-26 09:35:52
 * @Email: wuhuanhost@163.com
 * @LastEditors: Dreamer
 * @LastEditTime: 2021-08-10 11:05:44
 */
package com.geekfanfan.think.services.impl;

import java.util.List;

import com.geekfanfan.think.common.exception.Asserts;
import com.geekfanfan.think.entity.User;
import com.geekfanfan.think.mapper.UserMapper;
import com.geekfanfan.think.services.UserService;

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

		// int b = 0;
		// try {
		// b = 10 / 0;
		// } catch (Exception e) {
		// Asserts.error("被除数不能为0！");
		// }
		int a = 5;
		double b = a / 2;
		System.out.println(a);
		System.out.println(b);
		// Asserts.error("发生异常事务回滚,此次转账失败！");

		// System.out.println(b);

		// 转入
		boolean b2 = userMapper.transferIn(toUserId, money);

		System.out.println(b1 + "-------------------" + b2);

		return b1 && b2;
	}

}
