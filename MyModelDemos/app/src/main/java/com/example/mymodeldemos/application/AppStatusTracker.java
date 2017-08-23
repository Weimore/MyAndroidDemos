package com.example.mymodeldemos.application;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import com.example.mymodeldemos.utils.LogUtils;

import java.util.WeakHashMap;

/**
 * Created by 吴城林 on 2017/8/23.
 */

public class AppStatusTracker implements Application.ActivityLifecycleCallbacks {
    //app当前的可能各种状态
    public static final int STATUS_FORCE_KILLED = -1;  //系统被强杀状态
    public static final int STATUS_OFF_LINE = 1;   //未登录或离线状态
    public static final int STATUS_ON_LINE = 2;    //已登录状态
    public static final int STATUS_KICK_OUT = 3;   //登录失效状态（token过时 或 被挤下线）

    private AppStatusTracker() {
    }

    private static class TrackerHolder{
        private static AppStatusTracker tracker = new AppStatusTracker();
    }

    //获得tracker实例
    public static AppStatusTracker getInstance(){
        return TrackerHolder.tracker;
    }

    public Configurator init(Context context){
        getAppConfig().put(ConfigType.APPLICATION_CONTEXT.name(),context);  //配置全局context
        getAppConfig().put(ConfigType.APP_STATUS.name(),STATUS_FORCE_KILLED);  //配置AppStatus
        registListener(context);  //设置监听
        return Configurator.getInstance();
    }

    //获得配置本体
    private final WeakHashMap<String,Object> getAppConfig(){
        return Configurator.getInstance().getAppConfig();
    }

    //设置监听
    private  void registListener(Context context){
        Application application = (Application) context;
        application.registerActivityLifecycleCallbacks(this);
    }


    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        LogUtils.d("onActivityCreated..........................." + activity.getClass().getName());
    }

    @Override
    public void onActivityStarted(Activity activity) {
        LogUtils.d("onActivityStarted..........................." + activity.getClass().getName());
    }

    @Override
    public void onActivityResumed(Activity activity) {
        LogUtils.d("onActivityResumed..........................." + activity.getClass().getName());
    }

    @Override
    public void onActivityPaused(Activity activity) {
        LogUtils.d("onActivityPaused..........................." + activity.getClass().getName());
    }

    @Override
    public void onActivityStopped(Activity activity) {
        LogUtils.d("onActivityStopped..........................." + activity.getClass().getName());
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        LogUtils.d("onActivitySaveInstanceState..........................." + activity.getClass().getName());
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        LogUtils.d("onActivityDestroyed..........................." + activity.getClass().getName());
    }
}
