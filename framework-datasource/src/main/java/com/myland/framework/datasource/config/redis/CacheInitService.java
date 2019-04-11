package com.myland.framework.datasource.config.redis;

/**
 * 缓存操作接口
 * 如果某个业务service使用到缓存时，这个业务的接口需要继承本接口以供实现各自的缓存操作业务
 */
public interface CacheInitService {

	String getName();

	/**
	 * 将信息放入缓存中
	 */
	void inputCache();
}
