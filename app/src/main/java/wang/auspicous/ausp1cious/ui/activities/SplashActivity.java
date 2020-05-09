package wang.auspicous.ausp1cious.ui.activities;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.widget.TextView;

import butterknife.BindView;
import wang.auspicous.ausp1cious.MainActivity;
import wang.auspicous.ausp1cious.presenters.contracts.SplashContract;
import wang.auspicous.ausp1cious.presenters.SplashPresenterImpl;
import wang.auspicous.ausp1cious.R;
import wang.auspicous.ausp1ciouslib.base.activity.MVPActivity;
import wang.auspicous.ausp1ciouslib.utils.ActivityJumpUtils;
import wang.auspicous.ausp1ciouslib.utils.interpolator.BreathInterpolator;

public class SplashActivity extends
        MVPActivity<SplashContract.SplashView, SplashPresenterImpl> implements
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
        tvSplashBreath.setOnClickListener(v -> {
            ActivityJumpUtils.jump(this, MainActivity.class);
            finish();
        });
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
        ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(tvSplashTips, "alpha", 0f, 1f);
        alphaAnimator.setDuration(4000);
        alphaAnimator.setInterpolator(new BreathInterpolator());//使用自定义的插值器
        alphaAnimator.setRepeatCount(ValueAnimator.INFINITE);
        alphaAnimator.start();
    }

}
