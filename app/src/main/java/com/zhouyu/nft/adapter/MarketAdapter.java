package com.zhouyu.nft.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zhouyu.nft.R;
import com.zhouyu.nft.bean.PresellBean;
import com.zhouyu.nft.util.GlideUtil;

import java.util.List;

public class MarketAdapter extends RecyclerView.Adapter<MarketAdapter.My>{

    private final Context mContext;
    private  List<PresellBean.RecordsBean> recordsBeans;

    public MarketAdapter(Context context, List<PresellBean.RecordsBean> messageList){
        mContext=context;
        this.recordsBeans=messageList;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void addData(List<PresellBean.RecordsBean> recordsBeans){
        this.recordsBeans.addAll(recordsBeans);
        notifyDataSetChanged();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void refreshData(List<PresellBean.RecordsBean> recordsBeans){
        if (recordsBeans!=null&&recordsBeans.size()>0)
        {
            this.recordsBeans.clear();
            this.recordsBeans.addAll(recordsBeans);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public My onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_market, parent, false);

        return new My(view);
    }

    @Override
    public void onBindViewHolder(@NonNull My holder, int position) {

       holder.itemView.setOnClickListener(v -> setOnClick.onClick("1"));

    }

    @Override
    public int getItemCount() {
        return recordsBeans.size();
    }

    static class My extends RecyclerView.ViewHolder {
        public My(@NonNull View itemView) {
            super(itemView);
        }
    }

    SetOnClick setOnClick;
    public void setOnClickListener(SetOnClick setOnClick) {
        this.setOnClick = setOnClick;
    }
    public interface SetOnClick{
        void onClick(String id);
    }
}
