package com.example.mymodeldemos.utils.imageutils;

import android.content.Context;
import android.view.View;

/**
 * Created by 吴城林 on 2017/8/21.
 */
//对各种处理图片的框架进行再封装
public class ImageLoader{
    private static ImageLoader imageLoader = new ImageLoader();
    private ImageLoaderStrategy strategy;

    //获得ImageLoader实例
    public static ImageLoader getInstance() {
        return imageLoader;
    }

    //默认使用Glide框架
    private ImageLoader() {
        strategy = new GlideImageLoaderStrategy();
    }

    //设置采用哪种框架
    public void setStrategy(ImageLoaderStrategy strategy) {
        this.strategy = strategy;
    }

    public void showImage(View v, String url){
        strategy.showImage(v,url,null);
    }
    public void showImage(View v, String url, ImageLoaderOptions options){
        strategy.showImage(v,url,options);
    }
    public void showImage(View v,int drawable){
        strategy.showImage(v,drawable,null);
    }
    public void showImage(View v,int drawable,ImageLoaderOptions options){
        strategy.showImage(v,drawable,options);
    }
}
