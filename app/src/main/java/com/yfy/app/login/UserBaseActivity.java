package com.yfy.app.login;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.yfy.app.bean.KeyValue;
import com.yfy.base.Base;
import com.yfy.base.activity.BaseActivity;
import com.yfy.final_tag.ColorRgbUtil;
import com.yfy.final_tag.StringUtils;
import com.yfy.final_tag.TagFinal;
import com.yfy.recycerview.RecycleViewDivider;
import com.yfy.wuhoudish.R;
import com.yfy.wuhoudish.bean.Score;
import com.yfy.wuhoudish.bean.ScoreBean;

import java.util.ArrayList;
import java.util.List;


public class UserBaseActivity extends BaseActivity {
    private static final String TAG = UserBaseActivity.class.getSimpleName();
    private UserBaseAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.swip_recycler_main);


        initRecycler();
        getdaTa();
    }

    private boolean is_user=false;

    private ScoreBean scoreBean;
    private void getdaTa(){
        is_user = getIntent().getBooleanExtra(Base.type, false);
        if (is_user){
            initToolbar("基本信息");
            setData();
        }else {
            initToolbar("体质");
            scoreBean=getIntent().getParcelableExtra(Base.data);
            for (Score score:scoreBean.getScores()){
                keyValues.add(new KeyValue(StringUtils.getTextJoint("%1$s：",score.getExamname()),score.getExamscore()+" 分",TagFinal.TYPE_TXT));
            }
            adapter.setDataList(keyValues);
            adapter.setLoadState(TagFinal.LOADING_END);
        }

    }
    private void initToolbar(String title) {
        assert toolbar!= null;
        toolbar.setTitle(title);
    }

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    public void initRecycler(){

        recyclerView = (RecyclerView) findViewById(R.id.public_recycler);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.public_swip);
        //AppBarLayout 展开执行刷新
        // 设置刷新控件颜色
        swipeRefreshLayout.setColorSchemeColors(ColorRgbUtil.getBaseColor());
        // 设置下拉刷新
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // 刷新数据
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
        adapter=new UserBaseAdapter(mActivity);
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


    private List<KeyValue> keyValues=new ArrayList<>();

    private void setData(){
        keyValues.add(new KeyValue("姓名：",Base.user.getRealname(),TagFinal.TYPE_TXT));
        if (Base.user.getSex()!=null)
        keyValues.add(new KeyValue("性别：",Base.user.getSex(),TagFinal.TYPE_TXT));

        keyValues.add(new KeyValue("职业：",Base.user.getTerm(),TagFinal.TYPE_TXT));
        keyValues.add(new KeyValue("学校：",Base.user.getSchoolname(),TagFinal.TYPE_TXT));
        if (Base.user.getClassname()!=null)
        keyValues.add(new KeyValue("班级：",Base.user.getClassname(),TagFinal.TYPE_TXT));
        adapter.setDataList(keyValues);
        adapter.setLoadState(TagFinal.LOADING_END);
    }

}
