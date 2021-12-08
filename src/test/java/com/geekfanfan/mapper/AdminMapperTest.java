package com.geekfanfan.mapper;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.geekfanfan.think.ThinkApplication;
import com.geekfanfan.think.common.response.BaseResult;
import com.geekfanfan.think.entity.Admin;
import com.geekfanfan.think.entity.Role;
import com.geekfanfan.think.mapper.AdminMapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.slf4j.Slf4j;

/*
 * @Author: Dreamer
 * 
 * @Site: https://www.geekfanfan.com
 * 
 * @Date: 2021-12-07 11:40:52
 * 
 * @Email: wuhuanhost@163.com
 * 
 * @LastEditors: Dreamer
 * 
 * @LastEditTime: 2021-12-07 11:40:52
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = { ThinkApplication.class })
@Slf4j
public class AdminMapperTest {
	@Autowired
	private AdminMapper adminMapper;

	/**
	 * 查询所有的系统用户
	 */
	@Test
	public void queryAllAdmins() {
		List<Admin> admins = adminMapper.queryAdmins();

		for (Admin admin : admins) {
			log.info("==========================================================>");
			log.info(admin.getUsername());
			log.info("用户的角色为:");
			List<Role> roles = admin.roles;
			for (Role role : roles) {
				log.info(role.getName());
			}
		}
		log.info("============================================================>");
		String result = JSON.toJSONString(admins);
		log.info(result);

	}

	/**
	 * 分页查询所有的用户信息
	 */
	@Test
	public void queryAllAdminsByPage() {
		Page<Admin> adminPage = new Page<>(1, 10);
		// 分页之后的数据
		IPage<Admin> userIPage = adminMapper.queryAdmins(adminPage);
		// 迭代分页数据
		userIPage.getRecords().forEach(System.out::println);
		log.info("=============================================>");
		log.info(JSON.toJSONString(BaseResult.success(userIPage)));
	}

}
