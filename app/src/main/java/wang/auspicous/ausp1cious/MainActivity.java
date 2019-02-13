package wang.auspicous.ausp1cious;

import android.widget.Button;

import butterknife.BindView;
import wang.auspicous.ausp1ciouslib.base.activity.BaseUIActivity;

public class MainActivity extends BaseUIActivity {

  @BindView(R.id.btn_turn)
  Button btnTurn;

//  @Override
//  protected void onCreate(Bundle savedInstanceState) {
//    super.onCreate(savedInstanceState);
//    setContentView(R.layout.activity_main);
//    btnTurn.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, TestActivity.class)));
//  }

  @Override
  protected int setContainerView() {
    return R.layout.activity_test;
  }

  @Override
  protected void initValue() {

  }

  @Override
  protected void initWidget() {

  }

  @Override
  protected void initListener() {

  }

  @Override
  protected int setHeaderLayoutView() {
    return R.layout.layout_title;
  }
}
