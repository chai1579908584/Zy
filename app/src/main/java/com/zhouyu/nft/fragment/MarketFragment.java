package com.zhouyu.nft.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.zhouyu.nft.R;
import com.zhouyu.nft.activity.IntonedDetailsActivity;
import com.zhouyu.nft.adapter.MarketAdapter;
import com.zhouyu.nft.bean.PresellBean;

import java.util.ArrayList;
import java.util.List;

public class MarketFragment extends Fragment {

    List<PresellBean.RecordsBean> list=new ArrayList<>();

    Context mContext;
    public MarketFragment(Context context){
        mContext=context;
    }

    RecyclerView recycler;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_market, container, false);

        recycler=inflate.findViewById(R.id.recycler);

        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recycler.setLayoutManager(staggeredGridLayoutManager);
        MarketAdapter marketAdapter = new MarketAdapter(mContext, list);
        marketAdapter.setOnClickListener(id -> startActivity(new Intent(mContext, IntonedDetailsActivity.class)));
        recycler.setAdapter(marketAdapter);

        return inflate;
    }
}
