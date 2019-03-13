package com.myland.framework.datasource.config.redis;

import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Redis工具类
 *
 * @author SunYanQing
 */
@Component
public class RedisUtils {

	@Resource
	private RedisTemplate<String, Object> redisTemplate;

	/**
	 * 删除key存储的缓存
	 * @param key 键
	 */
	public void del(String key) {
		redisTemplate.delete(key);
	}

	/**
	 * 指定缓存失效时间
	 * @param key 键
	 * @param timeout 时间
	 * @param timeUnit 时间单位
	 */
	public void expire(String key, long timeout, TimeUnit timeUnit) {
		redisTemplate.expire(key, timeout, timeUnit);
	}

	/**
	 * 根据key获取过期时间
	 * @param key 键，不能为null
	 * @param timeUnit 时间单位
	 * @return 过期时间，返回0代表为永久有效
	 */
	public Long getExpire(String key, TimeUnit timeUnit) {
		return redisTemplate.getExpire(key, timeUnit);
	}

	/**
	 * 获取指定key的值
	 * @param key 键
	 * @return 值
	 */
	public Object get(String key) {
		return redisTemplate.opsForValue().get(key);
	}

	/**
	 * 获取指定key的值
	 * @param key 键
	 * @param clazz 值类型
	 * @return 值
	 */
	@SuppressWarnings("unchecked")
	public <T> T get(String key, Class<T> clazz) {
		ValueOperations<String, T> opsForValue = (ValueOperations<String, T>) redisTemplate.opsForValue();
		return opsForValue.get(key);
	}

	/**
	 * 设置指定 key 的值
	 * @param key 键
	 * @param value 值
	 */
	public void set(String key, Object value) {
		redisTemplate.opsForValue().set(key, value);
	}

	/**
	 * 返回 key 所储存的字符串值的长度
	 * @param key 键
	 * @return 字符串值的长度
	 */
	public Long strLen(String key) {
		return redisTemplate.opsForValue().size(key);
	}

	/**
	 * 将给定 key的值设为value，并返回 key的旧值(old value)
	 * @param key 键
	 * @param value 新值
	 * @return 旧值
	 */
	public Object getSet(String key, Object value) {
		return redisTemplate.opsForValue().getAndSet(key, value);
	}

	/**
	 * 获取存储在哈希表中指定字段的值
	 * @param key 键
	 * @param hKey 哈希表中的键
	 * @return 哈希表中的值
	 */
	public Object hGet(String key, String hKey) {
		return redisTemplate.opsForHash().get(key, hKey);
	}

	/**
	 * 获取存储在哈希表中指定字段的值
	 * @param key 键
	 * @param hKey 哈希表中的键
	 * @param clazz 值类型
	 * @param <T> 值类型
	 * @return 值
	 */
	public <T> T hGet(String key, String hKey, Class<T> clazz) {
		HashOperations<String, String, T> ops = redisTemplate.opsForHash();
		return ops.get(key, hKey);
	}

	/**
	 * 获取在哈希表中指定 key 的所有字段和值
	 * @param key 键
	 * @return 哈希表
	 */
	public Map<Object, Object> hGetAll(String key) {
		return redisTemplate.opsForHash().entries(key);
	}

	/**
	 * 获取在哈希表中指定 key 的所有字段和值
	 * @param key 键
	 * @param clazz 哈希表中的值的类型
	 * @return 哈希表
	 */
	public <T> Map<String, T> hGetAll(String key, Class<T> clazz) {
		HashOperations<String, String, T> ops = redisTemplate.opsForHash();
		return ops.entries(key);
	}

	/**
	 * 获取哈希表中所有的键
	 * @param key 键
	 * @return 哈希表中的键集合
	 */
	public Set<String> hKeys(String key) {
		HashOperations<String, String, Object> ops = redisTemplate.opsForHash();
		return ops.keys(key);
	}

	/**
	 * 获取哈希表中所有值
	 * @param key 键
	 * @return 哈希表中的值集合
	 */
	public List<Object> values(String key) {
		return redisTemplate.opsForHash().values(key);
	}

	/**
	 * 获取哈希表中所有值
	 * @param key 键
	 * @param clazz 哈希表中的值的类型
	 * @return 哈希表中的值集合
	 */
	public <T> List<T> values(String key, Class<T> clazz) {
		HashOperations<String, String, T> ops = redisTemplate.opsForHash();
		return ops.values(key);
	}

	/**
	 * 查看哈希表 key 中，指定的字段是否存在
	 * @param key 键
	 * @param hKey 哈希表中的键
	 * @return true存在，false不存在
	 */
	public boolean hExists(String key, String hKey) {
		return redisTemplate.opsForHash().hasKey(key, hKey);
	}

	/**
	 * 哈希表的大小
	 * @param key 键
	 * @return 哈希表的大小
	 */
	public Long hLen(String key) {
		return redisTemplate.opsForHash().size(key);
	}

	/**
	 * 将哈希表 key 中的字段 field 的值设为 value
	 * @param key 键
	 * @param hKey 哈希表中的键
	 * @param hVal 哈希表中的值
	 * @param <T> 哈希表中的值类型
	 */
	public <T> void hSet(String key, String hKey, T hVal) {
		redisTemplate.opsForHash().put(key, hKey, hVal);
	}

	/**
	 * 将哈希表 key 中的字段 field 的值设为 value，并设置失效时间
	 * @param key 键
	 * @param hKey 哈希表中的键
	 * @param hVal 哈希表中的值
	 * @param <T> 哈希表中的值类型
	 * @param timeout 时间
	 * @param timeUnit 时间单位
	 */
	public <T> void hSet(String key, String hKey, T hVal, long timeout, TimeUnit timeUnit) {
		hSet(key, hKey, hVal);
		expire(key, timeout, timeUnit);
	}

	/**
	 * 同时将多个 field-value (域-值)对设置到哈希表 key 中
	 * @param key 键
	 * @param map 哈希表
	 */
	public void hmSet(String key, Map<String, Object> map) {
		redisTemplate.opsForHash().putAll(key, map);
	}

	/**
	 * 删除一个或多个哈希表字段
	 * @param key 键
	 * @param hKey 哈希表中的键
	 */
	public void hDel(String key, String... hKey) {
		redisTemplate.opsForHash().delete(key, (Object[]) hKey);
	}

	/**
	 * 	LPOP key
	 *  移出并获取列表的第一个元素
	 * @param key 键
	 * @return 集合中的元素
	 */
	public Object lPop(String key) {
		return redisTemplate.opsForList().leftPop(key);
	}

	/**
	 * 	LPOP key
	 *  移出并获取列表的第一个元素
	 * @param key 键
	 * @param clazz 集合中元素的类型
	 * @return 集合中的元素
	 */
	public <T> T lPop(String key, Class<T> clazz) {
		ListOperations<String, T> ops = (ListOperations<String, T>) redisTemplate.opsForList();
		return ops.leftPop(key);
	}

	/**
	 * 	RPOP key
	 *  移除并获取列表最后一个元素
	 * @param key 键
	 * @return 集合中的元素
	 */
	public Object rPop(String key) {
		return redisTemplate.opsForList().rightPop(key);
	}

	/**
	 * 	RPOP key
	 *  移除并获取列表最后一个元素
	 * @param key 键
	 * @param clazz 集合中元素的类型
	 * @return 集合中的元素
	 */
	public <T> T rPop(String key, Class<T> clazz) {
		ListOperations<String, T> ops = (ListOperations<String, T>) redisTemplate.opsForList();
		return ops.rightPop(key);
	}

	/**
	 * 获取列表指定范围内的元素
	 * @param key 键
	 * @param start 开始范围
	 * @param end 结束范围
	 * @return 指定范围内的元素的集合
	 */
	public List<Object> lRange(String key, long start, long end) {
		return redisTemplate.opsForList().range(key, start, end);
	}

	/**
	 * 获取列表指定范围内的元素
	 * @param key 键
	 * @param start 开始范围
	 * @param end 结束范围
	 * @param clazz 集合中元素的类型
	 * @return 指定范围内的元素的集合
	 */
	public <T> List<T> lRange(String key, long start, long end, Class<T> clazz) {
		ListOperations<String, T> ops = (ListOperations<String, T>) redisTemplate.opsForList();
		return ops.range(key, start, end);
	}

	/**
	 * 获取列表长度
	 * @param key 键
	 * @return 集合长度
	 */
	public Long lLen(String key) {
		return redisTemplate.opsForList().size(key);
	}

	/**
	 * 通过索引设置列表元素的值
	 * @param key 键
	 * @param idx 索引
	 * @param value 值
	 */
	public void lSet(String key, long idx, Object value) {
		redisTemplate.opsForList().set(key, idx, value);
	}

	/**
	 * 对一个列表进行修剪(trim)，就是说，让列表只保留指定区间内的元素，不在指定区间之内的元素都将被删除
	 * @param key 键
	 * @param start 开始区间
	 * @param end 结束区间
	 */
	public void lTrim(String key, long start, long end) {
		redisTemplate.opsForList().trim(key, start, end);
	}

	/**
	 * 在列表中添加一个值
	 * @param key 键
	 * @param t 值
	 * @param <T> 值类型
	 * @return 执行 RPUSH 操作后，列表的长度
	 */
	@SuppressWarnings("unchecked")
	public <T> Long rPush(String key, T t) {
		ListOperations<String, T> ops = (ListOperations<String, T>) redisTemplate.opsForList();
		return ops.rightPush(key, t);
	}

	/**
	 * 在列表中添加一个或多个值
	 * @param key 键
	 * @param t 一个或多个值
	 * @param <T> 值类型
	 * @return 执行 RPUSH 操作后，列表的长度
	 */
	@SuppressWarnings("unchecked")
	public <T> Long rPush(String key, T... t) {
		ListOperations<String, T> ops = (ListOperations<String, T>) redisTemplate.opsForList();
		return ops.rightPushAll(key, t);
	}

	/**
	 * 在列表中添加一个或多个值
	 * @param key 键
	 * @param collection 集合
	 * @param <T> 值类型
	 * @return 执行 RPUSH 操作后，列表的长度
	 */
	@SuppressWarnings("unchecked")
	public <T> Long rPushAll(String key, Collection<T> collection) {
		ListOperations<String, T> ops = (ListOperations<String, T>) redisTemplate.opsForList();
		return ops.rightPushAll(key, collection);
	}

	/**
	 * 将一个插入到列表头部
	 * @param key 键
	 * @param t 值
	 * @param <T> 值类型
	 * @return 执行 LPUSH 命令后，列表的长度
	 */
	@SuppressWarnings("unchecked")
	public <T> Long lPush(String key, T t) {
		ListOperations<String, T> ops = (ListOperations<String, T>) redisTemplate.opsForList();
		return ops.leftPush(key, t);
	}

	/**
	 * 将一个或多个值插入到列表头部
	 * @param key 键
	 * @param t 值
	 * @param <T> 值类型
	 * @return 执行 LPUSH 命令后，列表的长度
	 */
	@SuppressWarnings("unchecked")
	public <T> Long lPush(String key, T... t) {
		ListOperations<String, T> ops = (ListOperations<String, T>) redisTemplate.opsForList();
		return ops.leftPushAll(key, t);
	}

	/**
	 * 将一个或多个值插入到列表头部
	 * @param key 键
	 * @param collection 集合
	 * @param <T> 值类型
	 * @return 执行 LPUSH 命令后，列表的长度
	 */
	@SuppressWarnings("unchecked")
	public <T> Long lPushAll(String key, Collection<T> collection) {
		ListOperations<String, T> ops = (ListOperations<String, T>) redisTemplate.opsForList();
		return ops.leftPushAll(key, collection);
	}

	/**
	 * 向集合添加一个或多个成员
	 * @param key 键
	 * @param value 值
	 */
	public void sAdd(String key, String... value) {
		redisTemplate.opsForSet().add(key, value);
	}

	/**
	 * 根据模板获得所有的key
	 *
	 * @param bytePattern 正则表达式
	 * @return 缓存KEY
	 */
	public Set<byte[]> keys(byte[] bytePattern) {
		return redisTemplate.execute((RedisCallback<Set<byte[]>>) connection -> connection.keys(bytePattern));
	}
}
