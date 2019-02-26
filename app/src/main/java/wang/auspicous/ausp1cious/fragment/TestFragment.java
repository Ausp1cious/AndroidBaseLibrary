package wang.auspicous.ausp1cious.fragment;

import wang.auspicous.ausp1cious.R;
import wang.auspicous.ausp1ciouslib.base.fragment.BaseUIFragment;

/**
 * Created by Ausp1cious on 2019/2/26.
 */
public class TestFragment extends BaseUIFragment {

    public static TestFragment getNewInstance() {
        return new TestFragment();
    }

    @Override
    protected int setContainerView() {
        return R.layout.activity_test;
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
}
