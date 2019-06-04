package com.myland.framework.authority.pattern;

import org.junit.Assert;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * @author SunYanQing
 * @version 1.0
 * @date 2019/6/3 14:17
 */
public class CachedPatternLayoutTest {
	@Test
	public void format() throws Exception {
		Assert.assertEquals(CachedPatternLayout.format("question"), "question");

		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		Assert.assertEquals(CachedPatternLayout.format("examinee/%d{yyyyMMdd}"), "examinee/" + dateFormat.format(new Date()));
	}

}