package com.myland.framework.logging.annotation;

import com.myland.framework.common.utils.ReflectUtils;

import java.lang.reflect.Method;

/**
 * 数据权限校验注解解析器，用以获得注解中的数据权限KEY
 * Created by SunYanQing on 2017/3/22.
 */
public class SysUserLogAnnotationParse {
	/**
	 * 获得操作名称
	 *
	 * @param targetObj      目标对象
	 * @param methodName     方法名
	 * @param parameterTypes 参数类型
	 * @return 操作名称
	 */
	public static String getOperation(Object targetObj, String methodName, Class<?>[] parameterTypes) {
		String operation = "";

		Method method = ReflectUtils.getDeclaredMethod(targetObj, methodName, parameterTypes);
		if (method == null) {
			return operation;
		}

		//判断方法上是否有Privilege注解
		if (method.isAnnotationPresent(SysUserLog.class)) {
			//得到方法上的注解
			SysUserLog sysUserLog = method.getAnnotation(SysUserLog.class);
			operation = sysUserLog.operation();
		}
		return operation;
	}

	/**
	 * 获得操作类型
	 *
	 * @param targetObj      目标对象
	 * @param methodName     方法名
	 * @param parameterTypes 参数类型
	 * @return 操作类型
	 */
	public static String getType(Object targetObj, String methodName, Class<?>[] parameterTypes) {
		String type = "";

		Method method = ReflectUtils.getDeclaredMethod(targetObj, methodName, parameterTypes);
		if (method == null) {
			return type;
		}

		//判断方法上是否有Privilege注解
		if (method.isAnnotationPresent(SysUserLog.class)) {
			//得到方法上的注解
			SysUserLog SysUserLog = method.getAnnotation(SysUserLog.class);
			type = SysUserLog.type().toString();
		}
		return type;
	}
}