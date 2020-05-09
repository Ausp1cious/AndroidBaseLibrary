package wang.auspicous.ausp1cious;

import android.widget.Button;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import butterknife.BindView;
import wang.auspicous.ausp1ciouslib.base.activity.UIActivity;
import wang.auspicous.ausp1ciouslib.component.eventbus.EventBusMessageCenter;
import wang.auspicous.ausp1ciouslib.widgets.recyclerview.IConvert;
import wang.auspicous.ausp1ciouslib.widgets.recyclerview.TestAdapter;

public class MainActivity extends UIActivity {
    private static final String TAG = "MainActivity";
    @BindView(R.id.btn_show)
    Button btnShow;
    @BindView(R.id.btn_hide)
    Button btnHide;
    @BindView(R.id.rv_test)
    RecyclerView rvTest;


    @Override
    protected int setContainerView() {
        return R.layout.activity_test;
    }

    @Override
    protected void initValue() {
    }

    @Override
    protected boolean canSwipeBack() {
        return false;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void initWidget() {
//    btnShow = findViewById(R.id.btn_show);
//    btnHide = findViewById(R.id.btn_hide);
        rvTest.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<String> data = new ArrayList<>();
        data.add("1");
        data.add("2");
        data.add("3");
        TestAdapter<String> adapter = new TestAdapter<>(this, R.layout.item_test, data,
                (IConvert<String>) (viewHolder, itemData) -> {
                    viewHolder.setText(R.id.tv_test_recycler, itemData);
                });
        rvTest.setAdapter(adapter);
    }

    @Override
    protected void initListener() {
        btnShow.setOnClickListener(v -> {
        });
        btnHide.setOnClickListener(v -> {
//            startActivity(new Intent(MainActivity.this, WebTest.class));
//            shutdown();
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
