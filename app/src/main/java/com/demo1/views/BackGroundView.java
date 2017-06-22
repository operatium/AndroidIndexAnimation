package com.demo1.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.annotation.Px;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.demo1.R;

/**
 * Created by java on 2017/6/21.
 */

public class BackGroundView extends View {
    private Canvas m_canvas=null;
    private Bitmap m_bitmap = null;
    private int m_src;
    private int m_backgorund;
    private int m_time;
    private boolean m_loop;
    private int m_width = 0;
    private int m_height = 0;


    public BackGroundView(Context context) {
        this(context, null, 0);
    }

    public BackGroundView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BackGroundView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        try {
            /**
             * 获得我们所定义的自定义样式属性
             */
            TypedArray styledAttributes = context.getTheme().obtainStyledAttributes(attrs, R.styleable.BackGroundView, defStyleAttr, 0);
            int n = styledAttributes.getIndexCount();
            for (int i = 0; i < n; i++) {
                int attr = styledAttributes.getIndex(i);
                switch (attr) {
                    case R.styleable.BackGroundView_src:
                        m_src = styledAttributes.getResourceId(attr, 0);
                        break;
                    case R.styleable.BackGroundView_backgroundcolor:
                        m_backgorund = styledAttributes.getColor(attr, Color.WHITE);
                        break;
                    case R.styleable.BackGroundView_time:
                        m_time = styledAttributes.getInt(attr, 0);
                        break;
                    case R.styleable.BackGroundView_loop:
                        m_loop = styledAttributes.getBoolean(attr, false);
                        break;
                }

            }
            Log.e("show", "new BackGroundView");

            styledAttributes.recycle();
            //1. 创建缓冲画布
            m_canvas = new Canvas();
            //2. 创建缓存Bitmap
//            m_bitmap = Bitmap.createBitmap(m_width, m_height, Bitmap.Config.ARGB_8888);
            //3. 将缓冲位图设置给缓冲画布
            m_canvas.setBitmap(m_bitmap);
            new Thread() {
                @Override
                public void run() {
                    while (m_bitmap != null )
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    postInvalidate();
                }
            }.start();
        }catch (Exception e)
        {
            Log.e("201706211548",e.toString());
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //5. 用屏幕的画布绘制缓冲位图
//        canvas.drawBitmap(m_bitmap,0,0, null);
        super.onDraw(canvas);
        Log.e("show","onDraw");
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        m_width = widthSize;
        m_height = heightSize;
        super.onMeasure(widthMeasureSpec,heightMeasureSpec);
        Log.e("show","onMeasure  widthSize = " + widthSize+"   heightSize = "+heightSize);
    }

    @Override
    public void layout(@Px int l, @Px int t, @Px int r, @Px int b) {
        super.layout(l, t, r, b);
        Log.e("show","layout  l = "+l+"  t = "+t +"  r = "+ r+"  b = "+b);
    }

    @Override
    public boolean hasWindowFocus() {
        boolean show = super.hasWindowFocus();
        if (!show)
            m_bitmap = null;

        return show;
    }
}
