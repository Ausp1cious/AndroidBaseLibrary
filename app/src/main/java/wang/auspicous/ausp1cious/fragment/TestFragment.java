package wang.auspicous.ausp1cious.fragment;

import wang.auspicous.ausp1cious.R;
import wang.auspicous.ausp1ciouslib.base.fragment.BaseMVPFragment;
import wang.auspicous.ausp1ciouslib.base.fragment.BaseUIFragment;

/**
 * Created by Ausp1cious on 2019/2/26.
 */
public class TestFragment extends BaseMVPFragment {

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

    @Override
    protected void initData() {

    }

    @Override
    protected void onFragmentResume() {

    }

    @Override
    protected void onFragmentPause() {

    }
}
