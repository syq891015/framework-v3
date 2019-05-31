package com.myland.framework.common.utils.validator;

import org.apache.commons.lang3.StringUtils;

/**
 * 断言
 *
 * @author SunYanQing
 * @version 1.0
 * @date 2019/5/9 13:56
 */
public abstract class Assert {
	public static void isNotBlank(String str, String msg) {
		if (StringUtils.isBlank(str)) {
			throw new IllegalArgumentException(msg);
		}
	}

	public static void isNotNull(Object obj, String msg) {
		if (obj == null) {
			throw new IllegalArgumentException(msg);
		}
	}
}
