package com.demo.jinx.finalproject.ui.me.user;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.Navigation;

import com.demo.jinx.finalproject.R;
import com.demo.jinx.finalproject.base.BaseFragment2;
import com.demo.jinx.finalproject.bean.User;
import com.google.android.material.snackbar.Snackbar;

public class LoginFragment extends BaseFragment2 {
    private EditText editText;
    private EditText editText2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_login,container,false);
        editText = root.findViewById(R.id.editText);
        editText2 = root.findViewById(R.id.editText2);
        TextView textView = root.findViewById(R.id.textView);
        textView.setOnClickListener(v-> Navigation.findNavController(v)
                .navigate(R.id.action_loginFragment_to_registerFragment));
        TextView textView2 = root.findViewById(R.id.textView2);
        textView.setOnClickListener(v-> Navigation.findNavController(v)
                .navigate(R.id.action_loginFragment_to_findPasswordFragment));
        Button button = root.findViewById(R.id.button);
        button.setOnClickListener(this::login);
        return root;
    }

    private void login(View view) {
        String name = editText.getText().toString();
        String password = editText2.getText().toString();
        if(TextUtils.isEmpty(name)){
            editText.setError("账号不能为空");
            return;
        }
        if(TextUtils.isEmpty(password)){
            editText2.setError("密码不能为空");
            return;
        }
        final User user = new User(name,password);
        //TODO: 请求登录API
        if("2112114535".equals(name) && "123456".equals(password)){
            Snackbar.make(view,"登录成功："+user.getStudentName(),
                    Snackbar.LENGTH_LONG).show();
        } else {
            Snackbar.make(view,"登录失败：密码或用户名错误",
                    Snackbar.LENGTH_LONG).show();
        }
    }
}
