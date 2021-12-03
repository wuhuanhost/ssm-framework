/*
 * @Author: Dreamer
 * @Site: https://www.geekfanfan.com
 * @Date: 2020-11-18 17:18:37
 * @Email: wuhuanhost@163.com
 * @LastEditors: Dreamer
 * @LastEditTime: 2021-12-03 16:51:48
 */
package com.geekfanfan.think.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.print.DocFlavor.STRING;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.geekfanfan.think.common.job.PropUtils;
import com.geekfanfan.think.common.job.QuartzManager;
import com.geekfanfan.think.common.job.WorkJob;
import com.geekfanfan.think.common.response.BaseResult;
import com.geekfanfan.think.common.response.ResultCode;
import com.geekfanfan.think.entity.User;
import com.geekfanfan.think.mapper.UserMapper;
import com.geekfanfan.think.services.UserService;
import com.geekfanfan.think.util.JWTUtils;
import com.geekfanfan.think.util.RedisUtil;
// import com.github.pagehelper.PageHelper;
// import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@RestController // 这种注解相当于@Controll注解然后在每个方法前面加上@ResponseBody
@Slf4j
@Api(tags = "HelloController", description = "测试文档生成")
@Validated
public class HelloController {

	@Autowired
	private UserService userService; // 注入services

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
		// 这里应该用相对地址
		String command = PropUtils.getPropValue(
				"E:\\workspace\\MyProject\\ssm-framework\\src\\main\\java\\com\\geekfanfan\\think\\utils\\job\\prop.properties",
				"command");
		String cron = PropUtils.getPropValue(
				"E:\\workspace\\MyProject\\ssm-framework\\src\\main\\java\\com\\geekfanfan\\think\\utils\\job\\prop.properties",
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
		boolean b1 = redisUtil.set("user-info1", userInfo, 20);// 20秒之后缓存过期
		System.out.println(redisUtil.get("user-info"));
		if (b && b1) {
			System.out.println(redisUtil.get("user-info"));
		} else {
			System.out.println("获取失败");
		}
		return redisUtil.get("user-info");
	}

	/**
	 * mysql测试
	 */

	@RequestMapping(value = "/mysql", method = RequestMethod.GET)
	@ApiOperation(value = "测试mysql", produces = "application/json", httpMethod = "GET")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long", paramType = "query"),
			@ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User", paramType = "query") })
	@ApiResponses({ @ApiResponse(code = 200, message = "success"), @ApiResponse(code = 400, message = "Invalid Order") })
	public BaseResult<IPage<User>> mysql() {
		// 分页数据（获取第2页，每个页面1条数据）
		Page<User> userPage = new Page<>(2, 1);
		// 分页之后的数据
		IPage<User> userIPage = userService.getAllUser(userPage);
		System.out.println("总页数： " + userIPage.getPages());
		System.out.println("总记录数： " + userIPage.getTotal());
		// 迭代分页数据
		userIPage.getRecords().forEach(System.out::println);

		return BaseResult.success(userIPage);
	}

	@ApiOperation(value = "获取person json返回值", notes = "该操作不会展示嵌套的数据注释")
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	@ApiResponses({ @ApiResponse(code = 200, message = "success"), @ApiResponse(code = 400, message = "Invalid Order") })
	public BaseResult<User> findPerson() {
		User user = new User();
		user.setId(1);
		user.setUsername("username");
		user.setPassword("password");
		return BaseResult.success(user);
	}

	@Resource
	UserMapper userMapper;

	@ApiOperation(value = "添加用户", notes = "该操作不会展示嵌套的数据注释", produces = "application/json", httpMethod = "POST")
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	@ApiResponses({ @ApiResponse(code = 200, message = "success"), @ApiResponse(code = 400, message = "Invalid Order") })
	public BaseResult<Integer> addUser(@Valid @RequestBody User user, BindingResult result) {
		System.out.println("-----------");
		System.out.println(result);
		System.out.println("-----------");
		if (result.hasErrors()) {
			List<ObjectError> errorList = result.getAllErrors();
			for (ObjectError error : errorList) {
				System.out.println(error.getDefaultMessage());
			}
		}
		log.info("用户名：" + user.getUsername() + "   密码：" + user.getPassword());
		Integer i = userMapper.insert(user);
		return BaseResult.success(i);
	}

	@ApiOperation(value = "转账事务测试", notes = "该操作不会展示嵌套的数据注释", produces = "application/json", httpMethod = "GET")
	@RequestMapping(value = "/transfer", method = RequestMethod.GET)
	@ApiResponses({ @ApiResponse(code = 200, message = "success"), @ApiResponse(code = 400, message = "Invalid Order") })
	public BaseResult<Boolean> transferMoney(
			@Min(value = 1, message = "转账用户id不正确") @RequestParam(name = "fromUserId") int fromUserId,
			@Min(value = 1, message = "被转账用户id不正确") @RequestParam(name = "toUserId") int toUserId,
			@Min(value = 1, message = "金额必须大于0") @RequestParam(name = "money") double money) throws Exception {
		System.out.println(fromUserId + "------------1-" + toUserId + "---------------------2-" + money);
		Boolean b;
		log.error("..............................");
		try {
			b = userService.transferMoney(fromUserId, toUserId, money);
		} catch (Exception e) {
			log.error("{}" + e.getMessage(), e);
			return BaseResult.error(e.getMessage());
		}
		if (!b) {
			return BaseResult.error(ResultCode.FAILED, "转账失败");
		}
		return BaseResult.success(b);
	}

	/**
	 * 登录
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "/login123", method = RequestMethod.GET)
	public BaseResult<String> login(@RequestParam String username, @RequestParam String password) {
		if (username.equals("admin") && password.equals("123456")) {
			User u = new User();
			u.setPassword(password);
			u.setUsername(username);
			u.setId(123456789);
			return BaseResult.success(JWTUtils.getToken(u));
		}

		return BaseResult.success("");
	}

	/**
	 * 登录之后进入首页的方法
	 * 
	 * @return
	 */
	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public BaseResult<HashMap<String, String>> Dashboard(@RequestHeader String token) throws Exception {
		Integer uid = JWTUtils.getUserId(token);
		log.info(token);
		log.info("------------------------------");
		log.info(uid + "+++++++++++");
		log.info("------------------------------");
		HashMap mp = new HashMap<String, String>();
		mp.put(uid, uid);
		mp.put("user-info", redisUtil.get("user-info1"));// 获取redis中的用户信息
		return BaseResult.success(mp);
	}
}
