package com.example.mymodeldemos.widget;

import android.content.Context;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.mymodeldemos.R;
import com.example.mymodeldemos.base.MyBaseActivity;

/**
 * Created by 吴城林 on 2017/7/30.
 */

public class MyToolbar extends Toolbar {
    private final Context mContext;

    public MyToolbar(Context context) {
        this(context, null);
    }

    public MyToolbar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyToolbar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mContext=context;
        inflateMenu(R.menu.toolbar);
    }

    //对toolbar进行一些设置
    public void initToolbar(AppCompatActivity activity) {
        activity.setSupportActionBar(this);
        ActionBar actionBar=activity.getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayShowTitleEnabled(false);  //不显示label
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        //toolbar上item的点击事件
        setOnMenuItemClickListener(new OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
//                    case android.R.id.home:
//                        mDrawerLayout.openDrawer(GravityCompat.START);
//                        break;
                    case R.id.download_cover:
                        Toast.makeText(mContext, "下载封面暂未实装", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.switch_cover:
                        Toast.makeText(mContext, "切换封面暂未实装", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.custon_cover:
                        Toast.makeText(mContext, "自定义封面暂未实装", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.custom_tag:
                        Toast.makeText(mContext, "自定义标签暂未实装", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.custom_theme:
                        Toast.makeText(mContext, "自定义主题暂未实装", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }


}
