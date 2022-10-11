package com.demo.jinx.finalproject.ui.video;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.demo.jinx.finalproject.bean.VideoBean;
import com.demo.jinx.finalproject.utils.NetUtils;
import com.github.leonardoxh.livedatacalladapter.Resource;

import java.util.List;

public class VideoViewModel extends ViewModel {

    public LiveData<List<VideoBean>> getVideoList(){
        return Transformations.map(NetUtils.get().getVideoList(), Resource::getResource);
    }
}
