package wang.auspicous.ausp1ciouslib.utils.deviceutils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.format.Formatter;

import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;

import androidx.annotation.RequiresApi;
import androidx.annotation.RequiresPermission;

import wang.auspicous.ausp1ciouslib.base.BaseApplication;

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

  @RequiresApi(api = Build.VERSION_CODES.M)
  public static NetworkAvailable isNetworkAvailable() {
    ConnectivityManager connMgr = (ConnectivityManager) BaseApplication.getInstance().getContext().getSystemService(
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
            (ConnectivityManager) BaseApplication.getInstance().getContext().getSystemService(
                    Context.CONNECTIVITY_SERVICE);
    if (cm == null) return null;
    return cm.getActiveNetworkInfo();
  }

  /**
   * 在移动网络状态下，返回网络类型
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

  /**
   * 获取IP地址
   * @param useIPv4 是否使用ipv4
   * @return ip地址
   */
  @RequiresPermission(Manifest.permission.INTERNET)
  public static String getIPAddress(final boolean useIPv4) {
    try {
      Enumeration<NetworkInterface> nis = NetworkInterface.getNetworkInterfaces();
      LinkedList<InetAddress> adds = new LinkedList<>();
      while (nis.hasMoreElements()) {
        NetworkInterface ni = nis.nextElement();
        // To prevent phone of xiaomi return "10.0.2.15"
        if (!ni.isUp() || ni.isLoopback()) continue;
        Enumeration<InetAddress> addresses = ni.getInetAddresses();
        while (addresses.hasMoreElements()) {
          adds.addFirst(addresses.nextElement());
        }
      }
      for (InetAddress add : adds) {
        if (!add.isLoopbackAddress()) {
          String hostAddress = add.getHostAddress();
          boolean isIPv4 = hostAddress.indexOf(':') < 0;
          if (useIPv4) {
            if (isIPv4) return hostAddress;
          } else {
            if (!isIPv4) {
              int index = hostAddress.indexOf('%');
              return index < 0
                      ? hostAddress.toUpperCase()
                      : hostAddress.substring(0, index).toUpperCase();
            }
          }
        }
      }
    } catch (SocketException e) {
      e.printStackTrace();
    }
    return "";
  }

  /**
   * 获取广播地址
   * @return 广播地址
   */
  public static String getBroadcastIpAddress() {
    try {
      Enumeration<NetworkInterface> nis = NetworkInterface.getNetworkInterfaces();
      LinkedList<InetAddress> adds = new LinkedList<>();
      while (nis.hasMoreElements()) {
        NetworkInterface ni = nis.nextElement();
        if (!ni.isUp() || ni.isLoopback()) continue;
        List<InterfaceAddress> ias = ni.getInterfaceAddresses();
        for (int i = 0, size = ias.size(); i < size; i++) {
          InterfaceAddress ia = ias.get(i);
          InetAddress broadcast = ia.getBroadcast();
          if (broadcast != null) {
            return broadcast.getHostAddress();
          }
        }
      }
    } catch (SocketException e) {
      e.printStackTrace();
    }
    return "";
  }

  /**
   * 获取域名地址
   * @param domain 域名
   * @return 域名地址
   */
  @RequiresPermission(Manifest.permission.INTERNET)
  public static String getDomainAddress(final String domain) {
    InetAddress inetAddress;
    try {
      inetAddress = InetAddress.getByName(domain);
      return inetAddress.getHostAddress();
    } catch (UnknownHostException e) {
      e.printStackTrace();
      return "";
    }
  }

  /**
   * 通过Wifi获取ip
   */
  @RequiresPermission(Manifest.permission.ACCESS_WIFI_STATE)
  public static String getIpAddressByWifi() {
    @SuppressLint("WifiManagerLeak")
    WifiManager wm = (WifiManager) BaseApplication.getInstance().getContext().getSystemService(Context.WIFI_SERVICE);
    if (wm == null) return "";
    return Formatter.formatIpAddress(wm.getDhcpInfo().ipAddress);
  }

  /**
   * 通过Wifi获取网关
   */
  @RequiresPermission(Manifest.permission.ACCESS_WIFI_STATE)
  public static String getGatewayByWifi() {
    @SuppressLint("WifiManagerLeak")
    WifiManager wm = (WifiManager) BaseApplication.getInstance().getContext().getSystemService(Context.WIFI_SERVICE);
    if (wm == null) return "";
    return Formatter.formatIpAddress(wm.getDhcpInfo().gateway);
  }

  /**
   *  通过Wifi获取掩码
   */
  @RequiresPermission(Manifest.permission.ACCESS_WIFI_STATE)
  public static String getNetMaskByWifi() {
    @SuppressLint("WifiManagerLeak")
    WifiManager wm = (WifiManager) BaseApplication.getInstance().getContext().getSystemService(Context.WIFI_SERVICE);
    if (wm == null) return "";
    return Formatter.formatIpAddress(wm.getDhcpInfo().netmask);
  }

  /**
   * 通过Wifi获取服务地址
   */
  @RequiresPermission(Manifest.permission.ACCESS_WIFI_STATE)
  public static String getServerAddressByWifi() {
    @SuppressLint("WifiManagerLeak")
    WifiManager wm = (WifiManager) BaseApplication.getInstance().getContext().getSystemService(Context.WIFI_SERVICE);
    if (wm == null) return "";
    return Formatter.formatIpAddress(wm.getDhcpInfo().serverAddress);
  }
}
