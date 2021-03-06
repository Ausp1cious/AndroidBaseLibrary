package wang.auspicous.ausp1ciouslib.base.activity;

import android.Manifest;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;


import androidx.annotation.Nullable;

import com.gyf.immersionbar.ImmersionBar;
import com.gyf.immersionbar.OSUtils;

import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.Utils;
import me.imid.swipebacklayout.lib.app.SwipeBackActivityBase;
import me.imid.swipebacklayout.lib.app.SwipeBackActivityHelper;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;
import wang.auspicous.ausp1ciouslib.Constants;
import wang.auspicous.ausp1ciouslib.R;
import wang.auspicous.ausp1ciouslib.utils.deviceutils.VibrateUtils;
import wang.auspicous.ausp1ciouslib.utils.sharedpreferences.SpUtils;

/**
 * Created by Ausp1cious on 2019/1/30.
 */
public abstract class BaseSwipeBackActivity extends BaseEventActivity implements
        SwipeBackActivityBase {
  //滑动退出震动时间
  private static final int VIBRATE_DURATION = 20;
  private SwipeBackActivityHelper mHelper;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mHelper = new SwipeBackActivityHelper(this);
    mHelper.onActivityCreate();
    initSwipeBack();
    initImmersionBar();
  }

  @Override
  protected void onPostCreate(Bundle savedInstanceState) {
    super.onPostCreate(savedInstanceState);
    mHelper.onPostCreate();
  }

  @Override
  protected void onResume() {
    super.onResume();
    if (OSUtils.isEMUI3_x()) {
      ImmersionBar.with(this).init();
    }
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
//    ImmersionBar.with(this).destroy();  不需要销毁了？
  }

  @Override
  public void onConfigurationChanged(Configuration newConfig) {
    super.onConfigurationChanged(newConfig);
    ImmersionBar.with(this).init();
  }

  @Override
  public SwipeBackLayout getSwipeBackLayout() {
    return mHelper.getSwipeBackLayout();
  }

  @Override
  public void setSwipeBackEnable(boolean enable) {
    getSwipeBackLayout().setEnableGesture(enable);
  }

  @Override
  public void scrollToFinishActivity() {
    Utils.convertActivityToTranslucent(this);
    getSwipeBackLayout().scrollToFinishActivity();
  }

  /**
   * 是否允许滑动退出
   * 默认配置不支持4.4滑动退出
   * @return true-允许 false-不允许
   */
  protected boolean canSwipeBack() {
    return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && SpUtils.getCanSwipeBack(null);
  }

  /**
   * 初始化设置
   */
  private void initSwipeBack() {
    getSwipeBackLayout().setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);
    getSwipeBackLayout().setEnableGesture(canSwipeBack());
    getSwipeBackLayout().addSwipeListener(new SwipeBackLayout.SwipeListener() {
      @Override
      public void onScrollStateChange(int state, float scrollPercent) {

      }

      @Override
      public void onEdgeTouch(int edgeFlag) {
        askPermissionForVibrate();
      }

      @Override
      public void onScrollOverThreshold() {
        askPermissionForVibrate();
      }
    });
  }

  /**
   * 配置状态栏
   */
  private void initImmersionBar() {
    ImmersionBar.with(this).init();
  }


  private void vibrateWhenSwipeBack() {
    if (SpUtils.getCanSwipeBackWithVibrate(null)) {
      VibrateUtils.vibrate(BaseSwipeBackActivity.this, VIBRATE_DURATION);
    }
  }

  /**
   * 震动的权限请求（其实可以不用申请震动权限的）
   */
  @AfterPermissionGranted(Constants.Permission.PERMISSION_VIBRATE)
  private void askPermissionForVibrate() {
    String[] perms = {Manifest.permission.VIBRATE};
    if (EasyPermissions.hasPermissions(this, perms)) {
      vibrateWhenSwipeBack();
    } else {
      EasyPermissions.requestPermissions(this, getString(R.string.permission_vibrate),
              Constants.Permission.PERMISSION_VIBRATE, perms);
    }
  }
}
