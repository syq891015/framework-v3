package com.myland.framework.datasource.config.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * Redis配置
 *
 * @author zhb
 * @date 2018/3/9
 */
@Configuration
@ConditionalOnProperty("spring.redis.host")
public class RedisConfig {

	/**
	 * redis失效时间
	 */
	@Value("${redis.expire:-1}")
	private int expire;

	private RedisSerializer fastJsonRedisSerializer() {
		return new FastJsonRedisSerializer<>(Object.class);
	}

	@Bean
	@ConditionalOnMissingBean(name = "redisTemplate")
	public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {
		RedisTemplate<String, String> template = new RedisTemplate<>();
		RedisSerializer<String> stringRedisSerializer = new StringRedisSerializer();

		template.setConnectionFactory(factory);
		// key序列化方式
		template.setKeySerializer(stringRedisSerializer);
		// value序列化
		template.setValueSerializer(stringRedisSerializer);
		// hashkey序列化
		template.setHashKeySerializer(stringRedisSerializer);
		// value hashmap序列化
		template.setHashValueSerializer(fastJsonRedisSerializer());

		RedisUtils.redisTemplate = template;
		RedisUtils.timeout = expire;
		return template;
	}
}
