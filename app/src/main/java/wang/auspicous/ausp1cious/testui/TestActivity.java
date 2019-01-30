package wang.auspicous.ausp1cious.testui;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import wang.auspicous.ausp1cious.R;
import wang.auspicous.ausp1ciouslib.base.activity.BaseSwipeBackActivity;

public class TestActivity extends BaseSwipeBackActivity {

  @BindView(R.id.tv_test)
  TextView tvTest;
  @BindView(R.id.id_status_bar)
  View idStatusBar;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_test);
    ButterKnife.bind(this);
    ImmersionBar.with(this).statusBarView(idStatusBar).init();
  }

}
