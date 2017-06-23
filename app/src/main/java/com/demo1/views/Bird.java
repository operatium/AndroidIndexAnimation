package com.demo1.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.DisplayMetrics;
import android.util.Log;

import com.demo1.R;
import com.demo1.utils.BitmapUtils;

import java.util.ArrayList;

/**
 * Created by java on 2017/6/23.
 */

public class Bird implements SurfaceViewCallBack {
    private ArrayList<Integer> m_res;
    private Context m_context;
    private DisplayMetrics m_dm;
    private FlyLineType m_flyline;
    private float m_speed;//整条轨迹运行完成的时间(秒)
    private ArrayList<Bitmap> m_bitmap;
    private int m_index;
    private int m_count;
    private float m_t;
    private Paint m_paint;
    private Bitmap m_drawBitmap;//提供当前帧的bitmap
    private PointF m_drawPoint;//提供当前帧的位置
    private PointF point1 = new PointF();
    private PointF point2 = new PointF();
    private PointF point3 = new PointF();
    private PointF point4 = new PointF();

    public Bird(FlyLineType m_flyline, float m_speed, Rect color, Context m_context, DisplayMetrics m_dm) {
        try {
            this.m_context = m_context;
            this.m_dm = m_dm;
            this.m_flyline = m_flyline;
            this.m_speed = m_speed;
            m_index = 0;
            m_t = 0;
            m_paint = new Paint();
            // 获取每个SeekBar当前的值
            float progressR = color.left/128f;
            float progressG = color.top/128f;
            float progressB = color.right/128f;
            float progressA= color.bottom/128f;
            Log.i("main", "R：G：B="+progressR+"："+progressG+"："+progressB);
            // 根据SeekBar定义RGBA的矩阵
            float[] src = new float[]{
                    progressR, 0, 0, 0, 0,
                    0, progressG, 0, 0, 0,
                    0, 0, progressB, 0, 0,
                    0, 0, 0, progressA, 0};
            // 定义ColorMatrix，并指定RGBA矩阵
            ColorMatrix colorMatrix = new ColorMatrix();
            colorMatrix.set(src);
            // 设置Paint的颜色
            m_paint.setColorFilter(new ColorMatrixColorFilter(src));
        } catch (Exception e) {
            Log.e("201706231353", e.toString());
        }
    }

    @Override
    public void initInNewFunc() {
        try {
            if(m_res == null)
                m_res = new ArrayList<>();
            m_res.add(R.drawable.cyanbird_00000);
            m_res.add(R.drawable.cyanbird_00001);
            m_res.add(R.drawable.cyanbird_00002);
            m_res.add(R.drawable.cyanbird_00003);
            m_count = m_res.size();
            if(m_bitmap == null)
                m_bitmap = new ArrayList<>();
            for (Integer it : m_res)
            {
                m_bitmap.add(BitmapUtils.Scale_Cut(m_context,it,m_dm,new PointF(0,0.1f),new RectF(0,0,0,0)));
            }
        } catch (Exception e) {
            Log.e("201706231354", e.toString());
        }
    }

    @Override
    public void draw(Canvas canvas) {
        try {
            if (m_drawBitmap != null) {
                canvas.drawBitmap(m_drawBitmap, m_drawPoint.x, m_drawPoint.y, m_paint);
            }
        } catch (Exception e) {
            Log.e("201706231355", e.toString());
        }
    }

    @Override
    public void preDraw() {
        try {
            if (m_index >= m_count)
                m_index = 0;
            else {
                m_index = (int) ((m_t *80)%m_count);
            }
            if (m_t >= 1) {
                m_t = 0;
                m_speed = (float) (Math.random()*1000)%4+2;
            }
            else {
                m_t += (1 / m_speed / 30);
            }
            m_drawBitmap = m_bitmap.get(m_index);
            m_drawPoint = getY4FlyLineType(m_flyline, m_t);
//            Log.d("show m_t",m_t+"/"+m_index);

        } catch (Exception e) {
            Log.e("201706231641", e.toString());
        }
    }

    private PointF getY4FlyLineType(FlyLineType type, float t) {
        PointF point = new PointF();
        try {
            if (t > 1 || t < 0)
                t = 0;
            float oneMinusT = 1.0f - t;
            switch (type) {
                case Line1:
                    point1.x = m_dm.widthPixels;
                    point2.x = m_dm.widthPixels * 0.3f;
                    point3.x = m_dm.widthPixels * 0.6f;
                    point4.x = 0;
                    point1.y = 0;
                    point2.y = m_dm.heightPixels * 0.2f;
                    point3.y = m_dm.heightPixels * 0.4f;
                    point4.y = 0;
                    break;
                case Line2:
                    point1.x = m_dm.widthPixels;
                    point2.x = m_dm.widthPixels * 0.8f;
                    point3.x = m_dm.widthPixels * 0.2f;
                    point4.x = 0;
                    point1.y = 0;
                    point2.y = m_dm.heightPixels * 0.2f;
                    point3.y = m_dm.heightPixels * 0.1f;
                    point4.y = 0;
                    break;
                case Line3:
                    point1.x = m_dm.widthPixels;
                    point2.x = m_dm.widthPixels * 0.6f;
                    point3.x = m_dm.widthPixels * 0.3f;
                    point4.x = 0;
                    point1.y = 0;
                    point2.y = m_dm.heightPixels * 0.2f;
                    point3.y = m_dm.heightPixels * 0.3f;
                    point4.y = 0;
                    break;
            }
            point.x = oneMinusT * oneMinusT * oneMinusT * (point1.x)
                    + 3 * oneMinusT * oneMinusT * t * (point2.x)
                    + 3 * oneMinusT * t * t * (point3.x)
                    + t * t * t * (point4.x);

            point.y = oneMinusT * oneMinusT * oneMinusT * (point1.y)
                    + 3 * oneMinusT * oneMinusT * t * (point2.y)
                    + 3 * oneMinusT * t * t * (point3.y)
                    + t * t * t * (point4.y);
        } catch (Exception e) {
            Log.e("201706231446", e.toString());
        }
        return point;
    }

    @Override
    public void destory() {
        try {
            for (Bitmap it : m_bitmap){
                it = null;
            }
            m_bitmap.clear();
            m_res.clear();
            m_paint= null;
        } catch (Exception e) {
            Log.e("201706231356", e.toString());
        }
    }

    public enum FlyLineType {
        Line1, Line2, Line3
    }
}
