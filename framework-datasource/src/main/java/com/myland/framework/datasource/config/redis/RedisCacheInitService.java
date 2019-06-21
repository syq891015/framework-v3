package com.myland.framework.datasource.config.redis;

import javax.annotation.Resource;

/**
 * Redis缓存
 *
 * @author SunYanQing
 * @version 1.0
 * @date 2019/6/20 16:40
 */
public abstract class RedisCacheInitService implements CacheInitService {
	@Resource
	public RedisUtils redisUtils;
}
