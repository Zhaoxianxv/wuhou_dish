
package com.yfy.charting_mp.zxxtest;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.yfy.charting_mp.data.BarData;
import com.yfy.charting_mp.data.BarDataSet;
import com.yfy.charting_mp.data.BarEntry;
import com.yfy.charting_mp.data.Entry;
import com.yfy.charting_mp.data.LineData;
import com.yfy.charting_mp.data.LineDataSet;
import com.yfy.charting_mp.data.PieData;
import com.yfy.charting_mp.data.PieDataSet;
import com.yfy.charting_mp.utils.ColorTemplate;
import com.yfy.wuhoudish.R;
import com.yfy.charting_mp.zxxtest.listviewitems.BarChartItem;
import com.yfy.charting_mp.zxxtest.listviewitems.ChartItem;
import com.yfy.charting_mp.zxxtest.listviewitems.LineChartItem;
import com.yfy.charting_mp.zxxtest.listviewitems.PieChartItem;
import com.yfy.charting_mp.zxxtest.notimportant.DemoBase;

import java.util.ArrayList;
import java.util.List;

/**
 * Demonstrates the use of charts inside a ListView. IMPORTANT: provide a
 * specific height attribute for the chart inside your listview-item
 * 
 * @author Philipp Jahoda
 */
public class ListViewMultiChartActivity extends DemoBase {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_listview_chart);
        
        ListView lv = (ListView) findViewById(R.id.listView1);

        ArrayList<ChartItem> list = new ArrayList<ChartItem>();

        // 30 items
        for (int i = 0; i < 30; i++) {
            
            if(i % 3 == 0) {
                list.add(new LineChartItem(generateDataLine(i + 1), getApplicationContext()));
            } else if(i % 3 == 1) {
                list.add(new BarChartItem(generateDataBar(i + 1), getApplicationContext()));
            } else if(i % 3 == 2) {
                list.add(new PieChartItem(generateDataPie(i + 1), getApplicationContext()));
            }
        }

        ChartDataAdapter cda = new ChartDataAdapter(getApplicationContext(), list);
        lv.setAdapter(cda);
    }

    /** adapter that supports 3 different item types */
    private class ChartDataAdapter extends ArrayAdapter<ChartItem> {
        
        public ChartDataAdapter(Context context, List<ChartItem> objects) {
            super(context, 0, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return getItem(position).getView(position, convertView, getContext());
        }
        
        @Override
        public int getItemViewType(int position) {           
            // return the views type
            return getItem(position).getItemType();
        }
        
        @Override
        public int getViewTypeCount() {
            return 3; // we have 3 different item-types
        }
    }
    
    /**
     * generates a random ChartData object with just one DataSet
     * 
     * @return
     */
    private LineData generateDataLine(int cnt) {

        ArrayList<Entry> e1 = new ArrayList<Entry>();

        for (int i = 0; i < 12; i++) {
            e1.add(new Entry((int) (Math.random() * 65) + 40, i));
        }

        LineDataSet d1 = new LineDataSet(e1, "New DataSet " + cnt + ", (1)");
        d1.setLineWidth(2.5f);
        d1.setCircleSize(4.5f);
        d1.setHighLightColor(Color.rgb(244, 117, 117));
        d1.setDrawValues(false);
        
        ArrayList<Entry> e2 = new ArrayList<Entry>();

        for (int i = 0; i < 12; i++) {
            e2.add(new Entry(e1.get(i).getVal() - 30, i));
        }

        LineDataSet d2 = new LineDataSet(e2, "New DataSet " + cnt + ", (2)");
        d2.setLineWidth(2.5f);
        d2.setCircleSize(4.5f);
        d2.setHighLightColor(Color.rgb(244, 117, 117));
        d2.setColor(ColorTemplate.VORDIPLOM_COLORS[0]);
        d2.setCircleColor(ColorTemplate.VORDIPLOM_COLORS[0]);
        d2.setDrawValues(false);
        
        ArrayList<LineDataSet> sets = new ArrayList<LineDataSet>();
        sets.add(d1);
        sets.add(d2);
        
        LineData cd = new LineData(getMonths(), sets);
        return cd;
    }
    
    /**
     * generates a random ChartData object with just one DataSet
     * 
     * @return
     */
    private BarData generateDataBar(int cnt) {

        ArrayList<BarEntry> entries = new ArrayList<BarEntry>();

        for (int i = 0; i < 12; i++) {
            entries.add(new BarEntry((int) (Math.random() * 70) + 30, i));
        }

        BarDataSet d = new BarDataSet(entries, "New DataSet " + cnt);
        d.setBarSpacePercent(20f);
        d.setColors(ColorTemplate.VORDIPLOM_COLORS);
        d.setHighLightAlpha(255);
        
        BarData cd = new BarData(getMonths(), d);
        return cd;
    }
    
    /**
     * generates a random ChartData object with just one DataSet
     * 
     * @return
     */
    private PieData generateDataPie(int cnt) {

        ArrayList<Entry> entries = new ArrayList<Entry>();

        for (int i = 0; i < 4; i++) {
            entries.add(new Entry((int) (Math.random() * 70) + 30, i));
        }

        PieDataSet d = new PieDataSet(entries, "");
        
        // space between slices
        d.setSliceSpace(2f);
        d.setColors(ColorTemplate.VORDIPLOM_COLORS);
        
        PieData cd = new PieData(getQuarters(), d);
        return cd;
    }
    
    private ArrayList<String> getQuarters() {
        
        ArrayList<String> q = new ArrayList<String>();
        q.add("1st Quarter");
        q.add("2nd Quarter");
        q.add("3rd Quarter");
        q.add("4th Quarter");
        
        return q;
    }

    private ArrayList<String> getMonths() {

        ArrayList<String> m = new ArrayList<String>();
        m.add("Jan");
        m.add("Feb");
        m.add("Mar");
        m.add("Apr");
        m.add("May");
        m.add("Jun");
        m.add("Jul");
        m.add("Aug");
        m.add("Sep");
        m.add("Okt");
        m.add("Nov");
        m.add("Dec");

        return m;
    }
}
