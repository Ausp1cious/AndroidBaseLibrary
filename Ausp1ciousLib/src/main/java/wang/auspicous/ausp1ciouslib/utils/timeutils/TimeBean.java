package wang.auspicous.ausp1ciouslib.utils.timeutils;

import java.util.Locale;

import wang.auspicous.ausp1ciouslib.component.BaseBean;

/**
 * Created by Ausp1cious on 2019/3/27.
 * 时间类
 */
public class TimeBean extends BaseBean {
    //0-23
    private final byte hour;
    //0-59
    private final byte minute;
    //0-59
    private final byte second;
    //0-999,999,999
    private final int nano;

    private TimeBean(int hour, int minute, int second, int nanoOfSecond) {
        this.hour = (byte) hour;
        this.minute = (byte) minute;
        this.second = (byte) second;
        this.nano = nanoOfSecond;
    }


    public static TimeBean of(int hour, int minute, int second, int nanoOfSecond) {
        return new TimeBean(hour, minute, second, nanoOfSecond);
    }

    public static TimeBean of(int hour, int minute, int second) {
        return new TimeBean(hour, minute, second, 0);
    }

    public static TimeBean of(int hour, int minute) {
        return new TimeBean(hour, minute, 0, 0);
    }

    public static TimeBean of(Locale locale,long timestamp) {
        int hour = Integer.parseInt(TimeUtils.getHour(locale, timestamp));
        int minute = Integer.parseInt(TimeUtils.getMinute(locale, timestamp));
        int second = Integer.parseInt(TimeUtils.getSecond(locale, timestamp));
        int millisecond = Integer.valueOf(TimeUtils.getMilliSecond(locale, timestamp))*1000000;
        return new TimeBean(hour, minute, second, millisecond);
    }

    //两个时间的差值

    //给一个时间添加时间
    //给一个时间减少时间


    public String getTime() {
        return hour + ":" + minute + ":" + second + "." + nano;
    }

}
