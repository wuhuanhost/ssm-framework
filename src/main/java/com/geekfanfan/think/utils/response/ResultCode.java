/*
 * @Author: Dreamer
 * @Site: https://www.geekfanfan.com
 * @Date: 2020-11-26 09:59:55
 * @Email: wuhuanhost@163.com
 * @LastEditors: Dreamer
 * @LastEditTime: 2020-12-02 09:30:38
 */
package com.geekfanfan.think.utils.response;

/**
 * 枚举了一些常用API操作码 Created by macro on 2019/4/19.
 */
public enum ResultCode implements IErrorCode {
	// =================成功===================
	SUCCESS(200, "操作成功"),
	// ================服务器错误，系统相关=================
	ERROR(500, "操作失败"),
	// ==========================失败，业务相关====================
	FAILED(1000, "失败"),
	// 参数检验失败
	VALIDATE_FAILED(501, "参数检验失败"),
	// 授权相关
	UNAUTHORIZED(401, "暂未登录或token已经过期"),
	// 权限相关
	FORBIDDEN(403, "没有相关权限");

	private int code;
	private String message;

	private ResultCode(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public String getMsg() {
		return message;
	}

}