package wang.auspicous.ausp1cious.ui.task;

import wang.auspicous.ausp1cious.Presenters.Contracts.SingleTaskAddContract;
import wang.auspicous.ausp1cious.Presenters.SingleTaskAddPrensenterImpl;
import wang.auspicous.ausp1cious.R;
import wang.auspicous.ausp1cious.base.AppMVPActivity;

/**
 * 单任务添加页面
 */
public class SingleTaskAddActivity extends AppMVPActivity<SingleTaskAddContract.SingleTaskAddView
        , SingleTaskAddPrensenterImpl> implements SingleTaskAddContract.SingleTaskAddView {

    @Override
    protected int setContainerView() {
        return R.layout.activity_single_task_add;
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
}
