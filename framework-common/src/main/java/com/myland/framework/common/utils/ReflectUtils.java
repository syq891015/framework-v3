
package com.myland.framework.common.utils;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * 反射工具
 *
 * @author 孙文祥
 */
@Slf4j
public class ReflectUtils {

	/**
	 * 对象操作
	 *
	 * @param obj       对象
	 * @param fieldName 属性名
	 * @param fieldVal  属性值
	 * @param type      操作类型（get或set）
	 */
	private static Object operate(Object obj, String fieldName, Object fieldVal, String type) {
		Object ret = null;
		try {
			// 获得对象类型
			Class<?> classType = obj.getClass();
			// 获得对象的所有属性
			Field fields[] = classType.getDeclaredFields();
			for (Field field : fields) {
				if (field.getName().equals(fieldName)) {
					String firstLetter = fieldName.substring(0, 1).toUpperCase();
					if ("set".equals(type)) {
						String setMethodName = "set" + firstLetter + fieldName.substring(1);
						Method setMethod = classType.getMethod(setMethodName, field.getType());
						ret = setMethod.invoke(obj, fieldVal);
					}
					if ("get".equals(type)) {
						String getMethodName = "get" + firstLetter + fieldName.substring(1);
						Method getMethod = classType.getMethod(getMethodName);
						ret = getMethod.invoke(obj);
					}
					return ret;
				}
			}
		} catch (Exception e) {
			log.error("### Java 反射调用时发生异常", e);
		}
		return ret;
	}

	/**
	 * 获取对象的属性值
	 *
	 * @param obj       对象
	 * @param fieldName 属性名
	 */
	public static Object getVal(Object obj, String fieldName) {
		return operate(obj, fieldName, null, "get");
	}

	/**
	 * 为对象属性赋值
	 *
	 * @param obj       对象
	 * @param fieldName 属性名
	 * @param fieldVal  属性值
	 */
	public static void setVal(Object obj, String fieldName, Object fieldVal) {
		operate(obj, fieldName, fieldVal, "set");
	}

	/**
	 * 获取对象的方法
	 *
	 * @param object         对象
	 * @param methodName     方法名
	 * @param parameterTypes 方法参数类型数组
	 */
	public static Method getDeclaredMethod(Object object, String methodName, Class<?>[] parameterTypes) {
		for (Class<?> superClass = object.getClass(); superClass != Object.class; superClass = superClass
				.getSuperclass()) {
			try {
				return superClass.getDeclaredMethod(methodName, parameterTypes);
			} catch (NoSuchMethodException e) {
				// Method 不在当前类定义, 继续向上转型
			}
		}
		return null;
	}

	/**
	 * 获取类型的方法
	 *
	 * @param cls            类型
	 * @param methodName     方法名
	 * @param parameterTypes 方法参数类型数组
	 * @return 有可能返回null，请注意
	 */
	public static Method getDeclaredMethod(Class cls, String methodName, Class<?>[] parameterTypes) {
		for (Class<?> superClass = cls; superClass != Object.class; superClass = superClass
				.getSuperclass()) {
			try {
				return superClass.getDeclaredMethod(methodName, parameterTypes);
			} catch (NoSuchMethodException e) {
				// Method 不在当前类定义, 继续向上转型
			}
		}
		return null;
	}

	private static void makeAccessible(Field field) {
		if (!Modifier.isPublic(field.getModifiers())) {
			field.setAccessible(true);
		}
	}

	/**
	 * 获取对象的属性对象
	 *
	 * @param object    对象
	 * @param filedName 属性名
	 */
	private static Field getDeclaredField(Object object, String filedName) {
		for (Class<?> superClass = object.getClass(); superClass != Object.class; superClass = superClass
				.getSuperclass()) {
			try {
				return superClass.getDeclaredField(filedName);
			} catch (NoSuchFieldException e) {
				// Field 不在当前类定义, 继续向上转型
			}
		}
		return null;
	}

	/**
	 * 调用对象方法
	 *
	 * @param object         对象
	 * @param methodName     方法名
	 * @param parameterTypes 方法参数类型数组
	 * @param parameters     方法参数对象数组
	 */
	public static Object invokeMethod(Object object, String methodName, Class<?>[] parameterTypes, Object[] parameters)
			throws InvocationTargetException {
		Method method = getDeclaredMethod(object, methodName, parameterTypes);
		if (method == null) {
			throw new IllegalArgumentException("Could not find method [" + methodName + "] on target [" + object + "]");
		}
		method.setAccessible(true);
		try {
			return method.invoke(object, parameters);
		} catch (IllegalAccessException ignored) {
		}
		return null;
	}

	/**
	 * 为对象属性赋值
	 *
	 * @param object    对象
	 * @param fieldName 属性名
	 * @param value     属性值
	 */
	public static void setFieldValue(Object object, String fieldName, Object value) {
		Field field = getDeclaredField(object, fieldName);
		if (field == null) {
			throw new IllegalArgumentException("Could not find field [" + fieldName + "] on target [" + object + "]");
		}
		makeAccessible(field);
		try {
			field.set(object, value);
		} catch (IllegalAccessException ignored) {
		}
	}

	/**
	 * 获取对象的属性值
	 *
	 * @param object    对象
	 * @param fieldName 属性名
	 */
	public static Object getFieldValue(Object object, String fieldName) {
		Field field = getDeclaredField(object, fieldName);
		if (field == null) {
			throw new IllegalArgumentException("Could not find field [" + fieldName + "] on target [" + object + "]");
		}
		makeAccessible(field);
		Object result = null;
		try {
			result = field.get(object);
		} catch (IllegalAccessException ignored) {
		}
		return result;
	}

}
