package com.yfy.hellocharts.listener;


import com.yfy.hellocharts.model.PointValue;
import com.yfy.hellocharts.model.SubcolumnValue;

public class DummyCompoLineColumnChartOnValueSelectListener implements ComboLineColumnChartOnValueSelectListener {

    @Override
    public void onColumnValueSelected(int columnIndex, int subcolumnIndex, SubcolumnValue value) {

    }

    @Override
    public void onPointValueSelected(int lineIndex, int pointIndex, PointValue value) {

    }

    @Override
    public void onValueDeselected() {

    }
}
