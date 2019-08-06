package wang.auspicous.ausp1cious.utils;

import com.trello.rxlifecycle3.LifecycleTransformer;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RxTimeUtils {

    public static Observable showScreenTime(LifecycleTransformer lifecycleTransformer) {
        return Observable.interval(500, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(lifecycleTransformer);
    }

    public static Observable showScreenTime() {
        return Observable.interval(500, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
