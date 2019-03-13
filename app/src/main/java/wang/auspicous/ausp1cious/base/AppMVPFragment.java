package wang.auspicous.ausp1cious.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.ParameterizedType;

import androidx.annotation.Nullable;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import wang.auspicous.ausp1ciouslib.base.fragment.BaseMVPFragment;
import wang.auspicous.ausp1ciouslib.base.presenter.BasePresenterImpl;
import wang.auspicous.ausp1ciouslib.component.contract.BaseContract;

/**
 * Created by Ausp1cious on 2019/3/13.
 */
public abstract class AppMVPFragment<V extends BaseContract.View,
        P extends BasePresenterImpl> extends BaseMVPFragment {
    private Unbinder mBind;

    @Override
    protected void bindButterKnife(View view) {
        super.bindButterKnife(view);
        mBind = ButterKnife.bind(this, view);
    }

    @Override
    protected void unBindButterKnife() {
        super.unBindButterKnife();
        mBind.unbind();
    }

    @Override
    protected void setPresenter(BasePresenterImpl presenter) {
        mPresenter = presenter;
    }

    @Override
    protected P getPresenter() {
        if (mPresenter != null) {
            attachPresenter();
        }
        return (P) mPresenter;
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

    @Override
    protected void attachPresenter() {
        mPresenter = createPresenter();
        if (null != mPresenter) {
            mPresenter.onAttach((V) this);
        }
    }

    @Override
    protected void detachPresenter() {
        if (null != mPresenter) {
            mPresenter.detach();
        }
    }
}
