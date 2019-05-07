package wang.auspicous.ausp1ciouslib.net.cache.strategy;



import java.lang.reflect.Type;
import java.util.Arrays;

import io.reactivex.Flowable;
import wang.auspicous.ausp1ciouslib.net.cache.DiskCache;

/**
 * 先请求网络，请求网络失败后再加载缓存
 */
public class FirstRemoteStrategy<R> implements ICacheStrategy<R> {
    @Override
    public Flowable<R> execute(DiskCache DiskCache, String key, long time, Type type, Flowable<R> source) {
        Flowable<R> cache = readLocal(DiskCache, key, type);
        Flowable<R> remote = readRemote(DiskCache, key, time, source);
        return Flowable.concatDelayError(Arrays.asList(remote, cache)).take(1);
    }
}
