Android 基础库
==================

构建Android应用的基础Library包。

目的：提供对业务逻辑的快速支持

# 基本功能

## 左滑退出

基于swipeback库。

封装在BaseSwipeBackActivity中。默认是开启滑动退出。如果不需要，覆写`canSwipeBack()`方法，返回false。

## 权限申请管理

基于easyPermission库

封装在BasePermissionActivity中。使用方式参照easePermission。在`Constants`定了权限的状态码。

# 使用3方包
- rxjava2
- rxAndroid
- logger
- immersionBar
- swipeback
- easyPermission