package com.demo1.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.RectF;
import android.util.DisplayMetrics;
import android.util.Log;

import com.bumptech.glide.Glide;

import static android.R.attr.bitmap;

/**
 * Created by java on 2017/6/22.
 */

public class Node implements SurfaceViewCallBack {
    private int m_res;
    private Context m_context;
    private PointF scale;//占屏比
    private DisplayMetrics dm;//屏幕尺寸
    private RectF m_yuanPic;//原始图片的切片区域
    private PointF m_anchor;//锚点(0-1)
    private PointF m_position;//锚点坐标

    private Bitmap m_bitmap;
    private RectF m_outPic;//显示图片的区域

    public Node(int m_res, Context m_context, PointF scale, DisplayMetrics dm, RectF m_yuanPic, PointF m_anchor, PointF m_position) {
        this.m_res = m_res;
        this.m_context = m_context;
        this.scale = scale;
        this.dm = dm;
        this.m_yuanPic = m_yuanPic;
        this.m_anchor = m_anchor;
        this.m_position = m_position;
    }

    @Override
    public void initInNewFunc() {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        m_bitmap = BitmapFactory.decodeResource(m_context.getResources(), m_res, options);
//        Log.i("bitmap.getWidth = ",m_bitmap.getWidth()+"");
//        Log.i("bitmap.getHeight = ",m_bitmap.getHeight()+"");
        float bitmapHeight = dm.heightPixels * scale.y;
        float scaleHeight = bitmapHeight / options.outHeight;
        Matrix matrix = new Matrix();
        matrix.postScale(scaleHeight, scaleHeight);
        int x = (int) (options.outWidth * m_yuanPic.left);
        int y = (int) (options.outHeight *m_yuanPic.top);
        int r = (int) (options.outWidth - m_yuanPic.right*options.outWidth-x);
        int b = (int) (options.outHeight - m_yuanPic.bottom*options.outHeight-y);
//        m_bitmap = Bitmap.createBitmap(m_bitmap, x, y, r, b, matrix, true);
        int BitmapWidth = m_bitmap.getWidth();
        int BitmapHeight = m_bitmap.getHeight();
//        Glide.with(m_context).load(m_res).asBitmap().override().into()
        Log.i("m_bitmap.getWidth = ",m_bitmap.getWidth()+"");
        Log.i("m_bitmap.getHeight = ",m_bitmap.getHeight()+"");
        //根据锚点找出左上角
        if (m_outPic == null)
            m_outPic = new RectF(0, 0, 0, 0);
        m_outPic.left = m_position.x - m_anchor.x * BitmapWidth;
        m_outPic.top = m_position.y - m_anchor.y * BitmapHeight;
        m_outPic.right = m_outPic.left + BitmapWidth;
        m_outPic.bottom = m_outPic.top + BitmapHeight;
    }

    @Override
    public void draw(Canvas canvas) {
        try {
            if (m_bitmap != null && canvas != null) {
                canvas.drawBitmap(m_bitmap, m_outPic.left, m_outPic.top, null);
//                canvas.drawBitmap(m_bitmap, 0, 0, null);
            }
        } catch (Exception e) {
            Log.e("201706221032", e.toString());
        }
    }

    @Override
    public void destory() {
        m_bitmap = null;
        m_outPic = null;
    }

    public int getM_res() {
        return m_res;
    }

    public void setM_res(int m_res) {
        this.m_res = m_res;
    }

    public Context getM_context() {
        return m_context;
    }

    public void setM_context(Context m_context) {
        this.m_context = m_context;
    }

    public PointF getScale() {
        return scale;
    }

    public void setScale(PointF scale) {
        this.scale = scale;
    }

    public DisplayMetrics getDm() {
        return dm;
    }

    public void setDm(DisplayMetrics dm) {
        this.dm = dm;
    }

    public RectF getM_yuanPic() {
        return m_yuanPic;
    }

    public void setM_yuanPic(RectF m_yuanPic) {
        this.m_yuanPic = m_yuanPic;
    }

    public PointF getM_anchor() {
        return m_anchor;
    }

    public void setM_anchor(PointF m_anchor) {
        this.m_anchor = m_anchor;
    }

    public PointF getM_position() {
        return m_position;
    }

    public void setM_position(PointF m_position) {
        this.m_position = m_position;
    }

    public Bitmap getM_bitmap() {
        return m_bitmap;
    }

    public void setM_bitmap(Bitmap m_bitmap) {
        this.m_bitmap = m_bitmap;
    }

    public RectF getM_outPic() {
        return m_outPic;
    }

    public void setM_outPic(RectF m_outPic) {
        this.m_outPic = m_outPic;
    }
}
