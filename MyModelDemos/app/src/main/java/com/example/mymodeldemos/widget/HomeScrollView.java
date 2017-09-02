package com.example.mymodeldemos.widget;

import android.content.Context;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.example.mymodeldemos.utils.ScreenUtils;

/**
 * Created by 吴城林 on 2017/7/8.
 * 为SecondActivity自定义的滚动View
 * 当前问题：和系统栏不兼容(已解决)
 */

public class HomeScrollView extends ScrollView {

    private LinearLayout mWrapper;
    private ViewGroup mTopLayout;
    private ViewGroup mShowLayout;
    private View mImageCoverView;
    private View myToolbar;

    private boolean once;   //保证只measure一次

    private int mScreenHeight;   //用于测量该view高度
    private int mTopHeight = 180;  //该高度即为顶部图片布局高度，用来计算覆盖层alpha值
    private float skewNum = 0.8f;

    private int mLastYIntercept = 0;

    public HomeScrollView(Context context) {
        this(context, null);
    }

    public HomeScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HomeScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

//        TypedArray ta=context.obtainStyledAttributes(attrs, R.styleable.HomeScrollView);
//        mTopHeight=ta.getDimensionPixelSize(R.styleable.MyScrollView_topheight,ScreenUtils.dp2px(getContext(),mTopHeight));
//        ta.recycle();

        mScreenHeight = ScreenUtils.getSreenHeight(getContext());
    }

//    @Subscribe(threadMode = ThreadMode.POSTING)
//    public void onColorChanged(ColorUtils colorUtils){
//        LogUtils.d("getColorEvent...........................................");
//        setCoverColor(colorUtils.getColor());
//    }

    //设置遮罩层颜色
    public void setCoverColor(int color) {
        mImageCoverView.setBackgroundColor(color);
        invalidateView();
    }

    private void invalidateView() {
        if (Looper.getMainLooper() == Looper.myLooper()) {//如果当前线程为UI线程的话，直接重绘
            invalidate();
        } else {//如果不是UI线程,则post到消息队列中，等待自动调用重绘
            postInvalidate();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        if (!once) {
            mWrapper = (LinearLayout) getChildAt(0);
            mTopLayout = (ViewGroup) mWrapper.getChildAt(0);
            mShowLayout = (ViewGroup) mWrapper.getChildAt(1);
            mImageCoverView = mTopLayout.getChildAt(1);
            myToolbar = mTopLayout.getChildAt(4);

            ViewGroup.LayoutParams topLp = mTopLayout.getLayoutParams();
            mTopHeight = topLp.height;
            ViewGroup.LayoutParams showLp = mShowLayout.getLayoutParams();
//            showLp.height = mScreenHeight;
            showLp.height = mScreenHeight - ScreenUtils.dp2px(getContext(), 20);
            mShowLayout.setLayoutParams(showLp);
            once = true;
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (changed) {
            this.scrollTo(0, 0);
        }
    }

    //供外部调用，用来设置偏移效果，只能取值0 - 1.0f,否则默认不偏移
    //取值越小，偏移幅度越大
    public void setSkew(float num) {
        if (num < 0 && num > 1.0f) {
            return;
        }
        this.skewNum = num;
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        float scale = t * 1.0f / (mTopHeight - ScreenUtils.dp2px(getContext(), 20));  //1 -- 0
        float translation = (mTopHeight - ScreenUtils.dp2px(getContext(), 20)) * scale;  //(mTopHeight-20dp) -- 0
        mTopLayout.setTranslationY(translation * (skewNum));
        myToolbar.setTranslationY(translation * (1 - skewNum));  //toolbar不能偏移
        mImageCoverView.setAlpha(scale);
        invalidateView();
    }

//    @Override
//    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        boolean intercepted = false;
////        int x = (int) ev.getX();
//        int y = (int) ev.getY();
//        switch (ev.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                intercepted = false;
//                break;
//            case MotionEvent.ACTION_MOVE:
//                int detalY = y - mLastYIntercept;
//                if (getScrollY() != mTopHeight-ScreenUtils.dp2px(getContext(),20)) {
//                    intercepted = true;
//                } else {
//                    intercepted = false;
//                }
//                break;
//            case MotionEvent.ACTION_UP:
//                intercepted = false;
//                break;
//            default:
//                break;
//        }
//        mLastYIntercept = y;
//        return intercepted;
//    }
}
