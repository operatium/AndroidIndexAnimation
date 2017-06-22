package com.demo1

import android.graphics.PointF
import android.graphics.RectF
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.demo1.views.LeftNode
import com.demo1.views.Node
import com.demo1.views.Tree
import com.demo1.views.YuanShan
import kotlinx.android.synthetic.main.activity_kotlin.*;

class Kotlin : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        try {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_kotlin)
//            morningbackground()
            leftNode()
        } catch (e: Exception) {
            Log.e("201706211711", e.toString())
        }
    }

    override fun onResume() {
        try {
            super.onResume()
//            if (bg.isEmpty)
//                morningbackground()
        }catch (e: Exception){
            Log.e("201706212242", e.toString())
        }
    }

    fun morningbackground()
    {
        try {
            val dm = resources.displayMetrics
            bg.addView(Tree(50F,0.1f,dm.widthPixels,dm.heightPixels,this,R.drawable.background_yun,500,0.5f))
            bg.addView(Tree(100F,0.05f,dm.widthPixels,dm.heightPixels,this,R.drawable.background_yun,800,0.5f))
            bg.addView(YuanShan(dm.widthPixels,dm.heightPixels,this,R.drawable.background_yuanshan,1f))
            bg.addView(YuanShan(dm.widthPixels,dm.heightPixels,this,R.drawable.background_conglin,1.5f))
            bg.addView(Tree(dm.heightPixels*0.1f,0.73f,dm.widthPixels,dm.heightPixels,this,R.drawable.background_dashu,150,2f))
            bg.addView(Tree(dm.heightPixels*0.07f,0.75f,dm.widthPixels,dm.heightPixels,this,R.drawable.background_xiaoshu,90,2f))
            bg.addView(YuanShan(dm.widthPixels,dm.heightPixels,this,R.drawable.background_huocheguidao,2.5f))
            bg.addView(Tree(dm.heightPixels*0.1f,0.87f,dm.widthPixels,dm.heightPixels,this,R.drawable.flower3_00020,500,2.5f))
        }catch (e: Exception){
            Log.e("201706212145", e.toString())
        }
    }

    fun leftNode()
    {
        try {
            val dm = resources.displayMetrics
            bg.addView(Node(R.drawable.index_tree_l_143,this, PointF(0f,1f),dm, RectF(200/706f,0f,0f,0f), PointF(0f,1f), PointF(0f, dm.heightPixels.toFloat())))
            bg.addView(Node(R.drawable.index_tree_r_185,this, PointF(0f,1f),dm, RectF(0f,0f,240/568f,0f), PointF(1f,1f), PointF(dm.widthPixels.toFloat(), dm.heightPixels.toFloat())))
            bg.addView(Node(R.drawable.index_background05_l,this, PointF(0f,180/1152f),dm, RectF(0f,0f,0f,0f), PointF(0f,1f),PointF(0f, dm.heightPixels.toFloat())))
            bg.addView(Node(R.drawable.index_background05_r,this, PointF(0f,180/1152f),dm, RectF(0f,0f,0f,0f), PointF(1f,1f),PointF(dm.widthPixels.toFloat(), dm.heightPixels.toFloat())))
        }catch (e: Exception)
        {
            Log.e("201706212320",e.toString())
        }
    }
}
