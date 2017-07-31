package com.example.mymodeldemos;

import android.app.ActionBar;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mymodeldemos.adapter.TabTitleAdapter;
import com.example.mymodeldemos.base.MyBaseActivity;
import com.example.mymodeldemos.fragment.FirstFragment;
import com.example.mymodeldemos.model.RowClickedEvent;
import com.example.mymodeldemos.utils.ImageLoder;
import com.example.mymodeldemos.utils.ScreenUtils;
import com.example.mymodeldemos.widget.MyScrollView;
import com.example.mymodeldemos.widget.MyToolbar;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 吴城林 on 2017/7/8.
 */

public class SecondActivity extends MyBaseActivity {
    private DrawerLayout mDrawerLayout;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private TabTitleAdapter mAdapter;
    private ImageView mThemeImage;
    private MyToolbar mToolbar;



    private String[] mTitles;
    private FirstFragment firstFrag;
    private List<Fragment> mFragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
//            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
//        }
        EventBus.getDefault().register(this);
        initView();
        initData();
    }


    private void initData() {
        mTitles = getResources().getStringArray(R.array.tab_title);

        for (int i = 0; i < mTitles.length; i++) {
            firstFrag = new FirstFragment();
            mFragments.add(firstFrag);
        }
        mAdapter = new TabTitleAdapter(getSupportFragmentManager(), mFragments, mTitles);
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);


        //添加theme图片
        int screenWidth = ScreenUtils.getSreenWidth(this);
        int heightSize = ScreenUtils.dp2px(this, 230);
        Bitmap bitmap = ImageLoder.decodeSampleBitmapForResource(getResources(), R.drawable.themeimage, screenWidth, heightSize);
        mThemeImage.setImageBitmap(bitmap);
    }

    private void initView() {
        mDrawerLayout= (DrawerLayout) findViewById(R.id.drawlayout);
        mToolbar= (MyToolbar) findViewById(R.id.toolbar);
        mToolbar.initToolbar(this);
        ActionBarDrawerToggle mToogle = new ActionBarDrawerToggle(        //设定drawLayout的开关及动画
                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mToogle.syncState();
        mDrawerLayout.setDrawerListener(mToogle);           //让ActionBarDrawerToggle监听drawerLayout的开关状态

        mTabLayout = (TabLayout) findViewById(R.id.tab_layout);
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mThemeImage = (ImageView) findViewById(R.id.theme_image);

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetRowClickEvent(RowClickedEvent event){
        mDrawerLayout.closeDrawer(GravityCompat.START);  //点击左边选项后关闭滑动菜单
        Toast.makeText(this, "You clicked :" + event.rowViewEnum.name(), Toast.LENGTH_SHORT).show();
        switch (event.rowViewEnum){
            case PROFILE://个人中心

                break;
            case SEARCH_PICTURE://以图搜图

                break;
            case SUPPORT://续一秒

                break;
            case SETTING://设置

                break;
            case LIKE://给个好评

                break;
            case NIGHT://夜间模式

                break;
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    //加载toolbar布局为menu形式
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }


}
