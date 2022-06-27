package com.zhouyu.nft.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zhouyu.nft.R;
import com.zhouyu.nft.util.GlideUtil;

import java.io.File;
import java.util.List;

public class AddImageAdapter extends RecyclerView.Adapter<AddImageAdapter.My>{

    private final Context mContext;
    private final  List<File> messageList;

    public AddImageAdapter(Context context, List<File> messageList){
        mContext=context;
        this.messageList=messageList;
    }

    @NonNull
    @Override
    public My onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_addimage, parent, false);

        return new My(view);
    }
    @Override
    public void onBindViewHolder(@NonNull My holder, int position) {
        if (position<messageList.size()&&position<=8)
        {
            holder.image.setVisibility(View.VISIBLE);
            holder.addImage.setVisibility(View.GONE);
            GlideUtil.GlideCir(mContext,messageList.get(position).getPath(),holder.image,10);
        }else {
            if (position<9)
            {
                holder.addImage.setVisibility(View.VISIBLE);
            }else {
                holder.addImage.setVisibility(View.GONE);
            }
            holder.image.setVisibility(View.GONE);
        }
        holder.addImage.setOnClickListener(v -> setOnClick.onClick());
        holder.image.setOnClickListener(v -> setOnClick.onClickItem(position));
    }

    @Override
    public int getItemCount() {
        if (messageList.size()<9)
        {
            return messageList.size()+1;
        }else {
            return messageList.size();
        }
    }

    static class My extends RecyclerView.ViewHolder {
        ImageView addImage,image;
        public My(@NonNull View itemView) {
            super(itemView);
            addImage=itemView.findViewById(R.id.addImage);
            image=itemView.findViewById(R.id.image);
        }
    }

    SetOnClick setOnClick;
    public void setOnClickListener(SetOnClick setOnClick) {
        this.setOnClick = setOnClick;
    }
    public interface SetOnClick{
        void onClick();
        void onClickItem(int position);
    }
}
