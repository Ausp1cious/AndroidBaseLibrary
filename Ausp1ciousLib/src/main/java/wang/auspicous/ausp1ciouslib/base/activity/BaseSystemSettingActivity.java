package wang.auspicous.ausp1ciouslib.base.activity;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import com.trello.rxlifecycle3.components.support.RxAppCompatActivity;

import androidx.annotation.Nullable;
import wang.auspicous.ausp1ciouslib.Constants;

/**
 * Created by Ausp1cious on 2019/3/26.
 */
public abstract class BaseSystemSettingActivity extends RxAppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setScreenOrientation();
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
}
