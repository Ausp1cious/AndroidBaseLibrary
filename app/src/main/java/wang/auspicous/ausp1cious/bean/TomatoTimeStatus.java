package wang.auspicous.ausp1cious.bean;

import com.orhanobut.logger.Logger;

import java.math.BigDecimal;

import wang.auspicous.ausp1cious.utils.AppSpUtils;
import wang.auspicous.ausp1cious.utils.AppTimeUtils;
import wang.auspicous.ausp1ciouslib.component.bean.BaseBean;

public class TomatoTimeStatus extends BaseBean {
    public static final int STATUS_PREPARE = 0;
    public static final int STATUS_TOMATO_TIME = 1;
    public static final int STATUS_SUMMARIZE = 2;
    public static final int STATUS_OTHERS = 3;
    public static final int TIME_UNIT_SECONDS = 0;
    public static final int TIME_UNIT_MINUTES = 1;
    //0-准备阶段，1-番茄时间阶段，2-总结阶段，3-其他阶段
    private int status = 0;
    //剩余时间
    private long restTime = AppSpUtils.getTomatoTimeConfiguration().getUnitTime();
    //剩余时间单位：0-Seconds 1-minutes
    private int restTimeUnit = 1;
    //单位时间
    private long totalTime = AppSpUtils.getTomatoTimeConfiguration().getUnitTime();
    //剩余百分比
    private float restRate = 0;

    public long getRestTime() {
        return restTime;
    }

    public int getRestTimeUnit() {
        return restTimeUnit;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setRestTime(long restTime) {
        this.restTime = restTime;
    }

    public void setRestTimeUnit(int restTimeUnit) {
        this.restTimeUnit = restTimeUnit;
    }

    public void setTotalTime(long totalTime) {
        this.totalTime = totalTime;
    }

    public void setRestRate(float restRate) {
        this.restRate = restRate;
    }

    public float getRestRate() {
        return restRate;
    }
}
