package wang.auspicous.ausp1ciouslib.component.activitylifecyle;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.orhanobut.logger.Logger;

import java.lang.reflect.Field;
import java.util.LinkedList;
import wang.auspicous.ausp1ciouslib.base.BaseApp;

/**
 * Created by Ausp1cious on 2019/2/16.
 */
public class ActivityLifecycleImpl implements Application.ActivityLifecycleCallbacks {
  private final LinkedList<Activity> mActivities = new LinkedList<>();

  private IActivityLifecycleObservable mActivityLifecycleObservable;

  private int mForegroundCount = 0;
  private int mConfigCount = 0;
  private boolean mIsBackground = false;

  public void setIActivityLifecycleObservable(
          IActivityLifecycleObservable activityLifecycleObservable) {
    this.mActivityLifecycleObservable = activityLifecycleObservable;
  }
  @Override
  public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
    addActivityToLast(activity);
    ActivityStacks.getInstance().push(activity);
//    Logger.t("ActivityLifecycle").i("onCreate");
  }

  @Override
  public void onActivityStarted(Activity activity) {
    if (!mIsBackground) {
      addActivityToLast(activity);
    }
    if (mConfigCount < 0) {
      ++mConfigCount;
    } else {
      ++mForegroundCount;
    }
//    Logger.t("ActivityLifecycle").i("onStart");
  }

  @Override
  public void onActivityResumed(Activity activity) {
    addActivityToLast(activity);
    if (mIsBackground) {
      mIsBackground = false;
      if (mActivityLifecycleObservable != null) {
        mActivityLifecycleObservable.onActivityBackInForeBackground();
      }
    }
//    Logger.t("ActivityLifecycle").i("onResumed");
  }

  @Override
  public void onActivityPaused(Activity activity) {
//    Logger.t("ActivityLifecycle").i("onPaused");
  }

  @Override
  public void onActivityStopped(Activity activity) {
    if (activity.isChangingConfigurations()) {
      --mConfigCount;
    } else {
      --mForegroundCount;
      if (mForegroundCount <= 0) {
        mIsBackground = true;
        if (mActivityLifecycleObservable!=null) {
          mActivityLifecycleObservable.onActivityRunInBackground();
        }
      }
    }
//    Logger.t("ActivityLifecycle").i("onStopped");
//    Logger.t("ActivityLifecycle").i("onStopped:"+mForegroundCount);
  }

  @Override
  public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

  }

  @Override
  public void onActivityDestroyed(Activity activity) {
    mActivities.remove(activity);
    ActivityStacks.getInstance().pop(activity);
    if (mActivities.size() == 0) {
      if (mActivityLifecycleObservable != null) {
        mActivityLifecycleObservable.onActivityAllDestroyed();
      }
    }
    fixSoftInputLeaks(activity);
//    Logger.t("ActivityLifecycle").i(
//            "onDestroyed:前台个数" + mForegroundCount + ";activity栈中的个数" + mActivities.size());
  }

  private void addActivityToLast(final Activity activity) {
    if (mActivities.contains(activity)) {
      if (!mActivities.getLast().equals(activity)) {
        mActivities.remove();
        mActivities.addLast(activity);
      }
    } else {
      mActivities.addLast(activity);
    }
  }

  private static void fixSoftInputLeaks(final Activity activity) {
    if (activity == null) return;
    InputMethodManager imm =
            (InputMethodManager) BaseApp.getInstance().getContext().getSystemService(
                    Context.INPUT_METHOD_SERVICE);
    if (imm == null) return;
    String[] leakViews =
            new String[]{"mLastSrvView", "mCurRootView", "mServedView", "mNextServedView"};
    for (String leakView : leakViews) {
      try {
        Field declaredField = InputMethodManager.class.getDeclaredField(leakView);
        if (!declaredField.isAccessible()) {
          declaredField.setAccessible(true);
        }
        Object obj = declaredField.get(imm);
        if (!(obj instanceof View)) continue;
        View view = (View) obj;
        if (view.getRootView() == activity.getWindow().getDecorView().getRootView()) {
          declaredField.set(imm, null);
        }
      } catch (Throwable th) {
//        th.printStackTrace();
      }
    }
  }
}

