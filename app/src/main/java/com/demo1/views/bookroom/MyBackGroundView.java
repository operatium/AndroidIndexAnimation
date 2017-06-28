package com.demo1.views.bookroom;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

import com.demo1.utils.BitmapUtils;

/**
 * Created by java on 2017/6/27.
 */

public class MyBackGroundView extends View {
    private int m_res;
    private Context m_context;
    private Bitmap m_bitmap;
    private PointF m_lefttop;

    public MyBackGroundView(Context context) {
        this(context,null,0);
    }

    public MyBackGroundView(Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }

    public MyBackGroundView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        m_context = context;
    }

    public void init(){
        try {
            DisplayMetrics dm = m_context.getResources().getDisplayMetrics();
            m_bitmap = BitmapUtils.Scale_Cut(m_context,m_res,dm,new PointF(0,1f),new RectF(0,0,0,0));
            m_lefttop = BitmapUtils.getLeftAndTopPoint(new PointF(0.5f,0.5f),new PointF(getWidth()/2,getHeight()/2),new PointF(getWidth(),getHeight()));

        }catch (Exception e)
        {
            Log.e("201706271242",e.toString());
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        try {
            if (m_bitmap != null)
                canvas.drawBitmap(m_bitmap, m_lefttop.x, m_lefttop.y, null);
        }catch (Exception e)
        {
            Log.e("201706271256",e.toString());
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        try {
            if (m_bitmap != null)
                setMeasuredDimension(m_bitmap.getWidth(), m_bitmap.getHeight());
        }catch (Exception e)
        {
            Log.e("201706271405",e.toString());
        }
    }

    public int getM_res() {
        return m_res;
    }

    public void setM_res(int m_res) {
        this.m_res = m_res;
        init();
    }
}
