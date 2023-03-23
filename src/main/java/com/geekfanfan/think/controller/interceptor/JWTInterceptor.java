/*
 * @Author: Dreamer
 * @Site: https://www.geekfanfan.com
 * @Date: 2021-12-02 13:54:32
 * @Email: wuhuanhost@163.com
 * @LastEditors: Dreamer
 * @LastEditTime: 2021-12-03 15:47:27
 */
package com.geekfanfan.think.controller.interceptor;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.servlet.HandlerInterceptor;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.geekfanfan.think.common.exception.Asserts;
import com.geekfanfan.think.util.JWTUtils;

/**
 * @author admin
 */
@Slf4j
public class JWTInterceptor implements HandlerInterceptor {

	@Override
	/**
	 * 拦截请求，访问controll之前
	 */
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		System.out.println(request);
		String token = request.getHeader("token");
		String access_token = request.getHeader("access-token");
		log.info("access_token:" + access_token);
		if (StringUtils.isEmpty(token)) {
			// throw new MyException("token不能为空");
			Asserts.error("token不能为空");
			return false;
		}
		try {
			JWTUtils.verify(token);
		} catch (SignatureVerificationException e) {
			log.error("无效签名！ 错误 ->", e);
			Asserts.error("无效签名！ 错误 ->");
			return false;
		} catch (TokenExpiredException e) {
			log.error("token过期！ 错误 ->", e);
			Asserts.error("token过期！ 错误 ->");
			return false;
		} catch (AlgorithmMismatchException e) {
			log.error("token算法不一致！ 错误 ->", e);
			Asserts.error("token算法不一致！ 错误 ->");
			return false;
		} catch (Exception e) {
			log.error("token无效！ 错误 ->", e);
			Asserts.error("token算法不一致！ 错误 ->");
			return false;
		}
		return true;
	}
}
