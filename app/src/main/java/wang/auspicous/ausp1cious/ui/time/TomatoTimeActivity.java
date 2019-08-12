package wang.auspicous.ausp1cious.ui.time;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import wang.auspicous.ausp1cious.Presenters.Contracts.TomatoTimeContract;
import wang.auspicous.ausp1cious.Presenters.TomatoTimePresenterImpl;
import wang.auspicous.ausp1cious.R;
import wang.auspicous.ausp1cious.base.AppMVPActivity;
import wang.auspicous.ausp1cious.ui.time.adpter.TomatoAdapter;

public class TomatoTimeActivity extends AppMVPActivity<TomatoTimeContract.TomatoTimeView,
        TomatoTimePresenterImpl> implements TomatoTimeContract.TomatoTimeView {
    @BindView(R.id.rv_tomato)
    RecyclerView rvTomato;

    @Override
    protected int setContainerView() {
        return R.layout.activity_tomato_time;
    }

    @Override
    protected void initValue() {

    }

    @Override
    protected void initWidget() {
        rvTomato.setLayoutManager(new LinearLayoutManager(this));

    }

    protected void initListener() {

    }

    @SuppressLint("CheckResult")
    @Override
    protected void initData() {
        rvTomato.setAdapter(new TomatoAdapter(this));
    }

}
