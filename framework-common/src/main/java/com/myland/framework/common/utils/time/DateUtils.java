package com.myland.framework.common.utils.time;

import org.apache.commons.lang3.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 日期处理工具类，主要提供日期对象相关的计算等操作
 */
public class DateUtils {

	/**
	 * 对传入的日期对象，在指定的日期字段上添加或减少相应的日期数。
	 *
	 * @param date         要进行计算的日期对象，在该日期基础之上进行添加或减少指定的时间段
	 * @param dateField    参考 Calendar的各时间段常量定义，或者下述 see标签的描述
	 * @param intervalTime 要添加或减少的时间段数量（正数为添加，负数为减少）
	 * @return 进行计算后的新日期对象
	 * @throws IllegalArgumentException 参数date为空时抛出该异常
	 * @see Calendar#YEAR
	 * @see Calendar#MONTH
	 * @see Calendar#DAY_OF_MONTH
	 * @see Calendar#HOUR
	 * @see Calendar#MINUTE
	 * @see Calendar#SECOND
	 */
	public static final Date addDate(Date date, int dateField, int intervalTime) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(dateField, intervalTime);
		return cal.getTime();
	}

	/**
	 * 对传入的日期对象，进行年份字段的加减操作。
	 *
	 * @param date         在该日期基础之上进行计算
	 * @param intervalTime 要添加或减少的年数
	 * @return 计算后的新日期对象
	 * @see #addDate(Date, int, int)
	 */
	public static final Date addYear(Date date, int intervalTime) {
		return addDate(date, Calendar.YEAR, intervalTime);
	}

	/**
	 * 对传入的日期对象，进行月份字段的加减操作。
	 *
	 * @param date         在该日期基础之上进行计算
	 * @param intervalTime 要添加或减少的月数
	 * @return 计算后的新日期对象
	 * @see #addDate(Date, int, int)
	 */
	public static final Date addMonth(Date date, int intervalTime) {
		return addDate(date, Calendar.MONTH, intervalTime);
	}

	/**
	 * 对传入的日期对象，进行日期字段的加减操作。
	 *
	 * @param date         在该日期基础之上进行计算
	 * @param intervalTime 要添加或减少的天数
	 * @return 计算后的新日期对象
	 * @see #addDate(Date, int, int)
	 */
	public static final Date addDay(Date date, int intervalTime) {
		return addDate(date, Calendar.DAY_OF_MONTH, intervalTime);
	}

	/**
	 * 对传入的日期对象，进行小时字段的加减操作。
	 *
	 * @param date         在该日期基础之上进行计算
	 * @param intervalTime 要添加或减少的小时数
	 * @return 计算后的新日期对象
	 * @see #addDate(Date, int, int)
	 */
	public static final Date addHour(Date date, int intervalTime) {
		return addDate(date, Calendar.HOUR, intervalTime);
	}

	/**
	 * 对传入的日期对象，进行分钟字段的加减操作。
	 *
	 * @param date         在该日期基础之上进行计算
	 * @param intervalTime 要添加或减少的分钟数
	 * @return 计算后的新日期对象
	 * @see #addDate(Date, int, int)
	 */
	public static final Date addMinute(Date date, int intervalTime) {
		return addDate(date, Calendar.MINUTE, intervalTime);
	}

	/**
	 * 对传入的日期对象，进行秒字段的加减操作。
	 *
	 * @param date         在该日期基础之上进行计算
	 * @param intervalTime 要添加或减少的秒数
	 * @return 计算后的新日期对象
	 * @see #addDate(Date, int, int)
	 */
	public static final Date addSecond(Date date, int intervalTime) {
		return addDate(date, Calendar.SECOND, intervalTime);
	}

	/**
	 * 重置日期对象的年月日
	 *
	 * @param initDate 原日期对象
	 * @param year     要重置成的年份
	 * @param month    要重置成的月份
	 * @param date     要重置成的日
	 * @return 重置后的日期
	 * @throws IllegalArgumentException 参数initDate为空时抛出该异常
	 */
	public static final Date resetDate(Date initDate, int year, int month, int date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(initDate);
		cal.set(year, month - 1, date);
		return cal.getTime();
	}

	/**
	 * 重置日期对象的年月日
	 *
	 * @param initDate  原日期对象
	 * @param dateField 需要重置的日期字段
	 * @param value     新的字段值
	 * @return 重置后的日期
	 * @throws IllegalArgumentException 参数initDate为空时抛出该异常
	 * @see Calendar#YEAR
	 * @see Calendar#MONTH
	 * @see Calendar#DAY_OF_MONTH
	 * @see Calendar#HOUR
	 * @see Calendar#MINUTE
	 * @see Calendar#SECOND
	 */
	public static final Date resetDate(Date initDate, int dateField, int value) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(initDate);

		if (dateField == Calendar.MONTH) {
			value--;
		}

		cal.set(dateField, value);
		return cal.getTime();
	}

	/**
	 * 给定两个日期参数，计算两个日期之间相隔的天数
	 *
	 * @param firDate 日期值
	 * @param secDate 日期值
	 * @return 两个日期之间相差的天数
	 * @throws IllegalArgumentException 参数firDate或secDate为空时抛出该异常
	 */
	public static final int daysBetween(Date firDate, Date secDate) {
		Date truncFirDate = org.apache.commons.lang3.time.DateUtils.truncate(firDate, Calendar.DAY_OF_MONTH);
		Date truncSecDate = org.apache.commons.lang3.time.DateUtils.truncate(secDate, Calendar.DAY_OF_MONTH);

		long firTime = truncFirDate.getTime();
		long secTime = truncSecDate.getTime();

		long diff = firTime - secTime;
		if (diff < 0) {
			diff = -diff;
		}

		long daysL = diff / org.apache.commons.lang3.time.DateUtils.MILLIS_PER_DAY;
		int daysI = Integer.parseInt(String.valueOf(daysL));

		return daysI;
	}

	/**
	 * 判断当前日期是否在给定的日期区间
	 */
	public static final boolean betweenDateInterval(Date startDate, Date endDate) {
		Date now = new Date();
		Calendar calStart = Calendar.getInstance();
		Calendar calCurrent = Calendar.getInstance();
		Calendar calEnd = Calendar.getInstance();
		calStart.setTime(startDate);
		calCurrent.setTime(now);
		calEnd.setTime(endDate);
		if (calStart.before(calCurrent) && calEnd.after(calCurrent)) {
			// 在时间段内
			return true;
		}
		return false;
	}

	/**
	 * 两个日期之差
	 * @param target
	 * @return
	 */
	public static String timeDistance(String target) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long senconds = 0;
		try {
			Date date1 = df.parse(target);
			Date date2 = new Date();
			senconds = date2.getTime() - date1.getTime();
		} catch (Exception e) {
			e.printStackTrace();
		}
		long secondes = senconds / 1000;
		String timeString = "";
		if (secondes < 60) {
			timeString = "刚刚"; /* secondes + "秒前"; */
		} else if (secondes < 3600) {
			timeString = secondes / 60 + "分钟前";
		} else if (secondes < 3600 * 24) {
			timeString = secondes / 3600 + "小时" + secondes % 3600 / 60 + "分钟前";
		} else if (secondes < 3600 * 24 * 30) {
			timeString = secondes / 3600 / 24 + "天前";
		} else if (secondes < 3600 * 24 * 365) {
			timeString = secondes / 3600 / 24 / 30 + "个月前";
		} else {
			timeString = secondes / 3600 / 24 / 365 + "年前";
		}
		return timeString;
	}

	/**
	 * 获取最近N个月
	 *
	 * @param n
	 * @return
	 * @author wangdaiwei
	 * @create 2015年3月3日 上午10:15:34
	 */
	public static String[] getLastYearMonths(int n) {
		String[] lastMonths = new String[n];
		Calendar cal = Calendar.getInstance();
		for (int i = 0; i < n; i++) {
			cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) - 1); // 逐次往前推1个月
			lastMonths[n - 1 - i] = cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1);
		}
		return lastMonths;
	}

	/**
	 * 根据年月获取当月的第一天
	 *
	 * @param year
	 * @param month
	 * @return
	 * @author wangdaiwei
	 * @create 2015年3月4日 上午10:19:56
	 */
	public static String getBeginTimeByYearMonth(int year, int month) {
		SimpleDateFormat datef = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.set(year, month - 1, 0);
		cal.set(GregorianCalendar.DAY_OF_MONTH, 1);
		Date beginTime = cal.getTime();
		String beginTimeStr = datef.format(beginTime) + " 00:00:00";
		return beginTimeStr;
	}

	/**
	 * 根据年月获取当月的最后一天
	 *
	 * @param year
	 * @param month
	 * @return
	 * @author wangdaiwei
	 * @create 2015年3月4日 上午10:19:59
	 */
	public static String getEndTimeByYearMonth(int year, int month) {
		SimpleDateFormat datef = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.set(year, month, 0);
		Date endTime = cal.getTime();
		String endTimeStr = datef.format(endTime) + " 23:59:59";
		return endTimeStr;
	}

	/**
	 * @param @param  date
	 * @param @return
	 * @return String
	 * @throws
	 * @Title: getUTCTime
	 * @Description: 获取yyyy-MM-dd'T'HH:mm:ss.SSSXXX日期
	 * @author zhangbin
	 */
	public static String getUTCTime(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
		String time = format.format(date);
		return time;
	}

	/**
	 * @param @param  datetime
	 * @param @return
	 * @param @throws ParseException
	 * @return String
	 * @throws
	 * @Title: getUTCTime
	 * @Description:
	 * @author zhangbin
	 */
	public static String getUTCTime(String datetime) throws ParseException {
		Date date = DateParseUtils.parse24HyyyyMMddHHmmss(datetime);

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
		String time = format.format(date);
		return time;
	}

	/**
	 * @param @param  time
	 * @param @param  i
	 * @param @return
	 * @return Date
	 * @throws
	 * @Title: addOrMinusYear
	 * @Description: 日期时间加减年 i>0 加年 i《0减年
	 * @author zhangbin
	 */
	public static Date addOrMinusYear(Long time, int i) {
		GregorianCalendar cal = new GregorianCalendar();
		Date date = new Date(time);
		cal.setTime(date);
		cal.add(1, i);
		return cal.getTime();
	}

	/**
	 * @param @param  time
	 * @param @param  i
	 * @param @return
	 * @return Date
	 * @throws
	 * @Title: addOrMinusMon
	 * @Description: 当前日期时间 i》0 加月份 i《0 减月份
	 * @author zhangbin
	 */
	public static Date addOrMinusMon(Long time, int i) {
		GregorianCalendar cal = new GregorianCalendar();
		Date date = new Date(time);
		cal.setTime(date);
		cal.add(2, i);
		return cal.getTime();
	}

	/**
	 * @param @param  time
	 * @param @param  i
	 * @param @return
	 * @return Date
	 * @throws
	 * @Title: addOrMinusDate
	 * @Description: 当前日期时间 i》0 加天 i《0 减天
	 * @author zhangbin
	 */
	public static Date addOrMinusDate(Long time, int i) {
		GregorianCalendar cal = new GregorianCalendar();
		Date date = new Date(time);
		cal.setTime(date);
		cal.add(5, i);
		return cal.getTime();
	}

	/**
	 * @param @param  time
	 * @param @param  i
	 * @param @return
	 * @return Date
	 * @throws
	 * @Title: addOrMinusHour
	 * @Description: 当前日期时间 i》0 加小时 i《0 减小时
	 * @author zhangbin
	 */
	public static Date addOrMinusHour(Long time, int i) {
		GregorianCalendar cal = new GregorianCalendar();
		Date date = new Date(time);
		cal.setTime(date);
		cal.add(10, i);
		return cal.getTime();
	}

	/**
	 * @param @param  time
	 * @param @param  i
	 * @param @return
	 * @return Date
	 * @throws
	 * @Title: addOrMinusMinute
	 * @Description: 当前日期时间 i》0 加分 i《0 减分
	 * @author zhangbin
	 */
	public static Date addOrMinusMinute(Long time, int i) {
		GregorianCalendar cal = new GregorianCalendar();
		Date date = new Date(time);
		cal.setTime(date);
		cal.add(12, i);
		return cal.getTime();
	}

	/**
	 * @param time
	 * @param i
	 * @return Date
	 * @throws
	 * @Title: addOrMinusSecond
	 * @Description: 当前日期时间 i》0 加秒 i《0 减秒
	 * @author zhangbin
	 */
	public static Date addOrMinusSecond(Long time, int i) {
		GregorianCalendar cal = new GregorianCalendar();
		Date date = new Date(time);
		cal.setTime(date);
		cal.add(13, i);
		return cal.getTime();
	}

	/**
	 * 计算两个日期之间相差的天数
	 *
	 * @param smdate 开始时间
	 * @param bdate  结束时间
	 * @return 相差天数
	 * @throws ParseException
	 * @author hemimi
	 * @create 2016年2月17日 下午2:38:47
	 */
	public static int daysBetween(String smdate, String bdate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		try {
			cal.setTime(sdf.parse(smdate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		long time1 = cal.getTimeInMillis();
		try {
			cal.setTime(sdf.parse(bdate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		long time2 = cal.getTimeInMillis();
		long betweenDays = (time2 - time1) / (1000 * 3600 * 24);

		return Integer.parseInt(String.valueOf(betweenDays));
	}

	/**
	 * 根据输入的日期获取，改天的下一天
	 *
	 * @param inDate yyyy-MM-dd 格式的日期字符串
	 * @return 下一天
	 * @author hemimi
	 * @create 2016年2月17日 下午3:13:24
	 */
	public static String getNextDay(String inDate) {
		Date d2 = null;
		try {
			d2 = DateParseUtils.parseISOyyyyMMdd(inDate);
		} catch (Exception e) {
			return null;
		}

		// 创建 Calendar 对象
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(d2);
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		return DateFmtUtils.formatIsoYyyyMMdd(calendar.getTime());
	}

	/**
	 * 根据输入的参数t,获取该当前时间的后t天的日期
	 *
	 * @param inDate
	 * @param t
	 * @return
	 */
	public static String getNextsDay(String inDate, int t) {
		Date d2 = null;
		try {
			d2 = DateParseUtils.parseISOyyyyMMdd(inDate);
		} catch (Exception e) {
			return null;
		}

		// 创建 Calendar 对象
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(d2);
		calendar.add(Calendar.DAY_OF_MONTH, t);
		return DateFmtUtils.formatIsoYyyyMMdd(calendar.getTime());
	}

	/**
	 * 比较输入两个字符串日期的大小
	 *
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int compareDate(String date1, String date2) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date dt1 = df.parse(date1);
			Date dt2 = df.parse(date2);
			if (dt1.getTime() > dt2.getTime()) {
				return 1;
			} else if (dt1.getTime() < dt2.getTime()) {
				return -1;
			} else {
				return 0;
			}
		} catch (Exception e) {
		}
		return 0;
	}

	/**
	 * 判断输入的日期是否是周末
	 *
	 * @param inDate
	 * @return 是双休日返回true，否则是false
	 */
	public static boolean isWeekEnd(String inDate) {
		try {
			Date date = DateParseUtils.parseISOyyyyMMdd(inDate);

			Calendar cal = Calendar.getInstance();
			cal.setTime(date);

			int weekDay = cal.get(Calendar.DAY_OF_WEEK);
			if (weekDay == Calendar.SUNDAY || weekDay == Calendar.SATURDAY) {
				return true;
			}
			return false;
		} catch (ParseException e) {
		}

		return false;
	}

	/**
	 * @param startTime       设置开始时间
	 * @param endTime         设置结束时间
	 * @param targetStartTime 实际开始时间
	 * @param targetEndTime   实际结束时间
	 * @return
	 * @throws ParseException boolean
	 */
	public static boolean checkContainTime(String startTime, String endTime, String targetStartTime,
										   String targetEndTime) throws ParseException {

		Date startTimeDate = DateParseUtils.parse24Hhhmm(startTime);
		Date endTimeDate = DateParseUtils.parse24Hhhmm(endTime);

		Date targetStartTimeDate = DateParseUtils.parse24Hhhmm(targetStartTime);
		Date targetEndTimeDate = DateParseUtils.parse24Hhhmm(targetEndTime);

		long s1 = targetStartTimeDate.getTime() - startTimeDate.getTime();
		long s2 = targetStartTimeDate.getTime() - endTimeDate.getTime();
		long s3 = targetEndTimeDate.getTime() - endTimeDate.getTime();

		if (s1 >= 0 && s2 < 0 && s3 <= 0) {
			return true;
		}

		return false;
	}

	/**
	 * 获取时间离着现在时间多少个小时
	 *
	 * @param target
	 */
	public static int getTimeDistanceHours(String target) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long senconds = 0;
		try {
			Date date1 = df.parse(target);
			Date date2 = new Date();
			senconds = date1.getTime() - date2.getTime();
		} catch (Exception e) {
			e.printStackTrace();
		}
		long betweenHours = senconds / (1000 * 3600);
		return Integer.parseInt(String.valueOf(betweenHours));

	}

	/**
	 * 获得上一个月的第一天
	 * getMonthFristDay:(这里用一句话描述这个方法的作用). <br/>
	 */
	public static String getMonthFristDay() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		String firstDay = format.format(cal.getTime());
		return firstDay;
	}

	/**
	 * 获取上个月的最后一天
	 */
	public static String getMonthLastDay() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cale = Calendar.getInstance();
		cale.set(Calendar.DAY_OF_MONTH, 0); // 设置为1号,当前日期既为本月第一天
		String lastDay = format.format(cale.getTime());
		return lastDay;
	}

	public static String getFristMon() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
		Calendar cal = Calendar.getInstance(); // 获取当前日期
		int year = cal.get(Calendar.YEAR);
		cal.set(year, 0, 1);
		String firstDay = format.format(cal.getTime());
		return firstDay;
	}

	public static String getCurrentMon() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, 0);
		String firstDay = format.format(cal.getTime());
		return firstDay;
	}

	/**
	 * 获取上个月的最后一天
	 */
	public static String getFirstMonth() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy");
		Date currTime = new Date();
		String curTime = format.format(currTime);
		curTime += "-01";
		return curTime;
	}

	/**
	 * 获取当前月份
	 */
	public static String getCurrMonth() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
		Date currTime = new Date();
		String curTime = formatter.format(currTime);

		return curTime;
	}

	/**
	 * 根据输入的开始时间结束时间计算对应时间日期坐标(x,y)如(2016-01-01,0)
	 *
	 * @param sdate 开始日期
	 * @param edate 结束日期
	 * @return 所有的工作日期
	 */
	public static Map<String, Integer> getWorkDaysMap(String sdate, String edate) {

		int days = DateUtils.daysBetween(sdate, edate);
		Map<String, Integer> map = new HashMap<>(20);
		map.put(sdate, 0);
		for (int i = 0; i < days; i++) {
			String nextDate = DateUtils.getNextDay(sdate);
			map.put(nextDate, i + 1);
			sdate = nextDate;
		}
		return map;
	}

	/**
	 * 根据输入的开始时间结束时间结算对应日期数组
	 *
	 * @param sdate 开始日期
	 * @param edate 结束日期
	 * @return 所有的工作日期
	 */
	public static List<String> getWorkDaysList(String sdate, String edate) {

		int days = DateUtils.daysBetween(sdate, edate);
		List<String> workDateList = new ArrayList<String>();
		workDateList.add(sdate);
		for (int i = 0; i < days; i++) {
			String nextDate = DateUtils.getNextDay(sdate);
			workDateList.add(nextDate);
			sdate = nextDate;
		}
		return workDateList;
	}

	/**
	 * 根据输入的最小时间和最大时间获取中间整点时间坐标
	 *
	 * @param minTime 最小时间
	 * @param maxTime 最大日期
	 * @return 所有的工作日期
	 * @throws ParseException
	 */
	public static Map<String, Integer> getWorkTimeMap(String minTime, String maxTime) throws ParseException {

		Map<String, Integer> map = new HashMap<>(20);
		SimpleDateFormat sdf = new SimpleDateFormat("HH:ss");
		if (StringUtils.isNotBlank(maxTime) && StringUtils.isNotBlank(minTime)) {
			String min = minTime.split(":")[0];
			long bg = sdf.parse(minTime).getTime();
			long eg = sdf.parse(maxTime).getTime();
			int hourTime = Math.round((eg - bg) / 3600000F);
			int temptime = Integer.parseInt(min);
			String beforeTime = "";
			for (int z = 0; z < hourTime; z++) {
				temptime = temptime + 1;
				String timestr = "";
				if (temptime < 10) {
					timestr = "0" + temptime + ":00";
				} else {
					timestr = temptime + ":00";
				}
				if (z == 0) {
					map.put(minTime + "-" + timestr, z);
					beforeTime = timestr;
				} else {
					map.put(beforeTime + "-" + timestr, z);
					beforeTime = timestr;
				}
			}
		}

		return map;
	}

	/**
	 * 根据输入的最小时间和最大时间获取中间整点时间数组
	 *
	 * @param minTime 最小时间点
	 * @param maxTime 最大时间点
	 * @return 所有的工作日期
	 * @throws Exception
	 */
	public static List<String> getWorkTimeList(String minTime, String maxTime) throws Exception {

		List<String> workTimeList = new ArrayList<String>();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:ss");
		if (StringUtils.isNotBlank(maxTime) && StringUtils.isNotBlank(minTime)) {
			String min = minTime.split(":")[0];
			long bg = sdf.parse(minTime).getTime();
			long eg = sdf.parse(maxTime).getTime();
			int hourTime = Math.round((eg - bg) / 3600000F);
			workTimeList.add(minTime);
			Integer minx = Integer.parseInt(min);
			int temptime = minx;
			for (int z = 0; z < hourTime; z++) {
				temptime = temptime + 1;
				String timestr = "";
				if (temptime < 10) {
					timestr = "0" + temptime + ":00";
				} else {
					timestr = temptime + ":00";
				}
				workTimeList.add(timestr);
			}
		}
		return workTimeList;
	}
}
