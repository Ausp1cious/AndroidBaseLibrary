package wang.auspicous.ausp1cious.utils;

import com.orhanobut.logger.Logger;

import java.math.BigDecimal;

import wang.auspicous.ausp1cious.bean.TomatoSettingBean;
import wang.auspicous.ausp1cious.bean.TomatoTimeStatus;
import wang.auspicous.ausp1ciouslib.utils.timeutils.TimeUtils;

/**
 * 番茄时间计算工具类
 */
public class TomatoTimeUtils {

    /**
     * 返回预测结束时间
     */
    public static long getForecastEndTimeAsLong(long initTime) {
        TomatoSettingBean tomatoTimeConfiguration = AppSpUtils.getTomatoTimeConfiguration();
        return initTime + tomatoTimeConfiguration.getPlanTime() + tomatoTimeConfiguration.getUnitTime();
    }

    public static String getForecastEndTime(long initTime) {
        long forecastEndTime = getForecastEndTimeAsLong(initTime);
        return AppTimeUtils.getTime(TimeUtils.FORMAT_DATE_TIME_SECOND, forecastEndTime);
    }

    public static void getRestTime(long startTime, TomatoTimeStatus tomatoTimeStatus) {
        TomatoSettingBean tomatoTimeConfiguration = AppSpUtils.getTomatoTimeConfiguration();
        long preTime = tomatoTimeConfiguration.getPlanTime() + startTime;
        long tomatoTime =
                startTime + tomatoTimeConfiguration.getPlanTime() + tomatoTimeConfiguration.getUnitTime();
        long summarizeTime =
                startTime + tomatoTimeConfiguration.getPlanTime() + tomatoTimeConfiguration.getUnitTime() + tomatoTimeConfiguration.getSummarizeTime();
        if (AppTimeUtils.getCurrentTimeAsLong() < preTime) {//准备状态
            tomatoTimeStatus.setRestRate(1-new BigDecimal(preTime-AppTimeUtils.getCurrentTimeAsLong()).divide(new BigDecimal(tomatoTimeConfiguration.getPlanTime()), 6, BigDecimal.ROUND_DOWN).floatValue());
            long minutesBetweenTwoTime =
                    AppTimeUtils.getMinutesBetweenTwoTime(AppTimeUtils.getCurrentTimeAsLong(),
                            preTime);
            if (minutesBetweenTwoTime != 1) {
                tomatoTimeStatus.setRestTime(minutesBetweenTwoTime);
                tomatoTimeStatus.setRestTimeUnit(TomatoTimeStatus.TIME_UNIT_MINUTES);
                tomatoTimeStatus.setStatus(TomatoTimeStatus.STATUS_PREPARE);
            } else {
                long secondsBetweenTwoTime =
                        AppTimeUtils.getSecondsBetweenTwoTime(AppTimeUtils.getCurrentTimeAsLong(),
                                preTime);
                tomatoTimeStatus.setRestTime(secondsBetweenTwoTime);
                tomatoTimeStatus.setRestTimeUnit(TomatoTimeStatus.TIME_UNIT_SECONDS);
                tomatoTimeStatus.setStatus(TomatoTimeStatus.STATUS_PREPARE);
            }
        } else if (AppTimeUtils.getCurrentTimeAsLong() < tomatoTime) {//番茄工作法状态
            tomatoTimeStatus.setRestRate(1-new BigDecimal(tomatoTime-AppTimeUtils.getCurrentTimeAsLong()).divide(new BigDecimal(tomatoTimeConfiguration.getUnitTime()), 6, BigDecimal.ROUND_DOWN).floatValue());
            long minutesBetweenTwoTime =
                    AppTimeUtils.getMinutesBetweenTwoTime(AppTimeUtils.getCurrentTimeAsLong(),
                            tomatoTime);
            if (minutesBetweenTwoTime != 1) {
                tomatoTimeStatus.setRestTime(minutesBetweenTwoTime);
                tomatoTimeStatus.setRestTimeUnit(TomatoTimeStatus.TIME_UNIT_MINUTES);
                tomatoTimeStatus.setStatus(TomatoTimeStatus.STATUS_TOMATO_TIME);
            } else {
                long secondsBetweenTwoTime =
                        AppTimeUtils.getSecondsBetweenTwoTime(AppTimeUtils.getCurrentTimeAsLong(),
                                tomatoTime);
                tomatoTimeStatus.setRestTime(secondsBetweenTwoTime);
                tomatoTimeStatus.setRestTimeUnit(TomatoTimeStatus.TIME_UNIT_SECONDS);
                tomatoTimeStatus.setStatus(TomatoTimeStatus.STATUS_TOMATO_TIME);
            }
        } else if (AppTimeUtils.getCurrentTimeAsLong() < summarizeTime) {    //总结状态
            tomatoTimeStatus.setRestRate(1-new BigDecimal(summarizeTime-AppTimeUtils.getCurrentTimeAsLong()).divide(new BigDecimal(tomatoTimeConfiguration.getSummarizeTime()), 6, BigDecimal.ROUND_DOWN).floatValue());
            long minutesBetweenTwoTime =
                    AppTimeUtils.getMinutesBetweenTwoTime(AppTimeUtils.getCurrentTimeAsLong(),
                            summarizeTime);
            if (minutesBetweenTwoTime != 1) {
                tomatoTimeStatus.setRestTime(minutesBetweenTwoTime);
                tomatoTimeStatus.setRestTimeUnit(TomatoTimeStatus.TIME_UNIT_MINUTES);
                tomatoTimeStatus.setStatus(TomatoTimeStatus.STATUS_SUMMARIZE);
            } else {
                long secondsBetweenTwoTime =
                        AppTimeUtils.getSecondsBetweenTwoTime(AppTimeUtils.getCurrentTimeAsLong(),
                                summarizeTime);
                tomatoTimeStatus.setRestTime(secondsBetweenTwoTime);
                tomatoTimeStatus.setRestTimeUnit(TomatoTimeStatus.TIME_UNIT_MINUTES);
                tomatoTimeStatus.setStatus(TomatoTimeStatus.STATUS_SUMMARIZE);
            }
        } else {
            tomatoTimeStatus.setRestRate(1);
            tomatoTimeStatus.setRestTime(-1);
            tomatoTimeStatus.setRestTimeUnit(TomatoTimeStatus.TIME_UNIT_MINUTES);
            tomatoTimeStatus.setStatus(TomatoTimeStatus.STATUS_OTHERS);
        }
    }
}

