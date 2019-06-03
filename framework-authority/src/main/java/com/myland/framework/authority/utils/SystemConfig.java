package com.myland.framework.authority.utils;

import com.myland.framework.authority.config.ConfigService;
import com.myland.framework.authority.po.Config;

/**
 * 系统配置
 *
 * @author SunYanQing
 * @version 1.0
 * @date 2019/6/3 10:41
 */
public class SystemConfig {

	/**
	 * Hash[Key]，是否开启验证码
	 */
	private static final String HKEY_CAPTCHA_ENABLED = "Captcha-Enabled";

	/**
	 * Hash[Key]，上传文件一级目录
	 */
	private static final String HKEY_UPLOAD_DIR = "Upload-Dir";

	/**
	 * Hash[Key]，上传文件二级目录，即文件访问路径
	 */
	private static final String HKEY_ACCESS_DIR = "Access-Dir";

	/**
	 * Hash[Key]，文件访问路径URL
	 */
	private static final String HKEY_FILE_ACCESS_URL = "File-Access-Url";

	public static ConfigService configService;

	public static Config getConfig(String hKey) {
		Config config = configService.getConfigInCache(hKey);
		if (config == null) {
			throw new RuntimeException("###系统配置未配置[key=" + hKey + "]");
		}
		return config;
	}

	public static Config getFileAccessUrl() {
		return getConfig(HKEY_FILE_ACCESS_URL);
	}

	public static Config getCaptchaEnabled() {
		return getConfig(HKEY_CAPTCHA_ENABLED);
	}

	public static Config getUploadDir() {
		return getConfig(HKEY_UPLOAD_DIR);
	}

	public static Config getAccessDir() {
		return getConfig(HKEY_ACCESS_DIR);
	}
}
