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
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.zhouyu.nft.R;
import com.zhouyu.nft.activity.IntonedDetailsActivity;
import com.zhouyu.nft.adapter.MarketAdapter;
import com.zhouyu.nft.api.GXCallback;
import com.zhouyu.nft.api.YzApi;
import com.zhouyu.nft.bean.PresellBean;
import com.zhouyu.nft.loadmore.LoadMoreAdapter;
import com.zhouyu.nft.loadmore.LoadMoreWrapper;

import java.util.ArrayList;
import java.util.List;

public class PresellFragment extends Fragment  {

    SwipeRefreshLayout mRefreshLayout;
    RecyclerView recyclerView;
    int page=1;
    LoadMoreAdapter mLoadMore;
    List<PresellBean.RecordsBean> list=new ArrayList<>();
    MarketAdapter marketAdapter;

    Context mContext;
    public PresellFragment(Context context){
        mContext=context;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_presell, container, false);

        mRefreshLayout=inflate.findViewById(R.id.refresh_layout);

        recyclerView=inflate.findViewById(R.id.recyclerView);

        // 下拉刷新
        mRefreshLayout.setColorSchemeResources(R.color.teal_200);
        mRefreshLayout.setOnRefreshListener(() -> requestData(true));

        requestData(true);

        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        marketAdapter = new MarketAdapter(mContext, list);
        marketAdapter.setOnClickListener(id -> startActivity(new Intent(mContext, IntonedDetailsActivity.class)));
        recyclerView.setAdapter(marketAdapter);


        return inflate;
    }

    private void requestData(boolean refresh) {

        if (refresh)
        {
            page=1;
        }else {
            ++page;
        }
        YzApi.getPresell(mContext, page, new GXCallback<PresellBean>() {
            @Override
            public void onSuccess(PresellBean response, int id) {
             if (page==1)
             {
                marketAdapter.refreshData(response.getRecords());
             }else {
                 marketAdapter.addData(response.getRecords());
             }
                mRefreshLayout.setRefreshing(false);
             if (page>response.getPages())
             {
                 mLoadMore.setNoMoreView(R.layout.nomore);
                 mLoadMore.canScroll();
             }
                // 加载更多
                mLoadMore = LoadMoreWrapper.with(marketAdapter)
                        .setListener(enabled -> requestData(false))
                        .into(recyclerView);
            }
        });
    }
}
