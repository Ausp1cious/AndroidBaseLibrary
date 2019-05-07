package wang.auspicous.ausp1ciouslib.utils.storage;

import android.app.Application;
import android.content.Context;

public final class ContextProvider {
    private static Application application;

    public static Application getApp() {
        return application;
    }

    public static Context getContext() {
        return application.getBaseContext();
    }

    /**
     * class DemoApp extends Application{
     * protected void attachBaseContext(Context base) {
     * super.attachBaseContext(base);
     * ContextProvider.attachApp(this);
     * }
     * }
     **/
    public static void attachApp(Application app) {
        application = app;
    }
}
