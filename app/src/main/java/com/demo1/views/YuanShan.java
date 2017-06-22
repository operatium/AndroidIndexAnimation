package com.demo1.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.Log;

/**
 * Created by java on 2017/6/21.
 */

public class YuanShan implements SurfaceViewCallBack {
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

    public YuanShan(int fullWidth, int fullHeight,Context context, int res, float sudu) {
        this(fullWidth,fullHeight,context,res,-2,sudu);
    }

    public YuanShan(int fullWidth, int fullHeight, Context context, int res) {
        this(fullWidth,fullHeight,context,res,-2,2.0f);
    }

    public YuanShan(int fullWidth, int fullHeight, Context context, int res,int jianju, float sudu) {
        FullWidth = fullWidth;
        FullHeight = fullHeight;
        this.context = context;
        this.res = res;
        StartX = 1;
        this.jianju = jianju;
        m_sudu =sudu;
    }

    @Override
    public void initInNewFunc() {
        BitmapFactory.Options options = new BitmapFactory.Options();
        bitmap = BitmapFactory.decodeResource(context.getResources(), res, options);
        BitmapWidth = (int) (FullHeight *1.0 * options.outWidth / options.outHeight);
        BitmapHeight = (int) (FullHeight *1.0);
        float scaleHeight = ((float) BitmapHeight) / options.outHeight;
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
                if (StartX > 0)
                    StartX = -BitmapWidth;
                double d = (FullWidth - StartX) *1.00/BitmapWidth ;
                Count = (int) Math.ceil(d) ;//重复几个呢
                for (int i = 0; i < Count; ++i) {
                    int x = (int) (StartX + i * BitmapWidth + jianju*i);
                    canvas.drawBitmap(bitmap, x , 0, new Paint());
                }
                StartX += m_sudu;
            }
        } catch (Exception e) {
            Log.e("201706211820", e.toString());
        }
    }

    @Override
    public void destory() {
        bitmap = null;
        context = null;
    }


    public float getStartX() {
        return StartX;
    }

    public void setStartX(int startX) {
        StartX = startX;
    }

    public int getFullWidth() {
        return FullWidth;
    }

    public void setFullWidth(int fullWidth) {
        FullWidth = fullWidth;
    }

    public int getFullHeight() {
        return FullHeight;
    }

    public void setFullHeight(int fullHeight) {
        FullHeight = fullHeight;
    }

    public int getCount() {
        return Count;
    }

    public void setCount(int count) {
        Count = count;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public int getRes() {
        return res;
    }

    public void setRes(int res) {
        this.res = res;
    }

    public int getBitmapWidth() {
        return BitmapWidth;
    }

    public void setBitmapWidth(int bitmapWidth) {
        BitmapWidth = bitmapWidth;
    }

    public int getBitmapHeight() {
        return BitmapHeight;
    }

    public void setBitmapHeight(int bitmapHeight) {
        BitmapHeight = bitmapHeight;
    }

    public int getJianju() {
        return jianju;
    }

    public void setJianju(int jianju) {
        this.jianju = jianju;
    }
}


