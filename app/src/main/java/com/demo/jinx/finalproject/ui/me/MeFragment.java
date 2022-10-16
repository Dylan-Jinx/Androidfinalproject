package com.demo.jinx.finalproject.ui.me;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.demo.jinx.finalproject.R;
import com.demo.jinx.finalproject.bean.User;

import de.hdodenhof.circleimageview.CircleImageView;

public class MeFragment extends Fragment {

    private boolean isLogin;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_me,container,false);
        CircleImageView circleImageView = root.findViewById(R.id.circleImageView);
        circleImageView.setOnClickListener(this::click);
        TextView textView = root.findViewById(R.id.textView);
        textView.setOnClickListener(this::click);
        LinearLayout linearLayoutMap = root.findViewById(R.id.linearLayout_map);
        linearLayoutMap.setOnClickListener(v->Navigation.findNavController(v).navigate(R.id.action_navigation_me_to_mapFragment));

        //TODO: check loginStatus
//        if(BombUser.isLogin()){
//            User user  = BombUser.getCurrentUser(User.class);
//            textView.setText(user.getUsername());
//            isLogin = true;
//        }else{
//            textView.setText("点击登录");
//            isLogin = false;
//        }
        textView.setText("点击登录");
        isLogin = true;
        return root;
    }

    private void click(View view) {
        if(isLogin){
            Navigation.findNavController(view).navigate(R.id.action_meFragment_to_infoFragment);
        }else{
            Navigation.findNavController(view).navigate(R.id.action_meFragment_to_loginFragment);
        }
    }
}