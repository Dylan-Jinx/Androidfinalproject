package com.demo.jinx.finalproject.ui.chart.bar;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.demo.jinx.finalproject.bean.BarBean;
import com.demo.jinx.finalproject.bean.LineBean;

import java.util.ArrayList;
import java.util.List;

public class BarViewModel extends ViewModel {

    private MutableLiveData<List<BarBean>> barList;

    public BarViewModel(){
        String[]years = new String[]{"应届生","1-2年","2-3年"
                ,"3-5年","5-8年","8-10年","10年"};
        int[] salaries1 = {6000,13000,20000,26000,35000,50000,100000};
        int[] salaries2 = {4000,11000,18000,24000,33000,48000,98000};
        barList = new MutableLiveData<>();
        List<BarBean> list =new ArrayList<>();
        for (int i = 0; i < years.length; i++) {
            LineBean lineBean1 = new LineBean(years[i],salaries1[i]);
            LineBean lineBean2 = new LineBean(years[i],salaries1[i]);
            list.add(new BarBean(lineBean1,lineBean2));
        }
        barList.setValue(list);
    }

    public MutableLiveData<List<BarBean>> getBarList() {
        return barList;
    }
}
