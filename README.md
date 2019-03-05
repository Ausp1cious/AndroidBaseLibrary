Android 基础库
==================

构建Android应用的基础Library包。

目的：提供对业务逻辑的快速支持

# 基本功能

## 左滑退出

基于swipeback库。

封装在BaseSwipeBackActivity中。默认是开启滑动退出。如果不需要，覆写`canSwipeBack()`方法，返回false。

全局开关控制：`SpUtils`->`setCanSwipeBack`

滑动退出震动提示开关控制：`SpUtils`->`setSwipeBackWithVibrate`

## 权限申请管理

基于easyPermission库

封装在BasePermissionActivity中。使用方式参照easePermission。在`Constants`定了权限的状态码。

## 沉浸式状态栏

基于ImmersionBar库

封装在BaseUIActivity中

设置沉浸式状态栏：覆写方法`useImmersionBar`:返回`true`表示使用沉浸式状态栏; 返回`false`表示不使用沉浸式状态栏（默认不使用）

设置状态栏的颜色:覆写方法`setStatusBarColor`:返回`@ColorInt`类型的颜色。默认状态栏的颜色是白色

状态栏字体设置（深色/浅色):覆写方法`isStatusBarDarkFont`,返回true表示深色；返回false表示浅色。通过修改`SpUtils`->`setIsStatusBarDarkFont`来改变状态栏的颜色（按照常理来说，一般只会改变一次这个全局变量,且在加载第一个页面之前使用）

## 基本布局管理

根布局是由`RelativeLayout`,子布局是一个`LinearLayout`。然后动态添加(TitleBar和要挂载的rootView)布局到`LinearLayout`。LoadingView加载到`RelativeLayout`中。

### TitleBar设置
封装在BaseUIActivity中

有两种方式：
1. XML的方式添加TitleBar:覆写`setHeaderLayoutView`方法，返回一个需要加载的XML
2. View 的方式添加TitleBar：覆写`setHeaderView`方法，返回一个View
第1种方式的优先级高于第2种

获取设置的TitleBar:`setHeaderView`

如果需要通用的TitleBar 需要在BaseUIActivity的基础上再次封装特定的TitleBar

### 根布局的添加
封装在BaseUIActivity中

有两种方式：
1. XML的方式添加根布局:覆写`setContainerView`,返回布局Layout
2. View的方式添加根布局：覆写`setContainerRootView`，返回布局View
第1种方式的优先级高于第2种

使用`getContainerRootView`方法获取根布局

### Loading
封装在BaseUIActivity中

使用接口`ILoading`来实现具体的显示隐藏功能，指定LoadingView或者LoadingDialog。在实现的时候需要实现接口`ILoading`，并且返回具体的LoadingView或者LoadingDialog。

显示Loading的方法：`showLoadingView`

隐藏Loading的方法：`hideLoadingView`

### 关于ButterKnife
封装在BaseUIActivity中

将它的绑定和解绑放在具体的业务逻辑BaseActivity中了。

具体使用方法：
    - 绑定：覆写方法`bindButterKnife`
    - 解绑：覆写方法`unBindButterKnife`

## 网络环境管理
封装在BaseNetEvnActivity中

开关：
`openNetworkMonitor` true 开启网络监听 false 关闭网络监听。 在Activity中默认开始，在Fragment中默认关闭

某些覆写的方法可能会有重复，某些方法可能是6.0以上版本才支持的。
- `startIn2G`
- `startIn3G`
- `startIn4G`
- `startInWifi`
- `startInEthernet`
- `startInBlueTooth`
- `startInVPN`
- `startInUNKNOWN`
- `onNetworkDisconnected`
- `onWifiConnected`
- `onMobileDataTrafficConnected`
- `on4GConnected`
- `onNetworkAvailable`
- `onNetworkNotAvailable`
- `onCellularAvailable`
- `onWIFIAvailable`
- `onBluetoothAvailable`
- `onEthernetAvailable`
- `onVPNAvailable`
- `onLOWPANAvailable`

## EvenBus
封装在BaseEventActivity中

总开关`enableEventBus` 覆写方法返回true-允许使用（默认）EventBus false-不允许

发送事件：`EventBusMessageCenter.post`

发送stickEvent：`EventBusStickMessageCenter.post`

接收事件:覆写`onGetMessageEvent`后处理逻辑

接收stickEvent:覆写`onGetStickMessageEvent`后处理逻辑

## 栈管理工具
封装在ActivityStacks中，与`AppMonitorImpl`相结合，封装Activity的入栈和出栈。

## Activity管理的相关方法：
- `isStacksTop` 判断Activity是否在栈顶
- `moveActivityTopStacksTop`将制定Activity移动至栈顶，在它之上的Activity全部移除
- `findActivity`查找任务栈的Activity
- `removeAllKeepOnlyOne` 只保留一个Activity
- `exitApp` 删除全部的Activity

## MVP模式
MVPActivity,BasePresenterImpl以及BaseContract的组合来实现MVP模式

BaseContact 用于Activity和Presenter的接口逻辑控制
BaseActivity 用于界面的具体实现类
BasePresenterImpl  Presenter的具体实现类

# App状态监控
实现接口:IAppMonitor ->AppMonitorImpl

- `onLastActivityFinish` 最后一个ActivityFinish的时候的回调
- `onUIRunInBackground` 程序界面运行在后台的时候的回调
- `onActivityBackInForeBackground` 程序重后台回到前台时的回调

# Fragment懒加载
BaseLazyLoadFragment 中
- `onFragmentFirstVisible` 当Fragment第一次可见的时候
- `onFragmentResume` 当Fragment 可见的时候
- `onFragmentPause` 当Fragment 不可见的时候
- `whetherLazyLoad` 是否是用懒加载。 如果使用懒加载，控件的初始化，数据请求都会放于Fragment第一次可见的时候

# Utils
## VibrateUtils
- `vibrate` 开始震动
- `cancelVibrate` 取消震动
## NetworkUtils
- `getCurrentNetworkType`返回当前网络环境
- `getMobileNetworkType`在移动网络状态下，返回网络类型
- `getIPAddress`获取IP地址
- `getBroadcastIpAddress`获取广播地址
- `getDomainAddress`获取域名地址
- `getIpAddressByWifi`通过Wifi获取ip
- `getGatewayByWifi`通过Wifi获取网关
- `getNetMaskByWifi`通过Wifi获取掩码
- `getServerAddressByWifi`通过Wifi获取服务地址

# 关于打包
基本的配置在app的`build.gradle`中。 混淆的时候，需要将`Ausp1ciousLib`中的混淆文件复制到app中

[todo]软键盘弹起后，状态栏的相关设置

# 使用3方包
- rxjava2
- rxAndroid
- logger
- immersionBar
- swipeback
- easyPermission

# TODO
- 语言切换功能
