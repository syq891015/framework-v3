package com.myland.framework.authority.consts;

/**
 * 缓存常量
 *
 * @author SunYanQing
 * @version 1.0
 * @date 2019-03-04 17:12
 */
public class CacheConstants {

	/**
	 * Key，字典名称
	 */
	public static final String KEY_DIC_NAME = "DIC_NAME";

	/**
	 * Key，系统配置
	 */
	public static final String KEY_SYSTEM_CONFIG = "SYSTEM_CONFIG";

	/**
	 * Hash[Key]，是否开启验证码
	 */
	public static final String HKEY_CAPTCHA_ENABLED = "Captcha-Enabled";

	/**
	 * Hash[Key]，上传文件一级目录
	 */
	public static final String HKEY_UPLOAD_DIR = "Upload-Dir";

	/**
	 * Hash[Key]，上传文件二级目录，即文件访问路径
	 */
	public static final String HKEY_ACCESS_DIR = "Access-Dir";

	/**
	 * Hash[Key]，文件访问路径URL
	 */
	public static final String HKEY_FILE_ACCESS_URL = "File-Access-Url";

	/**
	 * Key，字典集合
	 */
	public static final String KEY_DIC_LIST = "DIC_LIST";
}
