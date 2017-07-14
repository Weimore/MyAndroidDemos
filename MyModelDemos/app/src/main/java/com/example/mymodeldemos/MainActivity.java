package com.example.mymodeldemos;


import android.app.ActionBar;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.mymodeldemos.adapter.TabTitleAdapter;
import com.example.mymodeldemos.base.MyBaseActivity;
import com.example.mymodeldemos.fragment.FirstFragment;
import com.example.mymodeldemos.utils.ImageLoder;
import com.example.mymodeldemos.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends MyBaseActivity {

    private DrawerLayout mDrawerLayout;

    private AppBarLayout appBarLayout;
    private int appBarLayoutHeight;
    private View imageCoverView;

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private TabTitleAdapter mAdapter;
    private ImageView mThemeImage;
    private Toolbar mToolbar;

    private String[] mTitles;
    private FirstFragment firstFrag;
    private List<Fragment> mFragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData() {
        mTitles = getResources().getStringArray(R.array.tab_title);
//        mTitles=new String[]{"美图","趣事","新闻","条漫","绘画"};
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
        Bitmap bitmap = ImageLoder.decodeSampleBitmapForResource(getResources(), R.drawable.themeimage, screenWidth, heightSize);
        mThemeImage.setImageBitmap(bitmap);
    }

    private void initView() {
        mDrawerLayout= (DrawerLayout) findViewById(R.id.drawlayout);
        mTabLayout = (TabLayout) findViewById(R.id.tab_layout);
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mThemeImage = (ImageView) findViewById(R.id.theme_image);
        mToolbar= (Toolbar) findViewById(R.id.toolbar);
        setActionBar(mToolbar);
        initToolbar();

        imageCoverView=findViewById(R.id.image_cover_view);  //遮罩层
        appBarLayout= (AppBarLayout) findViewById(R.id.appbar_layout);
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
//                Log.d("aaa","scale"+scale+"offset"+verticalOffset);
            }
        });
    }

    //对toolbar进行一些设置
    private void initToolbar() {
        ActionBar actionBar=getActionBar();
        if(actionBar!=null){
            actionBar.setDisplayShowTitleEnabled(false);  //不显示label
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.homelogo);
        }
    }


    //加载toolbar布局为menu形式
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.download_cover:
                Toast.makeText(this, "下载封面暂未实装", Toast.LENGTH_SHORT).show();
                break;
            case R.id.switch_cover:
                Toast.makeText(this, "切换封面暂未实装", Toast.LENGTH_SHORT).show();
                break;
            case R.id.custon_cover:
                Toast.makeText(this, "自定义封面暂未实装", Toast.LENGTH_SHORT).show();
                break;
            case R.id.custom_tag:
                Toast.makeText(this, "自定义标签暂未实装", Toast.LENGTH_SHORT).show();
                break;
            case R.id.custom_theme:
                Toast.makeText(this, "自定义主题暂未实装", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return true;
    }


}
