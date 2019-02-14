package wang.auspicous.ausp1ciouslib.base.activity;

import android.Manifest;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;

import androidx.annotation.Nullable;
import pub.devrel.easypermissions.EasyPermissions;
import wang.auspicous.ausp1ciouslib.Constants;
import wang.auspicous.ausp1ciouslib.R;
import wang.auspicous.ausp1ciouslib.component.network.NetworkStateReceiver;

/**
 * Created by Ausp1cious on 2019/2/13.
 */
public abstract class BaseNetEvnActivity extends BasePermissionActivity {
  private NetworkStateReceiver receiver;
  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    askForInternet();
  }

  @Override
  protected void onStart() {
    super.onStart();
    initNetworkReceiver();
  }

  @Override
  protected void onStop() {
    super.onStop();
    unregisterNetworkReceiver();
  }

  /**
   * 请求网络访问权限
   */
  private void askForInternet() {
    String[] perms = {Manifest.permission.INTERNET, Manifest.permission.CHANGE_NETWORK_STATE,
            Manifest.permission.CHANGE_WIFI_STATE};
    if (!EasyPermissions.hasPermissions(this, perms)) {
      EasyPermissions.requestPermissions(this, getString(R.string.permission_vibrate),
              Constants.Permission.PERMISSION_INTERNET, perms);
    }
  }

  /**
   * 初始化网络监听
   */
  private void initNetworkReceiver() {
    IntentFilter filter = new IntentFilter();
    filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
//    filter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
//    filter.addAction("android.net.wifi.STATE_CHANGE");
    receiver = new NetworkStateReceiver();
    registerReceiver(receiver, filter);
  }

  /**
   * 取消网络状态的监听
   */
  private void unregisterNetworkReceiver() {
    if (receiver != null) {
      unregisterReceiver(receiver);
    }
  }
}
