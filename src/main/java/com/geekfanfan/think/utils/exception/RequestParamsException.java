/*
 * @Author: Dreamer
 * @Site: https://www.geekfanfan.com
 * @Date: 2020-11-25 16:42:56
 * @Email: wuhuanhost@163.com
 * @LastEditors: Dreamer
 * @LastEditTime: 2020-11-25 17:16:27
 */
package com.geekfanfan.think.utils.exception;

/*
   自定义异常类

*/
// public class RequestParamsException extends Exception { // 编译时异常
public class RequestParamsException extends RuntimeException { // 运行时异常

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	// 定义异常一般提供两个构造方法
	public RequestParamsException() {
	}

	public RequestParamsException(String msg) {
		super(msg);
	}

}