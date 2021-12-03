/*
 * @Author: Dreamer
 * @Site: https://www.geekfanfan.com
 * @Date: 2020-11-18 14:12:12
 * @Email: wuhuanhost@163.com
 * @LastEditors: Dreamer
 * @LastEditTime: 2021-12-02 15:53:14
 */
package com.geekfanfan.think.controller;

import java.util.HashMap;

import com.geekfanfan.think.common.response.BaseResult;
import com.geekfanfan.think.entity.User;
import com.geekfanfan.think.util.JWTUtils;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HomeController {
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(HashMap<String, Object> map) {
		map.put("hello", "欢迎进入HTML页面>>>>");
		System.err.println("-------------------------");
		log.error("Something else is wrong here");
		return "index"; // 返回一个页面，如果要想返回json数据，需要在方法前面加上@ResponseBody注解
	}

}