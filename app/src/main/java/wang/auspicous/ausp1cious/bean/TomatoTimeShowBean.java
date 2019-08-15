package wang.auspicous.ausp1cious.bean;

import wang.auspicous.ausp1ciouslib.component.bean.BaseBean;

public class TomatoTimeShowBean extends BaseBean {
    private TomatoPeriodsBean tomatoPeriodsBean;
    private ShowSingleTimeBean showSingleTimeBean;
    private String tomatoTimeStatus;
    private float restRate = 0;

    public TomatoPeriodsBean getTomatoPeriodsBean() {
        return tomatoPeriodsBean;
    }

    public void setTomatoPeriodsBean(TomatoPeriodsBean tomatoPeriodsBean) {
        this.tomatoPeriodsBean = tomatoPeriodsBean;
    }

    public ShowSingleTimeBean getShowSingleTimeBean() {
        return showSingleTimeBean;
    }

    public void setShowSingleTimeBean(ShowSingleTimeBean showSingleTimeBean) {
        this.showSingleTimeBean = showSingleTimeBean;
    }

    public String getTomatoTimeStatus() {
        return tomatoTimeStatus;
    }

    public void setTomatoTimeStatus(String tomatoTimeStatus) {
        this.tomatoTimeStatus = tomatoTimeStatus;
    }

    public float getRestRate() {
        return restRate;
    }

    public void setRestRate(float restRate) {
        this.restRate = restRate;
    }
}
