package wang.auspicous.ausp1ciouslib.base.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
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
 * 处理根布局
 * 处理沉浸式
 * 处理TitleBar
 * 处理根布局
 */
public abstract class BaseUIActivity extends BaseSwipeBackActivity {
  private Unbinder mBind;
  protected ImmersionBar mImmersionBar;
  //基础根布局
  private RelativeLayout mRootView;
  private View mStatusBar;
  private LinearLayout mContainerView;
  //加载布局
  private View mHeaderView;//TitleBar
  private View mContainerRootView; //实际加载的根布局
  private View mLoadingView;//LoadingView
  private Dialog mLoadingDialog;//LoadingDialog


  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_base_common_layout);
    mBind = ButterKnife.bind(this);
    initValue();//初始化状态值
    initView();
    setImmersionBar();
    initInflateView();
    initWidget();//初始化控件
    initListener();//初始化监听
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    mBind.unbind();
  }

  /**
   * 添加根布局的基本方式
   * @return 根布局LayoutID
   */
  protected abstract int setContainerView();
  /**
   * 初始化状态值
   */
  protected abstract void initValue();

  /**
   * 初始化控件
   */
  protected abstract void initWidget();

  /**
   * 初始化监听
   */
  protected abstract void initListener();

//////////////////////////////////////////////////////////////////////////////
  //
  //处理根布局（Start）
  //
//////////////////////////////////////////////////////////////////////////////
  /**
   * 初始化根布局
   */
  private void initView() {
    //寻找布局
    mRootView = findViewById(R.id.rl_root_layout);
    mStatusBar = findViewById(R.id.view_status_bar);
    mContainerView = findViewById(R.id.ll_container_view);
  }

  /**
   * 初始化加载View(TitleBar,loading等)
   */
  private void initInflateView() {
    addHeadView();
    addContainerRootView();
  }

  /**
   * 加载布局
   *
   * @param resLayoutID 布局LayoutID
   */
  private View inflate(int resLayoutID) {
    return LayoutInflater.from(this).inflate(resLayoutID, null);
  }
//////////////////////////////////////////////////////////////////////////////
  //
  //处理根布局（End）
  //
//////////////////////////////////////////////////////////////////////////////


//////////////////////////////////////////////////////////////////////////////
  //
  //处理沉浸式（Start）
  //
//////////////////////////////////////////////////////////////////////////////
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
    } else {
      mStatusBar.setVisibility(View.VISIBLE);
      mStatusBar.setBackgroundColor(setStatusBarColor());
    }
  }

  /**
   * 是否使用沉浸式状态栏
   *
   * @return false-不使用（默认） true-使用
   */
  protected boolean useImmersionBar() {
    return false;
  }

  /**
   * 设置状态栏的颜色
   *
   * @return 状态栏的颜色，必须是@ColorInt
   */
  protected @ColorInt
  int setStatusBarColor() {
    return ContextCompat.getColor(this, R.color.white);
  }

  /**
   * 是否状态栏的字体是深色字体
   *
   * @return true-状态栏字体是深色字体(默认是深色字体) false-状态栏字体是浅色字体
   */
  protected boolean isStatusBarDarkFont() {
    return SpUtils.getStatusBarDarkFont(null);
  }
//////////////////////////////////////////////////////////////////////////////
  //
  //处理沉浸式（End）
  //
//////////////////////////////////////////////////////////////////////////////


//////////////////////////////////////////////////////////////////////////////
  //
  //TitleBar（Start）
  //
//////////////////////////////////////////////////////////////////////////////
  /**
   * 添加TitleBar
   */
  private void addHeadView() {
    if (setHeaderLayoutView() != -1) {
      mHeaderView = inflate(setHeaderLayoutView());
    } else {
      mHeaderView = getHeaderView();
    }
    if (mHeaderView != null) {
      mContainerView.addView(mHeaderView, -1, -2);
    }
  }

  /**
   * XML的方式添加TitleBar
   * @return TitleBar布局xml
   */
  protected int setHeaderLayoutView() {
    return -1;
  }

  /**
   * View 的方式添加TitleBar
   * @return View
   */
  protected View setHeaderView() {
    return null;
  }

  /**
   * 获取设置的HeaderView
   * @return 设置的HeaderView
   */
  @Nullable
  protected View getHeaderView() {
    return mHeaderView;
  }
//////////////////////////////////////////////////////////////////////////////
  //
  //TitleBar（End）
  //
//////////////////////////////////////////////////////////////////////////////


//////////////////////////////////////////////////////////////////////////////
  //
  //根布局（Start）
  //
//////////////////////////////////////////////////////////////////////////////
  /**
   * 添加ContainerRootView
   */
  private void addContainerRootView() {
    if (setContainerView() != 0) {
      mContainerRootView = inflate(setContainerView());
    } else {
      mContainerRootView = setContainerRootView();
    }
    if (mContainerRootView != null) {
      mContainerView.addView(mContainerRootView, -1, -2);
    }
  }

  /**
   * View 的方式添加TitleBar
   * @return View
   */
  protected View setContainerRootView() {
    return null;
  }

  /**
   * 获取设置的ContainerRootView
   * @return 设置的ContainerRootView
   */
  @Nullable
  protected View getContainerRootView() {
    return mContainerRootView;
  }
//////////////////////////////////////////////////////////////////////////////
  //
  //根布局（End）
  //
//////////////////////////////////////////////////////////////////////////////


}
