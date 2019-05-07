package wang.auspicous.ausp1ciouslib.net.httpclient.func;

import org.reactivestreams.Publisher;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;


public abstract class RetryFunction implements Function<Flowable<? extends Throwable>, Flowable<?>> {

    private int maxRetryCount;
    private long retryDelayMillis;
    private int retryCount;

    public RetryFunction(int maxRetryCount, long retryDelayMillis) {
        this.maxRetryCount = maxRetryCount;
        this.retryDelayMillis = retryDelayMillis;
    }

    @Override
    public Flowable<?> apply(Flowable<? extends Throwable> flowable) throws Exception {
        return flowable.flatMap(new Function<Throwable, Publisher<?>>() {
            @Override
            public Publisher<?> apply(Throwable throwable) throws Exception {
                if (++retryCount <= maxRetryCount && retry(throwable)) {
                    return Flowable.timer(retryDelayMillis, TimeUnit.MILLISECONDS);
                }
                return Flowable.error(throwable);
            }
        });
    }

    public abstract boolean retry(Throwable throwable);
}
