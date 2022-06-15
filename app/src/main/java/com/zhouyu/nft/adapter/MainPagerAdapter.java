package com.zhouyu.nft.adapter;

import android.content.Context;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.zhouyu.nft.fragment.BrandFragment;
import com.zhouyu.nft.fragment.DaoFragment;
import com.zhouyu.nft.fragment.HomeFragment;
import com.zhouyu.nft.fragment.MarketFragment;
import com.zhouyu.nft.fragment.MeFragment;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class MainPagerAdapter extends FragmentStatePagerAdapter {

    private final List<Fragment> mList = new ArrayList<>(5);

    public MainPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        mList.add(new HomeFragment(context));
        mList.add(new MarketFragment(context));
        mList.add(new BrandFragment(context));
        mList.add(new DaoFragment(context));
        mList.add(new MeFragment(context));
    }

    @Override
    public @NotNull Fragment getItem(int position) {
        return mList.get(position);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

//    public void refreshYanJiuYuan(){
//        if (mList.size() >= 2) {
//            YanJiuYuanFragment fragment = (YanJiuYuanFragment) mList.get(1);
//            if (fragment != null) {
//                fragment.refresh();
//            }
//        }
//    }

}
