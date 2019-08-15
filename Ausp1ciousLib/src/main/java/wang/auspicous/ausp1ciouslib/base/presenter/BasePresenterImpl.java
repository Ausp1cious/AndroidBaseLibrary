package wang.auspicous.ausp1ciouslib.base.presenter;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

import androidx.annotation.Nullable;
import wang.auspicous.ausp1ciouslib.component.contract.BaseContract;

/**
 * Created by Ausp1cious on 2019/2/21.
 * BasePresenter 实现类
 */
public class BasePresenterImpl<View extends BaseContract.View> implements BaseContract.Presenter {
    private Reference<View> mReference;

    /**
     * 添加View至Presenter
     */
    public void onAttach(View view) {
        mReference = new WeakReference<>(view);
    }

    /**
     * View是否绑定到Presenter上
     * @return true-绑定到Presenter上；false-没有绑定到Presenter上
     */
    public boolean isAttach() {
        return mReference != null && mReference.get() != null;
    }

    /**
     * 获取Presenter绑定的View
     */
    @Nullable
    public View getView(){
        if (isAttach()) {
            return mReference.get();
        }
        return null;
    }

    /**
     * 取消View的绑定
     */
    public void detach(){
        if (isAttach()) {
            mReference.clear();
            mReference = null;
        }
    }

    @Override
    public void onActivityDestroy() {

    }
}
