package wang.auspicous.ausp1cious.testui;

import android.net.Uri;
import android.webkit.WebView;

import wang.auspicous.ausp1cious.R;
import wang.auspicous.ausp1ciouslib.base.activity.BaseUIActivity;

public class WebTest extends BaseUIActivity {
    private WebView wbTest;
    @Override
    protected int setContainerView() {
        return R.layout.activity_web_test;
    }

    @Override
    protected void initValue() {

    }

    @Override
    protected void initWidget() {
        wbTest = findViewById(R.id.wb_test);

    }

    @Override
    protected void initListener() {
    }

}
