package com.myland.framework.authority.dataAuth;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 数据权限业务实现类
 *
 * @author SunYanQing
 * @version 1.0
 * @date 2019-03-05 10:29
 */
@Configuration
public class DataAuthServiceImpl implements DataAuthService {
	/**
	 * 获得用户的数据权限
	 *
	 * @return 各种数据权限组成的Map表
	 */
	@Override
	public Map<String, Object> getDataAuthority() {
		return new HashMap<>(1);
	}

	@Bean
	@ConditionalOnMissingBean(name = {"dataAuthService"})
	public DataAuthService dataAuthService() {
		return new DataAuthServiceImpl();
	}
}
