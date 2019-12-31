/**
 * @Title：DateUtil.java 
 * @Package：com.jjb.utils 
 * @Description：
 * @author：Chenlf
 * @date：2015年3月18日 下午5:31:28 
 * @version：V1.0   
 */

package cn.edu.xmut.lgrg.util;

import org.apache.commons.lang.time.DateUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName：DateUtil
 * @Description：
 * @author：Chenlf
 * @date：2015年3月18日 下午5:31:28
 */
public class DateUtil {
	/** 数据库存储的时间格式串，时间数字串 */
	public static final int DB_STORE_DATE_SPLIT = 1;

	/** 数据库存储的时间格式串，如yyyyMM */
	public static final String DB_STORE_DATE_YEAR = "yyyy";

	/** 数据库存储的时间格式串，如yyyyMM */
	public static final String DB_STORE_DATE_MONTH = "yyyyMM";

	/** 数据库存储的时间格式串，如yyyyMMdd */
	public static final String DB_STORE_DATE = "yyyyMMdd";

	/** 用连字符-分隔的时间格式串，如yyyyMMddHH */
	public static final String DB_STORE_DATE_HOUR = "yyyyMMddHH";

	/** 用连字符-分隔的时间格式串，如yyyyMMddHHMi */
	public static final String DB_STORE_DATE_MINUTE = "yyyyMMddHHmm";

	/** 数据库存储的时间格式串，如yyyyMMddHHMiSS */
	public static final String DB_STORE_DATE_FULL = "yyyyMMddHHmmss";

	/** 用连字符-分隔的时间格式串 */
	public static final int LINK_DISPLAY_DATE_SPLIT = 2;

	/** 用连字符-分隔的时间格式串，如yyyy-MM */
	public static final String LINK_DISPLAY_DATE_MONTH = "yyyy-MM";

	/** 用连字符-分隔的时间格式串，如yyyy-MM-dd */
	public static final String LINK_DISPLAY_DATE = "yyyy-MM-dd";

	/** 用连字符-分隔的时间格式串，如yyyy-MM-dd HH */
	public static final String LINK_DISPLAY_DATE_HOUR = "yyyy-MM-dd HH";

	/** 用连字符-分隔的时间格式串，如yyyy-MM-dd HH:Mi */
	public static final String LINK_DISPLAY_DATE_MINUTE = "yyyy-MM-dd HH:mm";

	/** 用连字符-分隔的时间格式串，如yyyy-MM-dd HH:Mi:SS */
	public static final String LINK_DISPLAY_DATE_FULL = "yyyy-MM-dd HH:mm:ss";

	/** 用连字符.分隔的时间格式串 */
	public static final int DOT_DISPLAY_DATE_SPLIT = 3;

	/** 用连字符.分隔的时间格式串，如yyyy.MM */
	public static final String DOT_DISPLAY_DATE_MONTH = "yyyy.MM";

	/** 用连字符.分隔的时间格式串，如yyyy.MM.dd */
	public static final String DOT_DISPLAY_DATE = "yyyy.MM.dd";

	/** 用连字符.分隔的时间格式串，如yyyy.MM.dd HH:Mi:SS */
	public static final String DOT_DISPLAY_DATE_FULL = "yyyy.MM.dd HH:mm:ss";

	/** 用中文字符分隔的时间格式串 */
	public static final int CN_DISPLAY_DATE_SPLIT = 4;

	/** 用中文字符分隔的时间格式串，如yyyy年MM月 */
	public static final String CN_DISPLAY_DATE_MONTH = "yyyy年MM月";

	/** 用中文字符分隔的时间格式串，如yyyy年MM月dd日 */
	public static final String CN_DISPLAY_DATE = "yyyy年MM月dd日";

	/** 用中文字符分隔的时间格式串，如yyyy年MM月dd日 HH:Mi:SS */
	public static final String CN_DISPLAY_DATE_FULL = "yyyy年MM月dd日 HH:mm:ss";

	/** 用/字符分隔的时间格式串 */
	public static final int SLASH_DISPLAY_DATE_SPLIT = 5;

	/** 用/字符分隔的时间格式串，如yyyy/MM */
	public static final String SLASH_DISPLAY_DATE_MONTH = "yyyy/MM";

	/** 用/字符分隔的时间格式串，如yyyy/MM/dd */
	public static final String SLASH_DISPLAY_DATE = "yyyy/MM/dd";

	/** 用/字符分隔的时间格式串，如yyyy/MM/dd HH:Mi:SS */
	public static final String SLASH_DISPLAY_DATE_FULL = "yyyy/MM/dd HH:mm:ss";

	public static final Map<String, SimpleDateFormat> SIMPLE_DATE_FORMAT = new HashMap<String, SimpleDateFormat>();

	static {
		Field[] fields = DateUtil.class.getDeclaredFields();
		for (Field field : fields) {
			int modify = field.getModifiers();
			if (!Modifier.isStatic(modify) || !Modifier.isFinal(modify)
					|| !field.getType().equals(String.class)) {
				continue;
			}
			try {
				String fieldValue = (String) field.get(field.getName());
				if (!fieldValue.startsWith("yyyy")) {
					continue;
				}
				SimpleDateFormat format = new SimpleDateFormat(fieldValue);
				SIMPLE_DATE_FORMAT.put(fieldValue, format);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 时间字符格式化检验
	 * 
	 * @param dateStr
	 * @return
	 * @author:chenxy
	 */
	private static boolean checkDateStr(String dateStr) {
		if (dateStr != null) {
			String dateStrCheck = dateStr.replaceAll("[^0-9]", "");
			if (dateStrCheck.length() >= 4 && dateStrCheck.length() <= 14
					&& dateStrCheck.length() % 2 == 0) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 将日期格式串(数字字符串)转换为各种显示的格式
	 * 
	 * @param dateStr
	 *            最小6位，最大14位的数据库存储格式时间串如:20041212
	 * @param formatType
	 *            时间格式的类型
	 * @return 格式化的时间串
	 */
	private static String getDateFormatType(String dateStr) {
		dateStr = dateStr.replaceAll("[^0-9]", "");
		switch (dateStr.length()) {
		case 4:
			return DB_STORE_DATE_YEAR;
		case 6:
			return DB_STORE_DATE_MONTH;
		case 8:
			return DB_STORE_DATE;
		case 10:
			return DB_STORE_DATE_HOUR;
		case 12:
			return DB_STORE_DATE_MINUTE;
		default:
			return DB_STORE_DATE_FULL;
		}
	}

	/** 获取当前时间 */
	public static Date getCurrentDate() {
		return new Date();
	}

	/** 获取当前日期字符串 yyyyMM */
	public static String getCurrentMonthStr() {
		Date date = new Date();
		return changeDateToStr(date, DB_STORE_DATE_MONTH);
	}

	/** 获取当前日期字符串 yyyy年MM月 */
	public static String getCurrentCNMonthStr() {
		Date date = new Date();
		return changeDateToStr(date, CN_DISPLAY_DATE_MONTH);
	}

	/** 获取当前日期字符串 yyyyMMdd */
	public static String getCurrentDateStr() {
		Date date = new Date();
		return changeDateToStr(date, DB_STORE_DATE);
	}

	/** 获取当前日期字符串 yyyy年MM月dd日 */
	public static String getCurrentCNDateStr() {
		Date date = new Date();
		return changeDateToStr(date, CN_DISPLAY_DATE);
	}

	/** 获取当前日期字符串 yyyy-MM-dd */
	public static String getCurrentLinkDateStr() {
		Date date = new Date();
		return changeDateToStr(date, LINK_DISPLAY_DATE);
	}

	/** 获取当前日期+时间字符串 yyyyMMddHHmmss */
	public static String getCurrentTimeStr() {
		Date date = new Date();
		return changeDateToStr(date, DB_STORE_DATE_FULL);
	}

	/** 获取当前日期+时间字符串 yyyy年MM月dd日 HH:Mi:SS */
	public static String getCurrentCNTimeStr() {
		Date date = new Date();
		return changeDateToStr(date, CN_DISPLAY_DATE_FULL);
	}

	/** 获取当前日期+时间字符串 YYYY-MM-DD HH:MI:SS */
	public static String getCurrentLinkTimeStr() {
		Date date = new Date();
		return changeDateToStr(date, LINK_DISPLAY_DATE_FULL);
	}

	/**
	 * 得到格式化时间串
	 * 
	 * @param date
	 *            指定时间
	 * @param formatType
	 *            时间格式的类型
	 * @return 指定时间的格式化时间串
	 */
	public static String changeDateToStr(Date date, String formatType) {
		if (date == null) {
			return null;
		}
		SimpleDateFormat fomate = SIMPLE_DATE_FORMAT.get(formatType);
		return fomate.format(date);
	}

	/**
	 * 转化Date类型
	 * 
	 * @param dateStr
	 * @return
	 * @author:chenxy
	 */
	public static Date changeStrToDate(String dateStr) {
		if (!checkDateStr(dateStr)) {
			return null;
		}
		dateStr = dateStr.replaceAll("[^0-9]", "");
		SimpleDateFormat fomateDate = SIMPLE_DATE_FORMAT
				.get(getDateFormatType(dateStr));
		try {
			return fomateDate.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 转化Date类型
	 * 
	 * @param dateStr
	 * @return
	 * @author:chenxy
	 */
	public static Date changeStrToDate(String dateStr, String formatType) {
		if (!checkDateStr(dateStr)) {
			return null;
		}
		try {
			SimpleDateFormat formatDate = SIMPLE_DATE_FORMAT.get(formatType);
			return formatDate.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 根据格式化类型将时间类型的数据格式化，并以字符串形式返回。
	 * 
	 * @param object
	 * @param formatType
	 * @return
	 * @author:chenxy
	 */
	public static String changeObj2DateStr(Object object, String formatType) {
		if (object == null) {
			return null;
		}
		String resultDateStr = null;
		if (object instanceof Date) {
			resultDateStr = changeDateToStr((Date) object, formatType);
		} else if (object instanceof String) {
			String dateStr = (String) object;
			dateStr = dateStr.replaceAll("[^0-9]", "");
			try {
				SimpleDateFormat formatDate = SIMPLE_DATE_FORMAT
						.get(getDateFormatType(dateStr));
				resultDateStr = changeDateToStr(formatDate.parse(dateStr),
						formatType);
			} catch (ParseException e) {
				resultDateStr = dateStr;
			}
		} else if (object instanceof Integer) {
			String dateStr = ((Integer) object).toString();
			try {
				SimpleDateFormat formatDate = SIMPLE_DATE_FORMAT
						.get(getDateFormatType(dateStr));
				resultDateStr = changeDateToStr(formatDate.parse(dateStr),
						formatType);
			} catch (ParseException e) {
				resultDateStr = dateStr;
			}
		}
		return resultDateStr;
	}

	/**
	 * 根据格式化类型将时间类型的数据格式化。
	 * 
	 * @param object
	 * @param formatType
	 * @return
	 * @author:chenxy
	 */
	public static Date changeObj2Date(Object object) {
		if (object == null) {
			return null;
		}
		Date resultDate = null;
		if (object instanceof Date) {
			return resultDate;
		} else if (object instanceof String) {
			String dateStr = (String) object;
			dateStr = dateStr.replaceAll("[^0-9]", "");
			try {
				SimpleDateFormat formatDate = SIMPLE_DATE_FORMAT
						.get(getDateFormatType(dateStr));
				resultDate = formatDate.parse(dateStr);
			} catch (ParseException e) {
				return null;
			}
		} else if (object instanceof Integer) {
			String dateStr = ((Integer) object).toString();
			try {
				SimpleDateFormat formatDate = SIMPLE_DATE_FORMAT
						.get(getDateFormatType(dateStr));
				resultDate = formatDate.parse(dateStr);
			} catch (ParseException e) {
				return null;
			}
		}
		return resultDate;
	}

	/**
	 * 对日期时间对象进行调整
	 * 
	 * @param date
	 * @param CALENDARFIELD
	 *            <pre>
	 * 年 Calendar.YEAR
	 * 月 Calendar.MONTH
	 * 日 Calendar.DATE
	 * 时 Calendar.HOUR
	 * 分 Calendar.MINUTE
	 * 秒 Calendar.SECOND
	 * </pre>
	 * @param amount
	 *            调整数量，>0表向后调整（明天，明年），<0表向前调整（昨天，去年）
	 * @return
	 * @author:chenxy
	 */
	public static Date transpositionDate(Date date, int CALENDARFIELD,
			int amount) {
		if (null == date) {
			return date;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(CALENDARFIELD, amount);
		return calendar.getTime();
	}

	public static Date setStartDay(Calendar cal) {
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		return cal.getTime();
	}

	public static Date setEndDay(Calendar cal) {
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		return cal.getTime();
	}

	public static Date setStartDay(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return setStartDay(cal);
	}

	public static Date setEndDay(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return setEndDay(cal);
	}

	/**
	 * 获取月份的最后一天
	 * 
	 * @param date
	 * @return
	 * @author:administrator
	 */
	public static Date setLastDayOfMonth(Calendar cal) {
		int value = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		cal.set(Calendar.DAY_OF_MONTH, value);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	/**
	 * 获取年份的最后一天
	 * 
	 * @param date
	 * @return
	 * @author:administrator
	 */
	public static Date setLastDayOfYear(Calendar cal) {
		cal.set(Calendar.MONTH, 11);
		cal.set(Calendar.DAY_OF_MONTH, 31);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	/**
	 * 获取月份的第一天
	 * 
	 * @param date
	 * @return
	 * @author:administrator
	 */
	public static Date setBeginDayOfMonth(Calendar cal) {
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	/**
	 * 获取年份的第一天
	 * 
	 * @param date
	 * @return
	 * @author:administrator
	 */
	public static Date setBeginDayOfYear(Calendar cal) {
		cal.set(Calendar.MONTH, 0);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	public static void copyYearMonthDay(Calendar destCal, Calendar sourceCal) {
		destCal.set(Calendar.YEAR, sourceCal.get(Calendar.YEAR));
		destCal.set(Calendar.MONTH, sourceCal.get(Calendar.MONTH));
		destCal.set(Calendar.DATE, sourceCal.get(Calendar.DATE));
	}

	public static String formatEnDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
		return sdf.format(date).replaceAll("上午", "AM").replaceAll("下午", "PM");
	}

	public static Date parseDate(String dateString) {
		Date date = null;
		try {
			date = DateUtils.parseDate(dateString, new String[] {
					"yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd" });
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * @Title: changeDay
	 * @Description: TODO(天数加减)
	 * @param date
	 * @param offset
	 * @return Date
	 * @throws
	 */
	public static Date changeMonth(Date date, int offset) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.MONTH, (calendar.get(Calendar.MONTH) + offset));
		return calendar.getTime();
	}

	public static Date changeDate(Date date, int offset) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_YEAR,
				(calendar.get(Calendar.DAY_OF_YEAR) + offset));
		return calendar.getTime();
	}

}
