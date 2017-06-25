package com.demo1.views;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Administrator on 2017/6/25.
 */

public class MyService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }

    public void postInvalidate() {
        new Thread() {
            @Override
            public void run() {
                try {
                    while (true) {
                        Thread.sleep(1000 / 60);
                        EventBus.getDefault().post(new MessageEvent("ui"));
//                        Log.d("EventBus", "ui");
                    }
                } catch (Exception e) {
                    Log.e("201706251252", e.toString());
                }
            }
        }.start();

    }

    public class MyBinder extends Binder {
        public MyService getSevice() {
            return MyService.this;
        }
    }
}
