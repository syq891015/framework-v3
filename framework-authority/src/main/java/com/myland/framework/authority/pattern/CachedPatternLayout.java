package com.myland.framework.authority.pattern;

import java.util.HashMap;
import java.util.Map;

/**
 * 缓存格式化工具
 *
 * @author SunYanQing
 * @version 1.0
 * @date 2019/6/3 14:12
 */
public class CachedPatternLayout {

	private static final Map<String, PatternLayout> PATTERN_LAYOUT_MAP = new HashMap<>();

	public static String format(String value) {
		PatternLayout patternLayout;
		if (!PATTERN_LAYOUT_MAP.containsKey(value)) {
			patternLayout = new PatternLayout(value);
			PATTERN_LAYOUT_MAP.put(value, patternLayout);
		} else {
			patternLayout = PATTERN_LAYOUT_MAP.get(value);
		}
		return patternLayout.format();
	}
}
