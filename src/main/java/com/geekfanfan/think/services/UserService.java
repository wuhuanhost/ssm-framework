/*
 * @Author: Dreamer
 * @Site: https://www.geekfanfan.com
 * @Date: 2020-11-26 09:33:41
 * @Email: wuhuanhost@163.com
 * @LastEditors: Dreamer
 * @LastEditTime: 2020-11-26 09:44:01
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

	public List<User> getAllUser();

}
