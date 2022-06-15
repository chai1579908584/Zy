package com.zhouyu.nft.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zhouyu.nft.R;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.My>{

    private final Context mContext;
    private final List<String> messageList;

    public SearchAdapter(Context context, List<String> messageList){
        mContext=context;
        this.messageList=messageList;
    }
    @NonNull
    @Override
    public My onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_search, parent, false);

        return new My(view);
    }

    @Override
    public void onBindViewHolder(@NonNull My holder, int position) {
         holder.searchData.setText(messageList.get(position));
         holder.searchData.setOnClickListener(v -> setOnClick.onClick(position));
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    static class My extends RecyclerView.ViewHolder {
        TextView searchData;
        public My(@NonNull View itemView) {
            super(itemView);
            searchData=itemView.findViewById(R.id.searchData);
        }
    }

    SetOnClick setOnClick;
    public void setOnClickListener(SetOnClick setOnClick) {
        this.setOnClick = setOnClick;
    }
    public interface SetOnClick{
        void onClick(int position);
    }
}
