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

	void save(T t);

	void delete(Long id);

	void update(T t);

	T getObjById(Long id);

	PageInfo<T> getList4Page(Map<String, Object> map);

	List<T> getAll();
}
