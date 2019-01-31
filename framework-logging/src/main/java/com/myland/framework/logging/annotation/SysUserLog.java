package com.myland.framework.logging.annotation;

import com.myland.framework.logging.consts.LogTypeEnum;

import java.lang.annotation.*;

/**
 * Created by SunYanQing on 2017/3/24.
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SysUserLog {

	/**
	 * 操作类型
	 */
	LogTypeEnum type();

	/**
	 * 操作名称
	 */
	String operation();
}
