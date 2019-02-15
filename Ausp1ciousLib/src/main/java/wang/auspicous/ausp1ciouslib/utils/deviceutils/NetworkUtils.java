package wang.auspicous.ausp1ciouslib.utils.deviceutils;

import android.Manifest;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.telecom.TelecomManager;
import android.telephony.TelephonyManager;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.RequiresApi;
import androidx.annotation.RequiresPermission;
import me.imid.swipebacklayout.lib.Utils;
import wang.auspicous.ausp1ciouslib.base.BaseApp;

/**
 * Created by Ausp1cious on 2019/2/15.
 */
public final class NetworkUtils {
  private NetworkUtils() {
  }

  public enum NetworkType {
    NETWORK_ETHERNET,
    NETWORK_WIFI,
    NETWORK_4G,
    NETWORK_3G,
    NETWORK_2G,
    NETWORK_UNKNOWN,
    VPN,
    BLUE_TOOTH,
  }

  public enum NetworkAvailable{
    NETWORK_AVAILABLE,
    NETWORK_NOT_AVAILABLE,
    UNKNOWN,
  }

  public enum TransportAvailable {
    TRANSPORT_CELLULAR,
    TRANSPORT_WIFI,
    TRANSPORT_BLUETOOTH,
    TRANSPORT_ETHERNET,
    TRANSPORT_VPN,
    TRANSPORT_WIFI_AWARE,
    TRANSPORT_LOWPAN
  }


  @RequiresApi(api = Build.VERSION_CODES.M)
  public static NetworkAvailable isNetworkAvailable() {
    ConnectivityManager connMgr = (ConnectivityManager) BaseApp.getInstance().getContext().getSystemService(
            Context.CONNECTIVITY_SERVICE);
    if (connMgr != null) {
      NetworkCapabilities networkCapabilities = connMgr.getNetworkCapabilities(
              connMgr.getActiveNetwork());
      if (networkCapabilities != null) {
        boolean isValidated = networkCapabilities.hasCapability(
                NetworkCapabilities.NET_CAPABILITY_VALIDATED);
        if (isValidated) {//网络完全可用状态
          return NetworkAvailable.NETWORK_AVAILABLE;
        } else {
          return NetworkAvailable.NETWORK_NOT_AVAILABLE;
        }
      }
    }
    return NetworkAvailable.UNKNOWN;
  }

  /**
   * 返回当前网络环境
   */
  @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
  public static NetworkType getCurrentNetworkType() {
    NetworkInfo info = getActiveNetworkInfo();
    if (info != null) {
      switch (info.getType()) {
        case ConnectivityManager.TYPE_MOBILE:
          return getMobileNetworkType(info);
        case ConnectivityManager.TYPE_WIFI:
          return NetworkType.NETWORK_WIFI;
        case ConnectivityManager.TYPE_ETHERNET:
          return NetworkType.NETWORK_ETHERNET;
        case ConnectivityManager.TYPE_VPN:
          return NetworkType.VPN;
        case ConnectivityManager.TYPE_BLUETOOTH:
          return NetworkType.BLUE_TOOTH;
        default:
          return NetworkType.NETWORK_UNKNOWN;
      }
    }
    return NetworkType.NETWORK_UNKNOWN;
  }


  /**
   * 获取可用网络环境
   */
  @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
  private static NetworkInfo getActiveNetworkInfo() {
    ConnectivityManager cm =
            (ConnectivityManager) BaseApp.getInstance().getContext().getSystemService(
                    Context.CONNECTIVITY_SERVICE);
    if (cm == null) return null;
    return cm.getActiveNetworkInfo();
  }

  /**
   * 在移动网络状态下，返回网络
   */
  @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
  private static NetworkType getMobileNetworkType(NetworkInfo info) {
    if (info != null && info.isAvailable()) {
      switch (info.getSubtype()) {
        case TelephonyManager.NETWORK_TYPE_GSM:
        case TelephonyManager.NETWORK_TYPE_GPRS:
        case TelephonyManager.NETWORK_TYPE_CDMA:
        case TelephonyManager.NETWORK_TYPE_EDGE:
        case TelephonyManager.NETWORK_TYPE_1xRTT:
        case TelephonyManager.NETWORK_TYPE_IDEN:
          return NetworkType.NETWORK_2G;

        case TelephonyManager.NETWORK_TYPE_TD_SCDMA:
        case TelephonyManager.NETWORK_TYPE_EVDO_A:
        case TelephonyManager.NETWORK_TYPE_UMTS:
        case TelephonyManager.NETWORK_TYPE_EVDO_0:
        case TelephonyManager.NETWORK_TYPE_HSDPA:
        case TelephonyManager.NETWORK_TYPE_HSUPA:
        case TelephonyManager.NETWORK_TYPE_HSPA:
        case TelephonyManager.NETWORK_TYPE_EVDO_B:
        case TelephonyManager.NETWORK_TYPE_EHRPD:
        case TelephonyManager.NETWORK_TYPE_HSPAP:
          return NetworkType.NETWORK_3G;

        case TelephonyManager.NETWORK_TYPE_IWLAN:
        case TelephonyManager.NETWORK_TYPE_LTE:
          return NetworkType.NETWORK_4G;

        default:
          String subtypeName = info.getSubtypeName();
          if (subtypeName.equalsIgnoreCase("TD-SCDMA")
                  || subtypeName.equalsIgnoreCase("WCDMA")
                  || subtypeName.equalsIgnoreCase("CDMA2000")) {
            return NetworkType.NETWORK_3G;
          }
      }
    }
    return NetworkType.NETWORK_UNKNOWN;
  }
}
