/*
 * @Author: Dreamer
 * @Site: https://www.geekfanfan.com
 * @Date: 2020-11-18 17:18:37
 * @Email: wuhuanhost@163.com
 * @LastEditors: Dreamer
 * @LastEditTime: 2020-11-19 10:12:08
 */
package com.geekfanfan.think.controller;

import com.alibaba.fastjson.JSONObject;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		String str = "<b>sdf</b>";
		StringBuffer response = new StringBuffer(name);
		response.append(str);
		return String.format("Hello %s!", response);
	}

	// 返回json数据
	@GetMapping("/json")
	public JSONObject json() {
		JSONObject userInfo = new JSONObject();
		userInfo.put("name", "liming");
		userInfo.put("age", "18");
		userInfo.put("sex", "男");
		JSONObject userScore = new JSONObject();
		userScore.put("math", 100);
		userScore.put("chinese", 90);
		userScore.put("english", 80);
		userInfo.put("score", userScore);
		return userInfo;
	}
}
