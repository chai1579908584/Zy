package com.zhouyu.nft.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

public class GlideUtil {

    //加载本地图片圆角
    @SuppressLint("CheckResult")
    public static void GlideCir(Context context, int pic, ImageView imageView,int round){
        RoundedCorners roundedCorners = new RoundedCorners(round);
        RequestOptions options = RequestOptions.bitmapTransform(roundedCorners);
        options.diskCacheStrategy(DiskCacheStrategy.ALL).transform(new MultiTransformation(new CenterCrop(), new RoundedCorners(round)));
        Glide.with(context)
                .load(pic)
                .apply(options)
                .into(imageView);
    }

    //加载URL图片圆角
    @SuppressLint("CheckResult")
    public static void GlideCir(Context context, String pic, ImageView imageView,int roundingRadius){
        RoundedCorners roundedCorners = new RoundedCorners(roundingRadius);
        RequestOptions options = RequestOptions.bitmapTransform(roundedCorners);
        options.diskCacheStrategy(DiskCacheStrategy.ALL).transform(new MultiTransformation(new CenterCrop(), new RoundedCorners(roundingRadius)));
        Glide.with(context)
                .load(pic)
                .apply(options)
                .into(imageView);
    }


}
