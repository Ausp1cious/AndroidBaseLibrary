package wang.auspicous.ausp1cious.dao.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;

import wang.auspicous.ausp1ciouslib.component.bean.BaseBean;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class TomatoTimeEntity extends BaseBean {
    @Id
    private Long tomatoTimeId;
    @Unique
    private Long taskId;
    //开始时间
    private Long tomatoTimeStart;
    //准备状态开始时间
    private Long tomatoTimePrepareStarted;
    //准备状态结束时间
    private Long tomatoTimePrepareEnded;
    //番茄时间开始时间
    private Long tomatoTimeStarted;
    //番茄时间预计结束时间
    private Long tomatoTimeForecastEnd;
    //番茄时间真正结束的时间
    private Long tomatoTimeEnded;
    //总结时间开始时间
    private Long tomatoTimeSummarizeStarted;
    //总结时间结束时间
    private Long tomatoTimeSummarizeEnded;
    //结束时间
    private Long tomatoTimeEnd;
    private int planTime;
    //番茄时间
    private int tomatoTime;
    //总结时间
    private int summarizeTime;
    //0:准备阶段；1:番茄时间阶段；2:总结阶段；3:正常结束；4:提前结束； 5:外在中断； 6:内在中断； 7:超时结束 8: 遗忘
    private int tomatoTimeStatus;
    @Generated(hash = 14132592)
    public TomatoTimeEntity(Long tomatoTimeId, Long taskId, Long tomatoTimeStart,
            Long tomatoTimePrepareStarted, Long tomatoTimePrepareEnded,
            Long tomatoTimeStarted, Long tomatoTimeForecastEnd,
            Long tomatoTimeEnded, Long tomatoTimeSummarizeStarted,
            Long tomatoTimeSummarizeEnded, Long tomatoTimeEnd, int planTime,
            int tomatoTime, int summarizeTime, int tomatoTimeStatus) {
        this.tomatoTimeId = tomatoTimeId;
        this.taskId = taskId;
        this.tomatoTimeStart = tomatoTimeStart;
        this.tomatoTimePrepareStarted = tomatoTimePrepareStarted;
        this.tomatoTimePrepareEnded = tomatoTimePrepareEnded;
        this.tomatoTimeStarted = tomatoTimeStarted;
        this.tomatoTimeForecastEnd = tomatoTimeForecastEnd;
        this.tomatoTimeEnded = tomatoTimeEnded;
        this.tomatoTimeSummarizeStarted = tomatoTimeSummarizeStarted;
        this.tomatoTimeSummarizeEnded = tomatoTimeSummarizeEnded;
        this.tomatoTimeEnd = tomatoTimeEnd;
        this.planTime = planTime;
        this.tomatoTime = tomatoTime;
        this.summarizeTime = summarizeTime;
        this.tomatoTimeStatus = tomatoTimeStatus;
    }
    @Generated(hash = 1376024784)
    public TomatoTimeEntity() {
    }
    public Long getTomatoTimeId() {
        return this.tomatoTimeId;
    }
    public void setTomatoTimeId(Long tomatoTimeId) {
        this.tomatoTimeId = tomatoTimeId;
    }
    public Long getTaskId() {
        return this.taskId;
    }
    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }
    public Long getTomatoTimeStart() {
        return this.tomatoTimeStart;
    }
    public void setTomatoTimeStart(Long tomatoTimeStart) {
        this.tomatoTimeStart = tomatoTimeStart;
    }
    public Long getTomatoTimePrepareStarted() {
        return this.tomatoTimePrepareStarted;
    }
    public void setTomatoTimePrepareStarted(Long tomatoTimePrepareStarted) {
        this.tomatoTimePrepareStarted = tomatoTimePrepareStarted;
    }
    public Long getTomatoTimePrepareEnded() {
        return this.tomatoTimePrepareEnded;
    }
    public void setTomatoTimePrepareEnded(Long tomatoTimePrepareEnded) {
        this.tomatoTimePrepareEnded = tomatoTimePrepareEnded;
    }
    public Long getTomatoTimeStarted() {
        return this.tomatoTimeStarted;
    }
    public void setTomatoTimeStarted(Long tomatoTimeStarted) {
        this.tomatoTimeStarted = tomatoTimeStarted;
    }
    public Long getTomatoTimeForecastEnd() {
        return this.tomatoTimeForecastEnd;
    }
    public void setTomatoTimeForecastEnd(Long tomatoTimeForecastEnd) {
        this.tomatoTimeForecastEnd = tomatoTimeForecastEnd;
    }
    public Long getTomatoTimeEnded() {
        return this.tomatoTimeEnded;
    }
    public void setTomatoTimeEnded(Long tomatoTimeEnded) {
        this.tomatoTimeEnded = tomatoTimeEnded;
    }
    public Long getTomatoTimeSummarizeStarted() {
        return this.tomatoTimeSummarizeStarted;
    }
    public void setTomatoTimeSummarizeStarted(Long tomatoTimeSummarizeStarted) {
        this.tomatoTimeSummarizeStarted = tomatoTimeSummarizeStarted;
    }
    public Long getTomatoTimeSummarizeEnded() {
        return this.tomatoTimeSummarizeEnded;
    }
    public void setTomatoTimeSummarizeEnded(Long tomatoTimeSummarizeEnded) {
        this.tomatoTimeSummarizeEnded = tomatoTimeSummarizeEnded;
    }
    public Long getTomatoTimeEnd() {
        return this.tomatoTimeEnd;
    }
    public void setTomatoTimeEnd(Long tomatoTimeEnd) {
        this.tomatoTimeEnd = tomatoTimeEnd;
    }
    public int getTomatoTime() {
        return this.tomatoTime;
    }
    public void setTomatoTime(int tomatoTime) {
        this.tomatoTime = tomatoTime;
    }
    public int getSummarizeTime() {
        return this.summarizeTime;
    }
    public void setSummarizeTime(int summarizeTime) {
        this.summarizeTime = summarizeTime;
    }
    public int getTomatoTimeStatus() {
        return this.tomatoTimeStatus;
    }
    public void setTomatoTimeStatus(int tomatoTimeStatus) {
        this.tomatoTimeStatus = tomatoTimeStatus;
    }
    public int getPlanTime() {
        return this.planTime;
    }
    public void setPlanTime(int planTime) {
        this.planTime = planTime;
    }

}
