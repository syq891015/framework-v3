package com.myland.framework.web.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 上传文件配置
 *
 * @author SunYanQing
 * @version 1.0
 * @date 2018-09-05 12:29
 */
@Configuration
@ConditionalOnProperty(name = "multipart.max-file-size")
public class MultipartConfig {

	/**
	 * 设置单个文件的大小，单位B
	 */
	@Value("${multipart.max-file-size:20}")
	private long maxFileSize;

	/**
	 * 设置单次请求的文件的总大小，单位B
	 */
	@Value("${multipart.max-request-size:200}")
	private long maxRequestSize;

	@Bean
	public MultipartConfigFactory config() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		// 设置单个文件的大小
		factory.setMaxFileSize(maxFileSize);
		// 设置单次请求的文件的总大小
		factory.setMaxRequestSize(maxRequestSize);
		return factory;
	}
}
