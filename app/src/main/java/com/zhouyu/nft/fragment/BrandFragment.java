package com.zhouyu.nft.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;
import com.zhouyu.nft.R;
import com.zhouyu.nft.activity.BrandHomeActivity;
import com.zhouyu.nft.activity.BrandListActivity;
import com.zhouyu.nft.activity.SearchActivity;
import com.zhouyu.nft.adapter.BrandHomeAdapter;
import com.zhouyu.nft.api.GXCallback;
import com.zhouyu.nft.api.YzApi;
import com.zhouyu.nft.bean.BannerBean;
import com.zhouyu.nft.bean.UserInfo;
import com.zhouyu.nft.util.GlideImageLoader;
import com.zhouyu.nft.util.ToastUtils;

import java.util.ArrayList;
import java.util.List;

public class BrandFragment extends Fragment implements OnBannerListener,View.OnClickListener {

    Context mContext;
    Banner slide_pages;
    TextView search;
    RecyclerView recycler;
    LinearLayout brand_list;
    public BrandFragment(Context context){
        mContext=context;
    }
    List<BannerBean> beanList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View inflate = inflater.inflate(R.layout.fragment_brank, container, false);

        slide_pages=inflate.findViewById(R.id.banner);
        search=inflate.findViewById(R.id.search);
        recycler=inflate.findViewById(R.id.recycler);
        brand_list=inflate.findViewById(R.id.brand_list);
        search.setOnClickListener(this);
        brand_list.setOnClickListener(this);

        getBanner();

        BannerBean data = new BannerBean();
        data.setBannerUrl("https://newbbs-fd.zol-img.com.cn/t_s288x500/g7/M00/08/05/ChMkLGKF9u6IFqbiAAdgANq759sAADhHwPbfPEAB2AY118.jpg");
        beanList=new ArrayList<>();
        beanList.add(data);
        beanList.add(data);
        beanList.add(data);
        beanList.add(data);


        List<String> strings=new ArrayList<>();
        strings.add("11");
        strings.add("11");
        strings.add("11");
        strings.add("11");
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        BrandHomeAdapter brandHomeAdapter=new BrandHomeAdapter(mContext,strings);
        brandHomeAdapter.setOnClickListener(id -> startActivity(new Intent(mContext, BrandHomeActivity.class)));
        recycler.setAdapter(brandHomeAdapter);

        bannerView(beanList);
        return inflate;
    }

    private void getBanner() {
        YzApi.getBanner(mContext, "3", new GXCallback<BannerBean>() {
            @Override
            public void onSuccess(BannerBean response, int id) {
            }
        });
    }

    //定义一个方法去初始化Banner控件
    private void bannerView(List<BannerBean> beanList){
        slide_pages.setImageLoader(new GlideImageLoader());
        //设置图片集合
        slide_pages.setIndicatorGravity(BannerConfig.CENTER);
        slide_pages.setImages(beanList);
        //设置轮播间隔时间
        slide_pages.setDelayTime(3000);
        slide_pages.setOnBannerListener(BrandFragment.this);
        slide_pages.start();
    }

    //轮播图的监听方法
    @Override
    public void OnBannerClick(int position) {
        ToastUtils.show(mContext,"第"+position+"张");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.search:
                startActivity(new Intent(mContext, SearchActivity.class));
                break;
            case R.id.brand_list:
                startActivity(new Intent(mContext, BrandListActivity.class));
                break;
        }
    }

}
