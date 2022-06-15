package com.zhouyu.nft.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.zhouyu.nft.R;
import com.zhouyu.nft.adapter.AirDropRecordAdapter;
import com.zhouyu.nft.adapter.MysteryRecordAdapter;
import com.zhouyu.nft.base.BaseActivity;
import com.zhouyu.nft.view.LockableViewPager;

public class MysteryRecordActivity extends BaseActivity implements View.OnClickListener {

    ImageView iv_back;
    TextView unpaid,completed,open;
    LockableViewPager mMainPager;

    MysteryRecordAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mystery_record);

        iv_back=findViewById(R.id.iv_back);
        unpaid=findViewById(R.id.unpaid);
        completed=findViewById(R.id.completed);
        open=findViewById(R.id.open);
        mMainPager=findViewById(R.id.main_pager);

        iv_back.setOnClickListener(this);
        unpaid.setOnClickListener(this);
        completed.setOnClickListener(this);
        open.setOnClickListener(this);

        mAdapter = new MysteryRecordAdapter(getSupportFragmentManager(),this);
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
                        selectTab(completed);
                        break;
                    case 2:
                        selectTab(open);
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
            case R.id.completed:
                selectTab(completed);
                mMainPager.setCurrentItem(1);
                break;
            case R.id.open:
                selectTab(open);
                mMainPager.setCurrentItem(2);
                break;
        }

    }

    private void selectTab(TextView tabView) {
        if (unpaid!=null&&completed!=null&&open!=null)
        {
            unpaid.setTextColor(0xff222629);
            completed.setTextColor(0xff222629);
            open.setTextColor(0xff222629);
            tabView.setTextColor(0xff3392FB);
        }
    }
}