package com.demo1.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.graphics.RectF;
import android.util.DisplayMetrics;
import android.util.Log;

import com.demo1.utils.BitmapUtils;

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

    public Node(int m_res, Context m_context, PointF scale, DisplayMetrics dm, RectF cut, PointF m_anchor, PointF m_position) {
        this.m_res = m_res;
        this.m_context = m_context;
        this.scale = scale;
        this.dm = dm;
        this.m_yuanPic = cut;//位图剪切 left = x轴左侧的剪切比例  top = y轴上侧的剪切比例 right = x轴右侧的剪切比例  bottom = y轴下侧的剪切比例
        this.m_anchor = m_anchor;
        this.m_position = m_position;
    }

    @Override
    public void initInNewFunc() {
        m_bitmap = BitmapUtils.Scale_Cut(m_context,m_res,dm,scale,m_yuanPic);
        //根据锚点找出左上角
        int BitmapWidth = m_bitmap.getWidth();
        int BitmapHeight = m_bitmap.getHeight();
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
            }
        } catch (Exception e) {
            Log.e("201706221032", e.toString());
        }
    }

    @Override
    public void preDraw() {
        try {
        } catch (Exception e) {
            Log.e("201706221032", e.toString());
        }
    }

    @Override
    public void destory() {
        m_bitmap = null;
        m_outPic = null;
        m_context = null;
    }
}
