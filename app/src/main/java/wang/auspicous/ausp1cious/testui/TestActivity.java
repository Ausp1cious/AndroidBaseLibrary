package wang.auspicous.ausp1cious.testui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import wang.auspicous.ausp1cious.R;
import wang.auspicous.ausp1ciouslib.base.activity.BaseSwipeBackActivity;
import wang.auspicous.ausp1ciouslib.component.eventbus.EventBusMessageCenter;
import wang.auspicous.ausp1ciouslib.component.eventbus.EventBusStickMessageCenter;

public class TestActivity extends BaseSwipeBackActivity {
  private static final String TAG = "MainActivity";
  @BindView(R.id.id_status_bar)
  View idStatusBar;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_test);
    ImmersionBar.with(this).statusBarView(idStatusBar).init();
    EventBusMessageCenter.post(1);
  }

  @Override
  public void onGetStickMessageEvent(EventBusStickMessageCenter stickEvent) {
    super.onGetStickMessageEvent(stickEvent);
    Log.i(TAG, "onGetStickMessageEvent: ");
  }
}
