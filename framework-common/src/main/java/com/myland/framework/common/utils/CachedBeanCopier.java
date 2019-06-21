package com.myland.framework.common.utils;

import com.myland.framework.common.utils.validator.Assert;
import net.sf.cglib.beans.BeanCopier;
import org.apache.commons.lang3.ClassUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 缓存BeanCopier工具
 *
 * @author SunYanQing
 */
public class CachedBeanCopier {

	private static final Map<String, BeanCopier> BEAN_COPIERS = new HashMap<>();

	public static void copy(Object srcObj, Object destObj) {
		Assert.isNotNull(srcObj, "BeanCopier source object is null.");
		Assert.isNotNull(destObj, "BeanCopier dest object is null.");

		String key = genKey(srcObj.getClass(), destObj.getClass());
		BeanCopier copier;
		if (!BEAN_COPIERS.containsKey(key)) {
			copier = BeanCopier.create(srcObj.getClass(), destObj.getClass(), false);
			BEAN_COPIERS.put(key, copier);
		} else {
			copier = BEAN_COPIERS.get(key);
		}
		copier.copy(srcObj, destObj, null);
	}

	private static String genKey(Class<?> srcClazz, Class<?> destClazz) {
		return srcClazz.getName() + destClazz.getName();
	}

	public static <K, J> List<J> copy(List<K> srcList, Class<J> destClazz) {
		Assert.isNotNull(srcList, "BeanCopier source list is null.");
		Assert.isNotNull(destClazz, "BeanCopier dest class is null.");
		try {
			List<J> destList = new ArrayList<>(srcList.size());
			for (K k : srcList) {
				J j = destClazz.newInstance();
				copy(k, j);
				destList.add(j);
			}
			return destList;
		} catch (InstantiationException e) {
			throw new RuntimeException("目标类实例化异常, className=[" + destClazz.getName() + "]");
		} catch (IllegalAccessException e) {
			throw new RuntimeException("目标类非法访问, className=[" + destClazz.getName() + "]");
		}
	}
}