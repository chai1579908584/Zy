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
import com.zhouyu.nft.bean.BrandDetailBean;

import java.util.List;

public class LabelAdapter extends RecyclerView.Adapter<LabelAdapter.My>{

    private final Context mContext;
    private List<BrandDetailBean.SeriesListBean> response;

    public LabelAdapter(Context context, List<BrandDetailBean.SeriesListBean> response){
        mContext=context;
        this.response=response;
    }


    @NonNull
    @Override
    public My onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_label, parent, false);

        return new My(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull My holder, int position) {
        BrandDetailBean.SeriesListBean seriesListBean = response.get(position);
        holder.label.setText(seriesListBean.getSeriesName());
    }

    @Override
    public int getItemCount() {
        return response.size();
    }

    static class My extends RecyclerView.ViewHolder {
        TextView label;
        public My(@NonNull View itemView) {
            super(itemView);
            label=itemView.findViewById(R.id.label);

        }
    }
}
