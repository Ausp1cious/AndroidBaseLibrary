package wang.auspicous.ausp1ciouslib.component.contract;

import com.trello.rxlifecycle3.LifecycleTransformer;
import com.trello.rxlifecycle3.android.ActivityEvent;

/**
 * Created by Ausp1cious on 2019/2/21.
 * 协议基类
 */
public interface BaseContract {
    /**
     * 协议基类View
     */
    interface View {
        /**
         * 绑定Activity的生命周期
         * 不带参数的默认结束在对立周期。带参数表示指定周期
         */
        LifecycleTransformer bindApiLifecycle();

        LifecycleTransformer bindApiLifcycle(ActivityEvent activityEvent);
    }

    /**
     * 协议基类Presenter
     */
    interface Presenter {

    }
}
