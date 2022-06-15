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
import com.zhouyu.nft.util.GlideUtil;

import java.util.List;

public class BrandHomeAdapter extends RecyclerView.Adapter<BrandHomeAdapter.My>{

    private final Context mContext;
    private final List<String> messageList;

    public BrandHomeAdapter(Context context, List<String> messageList){
        mContext=context;
        this.messageList=messageList;
    }
    @NonNull
    @Override
    public My onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_brandhome, parent, false);

        return new My(view);
    }

    @Override
    public void onBindViewHolder(@NonNull My holder, int position) {

        GlideUtil.GlideCir(mContext,"https://newbbs-fd.zol-img.com.cn/t_s288x500/g7/M00/08/05/ChMkLGKF9u6IFqbiAAdgANq759sAADhHwPbfPEAB2AY118.jpg",holder.bg,30);
        GlideUtil.GlideCir(mContext,"https://newbbs-fd.zol-img.com.cn/t_s288x500/g7/M00/08/05/ChMkLGKF9u6IFqbiAAdgANq759sAADhHwPbfPEAB2AY118.jpg",holder.head_image,100);

        holder.itemView.setOnClickListener(v -> setOnClick.onClick("11"));

    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    static class My extends RecyclerView.ViewHolder {
        ImageView bg,head_image;
        TextView name,num;
        public My(@NonNull View itemView) {
            super(itemView);
            bg=itemView.findViewById(R.id.bg);
            head_image=itemView.findViewById(R.id.head_image);
            name=itemView.findViewById(R.id.name);
            num=itemView.findViewById(R.id.num);
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
