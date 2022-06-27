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

public class BrandListAdapter extends RecyclerView.Adapter<BrandListAdapter.My>{

    private final Context mContext;
    private final List<BrandBean.RecordsBean> messageList;

    public BrandListAdapter(Context context, List<BrandBean.RecordsBean> messageList){
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
        View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_brandlist, parent, false);

        return new My(view);
    }

    @Override
    public void onBindViewHolder(@NonNull My holder, int position) {
        BrandBean.RecordsBean recordsBean = messageList.get(position);
        GlideUtil.GlideCirHead(mContext,recordsBean.getLogoUrl(),holder.headLogo,100);
        holder.brandName.setText(recordsBean.getName());
        holder.itemView.setOnClickListener(v -> setOnClick.onClick(recordsBean.getBid()));
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    static class My extends RecyclerView.ViewHolder {
        ImageView headLogo;
        TextView brandName;
        public My(@NonNull View itemView) {
            super(itemView);
            headLogo=itemView.findViewById(R.id.headLogo);
            brandName=itemView.findViewById(R.id.brandName);
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
