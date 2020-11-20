/*
 * @Author: Dreamer
 * @Site: https://www.geekfanfan.com
 * @Date: 2020-11-18 14:12:12
 * @Email: wuhuanhost@163.com
 * @LastEditors: Dreamer
 * @LastEditTime: 2020-11-20 10:05:22
 */
package com.geekfanfan.think.controller;

import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HomeController {
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(HashMap<String, Object> map) {
		map.put("hello", "欢迎进入HTML页面");
		System.err.println("-------------------------");
		log.error("Something else is wrong here");
		return "index";
	}

}