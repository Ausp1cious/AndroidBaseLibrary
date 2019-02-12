package wang.auspicous.ausp1ciouslib.utils.deviceutils;

import android.content.Context;
import android.os.Vibrator;

/**
 * Created by Ausp1cious on 2019/1/31.
 * 手机震动工具类
 */
public class VibrateUtils {
  public static void vibrate(Context context, long duration) {
    Vibrator vibrator = (Vibrator) context.getApplicationContext().getSystemService(
            Context.VIBRATOR_SERVICE);
    long[] pattern = {0, duration};
    if (vibrator != null) {
      vibrator.vibrate(pattern, -1);
    }

  }
}
