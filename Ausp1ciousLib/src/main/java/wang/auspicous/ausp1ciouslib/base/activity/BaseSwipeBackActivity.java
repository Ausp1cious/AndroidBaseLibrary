package wang.auspicous.ausp1ciouslib.base.activity;

import android.Manifest;
import android.content.res.Configuration;
import android.os.Bundle;

import com.gyf.barlibrary.ImmersionBar;
import com.gyf.barlibrary.OSUtils;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.Utils;
import me.imid.swipebacklayout.lib.app.SwipeBackActivityBase;
import me.imid.swipebacklayout.lib.app.SwipeBackActivityHelper;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;
import wang.auspicous.ausp1ciouslib.Constants;
import wang.auspicous.ausp1ciouslib.R;
import wang.auspicous.ausp1ciouslib.utils.DeviceUtils;

/**
 * Created by Ausp1cious on 2019/1/30.
 */
public abstract class BaseSwipeBackActivity extends BasePermissionActivity implements
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
    ImmersionBar.with(this).destroy();
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
   *
   * @return true-允许 false-不允许
   */
  protected boolean canSwipeBack() {
    // TODO: 2019/1/30 全局滑动退出配置
    return true;
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
    // TODO: 2019/1/31 震动全局配置开关
    DeviceUtils.vibrate(BaseSwipeBackActivity.this, VIBRATE_DURATION);
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
