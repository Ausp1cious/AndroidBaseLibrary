package wang.auspicous.ausp1ciouslib.base.activity;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gyf.immersionbar.ImmersionBar;
import com.orhanobut.logger.Logger;

import androidx.annotation.ColorInt;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import wang.auspicous.ausp1ciouslib.R;
import wang.auspicous.ausp1ciouslib.component.loading.BaseLoadingView;
import wang.auspicous.ausp1ciouslib.component.loading.ILoading;
import wang.auspicous.ausp1ciouslib.utils.SoftKeyBoardUtils;
import wang.auspicous.ausp1ciouslib.utils.sharedpreferences.SpUtils;

/**
 * Created by Ausp1cious on 2019/2/12.
 * 处理根布局
 * 处理沉浸式
 * 处理TitleBar
 * 处理根布局
 * 处理Loading布局
 * 处理ButterKnife 页面初始化
 */
public abstract class BaseUIActivity extends BaseSwipeBackActivity {
    protected ImmersionBar mImmersionBar;
    //基础根布局
    private RelativeLayout mRootView;
    private View mStatusBar;
    private LinearLayout mContainerView;
    //加载布局
    private View mHeaderView;//TitleBar
    private View mContainerRootView; //实际加载的根布局
    private ILoading mILoading;
    private InputMethodManager mInputMethodManager;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (fullScreenMode()) {
            requestWindowFeature(Window.FEATURE_NO_TITLE); //去除标题栏
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN); //去除状态栏
        }
        setContentView(R.layout.activity_base_common_layout);
        initValue();//初始化状态值
        initView();
        setImmersionBar();
        initInflateView();
        this.bindButterKnife();//加载布局之后使用，否者将不会找到控件
        initWidget();//初始化控件
        initListener();//初始化监听

        setILoading(new BaseLoadingView());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.unBindButterKnife();
        //页面销毁的时候关闭软键盘
        SoftKeyBoardUtils.hideSoftKeyBoard(this,mInputMethodManager);
        mInputMethodManager = null;
    }

    @Override
    public void finish() {
        super.finish();
        //Finish页面的时候关闭软键盘
        SoftKeyBoardUtils.hideSoftKeyBoard(this,mInputMethodManager);
    }

    /**
     * 添加根布局的基本方式
     *
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
        if (useImmersionBar()||fullScreenMode()) {
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
     *
     * @return TitleBar布局xml
     */
    protected int setHeaderLayoutView() {
        return -1;
    }

    /**
     * View 的方式添加TitleBar
     *
     * @return View
     */
    protected View setHeaderView() {
        return null;
    }

    /**
     * 获取设置的HeaderView
     *
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
            mContainerView.addView(mContainerRootView, -1, -1);
        }
    }

    /**
     * View 的方式添加TitleBar
     *
     * @return View
     */
    protected View setContainerRootView() {
        return null;
    }

    /**
     * 获取设置的ContainerRootView
     *
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


    //////////////////////////////////////////////////////////////////////////////
    //
    //Loading布局（Start）
    //
//////////////////////////////////////////////////////////////////////////////

    /**
     * 设置Loading的具体实现类
     *
     * @param loading loading具体实现类
     */
    protected void setILoading(ILoading loading) {
        this.mILoading = loading;
        if (mILoading.getLoadingView(this) != null) {
            setLoadingView();
        } else if (mILoading.getLoadingDialog(this) != null) {
            setLoadingDialog();
        }
    }

    /**
     * 设置LoadingView
     */
    private void setLoadingView() {
        //LoadingView
        View loadingView = mILoading.getLoadingView(this);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.addRule(RelativeLayout.CENTER_IN_PARENT);
        mRootView.addView(loadingView, lp);
    }

    /**
     * 设置LoadingDialog
     */
    private void setLoadingDialog() {
        //LoadingDialog
        Dialog loadingDialog = mILoading.getLoadingDialog(this);
    }

    /**
     * 显示LoadingView
     */
    protected void showLoadingView() {
        mILoading.showLoading();
    }

    /**
     * 隐藏LoadingView
     */
    protected void hideLoadingView() {
        mILoading.hideLoading();
    }
//////////////////////////////////////////////////////////////////////////////
    //
    //Loading布局（End）
    //
//////////////////////////////////////////////////////////////////////////////


//////////////////////////////////////////////////////////////////////////////
    //
    //ButterKnife 页面初始化（Start）
    //
//////////////////////////////////////////////////////////////////////////////

    /**
     * ButterKnife绑定
     */
    protected void bindButterKnife() {

    }

    /**
     * ButterKnife解绑
     */
    protected void unBindButterKnife() {

    }
//////////////////////////////////////////////////////////////////////////////
    //
    //ButterKnife 页面初始化（End）
    //
//////////////////////////////////////////////////////////////////////////////

    /**
     * 全屏显示模式
     * @return true-全屏显示 false-不全屏显示(默认)
     */
    protected boolean fullScreenMode() {
        return false;
    }

}
