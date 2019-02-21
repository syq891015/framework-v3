package com.myland.framework.common.utils.time;

import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.Locale;

/**
 * 日期格式化工具封装类，基于common_lang3包里提供的API进行日期格式化数据封装
 * 该工具类里的方法用来将日期对象按指定格式转换成相应的字符串形式。
 *
 * @author SunYanQing
 */
public final class DateFmtUtils {

	public static final String BAR = "-";

	public static final int THREE = 3;

	public static final int TWO = 2;

	private DateFmtUtils() {

	}

	/**
	 * 根据指定的格式化规则，将指定的日期对象格式化成相应的字符串形式。
	 *
	 * @param targetDate 要格式化的日期对象
	 * @param pattern    格式
	 * @return 格式化后的字符串形式
	 * @throws IllegalArgumentException 参数targetDate或pattern为空时可能抛出的异常
	 */
	public static String getFormatValueByPattern(Date targetDate, String pattern) {
		return org.apache.commons.lang3.time.DateFormatUtils.format(targetDate, pattern);
	}

	/**
	 * 根据指定的格式化规则，将指定的日期对象格式化成相应的字符串形式。
	 *
	 * @param targetDate 要格式化的日期对象
	 * @param pattern    格式
	 * @param local      表示了特定的地理、政治和文化地区。
	 * @return 格式化后的字符串形式
	 * @throws IllegalArgumentException 参数targetDate、pattern或local为空时可能抛出的异常
	 */
	public static String getFormatValueByPattern(Date targetDate, String pattern, Locale local) {
		return org.apache.commons.lang3.time.DateFormatUtils.format(targetDate, pattern, local);
	}

	/**
	 * 从给定的日期对象中获取4位数表示的年份值
	 *
	 * @param targetDate 要处理的日期对象
	 * @return 日期对象中对应的年份值，形如"2014"
	 * @throws IllegalArgumentException 参数targetDate为空时可能抛出的异常
	 */
	public static String formatYear(Date targetDate) {
		return getFormatValueByPattern(targetDate, DateUtilConst.FMT_YYYY);
	}

	/**
	 * 从给定的日期对象中获取2位数表示的月份值
	 *
	 * @param targetDate 要处理的日期对象
	 * @return 日期对象中对应的两位数月份值，形如"08"
	 * @throws IllegalArgumentException 参数targetDate为空时可能抛出的异常
	 */
	public static String formatMonth(Date targetDate) {
		return getFormatValueByPattern(targetDate, DateUtilConst.FMT_MM);
	}

	/**
	 * 从给定的日期对象中获取2位数表示的日期值
	 *
	 * @param targetDate 要处理的日期对象
	 * @return 日期对象中对应的两位数日期值，形如"29"
	 * @throws IllegalArgumentException 参数targetDate为空时可能抛出的异常
	 */
	public static String formatDay(Date targetDate) {
		return getFormatValueByPattern(targetDate, DateUtilConst.FMT_DD);
	}

	/**
	 * 日期格式化(MMdd)成字符串，取月日
	 *
	 * @param targetDate 要处理的日期对象
	 * @return 格式化后的字符串，形如"0825"
	 * @throws IllegalArgumentException 参数targetDate为空时可能抛出的异常
	 */
	public static String formatMMdd(Date targetDate) {
		return getFormatValueByPattern(targetDate, DateUtilConst.FMT_MMDD);
	}

	/**
	 * 日期格式化(yyyy年MM月)成字符串
	 *
	 * @param targetDate 要处理的日期对象
	 * @return 格式化后的字符串，形如"2014年08月"
	 * @throws IllegalArgumentException 参数targetDate为空时可能抛出的异常
	 */
	public static String formatCnYyyyMM(Date targetDate) {
		return getFormatValueByPattern(targetDate, DateUtilConst.FMT_CN_YYYYMM);
	}

	/**
	 * 日期格式化(yyyyMM)成字符串
	 *
	 * @param targetDate 要处理的日期对象
	 * @return 格式化后的字符串，形如"201408"
	 * @throws IllegalArgumentException 参数targetDate为空时可能抛出的异常
	 */
	public static String formatYyyyMM(Date targetDate) {
		return getFormatValueByPattern(targetDate, DateUtilConst.FMT_YYYYMM);
	}

	/**
	 * 日期格式化(yyyyMMdd)成字符串,取年月日。
	 *
	 * @param targetDate 要处理的日期对象
	 * @return 格式化后的字符串，"20140825"
	 * @throws IllegalArgumentException 参数targetDate为空时可能抛出的异常
	 */
	public static String formatYyyyMMdd(Date targetDate) {
		return getFormatValueByPattern(targetDate, DateUtilConst.FMT_YYYYMMDD);
	}

	/**
	 * 日期格式化(yyyy-MM-dd)成字符串,取年月日。
	 *
	 * @param targetDate 要处理的日期对象
	 * @return 格式化后的字符串，形如"2014-08-25"
	 * @throws IllegalArgumentException 参数targetDate为空时可能抛出的异常
	 */
	public static String formatIsoYyyyMMdd(Date targetDate) {
		return getFormatValueByPattern(targetDate, DateUtilConst.FMT_ISO_YYYYMMDD);
	}

	/**
	 * 日期格式化(yyyy年MM月dd日)成字符串
	 *
	 * @param targetDate 要处理的日期对象
	 * @return 格式化后的字符串，形如"2014年08月25日"
	 * @throws IllegalArgumentException 参数targetDate为空时可能抛出的异常
	 */
	public static String formatCnYyyyMMdd(Date targetDate) {
		return getFormatValueByPattern(targetDate, DateUtilConst.FMT_CN_YYYYMMDD);
	}

	/**
	 * 日期格式化(yyyyMMddhhmmss)成字符串, 取年月日时分秒，12小时制表示。
	 *
	 * @param targetDate 要处理的日期对象
	 * @return 形如"20140825013509"
	 * @throws IllegalArgumentException 参数targetDate为空时可能抛出的异常
	 */
	public static String format12HYyyyMMddhhmmss(Date targetDate) {
		return getFormatValueByPattern(targetDate, DateUtilConst.FMT_12H_YYYYMMDDHHMMSS);
	}

	/**
	 * 日期格式化(yyyyMMddhhmmssSSS)成字符串, 取年月日时分秒毫秒，12小时制表示。
	 *
	 * @param targetDate 要处理的日期对象
	 * @return 形如"20140825013509345"
	 * @throws IllegalArgumentException 参数targetDate为空时可能抛出的异常
	 * @author chenem
	 * @create 2015年5月15日  下午3:15:23
	 */
	public static String format12HYyyyMMddhhmmssSSS(Date targetDate) {
		return getFormatValueByPattern(targetDate, DateUtilConst.FMT_12H_YYYYMMDDHHMMSSSSS);
	}

	/**
	 * 日期格式化(yyyyMMddHHmmss)成字符串, 取年月日时分秒，24小时制表示。
	 *
	 * @param targetDate 要处理的日期对象
	 * @return 格式化后的字符串，形如"20140825013509"
	 * @throws IllegalArgumentException 参数targetDate为空时可能抛出的异常
	 */
	public static String format24HYyyyMMddHHmmss(Date targetDate) {
		return getFormatValueByPattern(targetDate, DateUtilConst.FMT_24H_YYYYMMDDHHMMSS);
	}

	/**
	 * 日期格式化(yyyyMMddHHmmss)成字符串, 取年月日时分秒，24小时制表示。
	 *
	 * @param targetDate 要处理的日期对象
	 * @return 格式化后的字符串，形如"2014-08-25 01:35:09"
	 * @throws IllegalArgumentException 参数targetDate为空时可能抛出的异常
	 */
	public static String format24HyyyyMMddHHmmss(Date targetDate) {
		return getFormatValueByPattern(targetDate, DateUtilConst.FMT_24H_ISO_YYYYMMDDHHMMSS);
	}

	/**
	 * @param targetDate 要处理的日期对象
	 * @return 格式化后的字符串，形如"20140825013509345"
	 * @throws IllegalArgumentException 参数targetDate为空时可能抛出的异常
	 * @author chenem
	 * @create 2015年5月15日  下午3:11:23
	 */
	public static String format24HYyyyMMddHHmmssSSS(Date targetDate) {
		return getFormatValueByPattern(targetDate, DateUtilConst.FMT_24H_YYYYMMDDHHMMSSSSS);
	}

	/**
	 * 日期格式化(yyyy-MM-dd hh:mm:ss)成字符串。取年月日时分秒，12小时制表示。
	 *
	 * @param targetDate 要处理的日期对象
	 * @return 格式化后的字符串，形如"2014-08-25 01:55:26"
	 * @throws IllegalArgumentException 参数targetDate为空时可能抛出的异常
	 */
	public static String format12HIsoYyyyMMddhhmmss(Date targetDate) {
		return getFormatValueByPattern(targetDate, DateUtilConst.FMT_12H_ISO_YYYYMMDDHHMMSS);
	}

	/**
	 * 日期格式化(yyyy-MM-dd HH:mm:ss)成字符串, 取年月日时分秒，24小时制表示。
	 *
	 * @param targetDate 要处理的日期对象
	 * @return 格式化后的字符串，形如"2014-08-25 13:37:45"
	 * @throws IllegalArgumentException 参数targetDate为空时可能抛出的异常
	 */
	public static String format24HIsoYyyyMMddHHmmss(Date targetDate) {
		return getFormatValueByPattern(targetDate, DateUtilConst.FMT_24H_ISO_YYYYMMDDHHMMSS);
	}

	/**
	 * 日期格式化(yyyy年MM月dd日 hh时mm分ss秒)成字符串，取年月日时分秒，12小时制表示。
	 *
	 * @param targetDate 要处理的日期对象
	 * @return 形如"2014年08月25日 01时57分43秒"
	 * @throws IllegalArgumentException 参数targetDate为空时可能抛出的异常
	 */
	public static String format12HCnYyyyMMddhhmmss(Date targetDate) {
		return getFormatValueByPattern(targetDate, DateUtilConst.FMT_12H_CN_YYYYMMDDHHMMSS);
	}

	/**
	 * 日期格式化(yyyy年MM月dd日 HH时mm分ss秒)成字符串, 24小时制取年月日时分秒。
	 *
	 * @param targetDate 要处理的日期对象
	 * @return 24小时制表示。形如"2014年08月25日 21时57分43秒"
	 */
	public static String format24HCnYyyyMMddHHmmss(Date targetDate) {
		return getFormatValueByPattern(targetDate, DateUtilConst.FMT_24H_CN_YYYYMMDDHHMMSS);
	}

	/**
	 * yyyy年M月d日 E >> 取当前年月日星期，星期按本地化格式获取
	 *
	 * @param targetDate 要处理的日期对象
	 * @return 形如"2014年8月25日 星期一"
	 * @throws IllegalArgumentException 参数targetDate为空时可能抛出的异常
	 */
	public static String formatCnYyyyMdE(Date targetDate) {
		return getFormatValueByPattern(targetDate, DateUtilConst.FMT_CN_YYYYMDE, Locale.CHINESE);
	}

	/**
	 * 日期格式化(MM月dd日 hh时mm分)成字符串,取月日时分,12小时制
	 *
	 * @param targetDate 要处理的日期对象
	 * @return 形如"08月25日 02时01分"
	 * @throws IllegalArgumentException 参数targetDate为空时可能抛出的异常
	 */
	public static String format12HCnMMddhhmm(Date targetDate) {
		return getFormatValueByPattern(targetDate, DateUtilConst.FMT_12H_CN_MMDDHHMM);
	}

	/**
	 * 日期格式化(yyyy-MM-dd hh:mm)成字符串，取年月日时分，12小时制。
	 *
	 * @param targetDate 要处理的日期对象
	 * @return 形如"2014-08-25 02:05"
	 * @throws IllegalArgumentException 参数targetDate为空时可能抛出的异常
	 */
	public static String format12HYyyyMMddhhmm(Date targetDate) {
		return getFormatValueByPattern(targetDate, DateUtilConst.FMT_12H_YYYYMMDDHHMM);
	}

	/**
	 * 日期格式化(yyyy年MM月dd日 hh时mm分)成字符串，取年月日时分，12小时制。
	 *
	 * @param targetDate 要处理的日期对象
	 * @return 形如"2014年08月29日 04时52分"
	 * @throws IllegalArgumentException 参数targetDate为空时可能抛出的异常
	 */
	public static String format12HCnYyyyMMddhhmm(Date targetDate) {
		return getFormatValueByPattern(targetDate, DateUtilConst.FMT_12H_CN_YYYYMMDDHHMM);
	}

	/**
	 * 日期格式化(yyyy年MM月dd日 HH时mm分)成字符串，取年月日时分，24小时制。
	 *
	 * @param targetDate 要处理的日期对象
	 * @return 形如"2014年08月25日 14时07分"
	 * @throws IllegalArgumentException 参数targetDate为空时可能抛出的异常
	 */
	public static String format24HCnYyyyMMddHHmm(Date targetDate) {
		return getFormatValueByPattern(targetDate, DateUtilConst.FMT_24H_CN_YYYYMMDDHHMM);
	}

	public static String format24HHmmss(Date targetDate) {
		return getFormatValueByPattern(targetDate, DateUtilConst.FMT_24H_ISO_HHMMSS);
	}

	public static String currentTime() {
		return format24HHmmss(new Date());
	}

	/**
	 * 当前日期格式化(yyyy)成字符串,取年。
	 *
	 * @return 当前年份字符 串形式，如"2014"
	 */
	public static String currentYear() {
		return formatYear(new Date());
	}

	/**
	 * 当前日期格式化(MM)成字符串,取两位数字形式的月份。
	 *
	 * @return 当前月份字符 串形式，如"08"
	 */
	public static String currentMonth() {
		return formatMonth(new Date());
	}

	/**
	 * 当前日期格式化(dd)成字符串,取两位数字形式的日期。
	 *
	 * @return 当前日期字符 串形式，如"29"
	 */
	public static String currentDay() {
		return formatDay(new Date());
	}

	/**
	 * 当前日期格式化(yyyyMMdd)成字符串,取年月日。
	 *
	 * @return 当前日期格式化后的字符串，形如"20140825"
	 */
	public static String currentYyyyMMdd() {
		return formatYyyyMMdd(new Date());
	}

	/**
	 * 当前日期格式化(yyyy-MM-dd)成字符串,取年月日。
	 *
	 * @return 当前日期格式化后的字符串，形如"2014-08-29"
	 */
	public static String currentIsoYyyyMMdd() {
		return formatIsoYyyyMMdd(new Date());
	}

	/**
	 * 当前日期格式化(yyyy年MM月dd日)成字符串,取年月日。
	 *
	 * @return 当前日期格式化后的字符串，形如"2014年08月29日"
	 */
	public static String currentCnYyyyMMdd() {
		return formatCnYyyyMMdd(new Date());
	}

	/**
	 * 当前日期格式化(yyyy-MM-dd HH:mm:ss)成字符串,取年月日时分秒。
	 *
	 * @return 24小时制表示。形如"2014-08-25 13:37:45"
	 */
	public static String current24HIsoYyyyMMddHHmmss() {
		return format24HIsoYyyyMMddHHmmss(new Date());
	}

	/**
	 * 当前日期格式化(yyyy年MM月dd日 HH时mm分ss秒)成字符串, 24小时制取年月日时分秒。
	 *
	 * @return 24小时制表示。形如"2014年08月25日 21时57分43秒"
	 */
	public static String current24HCnYyyyMMddHHmmss() {
		return format24HCnYyyyMMddHHmmss(new Date());
	}

	/**
	 * 对日期格式进行转换 由2014-1-9转换为2014-01-09 或者是2014-1转换为2014-01
	 *
	 * @param date 日期字符串
	 * @return 格式化后的日期字符串
	 */
	public static String format(String date) {
		if (date.contains(BAR)) {
			String[] dateArrs = date.split(BAR);
			// 如果是2014-1-9形式的做如下处理
			if (dateArrs.length == THREE) {
				String year = dateArrs[0];
				String month = dateArrs[1];
				String day = dateArrs[2];
				if (month.length() == 1) {
					month = "0" + month;
				}
				if (day.length() == 1) {
					day = "0" + day;
				}
				return year + "-" + month + "-" + day;
			} else if (dateArrs.length == TWO) {
				// 如果是2014-1形式的做如下处理
				String year = dateArrs[0];
				String month = dateArrs[1];
				if (month.length() == 1) {
					month = "0" + month;
				}
				return year + "-" + month;
			}
		}
		return date;
	}

	/**
	 * <pre>
	 * 去掉日期间的分隔符，转化为yyyymmdd
	 * 版本修改历史记录：
	 * 1) 1.0.0 2017年2月15日 上午10:36:40 hemimi created
	 * </pre>
	 *
	 * @param targetDate 要格式化的日期
	 * @param separator  分隔符
	 * @return
	 */
	public static String formatYyyyMmDd(String targetDate, String separator) {
		if (StringUtils.isNotBlank(targetDate)) {
			targetDate = targetDate.replace(separator, "");
		}
		return targetDate;
	}

	/**
	 * <pre>
	 * 加上日期间的分隔符，转化为yyyy-mm-dd
	 * </pre>
	 *
	 * @param targetDate
	 * @param separator
	 * @return
	 */
	public static String formatYyyyMmDdSeprator(String targetDate, String separator) {
		StringBuilder s = new StringBuilder(targetDate);
		s.insert(4, separator);
		s.insert(7, separator);
		return s.toString();
	}
}
