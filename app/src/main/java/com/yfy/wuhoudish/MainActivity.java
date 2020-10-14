package com.yfy.wuhoudish;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.yfy.app.login.LoginActivity;
import com.yfy.app.login.UserRes;
import com.yfy.app.net.ReqBody;
import com.yfy.app.net.ReqEnv;
import com.yfy.app.net.ResBody;
import com.yfy.app.net.ResEnv;
import com.yfy.app.net.RetrofitGenerator;
import com.yfy.app.net.login.UserLogoutReq;
import com.yfy.app.net.wuhou.GetSchoolReq;
import com.yfy.base.Base;
import com.yfy.base.activity.BaseActivity;
import com.yfy.db.GreenDaoManager;
import com.yfy.db.UserPreferences;
import com.yfy.dialog.ConfirmContentWindow;
import com.yfy.final_tag.AppLess;
import com.yfy.final_tag.StringUtils;
import com.yfy.final_tag.TagFinal;
import com.yfy.jpush.Logger;
import com.yfy.wuhoudish.adapter.MainAdapter;
import com.yfy.wuhoudish.bean.SchoolInfo;
import com.yfy.wuhoudish.bean.SchoolRes;

import java.util.ArrayList;
import java.util.List;

import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity  implements Callback<ResEnv> {
    private static final String TAG = MainActivity.class.getSimpleName();


    private MainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initRecycler();
        initData();
        initContentDialog();

        getSchoolList();
    }



    private void initData(){
        SchoolInfo user=new SchoolInfo();
        user.setView_type(TagFinal.TYPE_TOP);
        user.setSession_key(Base.user.getSession_key());
        user.setRealname(Base.user.getRealname());
        user.setHeadpic(Base.user.getHeadpic());
        user.setSchoolname(Base.user.getSchoolname());
        user.setSchoolid(Base.user.getSchoolid());
        user.setTerm(Base.user.getTerm());
        adapter_list.add(user);
        adapter.setDataList(adapter_list);
        adapter.setLoadState(TagFinal.LOADING_COMPLETE);
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
                getSchoolList();
            }
        });


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        xlist.setLayoutManager(new GridLayoutManager(this, 1));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //添加分割线
//        recyclerView.addItemDecoration(new RecycleViewDivider(
//                mActivity,
//                LinearLayoutManager.HORIZONTAL,
//                1,
//                ColorRgbUtil.getGainsboro()));
        adapter=new MainAdapter(mActivity);
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

//
//    private void getStulist() {
//        //登陆时传的Constants.APP_ID：
//
//        ReqEnv evn = new ReqEnv();
//        ReqBody reqBody = new ReqBody();
//        GetStuListReq request = new GetStuListReq();
//        //获取参数
//
//        request.setSchoolid("");
//        reqBody.getStuListReq = request;
//        evn.body = reqBody;
//        Call<ResEnv> call = RetrofitGenerator.getWeatherInterfaceApi().get_stu_list(evn);
//        call.enqueue(this);
//        showProgressDialog("");
//        Logger.e(evn.toString());
//
//    }


    private void getSchoolList() {
        //登陆时传的Constants.APP_ID：

        ReqEnv evn = new ReqEnv();
        ReqBody reqBody = new ReqBody();
        GetSchoolReq request = new GetSchoolReq();
        //获取参数

        reqBody.getSchoolReq= request;
        evn.body = reqBody;
        Call<ResEnv> call = RetrofitGenerator.getWeatherInterfaceApi().get_school_list(evn);
        call.enqueue(this);
        showProgressDialog("");
        Logger.e(evn.toString());

    }

//    private void getScore() {
//        //登陆时传的Constants.APP_ID：
//
//        ReqEnv evn = new ReqEnv();
//        ReqBody reqBody = new ReqBody();
//        GetScoreReq request = new GetScoreReq();
//        //获取参数
//
//        reqBody.getScoreReq= request;
//        evn.body = reqBody;
//        Call<ResEnv> call = RetrofitGenerator.getWeatherInterfaceApi().get_stu_score(evn);
//        call.enqueue(this);
//        showProgressDialog("");
//        Logger.e(evn.toString());
//
//    }

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
        closeSwipeRefresh();
        List<String> names=StringUtils.getListToString(call.request().headers().toString().trim(), "/");
        String name=names.get(names.size()-1);
        ResEnv respEnvelope = response.body();
        if (respEnvelope != null) {
            ResBody b=respEnvelope.body;
            if (b.getStuListRes!=null){
                String result=b.getStuListRes.result;
                Logger.e(StringUtils.getTextJoint("%1$s:\n%2$s",name,result));

            }
            if (b.getScoreRes!=null){
                String result=b.getScoreRes.result;
                Logger.e(StringUtils.getTextJoint("%1$s:\n%2$s",name,result));
            }
            if (b.getSchoolRes!=null){
                String result=b.getSchoolRes.result;
                Logger.e(StringUtils.getTextJoint("%1$s:\n%2$s",name,result));
                SchoolRes res=gson.fromJson(result,SchoolRes.class);
                initCreateData(res);
            }
            if (b.userLogoutRes !=null){
                String result=b.userLogoutRes.result;
                Logger.e(StringUtils.getTextJoint("%1$s:\n%2$s",name,result));
                UserRes res=gson.fromJson(result,UserRes.class);
                if (res.getResult().equals(TagFinal.TRUE)){
                    toastShow("已注销");
                    Base.user=null;
                    GreenDaoManager.getInstance().clearUser();
                    UserPreferences.getInstance().clearUserData();
                    Intent intent=new Intent(mActivity,LoginActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    toastShow(res.getError_code());



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
        closeSwipeRefresh();
        Logger.e( "onFailure  "+call.request().headers().toString() );
        toast(R.string.fail_do_not);

    }


    private List<SchoolInfo> adapter_list=new ArrayList<>();
    private void initCreateData(SchoolRes res){
        adapter_list.clear();
        SchoolInfo user=new SchoolInfo();
        user.setView_type(TagFinal.TYPE_TOP);
        user.setSession_key(Base.user.getSession_key());
        user.setRealname(Base.user.getRealname());
        user.setHeadpic(Base.user.getHeadpic());
        user.setSchoolname(Base.user.getSchoolname());
        user.setSchoolid(Base.user.getSchoolid());
        user.setTerm(Base.user.getTerm());
        adapter_list.add(user);
        adapter_list.addAll(res.getSchoollist());
        adapter.setDataList(adapter_list);
        adapter.setLoadState(TagFinal.LOADING_COMPLETE);
    }
    @Override
    public boolean isActivity() {
        return AppLess.isTopActivy(TAG);
    }


}
