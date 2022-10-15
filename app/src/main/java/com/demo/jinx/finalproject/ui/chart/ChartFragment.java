package com.demo.jinx.finalproject.ui.chart;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.demo.jinx.finalproject.R;
import com.demo.jinx.finalproject.bean.BoomMenuItemBean;
import com.nightonke.boommenu.BoomButtons.TextInsideCircleButton;
import com.nightonke.boommenu.BoomMenuButton;

public class ChartFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ChartViewModel chartViewModel = new ViewModelProvider(this).get(ChartViewModel.class);
        View root = inflater.inflate(R.layout.fragment_chart,container,false);
        BoomMenuButton bmb = root.findViewById(R.id.bmb);
        chartViewModel.getBoomMenuItemList().observe(getViewLifecycleOwner(),boomMenuItemBeans -> {
            for (BoomMenuItemBean boomMenuItemBean : boomMenuItemBeans) {
                TextInsideCircleButton.Builder builder = new TextInsideCircleButton.Builder();
                builder.normalImageRes(boomMenuItemBean.getImageId())
                        .normalText(boomMenuItemBean.getTitle())
                        .listener(index -> {
                            switch (index){
                                case 0:
                                    Navigation.findNavController(root).navigate(
                                            R.id.action_navigation_chart_to_lineFragment
                                    );
                                    break;
                                case 1:
                                    Navigation.findNavController(root).navigate(
                                            R.id.action_navigation_chart_to_barFragment
                                    );
                                    break;
                                case 2:
                                    Navigation.findNavController(root).navigate(
                                            R.id.action_navigation_chart_to_pieFragment
                                    );
                                    break;
                            }
                        });
                bmb.addBuilder(builder);
            }
        });
        return root;
    }
}