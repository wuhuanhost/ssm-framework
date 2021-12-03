/*
 * @Author: Dreamer
 * @Site: https://www.geekfanfan.com
 * @Date: 2020-11-26 09:33:41
 * @Email: wuhuanhost@163.com
 * @LastEditors: Dreamer
 * @LastEditTime: 2021-12-03 14:49:56
 */
package com.geekfanfan.think.services;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.geekfanfan.think.entity.User;

import org.springframework.stereotype.Service;

/**
 * UserService
 */
@Service
public interface UserService {

	// 获取所有的用户信息
	public IPage<User> getAllUser(Page<?> page);

	// 转账事务测试
	public boolean transferMoney(int fromUserId, int toUserId, double money) throws Exception;

}
