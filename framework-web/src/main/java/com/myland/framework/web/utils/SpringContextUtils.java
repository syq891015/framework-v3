package com.myland.framework.web.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Spring Context 工具类
 *
 * @author SunYanQing
 */
@Component
public class SpringContextUtils implements ApplicationContextAware {
	private static ApplicationContext applicationContext;

	private static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		if (SpringContextUtils.applicationContext == null) {
			SpringContextUtils.applicationContext = applicationContext;
		}
	}

	public static Object getBean(String name) {
		return getApplicationContext().getBean(name);
	}

	public static <T> T getBean(Class<T> clz) {
		return getApplicationContext().getBean(clz);
	}

	public static <T> T getBean(String name, Class<T> requiredType) {
		return getApplicationContext().getBean(name, requiredType);
	}

	public static boolean containsBean(String name) {
		return getApplicationContext().containsBean(name);
	}

	public static boolean isSingleton(String name) {
		return getApplicationContext().isSingleton(name);
	}

	public static Class<? extends Object> getType(String name) {
		return getApplicationContext().getType(name);
	}

}