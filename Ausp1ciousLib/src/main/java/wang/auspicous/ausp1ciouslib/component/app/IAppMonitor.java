package wang.auspicous.ausp1ciouslib.component.app;

/**
 * Created by Ausp1cious on 2019/2/18.
 */
public interface IAppMonitor {
  /**
   * 当最后一个Activity Finish的时候
   */
  void onLastActivityFinish();

  /**
   * 当Activity运行在后台时
   */
  void onUIRunInBackground();

  /**
   * 当Activity从后台运行切换到前台运行时
   */
  void onActivityBackInForeBackground();
}
