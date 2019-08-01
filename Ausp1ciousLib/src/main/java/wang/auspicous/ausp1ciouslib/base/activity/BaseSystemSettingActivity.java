package wang.auspicous.ausp1ciouslib.base.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.PowerManager;
import android.view.WindowManager;

import com.trello.rxlifecycle3.components.support.RxAppCompatActivity;

import androidx.annotation.Nullable;
import wang.auspicous.ausp1ciouslib.Constants;
import wang.auspicous.ausp1ciouslib.base.broadcast.SystemBroadcastReceiver;

/**
 * Created by Ausp1cious on 2019/3/26.
 */
public abstract class BaseSystemSettingActivity extends RxAppCompatActivity {
    private static final String TAG = "BaseSystemSettingActivity";
    protected boolean screenOn = false;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setScreenOrientation();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (SystemBroadcastReceiver.getInstance().isRegister()) {
            SystemBroadcastReceiver.getInstance().unregisterSystemBroadcastReceiver(this);
        }
    }

    /**
     * 用于子类设置当前页面横竖屏方向情况
     * {@Link wang.auspicous.ausp1ciouslib.Constants#ScreenOrientationType}
     */
    protected int screenOrientationType() {
        return Constants.ScreenOrientationType.ORIENTATION_PORTRAIT;
    }


    @SuppressLint("WrongConstant")
    private void setScreenOrientation() {
        setRequestedOrientation(screenOrientationType());
    }

    protected void screenLightSwitch(boolean onLight) {
        if (onLight) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
            screenOn = true;
        }else {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
            screenOn = false;
        }
    }
}
