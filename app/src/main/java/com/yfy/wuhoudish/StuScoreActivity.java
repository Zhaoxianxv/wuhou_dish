package com.yfy.wuhoudish;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

import com.yfy.app.net.ReqBody;
import com.yfy.app.net.ReqEnv;
import com.yfy.app.net.ResBody;
import com.yfy.app.net.ResEnv;
import com.yfy.app.net.RetrofitGenerator;
import com.yfy.app.net.tea_evaluate.JudgeChartReq;
import com.yfy.base.Base;
import com.yfy.base.activity.BaseActivity;
import com.yfy.charting_mp.animation.Easing;
import com.yfy.charting_mp.charts.HorizontalBarChart;
import com.yfy.charting_mp.charts.PieChart;
import com.yfy.charting_mp.components.Legend;
import com.yfy.charting_mp.components.XAxis;
import com.yfy.charting_mp.components.YAxis;
import com.yfy.charting_mp.data.BarData;
import com.yfy.charting_mp.data.BarDataSet;
import com.yfy.charting_mp.data.BarEntry;
import com.yfy.charting_mp.data.Entry;
import com.yfy.charting_mp.data.PieData;
import com.yfy.charting_mp.data.PieDataSet;
import com.yfy.charting_mp.highlight.Highlight;
import com.yfy.charting_mp.listener.OnChartValueSelectedListener;
import com.yfy.charting_mp.utils.ColorTemplate;
import com.yfy.charting_mp.utils.PercentFormatter;
import com.yfy.final_tag.AppLess;
import com.yfy.final_tag.ColorRgbUtil;
import com.yfy.final_tag.ConvertObjtect;
import com.yfy.final_tag.StringJudge;
import com.yfy.final_tag.StringUtils;
import com.yfy.jpush.Logger;
import com.yfy.wuhoudish.bean.Motbean;
import com.yfy.wuhoudish.bean.ScoreBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StuScoreActivity extends BaseActivity implements Callback<ResEnv> {
    private static final String TAG = StuScoreActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stu_score_main);
//        initView();
        getData();
    }

    private List<ScoreBean> motbeanList=new ArrayList<>();
    private String school_name="";
    private void getData(){
        Intent intent=getIntent();
        school_name=intent.getStringExtra(Base.name);
        motbeanList=intent.getParcelableArrayListExtra(Base.data);
        initSQToolbar(school_name);
        initChart();


        if (StringJudge.isNotEmpty(motbeanList)) setData();
    }



    private void initSQToolbar(String title){
        assert toolbar!=null;
        toolbar.setTitle(title);

    }



    private HorizontalBarChart mChart;
    private void initChart(){
        mChart = (HorizontalBarChart) findViewById(R.id.stu_score_chart);
//        mChart.setOnChartValueSelectedListener(this);
        // mChart.setHighlightEnabled(false);

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

//        tf = Typeface.createFromAsset(getAssets(), "OpenSans-Regular.ttf");

        XAxis xl = mChart.getXAxis();
        xl.setPosition(XAxis.XAxisPosition.BOTTOM);
//        xl.setTypeface(tf);
        xl.setDrawAxisLine(true);
        xl.setDrawGridLines(true);
        xl.setGridLineWidth(0.3f);

        YAxis yl = mChart.getAxisLeft();
//        yl.setTypeface(tf);
        yl.setDrawAxisLine(true);
        yl.setDrawGridLines(true);
        yl.setGridLineWidth(0.3f);
//        yl.setInverted(true);

        YAxis yr = mChart.getAxisRight();
//        yr.setTypeface(tf);
        yr.setDrawAxisLine(true);
        yr.setDrawGridLines(false);
//        yr.setInverted(true);


        mChart.animateY(2500);



        Legend l = mChart.getLegend();
        l.setPosition(Legend.LegendPosition.BELOW_CHART_LEFT);
        l.setFormSize(8f);
        l.setXEntrySpace(4f);
    }


    private void setData() {

        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();
        ArrayList<BarEntry> yVals2 = new ArrayList<BarEntry>();
        ArrayList<BarDataSet> yDatas = new ArrayList<BarDataSet>();
        int i=0;
        for (ScoreBean score:motbeanList) {
            yVals1.add(new BarEntry(ConvertObjtect.getInstance().getFloat(score.getScores().get(0).getExamscore()), i));
            yVals2.add(new BarEntry(ConvertObjtect.getInstance().getFloat(score.getScores().get(0).getExamscore()), i));
            i++;
        }

        BarDataSet set = new BarDataSet(yVals1, motbeanList.get(0).getScores().get(0).getExamname());
        set.setColor(Color.rgb(104, 241, 175));
        set.setDrawValues(false);
        set.setBarSpacePercent(0f);
        BarDataSet set2 = new BarDataSet(yVals2, motbeanList.get(0).getScores().get(1).getExamname());
        set2.setColor(Color.rgb(164, 228, 251));
        set2.setDrawValues(false);
        set2.setBarSpacePercent(0f);

        yDatas.add(set);
        yDatas.add(set2);


        BarData cd = new BarData(getXdatas(), yDatas);

        mChart.setData(cd);
    }

    private ArrayList<String> getXdatas() {

        ArrayList<String> m = new ArrayList<String>();
        for (ScoreBean score:motbeanList) {
            m.add(score.getCoursename());
        }

        return m;
    }
    /**
     * ----------------------------retrofit-----------------------
     */




    public void getChartData(boolean is,int year)  {

        ReqEnv reqEnvelop = new ReqEnv();
        ReqBody reqBody = new ReqBody();
        JudgeChartReq request = new JudgeChartReq();
        //获取参数

        request.setYear(year);
        reqBody.judgechartReq = request;
        reqEnvelop.body = reqBody;

        Call<ResEnv> call = RetrofitGenerator.getWeatherInterfaceApi().judge_statistics(reqEnvelop);
        call.enqueue(this);

    }

    @Override
    public void onResponse(Call<ResEnv> call, Response<ResEnv> response) {
        if (!isActivity())return;
        dismissProgressDialog();
        if (response.code()==500){
            toastShow("数据出差了");
        }
        ResEnv respEnvelope = response.body();

        List<String> names=StringUtils.getListToString(call.request().headers().toString().trim(), "/");
        String name=names.get(names.size()-1);

        if (respEnvelope != null) {
            ResBody b = respEnvelope.body;
            if (b.year_Response != null) {
                String result = b.year_Response.year_Result;
                Logger.e(StringUtils.getTextJoint("%1$s:\n%2$s",name,result));

//                ResultInfo infor=gson.fromJson(result, ResultInfo.class);
//                if (StringJudge.isNotNull(infor)){
//                    for (YearData bean:infor.getJudge_year()){
//                        if (bean.getIsnow().equals("是")){
//                            Variables.year=bean.getYear();
//                            Variables.year_name=bean.getYearname();
//                            oneMenu.setText(bean.getYearname());
//                            year= ConvertObjtect.getInstance().getInt(Variables.year);
//                            getChartData(true, year);
//                            break;
//                        }
//                    }
//
//                }
            }
            if (b.judge_chart_Response != null) {
//                String result = b.judge_chart_Response.judge_statisticsResult;
//                Logger.e("judge_chart_Response: " + result);
//                ResultInfo info=gson.fromJson(result, ResultInfo.class);
//                List<ChartInfo> chartinfo=info.getJudge_statistics();
//                if (StringJudge.isEmpty(chartinfo)){
//                    toastShow(R.string.not_have_data);
//                }else{
//                    initSetData(chartinfo.get(0).getInfo());
//                }

            }
        }else{
            Logger.e(name+"---ResEnv:null");
        }
    }

//    private List<ChartBean> adapterData=new ArrayList<>();
//    private void initSetData(List<ChartBean> list){
//        adapterData.clear();
//        for (ChartBean bean:list){
//            bean.setView_type(TagFinal.TYPE_ITEM);
//            adapterData.add(bean);
//        }
//        adapter.setDataList(adapterData);
//        adapter.setLoadState(TagFinal.LOADING_COMPLETE);
//    }
    @Override
    public void onFailure(Call<ResEnv> call, Throwable t) {
        if (!isActivity())return;
        Logger.e("onFailure "+call.request().headers().toString());
        dismissProgressDialog();
        toastShow(R.string.fail_do_not);
    }


    @Override
    public boolean isActivity() {
        return AppLess.isTopActivy(TAG);
    }
}
