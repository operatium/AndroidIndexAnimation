package com.demo1.views.bookroom;

import android.app.Activity;
import android.graphics.PointF;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.demo1.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by java on 2017/6/27.
 */

public class BookRoomActivity extends Activity {
    @InjectView(R.id.bg)
    NodeView bg;
    @InjectView(R.id.left)
    NodeView left;
    @InjectView(R.id.right)
    NodeView right;
    @InjectView(R.id.back)
    ImageButton back;
    @InjectView(R.id.ella)
    ImageView ella;
    @InjectView(R.id.treezhi)
    ImageView treezhi;
    @InjectView(R.id.seekBar3)
    SeekBar seekBar3;
    @InjectView(R.id.book)
    BookView book;
    @InjectView(R.id.seekBar4)
    SeekBar seekBar4;
    private DisplayMetrics m_dm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookroom);
        ButterKnife.inject(this);
        m_dm = getResources().getDisplayMetrics();
        bg.setNode(R.drawable.bookhome_bj_736h, new PointF(0, 1f), new RectF(0, 0, 0, 0), new PointF(0, 0), new PointF(0, 0));
        left.setNode(R.drawable.bookhome_girl_0003, new PointF(0, 1f), new RectF(0, 0, 0, 0), new PointF(0, 0.5f), new PointF(0, m_dm.heightPixels * 0.5f));
        right.setNode(R.drawable.bookhome_girl_0002, new PointF(0, 1f), new RectF(0, 0, 0, 0), new PointF(0, 0.5f), new PointF(0, m_dm.heightPixels * 0.5f));
        seekBar3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                book.setDownload(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        seekBar4.setProgress(100);
        seekBar4.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                book.setSwithchOver(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
