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
import androidx.navigation.Navigation;

import com.demo.jinx.finalproject.R;
import com.demo.jinx.finalproject.base.BaseFragment2;
import com.demo.jinx.finalproject.bean.User;
import com.google.android.material.snackbar.Snackbar;

public class RegisterFragment extends BaseFragment2 {
    private EditText editText;
    private EditText editText2;
    private EditText editText3;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_register,container,false);
        editText = root.findViewById(R.id.editText);
        editText2 = root.findViewById(R.id.editText2);
        editText3 = root.findViewById(R.id.editText3);
        Button button = root.findViewById(R.id.button);
        button.setOnClickListener(this::signUp);
        return root;
    }

    private void signUp(View view) {
        String name = editText.getText().toString();
        String password = editText2.getText().toString();
        String email = editText3.getText().toString();
        if(TextUtils.isEmpty(name)){
            editText.setError("账号不能为空");
            return;
        }
        if(TextUtils.isEmpty(password)){
            editText2.setError("密码不能为空");
            return;
        }
        if(TextUtils.isEmpty(email)){
            editText3.setError("邮箱不能为空");
            return;
        }
        User user = new User(name,password);
        user.setStudentTel(email);
        user.setStudentClass("21软5专升本");
        //TODO 网络请求注册
        Snackbar.make(view,"注册成功",Snackbar.LENGTH_LONG).show();
        Navigation.findNavController(view).navigateUp();
//        Snackbar.make(view,"注册失败",Snackbar.LENGTH_LONG).show();
    }
}
