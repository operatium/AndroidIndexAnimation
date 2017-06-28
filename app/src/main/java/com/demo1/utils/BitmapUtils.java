package com.demo1.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.RectF;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.Size;

public class BitmapUtils {

    //缩放和裁切
    public static Bitmap Scale_Cut(Context context, int res, DisplayMetrics dm, PointF scale, RectF cutRatio) {
        Bitmap m_bitmap = null;
        try {
            //加载原始图片
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = false;
            options.inPreferredConfig = Bitmap.Config.RGB_565;
            options.inScaled = false;
            Bitmap tempBitmap = BitmapFactory.decodeResource(context.getResources(), res, options);
            //原始位图上剪切区域
            int x = (int) (tempBitmap.getWidth() * cutRatio.left);
            int y = (int) (tempBitmap.getHeight() * cutRatio.top);
            int width = (int) (tempBitmap.getWidth() - cutRatio.right * tempBitmap.getWidth() - x);
            int height = (int) (tempBitmap.getHeight() - cutRatio.bottom * tempBitmap.getHeight() - y);
            //计算尺寸
            float newBitmapHeight = dm.heightPixels * scale.y;//新位图的高
            float scaleHeight = newBitmapHeight / height;//等比例缩放系数
            float newBitmapWidth = width * scaleHeight;//新位图的宽
//            Log.i("tempBitmap.getWidth = ", tempBitmap.getWidth() + "");
//            Log.i("tempBitmap.getHeight = ", tempBitmap.getHeight() + "");
//            Log.i("newBitmap.getWidth = ", newBitmapWidth + "");
//            Log.i("newBitmap.getHeight = ", newBitmapHeight + "");
//            Log.i("scaleHeight = ", scaleHeight + "");
//            Log.i("bitmap.width = ", width + "");
//            Log.i("bitmap.height = ", height + "");
            Matrix matrix = new Matrix();
            matrix.postScale(scaleHeight, scaleHeight);
            m_bitmap = Bitmap.createBitmap(tempBitmap, x, y, width, height, matrix, true);
            tempBitmap = null;
//            Log.i("m_bitmap.getWidth = ", m_bitmap.getWidth() + "");
//            Log.i("m_bitmap.getHeight = ", m_bitmap.getHeight() + "");
        } catch (Exception e) {
            Log.e("201706251428", e.toString());
        }
        return m_bitmap;
    }

    //图片旋转
    public static Bitmap rotate(Bitmap bitmap,float rotateRotationAngle) {
        try {
            Matrix matrix = new Matrix();
            matrix.setRotate(rotateRotationAngle,bitmap.getWidth()/2,bitmap.getHeight()/2);
            Bitmap bitmap1 = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
            //旋转之后图片尺寸变化了
            float w = (bitmap1.getWidth() - bitmap.getWidth())/2;
            float h = (bitmap1.getHeight() - bitmap.getHeight())/2;
            Bitmap bitmap2 = Bitmap.createBitmap(bitmap1, (int)w, (int)h, bitmap.getWidth(), bitmap.getHeight(), new Matrix(), true);
            return bitmap2;
        } catch (Exception e) {
            Log.e("201706251502", e.toString());
            return null;
        }
    }

    //根据锚点获取左上角坐标
    public static PointF getLeftAndTopPoint(PointF anchor,//图片的锚点
                                            PointF positionScale4parent,//图片位于当前控件的坐标
                                            PointF childsize) {//图片的尺寸
        PointF lefttop = new PointF();
        try {
            lefttop.x = positionScale4parent.x - anchor.x * childsize.x;
            lefttop.y = positionScale4parent.y - anchor.y * childsize.y;
        } catch (Exception e) {
            Log.e("201706251429", e.toString());
        }
        return lefttop;
    }
}
