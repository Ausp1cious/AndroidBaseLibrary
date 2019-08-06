package wang.auspicous.ausp1cious.bean;

import wang.auspicous.ausp1ciouslib.component.bean.BaseBean;

public class TomatoSettingBean extends BaseBean {
    //单位时间(ms)
    private int unitTime;
    private int shortRestTime;
    private int longRestTime;
    private int periodTime;
    private int planTime;
    private int summarizeTime;

    public int getUnitTime() {
        return unitTime;
    }

    public void setUnitTime(int unitTime) {
        this.unitTime = unitTime;
    }

    public int getShortRestTime() {
        return shortRestTime;
    }

    public void setShortRestTime(int shortRestTime) {
        this.shortRestTime = shortRestTime;
    }

    public int getLongRestTime() {
        return longRestTime;
    }

    public void setLongRestTime(int longRestTime) {
        this.longRestTime = longRestTime;
    }

    public int getPeriodTime() {
        return periodTime;
    }

    public void setPeriodTime(int periodTime) {
        this.periodTime = periodTime;
    }

    public int getPlanTime() {
        return planTime;
    }

    public void setPlanTime(int planTime) {
        this.planTime = planTime;
    }

    public int getSummarizeTime() {
        return summarizeTime;
    }

    public void setSummarizeTime(int summarizeTime) {
        this.summarizeTime = summarizeTime;
    }
}
