package wang.auspicous.ausp1ciouslib.component.network;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;

import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ausp1cious on 2019/2/13.
 */
public class NetworkStateReceiver extends BroadcastReceiver {
  private List<NetworkStateChangeObserver> mObservers = new ArrayList<>();

  @Override
  public void onReceive(Context context, Intent intent) {
    if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.LOLLIPOP) {
      monitorNetworkWhenVersionLessThanLOLLIPOP(context);
      //API大于23时使用下面的方式进行网络监听
    } else if (android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
      monitorNetworkWhenVersionLessThanMarshmallow(context);
    } else {
      monitorNetworkWhenVersionLessThanMarshmallow(context);
      monitorNetworkConnectionType(context);
    }
  }

  // TODO: 2019/2/14 多网络连接状态
  @TargetApi(Build.VERSION_CODES.M)
  private void monitorNetworkConnectionType(Context context) {
    ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(
            Context.CONNECTIVITY_SERVICE);
    if(connMgr!=null) {
      NetworkCapabilities networkCapabilities = connMgr.getNetworkCapabilities(
              connMgr.getActiveNetwork());
      if (networkCapabilities != null) {
        boolean isValidated = networkCapabilities.hasCapability(
                NetworkCapabilities.NET_CAPABILITY_VALIDATED);
        if (isValidated) {//网络完全可用状态
//      networkCapabilities.hasTransport();
          notifyNetworkAvailable();
          getTransportAvailable(networkCapabilities);
        } else {//网络不可用状态
          notifyNetworkNotAvailable();
        }
      }
    }
  }

  /**
   * 检测网络连接状态
   */
  private void monitorNetworkWhenVersionLessThanMarshmallow(Context context) {
    //获得ConnectivityManager对象
    ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(
            Context.CONNECTIVITY_SERVICE);

    //获取所有当前已有连接上状态的网络连接的信息
    Network[] networks = connMgr.getAllNetworks();

    //用于记录最后的网络连接信息
    int result = 0;//mobile false = 1, mobile true = 2, wifi = 4

    //通过循环将网络信息逐个取出来
    for (int i = 0; i < networks.length; i++) {
      //获取ConnectivityManager对象对应的NetworkInfo对象
      NetworkInfo networkInfo = connMgr.getNetworkInfo(networks[i]);

      //检测到有数据连接，但是并连接状态未生效，此种状态为wifi和数据同时已连接，以wifi连接优先
      if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE && !networkInfo.isConnected()) {
        result += 1;
      }

      //检测到有数据连接，并连接状态已生效，此种状态为只有数据连接，wifi并未连接上
      if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE && networkInfo.isConnected()) {
        result += 2;
      }

      //检测到有wifi连接，连接状态必为true
      if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
        result += 4;
      }

    }
    //因为存在上述情况的组合情况，以组合相加的唯一值作为最终状态的判断
    switch (result) {
      case 0:
//        Log.i("NetworkStatus", "WIFI已断开,移动数据已断开");
        notifyNetworkDisconnected();
        break;
      case 2:
        notifyMobileDataTrafficConnected();
//        Log.i("NetworkStatus", "WIFI已断开,移动数据已连接");
        break;
      case 4:
//        Log.i("NetworkStatus", "WIFI已连接,移动数据已断开");
        notifyWifiConnected();
        break;
      case 5:
//        Log.i("NetworkStatus", "WIFI已连接,移动数据已连接");
        notifyWifiConnected();
        break;
    }
  }

  private void monitorNetworkWhenVersionLessThanLOLLIPOP(Context context) {
    //获得ConnectivityManager对象
    ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(
            Context.CONNECTIVITY_SERVICE);
    //获取ConnectivityManager对象对应的NetworkInfo对象
    //获取WIFI连接的信息
    if (connMgr == null) {
      return;
    }
    NetworkInfo wifiNetworkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
    //获取移动数据连接的信息
    NetworkInfo dataNetworkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
    if (wifiNetworkInfo.isConnected() && dataNetworkInfo.isConnected()) {
//      Log.i("NetworkStatus", "WIFI已连接,移动数据已连接");
      notifyWifiConnected();
    } else if (wifiNetworkInfo.isConnected() && !dataNetworkInfo.isConnected()) {
//      Log.i("NetworkStatus", "WIFI已连接,移动数据已断开");
      notifyWifiConnected();
    } else if (!wifiNetworkInfo.isConnected() && dataNetworkInfo.isConnected()) {
//      Log.i("NetworkStatus", "WIFI已断开,移动数据已连接");
      notifyMobileDataTrafficConnected();
    } else {
//      Log.i("NetworkStatus", "WIFI已断开,移动数据已断开");
      notifyNetworkDisconnected();
    }
  }

  /**
   * 监听网络可用通道变化
   */
  private void getTransportAvailable(NetworkCapabilities networkCapabilities) {
    if (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
      notifyCellularAvailable();
      Logger.t("NetworkStatus").i("notifyCellularAvailable");
    }
    if (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
      notifyWIFIAvailable();
      Logger.t("NetworkStatus").i("notifyWIFIAvailable");
    }
    if (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH)) {
      notifyBluetoothAvailable();
      Logger.t("NetworkStatus").i("notifyBluetoothAvailable");
    }
    if (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
      notifyEthernetAvailable();
      Logger.t("NetworkStatus").i("notifyEthernetAvailable");
    }
    if (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_VPN)) {
      notifyVPNAvailable();
    }
    if (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI_AWARE)) {
      notifyWIFIAvailable();
    }
    if (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_LOWPAN)) {
      notifyLOWPANAvailable();
    }
  }

  /**
   * 注册Observer
   */
  public void registerReceiver(NetworkStateChangeObserver observer) {
    if (observer == null) {
      return;
    }
    if (!mObservers.contains(observer)) {
      mObservers.add(observer);
    }
  }

  /**
   * 移除Observer
   */
  public void unregisterObserver(NetworkStateChangeObserver observer) {
    if (observer == null) {
      return;
    }
    mObservers.remove(observer);
  }

  /**
   * 通知Wifi连接
   */
  private void notifyWifiConnected() {
    for (NetworkStateChangeObserver observer : mObservers) {
      observer.onWifiConnected();
    }
  }

  /**
   * 移动数据连接
   */
  private void notifyMobileDataTrafficConnected() {
    for (NetworkStateChangeObserver observer : mObservers) {
      observer.onMobileDataTrafficConnected();
    }
  }

  /**
   * 失去连接
   */
  private void notifyNetworkDisconnected() {
    for (NetworkStateChangeObserver observer : mObservers) {
      observer.onNetworkDisconnected();
    }
  }

  private void notifyNetworkAvailable() {
    for (NetworkStateChangeObserver observer : mObservers) {
      observer.onNetworkAvailable();
    }
  }

  private void notifyNetworkNotAvailable() {
    for (NetworkStateChangeObserver observer : mObservers) {
      observer.onNetworkNotAvailable();
    }
  }

  private void notifyCellularAvailable() {
    for (NetworkStateChangeObserver observer : mObservers) {
      observer.onCellularAvailable();
    }
  }

  private void notifyWIFIAvailable() {
    for (NetworkStateChangeObserver observer : mObservers) {
      observer.onWIFIAvailable();
    }
  }

  private void notifyBluetoothAvailable() {
    for (NetworkStateChangeObserver observer : mObservers) {
      observer.onBluetoothAvailable();
    }
  }

  private void notifyEthernetAvailable() {
    for (NetworkStateChangeObserver observer : mObservers) {
      observer.onEthernetAvailable();
    }
  }

  private void notifyVPNAvailable() {
    for (NetworkStateChangeObserver observer : mObservers) {
      observer.onVPNAvailable();
    }
  }

  private void notifyLOWPANAvailable() {
    for (NetworkStateChangeObserver observer : mObservers) {
      observer.onLOWPANAvailable();
    }
  }
}
