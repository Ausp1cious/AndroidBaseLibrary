package wang.auspicous.ausp1ciouslib.component.activitylifecyle;

import android.app.Activity;

import com.orhanobut.logger.Logger;

import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by Ausp1cious on 2019/2/18.
 */
public class ActivityStacks {
  private ActivityStacks() {
  }

  private static class ActivityStacksHold {
    private static final ActivityStacks INSTANCE = new ActivityStacks();
  }

  public static ActivityStacks getInstance() {
    return ActivityStacksHold.INSTANCE;
  }

  private LinkedList<WeakReference<Activity>> mActivityStacks = new LinkedList<>();

  public LinkedList<WeakReference<Activity>> getActivityStacks() {
    return mActivityStacks;
  }

  public int getActivityStacksSize() {
    return mActivityStacks.size();
  }

  /**
   * 是否在Activity的栈顶
   */
  public boolean isStacksTop(Activity activity) {
    return !mActivityStacks.isEmpty() && activity == mActivityStacks.get(0).get();
  }

  public boolean isStacksTop(Class clz) {
    return !mActivityStacks.isEmpty() && null != mActivityStacks.get(0).get()
            && clz == mActivityStacks.get(0).get().getClass();
  }

  /**
   * 将指定Activity移动值栈顶，并且删除在它之上的所有Activity
   */
  public void moveActivityTopStacksTop(Activity ac) {
    if (!mActivityStacks.isEmpty()) {
      Iterator<WeakReference<Activity>> it = mActivityStacks.iterator();
      while (it.hasNext()) {
        Activity activity = it.next().get();
        if (ac != activity) {
          it.remove();
          finish(activity);
          continue;
        }
        break;
      }
    }
  }

  public void moveActivityTopStacksTop(Class clz) {
    if (!mActivityStacks.isEmpty()) {
      Iterator<WeakReference<Activity>> it = mActivityStacks.iterator();
      while (it.hasNext()) {
        Activity ac = it.next().get();
        if (null == ac || ac.getClass() != clz) {
          it.remove();
          finish(ac);
          continue;
        }
        break;
      }
    }
  }

  /**
   * 在Activity栈中查找是否存在Activity
   *
   * @return true-存在 false-不存在
   */
  public boolean findActivity(Class clz) {
    if (!mActivityStacks.isEmpty()) {
      for (WeakReference<Activity> mActivityStack : mActivityStacks) {
        Activity ac = mActivityStack.get();
        if (null == ac || ac.getClass() == clz) {
          return true;
        }
      }
    }
    return false;
  }

  public boolean findActivity(Activity activity) {
    if (!mActivityStacks.isEmpty()) {
      for (WeakReference<Activity> mActivityStack : mActivityStacks) {
        Activity ac = mActivityStack.get();
        if (null == ac || ac == activity) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * finish掉除ac以外的所有activity，并且只保留一个ac
   */
  public void removeAllKeepOnlyOne(Activity ac) {
    if (mActivityStacks.size() > 0) {
      Iterator<WeakReference<Activity>> it = mActivityStacks.iterator();
      while (it.hasNext()) {
        Activity activity = it.next().get();
        if (null != activity && activity == ac) {
          continue;
        }
        it.remove();
        finish(activity);
      }
    }
  }

  public void removeAllKeepOnlyOne(Class clz) {
    if (mActivityStacks.size() > 0) {
      int count = 1;
      Iterator<WeakReference<Activity>> it = mActivityStacks.iterator();
      while (it.hasNext()) {
        Activity activity = it.next().get();
        if (null != activity && activity.getClass() == clz && 1 == count) {
          count++;
          continue;
        }
        it.remove();
        finish(activity);
      }
    }
  }

  /**
   * 入栈
   */
  public void push(Activity ac) {
    if (mActivityStacks!=null) {
      mActivityStacks.addFirst(element(ac));
    }

  }

  /**
   * 出栈
   */
  public void pop(Activity ac) {
    if (!mActivityStacks.isEmpty()) {
      if (isStacksTop(ac)) {
        mActivityStacks.removeFirst();
      }
    }
  }

  public void popWithFinishActivity(Activity ac) {
    if (!mActivityStacks.isEmpty()) {
      if (isStacksTop(ac)) {
        mActivityStacks.removeFirst();
        finish(ac);
      }
    }
  }

  /**
   * 删除链表中的Activity
   */
  public void removeActivityInStacks(Activity ac) {
    if (!mActivityStacks.isEmpty()) {
      mActivityStacks.remove(ac);
    }
  }

  /**
   * 清空stack，不会finish栈里的activity
   */
  public void clear() {
    mActivityStacks.clear();
  }

  /**
   * 退出整个App
   */
  public void exitApp() {
    if (!mActivityStacks.isEmpty()) {
      for (int i = mActivityStacks.size() - 1; i >= 0; i--) {
        finish(mActivityStacks.remove(i).get());
      }
    }
  }

  /**
   * finish Activity
   */
  private void finish(Activity activity) {
    if (activity != null) {
      activity.finish();
    }
  }

  private WeakReference<Activity> element(Activity ac) {
    return new WeakReference<>(ac);
  }

}
