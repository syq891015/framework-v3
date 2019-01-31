package com.myland.framework.authority.dao;

import com.myland.framework.common.base.BaseDao;
import com.myland.framework.authority.po.Config;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * 系统配置项
 *
 * @author SunYanQing
 * @version 1.0
 * @date 2018-11-30 16:29:35
 */
@Repository("configDao")
public interface ConfigDao extends BaseDao<Config> {

	/**
	 * 查询配置键的数量
	 * @param key 系统配置键
	 * @return 数量
	 */
	int selectCountByKey(String key);

	/**
	 * 修改启用标志
	 * @param map ids、flag(1启用，0禁用)
	 */
	void updateEnabled(Map<String, Object> map);
}
