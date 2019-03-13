package com.myland.framework.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import java.util.Map;

/**
 * Shiro工具类
 *
 * @author SunYanQing
 */
public class ShiroUtils {

	/**
	 * 获得服务器保持的会话
	 * @return 会话
	 */
	private static Session getSession() {
		return SecurityUtils.getSubject().getSession();
	}

	/**
	 * 设置会话属性
	 * @param map 属性map
	 */
	public static void setSession(Map<String, Object> map) {
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			setSessionAttribute(entry.getKey(), entry.getValue());
		}
	}

	public static Subject getSubject() {
		return SecurityUtils.getSubject();
	}

	/**
	 * 获得登录用户
	 * @return 登录用户
	 */
	public static Object getLoginUser() {
		return getSubject().getPrincipal();
	}

	/**
	 * 向session中设置属性
	 * @param key 属性键
	 * @param value 属性值
	 */
	public static void setSessionAttribute(Object key, Object value) {
		getSession().setAttribute(key, value);
	}

	/**
	 * 获得session中的属性值
	 * @param key 属性键
	 * @return 属性值
	 */
	public static Object getSessionAttribute(Object key) {
		return getSession().getAttribute(key);
	}

	/**
	 * 判断是否登录了
	 * @return true登录了，false未登录
	 */
	public static boolean isLogin() {
		return SecurityUtils.getSubject().getPrincipal() != null;
	}

	/**
	 * 登出
	 */
	public static void logout() {
		SecurityUtils.getSubject().logout();
	}
}
