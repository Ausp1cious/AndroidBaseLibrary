package wang.auspicous.ausp1ciouslib.net.cache.strategy;


import java.lang.reflect.Type;

import io.reactivex.Flowable;
import wang.auspicous.ausp1ciouslib.net.cache.DiskCache;


public class OnlyRemoteStrategy<R> implements ICacheStrategy<R> {

    @Override
    public Flowable<R> execute(DiskCache DiskCache, String key, long time, Type type, Flowable<R> source) {
        return readRemote(DiskCache, key, time, source);
    }
}
