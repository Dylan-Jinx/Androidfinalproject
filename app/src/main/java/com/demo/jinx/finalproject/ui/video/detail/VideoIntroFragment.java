package com.demo.jinx.finalproject.ui.video.detail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.demo.jinx.finalproject.R;

public class VideoIntroFragment extends Fragment {
    private String intro;

    public VideoIntroFragment(String intro) {
        this.intro = intro;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_video_intro,container,false);
        TextView textView = root.findViewById(R.id.textView);
        textView.setText(intro);
        return root;
    }
}
