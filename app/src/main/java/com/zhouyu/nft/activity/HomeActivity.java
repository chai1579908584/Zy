package com.zhouyu.nft.activity;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.zhouyu.nft.R;
import com.zhouyu.nft.adapter.MainPagerAdapter;
import com.zhouyu.nft.base.BaseActivity;
import com.zhouyu.nft.util.ParamsConfigs;
import com.zhouyu.nft.util.SpUtil;
import com.zhouyu.nft.view.LockableViewPager;

public class HomeActivity extends BaseActivity {

    LockableViewPager mMainPager;
    TextView tvHome;
    TextView tvMarket;
    TextView tvBrand;
    TextView tvDao;
    TextView tvMe;
    MainPagerAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setEnableDoubleBackExit(true);
        makeStatusBarTransparent(HomeActivity.this);

        if (SpUtil.isLogin(getApplicationContext()))
        {
            Intent intent=new Intent(HomeActivity.this,LoginActivity.class);
            startActivity(intent);
            finish();
        }
        initView();
        initClick();
    }

    private void initView() {
        mMainPager=findViewById(R.id.main_pager);
        tvHome = findViewById(R.id.tv_tab_home);
        tvMarket = findViewById(R.id.tv_tab_market);
        tvBrand = findViewById(R.id.tv_tab_brand);
        tvDao = findViewById(R.id.tv_tab_dao);
        tvMe = findViewById(R.id.tv_tab_me);
        mAdapter = new MainPagerAdapter(getSupportFragmentManager(),this);
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
                        selectTab(tvHome);
                        break;
                    case 1:
                        selectTab(tvMarket);
                        break;
                    case 2:
                        selectTab(tvBrand);
                        break;
                    case 3:
                        selectTab(tvDao);
                        break;
                    case 4:
                        selectTab(tvMe);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });
        selectTab(tvHome);
        mMainPager.setCurrentItem(0);

        tvHome.setOnClickListener(v -> {
            selectTab(tvHome);
            mMainPager.setCurrentItem(0);
        });
        tvMarket.setOnClickListener(v -> {
            selectTab(tvMarket);
            mMainPager.setCurrentItem(1);
        });
        tvBrand.setOnClickListener(v -> {
            selectTab(tvBrand);
            mMainPager.setCurrentItem(2);
        });
        tvDao.setOnClickListener(v -> {
            selectTab(tvDao);
            mMainPager.setCurrentItem(3);
        });
        tvMe.setOnClickListener(v -> {
            selectTab(tvMe);
            mMainPager.setCurrentItem(4);
        });

    }

    private void initClick() {
    }

    private void selectTab(TextView tabView) {
        if (tvHome!=null&&tvMarket!=null&&tvBrand!=null&&tvDao!=null&&tvMe!=null)
        {
            tvHome.setSelected(false);
            tvHome.setTextColor(0xff8D8D8D);
            tvMarket.setSelected(false);
            tvMarket.setTextColor(0xff8D8D8D);
            tvBrand.setSelected(false);
            tvBrand.setTextColor(0xff8D8D8D);
            tvDao.setSelected(false);
            tvDao.setTextColor(0xff8D8D8D);
            tvMe.setSelected(false);
            tvMe.setTextColor(0xff8D8D8D);
            tabView.setSelected(true);
            tabView.setTextColor(0xff3392FB);
        }
    }
}