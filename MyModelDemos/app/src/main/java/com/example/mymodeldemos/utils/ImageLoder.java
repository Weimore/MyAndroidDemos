package com.example.mymodeldemos.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v4.os.EnvironmentCompat;
import android.support.v4.util.LruCache;
import android.util.Log;

import java.io.File;
import java.io.FileDescriptor;
import java.io.IOException;

import libcore.io.DiskLruCache;

/**
 * Created by wn123 on 2017/4/6.
 */

public class ImageLoder {

    private Context mContext;
    private LruCache<String, Bitmap> mMemoryCache;  //内存缓存
    private DiskLruCache mDiskLruCache;  //磁盘缓存

    private boolean mIsDiskLruCaheCreated = false;  //判断磁盘缓存是否已经创建

    private ImageLoder(Context context) {
        mContext = context.getApplicationContext(); //获取全局context?
        int maxMemorySize = (int) Runtime.getRuntime().maxMemory();
        int mCacheSize = maxMemorySize / 8;
        mMemoryCache = new LruCache<String, Bitmap>(mCacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getByteCount();
            }
        };

        try {

            File diskFile = getDiskCacheDir(context, "Bitmap");
            if (!diskFile.exists()) {
                diskFile.mkdir();//如果该文件不存在，则创建
            }
            //open方法创建一个磁盘缓存，第一个参数为缓存的路径，第二个参数为当前app的版本号
            // 第三个参数指定同一个key对应多少个缓存文件（一般都传1）,第4个参数指定磁盘缓存的总大小
            mDiskLruCache = DiskLruCache.open(diskFile, 1, 1, 1024 * 1024 * 10);
            mIsDiskLruCaheCreated = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //根据图片所需宽高和图片原宽高，对图片进行压缩处理
    public static Bitmap decodeSampleBitmapForResource(Resources res, int resId, int reqWidth, int reqHeight) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);  //获得采样率
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    public Bitmap decodeSampleBitmapFromFileDescriptor(FileDescriptor fd, int reqWidth, int reqHeight) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFileDescriptor(fd, null, options);
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFileDescriptor(fd, null, options);
    }

    //获取图片采样率
    private static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        if (reqWidth == 0 || reqHeight == 0) {
            return 1;
        }
        final int height = options.outHeight;  //图片原始宽高
        final int width = options.outWidth;

        Log.d("ImageLorder", "origin,w=" + width + " h=" + height);
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            int halfHeight = height / 2;
            int halfWidth = width / 2;
            while ((halfHeight / inSampleSize) >= reqHeight && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }
        Log.d("ImageLorder", "sampleSize=" + inSampleSize);
        return inSampleSize;
    }

    //将图片存入内存缓存的方法
    private void addBitmapToMemoryCache(String key, Bitmap bitmap) {
        if (getBitmapFromMemoryCache(key) == null) {//如果图片不在内存缓存中，则添加图片到缓存
            mMemoryCache.put(key, bitmap);
        }
    }

    //将图片从内存缓存中取出的方法
    private Bitmap getBitmapFromMemoryCache(String key) {
        return mMemoryCache.get(key);
    }

    //根据指定的key值从内存缓存中移除图片（一般用不到）
    private void removeBitmapFromMemoryCache(String key) {
        if (getBitmapFromMemoryCache(key) != null) {
            mMemoryCache.remove(key);
        }
    }

    //获得sd卡路径的方法
    private File getDiskCacheDir(Context context, String uniqueName) {
        String cachePath;
        if (EnvironmentCompat.MEDIA_UNKNOWN.equals(Environment.getExternalStorageState()) || Environment.isExternalStorageRemovable()) {//如果存在sd卡
            cachePath = context.getExternalCacheDir().getPath();
        } else {//如果未挂载sd卡
            cachePath = context.getCacheDir().getPath();
        }
        return new File(cachePath + File.separator + uniqueName);  //返回以sdka路径加上自定义名字为文件名的文件
    }

    //获得url的key值，因为图片的url中可能有特殊字符，会影响url在安卓中的使用，所以一般采取url的md5值作为key
    private String hanshKeyFromUrl(String url){
        String cacheKey;


        return null;
    }

    private Bitmap loadBitmapFromHttp(){
        return null;
    }


}
