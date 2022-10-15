package com.demo.jinx.finalproject.ui.me.user;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.demo.jinx.finalproject.R;
import com.demo.jinx.finalproject.base.BaseFragment2;
import com.google.android.material.snackbar.Snackbar;

public class InfoFragment extends BaseFragment2 {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_info,container,false);
        TextView textView = root.findViewById(R.id.textView);//用户名
        TextView textView2 = root.findViewById(R.id.textView2);//昵称
        //TODO:登录过后获取用户信息操作

        Button button = root.findViewById(R.id.button);
        button.setOnClickListener(this::logOut);
        return root;
    }

    private void logOut(View view) {
        //TODO：退出登录API操作
        Snackbar.make(view,"退出登录成功",Snackbar.LENGTH_LONG).show();
    }
}
