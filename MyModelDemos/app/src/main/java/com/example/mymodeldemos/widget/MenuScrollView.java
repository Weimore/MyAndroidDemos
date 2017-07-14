package com.example.mymodeldemos.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Scroller;

/**
 * Created by 吴城林 on 2017/7/12.
 */

public class MenuScrollView extends LinearLayout {

    private Scroller mScroller;
    private Context mContext;
    private GestureDetector mGestureDetector;
    private boolean intercept = false;
    private int mLastX;
    private int mLastY;

    public MenuScrollView(Context context) {
        this(context, null);
    }

    public MenuScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MenuScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        setClickable(true);
        setLongClickable(false);
//        mScroller = new Scroller(mContext);
//        mGestureDetector = new GestureDetector(mContext, new MyGestureListener());
    }

//    class MyGestureListener implements GestureDetector.OnGestureListener {
//        private int x;
//        private int y;
//
//        @Override
//        public boolean onDown(MotionEvent e) {
//            return false;
//        }
//
//        @Override
//        public void onShowPress(MotionEvent e) {
//
//        }
//
//        @Override
//        public boolean onSingleTapUp(MotionEvent e) {
//            return false;
//        }
//
//        @Override
//        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
//            requestDisallowInterceptTouchEvent(true);
//            Log.d("aaa", "onInterceptTouchEvent.....");
////            if(intercept){
////                int disY = (int) ((distanceY - 0.5) / 2);
////                beginScroll(0, disY);
////            }
//            return true;
//        }
//
//        public void onLongPress(MotionEvent e) {
//        }
//
//        @Override
//        public boolean onFling(MotionEvent e1, MotionEvent e2, float x, float y) {
//            return false;
//        }
//
//    }
//
//
//
//    @Override
//    public boolean onInterceptTouchEvent(MotionEvent ev) {
//
//        int x = (int) ev.getX();
//        int y = (int) ev.getY();
//        switch (ev.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                intercept = false;
//                break;
//            case MotionEvent.ACTION_MOVE:
//                int dx = Math.abs(x - mLastX);
//                int dy =Math.abs( y - mLastY);
//                if (dx > 20 || dy > 20) {
//                    intercept = true;
////                    Log.d("aaa", "onInterceptTouchEvent.....");
//                }
//                break;
//            default:
//                intercept = false;
//                break;
//        }
//        mLastX = x;
//        mLastY = y;
//
//        return intercept;
//    }
//
//
//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_UP:
//                reset(0, 0);
//                requestDisallowInterceptTouchEvent(false);
//                break;
//            default:
////                Log.d("aaa", "eventGet.........");
//                return mGestureDetector.onTouchEvent(event);
//        }
//        return super.onTouchEvent(event);
//    }
//
//    private void reset(int x, int y) {
//        int dx = x - mScroller.getFinalX();
//        int dy = y - mScroller.getFinalY();
//        beginScroll(dx, dy);
//    }
//
//    private void beginScroll(int dx, int dy) {
//        mScroller.startScroll(mScroller.getFinalX(), mScroller.getFinalY(), dx, dy);
//        invalidate();
//    }
//
//    @Override
//    public void computeScroll() {
//        if (mScroller.computeScrollOffset()) {
//            this.scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
//            postInvalidate();
//        }
//    }
}
