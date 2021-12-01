/*
 * @Author: Dreamer
 * @Site: https://www.geekfanfan.com
 * @Date: 2020-11-26 09:48:04
 * @Email: wuhuanhost@163.com
 * @LastEditors: Dreamer
 * @LastEditTime: 2020-11-26 11:06:15
 */
package com.geekfanfan.think.common.exception;

import com.geekfanfan.think.common.response.IErrorCode;

/**
 * 断言处理类，用于抛出各种API异常 Created by macro on 2020/2/27.
 */
public class Asserts {

	public static void error(String message) {
		throw new ApiException(message);
	}

	public static void error(IErrorCode errorCode) {
		throw new ApiException(errorCode);
	}

}