package com.example.mymodeldemos.utils.imageutils;

import android.view.ViewPropertyAnimator;

import com.bumptech.glide.request.animation.ViewPropertyAnimation;

/**
 * Created by 吴城林 on 2017/8/21.
 */

public class ImageLoaderOptions {
    private int placeHolder = -1;  //占位符，当未成功加载时显示的图片
    private ImageReSize size = null;  //重新设置图片的宽高时使用
    private int errorDrawable = -1;  //当加载错误时显示的drawable
    private boolean isCrossFade = false;  //是否渐变平滑的显示drawable
    private boolean isSkipMemoryCache = false;  //是否跳过内存缓存
    private ViewPropertyAnimation.Animator animator = null;  //图片加载动画

    public ImageLoaderOptions(int placeHolder, ImageReSize size, int errorDrawable, boolean isCrossFade, boolean isSkipMemoryCache, ViewPropertyAnimation.Animator animator) {
        this.placeHolder = placeHolder;
        this.size = size;
        this.errorDrawable = errorDrawable;
        this.isCrossFade = isCrossFade;
        this.isSkipMemoryCache = isSkipMemoryCache;
        this.animator = animator;
    }

    public class ImageReSize{
        int reWidth = 0;
        int reHeight = 0;

        public ImageReSize(int reWidth, int reHeight) {
            this.reWidth = reWidth;
            this.reHeight = reHeight;
        }
    }

    public int getPlaceHolder() {
        return placeHolder;
    }

    public ImageReSize getSize() {
        return size;
    }

    public int getErrorDrawable() {
        return errorDrawable;
    }

    public boolean isCrossFade() {
        return isCrossFade;
    }

    public boolean isSkipMemoryCache() {
        return isSkipMemoryCache;
    }

    public ViewPropertyAnimation.Animator getAnimator() {
        return animator;
    }
}
