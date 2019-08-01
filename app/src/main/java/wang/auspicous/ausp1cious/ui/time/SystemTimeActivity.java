package wang.auspicous.ausp1cious.ui.time;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.orhanobut.logger.Logger;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import wang.auspicous.ausp1cious.R;
import wang.auspicous.ausp1cious.base.AppUIActivity;
import wang.auspicous.ausp1cious.utils.AppTimeUtils;
import wang.auspicous.ausp1ciouslib.Constants;
import wang.auspicous.ausp1ciouslib.base.broadcast.SystemBroadcastReceiver;
import wang.auspicous.ausp1ciouslib.component.bean.BatteryBean;
import wang.auspicous.ausp1ciouslib.component.eventbus.EventBusMessageCenter;
import wang.auspicous.ausp1ciouslib.utils.timeutils.TimeUtils;

public class SystemTimeActivity extends AppUIActivity {
    @BindView(R.id.tv_system_time_date)
    TextView tvSystemTimeDate;
    @BindView(R.id.tv_system_time_week)
    TextView tvSystemTimeWeek;
    @BindView(R.id.tv_system_time_time)
    TextView tvSystemTimeTime;
    @BindView(R.id.tv_system_time_second)
    TextView tvSystemTimeSecond;
    @BindView(R.id.tv_system_time_battery)
    TextView tvSystemTimeBattery;
    @BindView(R.id.iv_system_time_light)
    ImageView ivSystemTimeLight;

    private static final String CURRENT_SCREEN_STATE = "currentScreenState";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            screenOn = savedInstanceState.getBoolean(CURRENT_SCREEN_STATE);
        }
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int setContainerView() {
        return R.layout.activity_system_time;
    }

    @Override
    protected void initValue() {
        SystemBroadcastReceiver.getInstance().registerBatteryBroadcastReceiver(this);
        SystemBroadcastReceiver.getInstance().registerSystemBroadcastReceiver(this,
                Intent.ACTION_SCREEN_OFF);
    }

    @SuppressLint("CheckResult")
    @Override
    protected void initWidget() {
        screenLightSwitch(screenOn);
        setScreenSwitch();
        Observable.interval(500, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(bindToLifecycle())
                .subscribe(
                        aLong -> {
                            tvSystemTimeDate.setText(AppTimeUtils.getCurrentTime(TimeUtils.FORMAT_DATE_CHINESE));
                            tvSystemTimeWeek.setText(AppTimeUtils.getCurrentWeek());
                            tvSystemTimeTime.setText(AppTimeUtils.getCurrentTime(TimeUtils.FORMAT_TIME));
                            String second =
                                    AppTimeUtils.getSecond(AppTimeUtils.getCurrentTimeAsLong());
                            if (second.length() < 2) {
                                second = "0" + second;
                            }
                            tvSystemTimeSecond.setText(second);
                            if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                                ivSystemTimeLight.setVisibility(View.GONE);
                            } else if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                                ivSystemTimeLight.setVisibility(View.VISIBLE);
                            }
                        }
                );
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onGetMessageEvent(EventBusMessageCenter event) {
        super.onGetMessageEvent(event);
        if (event.getCode() == Constants.SystemInfo.SYSTEM_BATTERY_CHANGED) {
            if (event.getEvent() instanceof BatteryBean) {
                if (tvSystemTimeBattery != null) {
                    tvSystemTimeBattery.setText(new BigDecimal(((BatteryBean) event.getEvent()).getCurrenBatteryRate()).multiply(new BigDecimal(100)).setScale(0, BigDecimal.ROUND_DOWN).toPlainString() + "%");
                }
            }
        }
    }

    @Override
    protected void initListener() {
        ivSystemTimeLight.setOnClickListener(v -> {
            screenLightSwitch(!screenOn);
            setScreenSwitch();
        });
    }

    private void setScreenSwitch() {
        if (screenOn) {
            ivSystemTimeLight.setImageResource(R.drawable.icon_lightbulb_on);
        } else {
            ivSystemTimeLight.setImageResource(R.drawable.icon_lightbulb_off);
        }
    }

    @Override
    protected boolean fullScreenMode() {
        return true;
    }

    @Override
    protected int screenOrientationType() {
        return Constants.ScreenOrientationType.SCREEN_ORIENTATION_SENSOR;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(CURRENT_SCREEN_STATE, screenOn);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        screenOn = savedInstanceState.getBoolean(CURRENT_SCREEN_STATE);
    }
}
