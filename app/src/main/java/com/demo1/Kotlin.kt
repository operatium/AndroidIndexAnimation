package com.demo1

import android.graphics.PointF
import android.graphics.Rect
import android.graphics.RectF
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.ImageView
import android.widget.RelativeLayout
import com.demo1.utils.BitmapUtils
import com.demo1.views.Bird
import com.demo1.views.Tree
import com.demo1.views.YuanShan
import kotlinx.android.synthetic.main.activity_kotlin.*
import org.jetbrains.anko.alignParentBottom
import org.jetbrains.anko.alignParentEnd
import org.jetbrains.anko.alignParentStart

class Kotlin : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        try {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_kotlin)
            if (bg.isEmpty) {
                morningbackground()
            }
            node()

        } catch (e: Exception) {
            Log.e("201706211711", e.toString())
        }
    }

    override fun onResume() {
        try {
            super.onResume()
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
            bg.addView(YuanShan(R.drawable.index_background01, PointF(0f,193/614f), PointF(0f,1f), PointF(0f,dm.heightPixels*0.63f),1f,dm,this))
            bg.addView(YuanShan(R.drawable.index_background02, PointF(0f,299/614f), PointF(0f,1f),PointF(0f,dm.heightPixels*0.85f),1.5f,dm,this))
            bg.addView(YuanShan(R.drawable.index_background03, PointF(0f,212/614f), PointF(0f,1f), PointF(0f,dm.heightPixels*650/720f),2f,dm,this))
            bg.addView(YuanShan(R.drawable.index_background04, PointF(0f,117/614f), PointF(0f,1f), PointF(0f, dm.heightPixels.toFloat()),2.5f,dm,this))
            bg.addView(Tree(R.drawable.flower3_00020, PointF(0f,0.1f), PointF(0f,1f), PointF(0f,dm.heightPixels*700/720f),1/4f,2.5f,dm,this))
            bg.addView(Bird(Bird.FlyLineType.Line1,7f, Rect(128,128,128,128),this,dm))
            bg.addView(Bird(Bird.FlyLineType.Line2,7f, Rect(128,128,128,128),this,dm))
            bg.addView(Bird(Bird.FlyLineType.Line3,7f, Rect(128,128,128,128),this,dm))
            bg.addView(Bird(Bird.FlyLineType.Line4,7f, Rect(128,128,128,128),this,dm))
        }catch (e: Exception){
            Log.e("201706212145", e.toString())
        }
    }

    fun node()
    {
        try {
            val dm = resources.displayMetrics
            //左边树
            val leftTree = BitmapUtils.Scale_Cut(this,R.drawable.index_tree_l_143,dm, PointF(0f,1f), RectF(200/706f,0.1f,0f,0f))
            var imageviewleft = ImageView(this)
            val paramsleft = RelativeLayout.LayoutParams(leftTree!!.width,leftTree!!.height)
            paramsleft.alignParentStart()
            paramsleft.alignParentBottom()
            imageviewleft!!.setLayoutParams(paramsleft)
            imageviewleft!!.setImageBitmap(leftTree)

            //右边树
            val rightTree = BitmapUtils.Scale_Cut(this,R.drawable.index_tree_r_185,dm, PointF(0f,1f), RectF(0f,0.1f,240/568f,0f))
            var imageviewright = ImageView(this)
            val paramsright = RelativeLayout.LayoutParams(rightTree!!.width,rightTree!!.height)
            paramsright.alignParentEnd()
            paramsright.alignParentBottom()
            imageviewright!!.setLayoutParams(paramsright)
            imageviewright!!.setImageBitmap(rightTree)

            //左边草
            val leftGress = BitmapUtils.Scale_Cut(this,R.drawable.index_background05_l,dm, PointF(0f,180/1152f), RectF(0f,0f,0f,0f))
            var imageviewleftGress = ImageView(this)
            val paramsleftGress = RelativeLayout.LayoutParams(leftGress!!.width,leftGress!!.height)
            paramsleftGress.alignParentStart()
            paramsleftGress.alignParentBottom()
            imageviewleftGress!!.setLayoutParams(paramsleftGress)
            imageviewleftGress!!.setImageBitmap(leftGress)

            //右边草
            val rightGress = BitmapUtils.Scale_Cut(this,R.drawable.index_background05_r,dm, PointF(0f,180/1152f), RectF(0f,0f,0f,0f))
            var imageviewrightGress = ImageView(this)
            val paramsrightGress = RelativeLayout.LayoutParams(rightGress!!.width,rightGress!!.height)
            paramsrightGress.alignParentEnd()
            paramsrightGress.alignParentBottom()
            imageviewrightGress!!.setLayoutParams(paramsrightGress)
            imageviewrightGress!!.setImageBitmap(rightGress)


            relative.addView(imageviewleft)
            relative.addView(imageviewright)
            relative.addView(imageviewleftGress)
            relative.addView(imageviewrightGress)

        }catch (e: Exception)
        {
            Log.e("201706212320",e.toString())
        }
    }
}
