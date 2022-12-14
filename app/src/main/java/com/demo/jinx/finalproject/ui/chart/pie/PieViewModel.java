package com.demo.jinx.finalproject.ui.chart.pie;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.demo.jinx.finalproject.bean.PieBean;

import java.util.ArrayList;
import java.util.List;

public class PieViewModel extends ViewModel {

    private MutableLiveData<List<PieBean>> pieList;

    public PieViewModel(){
        String[] salaries = new String[]{"月薪8-15k","月薪15-30k","月薪30-100k","月薪100k+"};
        int[] percentage = {50,25,15,10};
        pieList = new MutableLiveData<>();
        List<PieBean> list = new ArrayList<>();
        for (int i = 0; i < salaries.length; i++) {
            list.add(new PieBean(salaries[i],percentage[i]));
        }
    }

    public MutableLiveData<List<PieBean>> getPieList() {
        return pieList;
    }
}
