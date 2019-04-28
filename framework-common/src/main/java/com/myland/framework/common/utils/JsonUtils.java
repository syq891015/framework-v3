package com.myland.framework.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * JSON工具类
 *
 * @author SunYanQing
 * @date 2017-11-30
 */
@Slf4j
public class JsonUtils {

	public static Object parseObject(String text) {

		if (StringUtils.isBlank(text)) {
			return null;
		}

		Object obj = null;
		try {
			obj = JSON.parse(text);
		} catch (Exception e) {
			log.error("### Parse JSON text err. JSON text is " + text, e);
		}
		return obj;
	}

	/**
	 * 将json字符串转为JSONObject
	 *
	 * @param text json字符串
	 * @return JSONObject
	 */
	public static JSONObject toJSONObject(String text) {
		if (StringUtils.isBlank(text)) {
			return new JSONObject();
		}
		return JSONObject.parseObject(text);
	}

	/**
	 * 将json字符串转为JSONOArray
	 *
	 * @param text json字符串
	 * @return JSONOArray
	 */
	public static <T> List<T> toJSONArray(String text, Class<T> clazz) {
		if (StringUtils.isBlank(text)) {
			return new ArrayList<>();
		}
		return JSONArray.parseArray(text, clazz);
	}

	/**
	 * 将字符串转换为Bean
	 *
	 * @param text  待转化的字符串
	 * @param clazz 转为此类的对象
	 * @param <T>   类型
	 * @return Bean对象
	 */
	public static <T> T parseObject(String text, Class<T> clazz) {
		if (StringUtils.isBlank(text)) {
			return null;
		}
		return JSON.parseObject(text, clazz);
	}

	/**
	 * 将Object对象转换为json字符串
	 *
	 * @param o 对象
	 * @return json字符串
	 */
	public static String toJSON(Object o) {
		if (o == null) {
			return StringUtils.EMPTY;
		}
		return JSONObject.toJSONString(o);
	}

	/**
	 * 将Object对象转换为json字符串
	 *
	 * @param o 对象
	 * @return json字符串
	 */
	public static String listToJSON(Object o) {
		if (o == null) {
			return StringUtils.EMPTY;
		}
		return JSONArray.toJSONString(o);
	}

	/**
	 * 将T类型对象转换为json字符串
	 *
	 * @param t                 对象
	 * @param serializerFeature 序列化特征
	 * @return json字符串
	 */
	public static <T> String toJSON(T t, SerializerFeature... serializerFeature) {
		if (t == null) {
			return StringUtils.EMPTY;
		}
		return JSON.toJSONString(t, serializerFeature);
	}

	/**
	 * JSONObject对象转换成JavaBean
	 * @param jsonObject JSONObject 对象
	 * @param clazz JavaBean类型
	 * @param <T> JavaBean类型
	 * @return JavaBean对象
	 */
	public static <T> T toBean(Object jsonObject, Class<T> clazz) {
		assert jsonObject instanceof JSON;
		return JSON.toJavaObject((JSON)jsonObject, clazz);
	}

	/**
	 * JSONArray对象转成List
	 * @param jsonArray JSONArray对象
	 * @param clazz list元素类型
	 * @param <T> list元素类型
	 * @return List
	 */
	public static <T> List<T> toList(Object jsonArray, Class<T> clazz) {
		assert jsonArray instanceof JSON;
		return toJSONArray(((JSON) jsonArray).toJSONString(), clazz);
	}
}
