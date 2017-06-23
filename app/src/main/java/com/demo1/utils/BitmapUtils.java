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

public class BitmapUtils {

    //缩放和裁切
    public static Bitmap Scale_Cut(Context context,int res, DisplayMetrics dm, PointF scale, RectF cutRatio)
    {
        Bitmap m_bitmap;
        //加载原始图片
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = false;
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        options.inScaled= false;
        Bitmap tempBitmap = BitmapFactory.decodeResource(context.getResources(), res,options);
        //原始位图上剪切区域
        int x = (int) (tempBitmap.getWidth() * cutRatio.left);
        int y = (int) (tempBitmap.getHeight() * cutRatio.top);
        int width = (int) (tempBitmap.getWidth() - cutRatio.right * tempBitmap.getWidth() - x);
        int height = (int) (tempBitmap.getHeight() - cutRatio.bottom * tempBitmap.getHeight() - y);
        //计算尺寸
        float newBitmapHeight = dm.heightPixels * scale.y;//新位图的高
        float scaleHeight = newBitmapHeight / height;//等比例缩放系数
        float newBitmapWidth = tempBitmap.getWidth() * scaleHeight;//新位图的宽
        Log.i("tempBitmap.getWidth = ", tempBitmap.getWidth()  + "");
        Log.i("tempBitmap.getHeight = ", tempBitmap.getHeight() + "");
        Log.i("newBitmap.getWidth = ", newBitmapWidth + "");
        Log.i("newBitmap.getHeight = ", newBitmapHeight + "");
        Log.i("scaleHeight = ", scaleHeight+ "");
        Log.i("bitmap.width = ", width + "");
        Log.i("bitmap.height = ", height + "");
        Matrix matrix = new Matrix();
        matrix.postScale(scaleHeight,scaleHeight);
        m_bitmap = Bitmap.createBitmap(tempBitmap, x, y, width, height, matrix, true);
        Log.i("m_bitmap.getWidth = ", m_bitmap.getWidth() + "");
        Log.i("m_bitmap.getHeight = ", m_bitmap.getHeight() + "");
        return m_bitmap;
    }

    //合图
    public static Bitmap leftAndRight_MergeBitmap(Bitmap firstBitmap, Bitmap secondBitmap,int jiange) {
        Bitmap bitmap = Bitmap.createBitmap(firstBitmap.getWidth()+secondBitmap.getWidth(),
                firstBitmap.getHeight(), Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawBitmap(firstBitmap, 0,0, null);
        canvas.drawBitmap(secondBitmap, firstBitmap.getWidth()+jiange, 0, null);
        return bitmap;
    }
}
