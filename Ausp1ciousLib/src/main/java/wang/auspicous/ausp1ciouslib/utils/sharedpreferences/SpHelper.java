package wang.auspicous.ausp1ciouslib.utils.sharedpreferences;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import wang.auspicous.ausp1ciouslib.Constants;
import wang.auspicous.ausp1ciouslib.base.BaseApp;
import wang.auspicous.ausp1ciouslib.utils.jsonutils.JsonUtils;

/**
 * Created by Ausp1cious on 2019/2/11.
 */
public class SpHelper {

  public static SharedPreferences getSharedPreferences() {
    return BaseApp.getInstance().getContext().getSharedPreferences(
            Constants.SharedPreference.NAME_OF_SHARED_PREFERENCE,
            Context.MODE_PRIVATE
    );
  }

  /**
   * 返回身份标识的键值对
   *
   * @param id  身份
   * @param key 键
   */
  private static String getIdentity(String id, String key) {
    return id == null ? key : id + "_" + key;
  }

  /**
   * 存储对象
   */
  public static <T> void putObj(String id, String key, T obj) {
    putObj(getIdentity(id, key), obj);
  }

  @SuppressLint("CommitPrefEdits")
  public static <T> void putObj(String key, T obj) {
    getSharedPreferences().edit().putString(key, JsonUtils.getInstance().toJsonString(obj)).apply();
  }

  /**
   * 保存列表
   */
  public static <T> void putList(String id, String key, List<T> list) {
    putObj(getIdentity(id, key), list);
  }

  public static <T> void putList(String key, List<T> list) {
    if (list != null) {
      getSharedPreferences().edit().putString(key,
              JsonUtils.getInstance().toJsonString(list)).apply();
    } else {
      getSharedPreferences().edit().putString(key, null).apply();
    }
  }

  /**
   * 获取SharedPreference保存的对象
   */
  public static <T> T getObj(String id, String key, Class<T> clazz) {
    return getObj(getIdentity(id, key), clazz, null);
  }

  public static <T> T getObj(String key, Class<T> clazz) {
    return getObj(key, clazz, null);
  }

  public static <T> T getObj(String id, String key, Class<T> clazz, T defaultValue) {
    return getObj(getIdentity(id, key), clazz, defaultValue);
  }

  public static <T> T getObj(String key, Class<T> clazz, T defaultValue) {
    T t = defaultValue;
    String jsonStr = getSharedPreferences().getString(key, null);
    if (!TextUtils.isEmpty(jsonStr)) {
      if (Integer.class == clazz) {
        t = (T) new Integer(0);
      } else if (Long.class == clazz) {
        t = (T) new Long(0);
      } else if (Float.class == clazz) {
        t = (T) new Float(0);
      } else if (Double.class == clazz) {
        t = (T) new Double(0);
      } else if (Boolean.class == clazz) {
        t = (T) new Boolean(false);
      } else {
        t = JsonUtils.getInstance().parseObj(jsonStr, clazz);
      }
    }
    return t;
  }

  /**
   * 获取对象作为Observable
   */
  public static <T> Observable<T> getObjAsObservable(String id, String key, Class<T> clazz) {
    return getObjAsObservable(getIdentity(id, key), clazz, null);
  }

  public static <T> Observable<T> getObjAsObservable(String key, Class<T> clazz) {
    return getObjAsObservable(key, clazz, null);
  }

  public static <T> Observable<T> getObjAsObservable(String id, String key, Class<T> clazz,
          T defaultValue) {
    return getObjAsObservable(getIdentity(id, key), clazz, defaultValue);
  }

  public static <T> Observable<T> getObjAsObservable(String key, Class<T> clazz, T
          defaultValue) {
    final T t = getObj(key, clazz, defaultValue);
    return Observable.create(new ObservableOnSubscribe<T>() {
      @Override
      public void subscribe(ObservableEmitter<T> emitter) throws Exception {
        emitter.onNext(t);
        emitter.onComplete();
      }
    });
  }


  /**
   * 获取SharedPreference保存的List
   */
  public static <T> List<T> getList(String id, String key, Class<T> clazz) {
    return getList(getIdentity(id, key), clazz);
  }

  public static <T> List<T> getList(String key, Class<T> clazz) {
    String jsonListStr = getSharedPreferences().getString(key, null);
    return JsonUtils.getInstance().parseList(jsonListStr, clazz);
  }

  public static <T> Observable<List<T>> getListAsObservable(String id, String key,
          Class<T> clazz) {
    return getListAsObservable(getIdentity(id, key), clazz);
  }

  public static <T> Observable<List<T>> getListAsObservable(String key, Class<T> clazz) {
    final List<T> list = getList(key, clazz);
    return Observable.create(new ObservableOnSubscribe<List<T>>() {
      @Override
      public void subscribe(ObservableEmitter<List<T>> emitter) throws Exception {
        emitter.onNext(list);
        emitter.onComplete();
      }
    });
  }

  /**
   * 移除SharedPreference中的字段
   */
  public static boolean remove(String id, String key) {
    return remove(getIdentity(id, key));
  }

  public static boolean remove(String key) {
    return getSharedPreferences().edit().remove(key).commit();
  }

  /**
   * 清除SharedPreference
   */
  public static boolean clear() {
    return getSharedPreferences().edit().clear().commit();
  }
}
