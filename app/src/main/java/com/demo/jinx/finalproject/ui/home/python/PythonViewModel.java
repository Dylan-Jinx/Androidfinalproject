package com.demo.jinx.finalproject.ui.home.python;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.demo.jinx.finalproject.bean.PythonBean;
import com.demo.jinx.finalproject.utils.NetUtils;
import com.github.leonardoxh.livedatacalladapter.Resource;

import java.util.List;

public class PythonViewModel extends ViewModel {
    public LiveData<List<PythonBean>> getPythonList(){
        return Transformations.map(NetUtils.get().getPythonList(), Resource::getResource);
    }
}
