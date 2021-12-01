/*
 * @Author: Dreamer
 * @Site: https://www.geekfanfan.com
 * @Date: 2020-11-20 15:04:23
 * @Email: wuhuanhost@163.com
 * @LastEditors: Dreamer
 * @LastEditTime: 2020-12-02 10:01:15
 */
package com.geekfanfan.think.common.handler;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Constraint;
import javax.validation.ConstraintViolationException;

import com.geekfanfan.think.common.exception.ApiException;
import com.geekfanfan.think.common.response.BaseResult;
import com.geekfanfan.think.common.response.IErrorCode;

import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
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
			return BaseResult.error("发生空指针异常");
		} else if (e instanceof IllegalArgumentException) {
			log.error("代码01：" + e.getMessage(), e);
			return BaseResult.error("请求参数类型不匹配");
		} else if (e instanceof SQLException) {
			log.error("代码02：" + e.getMessage(), e);
			return BaseResult.error("数据库访问异常");
		} else if (e instanceof ApiException) {
			log.error("代码03：" + "参数校验异常");
			return BaseResult.error(e.getMessage());
		} else if (e instanceof RedisConnectionFailureException) {
			log.error("代码04：" + "redis连接异常", e);
			return BaseResult.error(e.getMessage());
		} else {
			log.error("代码99：" + e.getMessage(), e);
			return BaseResult.error("服务器代码发生异常,请联系管理员");
		}
	}

	@ResponseBody
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public BaseResult handleValidException(MethodArgumentNotValidException e) {
		BindingResult bindingResult = e.getBindingResult();
		String message = null;
		if (bindingResult.hasErrors()) {
			FieldError fieldError = bindingResult.getFieldError();
			if (fieldError != null) {
				message = fieldError.getField() + fieldError.getDefaultMessage();
			}
		}
		return BaseResult.error(message);
	}

	// get参数异常
	@ResponseBody
	@ExceptionHandler(value = ConstraintViolationException.class)
	public BaseResult handleMethodArgumentNotValidException(ConstraintViolationException e) {
		String message = e.getMessage();
		if (message != null) {
			message = e.getMessage();
		}
		return BaseResult.validateFaild(message);
	}

	@ResponseBody
	@ExceptionHandler(value = BindException.class)
	public BaseResult handleValidException(BindException e) {
		BindingResult bindingResult = e.getBindingResult();
		String message = null;
		if (bindingResult.hasErrors()) {
			FieldError fieldError = bindingResult.getFieldError();
			if (fieldError != null) {
				message = fieldError.getField() + fieldError.getDefaultMessage();
			}
		}
		return BaseResult.error(message);
	}

	@ResponseBody
	@ExceptionHandler(value = ApiException.class)
	public BaseResult handle(ApiException e) {
		if (e.getErrorCode() != null) {
			return BaseResult.error((IErrorCode) e.getErrorCode());
		}
		return BaseResult.error(e.getMessage());
	}
}