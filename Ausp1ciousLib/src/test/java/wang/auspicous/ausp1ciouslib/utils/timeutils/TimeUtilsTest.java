package wang.auspicous.ausp1ciouslib.utils.timeutils;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Locale;

public class TimeUtilsTest {

    @Before
    public void setUp() {

    }

    @Test
    public void test() {

    }

    /**
     * 验证获取当前的时间
     */
    @Test
    public void testGetCurrentTime() {
        Assert.assertEquals(TimeUtils.getCurrentTimeAsLong(), System.currentTimeMillis());
    }

    /**
     * 测试获取的时间
     */
    @Test
    public void testGetTime() {
        //获取当前时间戳
        Assert.assertEquals(TimeUtils.getTimestamp("2019-07-30", TimeUtils.FORMAT_DATE,
                Locale.CHINA), 1564416000000L);
        Assert.assertEquals(TimeUtils.getTimestamp("2019-07-30 00:01", TimeUtils.FORMAT_DATE_TIME
                , Locale.CHINA), 1564416060000L);
        Assert.assertEquals(TimeUtils.getTimestamp("2019-07-30 00:01:01",
                TimeUtils.FORMAT_DATE_TIME_SECOND, Locale.CHINA), 1564416061000L);
        Assert.assertEquals(TimeUtils.getTimestamp("2019-07-30 00:01:01.001",
                TimeUtils.FORMAT_DATE_TIME_SECOND_MILLISECOND, Locale.CHINA), 1564416061001L);
        //获取时间
        Assert.assertEquals(TimeUtils.getTime(Locale.CHINA, 1564416061001L,
                TimeUtils.FORMAT_DATE_TIME_SECOND_MILLISECOND), "2019-07-30 00:01:01.001");
        Assert.assertEquals(TimeUtils.getTime(Locale.CHINA, 1564416061000L,
                TimeUtils.FORMAT_DATE_TIME_SECOND), "2019-07-30 00:01:01");
        Assert.assertEquals(TimeUtils.getTime(Locale.CHINA, 1564416060000L,
                TimeUtils.FORMAT_DATE_TIME), "2019-07-30 00:01");
        Assert.assertEquals(TimeUtils.getTime(Locale.CHINA, 1564416000000L,
                TimeUtils.FORMAT_DATE), "2019-07-30");
    }

    /**
     * 验证获取的年份是否正确
     */
    @Test
    public void testGetYearRight() {
        Assert.assertEquals(TimeUtils.getYear(Locale.CHINA, 1564416000000L), "2019");
        Assert.assertEquals(TimeUtils.getYear(Locale.CHINA, "2019-07-30", TimeUtils.FORMAT_DATE),
                "2019");
    }

    /**
     * 验证获取的月份是否正确
     */
    @Test
    public void testGetMonthRight() {
        Assert.assertEquals(TimeUtils.getMonth(Locale.CHINA, 1564416000000L), "7");
        Assert.assertEquals(TimeUtils.getMonth(Locale.CHINA, "2019-07-30", TimeUtils.FORMAT_DATE)
                , "7");
    }

    /**
     * 验证获取的小时是否正确
     */
    @Test
    public void testGetHourRight() {
        Assert.assertEquals(TimeUtils.getHour(Locale.CHINA, 1564416061001L), "0");
        Assert.assertEquals(TimeUtils.getHour(Locale.CHINA, "2019-07-30 00:01:01.001",
                TimeUtils.FORMAT_DATE_TIME_SECOND_MILLISECOND), "0");
    }

    /**
     * 验证获取的分钟是否正确
     */
    @Test
    public void testGetMinuteRight() {
        Assert.assertEquals(TimeUtils.getMinute(Locale.CHINA, 1564416061001L), "1");
        Assert.assertEquals(TimeUtils.getMinute(Locale.CHINA, "2019-07-30 00:01:01.001",
                TimeUtils.FORMAT_DATE_TIME_SECOND_MILLISECOND), "1");
    }

    /**
     * 验证获取的秒是否正确
     */
    @Test
    public void testGetSecondRight() {
        Assert.assertEquals(TimeUtils.getSecond(Locale.CHINA, 1564416061001L), "1");
        Assert.assertEquals(TimeUtils.getSecond(Locale.CHINA, "2019-07-30 00:01:01.001",
                TimeUtils.FORMAT_DATE_TIME_SECOND_MILLISECOND), "1");
    }

    /**
     * 验证获取的毫秒是否正确
     */
    @Test
    public void testGetMillisecondRight() {
        Assert.assertEquals(TimeUtils.getMilliSecond(Locale.CHINA, 1564416061002L), "2");
        Assert.assertEquals(TimeUtils.getMilliSecond(Locale.CHINA, "2019-07-30 00:01:01.002",
                TimeUtils.FORMAT_DATE_TIME_SECOND_MILLISECOND), "2");
    }

    /**
     * 一个时间添加毫秒
     */
    @Test
    public void testPlusMillisecond() {
        Assert.assertEquals(TimeUtils.datePlusMilliSeconds(Locale.CHINA, "2019-07-30 00:00:00.002"
                , 22L), "2019-07-30 00:00:00.024");
        Assert.assertEquals(TimeUtils.datePlusMilliSeconds(Locale.CHINA, "2019-07-30 00:00:00.002"
                , 998L), "2019-07-30 00:00:01.000");
        Assert.assertEquals(TimeUtils.datePlusMilliSeconds(Locale.CHINA, "2019-07-30 00:00:00.002"
                , 2 * 24 * 60 * 60 * 1000 + 998L), "2019-08-01 00:00:01.000");
    }

    /**
     * 一个时间添加秒
     */
    @Test
    public void testPlusSecond() {
        Assert.assertEquals(TimeUtils.datePlusSeconds(Locale.CHINA, "2019-07-30 00:00:00.002",
                1), "2019-07-30 00:00:01.002");
        Assert.assertEquals(TimeUtils.datePlusSeconds(Locale.CHINA, "2019-07-30 00:00:00.002",
                60), "2019-07-30 00:01:00.002");
        Assert.assertEquals(TimeUtils.datePlusSeconds(Locale.CHINA, "2019-07-30 00:00:00.002",
                2 * 24 * 60 * 60), "2019-08-01 00:00:00.002");
    }

    /**
     * 添加分钟
     */
    @Test
    public void testPlusMinutes() {
        Assert.assertEquals(TimeUtils.datePlusMinutes(Locale.CHINA, "2019-07-30 00:00:00.002",
                1), "2019-07-30 00:01:00.002");
        Assert.assertEquals(TimeUtils.datePlusMinutes(Locale.CHINA, "2019-07-30 00:00:00.002",
                60), "2019-07-30 01:00:00.002");
        Assert.assertEquals(TimeUtils.datePlusMinutes(Locale.CHINA, "2019-07-30 00:00:00.002",
                2 * 24 * 60), "2019-08-01 00:00:00.002");
    }

    /**
     * 添加小时
     */
    @Test
    public void testPlusHours() {
        Assert.assertEquals(TimeUtils.datePlusHours(Locale.CHINA, "2019-07-30 00:00:00.002",
                1), "2019-07-30 01:00:00.002");
        Assert.assertEquals(TimeUtils.datePlusHours(Locale.CHINA, "2019-07-30 00:00:00.002",
                15), "2019-07-30 15:00:00.002");
        Assert.assertEquals(TimeUtils.datePlusHours(Locale.CHINA, "2019-07-30 00:00:00.002",
                2 * 24), "2019-08-01 00:00:00.002");
    }

    /**
     * 测试添加天数
     */
    @Test
    public void testPlusDays() {
        Assert.assertEquals(TimeUtils.datePlusDays(Locale.CHINA, "2019-07-30 00:00:00.002",
                TimeUtils.FORMAT_DATE_TIME_SECOND_MILLISECOND,
                1), "2019-07-31 00:00:00.002");
        Assert.assertEquals(TimeUtils.datePlusDays(Locale.CHINA, "2019-07-30 00:00:00.002",
                TimeUtils.FORMAT_DATE_TIME_SECOND_MILLISECOND,
                2), "2019-08-01 00:00:00.002");
        Assert.assertEquals(TimeUtils.datePlusDays(Locale.CHINA, "2019-07-30 00:00:00.002",
                TimeUtils.FORMAT_DATE_TIME_SECOND_MILLISECOND,
                365), "2020-07-29 00:00:00.002");
    }

    /**
     * 测试添加周数
     */
    @Test
    public void testPlusWeek() {
        Assert.assertEquals(TimeUtils.datePlusWeeks(Locale.CHINA, "2019-07-30 00:00:00.002",
                TimeUtils.FORMAT_DATE_TIME_SECOND_MILLISECOND,
                1), "2019-08-06 00:00:00.002");
        Assert.assertEquals(TimeUtils.datePlusWeeks(Locale.CHINA, "2019-07-30 00:10:00.002",
                TimeUtils.FORMAT_DATE_TIME_SECOND_MILLISECOND,
                4), "2019-08-27 00:10:00.002");
    }

    /**
     * 测试添加月份
     */
    @Test
    public void testPlusMonths() {
        Assert.assertEquals("2019-08-30 00:00:00.002",
                TimeUtils.datePlusMonths(Locale.CHINA,
                        "2019-07-30 00:00:00.002",
                        TimeUtils.FORMAT_DATE_TIME_SECOND_MILLISECOND,
                        1));
        Assert.assertEquals("2020-07-30 00:00:00.002",
                TimeUtils.datePlusMonths(Locale.CHINA,
                        "2019-07-30 00:00:00.002",
                        TimeUtils.FORMAT_DATE_TIME_SECOND_MILLISECOND,
                        12));
        Assert.assertEquals("2020-02-29 00:00:00.002",
                TimeUtils.datePlusMonths(Locale.CHINA,
                        "2019-07-30 00:00:00.002",
                        TimeUtils.FORMAT_DATE_TIME_SECOND_MILLISECOND,
                        7));
    }

    /**
     * 测试添加年份
     */
    @Test
    public void testPlusYears() {
        Assert.assertEquals("2020-08-30 00:00:00.002",
                TimeUtils.datePlusYears(Locale.CHINA,
                        "2019-08-30 00:00:00.002",
                        TimeUtils.FORMAT_DATE_TIME_SECOND_MILLISECOND,
                        1));
        Assert.assertEquals("2129-08-30 00:00:00.002",
                TimeUtils.datePlusYears(Locale.CHINA,
                        "2019-08-30 00:00:00.002",
                        TimeUtils.FORMAT_DATE_TIME_SECOND_MILLISECOND,
                        110));
    }

    /**
     * 测试减少毫秒
     */
    @Test
    public void testMinusMillisecond() {
        Assert.assertEquals(TimeUtils.dateMinusMilliSeconds(Locale.CHINA, "2019-07-30 00:00:00.022"
                , 22L), "2019-07-30 00:00:00.000");
        Assert.assertEquals(TimeUtils.dateMinusMilliSeconds(Locale.CHINA, "2019-07-30 00:00:00.022"
                , 23L), "2019-07-29 23:59:59.999");
    }

    @Test
    public void testMinusSecond() {
        Assert.assertEquals("2019-07-30 00:00:00.002", TimeUtils.dateMinusSeconds(Locale.CHINA,
                "2019-07-30 00:00:01.002",
                1));
        Assert.assertEquals("2019-07-29 23:59:59.002", TimeUtils.dateMinusSeconds(Locale.CHINA,
                "2019-07-30 00:00:01.002",
                2));
    }

    @Test
    public void testMinusMinutes() {
        Assert.assertEquals("2019-07-29 23:59:00.002", TimeUtils.dateMinusMinutes(Locale.CHINA,
                "2019-07-30 00:00:00.002",
                1));
        Assert.assertEquals("2019-07-29 23:00:00.002", TimeUtils.dateMinusMinutes(Locale.CHINA,
                "2019-07-30 00:00:00.002",
                60));
    }

    @Test
    public void testMinusHours() {
        Assert.assertEquals("2019-07-29 23:00:00.002", TimeUtils.dateMinusHours(Locale.CHINA,
                "2019-07-30 00:00:00.002",
                1));
        Assert.assertEquals("2019-07-29 00:00:00.002", TimeUtils.dateMinusHours(Locale.CHINA,
                "2019-07-30 00:00:00.002",
                24));
    }

    @Test
    public void testMinusDays() {
        Assert.assertEquals("2019-07-29 00:00:00.002", TimeUtils.dateMinusDays(Locale.CHINA,
                "2019-07-30 00:00:00.002",
                TimeUtils.FORMAT_DATE_TIME_SECOND_MILLISECOND,
                1));
    }

    @Test
    public void testMinusWeeks() {
        Assert.assertEquals("2019-07-23 00:00:00.002", TimeUtils.dateMinusWeeks(Locale.CHINA,
                "2019-07-30 00:00:00.002",
                TimeUtils.FORMAT_DATE_TIME_SECOND_MILLISECOND,
                1));
    }

    @Test
    public void testMinusMonths() {
        Assert.assertEquals("2019-06-30 00:00:00.002", TimeUtils.dateMinusMonths(Locale.CHINA,
                "2019-07-30 00:00:00.002",
                TimeUtils.FORMAT_DATE_TIME_SECOND_MILLISECOND,
                1));
    }

    @Test
    public void testMinusYears() {
        Assert.assertEquals("2018-07-30 00:00:00.002", TimeUtils.dateMinusYears(Locale.CHINA,
                "2019-07-30 00:00:00.002",
                TimeUtils.FORMAT_DATE_TIME_SECOND_MILLISECOND,
                1));
    }

    @Test
    public void testGetHoursBetweenTwoTime() {
        Assert.assertEquals(0L, TimeUtils.getHoursBetweenTwoTime(Locale.CHINA,
                "2019-07-30 00:00:00.002", "2019-07-30 00:00:00.002"));
        Assert.assertEquals(-1L, TimeUtils.getHoursBetweenTwoTime(Locale.CHINA,
                "2019-07-30 01:00:00.002", "2019-07-30 00:00:00.002"));
        Assert.assertEquals(-1L, TimeUtils.getHoursBetweenTwoTime(Locale.CHINA,
                "2019-07-30 00:00:00.002", "2019-07-29 23:00:00.002"));
        Assert.assertEquals(48L, TimeUtils.getHoursBetweenTwoTime(Locale.CHINA,
                "2019-07-28 00:00:00.002", "2019-07-30 00:00:00.002"));
        Assert.assertEquals(-34L, TimeUtils.getHoursBetweenTwoTime(Locale.CHINA,
                "2019-07-31 10:00:00.002", "2019-07-30 00:00:00.002"));
    }

    @Test
    public void testGetMinutesBetweenTwoTime() {
        Assert.assertEquals(0L, TimeUtils.getMinutesBetweenTwoTime(Locale.CHINA,
                "2019-07-30 00:00:00.000", "2019-07-30 00:00:00.000"));
        Assert.assertEquals(1L, TimeUtils.getMinutesBetweenTwoTime(Locale.CHINA,
                "2019-07-30 00:00:00.000", "2019-07-30 00:01:00.000"));
        Assert.assertEquals(-1L, TimeUtils.getMinutesBetweenTwoTime(Locale.CHINA,
                "2019-07-30 00:01:00.000", "2019-07-30 00:00:00.000"));
        Assert.assertEquals(-61L, TimeUtils.getMinutesBetweenTwoTime(Locale.CHINA,
                "2019-07-30 01:01:00.000", "2019-07-30 00:00:00.000"));
        Assert.assertEquals(59L, TimeUtils.getMinutesBetweenTwoTime(Locale.CHINA,
                "2019-07-30 00:00:00.000", "2019-07-30 00:59:00.000"));
        Assert.assertEquals(59L + 60L, TimeUtils.getMinutesBetweenTwoTime(Locale.CHINA,
                "2019-07-30 00:00:00.000", "2019-07-30 01:59:00.000"));
        Assert.assertEquals(24L * 60, TimeUtils.getMinutesBetweenTwoTime(Locale.CHINA,
                "2020-02-28 00:00:00.000", "2020-02-29 00:00:00.000"));
    }

    @Test
    public void testGetSecondsBetweenTwoTime() {
        Assert.assertEquals(0L, TimeUtils.getSecondsBetweenTwoTime(Locale.CHINA,
                "2019-07-30 00:00:00.000", "2019-07-30 00:00:00.000"));
        Assert.assertEquals(-1L, TimeUtils.getSecondsBetweenTwoTime(Locale.CHINA,
                "2019-07-30 00:00:01.000", "2019-07-30 00:00:00.000"));
        Assert.assertEquals(-1L, TimeUtils.getSecondsBetweenTwoTime(Locale.CHINA,
                "2019-07-30 00:00:00.000", "2019-07-29 23:59:59.000"));
        Assert.assertEquals(1L, TimeUtils.getSecondsBetweenTwoTime(Locale.CHINA,
                "2019-07-30 00:00:00.000", "2019-07-30 00:00:01.000"));
        Assert.assertEquals(61L, TimeUtils.getSecondsBetweenTwoTime(Locale.CHINA,
                "2019-07-30 00:00:00.000", "2019-07-30 00:01:01.000"));
        Assert.assertEquals(3600 + 61L, TimeUtils.getSecondsBetweenTwoTime(Locale.CHINA,
                "2019-07-30 00:00:00.000", "2019-07-30 01:01:01.000"));
    }

    @Test
    public void testGetMillisecondsBetweenTwoTime() {
        Assert.assertEquals(0L, TimeUtils.getMillisecondsBetweenTwoTime(Locale.CHINA,
                "2019-07-30 00:00:00.000", "2019-07-30 00:00:00.000"));
        Assert.assertEquals(1L, TimeUtils.getMillisecondsBetweenTwoTime(Locale.CHINA,
                "2019-07-30 00:00:00.000", "2019-07-30 00:00:00.001"));
        Assert.assertEquals(-1L, TimeUtils.getMillisecondsBetweenTwoTime(Locale.CHINA,
                "2019-07-30 00:00:00.000", "2019-07-29 23:59:59.999"));
    }

    @Test
    public void testGetDaysBetweenTwoTime() {
        Assert.assertEquals(0L, TimeUtils.getDaysBetweenTwoTime(Locale.CHINA,
                TimeUtils.FORMAT_DATE_TIME_SECOND_MILLISECOND,
                "2019-07-30 00:00:00.000", "2019-07-30 00:00:00.000"));
        Assert.assertEquals(1L, TimeUtils.getDaysBetweenTwoTime(Locale.CHINA,
                TimeUtils.FORMAT_DATE_TIME_SECOND_MILLISECOND,
                "2019-07-30 00:00:00.000", "2019-07-31 00:00:00.000"));
        Assert.assertEquals(2L, TimeUtils.getDaysBetweenTwoTime(Locale.CHINA,
                TimeUtils.FORMAT_DATE_TIME_SECOND_MILLISECOND,
                "2019-07-30 00:00:00.000", "2019-08-01 00:00:00.000"));
        Assert.assertEquals(-1L, TimeUtils.getDaysBetweenTwoTime(Locale.CHINA,
                TimeUtils.FORMAT_DATE_TIME_SECOND_MILLISECOND,
                "2019-07-30 00:00:00.000", "2019-07-29 00:00:00.000"));
    }

    @Test
    public void testGetWeeksBetweenTwoTime() {
        Assert.assertEquals(0L, TimeUtils.getWeeksBetweenTwoTime(Locale.CHINA,
                TimeUtils.FORMAT_DATE_TIME_SECOND_MILLISECOND,
                "2019-07-30 00:00:00.000", "2019-07-30 00:00:00.000"));
        Assert.assertEquals(0L, TimeUtils.getWeeksBetweenTwoTime(Locale.CHINA,
                TimeUtils.FORMAT_DATE_TIME_SECOND_MILLISECOND,
                "2019-07-30 00:00:00.000", "2019-07-31 00:00:00.000"));
        Assert.assertEquals(0L, TimeUtils.getWeeksBetweenTwoTime(Locale.CHINA,
                TimeUtils.FORMAT_DATE_TIME_SECOND_MILLISECOND,
                "2019-07-30 00:00:00.000", "2019-07-26 00:00:00.000"));
        Assert.assertEquals(1L, TimeUtils.getWeeksBetweenTwoTime(Locale.CHINA,
                TimeUtils.FORMAT_DATE_TIME_SECOND_MILLISECOND,
                "2019-07-30 00:00:00.000", "2019-08-07 00:00:00.000"));
        Assert.assertEquals(-1L, TimeUtils.getWeeksBetweenTwoTime(Locale.CHINA,
                TimeUtils.FORMAT_DATE_TIME_SECOND_MILLISECOND,
                "2019-07-30 00:00:00.000", "2019-07-22 00:00:00.000"));
    }

    @Test
    public void testGetMonthsBetweenTwoTime() {
        Assert.assertEquals(0L, TimeUtils.getMonthsBetweenTwoTime(Locale.CHINA,
                TimeUtils.FORMAT_DATE_TIME_SECOND_MILLISECOND,
                "2019-07-30 00:00:00.000", "2019-07-30 00:00:00.000"));
        Assert.assertEquals(0L, TimeUtils.getMonthsBetweenTwoTime(Locale.CHINA,
                TimeUtils.FORMAT_DATE_TIME_SECOND_MILLISECOND,
                "2019-07-30 00:00:00.000", "2019-07-31 00:00:00.000"));
        Assert.assertEquals(1L, TimeUtils.getMonthsBetweenTwoTime(Locale.CHINA,
                TimeUtils.FORMAT_DATE_TIME_SECOND_MILLISECOND,
                "2019-07-31 00:00:00.000", "2019-08-31 00:00:00.000"));
        Assert.assertEquals(1L, TimeUtils.getMonthsBetweenTwoTime(Locale.CHINA,
                TimeUtils.FORMAT_DATE_TIME_SECOND_MILLISECOND,
                "2019-07-30 00:00:00.000", "2019-08-30 00:00:00.000"));
        Assert.assertEquals(-1L, TimeUtils.getMonthsBetweenTwoTime(Locale.CHINA,
                TimeUtils.FORMAT_DATE_TIME_SECOND_MILLISECOND,
                "2019-07-30 00:00:00.000", "2019-06-30 00:00:00.000"));
        Assert.assertEquals(-1L, TimeUtils.getMonthsBetweenTwoTime(Locale.CHINA,
                TimeUtils.FORMAT_DATE_TIME_SECOND_MILLISECOND,
                "2019-07-30 00:00:00.000", "2019-06-30 00:00:00.000"));
    }

    @Test
    public void testGetYearsBetweenTwoTime() {
        Assert.assertEquals(0L, TimeUtils.getYearsBetweenTwoTime(Locale.CHINA,
                TimeUtils.FORMAT_DATE_TIME_SECOND_MILLISECOND,
                "2019-07-30 00:00:00.000", "2019-07-30 00:00:00.000"));
        Assert.assertEquals(1L, TimeUtils.getYearsBetweenTwoTime(Locale.CHINA,
                TimeUtils.FORMAT_DATE_TIME_SECOND_MILLISECOND,
                "2018-07-30 00:00:00.000", "2019-07-30 00:00:00.000"));
        Assert.assertEquals(-1L, TimeUtils.getYearsBetweenTwoTime(Locale.CHINA,
                TimeUtils.FORMAT_DATE_TIME_SECOND_MILLISECOND,
                "2020-07-30 00:00:00.000", "2019-07-30 00:00:00.000"));
    }
}