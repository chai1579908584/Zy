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
import com.zhouyu.nft.bean.CalenderBean;
import com.zhouyu.nft.bean.PresellBean;
import com.zhouyu.nft.util.AmountUtil;
import com.zhouyu.nft.util.GlideUtil;

import java.util.List;

public class CalenderAdapter extends RecyclerView.Adapter<CalenderAdapter.My> {

    private final Context mContext;
    private List<CalenderBean.RecordsBean> response;
    String mouthStr = "";
    String hourStr = "";

    public CalenderAdapter(Context context, List<CalenderBean.RecordsBean> response) {
        mContext = context;
        this.response = response;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void refreshData(List<CalenderBean.RecordsBean> response) {
        if (response != null && response.size() > 0) {
            this.response.clear();
            this.response.addAll(response);

        }
        mouthStr = "";
        hourStr = "";
        notifyDataSetChanged();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void addData(List<CalenderBean.RecordsBean> recordsBeans) {
        this.response.addAll(recordsBeans);
        mouthStr = "";
        hourStr = "";
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public My onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_calender, parent, false);

        return new My(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull My holder, int position) {
        CalenderBean.RecordsBean recordsBean = response.get(holder.getLayoutPosition());
        String pubTime = recordsBean.getPubTime();
        if (!mouthStr.equals(pubTime.substring(0, 11))) {
            mouthStr = pubTime.substring(0, 11);
            hourStr = pubTime.substring(11, 16);
            holder.month.setVisibility(View.VISIBLE);
            holder.hour.setVisibility(View.VISIBLE);
            holder.month.setText(pubTime.substring(5, 7) + "月" + pubTime.substring(8, 10) + "日");
            holder.hour.setText(pubTime.substring(11, 16));
        } else {
            if (!hourStr.equals(pubTime.substring(11, 16))) {
                hourStr = pubTime.substring(11, 16);
                holder.hour.setVisibility(View.VISIBLE);
                holder.hour.setText(pubTime.substring(11, 16));
            } else {
                holder.month.setVisibility(View.GONE);
                holder.hour.setVisibility(View.GONE);
            }
        }
        switch (recordsBean.getProductType())
        {
            case "1":
                holder.typeImage.setImageResource(R.mipmap.threed_right);
                break;
            case "2":
                holder.typeImage.setImageResource(R.mipmap.video_right);
                break;
            case "3":
                holder.typeImage.setImageResource(R.mipmap.music_right);
                break;
            default:
                holder.typeImage.setImageResource(R.mipmap.image_right);
                break;
        }
        GlideUtil.GlideCir(mContext,recordsBean.getImgUrl(),holder.image,30);
        holder.name.setText(recordsBean.getProductName());
        holder.num.setText("限量 "+recordsBean.getSaleNum()+"份");
        holder.price.setText(AmountUtil.changeF2Y(recordsBean.getSalePrice()));
        holder.itemView.setOnClickListener(v -> setOnClick.onClick(recordsBean.getGid()));
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return response.size();
    }

    static class My extends RecyclerView.ViewHolder {

        TextView month, hour, name, num, price;
        ImageView image,typeImage;

        public My(@NonNull View itemView) {
            super(itemView);
            month = itemView.findViewById(R.id.month);
            hour = itemView.findViewById(R.id.hour);
            name = itemView.findViewById(R.id.name);
            num = itemView.findViewById(R.id.num);
            price = itemView.findViewById(R.id.price);
            image = itemView.findViewById(R.id.image);
            typeImage = itemView.findViewById(R.id.typeImage);
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
