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
