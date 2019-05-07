package wang.auspicous.ausp1ciouslib.net.cache.strategy;



import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import wang.auspicous.ausp1ciouslib.net.cache.DiskCache;
import wang.auspicous.ausp1ciouslib.net.cache.core.ICacheFilter;


public interface ICacheStrategy<R> {

    Flowable<R> execute(DiskCache DiskCache, String key, long time, Type type, Flowable<R> source);

    default Flowable<R> readLocal(DiskCache diskCache, String key, Type type) {
        return diskCache.readCache(key).filter(new Predicate<String>() {
            @Override
            public boolean test(String s) throws Exception {
                return null != s;
            }
        }).map(new Function<String, R>() {
            @Override
            public R apply(String s) throws Exception {
                return new GsonBuilder().disableHtmlEscaping().create().fromJson(s, type);
            }
        });
    }


    default Flowable<R> readRemote(DiskCache diskCache, String key, long time, Flowable<R> source) {
        return source.map(new Function<R, R>() {
            @Override
            public R apply(R r) throws Exception {
                if (r instanceof ICacheFilter) {
                    if (((ICacheFilter) r).accept()) {
                        diskCache.writeCache(key, r, time);
                    }
                } else {
                    diskCache.writeCache(key, r, time);
                }
                return r;
            }
        });
    }
}
