package com.demo1.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.graphics.RectF;
import android.util.DisplayMetrics;
import android.util.Log;

import com.demo1.utils.BitmapUtils;

/**
 * Created by java on 2017/6/21.
 */

public class Tree implements SurfaceViewCallBack {
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
    private int Count;

    public Tree(int res, PointF scale, PointF anchor, PointF position, float jianju, float sudu, DisplayMetrics dm, Context context) {
        try {
            m_res = res;
            m_scale = scale;
            m_anchor = anchor;
            m_position = position;
            m_jianju = (int) (jianju*dm.widthPixels);
            m_sudu = sudu;
            m_dm = dm;
            m_context = context;
        } catch (Exception e) {
            Log.e("201706231124", e.toString());
        }
    }

    @Override
    public void initInNewFunc() {
        try {
//            m_bitmap = BitmapUtils.Scale_Cut(m_context, m_res, m_dm, m_scale, new RectF(0, 0, 0, 0));
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
            StartX = -m_bitmapWidth - m_jianju;
            m_outPic.left = StartX;
            m_outPic.top = m_position.y - m_anchor.y * m_bitmapheight;
            m_outPic.right = m_outPic.left + m_bitmapWidth;
            m_outPic.bottom = m_outPic.top + m_bitmapheight;
        } catch (Exception e) {
            Log.e("201706231125", e.toString());
        }
    }

    @Override
    public void draw(Canvas canvas) {
        try {
            if (m_bitmap != null && canvas != null) {
                for (int i = 0; i < Count; ++i) {
                    int x = (int) (StartX + i * m_bitmapWidth + m_jianju * i);
                    canvas.drawBitmap(m_bitmap, x, m_outPic.top, null);
                }
            }
        } catch (Exception e) {
            Log.e("20170622228", e.toString());
        }
    }

    @Override
    public void preDraw() {
        try {
            if (m_bitmap != null ) {
                if (StartX >= 0)
                    StartX = -m_bitmapWidth - m_jianju+m_sudu;
                else
                    StartX += m_sudu;
                double d = (m_dm.widthPixels - StartX) * 1.00 / (m_bitmapWidth + m_jianju);
                Count = (int) Math.ceil(d);//重复几个呢
            }
        } catch (Exception e) {
            Log.e("201706231649", e.toString());
        }
    }

    @Override
    public void destory() {
        try {
            m_bitmap = null;
            m_context = null;
        } catch (Exception e) {
            Log.e("201706231126", e.toString());
        }
    }
}
