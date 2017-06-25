package com.demo1.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import android.util.DisplayMetrics;
import android.util.Log;

import com.demo1.utils.BitmapUtils;

public class NewYuanShan implements SurfaceViewCallBack {
    private float StartX;
    private DisplayMetrics m_dm;
    private Bitmap m_bitmap = null;
    private Context m_context;
    private int m_res;
    private int m_jianju;
    private float m_sudu;
    private PointF m_anchor;
    private PointF m_scale;
    private PointF m_position;

    private int m_bitmapWidth;
    private int m_bitmapheight;
    private RectF m_outPic;

    public NewYuanShan(int res, PointF scale, PointF anchor, PointF position, float sudu, DisplayMetrics dm, Context context) {
        this.m_dm = dm;
        this.m_context = context;
        this.m_res = res;
        this.m_sudu = sudu;
        this.m_anchor = anchor;
        this.m_scale = scale;
        this.m_position = position;
        this.m_jianju = -1;
    }

    @Override
    public void initInNewFunc() {
//        BitmapFactory.Options options = new BitmapFactory.Options();
//        bitmap = BitmapFactory.decodeResource(context.getResources(), res, options);
//        BitmapWidth = (int) (FullHeight *1.0 * options.outWidth / options.outHeight);
//        BitmapHeight = (int) (FullHeight *1.0);
//        float scaleHeight = ((float) BitmapHeight) / options.outHeight;
//        Matrix matrix = new Matrix();
//        matrix.postScale(scaleHeight, scaleHeight);
//        bitmap = Bitmap.createBitmap(bitmap, 0,0,options.outWidth,options.outHeight,matrix,true);
//        m_bitmap = BitmapUtils.Scale_Cut(m_context,m_res,m_dm,m_scale,new RectF(0,0,0,0));
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
        m_bitmap = BitmapData.getInstance().addBitmap(m_res+"",m_res,m_scale,new RectF(0,0,0,0),cut);
        m_bitmapWidth = m_bitmap.getWidth();
        m_bitmapheight = m_bitmap.getHeight();
        //根据锚点找出左上角
        if (m_outPic == null)
            m_outPic = new RectF(0, 0, 0, 0);
        StartX = -m_bitmapWidth;
        m_outPic.left = StartX;
        m_outPic.top = m_position.y - m_anchor.y * m_bitmapheight;
        m_outPic.right = m_outPic.left + m_bitmapWidth;
        m_outPic.bottom = m_outPic.top + m_bitmapheight;
    }

    @Override
    public void draw(Canvas canvas) {
        try {
            if (m_bitmap != null && canvas !=  null) {
                if (StartX > 0)
                    StartX = -m_bitmapWidth;
                double d = (m_dm.widthPixels - StartX) /m_bitmapWidth ;
                int Count = (int) Math.ceil(d) ;//重复几个呢
                for (int i = 0; i < Count; ++i) {
                    int x = (int) (StartX + i * m_bitmapWidth + m_jianju*i);
                    canvas.drawBitmap(m_bitmap, x , m_outPic.top, new Paint());
                }
                StartX += m_sudu;
            }
        } catch (Exception e) {
            Log.e("201706211820", e.toString());
        }
    }

    @Override
    public void preDraw() {
        
    }

    @Override
    public void destory() {
        m_bitmap = null;
        m_context = null;
    }
}


