package wang.auspicous.ausp1cious.ui.time;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.trello.rxlifecycle3.android.ActivityEvent;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.functions.Consumer;
import wang.auspicous.ausp1cious.Presenters.Contracts.TomatoTimeContract;
import wang.auspicous.ausp1cious.Presenters.TomatoTimePresenterImpl;
import wang.auspicous.ausp1cious.R;
import wang.auspicous.ausp1cious.base.AppMVPActivity;
import wang.auspicous.ausp1cious.bean.TomatoPeriodsBean;
import wang.auspicous.ausp1cious.bean.TomatoTimeStatus;
import wang.auspicous.ausp1cious.ui.time.adpter.TomatoAdapter;
import wang.auspicous.ausp1cious.utils.RxTimeUtils;
import wang.auspicous.ausp1cious.utils.TomatoTimeUtils;
import wang.auspicous.ausp1cious.widgets.TomatoTimeView;

public class TomatoTimeActivity extends AppMVPActivity<TomatoTimeContract.TomatoTimeView,
        TomatoTimePresenterImpl> implements TomatoTimeContract.TomatoTimeView {
    @BindView(R.id.rv_tomato)
    RecyclerView rvTomato;
    @BindView(R.id.ttv_tomato_time)
    TomatoTimeView ttvTime;
    private TomatoTimeStatus tomatoTimeStatus;

    @Override
    protected int setContainerView() {
        return R.layout.activity_tomato_time;
    }

    @Override
    protected void initValue() {

    }

    @Override
    protected void initWidget() {
//        rvTomato.setLayoutManager(new LinearLayoutManager(this));
        ttvTime.setPeriods(new TomatoPeriodsBean(11, 10));
        tomatoTimeStatus = new TomatoTimeStatus();
        ttvTime.setTomatoTimeStatus(tomatoTimeStatus);
    }

    protected void initListener() {

    }

    @SuppressLint("CheckResult")
    @Override
    protected void initData() {
//        rvTomato.setAdapter(new TomatoAdapter(this));
        RxTimeUtils.showScreenTime(bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(o -> {
                    TomatoTimeUtils.getRestTime(1565685600000L, tomatoTimeStatus);
                    ttvTime.updateTomatoTimeStatus();
                });
    }

}
