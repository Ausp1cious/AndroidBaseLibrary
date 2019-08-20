package wang.auspicous.ausp1cious.ui.time;

import android.annotation.SuppressLint;

import butterknife.BindView;
import wang.auspicous.ausp1cious.Presenters.Contracts.TomatoTimeContract;
import wang.auspicous.ausp1cious.Presenters.TomatoTimePresenterImpl;
import wang.auspicous.ausp1cious.R;
import wang.auspicous.ausp1cious.base.AppMVPActivity;
import wang.auspicous.ausp1cious.bean.TomatoTimeShowBean;
import wang.auspicous.ausp1cious.widgets.ProgressButtonView;
import wang.auspicous.ausp1cious.widgets.TomatoTimeView;

public class TomatoTimeActivity extends AppMVPActivity<TomatoTimeContract.TomatoTimeView,
        TomatoTimePresenterImpl> implements TomatoTimeContract.TomatoTimeView {
    @BindView(R.id.ttv_tomato_time)
    TomatoTimeView ttvTime;
    @BindView(R.id.pbv_tomato_time_left)
    ProgressButtonView pbvTomatoTimeLeft;
    @BindView(R.id.pbv_tomato_time_right)
    ProgressButtonView pbvTomatoTimeRight;

    @Override
    protected int setContainerView() {
        return R.layout.activity_tomato_time;
    }

    @Override
    protected void initValue() {

    }

    @Override
    protected void initWidget() {

    }

    protected void initListener() {

    }

    @SuppressLint("CheckResult")
    @Override
    protected void initData() {
        getPresenter().getTomatoTimeData();
    }

    @Override
    public void setTomatoTimeViewDate(TomatoTimeShowBean tomatoTimeShowBean) {
        ttvTime.setTomatoTimeShowBean(tomatoTimeShowBean);
    }

    @Override
    public void updateTomatoTime() {
        ttvTime.updateTomatoTime();
    }

    @Override
    public void setPreparePeriod() {
        pbvTomatoTimeLeft.setText(getResources().getString(R.string.tomato_time_prepare_add_time));
        pbvTomatoTimeRight.setText(getResources().getString(R.string.tomato_time_prepare_complete));
        pbvTomatoTimeLeft.setProgressMode(false);
        pbvTomatoTimeRight.setProgressMode(false);
    }

    @Override
    public void setTomatoTimePeriod() {
        pbvTomatoTimeLeft.setText(getResources().getString(R.string.tomato_time_time_todo));
        pbvTomatoTimeRight.setText(getResources().getString(R.string.tomato_time_time_complete));
        pbvTomatoTimeLeft.setProgressMode(false);
        pbvTomatoTimeRight.setProgressMode(true);
    }

    @Override
    public void setSummarizePeriod() {
        pbvTomatoTimeLeft.setText(getResources().getString(R.string.tomato_time_summarize_summarize));
        pbvTomatoTimeRight.setText(getResources().getString(R.string.tomato_time_summarize_complete));
        pbvTomatoTimeLeft.setProgressMode(false);
        pbvTomatoTimeRight.setProgressMode(true);
    }

}
