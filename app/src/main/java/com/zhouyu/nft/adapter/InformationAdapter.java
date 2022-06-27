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
import com.zhouyu.nft.bean.InformationBean;
import com.zhouyu.nft.bean.PresellBean;
import com.zhouyu.nft.util.GlideUtil;

import java.util.ArrayList;
import java.util.List;


public class InformationAdapter extends RecyclerView.Adapter<InformationAdapter.My>{

    private final Context mContext;
    List<InformationBean.RecordsBean> list;
    public InformationAdapter(Context context,List<InformationBean.RecordsBean> list){
        mContext=context;
        this.list=list;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void addData(List<InformationBean.RecordsBean> list){
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void refreshData(List<InformationBean.RecordsBean> list){
        if (list!=null&&list.size()>0)
        {
            this.list.clear();
            this.list.addAll(list);
        }
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public My onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_information, parent, false);

        return new My(view);
    }

    @Override
    public void onBindViewHolder(@NonNull My holder, int position) {
        InformationBean.RecordsBean recordsBean = list.get(position);
        holder.title.setText(recordsBean.getTitle());
        holder.time.setText(recordsBean.getPubTime());
        holder.message.setText(recordsBean.getContent());
        GlideUtil.GlideCir(mContext,recordsBean.getImgUrl(),holder.image,10);
        holder.itemView.setOnClickListener(v -> setOnClick.onClick(recordsBean.getLinkUrl(),recordsBean.getTitle()));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class My extends RecyclerView.ViewHolder {
        TextView title,time,message;
        ImageView image;
        public My(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.title);
            time=itemView.findViewById(R.id.time);
            message=itemView.findViewById(R.id.message);
            image=itemView.findViewById(R.id.image);
        }
    }

    SetOnClick setOnClick;
    public void setOnClickListener(SetOnClick setOnClick) {
        this.setOnClick = setOnClick;
    }
    public interface SetOnClick{
        void onClick(String linkUrl,String title);
    }
}
