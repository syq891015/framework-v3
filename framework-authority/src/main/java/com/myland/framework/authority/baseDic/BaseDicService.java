package com.myland.framework.authority.baseDic;

import com.github.pagehelper.PageInfo;
import com.myland.framework.common.base.BaseService;
import com.myland.framework.authority.po.BaseDic;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Map;

/**
 * 字典大类
 *
 * @author SunYanQing
 * @version 1.0
 * @date 2018-11-30 16:29:34
 */
public interface BaseDicService extends BaseService<BaseDic> {

	@Override
	default void save(BaseDic baseDic) {

	}

	@Override
	default void delete(Long id) {

	}

	@Override
	default void update(BaseDic baseDic) {

	}

	@Override
	default BaseDic getObjById(Long id) {
		return null;
	}

	@Override
	default PageInfo<BaseDic> getList4Page(Map<String, Object> map) {
		return null;
	}

	@Override
	default List<BaseDic> getAll() {
		return null;
	}

	/**
	 * 检查字典大类编码是否重复
	 * @param code 字典大类编码
	 * @param id 字典大类ID，排除自身检查是否存在编码重复
	 * @return true 有效， false 重复
	 */
	boolean checkCodeUnique(String code, Long id);

	/**
	 * 筛选
	 * @param paramMap 查询条件
	 * @return 字典大类集合
	 */
	List<BaseDic> getAll(Map<String, Object> paramMap);
}
