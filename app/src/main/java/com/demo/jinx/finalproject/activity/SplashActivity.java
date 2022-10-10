package com.demo.jinx.finalproject.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.demo.jinx.finalproject.R;

public class SplashActivity extends AppCompatActivity {

    private boolean isClick;
    private boolean isBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ImageView imageView = findViewById(R.id.imageView);
        Glide.with(this).load("http://5b0988e595225.cdn.sohucs.com/images/20190831/05de49d16e374e9e997bc97fdf29b0cc.gif")
                .into(imageView);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(!isClick && isBack){
                    startActivity(new Intent(SplashActivity.this,MainActivity.class));
                    finish();
                }
            }
        }, 5000);
    }

    public void skip(View view){
        isClick = true;
        startActivity(new Intent(SplashActivity.this,MainActivity.class));
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        isBack = true;
    }
}