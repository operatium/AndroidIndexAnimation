package com.demo1.views.bookroom;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.demo1.R;
import com.demo1.utils.BitmapUtils;

/**
 * Created by java on 2017/6/27.
 */

public class BookView extends View {
    private static Bitmap m_vip;
    private static Bitmap m_download;
    private static Bitmap m_downloadover;
    private static Bitmap m_lock;
    private static Typeface m_typeface;
    private Context m_context;
    private float m_show = 1;
    private DisplayMetrics m_dm;
    private Bitmap m_book;
    private ShowMode m_showMode = new ShowMode();

    private Canvas m_canvas;
    private Paint m_Paint;
    private Bitmap m_tempBitmap;


    private int m_bookid = 0;
    private String m_url;

    public BookView(Context context) {
        super(context);
        m_context = context;
        init();
    }

    public BookView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        m_context = context;
        init();
    }

    public BookView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        m_context = context;
        init();
    }

    public void init() {
        try {
            m_dm = m_context.getResources().getDisplayMetrics();
            if (m_vip == null)
                m_vip = BitmapUtils.Scale_Cut(m_context, R.drawable.bookhome_niankatushubiaoshi_736h, m_dm, new PointF(0, 0.03f), new RectF(0, 0, 0, 0));
            if (m_download == null)
                m_download = BitmapUtils.Scale_Cut(m_context, R.drawable.bookhome_download, m_dm, new PointF(0, 0.1f), new RectF(0, 0, 0, 0));
            if (m_downloadover == null)
                m_downloadover = BitmapUtils.Scale_Cut(m_context, R.drawable.bookhome_yixiazaibiaoshi_736h, m_dm, new PointF(0, 0.03f), new RectF(0, 0, 0, 0));
            if (m_lock == null)
                m_lock = BitmapUtils.Scale_Cut(m_context, R.drawable.wodeshufang_shubensuoding_736h, m_dm, new PointF(0, 0.1f), new RectF(0, 0, 0, 0));
            if (m_book == null)
                m_book = BitmapUtils.Scale_Cut(m_context, R.drawable.book_cover, m_dm, new PointF(0, 0.3f), new RectF(0, 0, 0, 0));
            if (m_typeface == null)
                m_typeface = Typeface.createFromAsset(m_context.getAssets(), "fonts/wdtyj.ttf");
            m_showMode.setDownload(-6);
            m_showMode.setLock(0);
            m_showMode.setSwitchover(100);
            m_showMode.setVip(1);


            //清屏
            m_Paint = new Paint();
            m_Paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));

        } catch (Exception e) {
            Log.e("201706281047", e.toString());
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        try {
            setMeasuredDimension(m_book.getWidth(), m_book.getHeight());
            m_tempBitmap = Bitmap.createBitmap(m_book.getWidth(), m_book.getHeight(), Bitmap.Config.ARGB_8888);
            m_canvas = new Canvas(m_tempBitmap);
        } catch (Exception e) {
            Log.e("201706271743", e.toString());
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        try {
            canvas.save();
            float y_canvas = getHeight() *(1-m_showMode.getSwitchover()/ 100f);
            canvas.translate(0,  y_canvas);
            canvas.drawBitmap(m_book, 0, 0, null);
            //VIP
            if (m_showMode.getVip() == 1)
                canvas.drawBitmap(m_vip, getWidth() - m_vip.getWidth() / 2 * 3, m_vip.getWidth() / 2, null);

            //未下载
            if (m_showMode.getDownload() <= -1) {
                PointF p1 = BitmapUtils.getLeftAndTopPoint(new PointF(0.5f, 0.5f), new PointF(getWidth() / 2, getHeight() / 2), new PointF(m_download.getWidth(), m_download.getHeight()));
                canvas.drawBitmap(m_download, p1.x, p1.y, null);
            }

            //已下载
            if (m_showMode.getDownload() >= 201) {
                PointF p2 = BitmapUtils.getLeftAndTopPoint(new PointF(1f, 0f), new PointF(getWidth(), getHeight() * 0.8f), new PointF(m_downloadover.getWidth(), m_downloadover.getHeight()));
                canvas.drawBitmap(m_downloadover, p2.x, p2.y, null);
            }
            //下载过程
            if (m_showMode.getDownload() <= 100 && m_showMode.getDownload() >= 0) {
                Paint paint = new Paint(Color.GREEN);
                paint.setAlpha(155);
                paint.setAntiAlias(true);
                canvas.drawRect(new RectF(0, getHeight() * (100 - m_showMode.getDownload()) / 100f, getWidth(), getHeight()), paint);

                //字
                Paint paint11 = new Paint();
                    paint11.setColor(Color.BLACK);
                paint11.setTextSize(getWidth() / 4);
                paint11.setTextAlign(Paint.Align.CENTER);
                paint11.setAntiAlias(true);
                paint11.setTypeface(m_typeface);
                canvas.drawText(m_showMode.getDownload() + "%", getWidth() / 2, getHeight() * 0.55f, paint11);
                //描边
                Paint paint12 = new Paint(paint11);
                    paint12.setColor(Color.WHITE);
                paint12.setStyle(Paint.Style.STROKE);
                paint12.setAntiAlias(true);
                paint12.setStrokeWidth(1);
                canvas.drawText(m_showMode.getDownload() + "%", getWidth() / 2, getHeight() * 0.55f, paint12);
            }
            //解压过程
            if (m_showMode.getDownload() >= 101 && m_showMode.getDownload() <= 200) {
                int x = getWidth() / 2;
                int y = getHeight() / 2;
                int r = getWidth() / 4;
                int hudu = (int) ((m_showMode.getDownload() - 100) / 100f * 360);
                m_canvas.drawPaint(m_Paint);

                Paint paintStroke = new Paint();
                paintStroke.setStyle(Paint.Style.STROKE);
                paintStroke.setStrokeWidth(getWidth() / 20);
                paintStroke.setAntiAlias(true);
                m_canvas.drawCircle(x, y, r, paintStroke);

                Paint paintArc = new Paint();
                paintArc.setAntiAlias(true);
                paintArc.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));
                //圆心
                RectF rectF = new RectF(x - r, y - r, x + r, y + r);
                m_canvas.drawArc(rectF, 0, hudu, true, paintArc);

                Paint paintRect = new Paint(Color.RED);
                paintRect.setAntiAlias(true);
                paintRect.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.XOR));
                m_canvas.drawRect(new RectF(0, 0, getWidth(), getHeight()), paintRect);

                Paint pb = new Paint();
                pb.setAlpha(150);
                pb.setAntiAlias(true);
                canvas.drawBitmap(m_tempBitmap, 0, 0, pb);
            }
            //解压完成
            if (m_showMode.getDownload() >= 201 && m_showMode.getDownload()<=300){
                int x = getWidth() / 2;
                int y = getHeight() / 2;
                int r = (int) (getWidth() / 4+ (m_showMode.getDownload()-200)/100f*(getHeight()/2-getWidth()/4));
                m_canvas.drawPaint(m_Paint);

                Paint paintCircle = new Paint();
                paintCircle.setAntiAlias(true);
                m_canvas.drawCircle(x, y, r,paintCircle);

                Paint paintRect = new Paint(Color.RED);
                paintRect.setAntiAlias(true);
                paintRect.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.XOR));
                m_canvas.drawRect(new RectF(0, 0, getWidth(), getHeight()), paintRect);

                Paint pb = new Paint();
                pb.setAlpha(150);
                pb.setAntiAlias(true);
                canvas.drawBitmap(m_tempBitmap, 0, 0, pb);
            }
            if (m_showMode.getLock() == 1) {
                PointF p3 = BitmapUtils.getLeftAndTopPoint(new PointF(0.5f, 0.5f), new PointF(getWidth() / 2, getHeight() / 2), new PointF(m_lock.getWidth(), m_lock.getHeight()));
                canvas.drawBitmap(m_lock, p3.x, p3.y, null);
            }
            canvas.restore();
        } catch (Exception e) {
            Log.e("201706271744", e.toString());
        }
    }

    //重新加载
    public void reload()
    {
        try {
            if (m_bookid > 0) {

            }
        }catch (Exception e)
        {
            Log.e("201706281758",e.toString());
        }
    }

    public void setSwithchOver(int switchover) {
        m_showMode.setSwitchover(switchover);
        postInvalidate();
    }

    public void setDownload(int download)
    {
        m_showMode.setDownload(download-6);
        postInvalidate();
    }

    public void setBookUrl(String url)
    {
        m_url = url;
    }

    public void setBookId(int bookId)
    {
        m_bookid = bookId;
    }

    public class ShowMode {
        private int switchover;//切换书
        private int download;//下载
        private int vip;//会员书
        private int lock;//上锁

        public int getSwitchover() {
            return switchover;
        }

        public void setSwitchover(int switchover) {
            this.switchover = switchover;
        }

        public int getDownload() {
            return download;
        }

        public void setDownload(int download) {
            this.download = download;
        }

        public int getVip() {
            return vip;
        }

        public void setVip(int vip) {
            this.vip = vip;
        }

        public int getLock() {
            return lock;
        }

        public void setLock(int lock) {
            this.lock = lock;
        }
    }
}
