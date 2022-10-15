package com.demo.jinx.finalproject.ui.chart.line;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.demo.jinx.finalproject.R;
import com.demo.jinx.finalproject.base.BaseFragment2;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.util.ArrayList;
import java.util.List;

public class LineFragment extends BaseFragment2 {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_line,container,false);
        LineChart chart = root.findViewById(R.id.lineChart);
        LineViewModel lineViewModel = new ViewModelProvider(this).get
                (LineViewModel.class);
        lineViewModel.getLineList().observe(getViewLifecycleOwner(), lineBeans -> {
            List<Entry> entries = new ArrayList<>();
            for (int i = 0; i < lineBeans.size(); i++) {
                entries.add(new Entry(i,lineBeans.get(i).getSalaries()));
            }
            LineDataSet dataSet = new LineDataSet(entries, "工资");
            dataSet.setValueTextColor(Color.RED);
            dataSet.setValueTextSize(12f);
            dataSet.setColor(Color.BLUE);
            dataSet.setLineWidth(6f);
            LineData lineData = new LineData(dataSet);
            chart.setData(lineData);
            chart.invalidate();
            // X
            XAxis xAxis = chart.getXAxis();
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
            xAxis.setAxisLineColor(Color.BLACK);
            xAxis.setAxisLineWidth(3f);
            xAxis.setTextSize(10f);
            xAxis.enableGridDashedLine(10f,10f,0f);
            xAxis.setTextColor(Color.BLACK);
            xAxis.setValueFormatter(new ValueFormatter() {
                @Override
                public String getFormattedValue(float value) {
                    return lineBeans.get((int)value).getYear();
                }
            });
            // Y
            chart.getAxisRight().setEnabled(false);
            YAxis yAxis = chart.getAxisRight();
            yAxis.setAxisLineColor(Color.BLACK);
            yAxis.setAxisLineWidth(3f);
            yAxis.setTextSize(10f);
            yAxis.enableGridDashedLine(10f,10f,0f);
            yAxis.setTextColor(Color.BLACK);
            yAxis.setAxisMinimum(0f);
            yAxis.setSpaceTop(38.2f);
            LimitLine limitLine = new LimitLine(10000f,"厦门市平均工资");
            limitLine.setLineColor(Color.MAGENTA);
            limitLine.setLineWidth(2f);
            yAxis.addLimitLine(limitLine);
            Legend l = chart.getLegend();
            l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
            l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
            //Describe
            Description description = chart.getDescription();
            description.setText("Java工程师经验与工资的对应情况");
            description.setTextColor(Color.BLACK);
            description.setTextSize(16f);
            description.setTextAlign(Paint.Align.CENTER);
            description.setPosition(540f,100f);
            chart.animateX(5000);
        });
        return root;
    }
}
