package com.example.mymodeldemos.utils.imageutils;

import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;

/**
 * Created by 吴城林 on 2017/8/21.
 */

public class GlideImageLoaderStrategy implements IImageLoaderStrategy {
    @Override
    public void showImage(View v, String url, ImageLoaderOptions options) {
        if(v instanceof ImageView){
            ImageView imageView = (ImageView) v;
            //装配基本的参数
            DrawableTypeRequest request = Glide.with(imageView.getContext()).load(url);
            //装配各种参数
            loadOptions(request,options).asBitmap().into(imageView);
        }
    }

    @Override
    public void showImage( View v, int drawable, ImageLoaderOptions options) {
        if(v instanceof ImageView){
            ImageView imageView = (ImageView)v;
            DrawableTypeRequest request = Glide.with(imageView.getContext()).load(drawable);
            loadOptions(request,options).asBitmap().into(imageView);
        }
    }

    //装配各种参数
    private DrawableTypeRequest loadOptions(DrawableTypeRequest request,ImageLoaderOptions options) {
        if(options ==null){
            return request;
        }
        if(options.getPlaceHolder()!=-1){
            request.placeholder(options.getPlaceHolder());
        }
        if(options.getSize()!=null){
            request.override(options.getSize().reWidth,options.getSize().reHeight);
        }
        if(options.getErrorDrawable()!=-1){
            request.error(options.getErrorDrawable());
        }
        if(options.isCrossFade()){
            request.crossFade();
        }
        if(options.isSkipMemoryCache()){
            request.skipMemoryCache(options.isSkipMemoryCache());
        }
        if(options.getAnimator()!=null){
            request.animate(options.getAnimator());
        }
        return request;
    }
}
