package com.demo1

import android.graphics.PointF
import android.graphics.Rect
import android.graphics.RectF
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.demo1.views.Bird
import com.demo1.views.Node
import com.demo1.views.Tree
import com.demo1.views.YuanShan
import kotlinx.android.synthetic.main.activity_kotlin.*

class Kotlin : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        try {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_kotlin)
        } catch (e: Exception) {
            Log.e("201706211711", e.toString())
        }
    }

    override fun onResume() {
        try {
            super.onResume()
            if (bg.isEmpty) {
                morningbackground()
//                node()
            }
        }catch (e: Exception){
            Log.e("201706212242", e.toString())
        }
    }

    fun morningbackground()
    {
        try {
            val dm = resources.displayMetrics
            bg.addView(Tree(R.drawable.background_yun, PointF(0f,0.1f), PointF(0f,0f), PointF(0f,dm.heightPixels*0.1f),1/3f,0.5f,dm,this))
            bg.addView(Tree(R.drawable.background_yun, PointF(0f,0.05f), PointF(0f,0f),PointF(0f,dm.heightPixels*0.1f),1/2f,0.5f,dm,this))
//            bg.addView(YuanShan(R.drawable.background_yuanshan, PointF(0f,1f), PointF(0f,0f), PointF(0f,0f),1f,dm,this))
            bg.addView(YuanShan(R.drawable.index_background01, PointF(0f,193/614f), PointF(0f,1f), PointF(0f,dm.heightPixels*0.63f),1f,dm,this))
//            bg.addView(YuanShan(R.drawable.background_conglin, PointF(0f,1f), PointF(0f,0f),PointF(0f,0f),1.5f,dm,this))
            bg.addView(YuanShan(R.drawable.index_background02, PointF(0f,299/614f), PointF(0f,1f),PointF(0f,dm.heightPixels*0.85f),1.5f,dm,this))
//            bg.addView(Tree(R.drawable.background_dashu, PointF(0f,0.1f), PointF(0f,1f), PointF(0f,dm.heightPixels*588/720f),1/10f,2f,dm,this))
            bg.addView(YuanShan(R.drawable.index_background03, PointF(0f,212/614f), PointF(0f,1f), PointF(0f,dm.heightPixels*650/720f),2f,dm,this))
//            bg.addView(Tree(R.drawable.background_xiaoshu, PointF(0f,0.08f), PointF(0f,1f), PointF(0f,dm.heightPixels*588/720f),1/28f,2f,dm,this))
//            bg.addView(YuanShan(R.drawable.background_huocheguidao, PointF(0f,1f), PointF(0f,1f), PointF(0f, dm.heightPixels.toFloat()),2.5f,dm,this))
            bg.addView(YuanShan(R.drawable.index_background04, PointF(0f,117/614f), PointF(0f,1f), PointF(0f, dm.heightPixels.toFloat()),2.5f,dm,this))
            bg.addView(Tree(R.drawable.flower3_00020, PointF(0f,0.1f), PointF(0f,1f), PointF(0f,dm.heightPixels*700/720f),1/4f,2.5f,dm,this))
            bg.addView(Bird(Bird.FlyLineType.Line1,7f, Rect(128,128,128,128),this,dm))
            bg.addView(Bird(Bird.FlyLineType.Line2,7f, Rect(128,128,128,128),this,dm))
            bg.addView(Bird(Bird.FlyLineType.Line3,7f, Rect(128,128,128,128),this,dm))
        }catch (e: Exception){
            Log.e("201706212145", e.toString())
        }
    }

    fun node()
    {
        try {
            val dm = resources.displayMetrics
            bg.addView(Node(R.drawable.index_tree_l_143,this, PointF(0f,1f),dm, RectF(200/706f,0.1f,0f,0f), PointF(0f,1f), PointF(0f, dm.heightPixels.toFloat())))
            bg.addView(Node(R.drawable.index_tree_r_185,this, PointF(0f,1f),dm, RectF(0f,0.1f,240/568f,0f), PointF(1f,1f), PointF(dm.widthPixels.toFloat(), dm.heightPixels.toFloat())))
            bg.addView(Node(R.drawable.index_background05_l,this, PointF(0f,180/1152f),dm, RectF(0f,0f,0f,0f), PointF(0f,1f),PointF(0f, dm.heightPixels.toFloat())))
            bg.addView(Node(R.drawable.index_background05_r,this, PointF(0f,180/1152f),dm, RectF(0f,0f,0f,0f), PointF(1f,1f),PointF(dm.widthPixels.toFloat(), dm.heightPixels.toFloat())))
        }catch (e: Exception)
        {
            Log.e("201706212320",e.toString())
        }
    }
}
