package wang.auspicous.ausp1ciouslib.base;

import android.app.Application;
import android.content.Context;

/**
 * Created by Ausp1cious on 2019/2/1.
 */
public class BaseApp extends Application {
  private static BaseApp mInstance;
  private static Context mContext;
  public static BaseApp getInstance() {
    return mInstance;
  }
  public Context getContext() {
    return mContext;
  }

  @Override
  public void onCreate() {
    super.onCreate();
    mInstance = this;
    mContext = this;
  }
}
