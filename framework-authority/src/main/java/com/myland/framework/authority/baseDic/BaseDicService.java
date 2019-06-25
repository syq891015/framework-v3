package com.myland.framework.authority.baseDic;

import com.github.pagehelper.PageInfo;
import com.myland.framework.authority.po.BaseDic;

import java.util.List;
import java.util.Map;

/**
 * 字典目录
 *
 * @author SunYanQing
 * @version 1.0
 * @date 2018-11-30 16:29:34
 */
public interface BaseDicService {

	void save(BaseDic baseDic);

	void delete(Long id);

	void update(BaseDic baseDic);

	BaseDic getObjById(Long id);

	PageInfo<BaseDic> getList4Page(Map<String, Object> map);

	List<BaseDic> getAll();

	/**
	 * 检查字典目录编码是否重复
	 * @param code 字典目录编码
	 * @param id 字典目录ID，排除自身检查是否存在编码重复
	 * @return true 有效， false 重复
	 */
	boolean checkCodeUnique(String code, Long id);

	/**
	 * 筛选
	 * @param paramMap 查询条件
	 * @return 字典目录集合
	 */
	List<BaseDic> getAll(Map<String, Object> paramMap);
}
