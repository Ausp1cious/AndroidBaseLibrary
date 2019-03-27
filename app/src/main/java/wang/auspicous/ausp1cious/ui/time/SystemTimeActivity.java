package wang.auspicous.ausp1cious.ui.time;

import android.widget.TextView;

import java.util.Locale;

import butterknife.BindView;
import wang.auspicous.ausp1cious.R;
import wang.auspicous.ausp1cious.base.AppUIActivity;
import wang.auspicous.ausp1ciouslib.Constants;
import wang.auspicous.ausp1ciouslib.utils.timeutils.TimeUtils;

public class SystemTimeActivity extends AppUIActivity {


    @BindView(R.id.tv_system_time)
    TextView tvSystemTime;

    @Override
    protected int setContainerView() {
        return R.layout.activity_system_time;
    }

    @Override
    protected void initValue() {

    }

    @Override
    protected void initWidget() {
        tvSystemTime.setText(TimeUtils.getCurrentTime(TimeUtils.FORMAT_TIME_SECOND_MILLISECOND,
                Locale.getDefault()));
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected boolean fullScreenMode() {
        return true;
    }

    @Override
    protected int screenOrientationType() {
        return Constants.ScreenOrientationType.SCREEN_ORIENTATION_SENSOR;
    }

}
