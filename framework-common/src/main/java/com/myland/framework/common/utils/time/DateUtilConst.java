package com.myland.framework.common.utils.time;

/**
 * 日期处理工具类相关的一些常量定义
 *
 * @author Changshuo.Feng
 * @create 2014年8月29日 下午4:05:24
 */
public class DateUtilConst {

	/**
	 * yyyy-MM-dd >> 取年月日，形如"2014-08-25"
	 */
	public static final String FMT_ISO_YYYYMMDD = "yyyy-MM-dd";

	/**
	 * yyyy-MM-dd HH:mm:ss >> 取年月日时分秒，24小时制表示。形如"2014-08-25 13:37:45"
	 */
	public static final String FMT_24H_ISO_YYYYMMDDHHMMSS = "yyyy-MM-dd HH:mm:ss";

	/**
	 * yyyy ：取4四位数表示的年
	 */
	public static final String FMT_YYYY = "yyyy";

	/**
	 * MM : 取两位数表示的月
	 */
	public static final String FMT_MM = "MM";

	/**
	 * dd ：取两位数表示的日
	 */
	public static final String FMT_DD = "dd";

	/**
	 * MMdd >> 取月日，形如"0825"
	 */
	public static final String FMT_MMDD = "MMdd";

	/**
	 * yyyy年MM月 >> 取年月，形如"2014年08月"
	 */
	public static final String FMT_CN_YYYYMM = "yyyy年MM月";

	/**
	 * yyyyMM >> 取年月，形如"201408"
	 */
	public static final String FMT_YYYYMM = "yyyyMM";

	/**
	 * yyyyMMdd ：取年月日，形如"20140825"
	 */
	public static final String FMT_YYYYMMDD = "yyyyMMdd";


	/**
	 * yyyy年MM月dd日 >> 取年月日格式化，形如"2014年08月25日"
	 */
	public static final String FMT_CN_YYYYMMDD = "yyyy年MM月dd日";

	/**
	 * yyyyMMddhhmmss >> 取年月日时分秒，12小时制表示。形如"20140825013509"
	 */
	public static final String FMT_12H_YYYYMMDDHHMMSS = "yyyyMMddhhmmss";

	/**
	 * yyyyMMddhhmmssssss >> 取年月日时分秒毫秒，12小时制表示。形如"201408250135090235"
	 */
	public static final String FMT_12H_YYYYMMDDHHMMSSSSS = "yyyyMMddhhmmssSSS";

	/**
	 * yyyyMMddHHmmss >> 取年月日时分秒，24小时制表示。形如"20140825013509"
	 */
	public static final String FMT_24H_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

	/**
	 * yyyyMMddHHmmss >> 取年月日时分秒毫秒，24小时制表示。形如"201408250135091235"
	 */
	public static final String FMT_24H_YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmssSSS";

	/**
	 * yyyy-MM-dd hh:mm:ss >> 取年月日时分秒，12小时制表示。形如"2014-08-25 01:55:26"
	 */
	public static final String FMT_12H_ISO_YYYYMMDDHHMMSS = "yyyy-MM-dd hh:mm:ss";

	/**
	 * yyyy年MM月dd日 hh时mm分ss秒 >> 取年月日时分秒，12小时制表示。形如"2014年08月25日 01时57分43秒"
	 */
	public static final String FMT_12H_CN_YYYYMMDDHHMMSS = "yyyy年MM月dd日 hh时mm分ss秒";

	/**
	 * yyyy年MM月dd日 hh时mm分ss秒 >> 取年月日时分秒，24小时制表示。形如"2014年08月25日 21时57分43秒"
	 */
	public static final String FMT_24H_CN_YYYYMMDDHHMMSS = "yyyy年MM月dd日 HH时mm分ss秒";

	/**
	 * hh时mm分ss秒 >> 取年月日时分秒，24小时制表示。形如"21时57分43秒"
	 */
	public static final String FMT_24H_ISO_HHMMSS = "HHmmss";

	/**
	 * HH:mm
	 */
	public static final String FMT_24H_ISO_HHMM = "HH:mm";

	/**
	 * yyyy年M月d日 E >> 取年月日星期，星期按本地化格式获取，形如"2014年8月25日 星期一"
	 */
	public static final String FMT_CN_YYYYMDE = "yyyy年M月d日 E";

	/**
	 * MM月dd日 hh时mm分 >> 取月日时分，12小时制表示，形如"08月25日 02时01分"
	 */
	public static final String FMT_12H_CN_MMDDHHMM = "MM月dd日 hh时mm分";

	/**
	 * yyyy-MM-dd hh:mm >> 取年月日时分，12小时制。形如"2014-08-25 02:05"
	 */
	public static final String FMT_12H_YYYYMMDDHHMM = "yyyy-MM-dd hh:mm";

	/**
	 * yyyy年MM月dd日 hh时mm分 >> 取年月日时分，12小时制。形如"2014年08月25日 02时07分"
	 */
	public static final String FMT_12H_CN_YYYYMMDDHHMM = "yyyy年MM月dd日 hh时mm分";

	/**
	 * yyyy年MM月dd日 HH时mm分 >> 取年月日时分，24小时制。形如"2014年08月25日 14时07分"
	 */
	public static final String FMT_24H_CN_YYYYMMDDHHMM = "yyyy年MM月dd日 HH时mm分";

	/**
	 * yyMMddHHmmss >> 取年月日时分秒，24小时制表示。形如"140825013509"
	 */
	public static final String FMT_24H_YYMMDDHHMMSS = "yyMMddHHmmss";

}
