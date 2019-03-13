package wang.auspicous.ausp1cious.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

}
