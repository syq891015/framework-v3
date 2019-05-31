package com.myland.framework.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 字符串工具
 *
 * @author SunYanQing
 * @date 2017-05-07
 */
public class StrUtils {

	/**
	 * 反驼峰形式，形如：projectNo => project_no
	 *
	 * @param orig 源字符串
	 */
	public static String inhump(String orig) {
		char[] origAry = orig.toCharArray();
		StringBuilder sb = new StringBuilder();
		for (char c : origAry) {
			int cIdx = (int) c;
			if (cIdx >= 65 && cIdx <= 90) {
				sb.append("_");
			}
			sb.append(c);
		}
		return sb.toString().toLowerCase();
	}

	public static List<String> split(String str, String sperator) {
		assert StringUtils.isNotBlank(str);
		assert sperator != null;

		return Arrays.asList(str.split(sperator));
	}

	public static List<Long> split2Long(String str, String sperator) {
		assert StringUtils.isNotBlank(str);
		assert sperator != null;

		List<String> list = split(str, sperator);

		List<Long> longList = new ArrayList<>(list.size());
		for (String s : list) {
			longList.add(Long.parseLong(s));
		}
		return longList;
	}
}
