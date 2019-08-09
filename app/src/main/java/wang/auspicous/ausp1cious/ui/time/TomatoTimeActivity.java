package wang.auspicous.ausp1cious.ui.time;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import com.trello.rxlifecycle3.android.ActivityEvent;

import butterknife.BindView;
import butterknife.ButterKnife;
import wang.auspicous.ausp1cious.Presenters.Contracts.TomatoTimeContract;
import wang.auspicous.ausp1cious.Presenters.TomatoTimePresenterImpl;
import wang.auspicous.ausp1cious.R;
import wang.auspicous.ausp1cious.base.AppMVPActivity;
import wang.auspicous.ausp1cious.utils.AppTimeUtils;
import wang.auspicous.ausp1cious.utils.RxTimeUtils;
import wang.auspicous.ausp1cious.utils.TomatoTimeUtils;

public class TomatoTimeActivity extends AppMVPActivity<TomatoTimeContract.TomatoTimeView,
        TomatoTimePresenterImpl> implements TomatoTimeContract.TomatoTimeView {


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
    }

}
