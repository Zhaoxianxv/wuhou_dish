
package com.yfy.charting_mp.zxxtest.notimportant;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.yfy.charting_mp.utils.Utils;
import com.yfy.wuhoudish.R;
import com.yfy.charting_mp.zxxtest.AnotherBarActivity;
import com.yfy.charting_mp.zxxtest.BarChartActivity;
import com.yfy.charting_mp.zxxtest.BarChartActivityMultiDataset;
import com.yfy.charting_mp.zxxtest.BarChartActivitySinus;
import com.yfy.charting_mp.zxxtest.BubbleChartActivity;
import com.yfy.charting_mp.zxxtest.CandleStickChartActivity;
import com.yfy.charting_mp.zxxtest.CombinedChartActivity;
import com.yfy.charting_mp.zxxtest.CubicLineChartActivity;
import com.yfy.charting_mp.zxxtest.DynamicalAddingActivity;
import com.yfy.charting_mp.zxxtest.HorizontalBarChartActivity;
import com.yfy.charting_mp.zxxtest.InvertedLineChartActivity;
import com.yfy.charting_mp.zxxtest.LineChartActivity1;
import com.yfy.charting_mp.zxxtest.LineChartActivity2;
import com.yfy.charting_mp.zxxtest.LineChartActivityColored;
import com.yfy.charting_mp.zxxtest.ListViewBarChartActivity;
import com.yfy.charting_mp.zxxtest.ListViewMultiChartActivity;
import com.yfy.charting_mp.zxxtest.MultiLineChartActivity;
import com.yfy.charting_mp.zxxtest.PerformanceLineChart;
import com.yfy.charting_mp.zxxtest.PieChartActivity;
import com.yfy.charting_mp.zxxtest.RadarChartActivitry;
import com.yfy.charting_mp.zxxtest.RealtimeLineChartActivity;
import com.yfy.charting_mp.zxxtest.ScatterChartActivity;
import com.yfy.charting_mp.zxxtest.ScrollViewActivity;
import com.yfy.charting_mp.zxxtest.StackedBarActivity;
import com.yfy.charting_mp.zxxtest.StackedBarActivityNegative;
import com.yfy.charting_mp.zxxtest.fragments.SimpleChartDemo;

import java.util.ArrayList;
import java.util.List;

public class ChartMainActivity extends Activity implements OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_chart_main);

        // initialize the utilities
        Utils.init(this);

        ArrayList<ContentItem> objects = new ArrayList<ContentItem>();

        objects.add(new ContentItem("Line Chart", "A simple demonstration of the linechart."));
        objects.add(new ContentItem("Line Chart (Dual YAxis)",
                "Demonstration of the linechart with dual y-axis."));
        objects.add(new ContentItem("Bar Chart", "A simple demonstration of the bar chart."));
        objects.add(new ContentItem("Horizontal Bar Chart",
                "A simple demonstration of the horizontal bar chart."));
        objects.add(new ContentItem("Combined Chart",
                "Demonstrates how to create a combined chart (bar and line in this case)."));
        objects.add(new ContentItem("Pie Chart", "A simple demonstration of the pie chart."));
        objects.add(new ContentItem("Scatter Chart", "A simple demonstration of the scatter chart."));
        objects.add(new ContentItem("Bubble Chart", "A simple demonstration of the bubble chart."));
        objects.add(new ContentItem("Stacked Bar Chart",
                "A simple demonstration of a bar chart with stacked bars."));
        objects.add(new ContentItem("Stacked Bar Chart Negative",
                "A simple demonstration of stacked bars with negative and positive values."));
        objects.add(new ContentItem("Another Bar Chart",
                "Implementation of a BarChart that only shows values at the bottom."));
        objects.add(new ContentItem("Multiple Lines Chart",
                "A line chart with multiple DataSet objects. One color per DataSet."));
        objects.add(new ContentItem("Multiple Bars Chart",
                "A bar chart with multiple DataSet objects. One multiple colors per DataSet."));
        objects.add(new ContentItem("Draw Chart",
                "Demonstration of drawing values into the chart per touch-gesture. With callbacks."));
        objects.add(new ContentItem(
                "Charts in ViewPager Fragments",
                "Demonstration of charts inside ViewPager Fragments. In this example the focus was on the design and look and feel of the chart."));
        objects.add(new ContentItem(
                "BarChart inside ListView",
                "Demonstrates the usage of a BarChart inside a ListView item."));
        objects.add(new ContentItem(
                "Multiple charts inside ListView",
                "Demonstrates the usage of different chart types inside a ListView."));
        objects.add(new ContentItem(
                "Inverted Line Chart",
                "Demonstrates the feature of inverting the y-axis."));
        objects.add(new ContentItem(
                "Candle Stick Chart",
                "Demonstrates usage of the CandleStickChart."));
        objects.add(new ContentItem(
                "Cubic Line Chart",
                "Demonstrates cubic lines in a LineChart."));
        objects.add(new ContentItem(
                "Radar Chart",
                "Demonstrates the use of a spider-web like (net) chart."));
        objects.add(new ContentItem(
                "Colored Line Chart",
                "Shows a LineChart with different background and line color."));
        objects.add(new ContentItem(
                "Realtime Chart",
                "This chart is fed with new data in realtime. It also restrains the view on the x-axis."));
        objects.add(new ContentItem(
                "Dynamical data adding",
                "This Activity demonstrates dynamical adding of Entries and DataSets (real time graph)."));
        objects.add(new ContentItem(
                "Performance Line Chart",
                "Renders up to 30.000 objects smoothly."));
        objects.add(new ContentItem(
                "Sinus Bar Chart",
                "A Bar Chart plotting the sinus function with 8.000 values."));
        objects.add(new ContentItem(
                "Chart in ScrollView",
                "This demonstrates how to use a chart inside a ScrollView."));

        MyAdapter adapter = new MyAdapter(this, objects);

        ListView lv = (ListView) findViewById(R.id.listView1);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> av, View v, int pos, long arg3) {

        Intent i;

        switch (pos) {
            case 0:
                i = new Intent(this, LineChartActivity1.class);
                startActivity(i);
                break;
            case 1:
                i = new Intent(this, LineChartActivity2.class);
                startActivity(i);
                break;
            case 2:
                i = new Intent(this, BarChartActivity.class);
                startActivity(i);
                break;
            case 3:
                i = new Intent(this, HorizontalBarChartActivity.class);
                startActivity(i);
                break;
            case 4:
                i = new Intent(this, CombinedChartActivity.class);
                startActivity(i);
                break;
            case 5:
                i = new Intent(this, PieChartActivity.class);
                startActivity(i);
                break;
            case 6:
                i = new Intent(this, ScatterChartActivity.class);
                startActivity(i);
                break;
            case 7:
                i = new Intent(this, BubbleChartActivity.class);
                startActivity(i);
                break;
            case 8:
                i = new Intent(this, StackedBarActivity.class);
                startActivity(i);
                break;
            case 9:
                i = new Intent(this, StackedBarActivityNegative.class);
                startActivity(i);
                break;
            case 10:
                i = new Intent(this, AnotherBarActivity.class);
                startActivity(i);
                break;
            case 11:
                i = new Intent(this, MultiLineChartActivity.class);
                startActivity(i);
                break;
            case 12:
                i = new Intent(this, BarChartActivityMultiDataset.class);
                startActivity(i);
                break;
            case 13:
                // i = new Intent(this, DrawChartActivity.class);
                // startActivity(i);

                AlertDialog.Builder b = new AlertDialog.Builder(this);
                b.setTitle("Feature not available");
                b.setMessage("Due to recent changes to the data model of the library, this feature is temporarily not available.");
                b.setPositiveButton("OK", null);
                b.create().show();
                break;
            case 14:
                i = new Intent(this, SimpleChartDemo.class);
                startActivity(i);
                break;
            case 15:
                i = new Intent(this, ListViewBarChartActivity.class);
                startActivity(i);
                break;
            case 16:
                i = new Intent(this, ListViewMultiChartActivity.class);
                startActivity(i);
                break;
            case 17:
                i = new Intent(this, InvertedLineChartActivity.class);
                startActivity(i);
                break;
            case 18:
                i = new Intent(this, CandleStickChartActivity.class);
                startActivity(i);
                break;
            case 19:
                i = new Intent(this, CubicLineChartActivity.class);
                startActivity(i);
                break;
            case 20:
                i = new Intent(this, RadarChartActivitry.class);
                startActivity(i);
                break;
            case 21:
                i = new Intent(this, LineChartActivityColored.class);
                startActivity(i);
                break;
            case 22:
                i = new Intent(this, RealtimeLineChartActivity.class);
                startActivity(i);
                break;
            case 23:
                i = new Intent(this, DynamicalAddingActivity.class);
                startActivity(i);
                break;
            case 24:
                i = new Intent(this, PerformanceLineChart.class);
                startActivity(i);
                break;
            case 25:
                i = new Intent(this, BarChartActivitySinus.class);
                startActivity(i);
                break;
            case 26:
                i = new Intent(this, ScrollViewActivity.class);
                startActivity(i);
                break;
        }

        overridePendingTransition(R.anim.move_right_in_activity, R.anim.move_left_out_activity);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent i = null;

        switch (item.getItemId()) {
            case R.id.viewGithub:
                i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("https://github.com/PhilJay/MPAndroidChart"));
                startActivity(i);
                break;
            case R.id.report:
                i = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto", "philjay.librarysup@gmail.com", null));
                i.putExtra(Intent.EXTRA_SUBJECT, "MPAndroidChart Issue");
                i.putExtra(Intent.EXTRA_TEXT, "Your error report here...");
                startActivity(Intent.createChooser(i, "Report Problem"));
                break;
            case R.id.blog:
                i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("http://www.xxmassdeveloper.com"));
                startActivity(i);
                break;
            case R.id.website:
                i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("http://at.linkedin.com/in/philippjahoda"));
                startActivity(i);
                break;
        }

        return true;
    }

    private class ContentItem {
        String name;
        String desc;

        public ContentItem(String n, String d) {
            name = n;
            desc = d;
        }
    }

    private class MyAdapter extends ArrayAdapter<ContentItem> {

        public MyAdapter(Context context, List<ContentItem> objects) {
            super(context, 0, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ContentItem c = getItem(position);

            ViewHolder holder = null;

            if (convertView == null) {

                holder = new ViewHolder();

                convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, null);
                holder.tvName = (TextView) convertView.findViewById(R.id.tvName);
                holder.tvDesc = (TextView) convertView.findViewById(R.id.tvDesc);

                convertView.setTag(holder);

            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.tvName.setText(c.name);
            holder.tvDesc.setText(c.desc);

            return convertView;
        }

        private class ViewHolder {

            TextView tvName, tvDesc;
        }
    }
}
