package wang.auspicous.ausp1cious.utils;

import com.orhanobut.logger.Logger;

import wang.auspicous.ausp1cious.bean.TomatoSettingBean;
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

    public static String getRestTime(long startTime) {
        TomatoSettingBean tomatoTimeConfiguration = AppSpUtils.getTomatoTimeConfiguration();
        long preTime = tomatoTimeConfiguration.getPlanTime() + startTime;
        long tomatoTime =
                startTime + tomatoTimeConfiguration.getPlanTime() + tomatoTimeConfiguration.getUnitTime();
        long summarizeTime =
                startTime + tomatoTimeConfiguration.getPlanTime() + tomatoTimeConfiguration.getUnitTime() + tomatoTimeConfiguration.getSummarizeTime();
        if (AppTimeUtils.getCurrentTimeAsLong() < preTime) {//准备状态
            long minutesBetweenTwoTime =
                    AppTimeUtils.getMinutesBetweenTwoTime(AppTimeUtils.getCurrentTimeAsLong(),
                            preTime);
            if (minutesBetweenTwoTime != 1) {
                return "准备状态：" + minutesBetweenTwoTime + "min";
            } else {
                long secondsBetweenTwoTime =
                        AppTimeUtils.getSecondsBetweenTwoTime(AppTimeUtils.getCurrentTimeAsLong(),
                                preTime);
                return "准备状态：" + secondsBetweenTwoTime + "s";
            }
        } else if (AppTimeUtils.getCurrentTimeAsLong() < tomatoTime) {//番茄工作法状态
            long minutesBetweenTwoTime =
                    AppTimeUtils.getMinutesBetweenTwoTime(AppTimeUtils.getCurrentTimeAsLong(),
                            tomatoTime);
            if (minutesBetweenTwoTime != 1) {
                return "番茄时间状态：" + minutesBetweenTwoTime + "min";
            } else {
                long secondsBetweenTwoTime =
                        AppTimeUtils.getSecondsBetweenTwoTime(AppTimeUtils.getCurrentTimeAsLong(),
                                tomatoTime);
                return "番茄时间状态：" + secondsBetweenTwoTime + "s";
            }
        } else if (AppTimeUtils.getCurrentTimeAsLong() < summarizeTime) {    //总结状态
            long minutesBetweenTwoTime =
                    AppTimeUtils.getMinutesBetweenTwoTime(AppTimeUtils.getCurrentTimeAsLong(),
                            summarizeTime);
            if (minutesBetweenTwoTime != 1) {
                return "总结状态：" + minutesBetweenTwoTime + "min";
            } else {
                long secondsBetweenTwoTime =
                        AppTimeUtils.getSecondsBetweenTwoTime(AppTimeUtils.getCurrentTimeAsLong(),
                                summarizeTime);
                return "总结状态：" + secondsBetweenTwoTime + "s";
            }
        } else {
            return "其他状态";
        }
    }
}

