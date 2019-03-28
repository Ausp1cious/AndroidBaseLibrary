package wang.auspicous.ausp1cious.utils;

import android.annotation.SuppressLint;

import java.util.Locale;

import wang.auspicous.ausp1ciouslib.utils.timeutils.TimeUtils;

/**
 * Created by Ausp1cious on 2019/3/27.
 */
public class AppTimeUtils extends TimeUtils {
    @SuppressLint("ConstantLocale")
    private static final Locale CURRENT_LOCAL = Locale.getDefault();

    /**
     * 获取当前的时间
     */
    public static String getCurrentTime(String pattern) {
        return getCurrentTime(pattern, CURRENT_LOCAL);
    }

    /**
     * 获取时间戳
     */
    public static long getTimestamp(String time, String pattern) {
        return getTimestamp(time, pattern, CURRENT_LOCAL);
    }

    /**
     * 获取当前时间的年份
     */
    public static String getYear(long timestamp) {
        return getYear(CURRENT_LOCAL, timestamp);
    }

    public static String getYear(String time, String pattern) {
        return getYear(CURRENT_LOCAL, time, pattern);
    }

    /**
     * 获取当前时间的月份
     */
    public static String getMonth(long timestamp) {
        return getMonth(CURRENT_LOCAL, timestamp);
    }

    public static String getMonth(String time, String pattern) {
        return getMonth(CURRENT_LOCAL, time, pattern);
    }

    /**
     * 获取当前时间的日期
     */
    public static String getDay(long timestamp) {
        return getDay(CURRENT_LOCAL, timestamp);
    }

    public static String getDay(String time, String pattern) {
        return getDay(CURRENT_LOCAL, time, pattern);
    }

    /**
     * 获取当前时间的小时
     */
    public static String getHour(long timestamp) {
        return getHour(CURRENT_LOCAL, timestamp);
    }

    public static String getHour(String time, String pattern) {
        return getHour(CURRENT_LOCAL, time, pattern);
    }

    /**
     * 获取当前时间的分钟
     */
    public static String getMinute(long timestamp) {
        return getMinute(CURRENT_LOCAL, timestamp);
    }

    public static String getMinute(String time, String pattern) {
        return getMinute(CURRENT_LOCAL, time, pattern);
    }

    /**
     * 获取当前时间的秒
     */
    public static String getSecond(long timestamp) {
        return getSecond(CURRENT_LOCAL, timestamp);
    }

    public static String getSecond(String time, String pattern) {
        return getSecond(CURRENT_LOCAL, time, pattern);
    }

    /**
     * 获取当前时间的毫秒
     */
    public static String getMilliSecond(long timestamp) {
        return getMilliSecond(CURRENT_LOCAL, timestamp);
    }

    public static String getMilliSecond(String time, String pattern) {
        return getMilliSecond(CURRENT_LOCAL, time, pattern);
    }


    /**
     * 一个日期添加天数，后返回日期
     */
    public static String datePlusDays(String time, String pattern, long addDays) {
        return datePlusDays(CURRENT_LOCAL, time, pattern, addDays);
    }

    public static long datePlusDaysAsLong(String time, String pattern, long addDays) {
        return datePlusDaysAsLong(time, pattern, addDays);
    }

    /**
     * 一个日期添加周数
     */
    public static String datePlusWeeks(String time, String pattern, long addWeeks) {
        return datePlusWeeks(CURRENT_LOCAL, time, pattern, addWeeks);
    }

    public static long datePlusWeeksAsLong(String time, String pattern, long addWeeks) {
        return datePlusWeeksAsLong(CURRENT_LOCAL, time, pattern, addWeeks);
    }

    /**
     * 一个日期添加月份
     */
    public static String datePlusMonths(String time, String pattern, long addMonths) {
        return datePlusMonths(CURRENT_LOCAL, time, pattern, addMonths);
    }

    public static long datePlusMonthsAsLong(String time, String pattern, long addMonths) {
        return datePlusMonthsAsLong(CURRENT_LOCAL, time, pattern, addMonths);
    }

    /**
     * 一个日期添加年
     */
    public static String datePlusYears(String time, String pattern, long addYears) {
        return datePlusYears(CURRENT_LOCAL, time, pattern, addYears);
    }

    public static long datePlusYearsAsLong(String time, String pattern, long addYears) {
        return datePlusYearsAsLong(CURRENT_LOCAL, time, pattern, addYears);
    }

    /**
     * 一个日期减少天数
     */
    public static String dateMinusDays(String time, String pattern, long subtractDays) {
        return dateMinusDays(CURRENT_LOCAL, time, pattern, subtractDays);
    }

    public static long dateMinusDaysAsLong(String time, String pattern, long subtractDays) {
        return dateMinusDaysAsLong(CURRENT_LOCAL, time, pattern, subtractDays);
    }

    /**
     * 一个日期减少周数
     */
    public static String dateMinusWeeks(String time, String pattern, long subtractWeeks) {
        return dateMinusWeeks(CURRENT_LOCAL, time, pattern, subtractWeeks);
    }

    public static long dateMinusWeeksAsLong(String time, String pattern, long subtractWeeks) {
        return dateMinusWeeksAsLong(CURRENT_LOCAL, time, pattern, subtractWeeks);
    }

    /**
     * 一个日期减少月份
     */
    public static String dateMinusMonths(String time, String pattern, long subtractMonths) {
        return dateMinusMonths(CURRENT_LOCAL, time, pattern, subtractMonths);
    }

    public static long dateMinusMonthsAsLong(String time, String pattern, long subtractMonths) {
        return dateMinusMonthsAsLong(CURRENT_LOCAL, time, pattern, subtractMonths);
    }

    /**
     * 一个日期减少年
     */
    public static String dateMinusYears(String time, String pattern, long subtractYears) {
        return dateMinusYears(CURRENT_LOCAL, time, pattern, subtractYears);
    }

    public static long dateMinusYearsAsLong(String time, String pattern, long subtractYears) {
        return dateMinusYearsAsLong(CURRENT_LOCAL, time, pattern, subtractYears);
    }

    /**
     * 两个日期相差的天数
     */
    public static long getDaysBetweenTwoTime(long timestampOne, long timestampTwo) {
        return getDaysBetweenTwoTime(CURRENT_LOCAL, timestampOne, timestampTwo);
    }

    public static long getDaysBetweenTwoTime(String pattern, String timeOne, String timeTwo) {
        return getDaysBetweenTwoTime(CURRENT_LOCAL, pattern, timeOne, timeTwo);
    }

    /**
     * 两个日期相差的周数
     */
    public static long getWeeksBetweenTwoTime(long timestampOne, long timestampTwo) {
        return getWeeksBetweenTwoTime(CURRENT_LOCAL, timestampOne, timestampTwo);
    }

    public static long getWeeksBetweenTwoTime(String pattern, String timeOne, String timeTwo) {
        return getWeeksBetweenTwoTime(CURRENT_LOCAL, pattern, timeOne, timeTwo);
    }

    /**
     * 两个日期相差的月份
     */
    public static long getMonthsBetweenTwoTime(long timestampOne, long timestampTwo) {
        return getMonthsBetweenTwoTime(CURRENT_LOCAL, timestampOne, timestampTwo);
    }

    public static long getMonthsBetweenTwoTime(String pattern, String timeOne, String timeTwo) {
        return getMonthsBetweenTwoTime(CURRENT_LOCAL, pattern, timeOne, timeTwo);
    }

    /**
     * 两个日期相差的年
     */
    public static long getYearsBetweenTwoTime(long timestampOne, long timestampTwo) {
        return getYearsBetweenTwoTime(CURRENT_LOCAL, timestampOne, timestampTwo);
    }

    public static long getYearsBetweenTwoTime(String pattern, String timeOne, String timeTwo) {
        return getYearsBetweenTwoTime(CURRENT_LOCAL, pattern, timeOne, timeTwo);
    }

    /**
     * 两个日期相差的时间戳
     */
    public static long getTimeBetweenTwoTimeAsTimestamp(long timestampOne,
            long timestampTwo) {
        return getTimeBetweenTwoTimeAsTimestamp(CURRENT_LOCAL, timestampOne, timestampTwo);
    }

    public static long getTimeBetweenTwoTimeAsTimestamp(String pattern, String timeOne,
            String timeTwo) {
        return getTimeBetweenTwoTimeAsTimestamp(CURRENT_LOCAL, pattern, timeOne, timeTwo);
    }

    /**
     * 两个日期相差的日期
     */
    public static String getTimeBetweenTwoTime(String pattern, long timestampOne,
            long timestampTwo) {
        return getTimeBetweenTwoTime(CURRENT_LOCAL, pattern, timestampOne, timestampTwo);
    }

    public static String getTimeBetweenTwoTime(String intoPattern, String timeOne,
            String timeTwo, String outPattern) {
        return getTimeBetweenTwoTime(CURRENT_LOCAL, intoPattern, timeOne, timeTwo, outPattern);
    }

}
