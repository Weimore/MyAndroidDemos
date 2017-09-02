package com.example.mymodeldemos;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.graphics.Palette;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mymodeldemos.adapter.TabTitleAdapter;
import com.example.mymodeldemos.base.BaseActivity;
import com.example.mymodeldemos.fragment.SampleListFragment;
import com.example.mymodeldemos.event.RowClickedEvent;
import com.example.mymodeldemos.utils.MyImageLoder;
import com.example.mymodeldemos.utils.ScreenUtils;
import com.example.mymodeldemos.widget.HomeScrollView;
import com.example.mymodeldemos.widget.SlidingMenu;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 吴城林 on 2017/7/8.
 */

public class HomeActivity extends BaseActivity {
    private SlidingMenu mSlidingMenu;
    private HomeScrollView mHomeScrollView;
    private TabLayout mTabLayout;
    private TextView mTabMore;
    private ViewPager mViewPager;
    private TabTitleAdapter mAdapter;
    private ImageView mThemeImage;

    private String[] mTitles;
    private SampleListFragment firstFrag;
    private List<Fragment> mFragments = new ArrayList<>();

    @Override
    protected void setUpContentView() {
        setContentView(R.layout.activity_home, "", MODE_DRAWER, R.menu.toolbar,R.id.drawer);
    }

    @Override
    protected void setUpData() {
        super.setUpData();
        mTitles = getResources().getStringArray(R.array.tab_title);

        for (int i = 0; i < mTitles.length; i++) {
            firstFrag = new SampleListFragment();
            mFragments.add(firstFrag);
        }
        mAdapter = new TabTitleAdapter(getSupportFragmentManager(), mFragments, mTitles);
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);


        //添加theme图片
        int screenWidth = ScreenUtils.getSreenWidth(this);
        int heightSize = ScreenUtils.dp2px(this, 230);
        Bitmap bitmap = MyImageLoder.decodeSampleBitmapForResource(getResources(), R.drawable.themeimage, screenWidth, heightSize);
        mThemeImage.setImageBitmap(bitmap);

        EventBus.getDefault().register(this);
    }

    @Override
    protected void setUpView() {
        super.setUpView();

        mSlidingMenu = (SlidingMenu) findViewById(R.id.sliding_menu);
        mHomeScrollView = (HomeScrollView) findViewById(R.id.home_scrollview);
        mTabLayout = (TabLayout) findViewById(R.id.tab_layout);
        mTabMore = (TextView) findViewById(R.id.tab_more);
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mThemeImage = (ImageView) findViewById(R.id.theme_image);

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
        Glide.with(this).load(imageRes).asBitmap().into(mThemeImage);
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
        mHomeScrollView.setCoverColor(darkColor);
        mSlidingMenu.changerColor(color);

    }
}
