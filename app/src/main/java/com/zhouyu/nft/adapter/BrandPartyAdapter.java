package com.zhouyu.nft.adapter;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.zhouyu.nft.fragment.BoutiqueFragment;
import com.zhouyu.nft.fragment.MysteryFragment;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class BrandPartyAdapter extends FragmentStatePagerAdapter {

    private final List<Fragment> mList = new ArrayList<>(2);

    public BrandPartyAdapter(FragmentManager fm, Context context ) {
        super(fm);
        mList.add(new BoutiqueFragment(context));
        mList.add(new MysteryFragment(context));
    }

    @Override
    public @NotNull Fragment getItem(int position) {
        return mList.get(position);
    }

    @Override
    public int getCount() {
        return mList.size();
    }


}
