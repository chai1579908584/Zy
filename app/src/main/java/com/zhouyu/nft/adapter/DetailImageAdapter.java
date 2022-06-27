package com.zhouyu.nft.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.zhouyu.nft.R;
import com.zhouyu.nft.bean.BankCardBean;
import com.zhouyu.nft.util.GlideUtil;

import java.io.File;
import java.util.List;

public class DetailImageAdapter extends RecyclerView.Adapter<DetailImageAdapter.My> {

    private final Context mContext;
    private List<String> response;

    public DetailImageAdapter(Context context, List<String> response) {
        mContext = context;
        this.response = response;
    }


    @NonNull
    @Override
    public My onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_image, parent, false);

        return new My(view);
    }

    @SuppressLint({"SetTextI18n", "CheckResult"})
    @Override
    public void onBindViewHolder(@NonNull My holder, int position) {

        Glide.with(mContext).load(response.get(position)).diskCacheStrategy(DiskCacheStrategy.ALL)
                .downloadOnly(new SimpleTarget<File>() {
                    @Override
                    public void onResourceReady(@NonNull File resource, Transition<? super File> transition) {
                        Uri uri = Uri.fromFile(resource);
                        holder.image.setImageURI(uri);
                    }
                });

    }

    @Override
    public int getItemCount() {
        return response.size();
    }

    static class My extends RecyclerView.ViewHolder {
        ImageView image;

        public My(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
        }
    }

}
