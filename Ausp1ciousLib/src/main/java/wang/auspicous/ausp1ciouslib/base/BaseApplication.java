package wang.auspicous.ausp1ciouslib.base;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

import org.greenrobot.greendao.database.Database;

import java.util.List;

import wang.auspicous.ausp1ciouslib.Constants;
import wang.auspicous.ausp1ciouslib.component.activitylifecyle.ActivityLifecycleImpl;
import wang.auspicous.ausp1ciouslib.component.activitylifecyle.IActivityLifecycleObservable;
import wang.auspicous.ausp1ciouslib.component.app.AppMonitorImpl;
import wang.auspicous.ausp1ciouslib.component.app.IAppMonitor;
import wang.auspicous.ausp1ciouslib.component.dao.entity.DaoMaster;
import wang.auspicous.ausp1ciouslib.component.dao.entity.DaoSession;

/**
 * Created by Ausp1cious on 2019/2/1.
 */
public class BaseApplication extends Application {
  private static BaseApplication mInstance;
  private static Context mContext;
  private IAppMonitor mIAppMonitor;
  private static DaoSession mDaoSession;
  private ActivityLifecycleImpl mActivityLifecycle;

  public static BaseApplication getInstance() {
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
    //初始化Activity生命周期监听
    initActivityLifecycle();
    //初始化App监控工具
    mIAppMonitor = new AppMonitorImpl();
    //初始化
    initLogger();
    setupDatabase();
  }

  /**
   * 初始化生命周期的检测
   */
  private void initActivityLifecycle() {
    if (mActivityLifecycle != null) {
      unregisterActivityLifecycleCallbacks(mActivityLifecycle);
    }
    mActivityLifecycle = new ActivityLifecycleImpl();
    mActivityLifecycle.setIActivityLifecycleObservable(new IActivityLifecycleObservable() {
      @Override
      public void onActivityAllDestroyed() {
        if (mIAppMonitor != null) {
          mIAppMonitor.onLastActivityFinish();
        }
      }

      @Override
      public void onActivityRunInBackground() {
        if (mIAppMonitor != null) {
          mIAppMonitor.onUIRunInBackground();
        }
      }

      @Override
      public void onActivityBackInForeBackground() {
        if (mIAppMonitor != null) {
          mIAppMonitor.onActivityBackInForeBackground();
        }
      }
    });
    registerActivityLifecycleCallbacks(mActivityLifecycle);
  }


  /**
   * 初始化日志
   */
  private void initLogger() {
    FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder().tag("BaseLog").build();
    Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));
  }

  /**
   * 判断App是否是前台进程
   *
   * @return true-前台进程 false-不是前台进程
   */
  public static boolean isAppForeground() {
    ActivityManager am = (ActivityManager) mContext.getSystemService(Context.ACTIVITY_SERVICE);
    if (am == null) return false;
    List<ActivityManager.RunningAppProcessInfo> info = am.getRunningAppProcesses();
    if (info == null || info.size() == 0) return false;
    for (ActivityManager.RunningAppProcessInfo aInfo : info) {
      if (aInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
        return aInfo.processName.equals(mContext.getPackageName());
      }
    }
    return false;
  }

  private static void setupDatabase() {
    DaoMaster.DevOpenHelper devOpenHelper =
            new DaoMaster.DevOpenHelper(BaseApplication.getInstance().getContext(),
                    Constants.DB.DB_NAME);
    Database db = devOpenHelper.getWritableDb();
    DaoMaster daoMaster = new DaoMaster(db);
    mDaoSession = daoMaster.newSession();
  }

  public DaoSession getLibraryDaoSession() {
    if (mDaoSession == null) {
      setupDatabase();
    }
    return mDaoSession;
  }

}
