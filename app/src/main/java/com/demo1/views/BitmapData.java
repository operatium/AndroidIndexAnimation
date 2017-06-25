package com.demo1.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.PointF;
import android.graphics.RectF;
import android.util.DisplayMetrics;
import android.util.Log;

import com.demo1.utils.BitmapUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;

/**
 * Created by Administrator on 2017/6/25.
 */

public class BitmapData {
    private Context m_context;
    private HashMap<String, Bitmap> m_data;
    private DisplayMetrics m_dm;

    private static class LazyHolder {
        private static final BitmapData INSTANCE = new BitmapData();
    }

    public static final BitmapData getInstance() {
        return LazyHolder.INSTANCE;
    }

    public void init(Context context,DisplayMetrics dm,boolean hengpping)
    {
        try {
            m_context = context;
            if (hengpping) {
                m_dm = new DisplayMetrics();
                m_dm.widthPixels = dm.heightPixels;
                m_dm.heightPixels = dm.widthPixels;
            }
        }catch (Exception e)
        {
            Log.e("201706252206",e.toString());
        }
    }

    //剪切缩放缓存
    public Bitmap addBitmap(String key ,int res, PointF scale, RectF cut, transformation transformation) {
        Bitmap bitmap = null;
        try {
            if (m_data == null)
                m_data = new HashMap<>();
            if (!m_data.containsKey(key)) {
                bitmap = transformation.transform4Cut(m_context,res,m_dm,scale,cut);
                m_data.put(key, bitmap);
            }
            else
                bitmap = m_data.get(key);
        } catch (Exception e) {
            Log.e("201706251356", e.toString());
        }
        return bitmap;
    }

    //旋转缓存
    public Bitmap addBitmap(String key ,Bitmap bitmap,float rotate, transformation transformation) {
        try {
            if (m_data == null)
                m_data = new HashMap<>();
            if (!m_data.containsKey(key)) {
                bitmap = transformation.transform4Rotate(bitmap,rotate);
                m_data.put(key, bitmap);
            }
            else
                bitmap = m_data.get(key);
        } catch (Exception e) {
            Log.e("201706251356", e.toString());
        }
        return bitmap;
    }

    public Bitmap getBitmap(String key) {
        Bitmap bitmap = null;
        try {
            if (m_data.containsKey(key))
                bitmap = m_data.get(key);
        } catch (Exception e) {
            Log.e("201706251355", e.toString());
        }
        return bitmap;
    }

    public void delBitmap(String key){
        try{
            if(m_data.containsKey(key)) {
                Bitmap b = m_data.get(key);
                if(!b.isRecycled())
                    b.recycle();
                b = null;
                m_data.remove(key);
                System.gc();
            }
        }catch (Exception e)
        {
            Log.e("201706252123",e.toString());
        }
    }

    public interface transformation{
        Bitmap transform4Cut(Context context, int res, DisplayMetrics dm, PointF scale, RectF cut);
        Bitmap transform4Rotate(Bitmap bitmap,float rotateRotationAngle);
    }
}
