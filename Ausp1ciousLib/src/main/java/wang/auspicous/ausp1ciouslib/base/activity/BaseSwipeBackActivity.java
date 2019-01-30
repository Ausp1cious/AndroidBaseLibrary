package wang.auspicous.ausp1ciouslib.base.activity;

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
import wang.auspicous.ausp1ciouslib.R;

/**
 * Created by Ausp1cious on 2019/1/30.
 */
public abstract class BaseSwipeBackActivity extends AppCompatActivity implements
        SwipeBackActivityBase {
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
        // TODO: 2019/1/30 滑动退出震动配置
      }

      @Override
      public void onScrollOverThreshold() {

      }
    });
  }

  /**
   * 配置状态栏
   */
  private void initImmersionBar() {
    ImmersionBar.with(this).init();
  }


}
