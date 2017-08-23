package com.example.mymodeldemos.utils.imageutils;

import android.content.Context;
import android.view.View;

/**
 * Created by 吴城林 on 2017/8/21.
 */

public interface ImageLoaderStrategy {
    void showImage( View v, String url, ImageLoaderOptions options);
    void showImage(View v,int drawable,ImageLoaderOptions options);
}
