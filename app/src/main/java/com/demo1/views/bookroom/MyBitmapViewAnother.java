package com.demo1.views.bookroom;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by java on 2017/6/28.
 */
public class MyBitmapViewAnother extends View {
    private int width;//设置高
    private int height;//设置高
    //设置一个Bitmap
    private Bitmap bitmap;
    //创建该Bitmap的画布
    private Canvas bitmapCanvas;
    private int m_show;

    public MyBitmapViewAnother(Context context) {
        this(context,null);
    }

    public MyBitmapViewAnother(Context context, AttributeSet attrs) {
        super(context, attrs);

//        PorterDuffXfermode mode = new PorterDuffXfermode(PorterDuff.Mode.XOR);
//        mPaintRect.setXfermode(mode);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        height = getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec);
        setMeasuredDimension(width, height);//设置宽和高
        //自己创建一个Bitmap
        bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        bitmapCanvas = new Canvas(bitmap);//该画布为bitmap的
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //设置该View画布的背景
        Paint mPaint = new Paint();//Bitmap的画笔
        mPaint.setAlpha(150);



        Paint mPaintRect = new Paint();
        mPaintRect.setAntiAlias(true);
        mPaintRect.setColor(Color.GRAY);
        bitmapCanvas.drawRect(0, 0, width, height, mPaintRect);

        Paint mPaintCirlcle = new Paint();
        mPaintCirlcle.setAntiAlias(true);
        PorterDuffXfermode mode  = new PorterDuffXfermode(PorterDuff.Mode.XOR);
        mPaintCirlcle.setXfermode(mode);
        mPaintCirlcle.setStrokeWidth(width/20);
        mPaintCirlcle.setStyle(Paint.Style.STROKE);
        bitmapCanvas.drawCircle(width / 2, height / 2, width / 4, mPaintCirlcle);
        canvas.drawBitmap(bitmap, 0, 0, mPaint);
        float y = getHeight() *(1-m_show/ 100f);
        canvas.translate(0,  y);

    }

    public void setProgress(int p)
    {
        m_show = p;
        postInvalidate();
    }
}