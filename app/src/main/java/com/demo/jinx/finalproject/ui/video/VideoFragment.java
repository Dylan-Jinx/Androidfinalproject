package com.demo.jinx.finalproject.ui.video;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.demo.jinx.finalproject.R;
import com.demo.jinx.finalproject.bean.VideoBean;
import com.scwang.smart.refresh.layout.api.RefreshLayout;

public class VideoFragment extends Fragment {

    private VideoViewModel videoViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        videoViewModel = new ViewModelProvider(this).get(VideoViewModel.class);
        View root = inflater.inflate(R.layout.fragment_video, container, false);
        RefreshLayout refreshLayout = root.findViewById(R.id.refreshLayout);
        refreshLayout.setOnRefreshListener(layout -> {
            layout.finishRefresh(2000);
            getVideoList();
        });
        refreshLayout.setOnLoadMoreListener(layout-> {
            layout.finishLoadMore(2000);
            Toast.makeText(getActivity(), "没有更多数据了", Toast.LENGTH_SHORT).show();
        });
        getVideoList();
        return root;
    }

    private void getVideoList() {
        videoViewModel.getVideoList().observe(getViewLifecycleOwner(), videoBeans->{
            for(VideoBean videoBean : videoBeans){
                Log.i("videoBean", videoBean.getName());
            }
        });
    }
}