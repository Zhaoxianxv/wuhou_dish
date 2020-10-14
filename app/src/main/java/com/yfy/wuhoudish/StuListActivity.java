package com.yfy.wuhoudish;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.yfy.app.net.ReqBody;
import com.yfy.app.net.ReqEnv;
import com.yfy.app.net.ResBody;
import com.yfy.app.net.ResEnv;
import com.yfy.app.net.RetrofitGenerator;
import com.yfy.app.net.wuhou.GetStuListReq;
import com.yfy.base.Base;
import com.yfy.base.activity.BaseActivity;
import com.yfy.final_tag.AppLess;
import com.yfy.final_tag.ColorRgbUtil;
import com.yfy.final_tag.StringUtils;
import com.yfy.final_tag.TagFinal;
import com.yfy.jpush.Logger;
import com.yfy.recycerview.RecycleViewDivider;
import com.yfy.wuhoudish.adapter.StuListAdapter;
import com.yfy.wuhoudish.bean.SchoolRes;
import com.yfy.wuhoudish.bean.StuBean;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StuListActivity extends BaseActivity implements Callback<ResEnv> {

    private static final String TAG = StuListActivity.class.getSimpleName();
    private StuListAdapter adapter;
    private List<StuBean> mainBeens=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.swip_recycler_main);


        initRecycler();

        getData();
    }

    private String school_name="",school_id="";
    private void getData(){
        Intent intent=getIntent();
        school_name=intent.getStringExtra(Base.name);
        school_id=intent.getStringExtra(Base.id);
        initSQToolbar(school_name);
        getStulist(true);
    }

    public void initSQToolbar(String title){
        assert toolbar!=null;
        toolbar.setTitle(title);

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
                getStulist(false);
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
        adapter=new StuListAdapter(mActivity);
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


//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode==RESULT_OK){
//            switch (requestCode){
//                case TagFinal.UI_REFRESH:
//                    refresh(false,TagFinal.REFRESH);
//                    break;
//            }
//        }
//    }


    /**
     * ----------------------------retrofit---------------
     */



    private void getStulist(boolean is) {
        //登陆时传的Constants.APP_ID：

        ReqEnv evn = new ReqEnv();
        ReqBody reqBody = new ReqBody();
        GetStuListReq request = new GetStuListReq();
        //获取参数

        request.setSchoolid(school_id);
        reqBody.getStuListReq = request;
        evn.body = reqBody;
        Call<ResEnv> call = RetrofitGenerator.getWeatherInterfaceApi().get_stu_list(evn);
        call.enqueue(this);
        if (is)showProgressDialog("");
        Logger.e(evn.toString());

    }





    @Override
    public void onResponse(Call<ResEnv> call, Response<ResEnv> response) {
        if (!isActivity())return;
        dismissProgressDialog();
        closeSwipeRefresh();
        if (response.code()==500){
            toast("数据出差了");
        }
        ResEnv respEnvelope = response.body();
        List<String> names=StringUtils.getListToString(call.request().headers().toString().trim(), "/");
        String name=names.get(names.size()-1);
        if (respEnvelope != null) {
            ResBody b=respEnvelope.body;
            if (b.getStuListRes!=null){
                String result=b.getStuListRes.result;
                Logger.e(StringUtils.getTextJoint("%1$s:\n%2$s",name,result));
                SchoolRes res=gson.fromJson(result,SchoolRes.class);
                adapter.setDataList(res.getStulist());
                adapter.setLoadState(TagFinal.LOADING_END);
            }
        }else{
            Logger.e(name+"---ResEnv:null");
        }
    }

    @Override
    public void onFailure(Call<ResEnv> call, Throwable t) {
        if (!isActivity())return;
        Logger.e("onFailure  :"+call.request().headers().toString());
        dismissProgressDialog();
        closeSwipeRefresh();
        toast(R.string.fail_do);
    }


    @Override
    public boolean isActivity() {
        return AppLess.isTopActivy(TAG);
    }
}
