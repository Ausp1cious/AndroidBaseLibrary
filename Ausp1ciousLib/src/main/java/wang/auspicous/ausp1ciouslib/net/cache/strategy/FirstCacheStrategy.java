package wang.auspicous.ausp1ciouslib.net.cache.strategy;



import java.lang.reflect.Type;

import io.reactivex.Flowable;
import wang.auspicous.ausp1ciouslib.net.cache.DiskCache;

/**
 * 先加载缓存，缓存没有再去请求网络
 */
public class FirstCacheStrategy<R>  implements ICacheStrategy<R> {

    @Override
    public Flowable<R> execute(DiskCache DiskCache, String key, long time, Type type, Flowable<R> source) {
        Flowable<R> cache = readLocal(DiskCache, key, type);
        Flowable<R> remote = readRemote(DiskCache, key, time, source);
        return cache.switchIfEmpty(remote);
    }
}
