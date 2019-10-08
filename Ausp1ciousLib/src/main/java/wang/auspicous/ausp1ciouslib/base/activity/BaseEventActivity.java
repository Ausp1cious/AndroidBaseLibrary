package wang.auspicous.ausp1ciouslib.base.activity;

import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import wang.auspicous.ausp1ciouslib.base.BaseApp;
import wang.auspicous.ausp1ciouslib.component.app.IAppMonitor;
import wang.auspicous.ausp1ciouslib.component.eventbus.EventBusMessageCenter;
import wang.auspicous.ausp1ciouslib.component.eventbus.EventBusStickMessageCenter;

/**
 * Created by Ausp1cious on 2019/2/15.
 * 基础事件Activity
 */
public abstract class BaseEventActivity extends BaseNetEvnActivity {
  @Override
  protected void onStart() {
    super.onStart();
    registerEventBus();
  }

  @Override
  protected void onStop() {
    super.onStop();

  }

  @Override
  protected void onDestroy() {
    unregisterEvenBus();
    super.onDestroy();
  }

  /**
   * 是否允许使用EvenBus
   * @return true-允许使用（默认） false-不允许使用
   */
  protected boolean enableEventBus() {
    return true;
  }

  /**
   * EventBus 消息接收的地方
   * @param event 事件
   * POSTING:从哪个线程出来，回到哪个线程
   * MAIN: 执行在主线程(默认)
   * BACKGROUND：执行在子线程
   * ASYNC：创建新子线程执行
   */
  @Subscribe(threadMode = ThreadMode.MAIN)
  public void onGetMessageEvent(EventBusMessageCenter event) {

  }


  /**
   * EventBus stickMessage 执行的地方
   * @param stickEvent stickMessage
   */
  @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
  public void onGetStickMessageEvent(EventBusStickMessageCenter stickEvent) {

  }

  /**
   * 注册EventBus
   */
  private void registerEventBus() {
    if (enableEventBus() && !EventBus.getDefault().isRegistered(this)) {
      EventBus.getDefault().register(this);
    }
  }

  /**
   * 注销EventBus
   */
  private void unregisterEvenBus() {
    if (EventBus.getDefault().isRegistered(this)) {
      EventBus.getDefault().unregister(this);
    }
  }
}
