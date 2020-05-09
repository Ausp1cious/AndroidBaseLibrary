package wang.auspicous.ausp1cious.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import androidx.annotation.Nullable;

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

    }


}
