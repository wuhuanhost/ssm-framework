/*
 * @Author: Dreamer
 * @Site: https://www.geekfanfan.com
 * @Date: 2020-11-25 16:42:56
 * @Email: wuhuanhost@163.com
 * @LastEditors: Dreamer
 * @LastEditTime: 2020-11-26 11:13:15
 */
package com.geekfanfan.think.utils.exception;

import com.geekfanfan.think.utils.response.IErrorCode;

/*
   自定义异常类

*/

// public class ApiException extends Exception { // 编译时异常
// public class ApiException extends RuntimeException { // 运行时异常

/**
 * 自定义API异常 Created by macro on 2020/2/27.
 */
public class ApiException extends RuntimeException {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private IErrorCode errorCode;

	public ApiException(IErrorCode errorCode) {
		super(errorCode.getMsg());
		this.errorCode = errorCode;
	}

	public ApiException(String message) {
		super(message);
	}

	public ApiException(Throwable cause) {
		super(cause);
	}

	public ApiException(String message, Throwable cause) {
		super(message, cause);
	}

	public IErrorCode getErrorCode() {
		return errorCode;
	}
}
