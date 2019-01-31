package com.myland.framework.common.base;

import java.util.List;
import java.util.Map;

/**
 * Base DAO
 *
 * @author SunYanQing
 * @date 2017/4/14
 */
public interface BaseDao<T> {

	/**
	 * 插入
	 *
	 * @param t 对象
	 */
	void insert(T t);

	/**
	 * 删除
	 *
	 * @param id 主键
	 */
	void deleteByPrimaryKey(Long id);

	/**
	 * 修改
	 *
	 * @param t 对象
	 */
	void updateByPrimaryKeySelective(T t);

	/**
	 * 根据主键查询
	 *
	 * @param id 主键
	 * @return 对象
	 */
	T selectByPrimaryKey(Long id);

	/**
	 * 根据map参数查询列表
	 *
	 * @param map 参数
	 * @return 列表
	 */
	List<T> selectList(Map<String, Object> map);

	/**
	 * 查询所有
	 *
	 * @return 列表
	 */
	List<T> selectAll();
}
