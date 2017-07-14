package com.example.mymodeldemos.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.WindowManager;

/**
 * Created by 吴城林 on 2017/7/7.
 */

public class ScreenUtils {

    private static int mSreenWidth;
    private static int mSreenHeight;

    public static int getSreenWidth(Context context){
        //获取屏幕宽度
        WindowManager wm = (WindowManager) context.getSystemService(context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        mSreenWidth = outMetrics.widthPixels;
        return mSreenWidth;
    }

    public static int getSreenHeight(Context context){
        //获取屏幕宽度
        WindowManager wm = (WindowManager) context.getSystemService(context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        mSreenHeight = outMetrics.heightPixels;
        return mSreenHeight;
    }

    //把dp转换成px
    public static int dp2px(Context context,int dp){
        int i= (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dp,context.getResources().getDisplayMetrics());
        return i;
    }

}
