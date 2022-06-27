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
import com.zhouyu.nft.util.AmountUtil;
import com.zhouyu.nft.util.GlideUtil;

import java.util.List;

public class MarkerAdapter extends RecyclerView.Adapter<MarkerAdapter.My> {

    private final Context mContext;
    List<PresellBean.RecordsBean> records;

    public MarkerAdapter(Context context, List<PresellBean.RecordsBean> records) {
        mContext = context;
        this.records = records;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void addData(List<PresellBean.RecordsBean> recordsBeans) {
        this.records.addAll(recordsBeans);
        notifyDataSetChanged();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void refreshData(List<PresellBean.RecordsBean> recordsBeans) {
        if (recordsBeans != null && recordsBeans.size() > 0) {
            this.records.clear();
            this.records.addAll(recordsBeans);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public My onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_marker, parent, false);

        return new My(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull My holder, int position) {
        PresellBean.RecordsBean recordsBean = records.get(position);
        GlideUtil.GlideCir(mContext,recordsBean.getImgUrl(),holder.image_bg,20);
        GlideUtil.GlideCirHead(mContext,recordsBean.getBrandlogo(),holder.head_image,100);
        holder.head_name.setText(recordsBean.getBrandName());
        switch (recordsBean.getProductType())
        {
            case "1":
                holder.type.setImageResource(R.mipmap.threed_right);
                break;
            case "2":
                holder.type.setImageResource(R.mipmap.video_right);
                break;
            case "3":
                holder.type.setImageResource(R.mipmap.music_right);
                break;
            default:
                holder.type.setImageResource(R.mipmap.image_right);
                break;
        }
        holder.name.setText(recordsBean.getProductName());
        holder.mark.setText(recordsBean.getOverNum()+"/"+recordsBean.getSaleNum());
        holder.price.setText(AmountUtil.changeF2Y(recordsBean.getSalePrice()));
        holder.itemView.setOnClickListener(v -> setOnClick.onClick(recordsBean.getGid()));
    }

    @Override
    public int getItemCount() {
        return records.size();
    }

    static class My extends RecyclerView.ViewHolder {
        ImageView image_bg, head_image, type;
        TextView head_name, name, mark, price;

        public My(@NonNull View itemView) {
            super(itemView);
            image_bg = itemView.findViewById(R.id.image_bg);
            head_image = itemView.findViewById(R.id.head_image);
            type = itemView.findViewById(R.id.type);
            head_name = itemView.findViewById(R.id.head_name);
            name = itemView.findViewById(R.id.name);
            mark = itemView.findViewById(R.id.mark);
            price = itemView.findViewById(R.id.price);
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
