package com.zhouyu.nft.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zhouyu.nft.R;
import com.zhouyu.nft.bean.BankCodeBean;
import com.zhouyu.nft.bean.HomeBean;
import com.zhouyu.nft.util.GlideUtil;

import java.util.List;

public class HomeBrandAdapter extends RecyclerView.Adapter<HomeBrandAdapter.My> {

    private final Context mContext;
    private final List<HomeBean.BrandListBean> messageList;

    public HomeBrandAdapter(Context context, List<HomeBean.BrandListBean> messageList) {
        mContext = context;
        this.messageList = messageList;
    }

    @NonNull
    @Override
    public My onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_homebrand, parent, false);

        return new My(view);
    }

    @Override
    public void onBindViewHolder(@NonNull My holder, int position) {
        HomeBean.BrandListBean brandListBean = messageList.get(position);
        GlideUtil.GlideCir(mContext, brandListBean.getShowImg(), holder.brandImage, 15);
        holder.itemView.setOnClickListener(v -> setOnClick.onClick(brandListBean.getBid()));
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    static class My extends RecyclerView.ViewHolder {

        ImageView brandImage;

        public My(@NonNull View itemView) {
            super(itemView);
            brandImage = itemView.findViewById(R.id.brandImage);
        }
    }

    SetOnClick setOnClick;

    public void setOnClickListener(SetOnClick setOnClick) {
        this.setOnClick = setOnClick;
    }

    public interface SetOnClick {
        void onClick(String id);
    }
}
