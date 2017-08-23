package com.example.mymodeldemos;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mymodeldemos.adapter.TabTitleAdapter;
import com.example.mymodeldemos.base.BaseActivity;
import com.example.mymodeldemos.fragment.FirstFragment;
import com.example.mymodeldemos.event.RowClickedEvent;
import com.example.mymodeldemos.utils.MyImageLoder;
import com.example.mymodeldemos.utils.ScreenUtils;
import com.example.mymodeldemos.widget.MyToolbar;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class NotUsedHomeActivity extends BaseActivity {

    private DrawerLayout mDrawerLayout;

    private AppBarLayout appBarLayout;
    private int appBarLayoutHeight;
    private View imageCoverView;

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
        setContentView(R.layout.activity_home_not_used);
        initView();
        initData();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void setUpContentView() {

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
        int heightSize = ScreenUtils.dp2px(this, 200);
        Bitmap bitmap = MyImageLoder.decodeSampleBitmapForResource(getResources(), R.drawable.themeimage, screenWidth, heightSize);
        mThemeImage.setImageBitmap(bitmap);
    }

    private void initView() {
        mDrawerLayout= (DrawerLayout) findViewById(R.id.drawlayout);  //滑动框架drawer
        mToolbar= (MyToolbar) findViewById(R.id.toolbar);          //我的toolbar
        mToolbar.initToolbar(this);       //toolbar进行一系列设置
        ActionBarDrawerToggle mToogle = new ActionBarDrawerToggle(        //设定drawLayout的开关及动画
                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mToogle.syncState();
        mDrawerLayout.setDrawerListener(mToogle);           //让ActionBarDrawerToggle监听drawerLayout的开关状态

        mTabLayout = (TabLayout) findViewById(R.id.tab_layout);      //标签布局tablayout
        mViewPager = (ViewPager) findViewById(R.id.view_pager);      // viewPager
        mThemeImage = (ImageView) findViewById(R.id.theme_image);    //顶部的主题图片

        imageCoverView=findViewById(R.id.image_cover_view);        //主题图片上的遮罩层
        appBarLayout= (AppBarLayout) findViewById(R.id.appbar_layout);     //
        initImageCoverAlpha();
    }

    //该方法用于根据AppBarLayout的滑动来设置遮罩层的透明度
    private void initImageCoverAlpha() {
        imageCoverView.setAlpha(0);
        //得到AppBarLayout的高度
        int heightSizeMode= View.MeasureSpec.makeMeasureSpec((1<<30)-1, View.MeasureSpec.AT_MOST);
        appBarLayout.measure(0, heightSizeMode);
        appBarLayoutHeight=appBarLayout.getMeasuredHeight();

        //aAppBarLayout的滚动监听，对应设置遮罩透明度
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                float scale=Math.abs(verticalOffset)*1.0f/appBarLayoutHeight;  //0-1
                imageCoverView.setAlpha(scale);
            }
        });
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


    //加载toolbar布局为menu形式
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
