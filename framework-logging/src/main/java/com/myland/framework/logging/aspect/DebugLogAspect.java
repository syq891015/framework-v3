package com.myland.framework.logging.aspect;

import com.myland.framework.logging.utils.MethodUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 方法进入进出DEBUG日志切面
 * <code>@Component</code> 声明这是一个组件
 * <code>@Aspect</code> 声明这是一个切面Bean
 *
 * @author SunYanQing
 * @date 2018/9/13
 */
@Component
@Aspect
public class DebugLogAspect {

	@Pointcut("execution(* com.myland..*.*Controller.*(..))")
	public void logController() {

	}

	/*@Pointcut("execution(* com.myland..*.*ServiceImpl.*(..))")
	public void logService() {

	}

	@Pointcut("execution(* com.myland..*.*Dao.*(..))")
	public void logDao() {

	}*/

	@Pointcut("logController()")
	public void myPoint() {
	}

	@Around(value = "myPoint()")
	public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
		// 目标对象
		Object targetObj = joinPoint.getTarget();

		//获取方法名
		String methodName = joinPoint.getSignature().getName();

		// 组装操作内容
		String content = MethodUtils.getMethodParamJson(joinPoint.getArgs());

		// 获得目的Logger
		Logger logger = LoggerFactory.getLogger(targetObj.getClass());

		logger.debug("Entering {} method. Request params: {}.", methodName, "".equals(content) ? "no" : content);

		//调用核心逻辑
		Object retVal = joinPoint.proceed();

		logger.debug("Exit {} method. Result: {}.", methodName, retVal);
		return retVal;
	}
}
