package wang.auspicous.ausp1cious.utils;

import android.annotation.SuppressLint;

import java.util.Locale;

import wang.auspicous.ausp1ciouslib.utils.TimeUtils;

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

}
