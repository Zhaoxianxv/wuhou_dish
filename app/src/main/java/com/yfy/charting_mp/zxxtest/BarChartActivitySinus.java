
package com.yfy.charting_mp.zxxtest;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import com.yfy.charting_mp.charts.BarChart;
import com.yfy.charting_mp.components.Legend;
import com.yfy.charting_mp.components.Legend.LegendForm;
import com.yfy.charting_mp.components.Legend.LegendPosition;
import com.yfy.charting_mp.components.XAxis;
import com.yfy.charting_mp.components.XAxis.XAxisPosition;
import com.yfy.charting_mp.components.YAxis;
import com.yfy.charting_mp.data.BarData;
import com.yfy.charting_mp.data.BarDataSet;
import com.yfy.charting_mp.data.BarEntry;
import com.yfy.charting_mp.data.DataSet;
import com.yfy.charting_mp.data.filter.Approximator;
import com.yfy.charting_mp.data.filter.Approximator.ApproximatorType;
import com.yfy.charting_mp.utils.FileUtils;
import com.yfy.wuhoudish.R;
import com.yfy.charting_mp.zxxtest.notimportant.DemoBase;

import java.util.ArrayList;
import java.util.List;

public class BarChartActivitySinus extends DemoBase implements OnSeekBarChangeListener {

    protected BarChart mChart;
    private SeekBar mSeekBarX;
    private TextView tvX;
    
    private Typeface mTf;
    
    private List<BarEntry> mSinusData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_barchart_sinus);
        
        mSinusData = FileUtils.loadBarEntriesFromAssets(getAssets(),"othersine.txt");

        tvX = (TextView) findViewById(R.id.tvValueCount);

        mSeekBarX = (SeekBar) findViewById(R.id.seekbarValues);

        mChart = (BarChart) findViewById(R.id.chart1);

        mChart.setDrawBarShadow(false);
        mChart.setDrawValueAboveBar(true);

        mChart.setDescription("");

        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
        mChart.setMaxVisibleValueCount(60);

        // scaling can now only be done on x- and y-axis separately
        mChart.setPinchZoom(false);

        // draw shadows for each bar that show the maximum value
        // mChart.setDrawBarShadow(true);

        // mChart.setDrawXLabels(false);

        mChart.setDrawGridBackground(false);
        // mChart.setDrawYLabels(false);

        mTf = Typeface.createFromAsset(getAssets(), "OpenSans-Regular.ttf");

        XAxis xAxis = mChart.getXAxis();
        xAxis.setPosition(XAxisPosition.BOTTOM);
        xAxis.setTypeface(mTf);
        xAxis.setDrawGridLines(false);
        xAxis.setEnabled(false);

        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.setTypeface(mTf);
        leftAxis.setLabelCount(6, false);
        leftAxis.setStartAtZero(false);
        leftAxis.setAxisMinValue(-2.5f);
        leftAxis.setAxisMaxValue(2.5f);

        YAxis rightAxis = mChart.getAxisRight();
        rightAxis.setDrawGridLines(false);
        rightAxis.setTypeface(mTf);
        rightAxis.setLabelCount(6, false);
        rightAxis.setStartAtZero(false);
        rightAxis.setAxisMinValue(-2.5f);
        rightAxis.setAxisMaxValue(2.5f);

        mSeekBarX.setOnSeekBarChangeListener(this);
        mSeekBarX.setProgress(150); // set data

        Legend l = mChart.getLegend();
        l.setPosition(LegendPosition.BELOW_CHART_LEFT);
        l.setForm(LegendForm.SQUARE);
        l.setFormSize(9f);
        l.setTextSize(11f);
        l.setXEntrySpace(4f);

        mChart.animateXY(2000, 2000);
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
                mChart.notifyDataSetChanged();
                mChart.invalidate();
                break;
            }
            case R.id.animateX: {
                mChart.animateX(1500);
                break;
            }
            case R.id.animateY: {
                mChart.animateY(1500);
                break;
            }
            case R.id.animateXY: {

                mChart.animateXY(2000, 2000);
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
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        tvX.setText("" + (mSeekBarX.getProgress()));

        setData(mSeekBarX.getProgress());
        mChart.invalidate();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        // TODO Auto-generated method stub

    }

    private void setData(int count) {

        ArrayList<String> xVals = new ArrayList<String>();
        
        ArrayList<BarEntry> entries = new ArrayList<BarEntry>();
        
        for (int i = 0; i < count; i++) {
            xVals.add(i+"");
            entries.add(mSinusData.get(i));
        }
        
        BarDataSet set = new BarDataSet(entries, "Sinus Function");
        set.setBarSpacePercent(40f);
        set.setColor(Color.rgb(240, 120, 124));

        BarData data = new BarData(xVals, set);
        data.setValueTextSize(10f);
        data.setValueTypeface(mTf);
        data.setDrawValues(false);

        mChart.setData(data);
    }
}