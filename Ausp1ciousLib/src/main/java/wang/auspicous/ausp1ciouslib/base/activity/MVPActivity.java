package wang.auspicous.ausp1ciouslib.base.activity;

import java.lang.reflect.ParameterizedType;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import wang.auspicous.ausp1ciouslib.base.presenter.BasePresenterImpl;
import wang.auspicous.ausp1ciouslib.component.contract.BaseContract;

/**
 * Created by Ausp1cious on 2019/3/13.
 */
public abstract class MVPActivity<V extends BaseContract.View,
        P extends BasePresenterImpl> extends BaseMVPActivity {
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
        if(mPresenter==null) {
            mPresenter = createPresenter();
        }
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
