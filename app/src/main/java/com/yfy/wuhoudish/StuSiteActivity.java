package com.yfy.wuhoudish;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import com.yfy.app.net.ReqBody;
import com.yfy.app.net.ReqEnv;
import com.yfy.app.net.ResBody;
import com.yfy.app.net.ResEnv;
import com.yfy.app.net.RetrofitGenerator;
import com.yfy.app.net.tea_evaluate.JudgeChartReq;
import com.yfy.app.net.tea_evaluate.YearReq;
import com.yfy.base.Base;
import com.yfy.base.activity.BaseActivity;
import com.yfy.charting_mp.animation.Easing;
import com.yfy.charting_mp.charts.PieChart;
import com.yfy.charting_mp.components.Legend;
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

import java.awt.font.TextAttribute;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StuSiteActivity extends BaseActivity implements Callback<ResEnv> {
    private static final String TAG = StuSiteActivity.class.getSimpleName();


    @Bind(R.id.stu_site_main_tag)
    TextView maintag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stu_site_main);
        initView();
        getData();
    }

    private List<Motbean> motbeanList=new ArrayList<>();
    private String school_name="",tag="";
    private void getData(){
        Intent intent=getIntent();
        school_name=intent.getStringExtra(Base.name);
        tag=intent.getStringExtra(Base.tag);
        motbeanList=intent.getParcelableArrayListExtra(Base.data);
        initSQToolbar(school_name);


        maintag.setText(tag);
        if (StringJudge.isNotEmpty(motbeanList))setData();
    }
    private void initSQToolbar(String title){
        assert toolbar!=null;
        toolbar.setTitle(title);
    }


    private PieChart mChart;
    private void initView(){
        mChart=  findViewById(R.id.chart1);
        mChart.setUsePercentValues(true);
        mChart.setDescription("");
        mChart.setDragDecelerationFrictionCoef(0.95f);
        mChart.setDrawHoleEnabled(true);
        mChart.setHoleColorTransparent(true);
        mChart.setTransparentCircleColor(Color.WHITE);
        mChart.setTransparentCircleAlpha(110);
        mChart.setHoleRadius(40f);
        mChart.setTransparentCircleRadius(50f);

        mChart.setDrawCenterText(true);
        mChart.setRotationAngle(0);
        // enable rotation of the chart by touch
        mChart.setRotationEnabled(true);

        // mChart.setUnit(" €");
        // mChart.setDrawUnitsInChart(true);

        // add a selection listener
            mChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
                @Override
                public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
                    Logger.e("onValueSelected");
                }

                @Override
                public void onNothingSelected() {
                    Logger.e("onNothingSelected");
                }
            });





        mChart.animateY(1500, Easing.EasingOption.EaseInOutQuad);
//         mChart.spin(2000, 0, 360);

        Legend l = mChart.getLegend();
        l.setPosition(Legend.LegendPosition.LEFT_OF_CHART);//名称显示位子
        l.setXEntrySpace(7f);
        l.setYEntrySpace(0f);
        l.setYOffset(0f);
    }


    private void setData() {

        float all_score=0f;
        List<Motbean> pieData=new ArrayList<>();
        for (Motbean chartBean:motbeanList){
            if (chartBean.getNation_stu().equals("0")){
                continue;
            }else{
                all_score+=ConvertObjtect.getInstance().getFloat(chartBean.getNation_stu());
                pieData.add(chartBean);
            }
        }

        ArrayList<Entry> yVals1 = new ArrayList<Entry>();
        ArrayList<String> xVals = new ArrayList<String>();

        if (StringJudge.isEmpty(pieData)){
            xVals.add("无数据");
            yVals1.add(new Entry(5f, 0));
        }else{
            for (int i = 0; i < pieData.size(); i++) {
                yVals1.add(new Entry(ConvertObjtect.getInstance().getFloat(pieData.get(i).getNation_stu()), i));
                xVals.add(StringUtils.getTextJoint("%1$s:%2$s",pieData.get(i).getNation_name(),
                        pieData.get(i).getNation_stu()));
            }
        }

        mChart.setCenterText(StringUtils.getTextJoint("总人数:%1$s",StringUtils.getTwoTofloat(all_score)));
        // IMPORTANT: In a PieChart, no values (Entry) should have the same
        // xIndex (even if from different DataSets), since no values can be
        // drawn above each other.

        PieDataSet dataSet = new PieDataSet(yVals1, "");
        dataSet.setSliceSpace(1f);
        dataSet.setSelectionShift(5f);

        // add a lot of colors

        ArrayList<Integer> colors = new ArrayList<Integer>();


        colors.add(ColorRgbUtil.getOne());
        colors.add(ColorRgbUtil.getTwo());
        colors.add(ColorRgbUtil.getThree());
        colors.add(ColorRgbUtil.getFour());
        colors.add(ColorRgbUtil.getFive());
        colors.add(ColorRgbUtil.getSix());
        colors.add(ColorRgbUtil.getSeven());
        colors.add(ColorRgbUtil.getEigth());
        colors.add(ColorRgbUtil.getBaseColor());

        colors.add(ColorTemplate.getHoloBlue());

        dataSet.setColors(colors);

        PieData data = new PieData(xVals, dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(9f);
        data.setValueTextColor(Color.WHITE);
//        data.setValueTypeface(tf);
        mChart.setData(data);

        // undo all highlights
        mChart.highlightValues(null);

        mChart.invalidate();
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
