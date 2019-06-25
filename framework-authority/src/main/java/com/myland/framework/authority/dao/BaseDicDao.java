package com.myland.framework.authority.dao;

import com.myland.framework.authority.po.BaseDic;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 字典目录
 *
 * @author SunYanQing
 * @version 1.0
 * @date 2018-11-30 16:29:34
 */
@Repository("baseDicDao")
public interface BaseDicDao {

	void insert(BaseDic baseDic);

	void deleteByPrimaryKey(Long id);

	void updateByPrimaryKeySelective(BaseDic baseDic);

	BaseDic selectByPrimaryKey(Long id);

	List<BaseDic> selectList(Map<String, Object> map);

	List<BaseDic> selectAll();

	/**
	 * 根据编码查询字典目录数量（不包括自身）
	 * @param map 编码、id
	 * @return 数量
	 */
	int selectCountByCode(Map<String, Object> map);

	/**
	 * 查询全部符合条件的数据
	 * @param paramMap 查询条件
	 * @return 返回集合
	 */
	List<BaseDic> selectAll(Map<String, Object> paramMap);
}
