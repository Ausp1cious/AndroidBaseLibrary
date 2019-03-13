package wang.auspicous.ausp1ciouslib.base.fragment;

import android.os.Bundle;

import com.trello.rxlifecycle3.LifecycleTransformer;
import com.trello.rxlifecycle3.android.ActivityEvent;
import com.trello.rxlifecycle3.android.FragmentEvent;

import java.lang.reflect.ParameterizedType;

import androidx.annotation.Nullable;
import wang.auspicous.ausp1ciouslib.base.presenter.BasePresenterImpl;
import wang.auspicous.ausp1ciouslib.component.contract.BaseContract;

/**
 * Created by Ausp1cious on 2019/3/5.
 */
public abstract class BaseMVPFragment<V extends BaseContract.View,
        P extends BasePresenterImpl> extends BaseUIFragment {
    protected P mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        attachPresenter();
    }

    @Override
    public void onStart() {
        super.onStart();
        initData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        detachPresenter();
    }

    public LifecycleTransformer bindApiLifecycle() {
        return bindToLifecycle();
    }

    public LifecycleTransformer bindApiLifcycle(FragmentEvent fragmentEvent) {
        return bindUntilEvent(fragmentEvent);
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
}
