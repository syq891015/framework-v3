package com.myland.framework.common.utils;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.zip.CRC32;
import java.util.zip.CheckedInputStream;

/**
 * 加密工具类，包含MD5,BASE64,SHA,CRC32
 *
 * @author SunYanQing
 */
public class CryptoUtils {

	/**
	 * DES
	 */
	public static final String ALGORITHM_DES = "DES";

	/**
	 * MD5加密
	 *
	 * @param bytes an array of byte.
	 * @return {@link String} object.
	 */
	public static String encodeMD5(final byte[] bytes) {
		return DigestUtils.md5Hex(bytes);
	}

	/**
	 * MD5加密，默认UTF-8
	 *
	 * @param str {@link String} object.
	 * @return {@link String} object.
	 */
	public static String encodeMD5(final String str) {
		return encodeMD5(str, StandardCharsets.UTF_8);
	}

	/**
	 * MD5加密
	 *
	 * @param str     {@link String} object.
	 * @param charset {@link Charset} object.
	 * @return {@link String} object.
	 */
	public static String encodeMD5(final String str, final Charset charset) {
		if (str == null) {
			return "";
		}
		return encodeMD5(str.getBytes(charset));
	}

	/**
	 * SHA加密
	 *
	 * @param bytes an array of byte.
	 * @return {@link String} object.
	 */
	public static String encodeSHA(final byte[] bytes) {
		return DigestUtils.sha512Hex(bytes);
	}

	/**
	 * SHA加密
	 *
	 * @param str     {@link String} object.
	 * @param charset {@link Charset} object.
	 * @return {@link String} object.
	 */
	public static String encodeSHA(final String str, final Charset charset) {
		if (str == null) {
			return "";
		}
		return encodeSHA(str.getBytes(charset));
	}

	/**
	 * SHA加密,默认utf-8
	 *
	 * @param str {@link String} object.
	 * @return {@link String} object.
	 */
	public static String encodeSHA(final String str) {
		return encodeSHA(str, StandardCharsets.UTF_8);
	}

	/**
	 * BASE64加密
	 *
	 * @param bytes an array of byte.
	 * @return {@link String} object.
	 */
	public static String encodeBASE64(final byte[] bytes) {
		return Base64.encodeBase64String(bytes);
	}

	/**
	 * BASE64加密
	 *
	 * @param str     {@link String} object.
	 * @param charset {@link Charset} object.
	 * @return {@link String} object.
	 */
	public static String encodeBASE64(final String str, Charset charset) {
		if (str == null) {
			return "";
		}
		return encodeBASE64(str.getBytes(charset));
	}

	/**
	 * BASE64加密,默认UTF-8
	 *
	 * @param str {@link String} object.
	 * @return {@link String} object.
	 */
	public static String encodeBASE64(final String str) {
		return encodeBASE64(str, StandardCharsets.UTF_8);
	}

	/**
	 * BASE64解密,默认UTF-8
	 *
	 * @param str {@link String} object.
	 * @return {@link String} object.
	 */
	public static String decodeBASE64(String str) {
		return decodeBASE64(str, StandardCharsets.UTF_8);
	}

	/**
	 * BASE64解密
	 *
	 * @param str     {@link String} object.
	 * @param charset {@link Charset} 字符编码
	 * @return {@link String} object.
	 */
	public static String decodeBASE64(String str, Charset charset) {
		return new String(Base64.decodeBase64(str.getBytes(charset)), charset);
	}

	/**
	 * CRC32字节校验
	 *
	 * @param bytes an array of byte.
	 * @return {@link String} object.
	 */
	public static String crc32(byte[] bytes) {
		CRC32 crc32 = new CRC32();
		crc32.update(bytes);
		return Long.toHexString(crc32.getValue());
	}

	/**
	 * CRC32字符串校验
	 *
	 * @param str     {@link String} object.
	 * @param charset {@link Charset} object.
	 * @return {@link String} object.
	 */
	public static String crc32(final String str, Charset charset) {
		byte[] bytes = str.getBytes(charset);
		return crc32(bytes);
	}

	/**
	 * CRC32字符串校验,默认UTF-8编码读取
	 *
	 * @param str {@link String} object.
	 * @return {@link String} object.
	 */
	public static String crc32(final String str) {
		return crc32(str, StandardCharsets.UTF_8);
	}

	/**
	 * CRC32流校验
	 *
	 * @param input {@link InputStream} object.
	 * @return {@link String} object.
	 */
	public static String crc32(InputStream input) {
		CRC32 crc32 = new CRC32();
		CheckedInputStream checkInputStream;
		int test = 0;
		try {
			checkInputStream = new CheckedInputStream(input, crc32);
			do {
				test = checkInputStream.read();
			} while (test != -1);
			return Long.toHexString(crc32.getValue());
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * CRC32文件唯一校验
	 *
	 * @param file {@link File} object.
	 * @return {@link String} object.
	 */
	public static String crc32(File file) {
		try (InputStream input = new BufferedInputStream(new FileInputStream(file))) {
			return crc32(input);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * CRC32文件唯一校验
	 *
	 * @param url {@link URL} object.
	 * @return {@link String} object.
	 */
	public static String crc32(URL url) {
		try (InputStream input = url.openStream()) {
			return crc32(input);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * DES加密
	 *
	 * @param bytes an array of byte.
	 * @param key   密钥字符串
	 * @return {@link String}
	 */
	public static String encodeDES(final byte[] bytes, final String key) {
		try {
			SecretKey deskey = getDESKey(key);
			Cipher c1 = Cipher.getInstance(ALGORITHM_DES);
			c1.init(Cipher.ENCRYPT_MODE, deskey);
			byte[] cipherByte = c1.doFinal(bytes);
			return new String(cipherByte, StandardCharsets.UTF_8);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * DES加密
	 *
	 * @param str orig str
	 * @param key 密钥字符串
	 * @return {@link String}
	 */
	public static String encodeDES(final String str, final String key) {
		return encodeDES(str.getBytes(StandardCharsets.UTF_8), key);
	}

	/**
	 * DES解密
	 *
	 * @param bytes an array of byte.
	 * @param key   密钥字符串
	 * @return {@link String}
	 */
	public static String decodeDES(final byte[] bytes, final String key) {
		try {
			SecretKey deskey = getDESKey(key);
			Cipher c1 = Cipher.getInstance(ALGORITHM_DES);
			c1.init(Cipher.DECRYPT_MODE, deskey);
			byte[] cipherByte = c1.doFinal(bytes);
			return new String(cipherByte, StandardCharsets.UTF_8);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * DES解密
	 *
	 * @param str orig str
	 * @param key 密钥字符串
	 * @return {@link String}
	 */
	public static String decodeDES(final String str, final String key) {
		return decodeDES(str.getBytes(StandardCharsets.UTF_8), key);
	}

	/**
	 * 从指定字节数组生成密钥，密钥所需的字节数组长度为8，不足8时后面补0，超出8只取前8
	 *
	 * @param bytes 用以指定密钥的字节数组
	 * @return 生成的密钥对象
	 */
	private static SecretKeySpec getKey(final byte[] bytes, final String algorithm) {
		// 创建一个空的8位字节数组（默认值为0）
		byte[] bytes8 = new byte[8];
		// 将原始字节数组转换为8位
		for (int i = 0; i < bytes.length && i < bytes8.length; i++) {
			bytes8[i] = bytes[i];
		}
		// 生成密钥
		return new SecretKeySpec(bytes8, algorithm);
	}

	/**
	 * 从指定字节数组生成密钥，密钥所需的字节数组长度为8，不足8时后面补0，超出8只取前8
	 *
	 * @param key 密钥
	 * @return 生成的密钥对象
	 */
	public static SecretKeySpec getKey(final String key, final String algorithm) {
		return getKey(key.getBytes(StandardCharsets.UTF_8), algorithm);
	}

	/**
	 * DES密钥
	 * 从指定字节数组生成密钥，密钥所需的字节数组长度为8，不足8时后面补0，超出8只取前8
	 *
	 * @param key 密钥
	 * @return 生成的密钥对象
	 */
	private static SecretKeySpec getDESKey(final String key) {
		return getKey(key.getBytes(StandardCharsets.UTF_8), ALGORITHM_DES);
	}
}