/*
 * @Author: Dreamer
 * @Site: https://www.geekfanfan.com
 * @Date: 2020-11-20 12:34:28
 * @Email: wuhuanhost@163.com
 * @LastEditors: Dreamer
 * @LastEditTime: 2020-11-26 11:13:26
 */
package com.geekfanfan.think.utils.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;

import com.google.common.collect.Maps;

import org.springframework.web.bind.annotation.ExceptionHandler;

//和以前唯一不一样的地方就是加了一个泛型T
@Data
@ApiModel("通用返回对象") // 注释这个类的信息
public class BaseResult<T> implements Serializable {
	// 解释各字段的意思
	@ApiModelProperty(value = "返回码", dataType = "Integer", example = "200")
	private int code;
	@ApiModelProperty(value = "提示信息", dataType = "String", example = "success")
	private String msg;
	@ApiModelProperty(value = "返回值", dataType = "Object", example = "{}")
	private T data;

	protected BaseResult() {
	}

	protected BaseResult(int code, String msg, T data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	/**
	 * 成功返回结果
	 *
	 * @param data 获取的数据
	 */
	public static <T> BaseResult<T> success(T data) {
		return new BaseResult<T>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg(), data);
	}

	/**
	 * 成功返回结果
	 *
	 * @param data 获取的数据
	 * @param msg  提示信息
	 */
	public static <T> BaseResult<T> success(T data, String msg) {
		return new BaseResult<T>(ResultCode.SUCCESS.getCode(), msg, data);
	}

	/**
	 * 失败返回结果
	 * 
	 * @param errorCode 错误码
	 */
	public static <T> BaseResult<T> error(IErrorCode errorCode) {
		return new BaseResult<T>(errorCode.getCode(), errorCode.getMsg(), null);
	}

	/**
	 * 失败返回结果
	 * 
	 * @param errorCode 错误码
	 * @param msg       错误信息
	 */
	public static <T> BaseResult<T> error(IErrorCode errorCode, String msg) {
		return new BaseResult<T>(errorCode.getCode(), msg, null);
	}

	/**
	 * 失败返回结果
	 * 
	 * @param msg 提示信息
	 */
	public static <T> BaseResult<T> error(String msg) {
		return new BaseResult<T>(ResultCode.ERROR.getCode(), msg, null);
	}

	/**
	 * 失败返回结果
	 */
	public static <T> BaseResult<T> error() {
		return error(ResultCode.ERROR);
	}

}