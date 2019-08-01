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
public class TimeUtils {
    public static final String FORMAT_TIME = "HH:mm";
    public static final String FORMAT_TIME_SECOND = "HH:mm:ss";
    public static final String FORMAT_TIME_SECOND_MILLISECOND = "HH:mm:ss.SSS";
    public static final String FORMAT_DATE = "yyyy-MM-dd";
    public static final String FORMAT_DATE_CHINESE = "yyyy年MM月dd日";
    public static final String FORMAT_DATE_TIME = "yyyy-MM-dd HH:mm";
    public static final String FORMAT_DATE_TIME_SECOND = "yyyy-MM-dd HH:mm:ss";
    public static final String FORMAT_DATE_TIME_SECOND_MILLISECOND = "yyyy-MM-dd HH:mm:ss.SSS";

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
     * @param time    需要匹配的时间戳
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
     *
     * @param locale    地方
     * @param timestamp 时间戳
     * @param pattern   时间匹配格式
     * @return 字符串时间格式
     */
    public static String getTime(Locale locale, long timestamp, String pattern) {
        SimpleDateFormat sdf = getSimpleDateFormat(pattern, locale);
        return sdf.format(timestamp);
    }

    /**
     * 获取当前时间的年份
     */
    public static String getYear(Locale locale, long timestamp) {
        Calendar calendar = Calendar.getInstance(locale);
        calendar.setTime(new Date(timestamp));
        return String.valueOf(calendar.get(Calendar.YEAR));
    }

    public static String getYear(Locale locale, String time, String pattern) {
        long timestamp = getTimestamp(time, pattern, locale);
        return getYear(locale, timestamp);
    }

    /**
     * 获取当前时间的月份
     */
    public static String getMonth(Locale locale, long timestamp) {
        Calendar calendar = Calendar.getInstance(locale);
        calendar.setTime(new Date(timestamp));
        return String.valueOf(calendar.get(Calendar.MONTH) + 1);
    }

    public static String getMonth(Locale locale, String time, String pattern) {
        long timestamp = getTimestamp(time, pattern, locale);
        return getMonth(locale, timestamp);
    }

    /**
     * 获取当前时间的日期
     */
    public static String getDay(Locale locale, long timestamp) {
        Calendar calendar = Calendar.getInstance(locale);
        calendar.setTime(new Date(timestamp));
        return String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
    }

    public static String getDay(Locale locale, String time, String pattern) {
        long timestamp = getTimestamp(time, pattern, locale);
        return getDay(locale, timestamp);
    }

    /**
     * 获取当前时间的小时
     */
    public static String getHour(Locale locale, long timestamp) {
        Calendar calendar = Calendar.getInstance(locale);
        calendar.setTime(new Date(timestamp));
        return String.valueOf(calendar.get(Calendar.HOUR_OF_DAY));
    }

    public static String getHour(Locale locale, String time, String pattern) {
        long timestamp = getTimestamp(time, pattern, locale);
        return getHour(locale, timestamp);
    }


    /**
     * 获取当前时间的分钟
     */
    public static String getMinute(Locale locale, long timestamp) {
        Calendar calendar = Calendar.getInstance(locale);
        calendar.setTime(new Date(timestamp));
        return String.valueOf(calendar.get(Calendar.MINUTE));
    }

    public static String getMinute(Locale locale, String time, String pattern) {
        long timestamp = getTimestamp(time, pattern, locale);
        return getMinute(locale, timestamp);
    }


    /**
     * 获取当前时间的秒
     */
    public static String getSecond(Locale locale, long timestamp) {
        Calendar calendar = Calendar.getInstance(locale);
        calendar.setTime(new Date(timestamp));
        return String.valueOf(calendar.get(Calendar.SECOND));
    }

    public static String getSecond(Locale locale, String time, String pattern) {
        long timestamp = getTimestamp(time, pattern, locale);
        return getSecond(locale, timestamp);
    }

    /**
     * 获取当前时间的毫秒
     */
    public static String getMilliSecond(Locale locale, long timestamp) {
        Calendar calendar = Calendar.getInstance(locale);
        calendar.setTime(new Date(timestamp));
        return String.valueOf(calendar.get(Calendar.MILLISECOND));
    }

    public static String getMilliSecond(Locale locale, String time, String pattern) {
        long timestamp = getTimestamp(time, pattern, locale);
        return getMilliSecond(locale, timestamp);
    }

    /**
     * 一个日期添加毫秒数
     */
    public static String datePlusMilliSeconds(Locale locale, String time, long addMilliSeconds) {
        return getTime(locale, datePlusMilliSecondsAsLong(locale, time, addMilliSeconds),
                FORMAT_DATE_TIME_SECOND_MILLISECOND);
    }

    public static long datePlusMilliSecondsAsLong(Locale locale, String time,
                                                  long addMilliSeconds) {
        long timestamp = getTimestamp(time, FORMAT_DATE_TIME_SECOND_MILLISECOND, locale);
        return timestamp + (addMilliSeconds);
    }

    /**
     * 一个日期添加秒数
     */
    public static String datePlusSeconds(Locale locale, String time, long addSeconds) {
        return getTime(locale, datePlusSecondsAsLong(locale, time, addSeconds),
                FORMAT_DATE_TIME_SECOND_MILLISECOND);
    }

    public static long datePlusSecondsAsLong(Locale locale, String time, long addSeconds) {
        long timestamp = getTimestamp(time, FORMAT_DATE_TIME_SECOND_MILLISECOND, locale);
        return timestamp + (1000 * addSeconds);
    }

    /**
     * 一个日期添加分钟数
     */
    public static String datePlusMinutes(Locale locale, String time, long addMinutes) {
        return getTime(locale, datePlusMinutesAsLong(locale, time, addMinutes),
                FORMAT_DATE_TIME_SECOND_MILLISECOND);
    }

    public static long datePlusMinutesAsLong(Locale locale, String time, long addMinutes) {
        long timestamp = getTimestamp(time, FORMAT_DATE_TIME_SECOND_MILLISECOND, locale);
        return timestamp + (1000 * 60 * addMinutes);
    }

    /**
     * 一个日期添加小时数
     */
    public static String datePlusHours(Locale locale, String time, long addHours) {
        return getTime(locale, datePlusHoursAsLong(locale, time, addHours),
                FORMAT_DATE_TIME_SECOND_MILLISECOND);
    }

    public static long datePlusHoursAsLong(Locale locale, String time, long addHours) {
        long timestamp = getTimestamp(time, FORMAT_DATE_TIME_SECOND_MILLISECOND, locale);
        return timestamp + (1000 * 60 * 60 * addHours);
    }

    /**
     * 一个日期添加天数
     */
    public static String datePlusDays(Locale locale, String time, String pattern, long addDays) {
        return getTime(locale, datePlusDaysAsLong(locale, time, pattern, addDays), pattern);
    }

    public static long datePlusDaysAsLong(Locale locale, String time, String pattern,
                                          long addDays) {
        long timestamp = getTimestamp(time, pattern, locale);
        String timeFormat =
                DateBean.of(locale, timestamp).plusDays(addDays).getDate() + " " + getHour(locale,
                        timestamp) + ":" + getMinute(locale, timestamp) + ":" + getSecond(locale,
                        timestamp) + "." + getMilliSecond(locale, timestamp);
        return getTimestamp(timeFormat, TimeUtils.FORMAT_DATE_TIME_SECOND_MILLISECOND,
                locale);
    }

    /**
     * 一个日期添加周数
     */
    public static String datePlusWeeks(Locale locale, String time, String pattern, long addWeeks) {
        return getTime(locale, datePlusWeeksAsLong(locale, time, pattern, addWeeks), pattern);
    }

    public static long datePlusWeeksAsLong(Locale locale, String time, String pattern,
                                           long addWeeks) {
        long timestamp = getTimestamp(time, pattern, locale);
        String timeFormat =
                DateBean.of(locale, timestamp).plusWeeks(addWeeks).getDate() + " " + getHour(locale,
                        timestamp) + ":" + getMinute(locale, timestamp) + ":" + getSecond(locale,
                        timestamp) + "." + getMilliSecond(locale, timestamp);
        return getTimestamp(timeFormat, TimeUtils.FORMAT_DATE_TIME_SECOND_MILLISECOND,
                locale);
    }

    /**
     * 一个日期添加月份
     */
    public static String datePlusMonths(Locale locale, String time, String pattern,
                                        long addMonths) {
        return getTime(locale, datePlusMonthsAsLong(locale, time, pattern, addMonths), pattern);
    }

    public static long datePlusMonthsAsLong(Locale locale, String time, String pattern,
                                            long addMonths) {
        long timestamp = getTimestamp(time, pattern, locale);
        String timeFormat =
                DateBean.of(locale, timestamp).plusMonths(addMonths).getDate() + " " + getHour(locale,
                        timestamp) + ":" + getMinute(locale, timestamp) + ":" + getSecond(locale,
                        timestamp) + "." + getMilliSecond(locale, timestamp);
        return getTimestamp(timeFormat, TimeUtils.FORMAT_DATE_TIME_SECOND_MILLISECOND,
                locale);
    }

    /**
     * 一个日期添加年
     */
    public static String datePlusYears(Locale locale, String time, String pattern, long addYears) {
        return getTime(locale, datePlusYearsAsLong(locale, time, pattern, addYears), pattern);
    }

    public static long datePlusYearsAsLong(Locale locale, String time, String pattern,
                                           long addYears) {
        long timestamp = getTimestamp(time, pattern, locale);
        String timeFormat =
                DateBean.of(locale, timestamp).plusYears(addYears).getDate() + " " + getHour(locale,
                        timestamp) + ":" + getMinute(locale, timestamp) + ":" + getSecond(locale,
                        timestamp) + "." + getMilliSecond(locale, timestamp);
        return getTimestamp(timeFormat, TimeUtils.FORMAT_DATE_TIME_SECOND_MILLISECOND,
                locale);
    }


    /**
     * 一个日期减少毫秒数
     */
    public static String dateMinusMilliSeconds(Locale locale, String time, long minusMilliSeconds) {
        return getTime(locale, dateMinusMilliSecondsAsLong(locale, time, minusMilliSeconds),
                FORMAT_DATE_TIME_SECOND_MILLISECOND);
    }

    public static long dateMinusMilliSecondsAsLong(Locale locale, String time,
                                                   long minusMilliSeconds) {
        long timestamp = getTimestamp(time, FORMAT_DATE_TIME_SECOND_MILLISECOND, locale);
        return timestamp - (minusMilliSeconds);
    }

    /**
     * 一个日期减少秒数
     */
    public static String dateMinusSeconds(Locale locale, String time, long minusSeconds) {
        return getTime(locale, dateMinusSecondsAsLong(locale, time, minusSeconds),
                FORMAT_DATE_TIME_SECOND_MILLISECOND);
    }

    public static long dateMinusSecondsAsLong(Locale locale, String time, long minusSeconds) {
        long timestamp = getTimestamp(time, FORMAT_DATE_TIME_SECOND_MILLISECOND, locale);
        return timestamp - (1000 * minusSeconds);
    }

    /**
     * 一个日期减少分钟数
     */
    public static String dateMinusMinutes(Locale locale, String time, long minusMinutes) {
        return getTime(locale, dateMinusMinutesAsLong(locale, time, minusMinutes),
                FORMAT_DATE_TIME_SECOND_MILLISECOND);
    }

    public static long dateMinusMinutesAsLong(Locale locale, String time, long minusMinutes) {
        long timestamp = getTimestamp(time, FORMAT_DATE_TIME_SECOND_MILLISECOND, locale);
        return timestamp - (1000 * 60 * minusMinutes);
    }

    /**
     * 一个日期减少小时数
     */
    public static String dateMinusHours(Locale locale, String time, long minusHours) {
        return getTime(locale, dateMinusHoursAsLong(locale, time, minusHours),
                FORMAT_DATE_TIME_SECOND_MILLISECOND);
    }

    public static long dateMinusHoursAsLong(Locale locale, String time, long minusHours) {
        long timestamp = getTimestamp(time, FORMAT_DATE_TIME_SECOND_MILLISECOND, locale);
        return timestamp - (1000 * 60 * 60 * minusHours);
    }

    /**
     * 一个日期减少天数
     */
    public static String dateMinusDays(Locale locale, String time, String pattern,
                                       long subtractDays) {
        return getTime(locale, dateMinusDaysAsLong(locale, time, pattern, subtractDays), pattern);
    }

    public static long dateMinusDaysAsLong(Locale locale, String time, String pattern,
                                           long subtractDays) {
        long timestamp = getTimestamp(time, pattern, locale);
        String timeFormat = DateBean.of(locale, timestamp).minusDays(subtractDays).getDate() + " "
                + getHour(locale,
                timestamp) + ":" + getMinute(locale, timestamp) + ":" + getSecond(locale,
                timestamp) + "." + getMilliSecond(locale, timestamp);
        return getTimestamp(timeFormat, TimeUtils.FORMAT_DATE_TIME_SECOND_MILLISECOND,
                locale);
    }

    /**
     * 一个日期减少周数
     */
    public static String dateMinusWeeks(Locale locale, String time, String pattern,
                                        long subtractWeeks) {
        return getTime(locale, dateMinusWeeksAsLong(locale, time, pattern, subtractWeeks), pattern);
    }

    public static long dateMinusWeeksAsLong(Locale locale, String time, String pattern,
                                            long subtractWeeks) {
        long timestamp = getTimestamp(time, pattern, locale);
        String timeFormat = DateBean.of(locale, timestamp).minusWeeks(subtractWeeks).getDate() +
                " " + getHour(locale,
                timestamp) + ":" + getMinute(locale, timestamp) + ":" + getSecond(locale,
                timestamp) + "." + getMilliSecond(locale, timestamp);
        return getTimestamp(timeFormat, TimeUtils.FORMAT_DATE_TIME_SECOND_MILLISECOND,
                locale);
    }

    /**
     * 一个日期减少月份
     */
    public static String dateMinusMonths(Locale locale, String time, String pattern,
                                         long subtractMonths) {
        return getTime(locale, dateMinusMonthsAsLong(locale, time, pattern, subtractMonths),
                pattern);
    }

    public static long dateMinusMonthsAsLong(Locale locale, String time, String pattern,
                                             long subtractMonths) {
        long timestamp = getTimestamp(time, pattern, locale);
        String timeFormat =
                DateBean.of(locale, timestamp).minusMonths(subtractMonths).getDate() + " " + getHour(locale,
                        timestamp) + ":" + getMinute(locale, timestamp) + ":" + getSecond(locale,
                        timestamp) + "." + getMilliSecond(locale, timestamp);
        return getTimestamp(timeFormat, TimeUtils.FORMAT_DATE_TIME_SECOND_MILLISECOND,
                locale);
    }

    /**
     * 一个日期减少年
     */
    public static String dateMinusYears(Locale locale, String time, String pattern,
                                        long subtractYears) {
        return getTime(locale, dateMinusYearsAsLong(locale, time, pattern, subtractYears), pattern);
    }

    public static long dateMinusYearsAsLong(Locale locale, String time, String pattern,
                                            long subtractYears) {
        long timestamp = getTimestamp(time, pattern, locale);
        String timeFormat = DateBean.of(locale, timestamp).minusYears(subtractYears).getDate() +
                " " + getHour(locale,
                timestamp) + ":" + getMinute(locale, timestamp) + ":" + getSecond(locale,
                timestamp) + "." + getMilliSecond(locale, timestamp);
        return getTimestamp(timeFormat, TimeUtils.FORMAT_DATE_TIME_SECOND_MILLISECOND,
                locale);
    }


    /**
     * 两个日期相差的毫秒数
     */
    public static long getMillisecondsBetweenTwoTime(Locale locale, long timestampOne,
                                                     long timestampTwo) {
        int millisecondOne = Integer.valueOf(getMilliSecond(locale, timestampOne));
        int millisecondTwo = Integer.valueOf(getMilliSecond(locale, timestampTwo));
        long secondBetweenTwoTime = getMinutesBetweenTwoTime(locale, timestampOne, timestampTwo);
        return secondBetweenTwoTime * 1000 - (millisecondOne - millisecondTwo);
    }

    public static long getMillisecondsBetweenTwoTime(Locale locale, String timeOne,
                                                     String timeTwo) {
        long timestampOne = getTimestamp(timeOne, FORMAT_DATE_TIME_SECOND_MILLISECOND, locale);
        long timestampTwo = getTimestamp(timeTwo, FORMAT_DATE_TIME_SECOND_MILLISECOND, locale);
        long secondBetweenTwoTime = getMinutesBetweenTwoTime(locale, timeOne, timeTwo);
        int millisecondOne = Integer.valueOf(getMilliSecond(locale, timestampOne));
        int millisecondTwo = Integer.valueOf(getMilliSecond(locale, timestampTwo));
        return secondBetweenTwoTime * 1000 - (millisecondOne - millisecondTwo);
    }


    /**
     * 两个日期相差的秒数
     */
    public static long getSecondsBetweenTwoTime(Locale locale, long timestampOne,
                                                long timestampTwo) {
        int secondOne = Integer.valueOf(getSecond(locale, timestampOne));
        int secondTwo = Integer.valueOf(getSecond(locale, timestampTwo));
        long minutesBetweenTwoTime = getMinutesBetweenTwoTime(locale, timestampOne, timestampTwo);
        return minutesBetweenTwoTime * 60 - (secondOne - secondTwo);
    }

    public static long getSecondsBetweenTwoTime(Locale locale, String timeOne,
                                                String timeTwo) {
        long timestampOne = getTimestamp(timeOne, FORMAT_DATE_TIME_SECOND_MILLISECOND, locale);
        long timestampTwo = getTimestamp(timeTwo, FORMAT_DATE_TIME_SECOND_MILLISECOND, locale);
        long minutesBetweenTwoTime = getMinutesBetweenTwoTime(locale, timeOne, timeTwo);
        int secondOne = Integer.valueOf(getSecond(locale, timestampOne));
        int secondTwo = Integer.valueOf(getSecond(locale, timestampTwo));
        return minutesBetweenTwoTime * 60 - (secondOne - secondTwo);
    }

    /**
     * 两个日期相差的分钟数
     */
    public static long getMinutesBetweenTwoTime(Locale locale, long timestampOne,
                                                long timestampTwo) {
        int minuteOne = Integer.valueOf(getMinute(locale, timestampOne));
        int minuteTwo = Integer.valueOf(getMinute(locale, timestampTwo));
        long hoursBetweenTwoTime = getHoursBetweenTwoTime(locale, timestampOne, timestampTwo);
        return hoursBetweenTwoTime * 60 - (minuteOne - minuteTwo);
    }

    public static long getMinutesBetweenTwoTime(Locale locale, String timeOne,
                                                String timeTwo) {
        long timestampOne = getTimestamp(timeOne, FORMAT_DATE_TIME_SECOND_MILLISECOND, locale);
        long timestampTwo = getTimestamp(timeTwo, FORMAT_DATE_TIME_SECOND_MILLISECOND, locale);
        long hoursBetweenTwoTime = getHoursBetweenTwoTime(locale, timeOne, timeTwo);
        int minuteOne = Integer.valueOf(getMinute(locale, timestampOne));
        int minuteTwo = Integer.valueOf(getMinute(locale, timestampTwo));
        return hoursBetweenTwoTime * 60 - (minuteOne - minuteTwo);
    }

    /**
     * 两个日期相差的小时数
     */
    public static long getHoursBetweenTwoTime(Locale locale, long timestampOne, long timestampTwo) {
        long daysBetweenTwoTime = getDaysBetweenTwoTime(locale, timestampOne, timestampTwo);
        int hourOne = Integer.valueOf(getHour(locale, timestampOne));
        int hourTwo = Integer.valueOf(getHour(locale, timestampTwo));
        return daysBetweenTwoTime * 24 - (hourOne - hourTwo);
    }

    public static long getHoursBetweenTwoTime(Locale locale, String timeOne,
                                              String timeTwo) {
        long timestampOne = getTimestamp(timeOne, FORMAT_DATE_TIME_SECOND_MILLISECOND, locale);
        long timestampTwo = getTimestamp(timeTwo, FORMAT_DATE_TIME_SECOND_MILLISECOND, locale);
        long daysBetweenTwoTime = getDaysBetweenTwoTime(locale, timestampOne, timestampTwo);
        int hourOne = Integer.valueOf(getHour(locale, timestampOne));
        int hourTwo = Integer.valueOf(getHour(locale, timestampTwo));
        return daysBetweenTwoTime * 24 - (hourOne - hourTwo);
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
    public static long getMonthsBetweenTwoTime(Locale locale, long timestampOne,
                                               long timestampTwo) {
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
     * 获取指定的时间的星期
     */
    public static String getWeek(String[] weekName,Date data) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(data);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekName[w];
    }

    private static SimpleDateFormat getSimpleDateFormat(String pattern, Locale locale) {
        return new SimpleDateFormat(pattern, locale);
    }

}
