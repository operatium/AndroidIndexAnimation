package com.demo1.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.support.annotation.Px;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

import com.demo1.R;
import com.demo1.utils.BitmapUtils;

/**
 * Created by Administrator on 2017/6/25.
 */

public class newLoco extends View {
    private Bitmap m_huochetou;

    public newLoco(Context context) {
        this(context,null,0);
    }

    public newLoco(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public newLoco(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        try{
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
            m_huochetou = BitmapData.getInstance().addBitmap("huochetou", R.drawable.headstock_ipad2x,new PointF(0,0.4f),new RectF(0,0,0,0),cut);

        }catch (Exception e)
        {
            Log.e("20170652052",e.toString());
        }
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(m_huochetou.getWidth(), m_huochetou.getHeight());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(m_huochetou,0,0,null);
    }
}
