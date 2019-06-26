package wang.auspicous.ausp1cious;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Map;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import wang.auspicous.ausp1cious.base.AppMVPActivity;
import wang.auspicous.ausp1cious.base.AppUIActivity;
import wang.auspicous.ausp1cious.base.SimpleCallBack;
import wang.auspicous.ausp1cious.testui.FGActivity;
import wang.auspicous.ausp1cious.testui.TestActivity;
import wang.auspicous.ausp1cious.testui.WebTest;
import wang.auspicous.ausp1cious.ui.time.SystemTimeActivity;
import wang.auspicous.ausp1ciouslib.component.eventbus.EventBusMessageCenter;
import wang.auspicous.ausp1ciouslib.net.HttpUtils;
import wang.auspicous.ausp1ciouslib.net.callback.ICallback;
import wang.auspicous.ausp1ciouslib.net.callback.RequestCallback;
import wang.auspicous.ausp1ciouslib.widgets.recyclerview.IConvert;
import wang.auspicous.ausp1ciouslib.widgets.recyclerview.TestAdapter;

public class MainActivity extends AppUIActivity {
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
            startActivity(new Intent(MainActivity.this, SystemTimeActivity.class));
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

//    private void shutdown() {
//        HttpUtils.get().setSuffixUrl("live/admin").execute(new SimpleCallBack<String>() {
//            @Override
//            public void onNext(String requestUrl, Map<String, Object> requestParams, String responseResult) {
//                Log.i(TAG, "onNext: "+responseResult);
//            }
//
//            @Override
//            public void onError(String requestUrl, Map<String, Object> requestParams, Throwable throwable) {
//                Log.i(TAG, "onError: url:"+requestUrl+";throwable:"+throwable.getMessage());
//            }
//
//            @Override
//            public void onStart() {
//
//            }
//
//            @Override
//            public void onFinish() {
//                Log.i(TAG, "onFinish: ");
//            }
//        },bindToLifecycle());
//    }
}
