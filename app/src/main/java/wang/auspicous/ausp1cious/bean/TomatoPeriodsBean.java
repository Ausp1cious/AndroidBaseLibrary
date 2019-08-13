package wang.auspicous.ausp1cious.bean;

import wang.auspicous.ausp1ciouslib.component.bean.BaseBean;

public class TomatoPeriodsBean extends BaseBean {
    private int planTime = 1;
    private int conductedTime =0;
    private int overTime = 0;

    /**
     * 是否存在超时
     */
    public boolean isOverTime() {
        return conductedTime > planTime;
    }

    public TomatoPeriodsBean() {
    }

    public TomatoPeriodsBean(int planTime, int conductedTime) {
        this.planTime = planTime;
        this.conductedTime = conductedTime;
    }

    public TomatoPeriodsBean(int planTime, int conductedTime, int overTime) {
        this.planTime = planTime;
        this.conductedTime = conductedTime;
        this.overTime = overTime;
    }

    public int getPlanTime() {
        return planTime;
    }

    public void setPlanTime(int planTime) {
        this.planTime = planTime;
    }

    public int getConductedTime() {
        return conductedTime;
    }

    public void setConductedTime(int conductedTime) {
        this.conductedTime = conductedTime;
    }

    public int getOverTime() {
        if (isOverTime()) {
            return conductedTime - planTime;
        }
        return 0;
    }
}
