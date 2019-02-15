package wang.auspicous.ausp1ciouslib.utils.deviceutils;

import android.Manifest;
import android.content.Context;
import android.os.Vibrator;

import androidx.annotation.RequiresPermission;

/**
 * Created by Ausp1cious on 2019/1/31.
 * 手机震动工具类
 */
public class VibrateUtils {

  /**
   * 开始震动
   */
  @RequiresPermission(Manifest.permission.VIBRATE)
  public static void vibrate(Context context, long duration) {
    Vibrator vibrator = (Vibrator) context.getApplicationContext().getSystemService(
            Context.VIBRATOR_SERVICE);
    long[] pattern = {0, duration};
    if (vibrator != null) {
      vibrator.vibrate(pattern, -1);
    }
  }

  /**
   * 取消震动
   */
  @RequiresPermission(Manifest.permission.VIBRATE)
  public static void cancelVibrate(Context context) {
    Vibrator vibrator = (Vibrator) context.getApplicationContext().getSystemService(
            Context.VIBRATOR_SERVICE);
    if (vibrator != null) {
      vibrator.cancel();
    }
  }
}
