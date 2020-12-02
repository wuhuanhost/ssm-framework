/*
 * @Author: Dreamer
 * @Site: https://www.geekfanfan.com
 * @Date: 2020-12-02 14:19:20
 * @Email: wuhuanhost@163.com
 * @LastEditors: Dreamer
 * @LastEditTime: 2020-12-02 14:34:18
 */
package com.geekfanfan.think.services;

import com.geekfanfan.think.ThinkApplication;
import com.geekfanfan.think.services.impl.UserServiceImpl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = { ThinkApplication.class })
public class UserServiceTest {

	@Autowired
	private UserServiceImpl userServiceImpl;

	@Test
	public void testTransferMoney() {
		try {
			userServiceImpl.transferMoney(53, 52, 100);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
