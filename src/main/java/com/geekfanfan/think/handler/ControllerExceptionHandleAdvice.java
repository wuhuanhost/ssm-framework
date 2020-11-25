/*
 * @Author: Dreamer
 * @Site: https://www.geekfanfan.com
 * @Date: 2020-11-20 15:04:23
 * @Email: wuhuanhost@163.com
 * @LastEditors: Dreamer
 * @LastEditTime: 2020-11-25 17:32:27
 */
package com.geekfanfan.think.handler;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.geekfanfan.think.response.BaseResult;
import com.geekfanfan.think.utils.exception.RequestParamsException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
/**
 * controller类统一异常处理
 */
class ControllerExceptionHandleAdvice {

	@ExceptionHandler
	public BaseResult handler(HttpServletRequest req, HttpServletResponse res, Exception e) {
		log.info("Restful Http请求发生异常...");

		if (res.getStatus() == HttpStatus.BAD_REQUEST.value()) {
			log.info("修改返回状态值为200");
			res.setStatus(HttpStatus.OK.value());
		}
		System.out.println("===============================");
		System.out.println(e);
		if (e instanceof NullPointerException) {
			log.error("代码00：" + e.getMessage(), e);
			return BaseResult.error(10000, "发生空指针异常");
		} else if (e instanceof IllegalArgumentException) {
			log.error("代码01：" + e.getMessage(), e);
			return BaseResult.error(10001, "请求参数类型不匹配");
		} else if (e instanceof SQLException) {
			log.error("代码02：" + e.getMessage(), e);
			return BaseResult.error(10002, "数据库访问异常");
		} else if (e instanceof RequestParamsException) {
			log.error("代码03：" + "参数校验异常");
			return BaseResult.error(10004, e.getMessage());
		} else {
			log.error("代码99：" + e.getMessage(), e);
			return BaseResult.error(10003, " 服务器代码发生异常,请联系管理员");
		}
	}
}