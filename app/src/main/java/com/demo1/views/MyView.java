package com.demo1.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

import java.util.ArrayList;

/**
 * Created by java on 2017/6/21.
 * 设计尺寸1094*614
 */

public class MyView extends SurfaceView implements SurfaceHolder.Callback {
    private MyThread m_myThread;
    private SurfaceHolder m_holder;
    private Context m_context;
    private ArrayList<SurfaceViewCallBack> images;
    private Animation mAlphaAnimation;
    private Animation mScaleAnimation;
    private Animation mTranslateAnimation;
    private Animation mRotateAnimation;

    public MyView(Context context) {
        this(context, null, 0);
    }

    public MyView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        try {
            m_context = context;
            m_holder = getHolder();
            m_holder.addCallback(this);
            Log.e("show", "MyView");
        } catch (Exception e) {
            Log.e("201706211632", e.toString());
        }
    }

    public void addView(SurfaceViewCallBack image)
    {
        if(images == null)
        {
            images = new ArrayList<>();
        }
        if (image != null) {
            images.add(image);
            image.initInNewFunc();
        }
    }

    public boolean isEmpty()
    {
        if(images !=null)
            return images.isEmpty();
        else
            return true;
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        try {
            Log.e("show", "surfaceCreated");
            m_myThread = new MyThread(surfaceHolder);
            m_myThread.isRun = true;
            m_myThread.start();
        } catch (Exception e) {
            Log.e("201706211726", e.toString());
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
        Log.e("show", "surfaceChanged");
        try{
        if (!m_myThread.isRun) {
            m_myThread = new MyThread(surfaceHolder);
            m_myThread.isRun = true;
            m_myThread.start();
        }
        } catch (Exception e) {
            Log.e("201706211727", e.toString());
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        Log.e("show", "surfaceDestroyed");
        m_myThread.isRun = false;
        for (SurfaceViewCallBack it : images) {
            it.destory();
            it = null;
        }
        images.clear();
    }

    //线程内部类
    class MyThread extends Thread {
        private SurfaceHolder m_holder;
        public boolean isRun;

        MyThread(SurfaceHolder holder) {
            try {
                m_holder = holder;
                isRun = true;
            } catch (Exception e) {
                Log.e("201706211643", e.toString());
            }
        }

        @Override
        public void run() {
            while (isRun) {
                Canvas c = null;
                try {
                    synchronized (m_holder) {
                        c = m_holder.lockCanvas();//锁定画布，一般在锁定后就可以通过其返回的画布对象Canvas，在其上面画图等操作了。
                        c.drawColor(Color.rgb(130,240,240));//设置画布背景颜色
                        for (SurfaceViewCallBack it : images)
                        {
                            it.draw(c);
                        }
                        c.drawLine(0,0,100,100,new Paint());
                        Thread.sleep(1000 / 60);//睡眠时间为1秒
                    }
                } catch (Exception e) {
                    Log.e("201706211642", e.toString());
                } finally {
                    if (c != null) {
                        m_holder.unlockCanvasAndPost(c);//结束锁定画图，并提交改变。
                    }
                }
            }
        }
    }
}


