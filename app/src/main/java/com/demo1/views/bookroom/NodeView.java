package com.demo1.views.bookroom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PointF;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

import com.demo1.R;
import com.demo1.utils.BitmapUtils;

/**
 * Created by java on 2017/6/27.
 */

public class NodeView extends View {
    private Context m_context;
    private Bitmap m_bitmap;
    private PointF m_lefttop;

    private int m_backgroundcolor;
    private float m_heightscale;

    public NodeView(Context context) {
        this(context, null, 0);
    }

    public NodeView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NodeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        m_context = context;
        try {
            TypedArray styledAttributes = context.getTheme().obtainStyledAttributes(attrs, R.styleable.NodeView, defStyleAttr, 0);
            int n = styledAttributes.getIndexCount();
            int src = 0;
            for (int i = 0; i < n; i++) {
                int attr = styledAttributes.getIndex(i);
                switch (attr) {
                    case R.styleable.NodeView_src:
                        src = styledAttributes.getResourceId(attr, 0);
                        break;
                    case R.styleable.NodeView_backgroundcolor:
                        m_backgroundcolor = styledAttributes.getColor(attr, Color.WHITE);
                        break;
                    case R.styleable.NodeView_heightscale:
                        m_heightscale = styledAttributes.getFloat(attr,1);
                        break;
                }
            }
            setNode(src,new PointF(0,m_heightscale),new RectF(0,0,0,0),new PointF(0,0),new PointF(0,0) );
        }catch (Exception e)
        {
            Log.e("201706271339",e.toString());
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        try {
            if (m_bitmap != null)
                canvas.drawBitmap(m_bitmap, m_lefttop.x, m_lefttop.y, null);
        } catch (Exception e) {
            Log.e("201706271316", e.toString());
        }
    }

    public void setNode(int res, PointF scale, RectF cut, PointF anchor, PointF positionScale4parent) {
        try {
            if (res == 0)
                return;
            DisplayMetrics dm = m_context.getResources().getDisplayMetrics();
            m_bitmap = BitmapUtils.Scale_Cut(m_context, res, dm, scale, cut);
            m_lefttop = BitmapUtils.getLeftAndTopPoint(anchor, positionScale4parent, new PointF(m_bitmap.getWidth(), m_bitmap.getHeight()));
            setBackgroundColor(m_backgroundcolor);
        } catch (Exception e) {
            Log.e("201706271317", e.toString());
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        try {
            setMeasuredDimension(m_bitmap.getWidth(), m_bitmap.getHeight());
        }catch (Exception e)
        {
            Log.e("201706271340",e.toString());
        }
    }
}
