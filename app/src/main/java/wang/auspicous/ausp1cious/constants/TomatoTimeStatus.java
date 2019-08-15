package wang.auspicous.ausp1cious.constants;

public interface TomatoTimeStatus {
    //准备阶段
    public int TOMATO_STATUS_PREPARE = 0;
    //番茄时间阶段
    public int TOMATO_STATUS_TOMATO_TIME = 1;
    //总结阶段
    public int TOMATO_STATUS_SUMMARIZE = 2;
    //正常结束
    public int TOMATO_STATUS_ENDED = 3;
    //提前结束
    public int TOMATO_STATUS_PRE_ENDED = 4;
    //外在中断
    public int TOMATO_STATUS_OUTSIDE_INTERRUPT = 5;
    //内在中断
    public int TOMATO_STATUS_INSIDE_INTERRUPT = 6;
    //超时结束
    public int TOMATO_STATUS_OVER_TIME = 7;
    //遗忘
    public int TOMATO_STATUS_FORGET = 8;
}
