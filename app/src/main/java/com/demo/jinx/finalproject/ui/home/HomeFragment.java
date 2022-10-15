package com.demo.jinx.finalproject.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.demo.jinx.finalproject.R;
import com.demo.jinx.finalproject.adapter.HomeAdapter;
import com.demo.jinx.finalproject.adapter.ImageAdapter;
import com.demo.jinx.finalproject.adapter.ImageTitleNumAdapter;
import com.demo.jinx.finalproject.bean.NewsBean;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.youth.banner.Banner;
import com.youth.banner.adapter.BannerAdapter;
import com.youth.banner.transformer.ZoomOutPageTransformer;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private HomeAdapter homeAdapter;
    private Banner<?, BannerAdapter<?,?>> banner;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        RecyclerView recyclerView=root.findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        homeAdapter=new HomeAdapter(null);
        recyclerView.setAdapter(homeAdapter);
        homeAdapter.setEmptyView(R.layout.empty_home);
        View headerView = inflater.inflate(R.layout.head_home, container, false);
        homeAdapter.setHeaderView(headerView);
        homeAdapter.setHeaderWithEmptyEnable(true);
        banner = headerView.findViewById(R.id.banner);
        banner.addBannerLifecycleObserver(this)//添加生命周期观察者
                .setPageTransformer(new ZoomOutPageTransformer())
                .start();
        List<Integer> list=new ArrayList<>();
        for (int i = 0; i <3 ; i++) {
            list.add(R.drawable.pic_item_list_default);
        }
        banner.setAdapter(new ImageAdapter(list));
        RefreshLayout refreshLayout = root.findViewById(R.id.refreshLayout);
        refreshLayout.setOnRefreshListener(layout -> {
            layout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
            getAdList();
            getNewsList();
        });
        refreshLayout.setOnLoadMoreListener(layout -> {
            layout.finishLoadMore(2000/*,false*/);//传入false表示加载失败
            Toast.makeText(getActivity(), "没有更多数据了", Toast.LENGTH_SHORT).show();
        });
        /** 测试的时候注释
        getAdList();
        getNewsList();
        **/
        LinearLayout linearLayout_python = headerView.findViewById(R.id.linearLayout_python);
        linearLayout_python.setOnClickListener(v-> Navigation.findNavController(v)
                .navigate(R.id.action_navigation_home_to_pythonFragment));
        homeAdapter.setOnItemChildClickListener(((adapter, view, position) -> {
            Bundle bundle = new Bundle();
            bundle.putString("url",homeAdapter.getData().get(position).getNewsUrl());
            Navigation.findNavController(view).navigate(R.id.action_navigation_home_to_webFragment);
        }));
        return root;
    }

    private void getNewsList() {
        try {
            homeViewModel.getNewsList().observe(getViewLifecycleOwner(), newsBeans -> {
//            Log.i("233", "getNewsList: "+newsBeans);
//            for (NewsBean newsBean : newsBeans ){
//                Log.i("AD:", newsBean.getNewsName());
//            }
                banner.setAdapter(new ImageTitleNumAdapter(newsBeans));
                banner.setOnBannerListener(((data, position) -> {
                    Bundle bundle = new Bundle();
                    bundle.putString("url",homeAdapter.getData().get(position).getNewsUrl());
                    Navigation.findNavController(banner).navigate(R.id.action_navigation_home_to_webFragment,bundle);
                }));
            });
        }catch (Exception e){
            Log.i("test", "interrupt");
            e.printStackTrace();
        }
    }

    private void getAdList() {
        homeViewModel.getAdList().observe(getViewLifecycleOwner(), newsBeans -> {
//            Log.i("233", "getNewsList: "+newsBeans);
//            for (NewsBean newsBean : newsBeans ){
//                Log.i("News:", newsBean.getNewsName());
//            }
            //TODO occur error code
            homeAdapter.setList(newsBeans);
        });
    }
}