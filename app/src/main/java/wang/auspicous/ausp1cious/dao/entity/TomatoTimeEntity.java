package wang.auspicous.ausp1cious.dao.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import wang.auspicous.ausp1ciouslib.component.bean.BaseBean;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Unique;

@Entity
public class TomatoTimeEntity extends BaseBean {
    @Id
    private Long tomatoTimeId;
    @Unique
    private Long taskId;
    //开始时间
    private Long tomatoTimeStart;
    //真正开始的时间
    private Long tomatoTimeStarting;
    //预计结束时间
    private Long tomatoTimeForecastEnd;
    //真正结束的时间
    private Long tomatoTimeEnding;
    //结束时间
    private Long tomatoTimeEnd;
    //1=正常结束；2=提前结束；3=外在中断；4=内在终端；5=持续中；6=遗忘
    private int tomatoTimeStatus;
    @Generated(hash = 1975419317)
    public TomatoTimeEntity(Long tomatoTimeId, Long taskId, Long tomatoTimeStart,
            Long tomatoTimeStarting, Long tomatoTimeForecastEnd,
            Long tomatoTimeEnding, Long tomatoTimeEnd, int tomatoTimeStatus) {
        this.tomatoTimeId = tomatoTimeId;
        this.taskId = taskId;
        this.tomatoTimeStart = tomatoTimeStart;
        this.tomatoTimeStarting = tomatoTimeStarting;
        this.tomatoTimeForecastEnd = tomatoTimeForecastEnd;
        this.tomatoTimeEnding = tomatoTimeEnding;
        this.tomatoTimeEnd = tomatoTimeEnd;
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
    public Long getTomatoTimeForecastEnd() {
        return this.tomatoTimeForecastEnd;
    }
    public void setTomatoTimeForecastEnd(Long tomatoTimeForecastEnd) {
        this.tomatoTimeForecastEnd = tomatoTimeForecastEnd;
    }
    public Long getTomatoTimeEnd() {
        return this.tomatoTimeEnd;
    }
    public void setTomatoTimeEnd(Long tomatoTimeEnd) {
        this.tomatoTimeEnd = tomatoTimeEnd;
    }
    public int getTomatoTimeStatus() {
        return this.tomatoTimeStatus;
    }
    public void setTomatoTimeStatus(int tomatoTimeStatus) {
        this.tomatoTimeStatus = tomatoTimeStatus;
    }
    public Long getTomatoTimeStarting() {
        return this.tomatoTimeStarting;
    }
    public void setTomatoTimeStarting(Long tomatoTimeStarting) {
        this.tomatoTimeStarting = tomatoTimeStarting;
    }
    public Long getTomatoTimeEnding() {
        return this.tomatoTimeEnding;
    }
    public void setTomatoTimeEnding(Long tomatoTimeEnding) {
        this.tomatoTimeEnding = tomatoTimeEnding;
    }


}
