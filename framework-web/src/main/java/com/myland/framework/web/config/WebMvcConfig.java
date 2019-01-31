package com.myland.framework.web.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * MVC配置
 *
 * @author abc
 */
@Configuration
@ServletComponentScan
public class WebMvcConfig implements WebMvcConfigurer {

	/**
	 * CORS跨域映射
	 *
	 * @param registry 注册器
	 */
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
				.allowedHeaders(CorsConfiguration.ALL)
				.allowedOrigins(CorsConfiguration.ALL)
				.exposedHeaders("Access-Control-Allow-Method", "Access-Control-Allow-Origin")
				.allowCredentials(true)
				.allowedMethods(CorsConfiguration.ALL)
				.maxAge(3600);
	}

	@Bean
	public FastJsonHttpMessageConverter fastJsonHttpMessageConverter() {
		return new FastJsonHttpMessageConverter();
	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();

		List<MediaType> mediaTypes = new ArrayList<>();
		mediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
		fastConverter.setSupportedMediaTypes(mediaTypes);

		FastJsonConfig fastJsonConfig = new FastJsonConfig();
		fastJsonConfig.setSerializerFeatures(
				SerializerFeature.PrettyFormat,
				SerializerFeature.WriteNullNumberAsZero,
				SerializerFeature.WriteNullListAsEmpty,
				SerializerFeature.WriteNullStringAsEmpty,
				SerializerFeature.WriteNullBooleanAsFalse,
				SerializerFeature.WriteDateUseDateFormat
		);
		fastConverter.setFastJsonConfig(fastJsonConfig);
		converters.add(fastConverter);
	}
}
