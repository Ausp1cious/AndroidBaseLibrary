package wang.auspicous.ausp1cious.utils;

import java.math.BigDecimal;

import wang.auspicous.ausp1cious.bean.ShowSingleTimeBean;
import wang.auspicous.ausp1cious.bean.TomatoSettingBean;
import wang.auspicous.ausp1cious.bean.TomatoTimeStatusBean;
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

    public static void getRestTime(long startTime, TomatoTimeStatusBean tomatoTimeStatusBean) {
        TomatoSettingBean tomatoTimeConfiguration = AppSpUtils.getTomatoTimeConfiguration();
        long preTime = tomatoTimeConfiguration.getPlanTime() + startTime;
        long tomatoTime =
                startTime + tomatoTimeConfiguration.getPlanTime() + tomatoTimeConfiguration.getUnitTime();
        long summarizeTime =
                startTime + tomatoTimeConfiguration.getPlanTime() + tomatoTimeConfiguration.getUnitTime() + tomatoTimeConfiguration.getSummarizeTime();
        if (AppTimeUtils.getCurrentTimeAsLong() < preTime) {//准备状态
            tomatoTimeStatusBean.setRestRate(1 - new BigDecimal(preTime - AppTimeUtils.getCurrentTimeAsLong()).divide(new BigDecimal(tomatoTimeConfiguration.getPlanTime()), 6, BigDecimal.ROUND_DOWN).floatValue());
            long minutesBetweenTwoTime =
                    AppTimeUtils.getMinutesBetweenTwoTime(AppTimeUtils.getCurrentTimeAsLong(),
                            preTime);
            if (minutesBetweenTwoTime > 1) {
                tomatoTimeStatusBean.setRestTime(minutesBetweenTwoTime);
                tomatoTimeStatusBean.setRestTimeUnit(TomatoTimeStatusBean.TIME_UNIT_MINUTES);
                tomatoTimeStatusBean.setStatus(TomatoTimeStatusBean.STATUS_PREPARE);
            } else {
                long secondsBetweenTwoTime =
                        AppTimeUtils.getSecondsBetweenTwoTime(AppTimeUtils.getCurrentTimeAsLong(),
                                preTime);
                tomatoTimeStatusBean.setRestTime(secondsBetweenTwoTime);
                tomatoTimeStatusBean.setRestTimeUnit(TomatoTimeStatusBean.TIME_UNIT_SECONDS);
                tomatoTimeStatusBean.setStatus(TomatoTimeStatusBean.STATUS_PREPARE);
            }
        } else if (AppTimeUtils.getCurrentTimeAsLong() < tomatoTime) {//番茄工作法状态
            tomatoTimeStatusBean.setRestRate(1 - new BigDecimal(tomatoTime - AppTimeUtils.getCurrentTimeAsLong()).divide(new BigDecimal(tomatoTimeConfiguration.getUnitTime()), 6, BigDecimal.ROUND_DOWN).floatValue());
            long minutesBetweenTwoTime =
                    AppTimeUtils.getMinutesBetweenTwoTime(AppTimeUtils.getCurrentTimeAsLong(),
                            tomatoTime);
            if (minutesBetweenTwoTime > 1) {
                tomatoTimeStatusBean.setRestTime(minutesBetweenTwoTime);
                tomatoTimeStatusBean.setRestTimeUnit(TomatoTimeStatusBean.TIME_UNIT_MINUTES);
                tomatoTimeStatusBean.setStatus(TomatoTimeStatusBean.STATUS_TOMATO_TIME);
            } else {
                long secondsBetweenTwoTime =
                        AppTimeUtils.getSecondsBetweenTwoTime(AppTimeUtils.getCurrentTimeAsLong(),
                                tomatoTime);
                tomatoTimeStatusBean.setRestTime(secondsBetweenTwoTime);
                tomatoTimeStatusBean.setRestTimeUnit(TomatoTimeStatusBean.TIME_UNIT_SECONDS);
                tomatoTimeStatusBean.setStatus(TomatoTimeStatusBean.STATUS_TOMATO_TIME);
            }
        }

//        else if (AppTimeUtils.getCurrentTimeAsLong() < summarizeTime) {    //总结状态
//            tomatoTimeStatusBean.setRestRate(1 - new BigDecimal(summarizeTime - AppTimeUtils
//            .getCurrentTimeAsLong()).divide(new BigDecimal(tomatoTimeConfiguration
//            .getSummarizeTime()), 6, BigDecimal.ROUND_DOWN).floatValue());
//            long minutesBetweenTwoTime =
//                    AppTimeUtils.getMinutesBetweenTwoTime(AppTimeUtils.getCurrentTimeAsLong(),
//                            summarizeTime);
//            if (minutesBetweenTwoTime > 1) {
//                tomatoTimeStatusBean.setRestTime(minutesBetweenTwoTime);
//                tomatoTimeStatusBean.setRestTimeUnit(TomatoTimeStatusBean.TIME_UNIT_MINUTES);
//                tomatoTimeStatusBean.setStatus(TomatoTimeStatusBean.STATUS_SUMMARIZE);
//            } else {
//                long secondsBetweenTwoTime =
//                        AppTimeUtils.getSecondsBetweenTwoTime(AppTimeUtils.getCurrentTimeAsLong(),
//                                summarizeTime);
//                tomatoTimeStatusBean.setRestTime(secondsBetweenTwoTime);
//                tomatoTimeStatusBean.setRestTimeUnit(TomatoTimeStatusBean.TIME_UNIT_MINUTES);
//                tomatoTimeStatusBean.setStatus(TomatoTimeStatusBean.STATUS_SUMMARIZE);
//            }
//        }

        else {
            tomatoTimeStatusBean.setRestRate(1);
            tomatoTimeStatusBean.setRestTime(-1);
            tomatoTimeStatusBean.setRestTimeUnit(TomatoTimeStatusBean.TIME_UNIT_MINUTES);
            tomatoTimeStatusBean.setStatus(TomatoTimeStatusBean.STATUS_OTHERS);
        }
    }

    /**
     * 计算剩余的时间
     */
    public static ShowSingleTimeBean getRestTime(long startTime,long duringTime) {
        String time;
        String timeUnit;
        boolean isOverTime = false;
        long endTime = startTime + duringTime;
        if (AppTimeUtils.getCurrentTimeAsLong() < endTime) { //在时间范围内
            long minutesBetweenTwoTime =
                    AppTimeUtils.getMinutesBetweenTwoTime(AppTimeUtils.getCurrentTimeAsLong(),
                            endTime);
            if (minutesBetweenTwoTime > 1) {
                time = minutesBetweenTwoTime + "";
                timeUnit = "minutes";
            } else {
                long secondsBetweenTwoTime =
                        AppTimeUtils.getSecondsBetweenTwoTime(AppTimeUtils.getCurrentTimeAsLong(),
                                endTime);
                time = secondsBetweenTwoTime + "";
                timeUnit = "seconds";
            }
        }else {
            long minutesBetweenTwoTime = AppTimeUtils.getMinutesBetweenTwoTime(startTime,
                    AppTimeUtils.getCurrentTimeAsLong());
            time = minutesBetweenTwoTime + "";
            timeUnit = "minutes";
            isOverTime = true;
        }
        return new ShowSingleTimeBean(time, timeUnit, isOverTime);
    }

    public static float getRestRate(long startTime, long duringTime) {
        if (duringTime == 0) {
            return 1;
        }
        return (AppTimeUtils.getCurrentTimeAsLong() - startTime)*1.0f / duringTime;
    }
}

