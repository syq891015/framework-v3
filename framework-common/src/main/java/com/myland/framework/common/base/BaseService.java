package com.myland.framework.common.base;

import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * Base Service
 *
 * @author SunYanQing
 * @date 2017/4/14
 */
public interface BaseService<T> {

	/**
	 * 保存
	 * @param t 对象
	 */
	void save(T t);

	/**
	 * 删除
	 * @param id 主键
	 */
	void delete(Long id);

	/**
	 * 修改
	 * @param t 对象
	 */
	void update(T t);

	/**
	 * 根据主键查询唯一数据
	 * @param id 主键
	 * @return 对象
	 */
	T getObjById(Long id);

	/**
	 * 分页查询符合条件的数据
	 * @param map 查询条件
	 * @return 一页数据
	 */
	PageInfo<T> getList4Page(Map<String, Object> map);

	/**
	 * 查询所有数据
	 * @return 所有数据集合
	 */
	List<T> getAll();
}
