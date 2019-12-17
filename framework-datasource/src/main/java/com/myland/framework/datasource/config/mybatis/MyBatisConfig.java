package com.myland.framework.datasource.config.mybatis;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * MyBatis基础配置
 * 定义框架和应用DAO扫描路径
 *
 * @author SunYanQing
 */
@Slf4j
@Configuration
@ConditionalOnProperty("spring.datasource.url")
public class MyBatisConfig {
	private final DataSource dataSource;

	@Value("${myland.mybatis.type-aliases-packages:com.myland.**.po}")
	private String typeAliasesPackages;

	@Value("${myland.mybatis.mapper-locations:classpath*:mapper/**/*.xml}")
	private String mapperLocations;

	@Autowired
	public MyBatisConfig(@Qualifier("dataSource") DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Bean
	@ConditionalOnMissingBean(name = "sqlSessionFactory")
	public SqlSessionFactory sqlSessionFactory() {
		SqlSessionFactoryBean sqlSession = new MybatisSqlSessionFactoryBean();
		sqlSession.setDataSource(dataSource);
		/*
		 * SpringBootVFS 是类扫描器
		 * 打包成jar时，setTypeAliasesPackage(“xxx”)找不到类的问题。MyBatis通过VFS来扫描，
		 * 在Spring Boot中由于是嵌套Jar，导致Mybatis默认的VFS实现DefaultVFS无法扫描嵌套Jar中的类，需要改成SpringBootVFS扫描
		 */
		sqlSession.setVfs(SpringBootVFS.class);
		// 扫描entity包 使用别名
		sqlSession.setTypeAliasesPackage(typeAliasesPackages);
		org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
		// 使用jdbc的getGeneratedKeys获取数据库自增主键值
		configuration.setUseGeneratedKeys(true);
		//使用列别名替换列名 select user as User
		configuration.setUseColumnLabel(true);
		//-自动使用驼峰命名属性映射字段   userId    user_id
		configuration.setMapUnderscoreToCamelCase(true);
		sqlSession.setConfiguration(configuration);
		sqlSession.setFailFast(true);

		//添加XML目录
		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		try {
			sqlSession.setMapperLocations(resolver.getResources(mapperLocations));
			return sqlSession.getObject();
		} catch (Exception e) {
			log.error("SqlSession配置错误！", e);
			throw new RuntimeException(e);
		}
	}

	@Bean
	@ConditionalOnMissingBean(name = "sqlSessionTemplate")
	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}

	@Bean
	public PlatformTransactionManager annotationDrivenTransactionManager() {
		return new DataSourceTransactionManager(dataSource);
	}
}