package com.demo1.views;

import android.graphics.Canvas;

/**
 * Created by java on 2017/6/21.
 */

public interface SurfaceViewCallBack {
    void initInNewFunc();
    void draw(Canvas c);
    void preDraw();
    void destory();
}
