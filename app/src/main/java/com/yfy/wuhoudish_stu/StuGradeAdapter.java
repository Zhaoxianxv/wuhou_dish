package com.yfy.wuhoudish_stu;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yfy.final_tag.StringJudge;
import com.yfy.final_tag.StringUtils;
import com.yfy.final_tag.TagFinal;
import com.yfy.wuhoudish.R;
import com.yfy.wuhoudish.bean.Score;
import com.yfy.wuhoudish.bean.ScoreBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yfyandr on 2017/12/27.
 */

public class StuGradeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ScoreBean> dataList;
    private Activity mContext;
    public void setDataList(List<ScoreBean> dataList) {

        this.dataList = dataList;
//        getRandomHeights(dataList);
    }

    // 当前加载状态，默认为加载完成
    private int loadState = 2;


    public StuGradeAdapter(Activity mContext) {
        this.mContext=mContext;
        this.dataList = new ArrayList<>();

    }

    @Override
    public int getItemViewType(int position) {
        // 最后一个item设置为FooterView
        return TagFinal.TYPE_ITEM;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //进行判断显示类型，来创建返回不同的View
        if (viewType == TagFinal.TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.selected_singe_item_layout, parent, false);
            return new RecyclerViewHolder(view);

        }
        return null;
    }



    private int heigh;
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof RecyclerViewHolder) {
            RecyclerViewHolder reHolder = (RecyclerViewHolder) holder;
            reHolder.bean=dataList.get(position);
            reHolder.key.setText(reHolder.bean.getCoursename());
            reHolder.value.setVisibility(View.VISIBLE);
            String value;
            List<String> list=new ArrayList<>();

            for (Score score:reHolder.bean.getScores()){
                list.add(StringUtils.getTextJoint("%1$s:%2$s",score.getExamname(),score.getExamscore()));
            }
            reHolder.value.setTextSize(10f);
            value=StringUtils.arraysToString(list, "\n");
            if (StringJudge.isEmpty(list)){
                reHolder.value.setText("");
            }else{
                reHolder.value.setText(value);
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
        ScoreBean bean;
        RecyclerViewHolder(View itemView) {
            super(itemView);
            key= (TextView) itemView.findViewById(R.id.selected_item_name);
            value= (TextView) itemView.findViewById(R.id.selected_item_type);

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
