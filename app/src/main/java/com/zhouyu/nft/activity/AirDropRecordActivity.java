package com.zhouyu.nft.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.zhouyu.nft.R;
import com.zhouyu.nft.adapter.AirDropRecordAdapter;
import com.zhouyu.nft.base.BaseActivity;
import com.zhouyu.nft.view.LockableViewPager;

public class AirDropRecordActivity extends BaseActivity implements View.OnClickListener {

    ImageView iv_back;
    TextView unclaimed,received;
    LockableViewPager mMainPager;

    AirDropRecordAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_airdrop_record);

        iv_back=findViewById(R.id.iv_back);
        unclaimed=findViewById(R.id.unclaimed);
        received=findViewById(R.id.received);
        mMainPager=findViewById(R.id.main_pager);

        iv_back.setOnClickListener(this);
        unclaimed.setOnClickListener(this);
        received.setOnClickListener(this);

        mAdapter = new AirDropRecordAdapter(getSupportFragmentManager(),this);
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
                        selectTab(unclaimed);
                        break;
                    case 1:
                        selectTab(received);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });
        selectTab(unclaimed);
        mMainPager.setCurrentItem(0);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.iv_back:
                finish();
                break;
            case R.id.unclaimed:
                selectTab(unclaimed);
                mMainPager.setCurrentItem(0);
                break;
            case R.id.received:
                selectTab(received);
                mMainPager.setCurrentItem(1);
                break;
        }

    }

    private void selectTab(TextView tabView) {
        if (unclaimed!=null&&received!=null)
        {
            unclaimed.setTextColor(0xff222629);
            received.setTextColor(0xff222629);
            tabView.setTextColor(0xff3392FB);
        }
    }
}