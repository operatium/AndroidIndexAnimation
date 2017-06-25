package com.demo1.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.PointF;
import android.graphics.RectF;
import android.util.DisplayMetrics;

import com.demo1.R;
import com.demo1.utils.BitmapUtils;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/6/25.
 */

public class PreLoad {
    private static class PriLoadHolder {
        private static final PreLoad INSTANCE = new PreLoad();
    }

    public static final PreLoad getInstance() {
        return PriLoadHolder.INSTANCE;
    }

    String[] keys = new String[]{"lunzi0", "lunzi1", "lunzi2", "lunzi3", "lunzi4", "lunzi5", "lunzi6", "lunzi7"};
    String[] starkeys = new String[]{"star0", "star1", "star2", "star3", "star4", "star5", "star6", "star7", "star8", "star9","star10", "star11", "star12", "star13", "star14"};
    String[] flagkeys = new String[]{"flag0","flag1","flag2"};
    Integer[] starRes = new Integer[]{R.drawable.star_00006,R.drawable.star_00007,R.drawable.star_00008,R.drawable.star_00009,R.drawable.star_00010,R.drawable.star_00011
            ,R.drawable.star_00012,R.drawable.star_00013,R.drawable.star_00014,R.drawable.star_00015,R.drawable.star_00016,R.drawable.star_00017,R.drawable.star_00018
            ,R.drawable.star_00019,R.drawable.star_00020};
    Integer[] flagRes = new Integer[]{R.drawable.flag_00002,R.drawable.flag_00001,R.drawable.flag_00000};


    //首页预加载
    public void preLoadHome()
    {
        //旋转
        BitmapData.transformation rotateAnimation = new BitmapData.transformation() {
            @Override
            public Bitmap transform4Cut(Context context, int res, DisplayMetrics dm, PointF scale, RectF cut) {
                return null;
            }

            @Override
            public Bitmap transform4Rotate(Bitmap bitmap, float rotateRotationAngle) {
                return BitmapUtils.rotate(bitmap, rotateRotationAngle);
            }
        };
        //剪切缩放
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
        //书城
        BitmapData.getInstance().addBitmap("bookcity", R.drawable.bookstore_ipad2x, new PointF(0, 0.6f), new RectF(0, 0, 0, 0), cut);
        //轮子
        Bitmap m_lunzi = BitmapData.getInstance().addBitmap(keys[0], R.drawable.wheel_ipad2x, new PointF(0, 0.1f), new RectF(0.06f, 0, 0, 0), cut);
        BitmapData.getInstance().addBitmap(keys[1], m_lunzi, 45, rotateAnimation);
        BitmapData.getInstance().addBitmap(keys[2], m_lunzi, 90, rotateAnimation);
        BitmapData.getInstance().addBitmap(keys[3], m_lunzi, 135, rotateAnimation);
        BitmapData.getInstance().addBitmap(keys[4], m_lunzi, 180, rotateAnimation);
        BitmapData.getInstance().addBitmap(keys[5], m_lunzi, 225, rotateAnimation);
        BitmapData.getInstance().addBitmap(keys[6], m_lunzi, 270, rotateAnimation);
        BitmapData.getInstance().addBitmap(keys[7], m_lunzi, 315, rotateAnimation);
        //书城星星
        for (int i =0;i<starRes.length ;++i){
            BitmapData.getInstance().addBitmap(starkeys[i],starRes[i],new PointF(0,0.4f),new RectF(0,0,0,0),cut);
        }
        //书城红旗
        for (int i=0 ; i<flagRes.length; ++i){
            BitmapData.getInstance().addBitmap(flagkeys[i],flagRes[i],new PointF(0,0.03f),new RectF(0,0,0,0),cut);
        }
        //背景
        BitmapData.getInstance().addBitmap(R.drawable.index_background01+"",R.drawable.index_background01,new PointF(0f, 193 / 614f),new RectF(0,0,0,0),cut);
        BitmapData.getInstance().addBitmap(R.drawable.index_background02+"",R.drawable.index_background02,new PointF(0f, 299 / 614f),new RectF(0,0,0,0),cut);
        BitmapData.getInstance().addBitmap(R.drawable.index_background03+"",R.drawable.index_background03,new PointF(0f, 212 / 614f),new RectF(0,0,0,0),cut);
        BitmapData.getInstance().addBitmap(R.drawable.index_background04+"",R.drawable.index_background04,new PointF(0f, 117 / 614f),new RectF(0,0,0,0),cut);
        //花
        BitmapData.getInstance().addBitmap(R.drawable.flower3_00020+"",R.drawable.flower3_00020,new PointF(0f, 0.05f),new RectF(0,0,0,0),cut);
    }

    //首页资源卸载
    public void clearHome()
    {
        for (String it : keys){
            BitmapData.getInstance().delBitmap(it);
        }
        for (String it : starkeys){
            BitmapData.getInstance().delBitmap(it);
        }
        for (String it : flagkeys){
            BitmapData.getInstance().delBitmap(it);
        }
        BitmapData.getInstance().delBitmap(R.drawable.index_background01+"");
        BitmapData.getInstance().delBitmap(R.drawable.index_background02+"");
        BitmapData.getInstance().delBitmap(R.drawable.index_background03+"");
        BitmapData.getInstance().delBitmap(R.drawable.index_background04+"");
        BitmapData.getInstance().delBitmap(R.drawable.flower3_00020+"");
        BitmapData.getInstance().delBitmap("bookcity");
    }

}
