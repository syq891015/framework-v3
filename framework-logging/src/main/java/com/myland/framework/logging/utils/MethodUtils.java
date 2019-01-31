package com.myland.framework.logging.utils;

import com.alibaba.fastjson.JSON;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author SunYanQing
 * @version 1.0
 * @date 2018-09-13 16:52
 */
public class MethodUtils {

	/**
	 * 获得方法的参数，以json格式返回
	 * @param args 方法参数数组
	 * @return json格式返回方法参数数组
	 */
	public static String getMethodParamJson(Object[] args) {
		if (args == null) {
			return "";
		}

		Map<String, Object> map = new HashMap<>(5);

		// 遍历参数对象放入map中转成json字符串
		for (Object arg : args) {
			//获取参数类型
			Class clazz = arg.getClass();
			String className = clazz.getName();
			if (arg instanceof ServletRequest || arg instanceof ServletResponse) {
				continue;
			}
			className = className.substring(className.lastIndexOf(".") + 1);
			map.put(className, arg);
		}

		if (map.isEmpty()) {
			return "";
		}

		return JSON.toJSONString(map);
	}
}
