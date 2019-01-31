package com.myland.framework.common.utils.time;

import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.util.Date;

/**
 * 日期匹配工具封装类，基于common_lang3包里提供的API进行日期数据匹配封装
 * 该工具类用于将字符串形式的日期值按指定的格式匹配转换成相应的日期对象。
 */
public class DateParseUtils {

	/**
	 * 根据指定的格式，将日期字符串匹配成相应的日期对象
	 *
	 * @param dateStr 日期字符串
	 * @param pattern 日期格式字符串
	 * @return 根据dateStr按照pattern格式匹配成的日期对象
	 * @throws ParseException           日期匹配异常
	 * @throws IllegalArgumentException 参数dateStr或pattern为空时可能抛出的异常
	 */
	public static final Date parse(String dateStr, String pattern) throws ParseException {
		return DateUtils.parseDate(dateStr, pattern);
	}

	/**
	 * 字符串(yyyy-MM-dd)匹配成日期
	 *
	 * @param dateStr 日期字符串，形如"2014-08-25"
	 * @return 匹配后对应的日期对象
	 * @throws ParseException 日期格式匹配异常
	 */
	public static final Date parseISOyyyyMMdd(String dateStr) throws ParseException {
		return parse(dateStr, DateUtilConst.FMT_ISO_YYYYMMDD);
	}

	/**
	 * 字符串(yyyyMMdd)匹配成日期
	 *
	 * @param dateStr 年月日，形如"20140825"
	 * @return
	 * @throws ParseException
	 */
	public static final Date parseYyyyMMdd(String dateStr) throws ParseException {
		return parse(dateStr, DateUtilConst.FMT_YYYYMMDD);
	}

	/**
	 * 字符串(yyyy-MM-dd HH:mm:ss，24小时制)匹配成日期
	 *
	 * @param dateStr 日期字符串，形如"2014-08-25 13:37:45"
	 * @return 匹配后对应的日期对象
	 * @throws ParseException 日期格式匹配异常
	 */
	public static final Date parse24HISOyyyyMMddHHmmss(String dateStr) throws ParseException {
		return parse(dateStr, DateUtilConst.FMT_24H_ISO_YYYYMMDDHHMMSS);
	}

	/**
	 * 字符串(yyyy-MM-dd hh:mm:ss，12小时制)匹配成日期
	 *
	 * @param dateStr 日期字符串，形如"2014-08-25 01:55:26"
	 * @return 匹配后对应的日期对象
	 * @throws ParseException 日期格式匹配异常
	 */
	public static final Date parse12HISOyyyyMMddhhmmss(String dateStr) throws ParseException {
		return parse(dateStr, DateUtilConst.FMT_12H_ISO_YYYYMMDDHHMMSS);
	}

	/**
	 * 字符串(yyyyMMddHHmmss，24小时制)匹配成日期
	 *
	 * @param dateStr 日期字符串，形如"20140825173509"
	 * @return 匹配后对应的日期对象
	 * @throws ParseException 日期格式匹配异常
	 */
	public static final Date parse24HyyyyMMddHHmmss(String dateStr) throws ParseException {
		return parse(dateStr, DateUtilConst.FMT_24H_YYYYMMDDHHMMSS);
	}

	/**
	 * 字符串(yyyy年MM月dd日)匹配成日期
	 *
	 * @param dateStr 日期字符串，形如"2014年08月25日"
	 * @return 匹配后对应的日期对象
	 * @throws ParseException 日期格式匹配异常
	 */
	public static final Date parseCnYyyyMMdd(String dateStr) throws ParseException {
		return parse(dateStr, DateUtilConst.FMT_CN_YYYYMMDD);
	}

	/**
	 * 字符串(yyyy-MM-dd hh:mm)匹配成日期
	 *
	 * @param dateStr 日期字符串，12小时制，形如"2014-08-25 02:05"
	 * @return 匹配后对应的日期对象
	 * @throws ParseException 日期格式匹配异常
	 */
	public static final Date parse12HYyyyMMddhhmm(String dateStr) throws ParseException {
		return parse(dateStr, DateUtilConst.FMT_12H_YYYYMMDDHHMM);
	}

	/**
	 * 字符串(yyyy年MM月dd日 hh时mm分ss秒)匹配成日期
	 *
	 * @param dateStr 日期字符串，12小时制，形如"2014年08月25日 01时57分43秒"
	 * @throws ParseException
	 */
	public static final Date parse12HCnYyyyMMddhhmmss(String dateStr) throws ParseException {
		return parse(dateStr, DateUtilConst.FMT_12H_CN_YYYYMMDDHHMMSS);
	}

	public static final Date parse24Hhhmm(String dateStr) throws ParseException {
		return parse(dateStr, DateUtilConst.FMT_24H_ISO_HHMM);
	}

	public static final Date parse24HyyMMddHHmmss(String dateStr) throws ParseException {
		return parse(dateStr, DateUtilConst.FMT_24H_YYMMDDHHMMSS);
	}

}
