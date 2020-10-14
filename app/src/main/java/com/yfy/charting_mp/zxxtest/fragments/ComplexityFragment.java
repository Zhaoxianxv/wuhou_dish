package com.yfy.charting_mp.zxxtest.fragments;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yfy.charting_mp.charts.LineChart;
import com.yfy.charting_mp.components.Legend;
import com.yfy.charting_mp.components.XAxis;
import com.yfy.charting_mp.components.YAxis;
import com.yfy.wuhoudish.R;


public class ComplexityFragment extends SimpleFragment {

    public static Fragment newInstance() {
        return new ComplexityFragment();
    }

    private LineChart mChart;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_simple_line, container, false);
        
        mChart = (LineChart) v.findViewById(R.id.lineChart1);
        
        mChart.setDescription("");
        
        mChart.setHighlightEnabled(false);
        mChart.setDrawGridBackground(false);
        
        mChart.setData(getComplexity());
        mChart.animateX(3000);
        
//        mChart.setScaleMinima(3f, 3f);
//        mChart.centerViewPort(300, 0);
        
        Typeface tf = Typeface.createFromAsset(getActivity().getAssets(),"fonts/OpenSans-Light.ttf");
        
        Legend l = mChart.getLegend();
        l.setTypeface(tf);
        
        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.setTypeface(tf);
        
        mChart.getAxisRight().setEnabled(false);
        
        XAxis xAxis = mChart.getXAxis();
        xAxis.setEnabled(false);
        
        return v;
    }
}
