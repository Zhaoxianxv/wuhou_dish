package com.yfy.wuhoudish_stu;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.yfy.app.login.LoginActivity;
import com.yfy.app.login.UserBaseActivity;
import com.yfy.app.login.UserRes;
import com.yfy.app.net.ReqBody;
import com.yfy.app.net.ReqEnv;
import com.yfy.app.net.ResBody;
import com.yfy.app.net.ResEnv;
import com.yfy.app.net.RetrofitGenerator;
import com.yfy.app.net.login.UserLogoutReq;
import com.yfy.app.net.wuhou.GetMyScoreReq;
import com.yfy.app.net.wuhou.GetSchoolReq;
import com.yfy.app.net.wuhou.GetScoreReq;
import com.yfy.app.net.wuhou.GetStuListReq;
import com.yfy.base.Base;
import com.yfy.base.activity.BaseActivity;
import com.yfy.charting_mp.animation.Easing;
import com.yfy.charting_mp.charts.PieChart;
import com.yfy.charting_mp.charts.RadarChart;
import com.yfy.charting_mp.components.Legend;
import com.yfy.charting_mp.components.XAxis;
import com.yfy.charting_mp.components.YAxis;
import com.yfy.charting_mp.data.Entry;
import com.yfy.charting_mp.data.PieData;
import com.yfy.charting_mp.data.PieDataSet;
import com.yfy.charting_mp.data.RadarData;
import com.yfy.charting_mp.data.RadarDataSet;
import com.yfy.charting_mp.utils.ColorTemplate;
import com.yfy.charting_mp.utils.PercentFormatter;
import com.yfy.charting_mp.zxxtest.custom.MyMarkerView;
import com.yfy.db.GreenDaoManager;
import com.yfy.db.UserPreferences;
import com.yfy.dialog.CPWBean;
import com.yfy.dialog.CPWListView;
import com.yfy.dialog.ConfirmContentWindow;
import com.yfy.final_tag.AppLess;
import com.yfy.final_tag.ColorRgbUtil;
import com.yfy.final_tag.ConvertObjtect;
import com.yfy.final_tag.StringJudge;
import com.yfy.final_tag.StringUtils;
import com.yfy.final_tag.TagFinal;
import com.yfy.glide.GlideTools;
import com.yfy.jpush.Logger;
import com.yfy.wuhoudish.adapter.MainAdapter;
import com.yfy.wuhoudish.R;
import com.yfy.wuhoudish.bean.SchoolRes;
import com.yfy.wuhoudish.bean.ScoreBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainStuActivity extends BaseActivity  implements Callback<ResEnv> {
    private static final String TAG = MainStuActivity.class.getSimpleName();
    @Bind(R.id.main_user_head)
    ImageView user_head;
    @Bind(R.id.main_user_name)
    TextView user_name;
    @Bind(R.id.main_user_one)
    TextView user_one;
    @Bind(R.id.main_user_two)
    TextView user_two;
    @Bind(R.id.main_top_right)
    TextView top_right;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_stu);
        initContentDialog();
        top_right.setVisibility(View.VISIBLE);
        top_right.setText(R.string.not_choose_type);
        initDialog();
        initView();
        getMyScore();
    }


    private Typeface tf;
    private RadarChart mChart;
    private void initView(){
        GlideTools.chanc(mActivity, Base.user.getHeadpic(),R.drawable.order_user_name, user_head);
        user_name.setText(StringUtils.getTextJoint("用户名: %1$s",Base.user.getRealname()));
        user_one.setText(StringUtils.getTextJoint("职能: %1$s",Base.user.getTerm()));
        user_two.setText(StringUtils.getTextJoint("所属: %1$s",Base.user.getSchoolname()));


        mChart = (RadarChart) findViewById(R.id.stu_main_pie);

        tf = Typeface.createFromAsset(getAssets(), "OpenSans-Regular.ttf");

        mChart.setDescription("");
        mChart.setTouchEnabled(false);

        mChart.setWebLineWidth(1.5f);
        mChart.setWebLineWidthInner(0.75f);
        mChart.setWebAlpha(100);

        mChart.setWebColor(Color.WHITE);
        mChart.setWebColorInner(Color.WHITE);
        // create a custom MarkerView (extend MarkerView) and specify the layout
        // to use for it
        MyMarkerView mv = new MyMarkerView(this, R.layout.custom_marker_view);

        // set the marker to the chart
        mChart.setMarkerView(mv);


        XAxis xAxis = mChart.getXAxis();
        xAxis.setTypeface(tf);
        xAxis.setTextSize(9f);
        xAxis.setTextColor(Color.WHITE);

        YAxis yAxis = mChart.getYAxis();
        yAxis.setTypeface(tf);
        yAxis.setLabelCount(5, false);
        yAxis.setTextSize(9f);
        yAxis.setStartAtZero(true);
        yAxis.setTextColor(Color.WHITE);

        Legend l = mChart.getLegend();
        l.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);
        l.setTypeface(tf);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(5f);
    }
    public void setData() {
        ArrayList<Entry> yVals1 = new ArrayList<Entry>();
        ArrayList<Entry> yVals2 = new ArrayList<Entry>();
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
        data.setValueTypeface(tf);
        data.setValueTextSize(8f);
        data.setDrawValues(false);
        data.setValueTextColor(Color.WHITE);

        mChart.setData(data);

        mChart.invalidate();
    }
    @OnClick(R.id.main_user_head)
    void setHead(){
        Intent intent=new Intent(mActivity,LoginActivity.class);
        startActivity(intent);
    }
    @OnClick(R.id.main_top_left)
    void setLogout(){
        confirmContentWindow.setTitle_s("提示",StringUtils.getTextJoint("是否!%1$s","退出登录"),"确定");
        confirmContentWindow.showAtCenter();
    }

    private ConfirmContentWindow confirmContentWindow;
    private void initContentDialog(){
        confirmContentWindow = new ConfirmContentWindow(mActivity);
        confirmContentWindow.setOnPopClickListenner(new ConfirmContentWindow.OnPopClickListenner() {
            @Override
            public void onClick(View view) {

                switch (view.getId()){
                    case R.id.pop_dialog_title:
                        break;
                    case R.id.pop_dialog_content:
                        break;
                    case R.id.pop_dialog_ok:
                        logout();
                        break;
                }
            }
        });
    }


    /**
     * ----------------------------retrofit-----------------------
     */





    private void getMyScore() {

        ReqEnv evn = new ReqEnv();
        ReqBody reqBody = new ReqBody();
        GetMyScoreReq request = new GetMyScoreReq();
        //获取参数

        reqBody.getMyScoreReq = request;
        evn.body = reqBody;
        Call<ResEnv> call = RetrofitGenerator.getWeatherInterfaceApi().get_my_score(evn);
        call.enqueue(this);
        showProgressDialog("");
        Logger.e(evn.toString());

    }




    private void logout() {
        String apikey= JPushInterface.getRegistrationID(mActivity);
        if(apikey==null){
            apikey="";
        }

        ReqEnv evn = new ReqEnv();
        ReqBody reqBody = new ReqBody();
        UserLogoutReq request = new UserLogoutReq();
        //获取参数
        request.setApikey(apikey);

        reqBody.userLogoutReq = request;
        evn.body = reqBody;
        Call<ResEnv> call = RetrofitGenerator.getWeatherInterfaceApi().login_out(evn);
        call.enqueue(this);
        showProgressDialog("");

    }


    @Override
    public void onResponse(Call<ResEnv> call, Response<ResEnv> response) {
        if (response.code()==500){
            toastShow("数据出差了");
        }
        if (!isActivity())return;
        dismissProgressDialog();

        List<String> names=StringUtils.getListToString(call.request().headers().toString().trim(), "/");
        String name=names.get(names.size()-1);
        ResEnv respEnvelope = response.body();
        if (respEnvelope != null) {
            ResBody b=respEnvelope.body;

            if (b.userLogoutRes !=null){
                String result=b.userLogoutRes.result;
                Logger.e(StringUtils.getTextJoint("%1$s:\n%2$s",name,result));
                UserRes res=gson.fromJson(result,UserRes.class);
                if (res.getResult().equals(TagFinal.TRUE)){
                    toastShow("已注销");
                    Base.user=null;
                    initView();
                    GreenDaoManager.getInstance().clearUser();
                    UserPreferences.getInstance().clearUserData();
                    Intent intent=new Intent(mActivity,LoginActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    toastShow(res.getError_code());
                }
            }

            if (b.getMyScoreRes!=null){
                String result=b.getMyScoreRes.result;
                Logger.e(StringUtils.getTextJoint("%1$s:\n%2$s",name,result));
                SchoolRes res=gson.fromJson(result,SchoolRes.class);
                if (StringJudge.isEmpty(res.getError_code())){
                    initData(res);
                }else{
                    toast(res.getError_code());
                }
            }
        }else{
            Logger.e(name+"---ResEnv:null");
            switch (name){
                case TagFinal.USER_LOGOUT:
                    toastShow("已注销");
                    Base.user=null;
                    GreenDaoManager.getInstance().clearUser();
                    UserPreferences.getInstance().clearUserData();
                    Intent intent=new Intent(mActivity,LoginActivity.class);
                    startActivity(intent);
                    finish();
                    break;
            }
        }

    }

    @Override
    public void onFailure(Call<ResEnv> call, Throwable t) {
        if (!isActivity())return;
        dismissProgressDialog();
        Logger.e( "onFailure  "+call.request().headers().toString() );
        toast(R.string.fail_do_not);

    }

    private List<TermBean> termBeanList =new ArrayList<>();
    private void initData(SchoolRes res){
        for (TermBean termBean:res.getTermlist()){
            termBeanList.add(termBean);
        }

        if (StringJudge.isNotEmpty(termBeanList)){
            initData();
        }
    }

    @Override
    public boolean isActivity() {
        return AppLess.isTopActivy(TAG);
    }




    @OnClick(R.id.main_top_right)
    void setTop_right(){
        if (StringJudge.isEmpty(txts)){
            toast("沒有数据");
            return;
        }
        cpwListView.showAtCenter();
    }


    private List<ScoreBean> scoreBeanList=new ArrayList<>();
    private CPWListView cpwListView;
    List<CPWBean> txts=new ArrayList<>();
    private void initData(){
        txts.clear();
        for(TermBean s:termBeanList){
            txts.add(new CPWBean(s.getTermname(), s.getTermid()));
        }
        closeKeyWord();
        cpwListView.setDatas(txts);
        cpwListView.showAtCenter();
    }
    private void initDialog(){
        cpwListView = new CPWListView(mActivity);
        cpwListView.setOnPopClickListenner(new CPWListView.OnPopClickListenner() {
            @Override
            public void onClick(int index) {
//                base_name=termBeanList.get(index).get()
                top_right.setVisibility(View.VISIBLE);
                top_right.setText(txts.get(index).getName());
                scoreBeanList=termBeanList.get(index).getScorelist();
                setData();
                artlist=termBeanList.get(index).getArtscorelist();
                cpwListView.dismiss();

            }
        });
    }


    private String base_name="";


    @OnClick(R.id.oa_base_one_layout)
    void setBase(){
        Intent intent=new Intent(mActivity,UserBaseActivity.class);
        intent.putExtra(Base.type, true);
        startActivity(intent);
    }

    @OnClick(R.id.oa_base_nine_layout)
    void setSt(){

        if (StringJudge.isEmpty(scoreBeanList)){
            toast("未选择学期");
            return;
        }
        for (ScoreBean scoreBean:scoreBeanList){

            if (scoreBean.getCoursename().equalsIgnoreCase("体育")){
                Intent intent = new Intent(mActivity, UserBaseActivity.class);
                intent.putExtra(Base.type, false);
                intent.putExtra(Base.data, scoreBean);
                startActivity(intent);
            }
        }


    }
    @OnClick(R.id.oa_base_four_layout)
    void setGrade(){
        if (StringJudge.isEmpty(scoreBeanList)){
            toast("未选择学期");
            return;
        }

        Intent intent=new Intent(mActivity,StuGradeActivity.class);
        intent.putParcelableArrayListExtra(Base.data, (ArrayList<? extends Parcelable>) scoreBeanList);
        intent.putExtra(Base.name, "成绩");
        startActivity(intent);

    }
    private List<ArtBean> artlist=new ArrayList();
    @OnClick(R.id.oa_base_five_layout)
    void setArt(){
        if (StringJudge.isEmpty(artlist)){
            toast("未选择学期");
            return;
        }
        Intent intent=new Intent(mActivity,StuArtActivity.class);
        intent.putParcelableArrayListExtra(Base.data, (ArrayList<? extends Parcelable>) artlist);
        intent.putExtra(Base.name, "艺体");
        startActivity(intent);
    }
}
