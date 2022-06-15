package com.zhouyu.nft.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;
import com.zhouyu.nft.R;
import com.zhouyu.nft.activity.CollectionActivity;
import com.zhouyu.nft.activity.InformationActivity;
import com.zhouyu.nft.bean.BannerBean;
import com.zhouyu.nft.bean.UserInfo;
import com.zhouyu.nft.util.GlideImageLoader;
import com.zhouyu.nft.util.PayPopupWindow;
import com.zhouyu.nft.util.ToastUtils;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment implements OnBannerListener,View.OnClickListener {

    List<BannerBean> beanList;
    Banner slide_pages;
    ImageView information;
    TextView into_collection;

    Context mContext;
    public HomeFragment(Context context){
        mContext=context;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_home, container, false);

        information=inflate.findViewById(R.id.information);
        into_collection=inflate.findViewById(R.id.into_collection);

        information.setOnClickListener(this);
        into_collection.setOnClickListener(this);

        BannerBean data = new BannerBean();
        data.setBannerUrl("https://newbbs-fd.zol-img.com.cn/t_s288x500/g7/M00/08/05/ChMkLGKF9u6IFqbiAAdgANq759sAADhHwPbfPEAB2AY118.jpg");
        beanList=new ArrayList<>();
        beanList.add(data);
        beanList.add(data);
        beanList.add(data);
        beanList.add(data);

        slide_pages=inflate.findViewById(R.id.banner);
        bannerView(beanList);

        return inflate;
    }
    //定义一个方法去初始化Banner控件
    private void bannerView(List<BannerBean> beanList){
        slide_pages.setImageLoader(new GlideImageLoader());
        //设置图片集合
        slide_pages.setIndicatorGravity(BannerConfig.CENTER);
        slide_pages.setImages(beanList);
        //设置轮播间隔时间
        slide_pages.setDelayTime(3000);
        slide_pages.setOnBannerListener(HomeFragment.this);
        slide_pages.start();
    }

    @Override
    public void OnBannerClick(int position) {
        ToastUtils.show(mContext,"第"+position+"张");
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.information:
                startActivity(new Intent(mContext, InformationActivity.class));
                break;
            case R.id.into_collection:
                startActivity(new Intent(mContext, CollectionActivity.class));
                break;
        }
    }
}
