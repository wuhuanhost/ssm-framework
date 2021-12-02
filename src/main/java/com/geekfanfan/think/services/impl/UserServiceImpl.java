/*
 * @Author: Dreamer
 * @Site: https://www.geekfanfan.com
 * @Date: 2020-11-26 09:35:52
 * @Email: wuhuanhost@163.com
 * @LastEditors: Dreamer
 * @LastEditTime: 2021-12-02 11:09:56
 */
package com.geekfanfan.think.services.impl;

import java.util.List;

import com.geekfanfan.think.common.exception.Asserts;
import com.geekfanfan.think.common.response.IErrorCode;
import com.geekfanfan.think.common.response.ResultCode;
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
	@Transactional(rollbackFor = Exception.class) // 如果类加了这个注解，那么这个类里面的方法抛出异常，就会回滚，数据库里面的数据也会回滚。在@Transactional注解中如果不配置rollbackFor属性,那么事物只会在遇到RuntimeException的时候才会回滚,加上rollbackFor=Exception.class,可以让事物在遇到非运行时异常时也回滚
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
		// 手动抛出异常，会被定义的全局异常捕获工具拦截
		// Asserts.error(ResultCode.TRANSFER_FAILED);

		// System.out.println(b);

		// 转入
		boolean b2 = userMapper.transferIn(toUserId, money);

		System.out.println(b1 + "-------------------" + b2);

		return b1 && b2;
	}

}
