package wang.auspicous.ausp1cious.ui;

import android.widget.TextView;

import butterknife.BindView;
import wang.auspicous.ausp1cious.Presenters.Contracts.SplashContract;
import wang.auspicous.ausp1cious.Presenters.SplashPresenterImpl;
import wang.auspicous.ausp1cious.R;
import wang.auspicous.ausp1cious.base.AppMVPActivity;

public class SplashActivity extends
        AppMVPActivity<SplashContract.SplashView, SplashPresenterImpl> implements
        SplashContract.SplashView {

    @BindView(R.id.tv_splash_tips)
    TextView tvSplashTips;
    @BindView(R.id.tv_splash_breath)
    TextView tvSplashBreath;

    @Override
    protected int setContainerView() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initValue() {

    }

    @Override
    protected void initWidget() {

    }

    @Override
    protected void initListener() {

    }


    @Override
    protected void initData() {
        getPresenter().getTips();
    }

    @Override
    protected boolean fullScreenMode() {
        return true;
    }

    @Override
    public void setTips(String tips) {
        tvSplashTips.setText(tips);
    }
}
