package com.example.mymodeldemos.utils.imageutils;

import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;

import java.io.File;
import java.io.IOException;

/**
 * Created by 吴城林 on 2017/9/14.
 */

public class WidgetUtils {

    public static Uri imageUri;  //存储拍照后得到的照片
    public static Uri imageUri2;  //存储拍照后照片裁剪得到的图片
    public static Uri imageUri3;  //存储从相册中获取的图片裁剪后得到的图片

    public static final int TAKE_PHOTO = 1;
    public static final int CROP_AFTRE_PHOTO = 2;
    public static final int VIEW_GALLERY = 3;
    public static final int CROP_AFTRE_GALLERY = 4;

    public static Intent getPictureFromPhoto(Context context) {
        //拍照后存储的图片
        File outputImage = new File(Environment.getExternalStorageDirectory(), "output_image.jpg");
        try {
            if (outputImage.exists()) {
                outputImage.delete();
            }
            outputImage.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //拍照后裁剪，存储的图片
        File outputImage2 = new File(context.getExternalCacheDir(), "output_image2.jpg");
        try {
            if (outputImage2.exists()) {
                outputImage2.delete();
            }
            outputImage2.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (Build.VERSION.SDK_INT >= 24) {
            imageUri = FileProvider.getUriForFile(context, "com.example.mymodeldemos.fileprovider", outputImage);
            imageUri2 = FileProvider.getUriForFile(context, "com.example.mymodeldemos.fileprovider", outputImage2);
        } else {
            imageUri = Uri.fromFile(outputImage);
            imageUri2 = Uri.fromFile(outputImage2);
        }
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        return intent;
    }

    public static Intent cropAfterTakePhoto() {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(imageUri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("scale", true);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri2);
        intent.putExtra("return-data", false);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
//        intent.putExtra("noFaceDetection", true); //是否去除面部检测， 如果你需要特定的比例去裁剪图片，那么这个一定要去掉，因为它会破坏掉特定的比例。
        return intent;
    }

    public static Intent cropAfterViewGallery(Context context, Intent data) {
        Uri srcUri = handleImage(context, data);
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(srcUri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("scale", true);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri3); //这里的imageUri3是先前定义好用来存裁剪后相片的
        intent.putExtra("return-data", false);
        return intent;
    }

    public static Intent getPictureFromGallery(Context context) {
        //相册选取后裁剪，存储的图片
        File outputImage3 = new File(context.getExternalCacheDir(), "output_image3.jpg");
        try {
            if (outputImage3.exists()) {
                outputImage3.delete();
            }
            outputImage3.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (Build.VERSION.SDK_INT >= 24) {
            imageUri3 = FileProvider.getUriForFile(context, "com.example.mymodeldemos.fileprovider", outputImage3);
        } else {
            imageUri3 = Uri.fromFile(outputImage3);
        }
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        return intent;
    }


    private static Uri handleImage(Context context, Intent data) { //解析Uri
        String imagePath = null;
        Uri uri = data.getData();
        if (DocumentsContract.isDocumentUri(context, uri)) {
            String docId = DocumentsContract.getDocumentId(uri);
            if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
                String id = docId.split(":")[1];
                String selecion = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(context, MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selecion);
            } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"),
                        Long.valueOf(docId));
                imagePath = getImagePath(context, contentUri, null);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            imagePath = getImagePath(context, uri, null);
        } else {                                //解析Gallery中图片的Uri
            imagePath = getImagePath(context, uri, null);
        }
        return Uri.fromFile(new File(imagePath)); //返回getImagePath方法得到的真实路径所转化的Uri
//        displayImage(imagePath);
    }

    private static String getImagePath(Context context, Uri uri, String selection) { //得到真实路径
        String path = null;
        Cursor cursor = context.getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToNext()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }
}
