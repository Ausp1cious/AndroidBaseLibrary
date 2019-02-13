package wang.auspicous.ausp1cious;

import android.os.Bundle;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import wang.auspicous.ausp1ciouslib.base.activity.BaseUIActivity;

public class MainActivity extends BaseUIActivity {
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
  protected void initWidget() {
//    btnShow = findViewById(R.id.btn_show);
//    btnHide = findViewById(R.id.btn_hide);
  }

  @Override
  protected void initListener() {
    btnShow.setOnClickListener(v -> showLoadingView());
    btnHide.setOnClickListener(v -> hideLoadingView());
  }

  @Override
  protected int setHeaderLayoutView() {
    return R.layout.layout_title;
  }

}
