package wang.auspicous.ausp1cious.presenters;

import wang.auspicous.ausp1cious.presenters.contracts.SplashContract;
import wang.auspicous.ausp1ciouslib.base.presenter.BasePresenterImpl;

/**
 * Created by Ausp1cious on 2019/3/13.
 */
public class SplashPresenterImpl extends BasePresenterImpl<SplashContract.SplashView> implements
        SplashContract.SplashPresenter {

    @Override
    public void getTips() {
        if (getView() != null) {
            getView().setTips("静心，深呼吸");
        }
    }
}
