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

    /**
     * 网络可用的情况（可能是Wifi,流量，蓝牙等网络可用）
     */
    void onNetworkAvailable();

    /**
     * 网络完全不可用的情况（Wifi,流量，蓝牙等网络都不可用）
     */
    void onNetworkNotAvailable();

    /**
     * 蜂窝网络可用的情况
     */
    void onCellularAvailable();

    /**
     * WIFI网络可用的情况
     */
    void onWIFIAvailable();

    /**
     * 蓝牙网络可用的情况
     */
    void onBluetoothAvailable();

    /**
     * 以太网络可用的情况
     */
    void onEthernetAvailable();

    /**
     * VPN网络可用的情况
     */
    void onVPNAvailable();

    /**
     * 6LoWPAN 网络可用的情况
     */
    void onLOWPANAvailable();
}
