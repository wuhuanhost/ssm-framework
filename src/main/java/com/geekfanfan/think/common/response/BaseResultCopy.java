/*
 * @Author: Dreamer
 * @Site: https://www.geekfanfan.com
 * @Date: 2020-11-20 12:34:28
 * @Email: wuhuanhost@163.com
 * @LastEditors: Dreamer
 * @LastEditTime: 2020-11-26 10:13:36
 */
package com.geekfanfan.think.common.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

// import com.google.common.collect.Maps;

import org.springframework.web.bind.annotation.ExceptionHandler;

//和以前唯一不一样的地方就是加了一个泛型T
@Data
@ApiModel("通用返回对象") // 注释这个类的信息
public class BaseResultCopy<T> implements Serializable {
	// 解释各字段的意思
	@ApiModelProperty(value = "返回码", dataType = "Integer", example = "200")
	private int code;
	@ApiModelProperty(value = "提示信息", dataType = "String", example = "success")
	private String msg;
	@ApiModelProperty(value = "返回值", dataType = "Object", example = "{}")
	private T data;

	// 几种构造方法
	public BaseResultCopy() {
	}

	public BaseResultCopy(Integer status, String msg) {
		this.code = status;
		this.msg = msg;

	}

	public BaseResultCopy(Integer status, String msg, T data) {
		this.code = status;
		this.msg = msg;
		if (data != null) {
			this.data = data;
		}
	}

	// 静态方法要使用泛型参数的话，要声明其为泛型方法
	public static <T> BaseResultCopy<T> success(T data) {
		return new BaseResultCopy<T>(200, messageMap.get(200), data);
	}

	public static <T> BaseResultCopy<T> success(Integer status, String msg, T data) {
		return new BaseResultCopy<T>(status, messageMap.get(status), data);
	}

	public static <T> BaseResultCopy<T> error(Integer status, String msg) {
		String message = msg;
		if (message == null || message.equals("")) {
			message = messageMap.get(status);
		}
		return new BaseResultCopy<T>(status, message, null);
	}

	// private static Map<Integer, String> messageMap = Maps.newHashMap();
	private static Map<Integer, String> messageMap = new HashMap();
	// 初始化状态码与文字说明
	static {
		/* 成功状态码 */
		messageMap.put(200, "成功");

		/* 服务器错误 */
		messageMap.put(500, "服务器错误");

		/* 参数错误：10001-19999 */
		messageMap.put(10001, "参数无效");
		messageMap.put(10002, "参数为空");
		messageMap.put(10003, "参数类型错误");
		messageMap.put(10004, "参数缺失");

		/* 用户错误：20001-29999 */
		messageMap.put(20001, "用户未登录");
		messageMap.put(20002, "账号不存在或密码错误");
		messageMap.put(20003, "账号已被禁用");
		messageMap.put(20004, "用户不存在");
		messageMap.put(20005, "用户已存在");

		/* 业务错误：30001-39999 */
		messageMap.put(30001, "某业务出现问题");

		/* 系统错误：40001-49999 */
		messageMap.put(40001, "系统繁忙，请稍后重试");

		/* 数据错误：50001-599999 */
		messageMap.put(50001, "数据未找到");
		messageMap.put(50002, "数据有误");
		messageMap.put(50003, "数据已存在");
		messageMap.put(50004, "查询出错");

		/* 接口错误：60001-69999 */
		messageMap.put(60001, "内部系统接口调用异常");
		messageMap.put(60002, "外部系统接口调用异常");
		messageMap.put(60003, "该接口禁止访问");
		messageMap.put(60004, "接口地址无效");
		messageMap.put(60005, "接口请求超时");
		messageMap.put(60006, "接口负载过高");

		/* 权限错误：70001-79999 */
		messageMap.put(70001, "无权限访问");
	}

}