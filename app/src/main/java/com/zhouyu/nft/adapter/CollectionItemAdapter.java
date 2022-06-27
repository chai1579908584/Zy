package com.zhouyu.nft.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zhouyu.nft.R;
import com.zhouyu.nft.bean.PresellBean;
import com.zhouyu.nft.util.AmountUtil;
import com.zhouyu.nft.util.GlideUtil;

import java.util.List;

public class CollectionItemAdapter extends RecyclerView.Adapter<CollectionItemAdapter.My> {

    private final Context mContext;
    private List<PresellBean.RecordsBean> recordsBeans;

    public CollectionItemAdapter(Context context, List<PresellBean.RecordsBean> messageList) {
        mContext = context;
        this.recordsBeans = messageList;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void addData(List<PresellBean.RecordsBean> recordsBeans) {
        this.recordsBeans.addAll(recordsBeans);
        notifyDataSetChanged();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void refreshData(List<PresellBean.RecordsBean> recordsBeans) {
        if (recordsBeans != null && recordsBeans.size() > 0) {
            this.recordsBeans.clear();
            this.recordsBeans.addAll(recordsBeans);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public My onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_collectionitem, parent, false);

        return new My(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull My holder, int position) {
        PresellBean.RecordsBean recordsBean = recordsBeans.get(position);
        switch (recordsBean.getType()) {
            case "2":
                holder.time.setVisibility(View.GONE);
                holder.hot_sale.setVisibility(View.VISIBLE);
                holder.shouqing.setVisibility(View.GONE);
                break;
            case "3":
                holder.time.setVisibility(View.GONE);
                holder.hot_sale.setVisibility(View.GONE);
                holder.shouqing.setVisibility(View.VISIBLE);
                break;
            default:
                holder.time.setVisibility(View.VISIBLE);
                holder.hot_sale.setVisibility(View.GONE);
                holder.shouqing.setVisibility(View.GONE);
                holder.time.setText(recordsBean.getPubTime().substring(5,16));
                break;
        }

        switch (recordsBean.getProductType()) {
            case "1":
                holder.type_image.setImageResource(R.mipmap.threed_right);
                break;
            case "2":
                holder.type_image.setImageResource(R.mipmap.video_right);
                break;
            case "3":
                holder.type_image.setImageResource(R.mipmap.music_right);
                break;
            default:
                holder.type_image.setImageResource(R.mipmap.image_right);
                break;
        }
        holder.shang_name.setText(recordsBean.getProductName());
        holder.price.setText(AmountUtil.changeF2Y(recordsBean.getSalePrice()));
        holder.name.setText(recordsBean.getBrandName());
        holder.num.setText(recordsBean.getSaleNum()+"份");

        GlideUtil.GlideCir(mContext, recordsBean.getImgUrl(), holder.image_bg, 30);
        if ("".equals(recordsBean.getBrandlogo()))
        {
            holder.head_image.setVisibility(View.GONE);
            holder.name.setText("#"+recordsBean.getProductNo());
            holder.ll_num.setVisibility(View.GONE);
            holder.time.setVisibility(View.GONE);
        }
        GlideUtil.GlideCirHead(mContext, recordsBean.getBrandlogo(), holder.head_image, 100);

        holder.itemView.setOnClickListener(v -> setOnClick.onClick(recordsBean.getGid()));

    }

    @Override
    public int getItemCount() {
        return recordsBeans.size();
    }

    static class My extends RecyclerView.ViewHolder {
        TextView time, name, shang_name, price,num;
        ImageView image_bg, type_image, head_image, hot_sale, shouqing;
        LinearLayout ll_num;

        public My(@NonNull View itemView) {
            super(itemView);
            time = itemView.findViewById(R.id.time);
            name = itemView.findViewById(R.id.name);
            num = itemView.findViewById(R.id.num);
            shang_name = itemView.findViewById(R.id.shang_name);
            price = itemView.findViewById(R.id.price);
            image_bg = itemView.findViewById(R.id.image_bg);
            type_image = itemView.findViewById(R.id.type_image);
            head_image = itemView.findViewById(R.id.head_image);
            hot_sale = itemView.findViewById(R.id.hot_sale);
            shouqing = itemView.findViewById(R.id.shouqing);
            ll_num = itemView.findViewById(R.id.ll_num);
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
