package com.demo1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.demo1.views.BitmapData;
import com.demo1.views.MessageEvent;
import com.demo1.views.PreLoad;
import com.demo1.views.newLoco;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.tv1)
    TextView tv1;
    @InjectView(R.id.progressBar)
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            ButterKnife.inject(this);
            EventBus.getDefault().register(this);
        } catch (Exception e) {
            Log.e("201706211710", e.toString());
        }
    }

    @OnClick(R.id.tv1)
    public void onViewClicked() {
        progressBar.setVisibility(View.VISIBLE);
        new Thread(){
            @Override
            public void run() {
                BitmapData.getInstance().init(MainActivity.this, getResources().getDisplayMetrics(),true);
//                PreLoad.getInstance().preLoadHome();
                EventBus.getDefault().post(new MessageEvent("gotohome"));
            }
        }.start();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    void OnEventMessage(MessageEvent e)
    {
        switch (e.getName()){
            case "gotohome":
                startActivity(new Intent(this, Kotlin.class));
                break;
        }
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
