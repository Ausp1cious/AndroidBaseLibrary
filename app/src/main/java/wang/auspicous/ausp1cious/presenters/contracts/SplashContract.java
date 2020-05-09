package wang.auspicous.ausp1cious.presenters.contracts;

import wang.auspicous.ausp1ciouslib.component.contract.BaseContract;

/**
 * Created by Ausp1cious on 2019/3/13.
 */
public interface SplashContract {
    interface SplashView extends BaseContract.View {
        /**
         * 设置Tips
         */
        void setTips(String tips);

    }

    interface SplashPresenter extends BaseContract.Presenter {
        /**
         * 获取Tips
         */
        void getTips();
    }
}
