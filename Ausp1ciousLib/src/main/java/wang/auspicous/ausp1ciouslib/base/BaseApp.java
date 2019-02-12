package wang.auspicous.ausp1ciouslib.base;

import android.app.Application;
import android.content.Context;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

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
    //初始化
    initLogger();
  }

  private void initLogger() {
    FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder().tag("BaseLog").build();
    Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));
  }
}
