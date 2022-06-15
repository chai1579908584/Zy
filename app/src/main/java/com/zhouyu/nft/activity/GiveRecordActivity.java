package com.zhouyu.nft.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.zhouyu.nft.R;
import com.zhouyu.nft.adapter.GiveRecordAdapter;
import com.zhouyu.nft.base.BaseActivity;
import com.zhouyu.nft.view.LockableViewPager;

public class GiveRecordActivity extends BaseActivity implements View.OnClickListener {

    ImageView iv_back;
    TextView out,reception;
    LockableViewPager mMainPager;

    GiveRecordAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_give_record);

        iv_back=findViewById(R.id.iv_back);
        out=findViewById(R.id.out);
        reception=findViewById(R.id.reception);
        mMainPager=findViewById(R.id.main_pager);

        iv_back.setOnClickListener(this);
        out.setOnClickListener(this);
        reception.setOnClickListener(this);

        mAdapter = new GiveRecordAdapter(getSupportFragmentManager(),this);
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
                        selectTab(out);
                        break;
                    case 1:
                        selectTab(reception);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });
        selectTab(out);
        mMainPager.setCurrentItem(0);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.iv_back:
                finish();
                break;
            case R.id.out:
                selectTab(out);
                mMainPager.setCurrentItem(0);
                break;
            case R.id.reception:
                selectTab(reception);
                mMainPager.setCurrentItem(1);
                break;
        }

    }

    private void selectTab(TextView tabView) {
        if (out!=null&&reception!=null)
        {
            out.setTextColor(0xff222629);
            reception.setTextColor(0xff222629);
            tabView.setTextColor(0xff3392FB);
        }
    }
}