package com.myland.framework.authority.dao;

import com.myland.framework.authority.po.Dic;
import com.myland.framework.common.base.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 字典
 *
 * @author SunYanQing
 * @version 1.0
 * @date 2018-11-30 16:29:33
 */
@Repository("dicDao")
public interface DicDao extends BaseDao<Dic> {

	/**
	 * 同种字典目录下字典编码唯一性查询
	 * @param param id、baseId, val
	 * @return 同种字典目录下字典编码相同的字典数量
	 */
	int selectCount4Uk(Map<String, Object> param);

	/**
	 * 修改启用标志
	 * @param map ids、flag(1启用，0禁用)
	 */
	void updateEnabled(Map<String, Object> map);

	/**
	 * 查询字典目录下的字典集合
	 * @param baseDicId 字典目录ID
	 * @return 字典集合
	 */
	List<Dic> selectListByBaseDic(Long baseDicId);
}
