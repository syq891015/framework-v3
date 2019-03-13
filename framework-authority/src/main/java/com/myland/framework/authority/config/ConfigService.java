package com.myland.framework.authority.config;

import com.github.pagehelper.PageInfo;
import com.myland.framework.common.base.BaseService;
import com.myland.framework.authority.po.Config;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Map;

/**
 * 系统配置项
 *
 * @author SunYanQing
 * @version 1.0
 * @date 2018-11-30 16:29:35
 */
public interface ConfigService extends BaseService<Config> {

	@Override
	default void save(Config config) {

	}

	@Override
	default void delete(Long id) {

	}

	@Override
	default void update(Config config) {

	}

	@Override
	default Config getObjById(Long id) {
		return null;
	}

	@Override
	default PageInfo<Config> getList4Page(Map<String, Object> map) {
		return null;
	}

	@Override
	default List<Config> getAll() {
		return null;
	}

	/**
	 * 检查系统配置键的唯一性
	 * @param key 系统配置键
	 * @return true 唯一，false 重复
	 */
	boolean checkKeyUnique(String key);

	/**
	 * 启用
	 * @param ids 系统配置项ID
	 */
	void enable(List<Long> ids);

	/**
	 * 禁用
	 * @param ids 系统配置项ID
	 */
	void disable(List<Long> ids);

	/**
	 * 从缓存中获得系统配置
	 * @param key 系统配置属性
	 * @return 系统配置
	 */
	Config getConfigInCache(String key);
}
