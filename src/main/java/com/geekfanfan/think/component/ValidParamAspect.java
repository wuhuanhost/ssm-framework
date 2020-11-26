package com.geekfanfan.think.component;

import java.util.List;

import com.geekfanfan.think.utils.response.BaseResult;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import lombok.extern.slf4j.Slf4j;

/**
 * 检查入参合法性
 */
@Aspect
@Component
@Order(2)
@Slf4j
public class ValidParamAspect {

	// Controller层切点
	@Pointcut("execution (* com.geekfanfan.think.controller..*.*(..))")
	public void aspect() {
	}

	@Around("aspect()")
	public Object doAround(ProceedingJoinPoint jp) throws Throwable {
		Object[] args = jp.getArgs();
		if (args != null && args.length > 1) {
			// 取出第2个参数
			Object obj = jp.getArgs()[1];
			if (obj instanceof BindingResult) {
				BindingResult bindingResult = (BindingResult) obj;
				// 校验返回错误集合中的第一个错误信息
				StringBuffer errorMsg = new StringBuffer();
				if (bindingResult.hasErrors()) {

					List<FieldError> errors = bindingResult.getFieldErrors();
					for (FieldError fieldError : errors) {
						log.error(fieldError.getField() + ":" + fieldError.getDefaultMessage());
						errorMsg.append(fieldError.getField() + ":" + fieldError.getDefaultMessage());

					}
					return BaseResult.error(errorMsg.toString());
					// List<ObjectError> errors = bindingResult.getAllErrors();
					// System.out.println(">>>>>>>>>>>>" + errors.get(0).getDefaultMessage());
					// throw new RequestParamsException(errorMsg.toString());
				}
			}
		}
		return jp.proceed();
	}

}