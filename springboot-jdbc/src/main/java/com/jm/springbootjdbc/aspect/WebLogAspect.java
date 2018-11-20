/**   
 * Copyright © 2018 eSunny Info. Tech Ltd. All rights reserved.
 * 
 * @Package: com.jm.springbootjdbc.aspect 
 * @author: 502774066   
 * @date: Nov 19, 2018 5:33:15 PM 
 */

package com.jm.springbootjdbc.aspect;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/** 
 * @ClassName: WebLogAspect 
 * @Description: TODO
 * @author: 502774066
 * @date: Nov 19, 2018 5:33:15 PM  
 */
@Aspect
@Order(5)
@Component
public class WebLogAspect {
	
	private static Logger logger = Logger.getLogger(WebLogAspect.class);
	
	ThreadLocal<Long> startTime = new ThreadLocal<>();
	
	@Pointcut("execution(public * com.jm.springbootjdbc.controller..*.*(..))")
	public void webLog() {}
	
	@Before("webLog()")
	public void doBefore(JoinPoint joinPoint) throws Throwable {
		startTime.set(System.currentTimeMillis());
		
		// receive the request and record the request content
		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = servletRequestAttributes.getRequest();
		
		// record the request content
		logger.info("URL: " + request.getRequestURI().toString());
		logger.info("Http_method: " + request.getMethod());
		logger.info("IP: " + request.getRemoteAddr());
		logger.info("class_method: " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
		logger.info("args: " + Arrays.toString(joinPoint.getArgs()));
	}
	
	@AfterReturning(returning = "ret", pointcut = "webLog()")
	public void doAfterReturning(Object ret) throws Throwable{
		// request complete response the content
		logger.info("response: " + ret);
		logger.info("spend time: " + (System.currentTimeMillis() - startTime.get()));
	}

}
