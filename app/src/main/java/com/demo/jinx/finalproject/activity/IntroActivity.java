package com.demo.jinx.finalproject.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.demo.jinx.finalproject.R;
import com.github.appintro.AppIntro;
import com.github.appintro.AppIntroFragment;

public class IntroActivity extends AppIntro {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addSlide(AppIntroFragment.createInstance("第一日","启动页、引导页、闪屏页与基础框架搭建",R.drawable.p1));
        addSlide(AppIntroFragment.createInstance("第二日","LiveData+Retrofit网络请求与下拉刷新SmartRefreshLayout",R.drawable.p2));
        addSlide(AppIntroFragment.createInstance("第三日","RecyclerAdapter框架与轮播图banner",R.drawable.p3));
        addSlide(AppIntroFragment.createInstance("第四日","新闻详情页AgentWeb与Python详情页",R.drawable.p4));
        addSlide(AppIntroFragment.createInstance("第五日","爆炸菜单BoomMenu与统计图表MpAndroidChart",R.drawable.p5));
        addSlide(AppIntroFragment.createInstance("第六日","视频列表与视频播放器GSYVideoPlayer",R.drawable.p6));
        addSlide(AppIntroFragment.createInstance("第七日","我的界面与基于Bmob后段云的登录注册，找回密码",R.drawable.p7));
        addSlide(AppIntroFragment.createInstance("第八日","百度地图API",R.drawable.p8));
        setSkipText("跳过");
        setSkipText("完成");
        setImmersiveMode();
    }

    @Override
    protected void onSkipPressed(@Nullable Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        startActivity(new Intent(this,MainActivity.class));
        finish();
    }

    @Override
    protected void onDonePressed(@Nullable Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        startActivity(new Intent(this,MainActivity.class));
        finish();
    }
}