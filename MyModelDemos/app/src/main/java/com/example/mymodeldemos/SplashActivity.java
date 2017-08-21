package com.example.mymodeldemos;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.mymodeldemos.utils.LogUtils;

public class SplashActivity extends AppCompatActivity implements View.OnClickListener{

    private boolean threadfinish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Intent intent = new Intent(SplashActivity.this,SecondActivity.class);
        initSplash(intent,R.drawable.rella4,2000);
    }

    protected void initSplash(final Intent intent, int imageResId ,long delayMillis) {
        LogUtils.d("initSplash....................");
        if (intent == null) {
            return;
        }
        if (imageResId != 0) {
            getWindow().setBackgroundDrawableResource(imageResId);
        }
        if (delayMillis == 0L) {
            delayMillis = 3000;
        }
        Handler handle = new Handler();
        handle.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (threadfinish) {
                    return;
                }
                startActivity(intent);
                overridePendingTransition(android.support.v7.appcompat.R.anim.abc_fade_in, android.support.v7.appcompat.R.anim.abc_fade_out);
                finish();
            }
        }, delayMillis);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        threadfinish = true;
    }
}
