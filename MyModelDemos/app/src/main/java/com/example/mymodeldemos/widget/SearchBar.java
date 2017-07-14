package com.example.mymodeldemos.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.mymodeldemos.R;

/**
 * Created by 吴城林 on 2017/7/7.
 * 用于处理搜索图片框的一些点击事件
 */

public class SearchBar extends LinearLayout{

    public SearchBar(Context context) {
        this(context,null);
    }

    public SearchBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        View v=LayoutInflater.from(context).inflate(R.layout.search_bar,this);
        v.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"搜索图片暂未实装",Toast.LENGTH_SHORT).show();
            }
        });
        ImageView cameraImage= (ImageView) findViewById(R.id.search_camera);
        cameraImage.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"以图搜图暂未实装",Toast.LENGTH_SHORT).show();
            }
        });
    }

}
