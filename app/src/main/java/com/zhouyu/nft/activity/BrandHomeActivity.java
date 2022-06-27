package com.zhouyu.nft.activity;


import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.zhouyu.nft.R;
import com.zhouyu.nft.adapter.BrandPartyAdapter;
import com.zhouyu.nft.adapter.LabelAdapter;
import com.zhouyu.nft.api.GXCallback;
import com.zhouyu.nft.api.YzApi;
import com.zhouyu.nft.base.BaseActivity;
import com.zhouyu.nft.bean.BrandDetailBean;
import com.zhouyu.nft.util.GlideUtil;
import com.zhouyu.nft.view.LockableViewPager;

public class BrandHomeActivity extends BaseActivity implements View.OnClickListener {

    ImageView iv_back, head_image;
    TextView tv_tab_boutique, tv_tab_mystery, name, introduce;
    LockableViewPager mMainPager;
    BrandPartyAdapter brandPartyAdapter;
    RecyclerView recycler;

    String bid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_home);

        makeStatusBarTransparent(BrandHomeActivity.this);

        bid = getIntent().getStringExtra("bid");

        mMainPager = findViewById(R.id.main_pager);
        iv_back = findViewById(R.id.iv_back);
        tv_tab_boutique = findViewById(R.id.tv_tab_boutique);
        tv_tab_mystery = findViewById(R.id.tv_tab_mystery);
        head_image = findViewById(R.id.head_image);
        name = findViewById(R.id.name);
        introduce = findViewById(R.id.introduce);
        recycler = findViewById(R.id.recycler);

        iv_back.setOnClickListener(this);

        brandPartyAdapter = new BrandPartyAdapter(getSupportFragmentManager(), this,bid);
        if (mMainPager == null) {
            return;
        }
        mMainPager.setAdapter(brandPartyAdapter);
        mMainPager.setSwipeLocked(true);
        mMainPager.setOffscreenPageLimit(brandPartyAdapter.getCount());
        mMainPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
            }

            @Override
            public void onPageSelected(int i) {
                switch (i) {
                    case 0:
                        selectTab(tv_tab_boutique);
                        break;
                    case 1:
                        selectTab(tv_tab_mystery);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });
        selectTab(tv_tab_boutique);
        mMainPager.setCurrentItem(0);

        tv_tab_boutique.setOnClickListener(v -> {
            selectTab(tv_tab_boutique);
            mMainPager.setCurrentItem(0);
        });
        tv_tab_mystery.setOnClickListener(v -> {
            selectTab(tv_tab_mystery);
            mMainPager.setCurrentItem(1);
        });

        getData();
    }

    private void getData() {
        YzApi.getBrandDetail(this, bid, new GXCallback<BrandDetailBean>() {
            @Override
            public void onSuccess(BrandDetailBean response, int id) {
                setData(response);
            }
        });
    }

    private void setData(BrandDetailBean response) {
        GlideUtil.GlideCirHead(this, response.getLogoUrl(), head_image, 100);
        name.setText(response.getName());
        introduce.setText(response.getIntroduce());
        recycler.setLayoutManager(new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false));
        recycler.setAdapter(new LabelAdapter(this,response.getSeriesList()));
    }

    private void selectTab(TextView tabView) {
        if (tv_tab_boutique != null && tv_tab_mystery != null) {
            tv_tab_boutique.setBackgroundColor(Color.argb(0, 0, 0, 0));
            tv_tab_boutique.setTextColor(0xff8D8D8D);
            tv_tab_mystery.setBackgroundColor(Color.argb(0, 0, 0, 0));
            tv_tab_mystery.setTextColor(0xff8D8D8D);
            tabView.setBackgroundResource(R.drawable.shape_corner_jianbian);
            ;
            tabView.setTextColor(0xffffffff);
        }
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
        }
    }
}