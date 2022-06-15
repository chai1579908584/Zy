package com.zhouyu.nft.adapter;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.zhouyu.nft.fragment.CompletedMysteryFragment;
import com.zhouyu.nft.fragment.OpenMysteryFragment;
import com.zhouyu.nft.fragment.ReceivedFragment;
import com.zhouyu.nft.fragment.UnclaimedFragment;
import com.zhouyu.nft.fragment.UnpaidMysteryFragment;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class MysteryRecordAdapter extends FragmentStatePagerAdapter {

    private final List<Fragment> mList = new ArrayList<>(3);

    public MysteryRecordAdapter(FragmentManager fm, Context context) {
        super(fm);
        mList.add(new UnpaidMysteryFragment(context));
        mList.add(new CompletedMysteryFragment(context));
        mList.add(new OpenMysteryFragment(context));
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
