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
import com.zhouyu.nft.bean.BrandBean;
import com.zhouyu.nft.bean.PresellBean;
import com.zhouyu.nft.util.GlideUtil;

import java.util.List;

public class BrandHomeAdapter extends RecyclerView.Adapter<BrandHomeAdapter.My>{

    private final Context mContext;
    private final List<BrandBean.RecordsBean> messageList;

    public BrandHomeAdapter(Context context, List<BrandBean.RecordsBean> messageList){
        mContext=context;
        this.messageList=messageList;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void addData(List<BrandBean.RecordsBean> recordsBeans) {
        this.messageList.addAll(recordsBeans);
        notifyDataSetChanged();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void refreshData(List<BrandBean.RecordsBean> recordsBeans) {
        if (recordsBeans != null && recordsBeans.size() > 0) {
            this.messageList.clear();
            this.messageList.addAll(recordsBeans);
        }
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public My onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_brandhome, parent, false);

        return new My(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull My holder, int position) {
        BrandBean.RecordsBean brandBean = messageList.get(position);
        GlideUtil.GlideCir(mContext,brandBean.getShowImg(),holder.bg,30);
        GlideUtil.GlideCirHead(mContext,brandBean.getLogoUrl(),holder.head_image,100);
        holder.name.setText(brandBean.getName());
        holder.num.setText("发行"+brandBean.getNftNum()+"个系列｜共"+brandBean.getNftPublishNum()+"个NFT");
        holder.itemView.setOnClickListener(v -> setOnClick.onClick(brandBean.getBid()));
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    static class My extends RecyclerView.ViewHolder {
        ImageView bg,head_image;
        TextView name,num;
        public My(@NonNull View itemView) {
            super(itemView);
            bg=itemView.findViewById(R.id.bg);
            head_image=itemView.findViewById(R.id.head_image);
            name=itemView.findViewById(R.id.name);
            num=itemView.findViewById(R.id.num);
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
