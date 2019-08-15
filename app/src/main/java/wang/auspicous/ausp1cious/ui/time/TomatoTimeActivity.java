package wang.auspicous.ausp1cious.ui.time;

import android.annotation.SuppressLint;
import android.widget.Button;

import butterknife.BindView;
import wang.auspicous.ausp1cious.Presenters.Contracts.TomatoTimeContract;
import wang.auspicous.ausp1cious.Presenters.TomatoTimePresenterImpl;
import wang.auspicous.ausp1cious.R;
import wang.auspicous.ausp1cious.base.AppMVPActivity;
import wang.auspicous.ausp1cious.bean.TomatoTimeShowBean;
import wang.auspicous.ausp1cious.utils.AppTimeUtils;
import wang.auspicous.ausp1cious.widgets.TomatoTimeView;

public class TomatoTimeActivity extends AppMVPActivity<TomatoTimeContract.TomatoTimeView,
        TomatoTimePresenterImpl> implements TomatoTimeContract.TomatoTimeView {
    @BindView(R.id.ttv_tomato_time)
    TomatoTimeView ttvTime;
    @BindView(R.id.btn_tomato_time_start)
    Button btnTomatoTimeStart;
    long startTime;

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
        btnTomatoTimeStart.setOnClickListener(v -> {
            startTime = AppTimeUtils.getCurrentTimeAsLong();
        });
    }

    @SuppressLint("CheckResult")
    @Override
    protected void initData() {
//        rvTomato.setAdapter(new TomatoAdapter(this));
//        RxTimeUtils.showScreenTime(bindUntilEvent(ActivityEvent.DESTROY))
//                .subscribe(o -> {
//                    if (startTime != 0) {
//                        TomatoTimeUtils.getRestTime(startTime, tomatoTimeStatusBean);
//                        ttvTime.updateTomatoTimeStatus();
//                    }
//                });
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

    // TODO: 8/15/19 是否需要开始开关
}
