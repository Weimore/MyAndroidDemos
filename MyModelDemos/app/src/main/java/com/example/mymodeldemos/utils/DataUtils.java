package com.example.mymodeldemos.utils;

import com.example.mymodeldemos.model.Android;
import com.example.mymodeldemos.model.Fuli;
import com.example.mymodeldemos.model.IOS;
import com.google.gson.Gson;

import java.util.List;

/**
 * Created by 吴城林 on 2017/9/13.
 */

public class DataUtils {

    public final static String FULI_URL = "http://gank.io/api/data/福利/10/";
    public final static String ANDROID_URL = "http://gank.io/api/data/Android/10/";
    public final static String IOS_URL = "http://gank.io/api/data/iOS/10/";
    public final static String REST_URL = "http://gank.io/api/data/休息视频/10/";
    //每日数据
    public final static String DAY_URL = "http://gank.io/api/day/2015/08/06";

    public static List<Fuli.ResultsBean> getFuliResult(String url){
        Gson gson = new Gson();
        Fuli fuli = gson.fromJson(url,Fuli.class);
        List<Fuli.ResultsBean> resultsList = fuli.getResults();
        return resultsList;
    }

    public static List<Android.ResultsBean> getAndroidResult(String url){
        Gson gson = new Gson();
        Android android = gson.fromJson(url,Android.class);
        List<Android.ResultsBean> resultsList = android.getResults();
        return resultsList;
    }

    public static List<IOS.ResultsBean> getIOSResult(String url){
        Gson gson = new Gson();
        IOS ios = gson.fromJson(url,IOS.class);
        List<IOS.ResultsBean> resultsList = ios.getResults();
        return resultsList;
    }

//    public static List<?> getResult(String url,Class tClass,Class<K> kClass){
//        Gson gson = new Gson();
//        gson.fromJson(url,tClass)
//
//    }
}
