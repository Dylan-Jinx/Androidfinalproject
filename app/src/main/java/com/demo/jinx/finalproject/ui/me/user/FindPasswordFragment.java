package com.demo.jinx.finalproject.ui.me.user;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.demo.jinx.finalproject.R;
import com.demo.jinx.finalproject.base.BaseFragment2;
import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

public class FindPasswordFragment extends BaseFragment2 {
    private EditText editText;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_find_password,container,false);
        editText = root.findViewById(R.id.editText);
        Button button = root.findViewById(R.id.button);
        button.setOnClickListener(this::resetPasswordByEmail);
        return root;
    }

    private void resetPasswordByEmail(View view) {
        String email = editText.getText().toString();
        if(TextUtils.isEmpty(email)){
            editText.setError("邮箱不能为空");
            return;
        }
        //TODO 重置密码API
        Snackbar.make(view, "重置密码请求收到",Snackbar.LENGTH_LONG).show();
    }
}
