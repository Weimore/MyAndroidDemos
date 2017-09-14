package com.example.mymodeldemos.application;

import android.app.Application;

import com.example.mymodeldemos.utils.LogUtils;

import java.util.List;

/**
 * Created by 吴城林 on 2017/8/20.
 */

public class MyApplication extends Application{
    public static MyApplication myApplication;
//    public static List<String> profile;

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtils.d("application create............................");
        myApplication=this;
        AppStatusTracker.getInstance().init(this).Configurate();
    }
}
