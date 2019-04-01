package com.myland.framework.shiro;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.crazycake.shiro.serializer.RedisSerializer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.annotation.Resource;

/**
 * Shiro配置
 *
 * @author SunYanQing
 */
@Configuration
@ConditionalOnProperty("shiro.filter.chain.definition-map")
@PropertySource("classpath:application.yml")
@EnableConfigurationProperties({FilterChainProperties.class})
public class ShiroConfig {

	@Resource
	private RedisProperties redisProperties;

	@Resource
	private FilterChainProperties filterChainProperties;

	/**
	 * ShiroFilterFactoryBean 处理拦截资源文件问题。
	 * 注意：单独一个ShiroFilterFactoryBean配置是或报错的，以为在
	 * 初始化ShiroFilterFactoryBean的时候需要注入：SecurityManager
	 * <p>
	 * Filter Chain定义说明
	 * 1、一个URL可以配置多个Filter，使用逗号分隔
	 * 2、当设置多个过滤器时，全部验证通过，才视为通过
	 * 3、部分过滤器可指定参数，如perms，roles
	 */
	@Bean
	public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

		// 必须设置 SecurityManager
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainProperties.getDefinitionMap());
		return shiroFilterFactoryBean;
	}

	@Bean
	public SecurityManager securityManager(AuthorizingRealm realm) {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		// 设置realm
		securityManager.setRealm(realm);
		// 自定义缓存实现 使用redis
		securityManager.setCacheManager(cacheManager());
		// 自定义session管理 使用redis
		securityManager.setSessionManager(sessionManager());
		return securityManager;
	}

	@Bean
	public RedisSerializer fastJson2JsonRedisSerializer() {
		return new FastJson2JsonRedisSerializer<>(Object.class);
	}


	/**
	 * cacheManager 缓存 redis实现
	 * 使用的是shiro-redis开源插件
	 */
	private RedisCacheManager cacheManager() {
		RedisCacheManager redisCacheManager = new RedisCacheManager();
		redisCacheManager.setRedisManager(redisManager());
		redisCacheManager.setValueSerializer(fastJson2JsonRedisSerializer());
		return redisCacheManager;
	}

	/**
	 * 配置shiro redisManager
	 * 使用的是shiro-redis开源插件
	 */
	private RedisManager redisManager() {
		RedisManager redisManager = new RedisManager();
		redisManager.setHost(redisProperties.getHost() + ":" + redisProperties.getPort());
		redisManager.setTimeout(new Long(redisProperties.getTimeout().toMillis()).intValue());
		redisManager.setPassword(redisProperties.getPassword());
		redisManager.setDatabase(redisProperties.getDatabase());
		return redisManager;
	}

	/**
	 * Session Manager
	 * 使用的是shiro-redis开源插件
	 */
	@Bean
	public DefaultWebSessionManager sessionManager() {
		DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
		sessionManager.setSessionDAO(redisSessionDAO());
		sessionManager.setGlobalSessionTimeout(1800000);
		sessionManager.setSessionIdCookie(sessionIdCookie());
		sessionManager.setSessionIdCookieEnabled(true);
		return sessionManager;
	}

	/**
	 * 指定本系统sessionid, 问题: 与servlet容器名冲突, 如jetty, tomcat 等默认jsessionid,
	 * 当跳出shiro servlet时如error-page容器会为jsessionid重新分配值导致登录会话丢失!
	 */
	@Bean
	public SimpleCookie sessionIdCookie() {
		SimpleCookie simpleCookie = new SimpleCookie();
		simpleCookie.setName("SESSION-ID");
		return simpleCookie;
	}

	/**
	 * RedisSessionDAO shiro sessionDao层的实现 通过redis
	 * 使用的是shiro-redis开源插件
	 */
	@Bean
	public RedisSessionDAO redisSessionDAO() {
		RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
		redisSessionDAO.setRedisManager(redisManager());
		return redisSessionDAO;
	}

	/**
	 * 开启shiro aop注解支持.
	 * 使用代理方式;所以需要开启代码支持;
	 */
	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
		return authorizationAttributeSourceAdvisor;
	}
}
