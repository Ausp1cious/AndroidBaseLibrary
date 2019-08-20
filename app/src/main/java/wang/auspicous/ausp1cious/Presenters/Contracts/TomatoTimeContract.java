package wang.auspicous.ausp1cious.Presenters.Contracts;

import wang.auspicous.ausp1cious.bean.TomatoTimeShowBean;
import wang.auspicous.ausp1ciouslib.component.contract.BaseContract;

public interface TomatoTimeContract {
    interface TomatoTimeView extends BaseContract.View {
        //设置番茄时钟显示的数据
        void setTomatoTimeViewDate(TomatoTimeShowBean tomatoTimeShowBean);
        //更新控件
        void updateTomatoTime();

        void setPreparePeriod();

        void setTomatoTimePeriod();

        void setSummarizePeriod();
    }

    interface TomatoTimePresenter extends BaseContract.Presenter {
        void getTomatoTimeData();

    }
}
