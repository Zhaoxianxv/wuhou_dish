
package com.yfy.charting_mp.zxxtest;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Toast;

import com.yfy.charting_mp.charts.HorizontalBarChart;
import com.yfy.charting_mp.components.Legend;
import com.yfy.charting_mp.components.Legend.LegendPosition;
import com.yfy.charting_mp.components.XAxis;
import com.yfy.charting_mp.components.XAxis.XAxisPosition;
import com.yfy.charting_mp.components.YAxis;
import com.yfy.charting_mp.data.BarData;
import com.yfy.charting_mp.data.BarDataSet;
import com.yfy.charting_mp.data.BarEntry;
import com.yfy.charting_mp.data.DataSet;
import com.yfy.charting_mp.data.Entry;
import com.yfy.charting_mp.data.filter.Approximator;
import com.yfy.charting_mp.data.filter.Approximator.ApproximatorType;
import com.yfy.charting_mp.listener.OnChartValueSelectedListener;
import com.yfy.charting_mp.highlight.Highlight;
import com.yfy.charting_mp.utils.ValueFormatter;
import com.yfy.wuhoudish.R;
import com.yfy.charting_mp.zxxtest.notimportant.DemoBase;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class StackedBarActivityNegative extends DemoBase implements
        OnChartValueSelectedListener {

    private HorizontalBarChart mChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_age_distribution);

        setTitle("Age Distribution Austria");

        mChart = (HorizontalBarChart) findViewById(R.id.chart1);
        mChart.setOnChartValueSelectedListener(this);
        mChart.setDrawGridBackground(false);
        mChart.setDescription("");

        // scaling can now only be done on x- and y-axis separately
        mChart.setPinchZoom(false);

        mChart.setDrawBarShadow(false);
        mChart.setDrawValueAboveBar(true);
        
        mChart.getAxisLeft().setEnabled(false);
        mChart.getAxisRight().setStartAtZero(false);
        mChart.getAxisRight().setAxisMaxValue(25f);
        mChart.getAxisRight().setAxisMinValue(-25f);
        mChart.getAxisRight().setLabelCount(7, false);
        mChart.getAxisRight().setValueFormatter(new CustomFormatter());
        mChart.getAxisRight().setTextSize(9f);

        XAxis xAxis = mChart.getXAxis();
        xAxis.setPosition(XAxisPosition.BOTH_SIDED);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(false);
        xAxis.setTextSize(9f);

        Legend l = mChart.getLegend();
        l.setPosition(LegendPosition.BELOW_CHART_RIGHT);
        l.setFormSize(8f);
        l.setFormToTextSpace(4f);
        l.setXEntrySpace(6f);

        // IMPORTANT: When using negative values in stacked bars, always make sure the negative values are in the array first
        ArrayList<BarEntry> yValues = new ArrayList<BarEntry>();
        yValues.add(new BarEntry(new float[]{ -10, 10 }, 0));
        yValues.add(new BarEntry(new float[]{ -12, 13 }, 1));
        yValues.add(new BarEntry(new float[]{ -15, 15 }, 2));
        yValues.add(new BarEntry(new float[]{ -17, 17 }, 3));
        yValues.add(new BarEntry(new float[]{ -19, 20 }, 4));
        yValues.add(new BarEntry(new float[]{ -19, 19 }, 5));
        yValues.add(new BarEntry(new float[]{ -16, 16 }, 6));
        yValues.add(new BarEntry(new float[]{ -13, 14 }, 7));
        yValues.add(new BarEntry(new float[]{ -10, 11 }, 8));
        yValues.add(new BarEntry(new float[]{ -5, 6 }, 9));
        yValues.add(new BarEntry(new float[]{ -1, 2 }, 10));

        BarDataSet set = new BarDataSet(yValues, "Age Distribution");
        set.setValueFormatter(new CustomFormatter());
        set.setValueTextSize(7f);
        set.setAxisDependency(YAxis.AxisDependency.RIGHT);
        set.setBarSpacePercent(40f);
        set.setColors(new int[] {Color.rgb(67,67,72), Color.rgb(124,181,236)});
        set.setStackLabels(new String[]{
                "Men", "Women"
        });

        String []xVals = new String[]{"0-10", "10-20", "20-30", "30-40", "40-50", "50-60", "60-70", "70-80", "80-90", "90-100", "100+"};

        BarData data = new BarData(xVals, set);
        mChart.setData(data);
        mChart.invalidate();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.actionToggleValues: {
                for (DataSet<?> set : mChart.getData().getDataSets())
                    set.setDrawValues(!set.isDrawValuesEnabled());

                mChart.invalidate();
                break;
            }
            case R.id.actionToggleHighlight: {
                if (mChart.isHighlightEnabled())
                    mChart.setHighlightEnabled(false);
                else
                    mChart.setHighlightEnabled(true);
                mChart.invalidate();
                break;
            }
            case R.id.actionTogglePinch: {
                if (mChart.isPinchZoomEnabled())
                    mChart.setPinchZoom(false);
                else
                    mChart.setPinchZoom(true);

                mChart.invalidate();
                break;
            }
            case R.id.actionToggleAutoScaleMinMax: {
                mChart.setAutoScaleMinMaxEnabled(!mChart.isAutoScaleMinMaxEnabled());
                mChart.notifyDataSetChanged();
                break;
            }
            case R.id.actionToggleHighlightArrow: {
                if (mChart.isDrawHighlightArrowEnabled())
                    mChart.setDrawHighlightArrow(false);
                else
                    mChart.setDrawHighlightArrow(true);
                mChart.invalidate();
                break;
            }
            case R.id.actionToggleStartzero: {
                mChart.getAxisLeft().setStartAtZero(!mChart.getAxisLeft().isStartAtZeroEnabled());
                mChart.getAxisRight().setStartAtZero(!mChart.getAxisRight().isStartAtZeroEnabled());
                mChart.invalidate();
                break;
            }
            case R.id.animateX: {
                mChart.animateX(3000);
                break;
            }
            case R.id.animateY: {
                mChart.animateY(3000);
                break;
            }
            case R.id.animateXY: {

                mChart.animateXY(3000, 3000);
                break;
            }
            case R.id.actionToggleFilter: {

                Approximator a = new Approximator(ApproximatorType.DOUGLAS_PEUCKER, 25);

                if (!mChart.isFilteringEnabled()) {
                    mChart.enableFiltering(a);
                } else {
                    mChart.disableFiltering();
                }
                mChart.invalidate();
                break;
            }
            case R.id.actionSave: {
                if (mChart.saveToGallery("title" + System.currentTimeMillis(), 50)) {
                    Toast.makeText(getApplicationContext(), "Saving SUCCESSFUL!",
                            Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(getApplicationContext(), "Saving FAILED!", Toast.LENGTH_SHORT)
                            .show();
                break;
            }
        }
        return true;
    }

    @Override
    public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {

        BarEntry entry = (BarEntry) e;
        Log.i("VAL SELECTED",
                "Value: " + Math.abs(entry.getVals()[h.getStackIndex()]));
    }

    @Override
    public void onNothingSelected() {
        // TODO Auto-generated method stub

    }

    private class CustomFormatter implements ValueFormatter {

        private DecimalFormat mFormat;

        public CustomFormatter() {
            mFormat = new DecimalFormat("###");
        }

        @Override
        public String getFormattedValue(float value) {
            return mFormat.format(Math.abs(value)) + "m";
        }
    }
}
