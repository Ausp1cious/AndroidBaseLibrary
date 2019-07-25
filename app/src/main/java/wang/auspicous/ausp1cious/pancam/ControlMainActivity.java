package wang.auspicous.ausp1cious.pancam;

import android.annotation.SuppressLint;
import android.os.BatteryManager;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.orhanobut.logger.Logger;

import butterknife.BindView;
import wang.auspicous.ausp1cious.Presenters.Contracts.ControlMainContract;
import wang.auspicous.ausp1cious.Presenters.ControlMainPresenterImpl;
import wang.auspicous.ausp1cious.R;
import wang.auspicous.ausp1cious.base.AppMVPActivity;
import wang.auspicous.ausp1ciouslib.utils.ActivityJumpUtils;
import wang.auspicous.ausp1ciouslib.utils.cryptology.Md5NativeUtils;

public class ControlMainActivity extends AppMVPActivity<ControlMainContract.ControlMainView, ControlMainPresenterImpl> implements ControlMainContract.ControlMainView {
    private static final String TAG = "ControlMainActivity";
    @BindView(R.id.btn_get_info)
    Button btnGetInfo;
    @BindView(R.id.btn_get_bitratios)
    Button btnGetBitratios;
    @BindView(R.id.btn_get_setting)
    Button btnGetSetting;
    @BindView(R.id.btn_set_update)
    Button btnSetUpdate;
    @BindView(R.id.btn_reboot)
    Button btnReboot;
    @BindView(R.id.et_bitratios)
    EditText etBitratios;
    @BindView(R.id.et_rtmp)
    EditText etRtmp;
    @BindView(R.id.et_ip_address)
    EditText etIpAddress;
    @BindView(R.id.et_port)
    EditText etPort;
    @BindView(R.id.btn_confirm)
    Button btnConfirm;

    @Override
    protected int setContainerView() {
        return R.layout.activity_control_main;
    }

    @Override
    protected void initValue() {
        Log.i(TAG, "initValue: "+getFilesDir().getAbsolutePath());
    }

    @Override
    protected void initWidget() {
        Logger.i(Md5NativeUtils.encryptMD5("123"));
    }

    @Override
    protected void initListener() {
        btnConfirm.setOnClickListener(v -> getPresenter().setBaseUrl(etIpAddress.getText().toString(), etPort.getText().toString()));
        btnGetInfo.setOnClickListener(v -> getPresenter().getPancamInfo());
        btnGetBitratios.setOnClickListener(v -> getPresenter().getBitratiosInfo());
        btnGetSetting.setOnClickListener(v -> getPresenter().getSettingInfo());
        btnSetUpdate.setOnClickListener(v -> getPresenter().setUpdate(etBitratios.getText().toString(), etRtmp.getText().toString()));
        btnReboot.setOnClickListener(v -> getPresenter().reboot());
    }

    @Override
    protected void initData() {
        BatteryManager manager = (BatteryManager) getSystemService(BATTERY_SERVICE);
        int chargeCounter = manager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CHARGE_COUNTER);
        int currentAverage = manager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CURRENT_AVERAGE);
        int currentNow = manager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CURRENT_NOW);
        int capacity = manager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY);///当前电量百分比
        @SuppressLint("DefaultLocale") String batterInfo = String.format("chargeCounter:%d;currentAverage:%d;currentNow:%d;capacity:%d", chargeCounter, currentAverage, currentNow, capacity);
        Log.i(TAG, "initData: " + batterInfo);
    }

    @Override
    public void getPancamInfo(String pancamInfo) {
        turnShowActivity(pancamInfo);
    }

    @Override
    public void getBitratiosInfo(String bitratiosInfo) {
        turnShowActivity(bitratiosInfo);
    }

    @Override
    public void getSettingInfo(String settingInfo) {
        turnShowActivity(settingInfo);
    }

    @Override
    public void setUpdate() {

    }

    @Override
    public void reboot() {

    }

    private void turnShowActivity(String info) {
        ActivityJumpUtils.jump(ControlMainActivity.this, ShowActivity.class, intent -> intent.putExtra(ShowActivity.SHOW_INFO, info));
    }

}
