package wang.auspicous.ausp1cious.base;

import java.lang.ref.WeakReference;

import wang.auspicous.ausp1ciouslib.component.contract.BaseContract;
import wang.auspicous.ausp1ciouslib.net.callback.RequestCallback;

public abstract class SimpleCallBack<R> implements RequestCallback<R> {
    private WeakReference<BaseContract.View> mReference;

    public SimpleCallBack(BaseContract.View view) {
        this.mReference = new WeakReference<>(view);
    }

    @Override
    public void onStart() {
        if (null != mReference.get()) {
//            mReference.get().showLoadingDialog();
        }
    }

    @Override
    public void onFinish() {
        if (null != mReference.get()) {
//            mReference.get().hideLoadingDialog();
        }
    }
}
