package com.myland.framework.shiro;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Shiro过滤器链定义属性
 * @author SunYanQing
 * @version 1.0
 * @date 2019-03-01 15:51
 */
@Data
@ConfigurationProperties(
		prefix = "shiro.filter.chain"
)
public class FilterChainProperties {

	private Map<String, String> definitionMap = new LinkedHashMap<>(10);
}
