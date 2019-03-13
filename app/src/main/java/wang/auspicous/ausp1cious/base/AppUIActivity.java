package wang.auspicous.ausp1cious.base;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import wang.auspicous.ausp1ciouslib.base.activity.BaseUIActivity;

/**
 * Created by Ausp1cious on 2019/3/13.
 */
public abstract class AppUIActivity extends BaseUIActivity {

    private Unbinder mBind;

    @Override
    protected void bindButterKnife() {
        super.bindButterKnife();
        mBind = ButterKnife.bind(this);
    }

    @Override
    protected void unBindButterKnife() {
        super.unBindButterKnife();
        mBind.unbind();
    }
}
