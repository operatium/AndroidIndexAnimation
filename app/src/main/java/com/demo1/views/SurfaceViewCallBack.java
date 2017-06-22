package com.demo1.views;

import android.graphics.Canvas;

/**
 * Created by java on 2017/6/21.
 */

public interface SurfaceViewCallBack {
    public void initInNewFunc();
    public void draw(Canvas c);
    public void destory();
}
