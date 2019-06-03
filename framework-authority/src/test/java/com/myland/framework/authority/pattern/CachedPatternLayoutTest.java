package com.myland.framework.authority.pattern;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author SunYanQing
 * @version 1.0
 * @date 2019/6/3 14:17
 */
public class CachedPatternLayoutTest {
	@Test
	public void format() throws Exception {
		Assert.assertEquals(CachedPatternLayout.format("exam"), "exam");
		System.out.println(CachedPatternLayout.format("examinee/%d{yyyyMMdd}"));
	}

}