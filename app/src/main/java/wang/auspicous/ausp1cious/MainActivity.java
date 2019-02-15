package wang.auspicous.ausp1cious;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import wang.auspicous.ausp1cious.testui.TestActivity;
import wang.auspicous.ausp1ciouslib.base.activity.BaseUIActivity;
import wang.auspicous.ausp1ciouslib.component.eventbus.EventBusMessageCenter;
import wang.auspicous.ausp1ciouslib.component.eventbus.EventBusStickMessageCenter;

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
    btnShow.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, TestActivity.class)));
    btnHide.setOnClickListener(v -> {
      EventBusStickMessageCenter.post(0);
    });
  }

  @Override
  protected int setHeaderLayoutView() {
    return R.layout.layout_title;
  }

  @Override
  public void onGetMessageEvent(EventBusMessageCenter event) {
    super.onGetMessageEvent(event);
    Log.i(TAG, "onGetMessageEvent: ");
  }
}
