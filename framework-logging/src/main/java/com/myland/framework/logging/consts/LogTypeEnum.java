package com.myland.framework.logging.consts;

/**
 * 日志类型常量
 *
 * @author SunYanQing
 * @date 2017/3/24
 */
public enum LogTypeEnum {
	/**
	 * 数据库插入操作
	 */
	add,

	/**
	 * 数据库修改操作
	 */
	update,

	other,

	/**
	 * 数据库删除操作，包括逻辑删除
	 */
	del
}
