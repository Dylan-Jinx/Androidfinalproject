package com.demo.jinx.finalproject.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Toast;

import com.demo.jinx.finalproject.R;
import com.demo.jinx.finalproject.base.BaseFragment2;
import com.demo.jinx.finalproject.base.OnFragmentKeyDownListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private NavController navController;
    private List<OnFragmentKeyDownListener> onFragmentKeyDownListenerList = new ArrayList<>();
    private long exitTime;

    public void setOnFragmentKeyDownListener(OnFragmentKeyDownListener onFragmentKeyDownListenerList) {
        this.onFragmentKeyDownListenerList.add(onFragmentKeyDownListenerList);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home,R.id.navigation_chart,R.id.navigation_video,
                R.id.navigation_me
        ).build();
        navController = Navigation.findNavController(this,R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this,navController,appBarConfiguration);
        NavigationUI.setupWithNavController(navView,navController);
        NavigationUI.navigateUp(navController, appBarConfiguration);
    }

    @Override
    public boolean onSupportNavigateUp() {
        if(onFragmentKeyDownListenerList.size() != 0){
            onFragmentKeyDownListenerList.remove(onFragmentKeyDownListenerList.size()-1);
        }
        return NavigationUI.navigateUp(navController,appBarConfiguration);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_BACK){
            if(onFragmentKeyDownListenerList.size() != 0){
                if(onFragmentKeyDownListenerList.get(onFragmentKeyDownListenerList.size()-1)
                        .onKeyDown(keyCode, event)){
                    return true;
                }else{
                    onSupportNavigateUp();
                }
            }else if(System.currentTimeMillis() - exitTime > 2000){
                Toast.makeText(this, "再次按返回键退出", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                startActivity(intent);
//                finish(); 真实退出
            }
            return true;
        }
        return super.onKeyDown(keyCode,event);
    }
}