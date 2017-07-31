package com.example.mymodeldemos;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class SplashActivity extends AppCompatActivity {

    private ImageView mImageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        initView();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this,SecondActivity.class));
                finish();
            }
        },2000);
    }

    private void initView() {
        mImageView= (ImageView) findViewById(R.id.splash_image);
        mImageView.setBackgroundResource(R.drawable.rella4);
    }
}
