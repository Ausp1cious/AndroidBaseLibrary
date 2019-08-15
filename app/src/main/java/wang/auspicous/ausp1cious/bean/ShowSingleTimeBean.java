package wang.auspicous.ausp1cious.bean;

import wang.auspicous.ausp1ciouslib.component.bean.BaseBean;

/**
 * 显示时间类
 */
public class ShowSingleTimeBean extends BaseBean {
    //显示时间（数字）
    private String time;
    //显示时间的单位
    private String timeUnit;

    private boolean isOverTime = false;


    public ShowSingleTimeBean(String time, String timeUnit) {
        this.time = time;
        this.timeUnit = timeUnit;
    }

    public ShowSingleTimeBean(String time, String timeUnit, boolean isOverTime) {
        this.time = time;
        this.timeUnit = timeUnit;
        this.isOverTime = isOverTime;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTimeUnit() {
        return timeUnit;
    }

    public void setTimeUnit(String timeUnit) {
        this.timeUnit = timeUnit;
    }

    public boolean isOverTime() {
        return isOverTime;
    }

    public void setOverTime(boolean overTime) {
        isOverTime = overTime;
    }
}
