package wang.auspicous.ausp1ciouslib.utils.jsonutils;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Ausp1cious on 2019/2/12.
 */
public interface IJsonUtils {
  /**
   * 生成Json对象
   */
  String toJsonString(Object obj);

  /**
   * 解析Json对象
   */
  <T> T parseObj(String jsonString, Class<T> clsBean);

  /**
   * 解析Json数组
   */
  <T> List<T> parseList(String jsonString, Class<T> clazz);

  /**
   * 生成Observable的JsonString对象
   */
  Observable<String> toJsonStringAsObservable(Object obj);

  /**
   * 解析Json对象并生成Observable对象
   */
  <T> Observable<T> parseObjAsObservable(String jsonString, Class<T> clsBean);

  /**
   * 解析Json数组并生成Observable对象
   */
  <T> Observable<List<T>> parseListAsObservable(String jsonString, Class<T> clazz);
}
