package wang.auspicous.ausp1ciouslib.utils.jsonutils;

import android.text.TextUtils;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

/**
 * Created by Ausp1cious on 2019/2/12.
 * 使用Jackson处理Json数据
 */
public class JsonUtils implements IJsonUtils {
  private static ObjectMapper sMapper;

  public JsonUtils() {
    sMapper = new ObjectMapper();
    sMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
    sMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    sMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
    //表示如果属性为空的时候不参与序列化
    //JsonInclude.Include.ALWAYS 默认
    //JsonInclude.Include.NON_DEFAULT  属性为默认值不序列化
    //JsonInclude.Include.NON_EMPTY  属性为 空（“”）  或者为 NULL 都不序列化
    //JsonInclude.Include.NON_NULL 属性为NULL 不序列化
    sMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
  }

  public static JsonUtils getInstance() {
    return new JsonUtils();
  }

  @Nullable
  @Override
  public String toJsonString(Object obj) {
    try {
      return sMapper.writeValueAsString(obj);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
      return null;
    }
  }

  @Nullable
  @Override
  public <T> T parseObj(String jsonString, Class<T> clsBean) {
    if (TextUtils.isEmpty(jsonString)) {
      return null;
    }
    try {
      return sMapper.readValue(jsonString, clsBean);
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }

  @Override
  public <T> List<T> parseList(String jsonString, Class<T> clazz) {
    if (TextUtils.isEmpty(jsonString)) {
      return null;
    }
    try {
      return sMapper.readValue(jsonString, getCollectionType(ArrayList.class, clazz));
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }

  @Override
  public Observable<String> toJsonStringAsObservable(Object obj) {
    final String toJsonString = toJsonString(obj);
    return Observable.create(new ObservableOnSubscribe<String>() {
      @Override
      public void subscribe(ObservableEmitter<String> emitter) throws Exception {
        if (TextUtils.isEmpty(toJsonString)) {
          emitter.onError(new Exception("JsonString为空"));
        }
        emitter.onNext(toJsonString);
        emitter.onComplete();
      }
    });
  }

  @Override
  public <T> Observable<T> parseObjAsObservable(String jsonString, Class<T> clsBean) {
    final T t = parseObj(jsonString, clsBean);
    return Observable.create(new ObservableOnSubscribe<T>() {
      @Override
      public void subscribe(ObservableEmitter<T> emitter) throws Exception {
        if (t == null) {
          emitter.onError(new Exception("解析JsonObj为空"));
        }
        emitter.onNext(t);
        emitter.onComplete();
      }
    });
  }

  @Override
  public <T> Observable<List<T>> parseListAsObservable(String jsonString, Class<T> clazz) {
    final List<T> ts = parseList(jsonString, clazz);
    return Observable.create(new ObservableOnSubscribe<List<T>>() {
      @Override
      public void subscribe(ObservableEmitter<List<T>> emitter) throws Exception {
        if (ts == null) {
          emitter.onError(new Exception("解析JsonList为空"));
        }
        emitter.onNext(ts);
        emitter.onComplete();
      }
    });
  }


  private JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
    return sMapper.getTypeFactory().constructParametricType(collectionClass,
            elementClasses);
  }
}
