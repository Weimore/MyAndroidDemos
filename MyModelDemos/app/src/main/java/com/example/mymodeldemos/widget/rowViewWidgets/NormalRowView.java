package com.example.mymodeldemos.widget.rowViewWidgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.os.Looper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.mymodeldemos.R;
import com.example.mymodeldemos.utils.LogUtils;
import com.example.mymodeldemos.utils.ScreenUtils;

/**
 * Created by 吴城林 on 2017/7/10.
 */

public class NormalRowView extends RelativeLayout implements View.OnClickListener{


    private Context mContext;

    private ImageView mRowViewIcon;
    private TextView mRowViewText;
//    private ImageView mRowViewPress;
    private rowClickListener mListener;
    RowDescriptor mDescriptor;

    private int textSize=13;    //文字的size
    private String text;
    private int mIconColor= Color.parseColor("#32AA78");
    private int mTextLeftMargin;  //文字距离icon的左边距
    private int mIconHeight;    //icon的宽高
    private int mIconLeftMargin;  //icon的左边距
    private int mPressHeight;  //press的高度
    private int mPressWidth;  //press的宽度
    private int mPressRightMargin;  //press的右边距

    private Bitmap mIconImage;  //icon的源图片
    private Bitmap mPressImage; //press箭头的图片
    private boolean drawPressButton=false;  //决定是否绘制press箭头

    private Paint mIconPaint;  //绘制icon的paint
    private Paint mPressPaint; //绘制press箭头的paint
    private Paint mTextPaint;  //绘制文字的paint
    private Bitmap mBitmap;  //在该bitmap上进行一系列对icon的绘制，最后再在ondraw（）中绘制该bitmap
    private Rect mIconRect;  //确定icon大小及位置的rect
    private Rect mPressRect; //确定press大小及位置的rect
    private Rect mTextRect;  //确定text大小及位置的rect

    public NormalRowView(Context context) {
        this(context,null);
    }

    public NormalRowView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public NormalRowView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext=context;
        LayoutInflater.from(context).inflate(R.layout.nomal_row_view,this);

        TypedArray ta=context.obtainStyledAttributes(attrs,R.styleable.NormalRowView);

        textSize= (int) ta.getDimension(R.styleable.NormalRowView_rowTextSize,ScreenUtils.sp2px(mContext,textSize));
        mIconColor=ta.getColor(R.styleable.NormalRowView_rowIconColor,mIconColor);
        ta.recycle();

        init();
    }

    private void init() {
//        mRowViewIcon= (ImageView) findViewById(R.id.row_view_icon);
//        mRowViewText= (TextView) findViewById(R.id.row_view_text);
//        mRowViewPress= (ImageView) findViewById(R.id.row_view_press);

    }

    public void initDatas(RowDescriptor descriptor,rowClickListener listener) {
        mListener=listener;
        mDescriptor=descriptor;
        if(mDescriptor!=null){
            notifyDataChanged();
        }
    }

    private void notifyDataChanged() {
//        mRowViewIcon.setBackgroundResource(mDescriptor.iconImageId);
//        mRowViewText.setText(mDescriptor.text);
        mIconImage= BitmapFactory.decodeResource(getResources(),mDescriptor.iconImageId);
        mPressImage=BitmapFactory.decodeResource(getResources(),R.drawable.press);
        text=mDescriptor.text;

        if (mDescriptor.rowViewEnum!=null){//如果可点击
//            mRowViewPress.setVisibility(View.VISIBLE);
            drawPressButton=true;
            setBackgroundResource(R.drawable.rowbackground);
            setOnClickListener(this);
        }else {//如果不可点击
//            mRowViewPress.setVisibility(View.GONE);
            drawPressButton=false;
            setBackgroundResource(R.color.colorWhite);

        }

        //设置text绘制相关
        mTextPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextSize(textSize);
        mTextRect=new Rect();
        mTextPaint.getTextBounds(text, 0, text.length(), mTextRect);  //测量文字的范围，可以获得要绘制文字的宽高
    }

    @Override
    public void onClick(View v) {
        if(mListener!=null){
            mListener.onRowClicked(mDescriptor.rowViewEnum);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mIconHeight=ScreenUtils.dp2px(mContext,20);
        mIconLeftMargin=ScreenUtils.dp2px(mContext,15)+getPaddingLeft();
        int top=(getMeasuredHeight()-mIconHeight)/2+getPaddingTop();
        mIconRect=new Rect(mIconLeftMargin,top,mIconHeight+mIconLeftMargin,mIconHeight+top); //图标的位置取决于iconRect相对于View的位置

        mPressHeight=ScreenUtils.dp2px(mContext,15);
        mPressWidth=ScreenUtils.dp2px(mContext,15);
        mPressRightMargin=ScreenUtils.dp2px(mContext,20);
        int pressLeft=getMeasuredWidth()-mPressWidth-getPaddingRight()-mPressRightMargin;
        int pressTop=(getMeasuredHeight()-mPressHeight)/2;
        mPressRect=new Rect(pressLeft,pressTop,mPressWidth+pressLeft,mPressHeight+pressTop);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawIcon();  //绘制icon
        canvas.drawBitmap(mBitmap,0,0,null);
        drawText(canvas);  //绘制文字
        drawPress(drawPressButton);  //绘制press箭头
        canvas.drawBitmap(mBitmap,0,0,null);
    }

    //绘制press箭头
    private void drawPress(boolean drawPressButton) {
        if(drawPressButton==false){
            return;
        }
        mBitmap=null;
        LogUtils.d("draw.....");
        mBitmap=Bitmap.createBitmap(getMeasuredWidth(),getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(mBitmap);

        mPressPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        mPressPaint.setColor(mIconColor);  //press的color和icon相同
        mPressPaint.setDither(true);
        canvas.drawRect(mPressRect,mPressPaint);  //先绘制颜色
        mPressPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        canvas.drawBitmap(mPressImage,null,mPressRect,mPressPaint);

    }

    //对文字进行绘制
    private void drawText(Canvas canvas) {

        mTextLeftMargin=ScreenUtils.dp2px(mContext,40)+getPaddingLeft();

        int left= mTextLeftMargin+mIconRect.width();
        int baseLine= getMeasuredHeight()/2+mTextRect.height()/2;  //文字的基准线
        canvas.drawText(text,left,baseLine,mTextPaint);
    }

    private void drawIcon() {
        //在mBitmap上进行各种绘制
        mBitmap = Bitmap.createBitmap(getMeasuredWidth(),getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(mBitmap);

        mIconPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        mIconPaint.setColor(mIconColor);
        mIconPaint.setDither(true);
        canvas.drawRect(mIconRect,mIconPaint);  //先绘制颜色
        mIconPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        canvas.drawBitmap(mIconImage,null,mIconRect,mIconPaint);
    }

    //供外部改变color的方法
    public void setIconColor(int colorResource){
        mIconColor=getResources().getColor(colorResource);
        invalidateView();//改变颜色后需要重新绘制
    }

    private void invalidateView() {
        if(Looper.getMainLooper()==Looper.myLooper()){//如果当前线程为UI线程的话，直接重绘
            invalidate();
        }else {//如果不是UI线程,则post到消息队列中，等待自动调用重绘
            postInvalidate();
        }
    }
}
