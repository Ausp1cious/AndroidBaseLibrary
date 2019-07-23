package wang.auspicous.ausp1cious.pancam;

import android.text.TextUtils;
import android.widget.TextView;

import butterknife.BindView;
import wang.auspicous.ausp1cious.R;
import wang.auspicous.ausp1cious.base.AppUIActivity;

public class ShowActivity extends AppUIActivity {
    public final static String SHOW_INFO = "SHOW_INFO";
    private String showInfo;
    @BindView(R.id.tv_show)
    TextView tvShow;

    @Override
    protected int setContainerView() {
        return R.layout.activity_show_activitity;
    }

    @Override
    protected void initValue() {
        showInfo = getIntent().getStringExtra(SHOW_INFO);
    }

    @Override
    protected void initWidget() {
        if (!TextUtils.isEmpty(showInfo)) {
            tvShow.setText(showInfo);
        }
    }

    @Override
    protected void initListener() {

    }
}
