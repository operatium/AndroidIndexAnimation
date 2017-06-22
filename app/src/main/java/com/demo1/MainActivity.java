package com.demo1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.TextView;

import com.demo1.views.MyView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.tv1)
    TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            ButterKnife.inject(this);
        }catch (Exception e)
        {
            Log.e("201706211710",e.toString() );
        }
    }

    @OnClick(R.id.tv1)
    public void onViewClicked() {
        startActivity(new Intent(this,Kotlin.class));
    }
}
