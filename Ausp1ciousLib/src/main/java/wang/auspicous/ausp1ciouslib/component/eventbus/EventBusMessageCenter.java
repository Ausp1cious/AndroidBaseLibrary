package wang.auspicous.ausp1ciouslib.component.eventbus;

import org.greenrobot.eventbus.EventBus;

import wang.auspicous.ausp1ciouslib.component.bean.BaseBean;

/**
 * Created by Ausp1cious on 2019/1/8.
 */
public final class EventBusMessageCenter extends BaseBean {
  private int code;
  private Object event;

  private EventBusMessageCenter() {
  }

  private EventBusMessageCenter(int code, Object event) {
    this.code = code;
    this.event = event;
  }

  private static EventBusMessageCenter newInstance(int code, Object event) {
    return new EventBusMessageCenter(code, event);
  }

  public static void post(int code) {
    post(code, null);
  }

  /**
   * 发送事件
   * @param code 事件编码
   * @param event 事件Bean
   */
  public static void post(int code, Object event) {
    EventBus.getDefault().post(newInstance(code, event));
  }

  public int getCode() {
    return code;
  }

  public Object getEvent() {
    return event;
  }
}
