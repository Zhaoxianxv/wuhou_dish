package com.yfy.wuhoudish.adapter;

import android.app.Activity;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yfy.base.Base;
import com.yfy.charting_mp.charts.RadarChart;
import com.yfy.charting_mp.components.Legend;
import com.yfy.charting_mp.components.XAxis;
import com.yfy.charting_mp.components.YAxis;
import com.yfy.charting_mp.data.Entry;
import com.yfy.charting_mp.data.RadarData;
import com.yfy.charting_mp.data.RadarDataSet;
import com.yfy.charting_mp.utils.ColorTemplate;
import com.yfy.charting_mp.zxxtest.custom.MyMarkerView;
import com.yfy.final_tag.ConvertObjtect;
import com.yfy.final_tag.StringJudge;
import com.yfy.final_tag.StringUtils;
import com.yfy.final_tag.TagFinal;
import com.yfy.glide.GlideTools;
import com.yfy.wuhoudish.R;
import com.yfy.wuhoudish.bean.ScoreBean;
import com.yfy.wuhoudish_stu.ArtBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yfyandr on 2017/12/27.
 */

public class StuDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ArtBean> dataList;
    private Activity mContext;
    public void setDataList(List<ArtBean> dataList) {

        this.dataList = dataList;
//        getRandomHeights(dataList);
    }

    // 当前加载状态，默认为加载完成
    private int loadState = 2;


    public StuDetailAdapter(Activity mContext) {
        this.mContext=mContext;
        this.dataList = new ArrayList<>();

    }

    @Override
    public int getItemViewType(int position) {
        // 最后一个item设置为FooterView
        return dataList.get(position).getView_type();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //进行判断显示类型，来创建返回不同的View
        if (viewType == TagFinal.TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.selected_singe_item_layout, parent, false);
            return new RecyclerViewHolder(view);

        }
        if (viewType == TagFinal.TYPE_TOP) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.stu_detail_top, parent, false);
            return new TopHolder(view);

        }
        return null;
    }



    private int heigh;
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof RecyclerViewHolder) {
            RecyclerViewHolder reHolder = (RecyclerViewHolder) holder;
            reHolder.bean=dataList.get(position);
            reHolder.key.setText(reHolder.bean.getArtname());
            reHolder.value.setVisibility(View.VISIBLE);
            if (reHolder.bean.getValue().equalsIgnoreCase("未打分")){
                reHolder.value.setText(reHolder.bean.getValue());
            }else{
                reHolder.value.setText(StringUtils.getTextJoint("得分：%1$s",reHolder.bean.getValue()));
            }
        }
        if (holder instanceof TopHolder) {
            TopHolder reHolder = (TopHolder) holder;
            if (StringJudge.isEmpty(dataList.get(position).getScoreBeanList())){
                   reHolder.layout.setVisibility(View.GONE);
            }else{
                reHolder.layout.setVisibility(View.VISIBLE);
                reHolder.initView();
                reHolder.setData(dataList.get(position).getScoreBeanList());
            }
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    private class RecyclerViewHolder extends RecyclerView.ViewHolder {

        TextView key;
        TextView value;
        ArtBean bean;
        RecyclerViewHolder(View itemView) {
            super(itemView);
            key= (TextView) itemView.findViewById(R.id.selected_item_name);
            value= (TextView) itemView.findViewById(R.id.selected_item_type);

        }
    }
    private class TopHolder extends RecyclerView.ViewHolder {

        RadarChart mChart;
        RelativeLayout layout;

        TopHolder(View itemView) {
            super(itemView);
            mChart=  itemView.findViewById(R.id.stu_main_pie);
            layout=  itemView.findViewById(R.id.stu_main_pie_layout);


        }

        private void initView(){



            mChart.setDescription("");
            mChart.setTouchEnabled(false);

            mChart.setWebLineWidth(1.5f);
            mChart.setWebLineWidthInner(0.75f);
            mChart.setWebAlpha(100);

            // create a custom MarkerView (extend MarkerView) and specify the layout
            // to use for it
            MyMarkerView mv = new MyMarkerView(mContext, R.layout.custom_marker_view);

            // set the marker to the chart
            mChart.setMarkerView(mv);


            XAxis xAxis = mChart.getXAxis();
            xAxis.setTextSize(9f);

            YAxis yAxis = mChart.getYAxis();
            yAxis.setLabelCount(5, false);
            yAxis.setTextSize(9f);
            yAxis.setStartAtZero(true);

            Legend l = mChart.getLegend();
            l.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);
            l.setXEntrySpace(7f);
            l.setYEntrySpace(5f);
        }
        public void setData(List<ScoreBean> scoreBeanList) {
            ArrayList<Entry> yVals1 = new ArrayList<Entry>();
            ArrayList<Entry> yVals2 = new ArrayList<Entry>();
            // IMPORTANT: In a PieChart, no values (Entry) should have the same
            // xIndex (even if from different DataSets), since no values can be
            // drawn above each other.
            for (int i = 0; i < scoreBeanList.size(); i++) {
                yVals1.add(new Entry((float) ConvertObjtect.getInstance().getFloat(scoreBeanList.get(i).getScores().get(0).getExamscore()), i));
            }

            for (int i = 0; i < scoreBeanList.size(); i++) {
                yVals2.add(new Entry((float) ConvertObjtect.getInstance().getFloat(scoreBeanList.get(i).getScores().get(1).getExamscore()), i));
            }

            ArrayList<String> xVals = new ArrayList<String>();

            for (int i = 0; i < scoreBeanList.size(); i++)
                xVals.add(scoreBeanList.get(i).getCoursename());

            RadarDataSet set1 = new RadarDataSet(yVals1, scoreBeanList.get(0).getScores().get(0).getExamname());
            set1.setColor(ColorTemplate.VORDIPLOM_COLORS[0]);
            set1.setDrawFilled(true);
            set1.setLineWidth(2f);

            RadarDataSet set2 = new RadarDataSet(yVals2, scoreBeanList.get(0).getScores().get(1).getExamname());
            set2.setColor(ColorTemplate.VORDIPLOM_COLORS[4]);
            set2.setDrawFilled(true);
            set2.setLineWidth(2f);

            ArrayList<RadarDataSet> sets = new ArrayList<RadarDataSet>();
            sets.add(set1);
            sets.add(set2);

            RadarData data = new RadarData(xVals, sets);
            data.setValueTextSize(8f);
            data.setDrawValues(false);

            mChart.setData(data);

            mChart.invalidate();
        }
    }



    /**
     * 设置上拉加载状态
     *
     * @param loadState 1.正在加载 2.加载完成 3.加载到底
     */
    public void setLoadState(int loadState) {
        this.loadState = loadState;
        notifyDataSetChanged();
    }

}
