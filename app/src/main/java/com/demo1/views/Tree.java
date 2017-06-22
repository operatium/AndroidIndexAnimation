package com.demo1.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.Log;

/**
 * Created by java on 2017/6/21.
 */

public class Tree implements SurfaceViewCallBack {
    private float m_bitmapHeight;
    private float StartX;
    private int FullWidth;
    private int FullHeight;
    private int BitmapWidth;
    private int BitmapHeight;
    private int Count;
    private Bitmap bitmap = null;
    private Context context;
    private int res;
    private int jianju;
    private float m_sudu;
    private float m_position;

    public Tree(float bitmapHeight, float positionScan,int fullWidth, int fullHeight, Context context, int res,int jianju,float sudu) {
        m_bitmapHeight=bitmapHeight;
        FullWidth = fullWidth;
        FullHeight = fullHeight;
        this.context = context;
        this.res = res;
        StartX = 0;
        this.jianju = jianju;
        m_sudu =sudu;
        m_position=positionScan;
    }

    @Override
    public void initInNewFunc() {
        BitmapFactory.Options options = new BitmapFactory.Options();
        bitmap = BitmapFactory.decodeResource(context.getResources(), res, options);
        float scaleHeight =  m_bitmapHeight / options.outHeight;
        Matrix matrix = new Matrix();
        matrix.postScale(scaleHeight, scaleHeight);
        bitmap = Bitmap.createBitmap(bitmap, 0,0,options.outWidth,options.outHeight,matrix,true);
        BitmapWidth = bitmap.getWidth();
        BitmapHeight = bitmap.getHeight();
    }

    @Override
    public void draw(Canvas canvas) {
        try {
            if (bitmap != null && canvas !=  null) {
                if (StartX >= 0)
                    StartX = -BitmapWidth-jianju;
                double d = (FullWidth - StartX) *1.00/(BitmapWidth +jianju);
                Count = (int) Math.ceil(d) ;//重复几个呢
                for (int i = 0; i < Count; ++i) {
                    int x = (int) (StartX + i * BitmapWidth + jianju*i);
                    int y = (int) (FullHeight*m_position);
                    canvas.drawBitmap(bitmap, x , y, null);
                }
                StartX += m_sudu;
            }
        } catch (Exception e) {
            Log.e("20170622228", e.toString());
        }
    }

    @Override
    public void destory() {
        bitmap = null;
        context = null;
    }
}
