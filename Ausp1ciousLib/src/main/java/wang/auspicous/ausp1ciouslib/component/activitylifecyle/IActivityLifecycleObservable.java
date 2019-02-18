package wang.auspicous.ausp1ciouslib.component.activitylifecyle;

/**
 * Created by Ausp1cious on 2019/2/18.
 */
public interface IActivityLifecycleObservable {
  /**
   * 当所有Activity销毁的时候
   */
  void onActivityAllDestroyed();

  /**
   * 当Activity运行在后台时
   */
  void onActivityRunInBackground();

  /**
   * 当Activity从后台运行切换到前台运行时
   */
  void onActivityBackInForeBackground();
}
