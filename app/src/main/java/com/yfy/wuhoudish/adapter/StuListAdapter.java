package com.yfy.wuhoudish.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yfy.base.Base;
import com.yfy.final_tag.StringUtils;
import com.yfy.final_tag.TagFinal;
import com.yfy.glide.GlideTools;
import com.yfy.wuhoudish.R;
import com.yfy.wuhoudish.StuDetailActivity;
import com.yfy.wuhoudish.bean.StuBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yfyandr on 2017/12/27.
 */

public class StuListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<StuBean> dataList;
    private Activity mContext;
    public void setDataList(List<StuBean> dataList) {

        this.dataList = dataList;
//        getRandomHeights(dataList);
    }

    // 当前加载状态，默认为加载完成
    private int loadState = 2;


    public StuListAdapter(Activity mContext) {
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
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.maintain_item_admin, parent, false);
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
            reHolder.replce.setText(StringUtils.getTextJoint("%1$s %2$s",
                    reHolder.bean.getStusex(),reHolder.bean.getStunation()));
            reHolder.user.setText(reHolder.bean.getStuname());
            GlideTools.chanCircle(mContext, reHolder.bean.getStuheadpic(), reHolder.head_icon, R.drawable.radius_oval_gray);

        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    private class RecyclerViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout layout;

        ImageView head_icon;
        TextView user;
        TextView time;
        TextView state;
        TextView replce;
        StuBean bean;
        RecyclerViewHolder(View itemView) {
            super(itemView);
            layout= (RelativeLayout) itemView.findViewById(R.id.maintain_item_layout);
            time= (TextView) itemView.findViewById(R.id.maintain_item_time);
            head_icon= (ImageView) itemView.findViewById(R.id.main_head_icon);
            replce= (TextView) itemView.findViewById(R.id.maintain_replce);
            user= (TextView) itemView.findViewById(R.id.maintain_user);
            state= (TextView) itemView.findViewById(R.id.maintain_new_state);
            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext, StuDetailActivity.class);
                    intent.putExtra(Base.name,bean.getStuname());
                    intent.putExtra(Base.id,bean.getStuid());
//                    Bundle b=new Bundle();
//                    b.putParcelable(TagFinal.OBJECT_TAG,bean);
//                    intent.putExtras(b);
                    mContext.startActivityForResult(intent,TagFinal.UI_REFRESH);
                }
            });




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
