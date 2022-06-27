package com.zhouyu.nft.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;
import com.zhouyu.nft.R;
import com.zhouyu.nft.activity.AllCollectionActivity;
import com.zhouyu.nft.activity.BrandHomeActivity;
import com.zhouyu.nft.activity.BrandListActivity;
import com.zhouyu.nft.activity.CalendarActivity;
import com.zhouyu.nft.activity.ClassifyCollectionActivity;
import com.zhouyu.nft.activity.CollectionActivity;
import com.zhouyu.nft.activity.HFiveActivity;
import com.zhouyu.nft.activity.InformationActivity;
import com.zhouyu.nft.activity.IntonedDetailsActivity;
import com.zhouyu.nft.adapter.HomeBrandAdapter;
import com.zhouyu.nft.adapter.HomeGoodsAdapter;
import com.zhouyu.nft.adapter.MarkerAdapter;
import com.zhouyu.nft.api.GXCallback;
import com.zhouyu.nft.api.YzApi;
import com.zhouyu.nft.bean.BannerBean;
import com.zhouyu.nft.bean.HomeBean;
import com.zhouyu.nft.bean.PresellBean;
import com.zhouyu.nft.bean.UserInfo;
import com.zhouyu.nft.util.GlideImageLoader;
import com.zhouyu.nft.util.ParamsConfigs;
import com.zhouyu.nft.util.SpUtil;
import com.zhouyu.nft.util.ToastUtils;

import java.util.List;

public class HomeFragment extends Fragment implements OnBannerListener, View.OnClickListener {
    List<BannerBean> beanList;
    Banner slide_pages;
    ImageView information,calendar;
    ImageView collection_image,collection_threed,collection_video,collection_music;
    TextView into_collection, cang_all,pin_all;
    RecyclerView recyclerHot, recyclerGoods, recyclerBrand;
    int page = 1;
    HomeGoodsAdapter hotAdapter;
    MarkerAdapter markerAdapter;
    SmartRefreshLayout mRefreshLayout;
    Context mContext;

    public HomeFragment(Context context) {
        mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_home, container, false);

        information = inflate.findViewById(R.id.information);
        into_collection = inflate.findViewById(R.id.into_collection);
        cang_all = inflate.findViewById(R.id.cang_all);
        recyclerHot = inflate.findViewById(R.id.recyclerHot);
        recyclerGoods = inflate.findViewById(R.id.recyclerView);
        recyclerBrand = inflate.findViewById(R.id.recyclerBrand);
        pin_all = inflate.findViewById(R.id.pin_all);
        calendar = inflate.findViewById(R.id.calendar);
        collection_image = inflate.findViewById(R.id.collection_image);
        collection_threed = inflate.findViewById(R.id.collection_threed);
        collection_video = inflate.findViewById(R.id.collection_video);
        collection_music = inflate.findViewById(R.id.collection_music);

        information.setOnClickListener(this);
        into_collection.setOnClickListener(this);
        cang_all.setOnClickListener(this);
        pin_all.setOnClickListener(this);
        calendar.setOnClickListener(this);
        collection_image.setOnClickListener(this);
        collection_threed.setOnClickListener(this);
        collection_video.setOnClickListener(this);
        collection_music.setOnClickListener(this);

        slide_pages = inflate.findViewById(R.id.banner);

        getBanner();

        getHome();

        requestData(true, null);

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

    private void getHome() {
        YzApi.getHomeMessage(mContext, new GXCallback<HomeBean>() {
            @Override
            public void onSuccess(HomeBean response, int id) {
                setGoods(response);
                setBrand(response);
            }
        });
    }

    private void setBrand(HomeBean response) {
        List<HomeBean.BrandListBean> brandList = response.getBrandList();
        if (brandList == null || brandList.size() == 0) {
            return;
        }
        recyclerBrand.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        HomeBrandAdapter homeBrandAdapter = new HomeBrandAdapter(mContext, brandList);
        homeBrandAdapter.setOnClickListener(bid -> startActivity(new Intent(mContext, BrandHomeActivity.class)
                .putExtra("bid", bid)));
        recyclerBrand.setAdapter(homeBrandAdapter);
    }

    private void setGoods(HomeBean response) {
        List<PresellBean.RecordsBean> goodsList = response.getGoodsList();
        if (goodsList == null || goodsList.size() == 0) {
            return;
        }
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recyclerGoods.setLayoutManager(staggeredGridLayoutManager);
        hotAdapter = new HomeGoodsAdapter(mContext, goodsList);
        hotAdapter.setOnClickListener(gid -> startActivity(new Intent(mContext, IntonedDetailsActivity.class).putExtra("gid", gid)
                .putExtra("type", "0")));
        recyclerGoods.setAdapter(hotAdapter);
    }

    private void requestData(boolean refresh, RefreshLayout cancel) {
        if (refresh) {
            page = 1;
        } else {
            ++page;
        }
        YzApi.getIntonedList(mContext, page,new GXCallback<PresellBean>() {
            @Override
            public void onSuccess(PresellBean response, int id) {
                List<PresellBean.RecordsBean> records = response.getRecords();
                if (recyclerHot == null) {
                    return;
                }
                if (markerAdapter == null) {
                    StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL) {
                        @Override
                        public boolean canScrollVertically() {
                            return false;
                        }
                    };
                    recyclerHot.setLayoutManager(staggeredGridLayoutManager);
                    markerAdapter = new MarkerAdapter(mContext, records);
                    markerAdapter.setOnClickListener(gid -> startActivity(new Intent(mContext, IntonedDetailsActivity.class).putExtra("gid", gid)
                            .putExtra("type", "0")));
                    recyclerHot.setAdapter(markerAdapter);
                } else {
                    if (refresh) {
                        markerAdapter.refreshData(records);
                        if (cancel != null) {
                            cancel.finishRefresh(1000);
                        }
                    } else {
                        markerAdapter.addData(records);
                        if (cancel != null) {
                            cancel.finishLoadMore(1000);
                        }
                    }
                }
            }
        });
    }

    private void getBanner() {
        YzApi.getBanner(mContext, "1", new GXCallback<List<BannerBean>>() {
            @Override
            public void onSuccess(List<BannerBean> response, int id) {
                beanList = response;
                bannerView();
            }
        });
    }

    //定义一个方法去初始化Banner控件
    private void bannerView() {
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
        startActivity(new Intent(mContext, HFiveActivity.class).putExtra("banner", beanList.get(position)));

//        if (beanList.get(position).getLinkUrl().contains(ParamsConfigs.IS_REAL_NAME)) {
//            startActivity(new Intent(mContext, HFiveActivity.class).putExtra("banner", beanList.get(position)));
//        } else {
//            UserInfo userInfo = SpUtil.readData(mContext);
//            if ("1".equals(userInfo.getIsRealName())) {
//                startActivity(new Intent(mContext, HFiveActivity.class).putExtra("banner", beanList.get(position)));
//            } else {
//                ToastUtils.show("请先实名认证");
//            }
//        }
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.information:
                startActivity(new Intent(mContext, InformationActivity.class));
                break;
            case R.id.into_collection:
                startActivity(new Intent(mContext, CollectionActivity.class));
                break;
            case R.id.cang_all:
                startActivity(new Intent(mContext, AllCollectionActivity.class));
                break;
            case R.id.pin_all:
                startActivity(new Intent(mContext, BrandListActivity.class));
                break;
            case R.id.calendar:
                startActivity(new Intent(mContext, CalendarActivity.class));
                break;
            case R.id.collection_image:
                startActivity(new Intent(mContext, ClassifyCollectionActivity.class).putExtra("type","0"));
                break;
            case R.id.collection_threed:
                startActivity(new Intent(mContext, ClassifyCollectionActivity.class).putExtra("type","1"));
                break;
            case R.id.collection_video:
                startActivity(new Intent(mContext, ClassifyCollectionActivity.class).putExtra("type","2"));
                break;
            case R.id.collection_music:
                startActivity(new Intent(mContext, ClassifyCollectionActivity.class).putExtra("type","3"));
                break;
        }
    }

}
