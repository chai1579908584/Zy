package com.zhouyu.nft.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zhouyu.nft.R;


public class SellRecordAdapter extends RecyclerView.Adapter<SellRecordAdapter.My>{

    private final Context mContext;

    public SellRecordAdapter(Context context){
        mContext=context;
    }
    @NonNull
    @Override
    public My onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_sellrecord, parent, false);

        return new My(view);
    }

    @Override
    public void onBindViewHolder(@NonNull My holder, int position) {
     holder.itemView.setOnClickListener(v -> setOnClick.onClick("1"));

    }

    @Override
    public int getItemCount() {
        return 20;
    }

    static class My extends RecyclerView.ViewHolder {
        public My(@NonNull View itemView) {
            super(itemView);
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
