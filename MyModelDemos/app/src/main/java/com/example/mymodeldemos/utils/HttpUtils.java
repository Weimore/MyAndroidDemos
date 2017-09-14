package com.example.mymodeldemos.utils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 吴城林 on 2017/9/13.
 */

public class HttpUtils {

    //通过
    public static void get(String url,Callback callback){
        OkHttpClient client = new OkHttpClient();
        Request request =new Request.Builder().get().url(url).build();
        client.newCall(request).enqueue(callback);
    }

}
