package wang.auspicous.ausp1ciouslib.component.network;

/**
 * Created by Ausp1cious on 2018/3/22
 */

public interface NetworkStateChangeObserver {
    /**
     * 网络状态消失时
     */
    void onNetworkDisconnected();

    /**
     * 当Wifi连接时
     */
    void onWifiConnected();

    /**
     * 当移动网络连接时
     */
    void onMobileDataTrafficConnected();

    /**
     * 当4G连接时
     */
    void on4GConnected();

    /**
     * 当4G断开时
     */
    void on4GDisConnected();

}
