package com.myland.framework.authority.dataAuth;

import java.util.Map;

/**
 * 数据权限接口
 *
 * @author SunYanQing
 * @version 1.0
 * @date 2019-03-05 10:29
 */
public interface DataAuthService {

	/**
	 * 获得用户的数据权限
	 * @return 各种数据权限组成的Map表
	 */
	Map<String,Object> getDataAuthority();
}
