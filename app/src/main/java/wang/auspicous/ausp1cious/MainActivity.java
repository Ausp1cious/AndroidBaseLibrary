package wang.auspicous.ausp1cious;

import android.content.Intent;
import android.util.Log;
import android.widget.Button;

import com.orhanobut.logger.Logger;

import java.util.concurrent.TimeUnit;

import androidx.core.content.ContextCompat;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.observers.DisposableObserver;
import wang.auspicous.ausp1cious.testui.FGActivity;
import wang.auspicous.ausp1cious.testui.TestActivity;
import wang.auspicous.ausp1ciouslib.base.BaseApp;
import wang.auspicous.ausp1ciouslib.base.activity.BaseUIActivity;
import wang.auspicous.ausp1ciouslib.component.activitylifecyle.ActivityStacks;
import wang.auspicous.ausp1ciouslib.component.eventbus.EventBusMessageCenter;
import wang.auspicous.ausp1ciouslib.component.eventbus.EventBusStickMessageCenter;

public class MainActivity extends BaseUIActivity {
  private static final String TAG = "MainActivity";
  @BindView(R.id.btn_show)
  Button btnShow;
  @BindView(R.id.btn_hide)
  Button btnHide;
  private Unbinder mBind;


//  @Override
//  protected void onCreate(Bundle savedInstanceState) {
//    super.onCreate(savedInstanceState);
//    setContentView(R.layout.activity_main);
//    btnTurn.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, TestActivity
// .class)));
//  }
//
  @Override
  protected void bindButterKnife() {
    mBind = ButterKnife.bind(MainActivity.this);
  }

  @Override
  protected void unBindButterKnife() {
    mBind.unbind();
  }

  @Override
  protected int setContainerView() {
    return R.layout.activity_test;
  }

  @Override
  protected void initValue() {

  }

  @Override
  protected void onResume() {
    super.onResume();
  }

  @Override
  protected void initWidget() {
//    btnShow = findViewById(R.id.btn_show);
//    btnHide = findViewById(R.id.btn_hide);
  }

  @Override
  protected void initListener() {
    btnShow.setOnClickListener(v -> {
      startActivity(new Intent(MainActivity.this, TestActivity.class));
    });
    btnHide.setOnClickListener(v -> {
      startActivity(new Intent(MainActivity.this, FGActivity.class));
    });
  }


  @Override
  protected int setHeaderLayoutView() {
    return R.layout.layout_title;
  }

  @Override
  public void onGetMessageEvent(EventBusMessageCenter event) {
    super.onGetMessageEvent(event);
  }
}
