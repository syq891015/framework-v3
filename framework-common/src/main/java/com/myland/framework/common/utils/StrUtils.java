package com.myland.framework.common.utils;

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
}
