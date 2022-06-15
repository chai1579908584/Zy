package com.zhouyu.nft.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.zhouyu.nft.R;
import com.zhouyu.nft.adapter.BuyRecordAdapter;
import com.zhouyu.nft.base.BaseActivity;
import com.zhouyu.nft.view.LockableViewPager;

public class BuyRecordActivity extends BaseActivity implements View.OnClickListener {

    ImageView iv_back;
    TextView unpaid,accomplish;
    LockableViewPager mMainPager;

    BuyRecordAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_record);

        iv_back=findViewById(R.id.iv_back);
        unpaid=findViewById(R.id.unpaid);
        accomplish=findViewById(R.id.accomplish);
        mMainPager=findViewById(R.id.main_pager);

        iv_back.setOnClickListener(this);
        unpaid.setOnClickListener(this);
        accomplish.setOnClickListener(this);

        mAdapter = new BuyRecordAdapter(getSupportFragmentManager(),this);
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
                        selectTab(unpaid);
                        break;
                    case 1:
                        selectTab(accomplish);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });
        selectTab(unpaid);
        mMainPager.setCurrentItem(0);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.iv_back:
                finish();
                break;
            case R.id.unpaid:
                selectTab(unpaid);
                mMainPager.setCurrentItem(0);
                break;
            case R.id.accomplish:
                selectTab(accomplish);
                mMainPager.setCurrentItem(1);
                break;
        }

    }

    private void selectTab(TextView tabView) {
        if (unpaid!=null&&accomplish!=null)
        {
            unpaid.setTextColor(0xff222629);
            accomplish.setTextColor(0xff222629);
            tabView.setTextColor(0xff3392FB);
        }
    }
}