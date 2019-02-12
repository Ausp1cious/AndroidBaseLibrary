package wang.auspicous.ausp1ciouslib.utils.sharedpreferences;

import androidx.annotation.Nullable;

/**
 * Created by Ausp1cious on 2019/2/1.
 * SharePreferenceUtils
 */
public class SpUtils {
  private static final String CAN_SWIPE_BACK = "canSwipeBack";
  private static final String CAN_SWIPE_BACK_WITH_VIBRATE = "canSwipeBackWithVibrate";

  /**
   * 设置是否支持滑动退出
   */
  public static void setCanSwipeBack(@Nullable String userID, boolean canSwipeBack) {
    SpHelper.putObj(userID, CAN_SWIPE_BACK, canSwipeBack);
  }

  /**
   * 获取是否支持滑动退出值
   *
   * @return true-支持滑动退出(默认值) false-不支持滑动退出
   */
  public static Boolean getCanSwipeBack(@Nullable String userID) {
    return SpHelper.getObj(userID, CAN_SWIPE_BACK, Boolean.class, true);
  }

  /**
   * 设置滑动退出使用震动提示
   */
  public static void setSwipeBackWithVibrate(@Nullable String userID, boolean vibrate) {
    SpHelper.putObj(userID, CAN_SWIPE_BACK_WITH_VIBRATE, vibrate);
  }

  /**
   * 滑动退出时是否使用了震动提示
   * @return true-退出时使用了震动提示 false-退出时没有使用震动提示
   */
  public static Boolean getCanSwipeBackWithVibrate(@Nullable String userID) {
    return SpHelper.getObj(userID, CAN_SWIPE_BACK_WITH_VIBRATE, Boolean.class, true);
  }

}
