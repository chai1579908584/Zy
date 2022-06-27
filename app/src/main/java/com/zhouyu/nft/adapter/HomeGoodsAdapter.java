package com.zhouyu.nft.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zhouyu.nft.R;
import com.zhouyu.nft.bean.PresellBean;
import com.zhouyu.nft.util.AmountUtil;
import com.zhouyu.nft.util.GlideUtil;

import java.util.List;

public class HomeGoodsAdapter extends RecyclerView.Adapter<HomeGoodsAdapter.My> {

    private final Context mContext;
    private List<PresellBean.RecordsBean> recordsBeans;

    public HomeGoodsAdapter(Context context, List<PresellBean.RecordsBean> messageList) {
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
        View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_homegoods, parent, false);

        return new My(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull My holder, int position) {
        PresellBean.RecordsBean recordsBean = recordsBeans.get(position);
        switch (recordsBean.getType()) {
            case "2":
                holder.rl_time.setVisibility(View.GONE);
                holder.hot_sale.setVisibility(View.VISIBLE);
                holder.shouqing.setVisibility(View.GONE);
                break;
            case "3":
                holder.rl_time.setVisibility(View.GONE);
                holder.hot_sale.setVisibility(View.GONE);
                holder.shouqing.setVisibility(View.VISIBLE);
                break;
            default:
                holder.rl_time.setVisibility(View.VISIBLE);
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
        holder.num.setText(recordsBean.getOverNum()+"/"+recordsBean.getSaleNum());
        holder.price.setText(AmountUtil.changeF2Y(recordsBean.getSalePrice()));

        GlideUtil.GlideCir(mContext, recordsBean.getImgUrl(), holder.image_bg, 30);

        holder.itemView.setOnClickListener(v -> setOnClick.onClick(recordsBean.getGid()));

    }

    @Override
    public int getItemCount() {
        return recordsBeans.size();
    }

    static class My extends RecyclerView.ViewHolder {
        TextView time,shang_name, price,num;
        ImageView image_bg, type_image, hot_sale, shouqing;
        RelativeLayout rl_time;

        public My(@NonNull View itemView) {
            super(itemView);
            time = itemView.findViewById(R.id.time);
            shang_name = itemView.findViewById(R.id.shang_name);
            num = itemView.findViewById(R.id.num);
            price = itemView.findViewById(R.id.price);
            image_bg = itemView.findViewById(R.id.image_bg);
            type_image = itemView.findViewById(R.id.type_image);
            hot_sale = itemView.findViewById(R.id.hot_sale);
            shouqing = itemView.findViewById(R.id.shouqing);
            rl_time = itemView.findViewById(R.id.rl_time);
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
