package com.demo1.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import android.util.DisplayMetrics;
import android.util.Log;

import com.demo1.R;
import com.demo1.utils.BitmapUtils;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/6/24.
 */

public class BookCity implements SurfaceViewCallBack {

    private ArrayList<Integer> m_res;
    private Context m_context;
    private DisplayMetrics m_dm;
    private float m_speed;//速度
    private ArrayList<Bitmap> m_shanguang;//闪光帧动画
    private Bitmap m_drawBitmap;//提供当前帧的bitmap
    private PointF m_drawPoint;//提供当前帧的位置
    private Bitmap m_chejia;//车架
    private RectF m_outPic;
    private PointF m_position;
    private PointF m_anchor;

    public BookCity(Context m_context, DisplayMetrics m_dm, float m_speed, PointF m_position, PointF m_anchor) {
        this.m_context = m_context;
        this.m_dm = m_dm;
        this.m_speed = m_speed;
        this.m_position = m_position;
        this.m_anchor = m_anchor;
    }

    @Override
    public void initInNewFunc() {
        try {
            if (m_chejia == null) {
                m_chejia = BitmapUtils.Scale_Cut(m_context, R.drawable.bookstore_ipad2x, m_dm, new PointF(0, 0.6f), new RectF(0, 0, 0, 0));
            }
            //根据锚点找出左上角
            int BitmapWidth = m_chejia.getWidth();
            int BitmapHeight = m_chejia.getHeight();
            if (m_outPic == null) {
                m_outPic = new RectF(0, 0, 0, 0);
                m_outPic.left = m_position.x - m_anchor.x * BitmapWidth;
                m_outPic.top = m_position.y - m_anchor.y * BitmapHeight;
                m_outPic.right =  BitmapWidth;
                m_outPic.bottom =  BitmapHeight;
            }
        } catch (Exception e) {
            Log.e("201706221114", e.toString());
        }
    }

    @Override
    public void draw(Canvas c) {
        try {
            c.drawBitmap(m_chejia, m_outPic.left, m_outPic.top, null);
        } catch (Exception e) {
            Log.e("201706221115", e.toString());
        }
    }

    @Override
    public void preDraw() {
        try {



        } catch (Exception e) {
            Log.e("201706221116", e.toString());
        }
    }

    @Override
    public void destory() {
        try {
        } catch (Exception e) {
            Log.e("201706221117", e.toString());
        }
    }

    public RectF getM_outPic() {
        return m_outPic;
    }
}
