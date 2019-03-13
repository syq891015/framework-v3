package com.myland.framework.authority.sysConfig;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;


/**
 * 生成验证码配置
 *
 * @author SunYanQing
 */
@Configuration
public class KaptchaConfig {

	@Bean
	public DefaultKaptcha producer() {
		Properties properties = new Properties();
		// 是否有边框  默认为true  我们可以自己设置yes，no
		properties.put(Constants.KAPTCHA_BORDER, "no");
		// 验证码文本字符颜色  默认为Color.BLACK
		properties.put(Constants.KAPTCHA_TEXTPRODUCER_FONT_COLOR, "black");
		// 验证码文本字符间距  默认为2
		properties.put(Constants.KAPTCHA_TEXTPRODUCER_CHAR_SPACE, "5");
		// 验证码文本字符内容范围  默认为abcde2345678gfynmnpwx
		properties.put(Constants.KAPTCHA_TEXTPRODUCER_CHAR_STRING, "abcde2345678gfynmnpwx");
		// 验证码文本字符长度  默认为5
		properties.put(Constants.KAPTCHA_TEXTPRODUCER_CHAR_LENGTH, "4");
		// 设置宽和高
		properties.put(Constants.KAPTCHA_IMAGE_WIDTH, "140");
		properties.put(Constants.KAPTCHA_IMAGE_HEIGHT, "40");
		Config config = new Config(properties);
		DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
		defaultKaptcha.setConfig(config);
		return defaultKaptcha;
	}
}
