package com.demo1.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.RectF;
import android.util.Log;

/**
 * Created by java on 2017/6/21.
 */

public class LeftNode implements SurfaceViewCallBack {
    private int m_res;
    private Context m_context;
    private Bitmap m_bitmap;
    private int m_fullHeight;
    private RectF m_inpic;
    private RectF m_outpic;
    private PointF m_scale;

    public LeftNode(int res, Context context, int fullHeight, RectF inpic, RectF outpic, PointF scale) {
        m_res = res;
        m_context = context;
        m_inpic = inpic;
        m_outpic = outpic;
        m_fullHeight = fullHeight;
        m_scale =scale;
    }

    @Override
    public void initInNewFunc() {
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            m_bitmap = BitmapFactory.decodeResource(m_context.getResources(), m_res, options);
            int x = (int) (options.outWidth*m_inpic.left);
            int y = (int) (options.outHeight*m_inpic.top);
            float scaleHeight = ((float) m_fullHeight*m_scale.y) / (options.outHeight - y);
            Matrix matrix = new Matrix();
            matrix.postScale(scaleHeight, scaleHeight);
            m_bitmap = Bitmap.createBitmap(m_bitmap, x, y, options.outWidth - x, options.outHeight - y, matrix, true);
        } catch (Exception e) {
            Log.e("201706212333", e.toString());
        }
    }

    @Override
    public void draw(Canvas canvas) {
        try {
            if (m_bitmap != null && canvas != null) {
                canvas.drawBitmap(m_bitmap, m_fullHeight*m_outpic.left, m_fullHeight*m_outpic.top, null);
            }
        } catch (Exception e) {
            Log.e("201706212328", e.toString());
        }
    }

    @Override
    public void destory() {
        m_bitmap = null;
        m_context = null;
    }
}
