package com.zhouyu.nft.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.zhouyu.nft.R;
import com.zhouyu.nft.adapter.CollectionAdapter;
import com.zhouyu.nft.adapter.MysteryRecordAdapter;
import com.zhouyu.nft.base.BaseActivity;
import com.zhouyu.nft.view.LockableViewPager;

public class CollectionActivity extends BaseActivity implements View.OnClickListener {

    ImageView iv_back;
    TextView presell,hot_sale,previous;
    LockableViewPager mMainPager;

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
}