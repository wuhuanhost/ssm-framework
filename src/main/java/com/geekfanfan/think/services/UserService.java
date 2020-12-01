/*
 * @Author: Dreamer
 * @Site: https://www.geekfanfan.com
 * @Date: 2020-11-26 09:33:41
 * @Email: wuhuanhost@163.com
 * @LastEditors: Dreamer
 * @LastEditTime: 2020-12-01 16:11:54
 */
package com.geekfanfan.think.services;

import java.util.List;

import com.geekfanfan.think.entity.User;

import org.springframework.stereotype.Service;

/**
 * UserService
 */
@Service
public interface UserService {

	// 获取所有的用户信息
	public List<User> getAllUser();

	// 转账事务测试
	public boolean transferMoney(int fromUserId, int toUserId, double money) throws Exception;

}
