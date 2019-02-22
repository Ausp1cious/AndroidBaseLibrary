package wang.auspicous.ausp1ciouslib.base.fragment;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import pub.devrel.easypermissions.EasyPermissions;
import wang.auspicous.ausp1ciouslib.Constants;
import wang.auspicous.ausp1ciouslib.R;
import wang.auspicous.ausp1ciouslib.component.network.NetworkStateChangeObserver;
import wang.auspicous.ausp1ciouslib.component.network.NetworkStateReceiver;
import wang.auspicous.ausp1ciouslib.utils.deviceutils.NetworkUtils;

/**
 * Created by Ausp1cious on 2019/2/22.
 */
public abstract class BaseNetEvnFragment extends BasePermissionFragment implements
        NetworkStateChangeObserver {
    private NetworkStateReceiver receiver;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        askForInternet();
    }

    /**
     * 请求网络访问权限
     */
    private void askForInternet() {
        String[] perms = {Manifest.permission.INTERNET, Manifest.permission.CHANGE_NETWORK_STATE,
                Manifest.permission.CHANGE_WIFI_STATE};
        if (EasyPermissions.hasPermissions(getActivity(), perms)) {
            getCurrentNetworkInfo();
        } else {
            EasyPermissions.requestPermissions(getActivity(),
                    getActivity().getString(R.string.permission_vibrate),
                    Constants.Permission.PERMISSION_INTERNET, perms);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (openNetworkMonitor()) {
            initNetworkReceiver();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        unregisterNetworkReceiver();
    }

    /**
     * 是否开启网络监听
     *
     * @return true-开启（关闭） false-关闭
     */
    protected boolean openNetworkMonitor() {
        return false;
    }

    /**
     * 初始化网络监听
     */
    private void initNetworkReceiver() {
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        receiver = new NetworkStateReceiver();
        getActivity().registerReceiver(receiver, filter);
        receiver.registerReceiver(this);

    }

    /**
     * 取消网络状态的监听
     */
    private void unregisterNetworkReceiver() {
        if (receiver != null) {
            getActivity().unregisterReceiver(receiver);
            receiver.unregisterObserver(this);
        }
    }

    /**
     * 获取当前的网络状态信息
     */
    private void getCurrentNetworkInfo() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            NetworkUtils.NetworkAvailable networkAvailable = NetworkUtils.isNetworkAvailable();
            switch (networkAvailable) {
                case NETWORK_AVAILABLE:
                    startInNetworkAvailable();
                    break;
                case NETWORK_NOT_AVAILABLE:
                    startInNetworkNotAvailable();
                    break;
                case UNKNOWN:
                    startInNetworkUnknown();
                    break;
            }
        }
        switch (NetworkUtils.getCurrentNetworkType()) {
            case NETWORK_2G:
                startIn2G();
                break;
            case NETWORK_3G:
                startIn3G();
                break;
            case NETWORK_4G:
                startIn4G();
                break;
            case NETWORK_WIFI:
                startInWifi();
                break;
            case NETWORK_ETHERNET:
                startInEthernet();
                break;
            case BLUE_TOOTH:
                startInBlueTooth();
                break;
            case VPN:
                startInVPN();
                break;
            case NETWORK_UNKNOWN:
                startInUNKNOWN();
                break;
        }
    }

    /**
     * 6.0以上网络可以连通
     */
    @TargetApi(Build.VERSION_CODES.M)
    protected void startInNetworkAvailable() {
    }

    /**
     * 6.0以上网络不可以连通
     */
    @TargetApi(Build.VERSION_CODES.M)
    protected void startInNetworkNotAvailable() {
    }

    /**
     * 6.0以上，获取不到网络参数
     */
    @TargetApi(Build.VERSION_CODES.M)
    protected void startInNetworkUnknown() {
    }

    /**
     * 打开页面时候，页面的状态
     */
    protected void startIn2G() {
    }

    protected void startIn3G() {
    }

    protected void startIn4G() {
    }

    protected void startInWifi() {
    }

    protected void startInEthernet() {
    }

    protected void startInBlueTooth() {
    }

    protected void startInVPN() {
    }

    protected void startInUNKNOWN() {
    }

    @Override
    public void onNetworkDisconnected() {

    }

    @Override
    public void onWifiConnected() {

    }

    @Override
    public void onMobileDataTrafficConnected() {

    }

    @Override
    public void on4GConnected() {

    }

    @Override
    public void onNetworkAvailable() {

    }

    @Override
    public void onNetworkNotAvailable() {

    }

    @Override
    public void onCellularAvailable() {

    }

    @Override
    public void onWIFIAvailable() {

    }

    @Override
    public void onBluetoothAvailable() {

    }

    @Override
    public void onEthernetAvailable() {

    }

    @Override
    public void onVPNAvailable() {

    }

    @Override
    public void onLOWPANAvailable() {

    }
}
