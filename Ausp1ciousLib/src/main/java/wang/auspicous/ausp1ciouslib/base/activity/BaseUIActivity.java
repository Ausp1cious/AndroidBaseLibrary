package wang.auspicous.ausp1ciouslib.base.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.gyf.barlibrary.ImmersionBar;

import androidx.annotation.ColorInt;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import wang.auspicous.ausp1ciouslib.R;
import wang.auspicous.ausp1ciouslib.utils.sharedpreferences.SpUtils;

/**
 * Created by Ausp1cious on 2019/2/12.
 */
public abstract class BaseUIActivity extends BaseSwipeBackActivity {
  private RelativeLayout mRootView;
  private View mStatusBar;
  private LinearLayout mContaiView;
  private Unbinder mBind;
  protected ImmersionBar mImmersionBar;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_base_common_layout);
    mBind = ButterKnife.bind(this);
    initView();
    setImmersionBar();
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    mBind.unbind();
  }

  /**
   * 初始化根布局
   */
  private void initView() {
    //寻找布局
    mRootView = findViewById(R.id.rl_root_layout);
    mStatusBar = findViewById(R.id.view_status_bar);
    mContaiView = findViewById(R.id.ll_container_view);

  }

  /**
   * 设置沉浸式状态栏
   */
  private void setImmersionBar() {
    mImmersionBar = ImmersionBar.with(this)
            .statusBarDarkFont(isStatusBarDarkFont())
            .statusBarView(mStatusBar);
    mImmersionBar.init();
    if (useImmersionBar()) {
      mStatusBar.setVisibility(View.GONE);
    }else {
      mStatusBar.setVisibility(View.VISIBLE);
      mStatusBar.setBackgroundColor(setStatusBarColor());
    }
  }

  /**
   * 是否使用沉浸式状态栏
   * @return false-不使用（默认） true-使用
   */
  protected boolean useImmersionBar() {
    return false;
  }

  /**
   * 设置状态栏的颜色
   * @return 状态栏的颜色，必须是@ColorInt
   */
  protected @ColorInt int  setStatusBarColor() {
    return ContextCompat.getColor(this, R.color.white);
  }

  /**
   * 是否状态栏的字体是深色字体
   * @return true-状态栏字体是深色字体(默认是深色字体) false-状态栏字体是浅色字体
   */
  protected boolean isStatusBarDarkFont() {
    return SpUtils.getStatusBarDarkFont(null);
  }
}
