package com.myland.framework.authority.consts;

import org.apache.shiro.crypto.hash.Sha256Hash;

/**
 * 用户常量
 *
 * @author SunYanQing
 * @version 1.0
 * @date 2018-07-01 14:14
 */
public class UserConstants {

	/**
	 * 超级管理员
	 */
	public static final Long SUPER_ADMIN = 1L;

	/**
	 * 超级管理员账号
	 */
	public static final String SUPER_ADMIN_NAME = "admin";

	/**
	 * 账户锁定
	 */
	public static final Byte LOCK_STATUS = 0;

	/**
	 * 账户激活
	 */
	public static final String ACTIVATE_STATUS = "1";

	/**
	 * 默认密码明文
	 */
	public static final String DEFAULT_PASSWD = "123";

	/**
	 * 默认密码密文
	 */
	public static final String DEFAULT_PASSWD_ENCRYPT = new Sha256Hash(DEFAULT_PASSWD).toHex();
}
