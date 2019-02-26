package wang.auspicous.ausp1cious.testui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;
import wang.auspicous.ausp1cious.R;
import wang.auspicous.ausp1cious.fragment.TestFragment;
import wang.auspicous.ausp1ciouslib.base.activity.BaseSwipeBackActivity;
import wang.auspicous.ausp1ciouslib.base.activity.BaseUIActivity;
import wang.auspicous.ausp1ciouslib.component.activitylifecyle.ActivityStacks;
import wang.auspicous.ausp1ciouslib.component.eventbus.EventBusMessageCenter;
import wang.auspicous.ausp1ciouslib.component.eventbus.EventBusStickMessageCenter;

public class TestActivity extends BaseUIActivity {
  private static final String TAG = "MainActivity";
  @BindView(R.id.id_status_bar)
  View idStatusBar;

  @BindView(R.id.fl_frame)
  FrameLayout flLayout;
  private TestFragment mNewInstance;

  @Override
  protected int setContainerView() {
    return R.layout.activity_test;
  }

  @Override
  protected void initValue() {
    mNewInstance = TestFragment.getNewInstance();
    getSupportFragmentManager().beginTransaction().replace(R.id.fl_frame, mNewInstance).commit();
  }

  @Override
  protected void initWidget() {

  }

  @Override
  protected void initListener() {

  }


  @Override
  public void onGetStickMessageEvent(EventBusStickMessageCenter stickEvent) {
    super.onGetStickMessageEvent(stickEvent);
  }
}
