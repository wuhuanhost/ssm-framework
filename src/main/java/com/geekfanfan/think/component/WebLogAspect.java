/*
 * @Author: Dreamer
 * @Site: https://www.geekfanfan.com
 * @Date: 2020-11-25 08:54:48
 * @Email: wuhuanhost@163.com
 * @LastEditors: Dreamer
 * @LastEditTime: 2020-12-01 18:01:17
 */
package com.geekfanfan.think.component;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.json.JSONUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.geekfanfan.think.bean.WebLog;

/**
 * 统一日志处理切面 Created by xc on 190903.
 */
@Aspect
@Component
@Order(1)
@Slf4j
public class WebLogAspect {

	@Pointcut("execution(public * com.geekfanfan.think.controller.*.*(..))")
	public void webLog() {
	}

	/**
	 * 65 * 设置操作异常切入点记录异常日志 扫描所有controller包下操作 66
	 */

	@Pointcut("execution(* com.geekfanfan.think.controller..*.*(..))")
	public void operExceptionLogPoinCut() {
	}

	@Before("webLog()")
	public void doBefore(JoinPoint joinPoint) throws Throwable {
	}

	@AfterReturning(value = "webLog()", returning = "ret")
	public void doAfterReturning(Object ret) throws Throwable {
	}

	@Around("webLog()")
	public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
		long startTime = System.currentTimeMillis();
		// 获取当前请求对象
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		// 记录请求信息
		WebLog webLog = new WebLog();
		Object result = joinPoint.proceed();
		Signature signature = joinPoint.getSignature();
		MethodSignature methodSignature = (MethodSignature) signature;
		Method method = methodSignature.getMethod();
		if (method.isAnnotationPresent(ApiOperation.class)) {
			ApiOperation apiOperation = method.getAnnotation(ApiOperation.class);
			webLog.setDescription(apiOperation.value());
		}
		long endTime = System.currentTimeMillis();
		String urlStr = request.getRequestURL().toString();
		webLog.setBasePath(StrUtil.removeSuffix(urlStr, URLUtil.url(urlStr).getPath()));
		webLog.setIp(getIpAddr(request));
		webLog.setMethod(request.getMethod());
		webLog.setParameter(getParameter(method, joinPoint.getArgs()));
		webLog.setResult(result);
		webLog.setSpendTime((int) (endTime - startTime));
		webLog.setStartTime(startTime);
		webLog.setUri(request.getRequestURI());
		webLog.setUrl(request.getRequestURL().toString());
		// webLog.setIp(WebLogAspect.getIpAddr(request));
		log.debug("===========================================记录API调用信息开始=========================================");
		log.debug("{}", JSONUtil.parse(webLog));
		log.debug("===========================================记录API调用信息结束=========================================");
		// System.out.println(webLog);
		return result;
	}

	/**
	 * 131 * 异常返回通知，用于拦截异常日志信息 连接点抛出异常后执行 132 * 133 * @param joinPoint 切入点 134
	 * * @param e 异常信息 135
	 */

	// 参考:https://www.cnblogs.com/wm-dv/p/11735828.html
	@AfterThrowing(pointcut = "operExceptionLogPoinCut()", throwing = "e")
	public void saveExceptionLog(JoinPoint joinPoint, Throwable e) {
		long startTime = System.currentTimeMillis();
		// 获取当前请求对象

		// webLog.setIp(WebLogAspect.getIpAddr(request));
		System.out
				.println("===========================================记录API调用信息开始=========================================");
		System.out.println(e);
		System.out
				.println("===========================================记录API调用信息结束=========================================");
		// System.out.println(webLog);
		return;
	}

	/**
	 * 根据方法和传入的参数获取请求参数
	 */
	private Object getParameter(Method method, Object[] args) {
		List<Object> argList = new ArrayList<>();
		Parameter[] parameters = method.getParameters();
		for (int i = 0; i < parameters.length; i++) {
			// 将RequestBody注解修饰的参数作为请求参数
			RequestBody requestBody = parameters[i].getAnnotation(RequestBody.class);
			if (requestBody != null) {
				argList.add(args[i]);
			}
			// 将RequestParam注解修饰的参数作为请求参数
			RequestParam requestParam = parameters[i].getAnnotation(RequestParam.class);
			if (requestParam != null) {
				Map<String, Object> map = new HashMap<>();
				String key = parameters[i].getName();
				if (!StringUtils.isEmpty(requestParam.value())) {
					key = requestParam.value();
				}
				map.put(key, args[i]);
				argList.add(map);
			}
		}
		if (argList.size() == 0) {
			return null;
		} else if (argList.size() == 1) {
			return argList.get(0);
		} else {
			return argList;
		}
	}

	/**
	 * 获取Ip地址
	 * 
	 * @param request
	 * @return
	 */
	public String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

}