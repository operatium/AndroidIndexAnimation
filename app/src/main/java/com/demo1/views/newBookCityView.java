package com.demo1.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.support.annotation.Px;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.demo1.R;
import com.demo1.utils.BitmapUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by Administrator on 2017/6/25.
 */

public class newBookCityView extends View {
    private int t;//频率参数 0-100
    private Bitmap m_lunzi;
    private Bitmap n_chejia;
    private int m_doudongHeight;//抖动高度
    private float m_dou;
    private DisplayMetrics m_dm;
    private int Maxt = 8*15;
    String[] keys = new String[]{"lunzi0", "lunzi1", "lunzi2", "lunzi3", "lunzi4", "lunzi5", "lunzi6", "lunzi7"};
    String[] starkeys = new String[]{"star0", "star1", "star2", "star3", "star4", "star5", "star6", "star7", "star8", "star9","star10", "star11", "star12", "star13", "star14"};
    String[] flagkeys = new String[]{"flag0","flag1","flag2"};
    Integer[] starRes = new Integer[]{R.drawable.star_00006,R.drawable.star_00007,R.drawable.star_00008,R.drawable.star_00009,R.drawable.star_00010,R.drawable.star_00011
    ,R.drawable.star_00012,R.drawable.star_00013,R.drawable.star_00014,R.drawable.star_00015,R.drawable.star_00016,R.drawable.star_00017,R.drawable.star_00018
    ,R.drawable.star_00019,R.drawable.star_00020};
    Integer[] flagRes = new Integer[]{R.drawable.flag_00002,R.drawable.flag_00001,R.drawable.flag_00000};

    public newBookCityView(Context context) {
        this(context, null, 0);
    }

    public newBookCityView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public newBookCityView(Context context, @Nullable AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        try {
            EventBus.getDefault().register(this);
            m_dm = context.getResources().getDisplayMetrics();
            m_doudongHeight = m_dm.heightPixels * 20 / 1440;
            //旋转
            BitmapData.transformation rotateAnimation = new BitmapData.transformation() {
                @Override
                public Bitmap transform4Cut(Context context, int res, DisplayMetrics dm, PointF scale, RectF cut) {
                    return null;
                }

                @Override
                public Bitmap transform4Rotate(Bitmap bitmap, float rotateRotationAngle) {
                    return BitmapUtils.rotate(bitmap, rotateRotationAngle);
                }
            };
            //剪切缩放
            BitmapData.transformation cut = new BitmapData.transformation() {
                @Override
                public Bitmap transform4Cut(Context context, int res, DisplayMetrics dm, PointF scale, RectF cut) {
                    return BitmapUtils.Scale_Cut(context, res, dm, scale, cut);
                }

                @Override
                public Bitmap transform4Rotate(Bitmap bitmap, float rotateRotationAngle) {
                    return null;
                }
            };
            n_chejia = BitmapData.getInstance().addBitmap("bookcity", R.drawable.bookstore_ipad2x, new PointF(0, 0.6f), new RectF(0, 0, 0, 0), cut);
            m_lunzi = BitmapData.getInstance().addBitmap(keys[0], R.drawable.wheel_ipad2x, new PointF(0, 0.1f), new RectF(0.06f, 0, 0, 0), cut);
            BitmapData.getInstance().addBitmap(keys[1], m_lunzi, 45, rotateAnimation);
            BitmapData.getInstance().addBitmap(keys[2], m_lunzi, 90, rotateAnimation);
            BitmapData.getInstance().addBitmap(keys[3], m_lunzi, 135, rotateAnimation);
            BitmapData.getInstance().addBitmap(keys[4], m_lunzi, 180, rotateAnimation);
            BitmapData.getInstance().addBitmap(keys[5], m_lunzi, 225, rotateAnimation);
            BitmapData.getInstance().addBitmap(keys[6], m_lunzi, 270, rotateAnimation);
            BitmapData.getInstance().addBitmap(keys[7], m_lunzi, 315, rotateAnimation);
            for (int i =0;i<starRes.length ;++i){
                BitmapData.getInstance().addBitmap(starkeys[i],starRes[i],new PointF(0,0.4f),new RectF(0,0,0,0),cut);
            }
            for (int i=0 ; i<flagRes.length; ++i)
            {
                BitmapData.getInstance().addBitmap(flagkeys[i],flagRes[i],new PointF(0,0.03f),new RectF(0,0,0,0),cut);
            }
        } catch (Exception e) {
            Log.e("201706251704", e.toString());
        }
    }

    @Override
    public void layout(@Px int l, @Px int t, @Px int r, @Px int b) {
        super.layout(l, t, r, b);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(n_chejia.getWidth(), n_chejia.getHeight());
    }

    @Override
    protected void onDraw(final Canvas canvas) {
        try {
            //轮子
            m_lunzi = BitmapData.getInstance().getBitmap(keys[t / 15 % 8]);
            PointF left = BitmapUtils.getLeftAndTopPoint(new PointF(0.5f, 1f), new PointF(getWidth() * 0.32f, getHeight() * 1f)
                    , new PointF(m_lunzi.getWidth(), m_lunzi.getHeight()));
            PointF right = BitmapUtils.getLeftAndTopPoint(new PointF(0.5f, 1f), new PointF(getWidth() * 0.7f, getHeight() * 1f)
                    , new PointF(m_lunzi.getWidth(), m_lunzi.getHeight()));
            //车
            PointF che = BitmapUtils.getLeftAndTopPoint(new PointF(0, 1f), new PointF(0, getHeight())
                    , new PointF(n_chejia.getWidth(), n_chejia.getHeight()));
            //星星
            Bitmap star = BitmapData.getInstance().getBitmap(starkeys[t/8%15]);
            PointF xingxing = BitmapUtils.getLeftAndTopPoint(new PointF(0.5f,0.5f), new PointF(getWidth()*0.5f,getHeight()*0.55f)
            ,new PointF(star.getWidth(),star.getHeight()));
            //旗帜
            Bitmap qizhi = BitmapData.getInstance().getBitmap(flagkeys[t/6%3]);
            PointF qizhiPosition = BitmapUtils.getLeftAndTopPoint(new PointF(0f,1f), new PointF(getWidth()*0.49f,getHeight()*0.07f)
                    ,new PointF(qizhi.getWidth(),qizhi.getHeight()));
            //车子抖动
            if (t < Maxt/2)
                m_dou = (float) (m_doudongHeight*2.0 / Maxt+m_dou);
            else
                m_dou = (float) (m_dou - m_doudongHeight *2.0/ Maxt);
            if (m_dou <0)
                m_dou = 0;
//            Log.d("onDraw  m_dou: ",m_dou+"");

            //绘制
            canvas.drawBitmap(n_chejia, che.x, che.y - m_dou, null);
            canvas.drawBitmap(m_lunzi, left.x, left.y, null);
            canvas.drawBitmap(m_lunzi, right.x, right.y, null);
            canvas.drawBitmap(star,xingxing.x,xingxing.y,null);
            canvas.drawBitmap(qizhi,qizhiPosition.x,qizhiPosition.y,null);
            t += 1;
            if (t > Maxt)
                t = 0;
        } catch (Exception e) {
            Log.e("201706251656", e.toString());
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        EventBus.getDefault().unregister(this);
        super.onDetachedFromWindow();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(MessageEvent event) {
        switch (event.getName()) {
            case "ui":
//                Log.d("t = ", t + "");
                postInvalidate();
                break;
        }
    }
}
