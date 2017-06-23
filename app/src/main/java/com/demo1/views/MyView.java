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

import com.demo1.utils.AppUtils;

import java.util.ArrayList;

/**
 * Created by java on 2017/6/21.
 * 设计尺寸1094*614
 */

public class MyView extends SurfaceView implements SurfaceHolder.Callback {
    private ArrayList<MyThread> m_Thread;
    private SurfaceHolder m_holder;
    private Context m_context;
    private ArrayList<SurfaceViewCallBack> images;
    private int fps = 0;
    private boolean isRun;
    private int cpu;

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
            isRun = true;
            Log.e("show", "MyView");
        } catch (Exception e) {
            Log.e("201706211632", e.toString());
        }
    }

    public void addView(SurfaceViewCallBack image) {
        if (images == null) {
            images = new ArrayList<>();
        }
        if (image != null) {
            images.add(image);
            image.initInNewFunc();
        }
    }

    public boolean isEmpty() {
        if (images != null)
            return images.isEmpty();
        else
            return true;
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        try {
            Log.e("show", "surfaceCreated");
            cpu = AppUtils.getNumberOfCPUCores();
            if (m_Thread == null)
                m_Thread = new ArrayList<>();
            if (cpu <= 2)
                cpu = 2;
            for (int i = 0; i < cpu; ++i) {
                m_Thread.add(startThread(surfaceHolder));
            }
            showFPS();
        } catch (Exception e) {
            Log.e("201706211726", e.toString());
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
        Log.e("show", "surfaceChanged");
        try {
        } catch (Exception e) {
            Log.e("201706211727", e.toString());
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        Log.e("show", "surfaceDestroyed");
        for (MyThread it : m_Thread) {
            it.isRun = false;
        }
        for (SurfaceViewCallBack it : images) {
            it.destory();
            it = null;
        }
        isRun = false;
        images.clear();
    }

    private MyThread startThread(SurfaceHolder surfaceHolder) {
        MyThread Thread2 = new MyThread(surfaceHolder);
        Thread2.isRun = true;
        Thread2.start();
        return Thread2;
    }

    private void showFPS() {
        new Thread() {
            @Override
            public void run() {
                try {
                    while (isRun) {
                        Thread.sleep(1000);
                        Log.i("fps : ", fps + "");
                        fps = 0;
                    }
                } catch (Exception e) {
                    Log.e("201706231528", e.toString());
                }
            }
        }.start();
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
            while (MyView.this.isRun) {
                Canvas c = null;
                try {
                    for (SurfaceViewCallBack it : images) {
                        it.preDraw();
                    }
                    synchronized (m_holder) {
                        c = m_holder.lockCanvas();//锁定画布，一般在锁定后就可以通过其返回的画布对象Canvas，在其上面画图等操作了。
                        if(c != null) {
                            c.drawColor(Color.rgb(130, 240, 240));//设置画布背景颜色
                            for (SurfaceViewCallBack it : images) {
                                it.draw(c);
                            }
                        }
//                        Log.e("show",MyThread.this.getId()+"");
                        fps += 1;
                    }
                } catch (Exception e) {
                    Log.e("201706211642", e.toString());
                } finally {
                    if (c != null) {
                        m_holder.unlockCanvasAndPost(c);//结束锁定画图，并提交改变。
                        try {
                            Thread.sleep(100);//睡眠时间为1秒
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}


