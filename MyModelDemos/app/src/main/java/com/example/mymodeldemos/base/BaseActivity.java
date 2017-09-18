package com.example.mymodeldemos.base;

import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.mymodeldemos.R;
import com.example.mymodeldemos.SplashActivity;
import com.example.mymodeldemos.application.AppStatusTracker;
import com.example.mymodeldemos.application.ConfigType;
import com.example.mymodeldemos.application.Configurator;
import com.example.mymodeldemos.utils.LogUtils;

public abstract class BaseActivity extends AppCompatActivity {

    public static final int MODE_NONE = -1;
    public static final int MODE_BACK = 0;
    public static final int MODE_DRAWER = 1;
    public static final int MENU_NONE = -1;
    public static final int DRAWERLAYOUT_NULL = -1;

    protected DrawerLayout drawerLayout;
    protected Toolbar toolbar;
    private int menuResID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        switch ((int) Configurator.getAppConfig().get(ConfigType.APP_STATUS.name())) {
            case AppStatusTracker.STATUS_FORCE_KILLED:
                protectApp();
                break;
            case AppStatusTracker.STATUS_KICK_OUT:
                kickOut();
                break;
            case AppStatusTracker.STATUS_OFF_LINE:
            case AppStatusTracker.STATUS_ON_LINE:
                setUpContentView();
                setUpView();
                setUpData();
                break;
        }
    }

    //子类必须实现该方法，设置布局
    protected abstract void setUpContentView();

    protected void setUpView() {
    }

    protected void setUpData() {
    }

    //如果被挤下线
    private void kickOut() {
    }

    //如果app被强杀
    private void protectApp() {
        Intent intent = new Intent(this, SplashActivity.class);
        LogUtils.d("protectApp...............................");
        startActivity(intent);
        finish();
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        this.setContentView(layoutResID, "");
    }

    public void setContentView(@LayoutRes int layoutResID, CharSequence titleContent) {
        this.setContentView(layoutResID, titleContent, MODE_NONE);
    }

    public void setContentView(@LayoutRes int layoutResID, CharSequence titleContent, int mode) {
        this.setContentView(layoutResID, titleContent, mode, MENU_NONE);
    }

    public void setContentView(@LayoutRes int layoutResID, CharSequence titleContent, int mode, int menuResID) {
        this.setContentView(layoutResID, titleContent, mode, menuResID, DRAWERLAYOUT_NULL);
    }


    public void setContentView(@LayoutRes int layoutResID, CharSequence titleContent, int mode, int menuResID, int drawerLayoutResID) {
        super.setContentView(layoutResID);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorWhite));
        if (toolbar == null) {
            Toast.makeText(this, "toobar未获得", Toast.LENGTH_SHORT).show();
            return;  //如果不存在toobar，则返回
        }
        toolbar.setTitle(titleContent);  //设置标题
        setToolbarMode(mode, drawerLayoutResID, toolbar);  //设置toobar的模式

        //加载menu
        if (menuResID != MENU_NONE) {
//            toolbar_menu.getMenu().clear();
//            toolbar_menu.inflateMenu(menuResID);
            this.menuResID =menuResID;
        }
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(item.getItemId() ==android.R.id.home){
                    drawerLayout.openDrawer(GravityCompat.START);
                    return true;
                }
                return onToolBarMenuSelected(item);
            }
        });
    }

    //通过重写该方法，完成toobar中item的点击事件
    protected boolean onToolBarMenuSelected(MenuItem item) {
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(menuResID,menu);
        return super.onCreateOptionsMenu(menu);
    }

    //设置toobar的模式
    private void setToolbarMode(int mode, int drawerLayoutResID, Toolbar toolbar) {
        switch (mode) {
            case MODE_NONE:
                break;
            case MODE_BACK:
                //具有返回按钮的模式
                //设置back图标
                toolbar.setNavigationIcon(android.support.v7.appcompat.R.drawable.abc_ic_ab_back_material);
                //点击就结束自己，并出栈
                toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
                break;
            case MODE_DRAWER:
                //具有侧滑菜单的模式
                if(drawerLayoutResID ==DRAWERLAYOUT_NULL){
                    Toast.makeText(this, "未得到drawerLayoutResID", Toast.LENGTH_SHORT).show();
                    return;
                }
                drawerLayout = (DrawerLayout) findViewById(drawerLayoutResID);
                if (drawerLayout == null) {
                    Toast.makeText(this, "未得到drawerLayout,或者ID错误", Toast.LENGTH_SHORT).show();
                    return;
                }

                setSupportActionBar(toolbar);
                ActionBar actionBar = getSupportActionBar();
                if (actionBar != null) {
                    actionBar.setDisplayShowTitleEnabled(false);  //不显示title
                    actionBar.setDisplayHomeAsUpEnabled(true);
                }
                toolbar.showOverflowMenu();
                ActionBarDrawerToggle toogle = new ActionBarDrawerToggle(this, drawerLayout,toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
                toogle.syncState();
                drawerLayout.addDrawerListener(toogle);
                break;
            default:
                break;
        }
    }

}
