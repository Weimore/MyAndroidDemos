package com.example.mymodeldemos;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.graphics.Palette;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mymodeldemos.base.BaseActivity;
import com.example.mymodeldemos.event.RowClickedEvent;
import com.example.mymodeldemos.fragment.FuliFragment;
import com.example.mymodeldemos.fragment.IChangeTextColor;
import com.example.mymodeldemos.fragment.SampleListFragment;
import com.example.mymodeldemos.utils.ScreenUtils;
import com.example.mymodeldemos.utils.imageutils.ImageLoader;
import com.example.mymodeldemos.widget.SlidingMenu;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class NewHomeActivity extends BaseActivity implements AppBarLayout.OnOffsetChangedListener {
    private SlidingMenu mSlidingMenu;
    private AppBarLayout mAppBarLayout;
    private TabLayout mTabLayout;
    private TextView mTabMore;
    private ViewPager mViewPager;
    private RelativeLayout mTopLayout;
    private FragmentStatePagerAdapter mAdapter;
    private ImageView mThemeImage;
    private View mImageCoverView;

    private String[] mTitles;

    private List<IChangeTextColor> mFragments = new ArrayList<>();
    private int mTopLayoutHeight;

    @Override
    protected void setUpContentView() {
        setContentView(R.layout.activity_new_home, "", MODE_DRAWER, R.menu.toolbar_menu,R.id.drawer);
    }

    @Override
    protected void setUpView() {
        super.setUpView();

        mSlidingMenu = (SlidingMenu) findViewById(R.id.sliding_menu);
        mAppBarLayout = (AppBarLayout) findViewById(R.id.home_appbar_layout);
        mTabLayout = (TabLayout) findViewById(R.id.tab_layout);
        mTopLayout = (RelativeLayout) findViewById(R.id.theme_top_layout);
        mTabMore = (TextView) findViewById(R.id.tab_more);
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mThemeImage = (ImageView) findViewById(R.id.theme_image);
        mImageCoverView = findViewById(R.id.image_cover_view);

    }

    @Override
    protected void setUpData() {
        super.setUpData();
        mTitles = getResources().getStringArray(R.array.tab_title);
        initFragments();
        initAdapter();
        setTopLayout();
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);

        //添加theme图片
        changeThemeImage(R.drawable.themeimage);
        EventBus.getDefault().register(this);
    }

    private void setTopLayout() {
        mTopLayout.post(new Runnable() {
            @Override
            public void run() {
                mTopLayoutHeight = mTopLayout.getHeight() ;
            }
        });
    }


    private void initAdapter() {
        mAdapter = new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position).getFragment();
            }

            @Override
            public int getCount() {
                return mTitles.length;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return mTitles[position];
            }
        };
    }

    private void initFragments() {
//        SampleListFragment firstFrag;
//        for (int i = 0; i < mTitles.length; i++) {
//            firstFrag = new SampleListFragment();
//            mFragments.add(firstFrag);
//        }
        SampleListFragment firstFrag = new SampleListFragment();
        mFragments.add(firstFrag);
        FuliFragment secondFrag = new FuliFragment();
        mFragments.add(secondFrag);


    }



    @Subscribe(threadMode = ThreadMode.POSTING)
    public void onGetRowClickEvent(RowClickedEvent event) {
        drawerLayout.closeDrawer(GravityCompat.START);  //点击左边选项后关闭滑动菜单
        Toast.makeText(this, "You clicked :" + event.rowViewEnum.name(), Toast.LENGTH_SHORT).show();
        switch (event.rowViewEnum) {
            case PROFILE://个人中心
//                Intent intent = new Intent(this,SampleSectionListActivity.class);
//                startActivity(intent);
                break;
            case SEARCH_PICTURE://以图搜图
                showDialog();
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

    private void showDialog() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected boolean onToolBarMenuSelected(MenuItem item){
        switch (item.getItemId()) {
            case R.id.download_cover:
                Toast.makeText(this, "下载封面暂未实装", Toast.LENGTH_SHORT).show();
                break;
            case R.id.switch_cover:
                Toast.makeText(this, "切换封面暂未实装", Toast.LENGTH_SHORT).show();
                changeThemeImage(R.drawable.rella4);
                break;
            case R.id.custon_cover:
                Toast.makeText(this, "自定义封面暂未实装", Toast.LENGTH_SHORT).show();
                changeThemeImage(R.drawable.selfimage2);
                break;
            case R.id.custom_tag:
                Toast.makeText(this, "自定义标签暂未实装", Toast.LENGTH_SHORT).show();
                changeThemeImage(R.drawable.rella7);
                break;
            case R.id.custom_theme:
                Toast.makeText(this, "自定义主题暂未实装", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return true;
    }

    //切换封面
    private void changeThemeImage(int imageRes) {
        // TODO: 设置随机切换封面的url
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), imageRes);
//        mThemeImage.setImageBitmap(bitmap);
//        Glide.with(this).load(imageRes).asBitmap().into(mThemeImage);
        ImageLoader.getInstance().showImage(mThemeImage,imageRes);
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                int color;
                Palette.Swatch swatch = palette.getDominantSwatch();
                if (swatch == null) {
                    color = palette.getDominantColor(getResources().getColor(R.color.colorTheme));  //如果提取不到颜色样本，就使用自定义颜色
                } else {
                    color = swatch.getRgb();   //如何提取到颜色样本，就从颜色样本中获得颜色
                }
                int darkColor = colorBurn(color);  //对颜色进行处理，使颜色更深
                changeThemeColor(color, darkColor);  //切换主题颜色
            }
        });
    }

    //获得更深的颜色
    private int colorBurn(int color) {
        int alpha = color >> 24;
        int red = color >> 16 & 0xFF;
        int green = color >> 8 & 0xFF;
        int blue = color & 0xFF;
        red = (int) Math.floor(red * (1 - 0.1));
        green = (int) Math.floor(green * (1 - 0.1));
        blue = (int) Math.floor(blue * (1 - 0.1));
        return Color.rgb(red, green, blue);
    }

    private void changeThemeColor(int color, int darkColor) {
        // 获取color，然后再分别设置给每个部件
        mTabLayout.setBackgroundColor(color);
        mTabMore.setBackgroundColor(color);
        mImageCoverView.setBackgroundColor(darkColor);
        mSlidingMenu.changerColor(color);
        for (IChangeTextColor mFragment : mFragments){
            mFragment.setColor(color);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mAppBarLayout.addOnOffsetChangedListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mAppBarLayout.removeOnOffsetChangedListener(this);
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        int v = Math.abs(verticalOffset);
        float scale = v * 1.0f / mTopLayoutHeight;  //0 -- 1
        float alpha = v * 1.0f / (mTopLayoutHeight - ScreenUtils.dp2px(this,20) );
        float translation = mTopLayoutHeight * scale;
//        LogUtils.e("mTopHeight:"+mTopLayoutHeight+"..." + "scale:"+scale +"..."+"translation:"+translation);
        mTopLayout.setTranslationY(translation);
        mImageCoverView.setAlpha(alpha);
    }
}
