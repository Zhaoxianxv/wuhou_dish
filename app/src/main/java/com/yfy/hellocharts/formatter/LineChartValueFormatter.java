package com.yfy.hellocharts.formatter;


import com.yfy.hellocharts.model.PointValue;

public interface LineChartValueFormatter {

    public int formatChartValue(char[] formattedValue, PointValue value);
}
