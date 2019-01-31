package com.myland.framework.authority.dao;

import com.myland.framework.common.base.BaseDao;
import com.myland.framework.authority.po.BaseDic;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 字典大类
 *
 * @author SunYanQing
 * @version 1.0
 * @date 2018-11-30 16:29:34
 */
@Repository("baseDicDao")
public interface BaseDicDao extends BaseDao<BaseDic> {

	/**
	 * 根据编码查询字典大类数量（不包括自身）
	 * @param map 编码、id
	 * @return 数量
	 */
	int selectCountByCode(Map<String, Object> map);

	List<BaseDic> selectAll(Map<String, Object> paramMap);
}
