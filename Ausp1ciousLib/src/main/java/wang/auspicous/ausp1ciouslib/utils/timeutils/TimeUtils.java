package wang.auspicous.ausp1ciouslib.utils.timeutils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
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
     * 获取时间
     * @param locale 地方
     * @param timestamp 时间戳
     * @param pattern 时间匹配格式
     * @return 字符串时间格式
     */
    public static String getTime(Locale locale, long timestamp, String pattern) {
        SimpleDateFormat sdf = getSimpleDateFormat(pattern, locale);
        return sdf.format(timestamp);
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
        return String.valueOf(calendar.get(Calendar.MONTH)+1);
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

    /**
     * 一个日期添加天数
     */
    public static String datePlusDays(Locale locale, String time, String pattern,long addDays) {
        return getTime(locale, datePlusDaysAsLong(locale,time,pattern,addDays), pattern);
    }
    public static long datePlusDaysAsLong(Locale locale, String time, String pattern,long addDays) {
        long timestamp = getTimestamp(time, pattern, locale);
        return DateBean.of(locale, timestamp).plusDays(addDays).getDateTimestamp(
                locale);
    }

    /**
     * 一个日期添加周数
     */
    public static String datePlusWeeks(Locale locale, String time, String pattern,long addWeeks) {
        return getTime(locale, datePlusWeeksAsLong(locale,time,pattern,addWeeks), pattern);
    }
    public static long datePlusWeeksAsLong(Locale locale, String time, String pattern,long addWeeks) {
        long timestamp = getTimestamp(time, pattern, locale);
        return DateBean.of(locale, timestamp).plusWeeks(addWeeks).getDateTimestamp(
                locale);
    }

    /**
     * 一个日期添加月份
     */
    public static String datePlusMonths(Locale locale, String time, String pattern,long addMonths) {
        return getTime(locale, datePlusMonthsAsLong(locale,time,pattern,addMonths), pattern);
    }
    public static long datePlusMonthsAsLong(Locale locale, String time, String pattern,long addMonths) {
        long timestamp = getTimestamp(time, pattern, locale);
        return DateBean.of(locale, timestamp).plusMonths(addMonths).getDateTimestamp(
                locale);
    }

    /**
     * 一个日期添加年
     */
    public static String datePlusYears(Locale locale, String time, String pattern,long addYears) {
        return getTime(locale, datePlusYearsAsLong(locale,time,pattern,addYears), pattern);
    }
    public static long datePlusYearsAsLong(Locale locale, String time, String pattern,long addYears) {
        long timestamp = getTimestamp(time, pattern, locale);
        return DateBean.of(locale, timestamp).plusYears(addYears).getDateTimestamp(
                locale);
    }


    /**
     * 一个日期减少天数
     */
    public static String dateMinusDays(Locale locale, String time, String pattern,long subtractDays) {
        return getTime(locale, dateMinusDaysAsLong(locale,time,pattern,subtractDays), pattern);
    }
    public static long dateMinusDaysAsLong(Locale locale, String time, String pattern,long subtractDays) {
        long timestamp = getTimestamp(time, pattern, locale);
        return DateBean.of(locale, timestamp).minusDays(subtractDays).getDateTimestamp(
                locale);
    }

    /**
     * 一个日期减少周数
     */
    public static String dateMinusWeeks(Locale locale, String time, String pattern,long subtractWeeks) {
        return getTime(locale, dateMinusWeeksAsLong(locale,time,pattern,subtractWeeks), pattern);
    }
    public static long dateMinusWeeksAsLong(Locale locale, String time, String pattern,long subtractWeeks) {
        long timestamp = getTimestamp(time, pattern, locale);
        return DateBean.of(locale, timestamp).minusWeeks(subtractWeeks).getDateTimestamp(
                locale);
    }

    /**
     * 一个日期减少月份
     */
    public static String dateMinusMonths(Locale locale, String time, String pattern,long subtractMonths) {
        return getTime(locale, dateMinusMonthsAsLong(locale,time,pattern,subtractMonths), pattern);
    }
    public static long dateMinusMonthsAsLong(Locale locale, String time, String pattern,long subtractMonths) {
        long timestamp = getTimestamp(time, pattern, locale);
        return DateBean.of(locale, timestamp).minusMonths(subtractMonths).getDateTimestamp(
                locale);
    }

    /**
     * 一个日期减少年
     */
    public static String dateMinusYears(Locale locale, String time, String pattern,long subtractYears) {
        return getTime(locale, dateMinusYearsAsLong(locale,time,pattern,subtractYears), pattern);
    }
    public static long dateMinusYearsAsLong(Locale locale, String time, String pattern,long subtractYears) {
        long timestamp = getTimestamp(time, pattern, locale);
        return DateBean.of(locale, timestamp).minusYears(subtractYears).getDateTimestamp(
                locale);
    }

    /**
     * 两个日期间隔多少天
     */
    public static long getDaysBetweenTwoTime(Locale locale, long timestampOne, long timestampTwo) {
        return DateBean.of(locale, timestampOne).daysUntil(DateBean.of(locale, timestampTwo));
    }

    public static long getDaysBetweenTwoTime(Locale locale, String pattern, String timeOne,
            String timeTwo) {
        long timestampOne = getTimestamp(timeOne, pattern, locale);
        long timestampTwo = getTimestamp(timeTwo, pattern, locale);
        return getDaysBetweenTwoTime(locale, timestampOne, timestampTwo);
    }
    /**
     * 两个日期间隔多少周
     */
    public static long getWeeksBetweenTwoTime(Locale locale, long timestampOne, long timestampTwo) {
        return DateBean.of(locale, timestampOne).weeksUntil(DateBean.of(locale, timestampTwo));
    }

    public static long getWeeksBetweenTwoTime(Locale locale, String pattern, String timeOne,
            String timeTwo) {
        long timestampOne = getTimestamp(timeOne, pattern, locale);
        long timestampTwo = getTimestamp(timeTwo, pattern, locale);
        return getWeeksBetweenTwoTime(locale, timestampOne, timestampTwo);
    }
    /**
     * 两个日期间隔多少月
     */
    public static long getMonthsBetweenTwoTime(Locale locale, long timestampOne, long timestampTwo) {
        return DateBean.of(locale, timestampOne).monthsUntil(DateBean.of(locale, timestampTwo));
    }

    public static long getMonthsBetweenTwoTime(Locale locale, String pattern, String timeOne,
            String timeTwo) {
        long timestampOne = getTimestamp(timeOne, pattern, locale);
        long timestampTwo = getTimestamp(timeTwo, pattern, locale);
        return getMonthsBetweenTwoTime(locale, timestampOne, timestampTwo);
    }
    /**
     * 两个日期间隔多少年
     */
    public static long getYearsBetweenTwoTime(Locale locale, long timestampOne, long timestampTwo) {
        return DateBean.of(locale, timestampOne).yearsUntil(DateBean.of(locale, timestampTwo));
    }

    public static long getYearsBetweenTwoTime(Locale locale, String pattern, String timeOne,
            String timeTwo) {
        long timestampOne = getTimestamp(timeOne, pattern, locale);
        long timestampTwo = getTimestamp(timeTwo, pattern, locale);
        return getYearsBetweenTwoTime(locale, timestampOne, timestampTwo);
    }

    /**
     * 两个日期相差的时间戳
     */
    public static long getTimeBetweenTwoTimeAsTimestamp(Locale locale, long timestampOne,
            long timestampTwo) {
        DateBean until = DateBean.of(locale, timestampOne).until(DateBean.of(locale, timestampTwo));
        return until.getDateTimestamp(locale);
    }

    public static long getTimeBetweenTwoTimeAsTimestamp(Locale locale, String pattern, String timeOne,
            String timeTwo) {
        long timestampOne = getTimestamp(timeOne, pattern, locale);
        long timestampTwo = getTimestamp(timeTwo, pattern, locale);
        return getTimeBetweenTwoTimeAsTimestamp(locale, timestampOne, timestampTwo);
    }

    /**
     * 两个日期相差的日期
     */
    public static String getTimeBetweenTwoTime(Locale locale, String pattern,long timestampOne,
            long timestampTwo) {
        long timeBetweenTwoTimeAsTimestamp = getTimeBetweenTwoTimeAsTimestamp(locale, timestampOne,
                timestampTwo);
        return getTime(locale, timeBetweenTwoTimeAsTimestamp, pattern);
    }

    public static String getTimeBetweenTwoTime(Locale locale, String intoPattern, String timeOne,
            String timeTwo, String outPattern) {
        long timestampOne = getTimestamp(timeOne, intoPattern, locale);
        long timestampTwo = getTimestamp(timeTwo, intoPattern, locale);
        long timeBetweenTwoTimeAsTimestamp = getTimeBetweenTwoTimeAsTimestamp(locale, timestampOne,
                timestampTwo);
        return getTime(locale, timeBetweenTwoTimeAsTimestamp, outPattern);
    }



    private static SimpleDateFormat getSimpleDateFormat(String pattern, Locale locale) {
        return new SimpleDateFormat(pattern, locale);
    }
}
