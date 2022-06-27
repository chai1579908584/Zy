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
import com.zhouyu.nft.bean.BankCardBean;
import com.zhouyu.nft.bean.BrandBean;
import com.zhouyu.nft.bean.UnpaidBean;
import com.zhouyu.nft.util.AmountUtil;
import com.zhouyu.nft.util.GlideUtil;

import java.util.List;

public class UnpaidAdapter extends RecyclerView.Adapter<UnpaidAdapter.My>{

    private final Context mContext;
    private List<UnpaidBean.RecordsBean> response;

    public UnpaidAdapter(Context context, List<UnpaidBean.RecordsBean> response){
        mContext=context;
        this.response=response;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void addData(List<UnpaidBean.RecordsBean> recordsBeans) {
        this.response.addAll(recordsBeans);
        notifyDataSetChanged();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void refreshData(List<UnpaidBean.RecordsBean> response){
        if (response!=null&&response.size()>0)
        {
            this.response.clear();
            this.response.addAll(response);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public My onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_unpaid, parent, false);

        return new My(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull My holder, int position) {
        UnpaidBean.RecordsBean recordsBean = response.get(position);
        GlideUtil.GlideCir(mContext,recordsBean.getProductImg(),holder.image,30);
        holder.tvName.setText(recordsBean.getProductName());
        holder.tvPrice.setText(AmountUtil.changeF2Y(recordsBean.getProductPrice()));
        holder.payPrice.setText(AmountUtil.changeF2Y(recordsBean.getOrderPrice()));
      //  holder.tvMessage.setText(recordsBean.getProductName());
       // holder.time.setText(recordsBean.getCancelTime());
        holder.itemView.setOnClickListener(v -> setOnClick.onClick(recordsBean.getOrderId()));
    }

    @Override
    public int getItemCount() {
        return response.size();
    }

    static class My extends RecyclerView.ViewHolder {
        TextView tvName,tvPrice,tvMessage,payPrice,time;
        ImageView image;
        public My(@NonNull View itemView) {
            super(itemView);
            tvName=itemView.findViewById(R.id.tvName);
            tvPrice=itemView.findViewById(R.id.tvPrice);
            tvMessage=itemView.findViewById(R.id.tvMessage);
            payPrice=itemView.findViewById(R.id.payPrice);
            time=itemView.findViewById(R.id.time);
            image=itemView.findViewById(R.id.image);
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
