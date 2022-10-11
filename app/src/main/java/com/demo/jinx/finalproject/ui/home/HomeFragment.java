package com.demo.jinx.finalproject.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.demo.jinx.finalproject.R;
import com.demo.jinx.finalproject.bean.NewsBean;
import com.scwang.smart.refresh.layout.api.RefreshLayout;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        Log.d("233", "onCreateView: "+homeViewModel.getNewsList());
        View root = inflater.inflate(R.layout.fragment_home,container,false);
        RefreshLayout refreshLayout = root.findViewById(R.id.refreshLayout);
        refreshLayout.setOnRefreshListener(layout -> {
            layout.finishRefresh(2000);
            getAdList();
            getNewsList();
        });
        refreshLayout.setOnLoadMoreListener(layout-> {
            layout.finishLoadMore(2000);
            Toast.makeText(getActivity(), "没有更多数据了", Toast.LENGTH_SHORT).show();
        });
        getAdList();
        getNewsList();
        return root;
    }

    private void getNewsList() {
        homeViewModel.getNewsList().observe(getViewLifecycleOwner(), newsBeans -> {
            Log.i("233", "getNewsList: "+newsBeans);
            for (NewsBean newsBean : newsBeans ){
                Log.i("AD:", newsBean.getNewsName());
            }
        });
    }

    private void getAdList() {
        homeViewModel.getAdList().observe(getViewLifecycleOwner(), newsBeans -> {
            Log.i("233", "getNewsList: "+newsBeans);
            for (NewsBean newsBean : newsBeans ){
                Log.i("News:", newsBean.getNewsName());
            }
        });
    }
}