package com.demo.jinx.finalproject.ui.video;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.demo.jinx.finalproject.R;
import com.demo.jinx.finalproject.adapter.VideoAdapter;
import com.demo.jinx.finalproject.bean.VideoBean;
import com.demo.jinx.finalproject.bean.VideoDetailListItem;
import com.scwang.smart.refresh.layout.api.RefreshLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class VideoFragment extends Fragment {

    private VideoViewModel videoViewModel;
    private VideoAdapter videoAdapter;

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
        RecyclerView recyclerView = root.findViewById(R.id.recycleView);
        videoAdapter = new VideoAdapter(null);
        recyclerView.setAdapter(videoAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        videoAdapter.setOnItemClickListener((adapter, view, position) -> {
            VideoBean videoBean = videoAdapter.getData().get(position);
            Bundle bundle = new Bundle();
            bundle.putString("image", videoBean.getImg());
            bundle.putString("name", videoBean.getName());
            bundle.putString("intro", videoBean.getIntro());
            List<String> list = new ArrayList<>();
            for (VideoDetailListItem videoDetailListItem : videoBean.getVideoDetailList()) {
                list.add(videoDetailListItem.getVideoName());
            }
            bundle.putStringArray("list", list.toArray(new String[0]));
            Navigation.findNavController(root).navigate(
                    R.id.action_videoFragment_to_videoDetailFragment,bundle);
        });
        getVideoList();
        return root;
    }

    private void getVideoList() {
        videoViewModel.getVideoList().observe(getViewLifecycleOwner(), videoBeans->{
            for(VideoBean videoBean : videoBeans){
//                Log.i("videoBean", videoBean.getName());
                videoAdapter.setList(videoBeans);
            }
        });
    }
}