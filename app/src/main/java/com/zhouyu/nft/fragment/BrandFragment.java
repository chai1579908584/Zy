package com.zhouyu.nft.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;
import com.zhouyu.nft.R;
import com.zhouyu.nft.activity.BrandHomeActivity;
import com.zhouyu.nft.activity.BrandListActivity;
import com.zhouyu.nft.activity.HFiveActivity;
import com.zhouyu.nft.activity.SearchActivity;
import com.zhouyu.nft.adapter.BrandHomeAdapter;
import com.zhouyu.nft.api.GXCallback;
import com.zhouyu.nft.api.YzApi;
import com.zhouyu.nft.bean.BannerBean;
import com.zhouyu.nft.bean.BrandBean;
import com.zhouyu.nft.bean.UserInfo;
import com.zhouyu.nft.util.GlideImageLoader;
import com.zhouyu.nft.util.ParamsConfigs;
import com.zhouyu.nft.util.SpUtil;
import com.zhouyu.nft.util.ToastUtils;

import java.util.List;

import okhttp3.Call;

public class BrandFragment extends Fragment implements OnBannerListener,View.OnClickListener {

    Context mContext;
    Banner slide_pages;
    TextView search;
    RecyclerView recycler;
    LinearLayout brand_list;
    SmartRefreshLayout mRefreshLayout;
    int page=1;
    BrandHomeAdapter brandHomeAdapter;

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
        requestData(true,null);

        mRefreshLayout = inflate.findViewById(R.id.refreshLayout);
        //下拉刷新
        mRefreshLayout.setOnRefreshListener(refreshLayout -> {
            requestData(true, refreshLayout);
        });
        //上拉加载更多
        mRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            requestData(false, refreshLayout);
        });

        return inflate;
    }

    private void requestData(boolean refresh, RefreshLayout cancel) {
        if (refresh) {
            page = 1;
        } else {
            ++page;
        }
        YzApi.getBrand(mContext, page, new GXCallback<BrandBean>() {
            @Override
            public void onSuccess(BrandBean response, int id) {
                if (recycler == null) {
                    return;
                }
                List<BrandBean.RecordsBean> records = response.getRecords();
                if (brandHomeAdapter == null) {
                    recycler.setLayoutManager(new LinearLayoutManager(getContext()));
                    brandHomeAdapter=new BrandHomeAdapter(mContext,records);
                    brandHomeAdapter.setOnClickListener(bid -> startActivity(new Intent(mContext, BrandHomeActivity.class)
                            .putExtra("bid",bid)));
                    recycler.setAdapter(brandHomeAdapter);
                } else {
                    if (refresh) {
                        brandHomeAdapter.refreshData(records);
                        if (cancel != null) {
                            cancel.finishRefresh(1000);
                        }
                    } else {
                        brandHomeAdapter.addData(records);
                        if (cancel != null) {
                            cancel.finishLoadMore(1000);
                        }
                    }
                }
            }
        });
    }

    private void getBanner() {
        YzApi.getBanner(mContext, "3", new GXCallback<List<BannerBean>>() {
            @Override
            public void onSuccess(List<BannerBean> response, int id) {
                beanList=response;
                bannerView();
            }
        });
    }
    //定义一个方法去初始化Banner控件
    private void bannerView(){
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
        startActivity(new Intent(mContext, HFiveActivity.class).putExtra("banner",beanList.get(position)));

//        if (beanList.get(position).getLinkUrl().contains(ParamsConfigs.IS_REAL_NAME))
//        {
//            startActivity(new Intent(mContext, HFiveActivity.class).putExtra("banner",beanList.get(position)));
//        }else {
//            UserInfo userInfo = SpUtil.readData(mContext);
//            if ("1".equals(userInfo.getIsRealName()))
//            {
//                startActivity(new Intent(mContext, HFiveActivity.class).putExtra("banner",beanList.get(position)));
//            }else {
//                ToastUtils.show("请先实名认证");
//            }
//        }
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.search:
                startActivity(new Intent(mContext, SearchActivity.class).putExtra("type","3"));
                break;
            case R.id.brand_list:
                startActivity(new Intent(mContext, BrandListActivity.class));
                break;
        }
    }

}
