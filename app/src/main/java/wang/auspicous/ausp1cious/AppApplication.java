package wang.auspicous.ausp1cious;

import android.content.Context;

import com.elvishew.xlog.LogLevel;
import com.elvishew.xlog.XLog;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import org.greenrobot.greendao.database.Database;

import java.io.File;

import okhttp3.logging.HttpLoggingInterceptor;
import wang.auspicous.ausp1cious.base.AppConstants;
import wang.auspicous.ausp1cious.dao.entity.DaoMaster;
import wang.auspicous.ausp1cious.dao.entity.DaoSession;
import wang.auspicous.ausp1cious.service.InitIntentService;
import wang.auspicous.ausp1ciouslib.base.BaseApp;
import wang.auspicous.ausp1ciouslib.net.HttpLogger;
import wang.auspicous.ausp1ciouslib.net.httpclient.HttpFactory;
import wang.auspicous.ausp1ciouslib.utils.storage.ContextProvider;
import wang.auspicous.ausp1ciouslib.utils.storage.StorageCardUtils;

/**
 * Created by Ausp1cious on 2019/2/1.
 */
public class AppApplication extends BaseApp {
    private static AppApplication mInstance;
    private static DaoSession mDaoSession;
    private static HttpFactory httpFactory;
    public static AppApplication getInstance() {
        return mInstance;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        InitIntentService.start(this);
        Logger.addLogAdapter(new AndroidLogAdapter());
        XLog.init(LogLevel.ALL);
        initNetwork();
        setupDatabase();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        ContextProvider.attachApp(this);
    }

    public static HttpFactory getHttpFactory() {
        return httpFactory;
    }

    private void initNetwork() {
        httpFactory = new HttpFactory.Builder()
                .baseUrl("http://192.168.1.11:8080/")
                .readTimeout(30)
                .writeTimeout(30)
                .connectTimeout(30)
                .addInterceptors(new HttpLoggingInterceptor())
                .addInterceptors(new HttpLoggingInterceptor(new HttpLogger()).setLevel(HttpLoggingInterceptor.Level.BODY))
                .cacheDir(getHttpCacheDir())
                .maxSize(1024 * 1024 * 100)
                .build();
    }

    private File getHttpCacheDir() {
        return new File(StorageCardUtils.getAppCacheDir(), StorageCardUtils.HTTP_DIR);
    }

    private static void setupDatabase() {
        DaoMaster.DevOpenHelper devOpenHelper =
                new DaoMaster.DevOpenHelper(BaseApp.getInstance().getContext(),
                        AppConstants.DB.DB_NAME);
        Database db = devOpenHelper.getWritableDb();
        DaoMaster daoMaster = new DaoMaster(db);
        mDaoSession = daoMaster.newSession();
    }

    public  DaoSession getDaoSession() {
        if (mDaoSession == null) {
            setupDatabase();
        }
        return mDaoSession;
    }
}
