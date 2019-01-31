package com.myland.framework.logging.itfc;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Component;

/**
 * @author SunYanQing
 * @version 1.0
 * @date 2018-10-12 16:52
 */
@Component
@ConditionalOnMissingBean(name = "userName")
public class UserNameImpl implements UserName {
	/**
	 * 获得用户名
	 *
	 * @return 返回登录的用户名
	 */
	@Override
	public String getUserName() {
		return "";
	}
}
