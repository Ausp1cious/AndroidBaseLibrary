package wang.auspicous.ausp1ciouslib.base.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.gyf.barlibrary.ImmersionBar;

import androidx.annotation.ColorInt;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import wang.auspicous.ausp1ciouslib.R;
import wang.auspicous.ausp1ciouslib.utils.sharedpreferences.SpUtils;

/**
 * Created by Ausp1cious on 2019/2/25.
 */
public abstract class BaseUIFragment extends BaseLazyLoadFragment {
    protected ImmersionBar mImmersionBar;
    private View mFragmentView;
    //基础根布局
    private RelativeLayout mRootView;
    private View mStatusBar;
    private LinearLayout mContainerView;

    //加载布局
    private View mHeaderView;//TitleBar
    private View mContainerRootView; //实际加载的根布局
    // TODO: 2019/2/28 绑定ButterKnife

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initValue();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            Bundle savedInstanceState) {
        if (mFragmentView == null) {
            initView(inflater, container);
            setImmersionBar();
            initInflateView(inflater);
            if (!whetherLazyLoad()) {
                initWidget();//初始化控件
                initListener();//初始化监听
//            setILoading(new BaseLoadingView());
            }
        }
        ViewGroup mViewGroup = (ViewGroup) mFragmentView.getParent();
        if (null != mViewGroup) {
            mViewGroup.removeView(mFragmentView);
        }
        return mFragmentView;
    }

    /**
     * 初始化BaseFragmentView
     */
    private void initView(LayoutInflater inflater, @Nullable ViewGroup container) {
        mFragmentView = inflater.inflate(R.layout.activity_base_common_layout, container, false);
        //寻找布局
        mRootView = mFragmentView.findViewById(R.id.rl_root_layout);
        mStatusBar = mFragmentView.findViewById(R.id.view_status_bar);
        mContainerView = mFragmentView.findViewById(R.id.ll_container_view);
    }

    private void initInflateView(LayoutInflater inflater) {
        addHeadView(inflater);
        addContainerRootView(inflater);
    }

//////////////////////////////////////////////////////////////////////////////
    //
    //TitleBar（Start）
    //
//////////////////////////////////////////////////////////////////////////////

    /**
     * 添加TitleBar
     */
    private void addHeadView(LayoutInflater inflater) {
        if (setHeaderLayoutView() != -1) {
            mHeaderView = inflater.inflate(setHeaderLayoutView(), null);
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
    private void addContainerRootView(LayoutInflater inflater) {
        if (setContainerView() != 0) {
            mContainerRootView = inflater.inflate(setContainerView(), null);
        } else {
            mContainerRootView = setContainerRootView();
        }
        if (mContainerRootView != null) {
            mContainerView.addView(mContainerRootView, -1, -2);
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
     * @return false-不使用 true-使用（默认）
     */
    protected boolean useImmersionBar() {
        return true;
    }

    /**
     * 设置状态栏的颜色
     *
     * @return 状态栏的颜色，必须是@ColorInt
     */
    protected @ColorInt int setStatusBarColor() {
//        return ContextCompat.getColor(this, R.color.white);
        if (getContext() != null) {
            return ContextCompat.getColor(getContext(), R.color.white);
        } else {
            return -1;
        }
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
    //Fragment懒加载（Start）
    //
//////////////////////////////////////////////////////////////////////////////

    @Override
    void onFragmentFirstVisible() {
        if (whetherLazyLoad()) {
            initWidget();//初始化控件
            initListener();//初始化监听
        }
    }

//////////////////////////////////////////////////////////////////////////////
    //
    //Fragment懒加载（Start）
    //
//////////////////////////////////////////////////////////////////////////////

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


}
