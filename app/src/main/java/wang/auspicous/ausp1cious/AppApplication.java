package wang.auspicous.ausp1cious;

import android.content.Context;

import java.io.File;

import okhttp3.logging.HttpLoggingInterceptor;
import wang.auspicous.ausp1ciouslib.base.BaseApp;
import wang.auspicous.ausp1ciouslib.net.httpclient.HttpFactory;
import wang.auspicous.ausp1ciouslib.utils.storage.ContextProvider;
import wang.auspicous.ausp1ciouslib.utils.storage.StorageCardUtils;

/**
 * Created by Ausp1cious on 2019/2/1.
 */
public class AppApplication extends BaseApp {
    @Override
    public void onCreate() {
        super.onCreate();
        initNetwork();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        ContextProvider.attachApp(this);
    }

    private void initNetwork(){
        new HttpFactory.Builder()
                .baseUrl("http://192.168.1.11:8080/")
                .readTimeout(30)
                .writeTimeout(30)
                .connectTimeout(30)
                .addInterceptors(new HttpLoggingInterceptor())
                .cacheDir(getHttpCacheDir())
                .maxSize(1024 * 1024 * 100)
                .build();
    }

    private File getHttpCacheDir() {
        return new File(StorageCardUtils.getAppCacheDir(), StorageCardUtils.HTTP_DIR);
    }
}
