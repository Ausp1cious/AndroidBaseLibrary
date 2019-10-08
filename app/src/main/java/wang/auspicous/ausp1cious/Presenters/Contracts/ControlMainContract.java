package wang.auspicous.ausp1cious.Presenters.Contracts;

import wang.auspicous.ausp1ciouslib.component.contract.BaseContract;

public interface ControlMainContract {
    interface ControlMainView extends BaseContract.View {

        void getPancamInfo(String pancamInfo);

        void getBitratiosInfo(String bitratiosInfo);

        void getSettingInfo(String settingInfo);

        void setUpdate();

        void reboot();
    }

    interface ControlMainPresenter extends BaseContract.Presenter {

        void setBaseUrl(String ip, String port);

        void getPancamInfo();

        void getBitratiosInfo();

        void getSettingInfo();

        void setUpdate(String bitratios,String rmpt);

        void reboot();

        void getPiInfo();
    }

}
