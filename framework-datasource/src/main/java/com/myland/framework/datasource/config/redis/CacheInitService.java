package com.myland.framework.datasource.config.redis;

/**
 * 缓存操作接口
 * 如果某个业务service使用到缓存时，这个业务的接口需要继承本接口以供实现各自的缓存操作业务
 *
 * @author chenem (chenermin@anjia365.com)
 * @version 1.0.0 2014年12月27日
 */
public interface CacheInitService {

	/**
	 * 将信息放入缓存中
	 */
	void inputCache();
}
