package wang.auspicous.ausp1cious;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import wang.auspicous.ausp1ciouslib.base.activity.BaseUIActivity;

public class MainActivity extends BaseUIActivity {
  private static final String TAG = "MainActivity";
  @BindView(R.id.btn_show)
  Button btnShow;
  @BindView(R.id.btn_hide)
  Button btnHide;
  private Unbinder mBind;


//  @Override
//  protected void onCreate(Bundle savedInstanceState) {
//    super.onCreate(savedInstanceState);
//    setContentView(R.layout.activity_main);
//    btnTurn.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, TestActivity
// .class)));
//  }
//
  @Override
  protected void bindButterKnife() {
    mBind = ButterKnife.bind(MainActivity.this);
  }

  @Override
  protected void unBindButterKnife() {
    mBind.unbind();
  }

  @Override
  protected int setContainerView() {
    return R.layout.activity_test;
  }

  @Override
  protected void initValue() {

  }

  @Override
  protected void initWidget() {
//    btnShow = findViewById(R.id.btn_show);
//    btnHide = findViewById(R.id.btn_hide);
  }

  @Override
  protected void initListener() {
    btnShow.setOnClickListener(v -> showLoadingView());
    btnHide.setOnClickListener(v -> hideLoadingView());
  }

  @Override
  protected int setHeaderLayoutView() {
    return R.layout.layout_title;
  }

  protected void startIn2G() {
    Log.i(TAG, "startIn2G: ");    
  }

  protected void startIn3G() {
    Log.i(TAG, "startIn3G: ");
  }

  protected void startIn4G() {
    Log.i(TAG, "startIn4G: ");
  }

  protected void startInWifi() {
    Log.i(TAG, "startInWifi: ");
  }

  protected void startInEthernet() {
    Log.i(TAG, "startInEthernet: ");
  }

  protected void startInBlueTooth() {
    Log.i(TAG, "startInBlueTooth: ");
  }

  protected void startInVPN() {
    Log.i(TAG, "startInVPN: ");
  }

  protected void startInUNKNOWN() {
    Log.i(TAG, "startInUNKNOWN: ");
  }

  @Override
  public void onNetworkDisconnected() {
    Log.i(TAG, "onNetworkDisconnected: ");
  }

  @Override
  public void onWifiConnected() {
    Log.i(TAG, "onWifiConnected: ");
  }

  @Override
  public void onMobileDataTrafficConnected() {
    Log.i(TAG, "onMobileDataTrafficConnected: ");
  }

  @Override
  public void on4GConnected() {
    Log.i(TAG, "on4GConnected: ");
  }

  @Override
  public void onNetworkAvailable() {
    Log.i(TAG, "onNetworkAvailable: ");
  }

  @Override
  public void onNetworkNotAvailable() {
    Log.i(TAG, "onNetworkNotAvailable: ");
  }

  @Override
  public void onCellularAvailable() {
    Log.i(TAG, "onCellularAvailable: ");
  }

  @Override
  public void onWIFIAvailable() {
    Log.i(TAG, "onWIFIAvailable: ");
  }

  @Override
  public void onBluetoothAvailable() {
    Log.i(TAG, "onBluetoothAvailable: ");
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
