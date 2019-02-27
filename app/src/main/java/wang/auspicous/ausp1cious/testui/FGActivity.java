package wang.auspicous.ausp1cious.testui;

import androidx.appcompat.app.AppCompatActivity;
import wang.auspicous.ausp1cious.R;
import wang.auspicous.ausp1cious.fragment.TestFragment;
import wang.auspicous.ausp1ciouslib.base.activity.BaseMVPActivity;
import wang.auspicous.ausp1ciouslib.base.activity.BaseUIActivity;

import android.os.Bundle;

public class FGActivity extends BaseUIActivity {

    @Override
    protected int setContainerView() {
        return R.layout.activity_fg;
    }

    @Override
    protected void initValue() {
        TestFragment newInstance = TestFragment.getNewInstance();
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_frame,
                newInstance).commit();
    }

    @Override
    protected void initWidget() {

    }

    @Override
    protected void initListener() {

    }


}
