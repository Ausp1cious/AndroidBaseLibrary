package wang.auspicous.ausp1ciouslib.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Ausp1cious on 2019/3/26.
 * 时间工具类
 * 输入输出为long/String 类型
 */
public class TimeUtils{
    public static final String FORMAT_TIME = "HH:mm";
    public static final String FORMAT_TIME_SECOND = "HH:mm:ss";
    public static final String FORMAT_TIME_SECOND_MILLISECOND = "HH:mm:ss SSS";
    public static final String FORMAT_DATE = "yyyy-MM-dd";
    public static final String FORMAT_DATE_TIME = "yyyy-MM-dd HH:mm";
    public static final String FORMAT_DATE_TIME_SECOND = "yyyy-MM-dd HH:mm:ss";
    /**
     * 获取当前时间
     *
     * @return long型的当前时间
     */
    public static long getCurrentTimeAsLong() {
        return System.currentTimeMillis();
    }

    /**
     * 获取当前的时间
     *
     * @param pattern 时间匹配格式
     * @param locale  地方
     * @return 当前时间
     */
    public static String getCurrentTime(String pattern, Locale locale) {
        SimpleDateFormat sdf = getSimpleDateFormat(pattern, locale);
        return sdf.format(new Date());
    }

    /**
     * 获取时间戳
     *
     * @param time  需要匹配的时间戳
     * @param pattern 时间匹配格式
     * @param locale  地方
     * @return 输入时间的时间戳
     */
    public static long getTimestamp(String time, String pattern, Locale locale) {
        SimpleDateFormat sdf = getSimpleDateFormat(pattern, locale);
        long timestamp = -1;
        try {
            timestamp = sdf.parse(time).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return timestamp;
    }

    /**
     * 获取当前时间的年份
     */
    public static String getYear(Locale locale,long timestamp) {
        Calendar calendar = Calendar.getInstance(locale);
        calendar.setTime(new Date(timestamp));
        return String.valueOf(calendar.get(Calendar.YEAR));
    }
    public static String getYear(Locale locale,String time, String pattern) {
        long timestamp = getTimestamp(time, pattern, locale);
        return getYear(locale, timestamp);
    }
    /**
     * 获取当前时间的月份
     */
    public static String getMonth(Locale locale,long timestamp) {
        Calendar calendar = Calendar.getInstance(locale);
        calendar.setTime(new Date(timestamp));
        return String.valueOf(calendar.get(Calendar.MONTH));
    }
    public static String getMonth(Locale locale,String time, String pattern) {
        long timestamp = getTimestamp(time, pattern, locale);
        return getMonth(locale, timestamp);
    }

    /**
     * 获取当前时间的日期
     */
    public static String getDay(Locale locale,long timestamp) {
        Calendar calendar = Calendar.getInstance(locale);
        calendar.setTime(new Date(timestamp));
        return String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
    }
    public static String getDay(Locale locale,String time, String pattern) {
        long timestamp = getTimestamp(time, pattern, locale);
        return getDay(locale, timestamp);
    }

    /**
     * 获取当前时间的小时
     */
    public static String getHour(Locale locale,long timestamp) {
        Calendar calendar = Calendar.getInstance(locale);
        calendar.setTime(new Date(timestamp));
        return String.valueOf(calendar.get(Calendar.HOUR));
    }
    public static String getHour(Locale locale,String time, String pattern) {
        long timestamp = getTimestamp(time, pattern, locale);
        return getHour(locale, timestamp);
    }


    /**
     * 获取当前时间的分钟
     */
    public static String getMinute(Locale locale,long timestamp) {
        Calendar calendar = Calendar.getInstance(locale);
        calendar.setTime(new Date(timestamp));
        return String.valueOf(calendar.get(Calendar.MINUTE));
    }
    public static String getMinute(Locale locale,String time, String pattern) {
        long timestamp = getTimestamp(time, pattern, locale);
        return getMinute(locale, timestamp);
    }


    /**
     * 获取当前时间的秒
     */
    public static String getSecond(Locale locale,long timestamp) {
        Calendar calendar = Calendar.getInstance(locale);
        calendar.setTime(new Date(timestamp));
        return String.valueOf(calendar.get(Calendar.SECOND));
    }
    public static String getSecond(Locale locale,String time, String pattern) {
        long timestamp = getTimestamp(time, pattern, locale);
        return getSecond(locale, timestamp);
    }

    /**
     * 获取当前时间的毫秒
     */
    public static String getMilliSecond(Locale locale,long timestamp) {
        Calendar calendar = Calendar.getInstance(locale);
        calendar.setTime(new Date(timestamp));
        return String.valueOf(calendar.get(Calendar.MILLISECOND));
    }
    public static String getMilliSecond(Locale locale,String time, String pattern) {
        long timestamp = getTimestamp(time, pattern, locale);
        return getMilliSecond(locale, timestamp);
    }




    private static SimpleDateFormat getSimpleDateFormat(String pattern, Locale locale) {
        return new SimpleDateFormat(pattern, locale);
    }
}
