package wang.auspicous.ausp1ciouslib.base.activity;

import android.os.Bundle;

import com.trello.rxlifecycle3.LifecycleTransformer;
import com.trello.rxlifecycle3.android.ActivityEvent;

import java.lang.reflect.ParameterizedType;

import androidx.annotation.Nullable;

import wang.auspicous.ausp1ciouslib.base.presenter.BasePresenterImpl;
import wang.auspicous.ausp1ciouslib.component.contract.BaseContract;

/**
 * Created by Ausp1cious on 2019/2/21.
 * MVP Activity 基类
 */
public abstract class BaseMVPActivity<V extends BaseContract.View,
        P extends BasePresenterImpl> extends BaseUIActivity {
    protected P mPresenter;
    private boolean isFirstInitData = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        attachPresenter();

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (isFirstInitData) {
            initData();
            isFirstInitData = false;
        }
        freshDataOnStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        detachPresenter();
    }

    public LifecycleTransformer bindApiLifecycle() {
        return bindToLifecycle();
    }

    public LifecycleTransformer bindApiLifecycle(ActivityEvent activityEvent) {
        return bindUntilEvent(activityEvent);
    }

    /**
     * 设置Presenter
     */
    protected abstract void setPresenter(P presenter);

    /**
     * 获取页面中的Presenter
     */
    protected abstract P getPresenter();

    /**
     * Presenter绑定View
     */
    protected abstract void attachPresenter();

    /**
     * Presenter取消绑定View
     */
    protected abstract void detachPresenter();

    /**
     * 初始化数据请求，在onStart中
     */
    protected abstract void initData();

    /**
     * 每次在onStart的时候，加载数据
     */
    protected abstract void freshDataOnStart();
}
