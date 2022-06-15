package com.zhouyu.nft.adapter;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.zhouyu.nft.fragment.OutFragment;
import com.zhouyu.nft.fragment.ReceptionFragment;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class GiveRecordAdapter extends FragmentStatePagerAdapter {

    private final List<Fragment> mList = new ArrayList<>(2);

    public GiveRecordAdapter(FragmentManager fm, Context context) {
        super(fm);
        mList.add(new OutFragment(context));
        mList.add(new ReceptionFragment(context));
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
