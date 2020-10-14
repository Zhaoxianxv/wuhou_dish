package com.yfy.app.login;

import android.app.Activity;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yfy.app.bean.KeyValue;
import com.yfy.final_tag.TagFinal;
import com.yfy.wuhoudish.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yfy1 on 2016/10/17.
 */
public class UserBaseAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {




    private List<KeyValue> dataList;
    private Activity mContext;

    public void setDataList(List<KeyValue> dataList) {
        this.dataList = dataList;
    }


    public UserBaseAdapter(Activity mContext){
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
        if (viewType == TagFinal.TYPE_TXT) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.public_type_txt, parent, false);
            return new TxtHolder(view);
        }

        return null;
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof TxtHolder) {
            TxtHolder reHolder = (TxtHolder) holder;
            reHolder.bean=dataList.get(position);
            reHolder.txt_key.setText(reHolder.bean.getKey());
            reHolder.txt_value.setText(reHolder.bean.getValue());
        }

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    private class TxtHolder extends RecyclerView.ViewHolder {
        TextView txt_key;
        AppCompatTextView txt_value;
        KeyValue bean;
        TxtHolder(View itemView) {
            super(itemView);
            txt_key=  itemView.findViewById(R.id.public_type_txt_key);
            txt_value=  itemView.findViewById(R.id.public_type_txt_value);
        }
    }



    /**
     * 设置上拉加载状态
     *
     * @param loadState 1.正在加载 2.加载完成 3.加载到底
     */
    public void setLoadState(int loadState) {
        notifyDataSetChanged();
    }





}
