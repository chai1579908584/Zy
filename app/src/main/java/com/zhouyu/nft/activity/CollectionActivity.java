package com.zhouyu.nft.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;
import com.zhouyu.nft.R;
import com.zhouyu.nft.adapter.CollectionAdapter;
import com.zhouyu.nft.api.GXCallback;
import com.zhouyu.nft.api.YzApi;
import com.zhouyu.nft.base.BaseActivity;
import com.zhouyu.nft.bean.BannerBean;
import com.zhouyu.nft.bean.UserInfo;
import com.zhouyu.nft.fragment.HomeFragment;
import com.zhouyu.nft.util.GlideImageLoader;
import com.zhouyu.nft.util.ParamsConfigs;
import com.zhouyu.nft.util.SpUtil;
import com.zhouyu.nft.util.ToastUtils;
import com.zhouyu.nft.view.LockableViewPager;

import java.util.List;

public class CollectionActivity extends BaseActivity implements View.OnClickListener, OnBannerListener {

    ImageView iv_back;
    TextView presell,hot_sale,previous;
    LockableViewPager mMainPager;
    Banner slide_pages;
    List<BannerBean> beanList;

    CollectionAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);

        iv_back=findViewById(R.id.iv_back);
        presell=findViewById(R.id.presell);
        hot_sale=findViewById(R.id.hot_sale);
        previous=findViewById(R.id.previous);
        mMainPager=findViewById(R.id.main_pager);
        slide_pages=findViewById(R.id.banner);

        iv_back.setOnClickListener(this);
        presell.setOnClickListener(this);
        hot_sale.setOnClickListener(this);
        previous.setOnClickListener(this);

        mAdapter = new CollectionAdapter(getSupportFragmentManager(),this);
        if (mMainPager==null)
        {
            return;
        }
        mMainPager.setAdapter(mAdapter);
        mMainPager.setSwipeLocked(true);
        mMainPager.setOffscreenPageLimit(mAdapter.getCount());
        mMainPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
            }

            @Override
            public void onPageSelected(int i) {
                switch (i) {
                    case 0:
                        selectTab(presell);
                        break;
                    case 1:
                        selectTab(hot_sale);
                        break;
                    case 2:
                        selectTab(previous);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });
        selectTab(presell);
        mMainPager.setCurrentItem(0);

        getBanner();
    }

    private void getBanner() {
        YzApi.getBanner(mContext, "2", new GXCallback<List<BannerBean>>() {
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
        slide_pages.setOnBannerListener(CollectionActivity.this);
        slide_pages.start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.iv_back:
                finish();
                break;
            case R.id.presell:
                selectTab(presell);
                mMainPager.setCurrentItem(0);
                break;
            case R.id.hot_sale:
                selectTab(hot_sale);
                mMainPager.setCurrentItem(1);
                break;
            case R.id.previous:
                selectTab(previous);
                mMainPager.setCurrentItem(2);
                break;
        }

    }

    private void selectTab(TextView tabView) {
        if (presell!=null&&hot_sale!=null&&previous!=null)
        {
            presell.setTextColor(0xff222629);
            hot_sale.setTextColor(0xff222629);
            previous.setTextColor(0xff222629);
            tabView.setTextColor(0xff3392FB);
        }
    }

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
}