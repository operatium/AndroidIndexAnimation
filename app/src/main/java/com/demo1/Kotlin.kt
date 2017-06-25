package com.demo1

import android.content.Context
import android.graphics.Color
import android.graphics.PointF
import android.graphics.Rect
import android.graphics.RectF
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.ImageView
import android.widget.RelativeLayout
import com.demo1.utils.BitmapUtils
import com.demo1.views.*
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.activity_kotlin.*
import org.jetbrains.anko.alignParentBottom
import org.jetbrains.anko.alignParentEnd
import org.jetbrains.anko.alignParentStart
import org.jetbrains.anko.alignParentTop
import android.content.Intent
import android.content.ComponentName
import android.os.IBinder
import android.content.ServiceConnection


class Kotlin : AppCompatActivity() {
    private var m_service: MyService? = null

    private val conn = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            try {
                m_service = (service as MyService.MyBinder).sevice
                m_service!!.postInvalidate()
            } catch (e: Exception) {
                Log.e("201706251248", e.toString())
            }
        }

        override fun onServiceDisconnected(name: ComponentName) {
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        try {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_kotlin)

            val intent = Intent(this@Kotlin, MyService::class.java)
            bindService(intent, conn, Context.BIND_AUTO_CREATE)

        } catch (e: Exception) {
            Log.e("201706211711", e.toString())
        }
    }

    override fun onResume() {
        try {
            super.onResume()
//            if (bg.isEmpty) {
//                morningbackground()
//            }
//            huoche()
//            node()
            Log.d("Kotlin","onResume")
        } catch (e: Exception) {
            Log.e("201706212242", e.toString())
        }
    }

    override fun onStop() {
        super.onStop()
        Log.d("Kotlin","onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        this.unbindService(conn)
        PreLoad.getInstance().clearHome()
        Log.d("Kotlin","onDestroy")
    }

    fun morningbackground() {
        try {
            val dm = resources.displayMetrics
            bg.addView(Tree(R.drawable.background_yun, PointF(0f, 0.1f), PointF(0f, 0f), PointF(0f, dm.heightPixels * 0.1f), 1 / 3f, 1f, dm, this))
            bg.addView(Tree(R.drawable.background_yun, PointF(0f, 0.05f), PointF(0f, 0f), PointF(0f, dm.heightPixels * 0.1f), 1 / 2f, 1f, dm, this))
            bg.addView(YuanShan(R.drawable.index_background01, PointF(0f, 193 / 614f), PointF(0f, 1f), PointF(0f, dm.heightPixels * 0.63f), 2f, dm, this))
            bg.addView(YuanShan(R.drawable.index_background02, PointF(0f, 299 / 614f), PointF(0f, 1f), PointF(0f, dm.heightPixels * 0.85f), 3f, dm, this))
            bg.addView(YuanShan(R.drawable.index_background03, PointF(0f, 212 / 614f), PointF(0f, 1f), PointF(0f, dm.heightPixels * 650 / 720f), 4f, dm, this))
            bg.addView(YuanShan(R.drawable.index_background04, PointF(0f, 117 / 614f), PointF(0f, 1f), PointF(0f, dm.heightPixels.toFloat()), 5f, dm, this))
            bg.addView(Tree(R.drawable.flower3_00020, PointF(0f, 0.05f), PointF(0f, 1f), PointF(0f, dm.heightPixels * 700 / 720f), 1 / 4f, 5f, dm, this))
            bg.addView(Bird(Bird.FlyLineType.Line1, 3f, Rect(128, 128, 128, 128), this, dm))
            bg.addView(Bird(Bird.FlyLineType.Line2, 5f, Rect(128, 128, 128, 128), this, dm))
            bg.addView(Bird(Bird.FlyLineType.Line3, 7f, Rect(128, 128, 128, 128), this, dm))
            bg.addView(Bird(Bird.FlyLineType.Line4, 2f, Rect(128, 128, 128, 128), this, dm))
        } catch (e: Exception) {
            Log.e("201706212145", e.toString())
        }
    }

    fun huoche() {
        try {

        } catch (e: Exception) {
            Log.e("201706241020", e.toString())
        }
    }

    fun node() {
        try {
            val dm = resources.displayMetrics
            //左边树
            val leftTree = BitmapUtils.Scale_Cut(this, R.drawable.index_tree_l_143, dm, PointF(0f, 1f), RectF(200 / 706f, 0.1f, 0f, 0f))
            var imageviewleft = ImageView(this)
            val paramsleft = RelativeLayout.LayoutParams(leftTree!!.width, leftTree!!.height)
            paramsleft.alignParentStart()
            paramsleft.alignParentBottom()
            imageviewleft!!.setLayoutParams(paramsleft)
            imageviewleft!!.setImageBitmap(leftTree)

            //右边树
            val rightTree = BitmapUtils.Scale_Cut(this, R.drawable.index_tree_r_185, dm, PointF(0f, 1f), RectF(0f, 0.1f, 240 / 568f, 0f))
            var imageviewright = ImageView(this)
            val paramsright = RelativeLayout.LayoutParams(rightTree!!.width, rightTree!!.height)
            paramsright.alignParentEnd()
            paramsright.alignParentBottom()
            imageviewright!!.setLayoutParams(paramsright)
            imageviewright!!.setImageBitmap(rightTree)

            //左边草
            val leftGress = BitmapUtils.Scale_Cut(this, R.drawable.index_background05_l, dm, PointF(0f, 180 / 1152f), RectF(0f, 0f, 0f, 0f))
            var imageviewleftGress = ImageView(this)
            val paramsleftGress = RelativeLayout.LayoutParams(leftGress!!.width, leftGress!!.height)
            paramsleftGress.alignParentStart()
            paramsleftGress.alignParentBottom()
            imageviewleftGress!!.setLayoutParams(paramsleftGress)
            imageviewleftGress!!.setImageBitmap(leftGress)

            //右边草
            val rightGress = BitmapUtils.Scale_Cut(this, R.drawable.index_background05_r, dm, PointF(0f, 180 / 1152f), RectF(0f, 0f, 0f, 0f))
            var imageviewrightGress = ImageView(this)
            val paramsrightGress = RelativeLayout.LayoutParams(rightGress!!.width, rightGress!!.height)
            paramsrightGress.alignParentEnd()
            paramsrightGress.alignParentBottom()
            imageviewrightGress!!.setLayoutParams(paramsrightGress)
            imageviewrightGress!!.setImageBitmap(rightGress)


            //左边的伊拉
            val ella_bitmap = BitmapUtils.Scale_Cut(this, R.drawable.bookhome_girl_0000, dm, PointF(0f, 150 / 1152f), RectF(0f, 0f, 0f, 0f))
            var imageview_ella = ImageView(this)
            val paramsella = RelativeLayout.LayoutParams(ella_bitmap!!.width, ella_bitmap!!.height)
            paramsella.alignParentStart()
            paramsella.alignParentTop()
            paramsella.setMargins((dm.widthPixels * 0.09).toInt(), (dm.heightPixels * 0.17).toInt(), 0, 0)
            imageview_ella!!.setLayoutParams(paramsella)
            imageview_ella!!.setImageBitmap(ella_bitmap)

            //左边的灯
            val light_bitmap = BitmapUtils.Scale_Cut(this, R.drawable.index_light_ipad2x, dm, PointF(0f, 60 / 1152f), RectF(0f, 0f, 0f, 0f))
            var imageview_light = ImageView(this)
            val paramslight = RelativeLayout.LayoutParams(light_bitmap!!.width, light_bitmap!!.height)
            paramslight.alignParentStart()
            paramslight.alignParentTop()
            paramslight.setMargins((dm.widthPixels * 0.064 - light_bitmap.width / 2).toInt(), (dm.heightPixels * 0.705).toInt(), 0, 0)
            imageview_light!!.setLayoutParams(paramslight)
            imageview_light!!.setImageBitmap(light_bitmap)

            //右边头像
            val tou_bitmap = BitmapUtils.Scale_Cut(this, R.drawable.user_bg_ipad2x, dm, PointF(0f, 120 / 1152f), RectF(0f, 0f, 0f, 0f))
            var imageview_tou = CircleImageView(this)
            val paramstou = RelativeLayout.LayoutParams(tou_bitmap!!.width, tou_bitmap!!.height)
            paramstou.alignParentEnd()
            paramstou.alignParentTop()
            paramstou.setMargins(0, (dm.heightPixels * 0.05).toInt(), (dm.widthPixels * 0.05).toInt(), 0)
            imageview_tou!!.setLayoutParams(paramstou)
            imageview_tou!!.setImageResource(R.drawable.role)
            imageview_tou!!.borderWidth = (dm.heightPixels * 1 / 144f).toInt()
            imageview_tou!!.borderColor = Color.rgb(200, 160, 50)

            //皇冠
            val huangguan_bitmap = BitmapUtils.Scale_Cut(this, R.drawable.user_up_ipad2x, dm, PointF(0f, 150 / 1152f), RectF(0f, 0f, 0f, 0f))
            var imageview_huangguan = ImageView(this)
            val paramshuangguan = RelativeLayout.LayoutParams(huangguan_bitmap!!.width, huangguan_bitmap!!.height)
            paramshuangguan.alignParentEnd()
            paramshuangguan.alignParentTop()
            paramshuangguan.setMargins(0, (dm.heightPixels * 0.02).toInt(), (dm.widthPixels * 0.05).toInt(), 0)
            imageview_huangguan!!.setLayoutParams(paramshuangguan)
            imageview_huangguan!!.setImageBitmap(huangguan_bitmap)

            //右边的设置
            val set_bitmap = BitmapUtils.Scale_Cut(this, R.drawable.parents_ipad2x, dm, PointF(0f, 120 / 1152f), RectF(0f, 0f, 0f, 0f))
            var imageview_set = ImageView(this)
            val paramsset = RelativeLayout.LayoutParams(set_bitmap!!.width, set_bitmap!!.height)
            paramsset.alignParentEnd()
            paramsset.alignParentTop()
            paramsset.setMargins(0, (dm.heightPixels * 0.22).toInt(), (dm.widthPixels * 0.073 - light_bitmap.width / 2).toInt(), 0)
            imageview_set!!.setLayoutParams(paramsset)
            imageview_set!!.setImageBitmap(set_bitmap)

            relative.addView(imageviewleft)
            relative.addView(imageviewright)
            relative.addView(imageviewleftGress)
            relative.addView(imageviewrightGress)
            relative.addView(imageview_ella)
            relative.addView(imageview_tou)
            relative.addView(imageview_huangguan)
            relative.addView(imageview_light)
            relative.addView(imageview_set)
        } catch (e: Exception) {
            Log.e("201706212320", e.toString())
        }
    }
}
