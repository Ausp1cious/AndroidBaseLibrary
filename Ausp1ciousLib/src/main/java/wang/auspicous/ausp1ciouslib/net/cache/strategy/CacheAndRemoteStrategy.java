package wang.auspicous.ausp1ciouslib.net.cache.strategy;



import java.lang.reflect.Type;

import io.reactivex.Flowable;
import io.reactivex.functions.Predicate;
import wang.auspicous.ausp1ciouslib.net.cache.DiskCache;

/**
 * 先使用缓存，不管是否存在，仍然请求网络，会回调两次
 */
public class CacheAndRemoteStrategy<R> implements ICacheStrategy<R> {

    @Override
    public Flowable<R> execute(DiskCache DiskCache, String key, long time, Type type, Flowable<R> source) {
        Flowable<R> cache = readLocal(DiskCache, key, type);
        Flowable<R> remote = readRemote(DiskCache, key, time, source);

        return Flowable.concat(cache, remote).filter(new Predicate<R>() {
            @Override
            public boolean test(R r) throws Exception {
                return null != r;
            }
        });
    }
}
