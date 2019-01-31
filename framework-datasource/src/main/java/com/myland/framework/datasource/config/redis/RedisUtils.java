package com.myland.framework.datasource.config.redis;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Redis管理器
 *
 * @author chenem
 * @date 2017年2月8日
 */
public class RedisUtils {

	static RedisTemplate<String, String> redisTemplate;

	static long timeout;

	public static void del(String key) {
		redisTemplate.delete(key);
	}

	public static void expire(String key, int time, TimeUnit unit) {
		redisTemplate.expire(key, time, unit);
	}

	/************************************字符串(String)**********************************/
	public static String get(String key) {
		return redisTemplate.opsForValue().get(key);
	}

	public static void set(String key, String value) {
		redisTemplate.opsForValue().set(key, value);
		if (timeout != -1) {
			redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
		}
	}

	public static void setExpire(String key, String value, int time, TimeUnit unit) {
		redisTemplate.opsForValue().set(key, value, time, unit);
	}

	public static Long strLen(String key) {
		return redisTemplate.opsForValue().size(key);
	}

	public static String getSet(String key, String value) {
		String oldVal = redisTemplate.opsForValue().getAndSet(key, value);
		if (timeout != -1) {
			redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
		}
		return oldVal;
	}

	/************************************哈希(Hash)**********************************/
	public static String hGet(String key, String hKey) {
		return (String) redisTemplate.opsForHash().get(key, hKey);
	}

	public static <T> T hGet(String key, String hKey, Class<T> clazz) {
		HashOperations<String, String, T> ops = redisTemplate.opsForHash();
		return ops.get(key, hKey);
	}

	public static Map<Object, Object> hGetAll(String key) {
		return redisTemplate.opsForHash().entries(key);
	}

	public static Set<Object> hKeys(String key) {
		return redisTemplate.opsForHash().keys(key);
	}

	public static List<Object> values(String key) {
		return redisTemplate.opsForHash().values(key);
	}

	public static boolean hExists(String key, String hKey) {
		return redisTemplate.opsForHash().hasKey(key, hKey);
	}

	public static Long hLen(String key) {
		return redisTemplate.opsForHash().size(key);
	}

	public static <T> void hSet(String key, String hKey, T hVal) {
		redisTemplate.opsForHash().put(key, hKey, hVal);
		if (timeout != -1) {
			redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
		}
	}

	public static void hSet(String key, String hKey, String hVal, int time, TimeUnit unit) {
		redisTemplate.opsForHash().put(key, hKey, hVal);
		redisTemplate.expire(key, time, unit);
	}

	public static void hmSet(String key, Map<String, Object> map) {
		redisTemplate.opsForHash().putAll(key, map);
		if (timeout != -1) {
			redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
		}
	}

	@SuppressWarnings("unchecked")
	public static void hDel(String key, String... hKey) {
		redisTemplate.opsForHash().delete(key, (Object[]) hKey);
	}

	/************************************列表(List)**********************************/
	/**
	 * 	LPOP key
	 *  移出并获取列表的第一个元素
	 * @param key 键
	 * @return 集合中的元素
	 */
	public static String lPop(String key) {
		return redisTemplate.opsForList().leftPop(key);
	}

	/**
	 * 	RPOP key
	 *  移除并获取列表最后一个元素
	 * @param key 键
	 * @return 集合中的元素
	 */
	public static String rPop(String key) {
		return redisTemplate.opsForList().rightPop(key);
	}

	public static List<String> lRange(String key, long start, long end) {
		return redisTemplate.opsForList().range(key, start, end);
	}

	public static Long lLen(String key) {
		return redisTemplate.opsForList().size(key);
	}

	public static void lSet(String key, long idx, String value) {
		redisTemplate.opsForList().set(key, idx, value);
	}

	public static void lTrim(String key, long start, long end) {
		redisTemplate.opsForList().trim(key, start, end);
	}

	@SuppressWarnings("unchecked")
	public static <E> Long rPush(String key, E e) {
		ListOperations<String, E> ops = (ListOperations<String, E>) redisTemplate.opsForList();
		return ops.rightPush(key, e);
	}

	@SuppressWarnings("unchecked")
	public static <E> Long rPush(String key, E... e) {
		ListOperations<String, E> ops = (ListOperations<String, E>) redisTemplate.opsForList();
		return ops.rightPushAll(key, e);
	}

	@SuppressWarnings("unchecked")
	public static <E> Long rPushAll(String key, Collection<E> collection) {
		ListOperations<String, E> ops = (ListOperations<String, E>) redisTemplate.opsForList();
		return ops.rightPushAll(key, collection);
	}

	@SuppressWarnings("unchecked")
	public static <E> Long lPush(String key, E e) {
		ListOperations<String, E> ops = (ListOperations<String, E>) redisTemplate.opsForList();
		return ops.leftPush(key, e);
	}

	@SuppressWarnings("unchecked")
	public static <E> Long lPush(String key, E... e) {
		ListOperations<String, E> ops = (ListOperations<String, E>) redisTemplate.opsForList();
		return ops.leftPushAll(key, e);
	}

	@SuppressWarnings("unchecked")
	public static <E> Long lPushAll(String key, Collection<E> collection) {
		ListOperations<String, E> ops = (ListOperations<String, E>) redisTemplate.opsForList();
		return ops.leftPushAll(key, collection);
	}


	/************************************集合(Set)**********************************/
	public static void sAdd(String key, String... value) {
		redisTemplate.opsForSet().add(key, value);
	}

	/**
	 * 根据模板获得所有的key
	 *
	 * @param bytePattern 正则表达式
	 * @return 缓存KEY
	 */
	public static Set<byte[]> keys(byte[] bytePattern) {
		return redisTemplate.execute((RedisCallback<Set<byte[]>>) connection -> connection.keys(bytePattern));
	}
}
