package com.zhouyu.nft.adapter;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.zhouyu.nft.fragment.AccomplishFragment;
import com.zhouyu.nft.fragment.UnpaidFragment;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class BuyRecordAdapter extends FragmentStatePagerAdapter {

    private final List<Fragment> mList = new ArrayList<>(2);

    public BuyRecordAdapter(FragmentManager fm, Context context) {
        super(fm);
        mList.add(new UnpaidFragment(context));
        mList.add(new AccomplishFragment(context));
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
