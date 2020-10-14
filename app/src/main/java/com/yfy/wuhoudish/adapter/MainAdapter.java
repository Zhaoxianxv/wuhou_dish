package com.yfy.wuhoudish.adapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.yfy.base.Base;
import com.yfy.charting_mp.animation.Easing;
import com.yfy.charting_mp.charts.BarChart;
import com.yfy.charting_mp.components.XAxis;
import com.yfy.charting_mp.components.YAxis;
import com.yfy.charting_mp.data.BarData;
import com.yfy.charting_mp.data.BarDataSet;
import com.yfy.charting_mp.data.BarEntry;
import com.yfy.final_tag.ConvertObjtect;
import com.yfy.final_tag.StringUtils;
import com.yfy.final_tag.TagFinal;
import com.yfy.glide.GlideTools;
import com.yfy.wuhoudish.R;
import com.yfy.wuhoudish.StuScoreActivity;
import com.yfy.wuhoudish.StuSiteActivity;
import com.yfy.wuhoudish.bean.SchoolInfo;
import com.yfy.wuhoudish.StuListActivity;
import com.yfy.wuhoudish.bean.ScoreBean;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by yfy1 on 2016/10/17.
 */
public class MainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {



    private List<SchoolInfo> dataList;
    private Activity mContext;

    public void setDataList(List<SchoolInfo> dataList) {
        this.dataList = dataList;
//        getRandomHeights(dataList);
    }


    private int loadState = 2;

    public MainAdapter(Activity mContext){
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
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mian_school_layout, parent, false);
            return new ItemHolder(view);

        }
        if (viewType == TagFinal.TYPE_TOP) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mian_user_layout, parent, false);
            return new UserHolder(view);

        }


        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof UserHolder) {
            UserHolder reHolder = (UserHolder) holder;
            reHolder.bean=dataList.get(position);
            GlideTools.chanc(mContext, Base.user.getHeadpic(),R.drawable.order_user_name, reHolder.user_head);
            reHolder.user_name.setText(StringUtils.getTextJoint("用户名: %1$s",Base.user.getRealname()));
            reHolder.user_ter.setText(StringUtils.getTextJoint("职能: %1$s",Base.user.getTerm()));
            reHolder.user_school.setText(StringUtils.getTextJoint("所属: %1$s",Base.user.getSchoolname()));
        }
        if (holder instanceof ItemHolder) {
            ItemHolder itemHolder = (ItemHolder) holder;
            itemHolder.bean=dataList.get(position);
            itemHolder.school_name.setText(itemHolder.bean.getSchoolname());
            itemHolder.school_name.setTypeface(Base.typeface);
            itemHolder.school_tag.setText(StringUtils.getTextJoint("总数:%1$s 男:%2$s 女:%3$s",
                    itemHolder.bean.getStucount(),itemHolder.bean.getBoycount(),itemHolder.bean.getGirlcount()));

            itemHolder.initView(itemHolder.bean,itemHolder.barChart );

        }

    }

    @Override
    public int getItemCount() {
        return dataList.size() ;
    }





    private class UserHolder extends RecyclerView.ViewHolder {
        TextView user_name;
        ImageView user_head;
        TextView user_school;
        TextView user_ter;
        SchoolInfo bean;

        UserHolder(View itemView) {
            super(itemView);
            user_head = itemView.findViewById(R.id.main_user_head);
            user_name = itemView.findViewById(R.id.main_user_name);
            user_ter = itemView.findViewById(R.id.main_user_one);
            user_school = itemView.findViewById(R.id.main_user_two);

            user_head.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }

    private class ItemHolder extends RecyclerView.ViewHolder {
        TextView school_name;
        TextView school_tag;
        Button button_score;
        Button button_site;
        Button button_list;
        BarChart barChart;
        SchoolInfo bean;

        ItemHolder(View itemView) {
            super(itemView);
            barChart = itemView.findViewById(R.id.main_layout_chart);
            school_name = itemView.findViewById(R.id.main_school_name);
            school_tag = itemView.findViewById(R.id.main_school_tag);
            button_score = itemView.findViewById(R.id.main_scan_score);
            button_list = itemView.findViewById(R.id.main_scan_stu_list);
            button_site = itemView.findViewById(R.id.main_scan_stu_site);
            button_site.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, StuSiteActivity.class);
                    intent.putParcelableArrayListExtra(Base.data, (ArrayList<? extends Parcelable>) bean.getNationlist());
                    intent.putExtra(Base.name,bean.getSchoolname());
                    intent.putExtra(Base.tag,StringUtils.getTextJoint("总数:%1$s 男:%2$s 女:%3$s", bean.getStucount(),bean.getBoycount(),bean.getGirlcount()));
                    mContext.startActivity(intent);
                }
            });
            button_score.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, StuScoreActivity.class);
                    intent.putParcelableArrayListExtra(Base.data, (ArrayList<? extends Parcelable>) bean.getScore());
                    intent.putExtra(Base.name,bean.getSchoolname());
                    intent.putExtra(Base.tag,"");
                    mContext.startActivity(intent);
                }
            });
            button_list.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent=new Intent(mContext,StuListActivity.class);
                    intent.putExtra(Base.name,bean.getSchoolname());
                    intent.putExtra(Base.id,bean.getSchoolid());
                    mContext.startActivity(intent);
                }
            });

        }


        private void initView(SchoolInfo bean, BarChart chart){
            BarData data=generateData(bean);


            data.setValueTextColor(Color.BLACK);
            chart.setDescription("");
            chart.setDrawGridBackground(false);
            chart.setPinchZoom(false);
            chart.setTouchEnabled(false);
            chart.setDoubleTapToZoomEnabled(false);//双击zoom
            chart.getLegend().setEnabled(true);//显示标注说明

            XAxis xAxis = chart.getXAxis();
            xAxis.setPosition(XAxis.XAxisPosition.BOTH_SIDED);
            xAxis.setDrawGridLines(false);
            xAxis.setTextSize(6f);

            YAxis leftAxis = chart.getAxisLeft();
//            leftAxis.setTypeface(mTf);
            leftAxis.setLabelCount(3, false);
            leftAxis.setSpaceTop(15f);
            leftAxis.setDrawGridLines(false);
//            leftAxis.setInverted(true);//反转

            YAxis rightAxis = chart.getAxisRight();
//            rightAxis.setTypeface(mTf);
            rightAxis.setLabelCount(0, false);
            rightAxis.setSpaceTop(15f);
            rightAxis.setDrawGridLines(false);
            rightAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
            // set data
            chart.setData(data);

            // do not forget to refresh the chart
            chart.invalidate();
//            chart.animateY(700, Easing.EasingOption.EaseInCubic);
        }
        private BarData generateData(SchoolInfo bean) {

            ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();
            ArrayList<BarEntry> yVals2 = new ArrayList<BarEntry>();
            ArrayList<BarDataSet> yDatas = new ArrayList<BarDataSet>();
            int i=0;
            for (ScoreBean score:bean.getScore()) {
                yVals1.add(new BarEntry(ConvertObjtect.getInstance().getFloat(score.getScores().get(0).getExamscore()), i));
                yVals2.add(new BarEntry(ConvertObjtect.getInstance().getFloat(score.getScores().get(0).getExamscore()), i));
                i++;
            }

            BarDataSet set = new BarDataSet(yVals1, bean.getScore().get(0).getScores().get(0).getExamname());
            set.setColor(Color.rgb(104, 241, 175));
            set.setDrawValues(false);
            set.setBarSpacePercent(0f);
            BarDataSet set2 = new BarDataSet(yVals2, bean.getScore().get(0).getScores().get(1).getExamname());
            set2.setColor(Color.rgb(164, 228, 251));
            set2.setDrawValues(false);
            set2.setBarSpacePercent(0f);

            yDatas.add(set);
            yDatas.add(set2);


            BarData cd = new BarData(getXdatas(), yDatas);
            return cd;
        }

        private ArrayList<String> getXdatas() {

            ArrayList<String> m = new ArrayList<String>();
            for (ScoreBean score:bean.getScore()) {
                m.add(score.getCoursename());
            }

            return m;
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

    public static final int[] LIBERTY_COLORS = {
            Color.TRANSPARENT
    };
}
