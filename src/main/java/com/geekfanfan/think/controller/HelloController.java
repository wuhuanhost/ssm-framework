/*
 * @Author: Dreamer
 * @Site: https://www.geekfanfan.com
 * @Date: 2020-11-18 17:18:37
 * @Email: wuhuanhost@163.com
 * @LastEditors: Dreamer
 * @LastEditTime: 2020-11-23 17:21:11
 */
package com.geekfanfan.think.controller;

import java.util.List;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSONObject;
import com.geekfanfan.think.bean.User;
import com.geekfanfan.think.job.PropUtils;
import com.geekfanfan.think.job.QuartzManager;
import com.geekfanfan.think.job.WorkJob;
import com.geekfanfan.think.mapper.UserMapper;
import com.geekfanfan.think.response.BaseResult;

import com.geekfanfan.think.utils.RedisUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.apache.ibatis.jdbc.Null;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@Api(tags = "HelloController", description = "测试文档生成")
public class HelloController {
	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	@ApiOperation(value = "测试hello world", notes = "notes......")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		String str = "<b>sdf</b>";
		StringBuffer response = new StringBuffer(name);
		response.append(str);
		return String.format("Hello %s!", response);
	}

	// 返回json数据
	// @RequestMapping(value = "/json", method = RequestMethod.GET)
	@GetMapping(value = "/json")
	@ApiOperation(value = "测试返回json", produces = "application/json", httpMethod = "GET")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long", paramType = "query"),
			@ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User", paramType = "query") })
	@ApiResponses({ @ApiResponse(code = 200, message = "success"), @ApiResponse(code = 400, message = "Invalid Order") })

	public BaseResult<JSONObject> json() {
		JSONObject userInfo = new JSONObject();
		userInfo.put("name", "liming");
		userInfo.put("age", "18");
		userInfo.put("sex", "男");
		JSONObject userScore = new JSONObject();
		userScore.put("math", 100);
		userScore.put("chinese", 90);
		userScore.put("english", 80);
		userInfo.put("score", userScore);
		User user = new User();
		user.setId(1);
		user.setUsername("username");
		user.setPassword("password");
		// System.out.println(1 / 0);// 模拟异常处理
		return BaseResult.success(userInfo);
	}

	/**
	 * 定时任务测试
	 * 
	 * @return
	 */
	@RequestMapping(value = "/task", method = RequestMethod.GET)
	@ApiOperation("测试定时任务")
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
	 * redis测试
	 * 
	 * 
	 * @return
	 */
	@RequestMapping(value = "/redis", method = RequestMethod.GET)
	@ApiOperation("测试redis")
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

	/**
	 * mysql测试
	 */
	@Resource
	private UserMapper userMapper;

	@RequestMapping(value = "/mysql", method = RequestMethod.GET)
	@ApiOperation(value = "测试mysql", produces = "application/json", httpMethod = "GET")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long", paramType = "query"),
			@ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User", paramType = "query") })
	@ApiResponses({ @ApiResponse(code = 200, message = "success"), @ApiResponse(code = 400, message = "Invalid Order") })
	public BaseResult<PageInfo<User>> mysql() {
		log.debug("debug......");
		log.info("info......");
		log.warn("warn......");
		log.error("error......");
		PageHelper.startPage(1, 1);
		List<User> userList = userMapper.listAll();
		PageInfo<User> pageInfo = new PageInfo<>(userList);
		System.out.println(pageInfo);
		return BaseResult.success(pageInfo);
	}

	@ApiOperation(value = "获取person json返回值", notes = "该操作不会展示嵌套的数据注释")
	@PostMapping("/user")
	@ApiResponses({ @ApiResponse(code = 200, message = "success"), @ApiResponse(code = 400, message = "Invalid Order") })
	public BaseResult<User> findPerson() {
		User user = new User();
		user.setId(1);
		user.setUsername("username");
		user.setPassword("password");
		return BaseResult.success(user);
	}
}
