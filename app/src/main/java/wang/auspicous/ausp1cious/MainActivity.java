package wang.auspicous.ausp1cious;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import wang.auspicous.ausp1cious.testui.TestActivity;

public class MainActivity extends AppCompatActivity {

  @BindView(R.id.btn_turn)
  Button btnTurn;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);
    btnTurn.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, TestActivity.class)));
  }

}
