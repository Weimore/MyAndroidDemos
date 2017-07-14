package com.example.mymodeldemos.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.example.mymodeldemos.R;
import com.example.mymodeldemos.utils.ScreenUtils;

/**
 * Created by 吴城林 on 2017/7/8.
 * 为SecondActivity自定义的滚动View
 * 当前问题：和系统栏不兼容
 */

public class MyScrollView extends ScrollView {

    private LinearLayout mWrapper;
    private ViewGroup mTopLayout;
    private ViewGroup mShowLayout;
    private View mImageCoverView;

    private boolean once;

    private int mScreenHeight;
    private int mTopHeight=200;

    public MyScrollView(Context context) {
        this(context, null);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

//        TypedArray ta=context.obtainStyledAttributes(attrs, R.styleable.MyScrollView);
//        mTopHeight=ta.getDimensionPixelSize(R.styleable.MyScrollView_topheight,ScreenUtils.dp2px(getContext(),mTopHeight));
//        ta.recycle();

        mScreenHeight = ScreenUtils.getSreenHeight(getContext());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        if (!once) {
            mWrapper = (LinearLayout) getChildAt(0);
            mTopLayout = (ViewGroup) mWrapper.getChildAt(0);
            mShowLayout = (ViewGroup) mWrapper.getChildAt(1);
            mImageCoverView=mTopLayout.getChildAt(1);

            ViewGroup.LayoutParams topLp = mTopLayout.getLayoutParams();
            mTopHeight = topLp.height;
            ViewGroup.LayoutParams showLp = mShowLayout.getLayoutParams();
            showLp.height = mScreenHeight-ScreenUtils.dp2px(getContext(),20);
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


    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        float scale = t * 1.0f / mTopHeight;  //1-0
        mTopLayout.setTranslationY(mTopHeight * scale);
        mImageCoverView.setAlpha(scale);
    }
}
