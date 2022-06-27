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
import com.zhouyu.nft.bean.SeriesListBean;

import java.util.List;

public class ScreenAdapter extends RecyclerView.Adapter<ScreenAdapter.My>{

    private final Context mContext;
    private List<SeriesListBean> message;

    public ScreenAdapter(Context context, List<SeriesListBean> message){
        mContext=context;
        this.message=message;
    }


    @NonNull
    @Override
    public My onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_screen, parent, false);

        return new My(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull My holder, int position) {
        SeriesListBean seriesListBean = message.get(position);

        holder.framer_zy.setText(seriesListBean.getSeriesName());

        holder.itemView.setOnClickListener(v -> setOnClick.onClick(seriesListBean.getSid()));
    }

    @Override
    public int getItemCount() {
        return message.size();
    }

    static class My extends RecyclerView.ViewHolder {
        TextView framer_zy;
        public My(@NonNull View itemView) {
            super(itemView);
            framer_zy=itemView.findViewById(R.id.framer_zy);
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
