package wang.auspicous.ausp1ciouslib.base.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.orhanobut.logger.Logger;

import wang.auspicous.ausp1ciouslib.Constants;
import wang.auspicous.ausp1ciouslib.component.bean.BatteryBean;
import wang.auspicous.ausp1ciouslib.component.eventbus.EventBusMessageCenter;

public class SystemBroadcastReceiver extends BroadcastReceiver {
    private boolean isRegister = false;

    private SystemBroadcastReceiver() {
    }

    private static class SystemBroadcastReceiverHolder {
        private static final SystemBroadcastReceiver INSTANCE = new SystemBroadcastReceiver();
    }

    public static SystemBroadcastReceiver getInstance() {
        return SystemBroadcastReceiverHolder.INSTANCE;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        //如果捕捉到的Action是ACTION_BATTERY_CHANGED则运行onBatteryInforECEIVER()
        if (Intent.ACTION_BATTERY_CHANGED.equals(action)) {//电量
            //获得当前电量
            int intLevel = intent.getIntExtra("level", 0);
            //获得手机总电量
            int intScale = intent.getIntExtra("scale", 100);
            EventBusMessageCenter.post(Constants.SystemInfo.SYSTEM_BATTERY_CHANGED,
                    new BatteryBean(intLevel, intScale));
        } else if (Intent.ACTION_SCREEN_OFF.equals(action)) {//关闭屏幕
            EventBusMessageCenter.post(Constants.SystemInfo.SYSTEM_SCREEN_OFF);
        } else if (Intent.ACTION_SCREEN_ON.equals(action)) {
            EventBusMessageCenter.post(Constants.SystemInfo.SYSTEM_SCREEN_ON);
        }
    }

    public void registerSystemBroadcastReceiver(Context context, String intentAction) {
        this.registerSystemBroadcastReceiver(context, intentAction, true);
    }

    public void registerSystemBroadcastReceiver(Context context, String intentAction,
                                                boolean needUnregister) {
        context.getApplicationContext().registerReceiver(this, new IntentFilter(intentAction));
        if (needUnregister) {
            isRegister = true;
        }
    }

    public void registerBatteryBroadcastReceiver(Context context) {
        registerSystemBroadcastReceiver(context, Intent.ACTION_BATTERY_CHANGED, true);
    }

    public void registerBatteryBroadcastReceiver(Context context, boolean needUnregister) {
        registerSystemBroadcastReceiver(context, Intent.ACTION_BATTERY_CHANGED, needUnregister);
    }

    public void registerScreenSwitchBroadcastReceiver(Context context, boolean needUnregister) {
        registerSystemBroadcastReceiver(context, Intent.ACTION_SCREEN_ON, needUnregister);
        registerSystemBroadcastReceiver(context, Intent.ACTION_SCREEN_OFF, needUnregister);
    }

    public void unregisterSystemBroadcastReceiver(Context context) {
        context.getApplicationContext().unregisterReceiver(this);
    }

    public boolean isRegister() {
        return isRegister;
    }
}
