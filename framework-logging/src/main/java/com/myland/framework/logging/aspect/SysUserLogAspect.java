package com.myland.framework.logging.aspect;

import com.myland.framework.logging.annotation.SysUserLogAnnotationParse;
import com.myland.framework.logging.itfc.UserName;
import com.myland.framework.logging.po.UserLog;
import com.myland.framework.logging.userLog.UserLogService;
import com.myland.framework.web.utils.WebUtils;
import com.myland.framework.web.utils.IpUtils;
import com.myland.framework.logging.utils.MethodUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 用户日志切面
 * <code>@Component</code> 声明这是一个组件
 * <code>@Aspect</code> 声明这是一个切面Bean
 *
 * @author SunYanQing
 * @date 2017/3/24
 */
@Component
@Aspect
public class SysUserLogAspect {
	@Resource
	private UserName userName;

	@Resource
	private UserLogService userLogService;

	@Pointcut("@annotation(com.myland.framework.logging.annotation.SysUserLog)")
	public void myPoint() {
	}

	@Around(value = "myPoint()")
	public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
		//调用核心逻辑
		Object retVal = joinPoint.proceed();

		// 目标对象
		Object targetObj = joinPoint.getTarget();

		// 类名
		String className = targetObj.getClass().getName();

		// 方法签名
		Signature signature = joinPoint.getSignature();

		//获取方法名
		String methodName = joinPoint.getSignature().getName();

		// 参数类型
		MethodSignature methodSignature = (MethodSignature) signature;
		Class[] parameterTypes = methodSignature.getParameterTypes();

		// 组装操作内容
		String content = MethodUtils.getMethodParamJson(joinPoint.getArgs());

		// 获得操作名称
		String operation = SysUserLogAnnotationParse.getOperation(targetObj, methodName, parameterTypes);
		String type = SysUserLogAnnotationParse.getType(targetObj, methodName, parameterTypes);

		// IP地址
		HttpServletRequest request = WebUtils.getHttpServletRequest();
		String ip = IpUtils.getIpAddr(request);

		// 插入用户日志
		UserLog userLog = new UserLog();
		userLog.setUsername(userName.getUserName());
		userLog.setParams(content);
		userLog.setOperation(operation);
		userLog.setType(type);
		userLog.setIp(ip);
		userLog.setMethod(className + "." + methodName + "()");

		userLogService.save(userLog);
		return retVal;
	}
}
