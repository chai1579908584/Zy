package com.zhouyu.nft.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zhouyu.nft.R;

import java.util.List;

public class BrandListAdapter extends RecyclerView.Adapter<BrandListAdapter.My>{

    private final Context mContext;
    private final List<String> messageList;

    public BrandListAdapter(Context context, List<String> messageList){
        mContext=context;
        this.messageList=messageList;
    }
    @NonNull
    @Override
    public My onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_brandlist, parent, false);

        return new My(view);
    }

    @Override
    public void onBindViewHolder(@NonNull My holder, int position) {

      holder.itemView.setOnClickListener(v -> setOnClick.onClick("1"));
    }

    @Override
    public int getItemCount() {
        return messageList.size();
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
