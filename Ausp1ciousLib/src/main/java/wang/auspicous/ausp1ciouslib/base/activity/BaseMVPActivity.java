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
        P extends BasePresenterImpl> extends
        BaseUIActivity {
    private P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        attachPresenter();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        detachPresenter();
    }

    public LifecycleTransformer bindApiLifecycle() {
        return bindToLifecycle();
    }

    public LifecycleTransformer bindApiLifcycle(ActivityEvent activityEvent) {
        return bindUntilEvent(activityEvent);
    }

    /**
     * 获取Presenter
     * @return
     */
    public P getPresenter() {
        if (mPresenter != null) {
            attachPresenter();
        }
        return mPresenter;
    }

    /**
     * 反射的方式创建Presenter
     * @return
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
}
