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
    private P mPresenter;
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        attachPresenter();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        detachPresenter();
    }

    @Override
    void onFragmentFirstVisible() {
        if (whetherLazyLoad()) {
            initData();
        }
    }

    public LifecycleTransformer bindApiLifecycle() {
        return bindToLifecycle();
    }

    public LifecycleTransformer bindApiLifcycle(FragmentEvent fragmentEvent) {
        return bindUntilEvent(fragmentEvent);
    }

    /**
     * 获取Presenter
     */
    public P getPresenter() {
        if (mPresenter != null) {
            attachPresenter();
        }
        return mPresenter;
    }

    /**
     * 反射的方式创建Presenter
     */
    protected P createPresenter() {
        try {
            Class<P> entityClass =
                    (Class<P>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
            return entityClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将Presenter与Activity绑定
     */
    private void attachPresenter() {
        mPresenter = createPresenter();
        if (null != mPresenter) {
            mPresenter.onAttach((V) this);
        }
    }

    /**
     * 将Presenter与Activity取消绑定
     */
    private void detachPresenter() {
        if (null != mPresenter) {
            mPresenter.detach();
        }
    }

    /**
     * 初始化数据请求，在onStart中
     */
    protected abstract void initData();
}
