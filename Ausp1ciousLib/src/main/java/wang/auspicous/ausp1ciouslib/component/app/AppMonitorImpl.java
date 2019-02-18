package wang.auspicous.ausp1ciouslib.component.app;

import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;

import wang.auspicous.ausp1ciouslib.component.eventbus.EventBusStickMessageCenter;

/**
 * Created by Ausp1cious on 2019/2/18.
 */
public class AppMonitorImpl implements IAppMonitor {
  @Override
  public void onLastActivityFinish() {
    //运行在主线程
    EventBusStickMessageCenter stickyEvent = EventBus.getDefault().getStickyEvent(
            EventBusStickMessageCenter.class);
    if (stickyEvent != null) {
      EventBus.getDefault().removeStickyEvent(stickyEvent);
    }
  }

  @Override
  public void onUIRunInBackground() {
      Logger.t("App状态监控").i("运行在Background");
  }

  @Override
  public void onActivityBackInForeBackground() {
    // TODO: 2019/2/18 UI重新运行到前台时
    Logger.t("App状态监控").i("重新运行到前台");
  }
}
