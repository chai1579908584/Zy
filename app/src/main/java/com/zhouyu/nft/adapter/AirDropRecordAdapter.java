package com.zhouyu.nft.adapter;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.zhouyu.nft.fragment.ReceivedFragment;
import com.zhouyu.nft.fragment.UnclaimedFragment;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class AirDropRecordAdapter extends FragmentStatePagerAdapter {

    private final List<Fragment> mList = new ArrayList<>(2);

    public AirDropRecordAdapter(FragmentManager fm, Context context) {
        super(fm);
        mList.add(new UnclaimedFragment(context));
        mList.add(new ReceivedFragment(context));
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
