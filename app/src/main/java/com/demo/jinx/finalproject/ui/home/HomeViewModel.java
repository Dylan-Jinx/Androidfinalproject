package com.demo.jinx.finalproject.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.demo.jinx.finalproject.bean.NewsBean;
import com.demo.jinx.finalproject.utils.NetUtils;
import com.github.leonardoxh.livedatacalladapter.Resource;

import java.util.List;

public class HomeViewModel extends ViewModel {
    public LiveData<List<NewsBean>> getNewsList(){
        return Transformations.map(NetUtils.get().getNewsList(), Resource::getResource);
    }

    public LiveData<List<NewsBean>> getAdList(){
        return Transformations.map(NetUtils.get().getAdList(), Resource::getResource);
    }
}
