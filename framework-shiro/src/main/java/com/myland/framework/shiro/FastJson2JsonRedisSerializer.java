package com.myland.framework.shiro;

import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.myland.framework.common.utils.JsonUtils;
import org.crazycake.shiro.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.nio.charset.StandardCharsets;

/**
 * FastJson redis序列化支持
 *
 * @author SunYanQing
 */
public class FastJson2JsonRedisSerializer<T> implements RedisSerializer<T> {

	private Class<T> clazz;

	FastJson2JsonRedisSerializer(Class<T> clazz) {
		super();
		ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
		this.clazz = clazz;
	}


	@Override
	public byte[] serialize(T t) throws SerializationException {
		if (t == null) {
			return new byte[0];
		}
		// SerializerFeature.WriteClassName将内层类型写到json中，用以反序列化时，将内层json反序列化为此类型的对象
		return JsonUtils.toJSON(t, SerializerFeature.WriteClassName).getBytes(StandardCharsets.UTF_8);
	}

	@Override
	public T deserialize(byte[] bytes) throws SerializationException {
		if (bytes == null || bytes.length <= 0) {
			return null;
		}
		return JsonUtils.parseObject(new String(bytes, StandardCharsets.UTF_8), clazz);
	}
}
