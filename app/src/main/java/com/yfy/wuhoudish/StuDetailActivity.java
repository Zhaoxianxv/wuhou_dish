package com.yfy.wuhoudish;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.yfy.app.net.ReqBody;
import com.yfy.app.net.ReqEnv;
import com.yfy.app.net.ResBody;
import com.yfy.app.net.ResEnv;
import com.yfy.app.net.RetrofitGenerator;
import com.yfy.app.net.tea_evaluate.JudgeChartReq;
import com.yfy.app.net.wuhou.GetScoreReq;
import com.yfy.base.Base;
import com.yfy.base.activity.BaseActivity;
import com.yfy.dialog.CPWBean;
import com.yfy.dialog.CPWListView;
import com.yfy.final_tag.AppLess;
import com.yfy.final_tag.ColorRgbUtil;
import com.yfy.final_tag.StringJudge;
import com.yfy.final_tag.StringUtils;
import com.yfy.final_tag.TagFinal;
import com.yfy.jpush.Logger;
import com.yfy.recycerview.RecycleViewDivider;
import com.yfy.view.SQToolBar;
import com.yfy.wuhoudish.adapter.StuDetailAdapter;
import com.yfy.wuhoudish.bean.Motbean;
import com.yfy.wuhoudish.bean.SchoolRes;
import com.yfy.wuhoudish.bean.ScoreBean;
import com.yfy.wuhoudish_stu.ArtBean;
import com.yfy.wuhoudish_stu.StuArtAdapter;
import com.yfy.wuhoudish_stu.TermBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StuDetailActivity extends BaseActivity implements Callback<ResEnv> {
    private static final String TAG = StuDetailActivity.class.getSimpleName();


    private StuDetailAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.swip_recycler_main);
        initRecycler();
        initDialog();
        getData();
    }

    private String user_name="",user_id="";
    private void getData(){
        Intent intent=getIntent();
        user_name=intent.getStringExtra(Base.name);
        user_id=intent.getStringExtra(Base.id);
//        motbeanList=intent.getParcelableArrayListExtra(Base.data);
        initSQToolbar(user_name);


        getScore();
    }
    private TextView top_right;
    private void initSQToolbar(String title){
        assert toolbar!=null;
        toolbar.setTitle(title);
        top_right=toolbar.addMenuText(TagFinal.ONE_INT, R.string.not_choose_type);
        toolbar.setOnMenuClickListener(new SQToolBar.OnMenuClickListener() {
            @Override
            public void onClick(View view, int position) {
                if (StringJudge.isEmpty(txts)){
                    toast("沒有數據");
                    return;
                }
                cpwListView.showAtCenter();
            }
        });
    }



    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    public void initRecycler(){

        recyclerView = (RecyclerView) findViewById(R.id.public_recycler);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.public_swip);
        //AppBarLayout 展开执行刷新
        // 设置刷新控件颜色
        swipeRefreshLayout.setColorSchemeColors(Color.parseColor("#4DB6AC"));
        // 设置下拉刷新
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // 刷新数据
//                getStulist(false);
                closeSwipeRefresh();
            }
        });


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        xlist.setLayoutManager(new GridLayoutManager(this, 1));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //添加分割线
        recyclerView.addItemDecoration(new RecycleViewDivider(
                mActivity,
                LinearLayoutManager.HORIZONTAL,
                1,
                ColorRgbUtil.getGainsboro()));
        adapter=new StuDetailAdapter(mActivity);
        recyclerView.setAdapter(adapter);

    }





    public void closeSwipeRefresh(){
        if (swipeRefreshLayout!=null){
            swipeRefreshLayout.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (swipeRefreshLayout != null && swipeRefreshLayout.isRefreshing()) {
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }
            }, 200);
        }
    }


    /**
     * ----------------------------retrofit-----------------------
     */




    private void getScore() {
        //登陆时传的Constants.APP_ID：

        ReqEnv evn = new ReqEnv();
        ReqBody reqBody = new ReqBody();
        GetScoreReq request = new GetScoreReq();
        //获取参数
        request.setStuid(user_id);
        reqBody.getScoreReq= request;
        evn.body = reqBody;
        Call<ResEnv> call = RetrofitGenerator.getWeatherInterfaceApi().get_stu_score(evn);
        call.enqueue(this);
        showProgressDialog("");
        Logger.e(evn.toString());

    }


    @Override
    public void onResponse(Call<ResEnv> call, Response<ResEnv> response) {
        if (!isActivity())return;
        dismissProgressDialog();
        closeSwipeRefresh();
        if (response.code()==500){
            toastShow("数据出差了");
        }
        ResEnv respEnvelope = response.body();
        List<String> names=StringUtils.getListToString(call.request().headers().toString().trim(), "/");
        String name=names.get(names.size()-1);

        if (respEnvelope != null) {
            ResBody b = respEnvelope.body;
            if (b.getScoreRes!=null){
                String result=b.getScoreRes.result;
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
        }
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


    private List<ArtBean> artlist=new ArrayList();
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
                top_right.setText(txts.get(index).getName());
                scoreBeanList=termBeanList.get(index).getScorelist();
//                setData();

                artlist.clear();
                ArtBean art=new ArtBean();
                art.setView_type(TagFinal.TYPE_TOP);
                art.setScoreBeanList(scoreBeanList);
                if (StringJudge.isEmpty(scoreBeanList)){
                    artlist.addAll(termBeanList.get(index).getArtscorelist());
                }else{
                    artlist.add(art);
                    artlist.addAll(termBeanList.get(index).getArtscorelist());
                }
                adapter.setDataList(artlist);
                adapter.setLoadState(TagFinal.LOADING_END);
                cpwListView.dismiss();

            }
        });
    }

    @Override
    public void onFailure(Call<ResEnv> call, Throwable t) {
        if (!isActivity())return;
        Logger.e("onFailure "+call.request().headers().toString());
        dismissProgressDialog();
        closeSwipeRefresh();
        toastShow(R.string.fail_do_not);
    }


    @Override
    public boolean isActivity() {
        return AppLess.isTopActivy(TAG);
    }
}
