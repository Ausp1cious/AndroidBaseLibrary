package wang.auspicous.ausp1cious.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import androidx.annotation.Nullable;

import wang.auspicous.ausp1cious.bean.TomatoSettingBean;
import wang.auspicous.ausp1cious.utils.AppSpUtils;

public class InitIntentService extends IntentService {
    private static final String ACTION_APP_INIT = "wang.auspicous.ausp1cious.service.init";
    public InitIntentService(){
        super("InitIntentService");
    }

    public InitIntentService(String name) {
        super(name);
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, InitIntentService.class);
        intent.setAction(ACTION_APP_INIT);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent != null) {
            String action = intent.getAction();
            if (TextUtils.equals(action, ACTION_APP_INIT)) {
                init();
            }
        }
    }
    private void init() {
        initAppConfiguration();
    }

    /**
     * 初始化配置参数
     */
    private void initAppConfiguration() {
        if (AppSpUtils.getTomatoTimeConfiguration() == null) {
            TomatoSettingBean setting = new TomatoSettingBean();
            setting.setUnitTime(25 * 60 * 1000);
            setting.setShortRestTime(5 * 60 * 1000);
            setting.setLongRestTime(15 * 60 * 1000);
            setting.setPeriodTime(4);
            setting.setPlanTime(1 * 60 * 1000);
            setting.setSummarizeTime(2 * 60 * 1000);
            AppSpUtils.setTomatoTimeConfiguration(setting);
        }
    }
}
