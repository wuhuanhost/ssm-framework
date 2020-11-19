/*
 * @Author: Dreamer
 * @Site: https://www.geekfanfan.com
 * @Date: 2020-11-18 17:18:37
 * @Email: wuhuanhost@163.com
 * @LastEditors: Dreamer
 * @LastEditTime: 2020-11-19 16:18:51
 */
package com.geekfanfan.think.controller;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSONObject;
import com.geekfanfan.think.job.PropUtils;
import com.geekfanfan.think.job.QuartzManager;
import com.geekfanfan.think.job.WorkJob;
import com.geekfanfan.think.utils.RedisUtil;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
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

	/**
	 * 定时任务测试
	 * 
	 * @return
	 */
	@GetMapping("/task")
	public String task() {
		String command = PropUtils.getPropValue(
				"E:\\workspace\\MyProject\\ssm-framework\\src\\main\\java\\com\\geekfanfan\\think\\job\\prop.properties",
				"command");
		String cron = PropUtils.getPropValue(
				"E:\\workspace\\MyProject\\ssm-framework\\src\\main\\java\\com\\geekfanfan\\think\\job\\prop.properties",
				"cron");
		System.out.println("cron时间表达式:" + cron);
		System.out.println("command执行命令：" + command);
		log.info("cron时间表达式:" + cron);
		log.info("command执行命令：" + command);
		QuartzManager.addJob("workJob", WorkJob.class, cron, command);
		return "定时任务启动 success";
	}

	@Autowired
	private RedisUtil redisUtil;

	/**
	 * redis
	 * 
	 * 
	 * @return
	 */
	@GetMapping("/redis")
	public Object redis() {

		JSONObject userInfo = new JSONObject();
		userInfo.put("name", "liming");
		userInfo.put("age", "18");
		userInfo.put("sex", "男");
		JSONObject userScore = new JSONObject();
		userScore.put("math", 100);
		userScore.put("chinese", 90);
		userScore.put("english", 80);
		userInfo.put("score", userScore);

		boolean b = redisUtil.set("user-info", userInfo);
		System.out.println(redisUtil.get("user-info"));
		if (b) {
			System.out.println(redisUtil.get("user-info"));
		} else {
			System.out.println("获取失败");
		}
		return redisUtil.get("user-info");
	}
}
