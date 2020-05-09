package wang.auspicous.ausp1ciouslib.base.activity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Ausp1cious on 2019/3/13.
 */
public abstract class UIActivity extends BaseUIActivity {

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
