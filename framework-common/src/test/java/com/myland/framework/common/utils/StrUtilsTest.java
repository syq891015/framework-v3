package com.myland.framework.common.utils;

import com.myland.framework.common.consts.CharacterConstants;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author SunYanQing
 * @version 1.0
 * @date 2019/5/30 10:16
 */
public class StrUtilsTest {
	@Test
	public void split() throws Exception {
	}

	@Test
	public void inhump() throws Exception {
	}

	@Test
	public void split2Long() throws Exception {
		String str = "232|123|5234";
		System.out.println(StrUtils.split2Long(str, CharacterConstants.PIPE_REG_EX));;
	}

}