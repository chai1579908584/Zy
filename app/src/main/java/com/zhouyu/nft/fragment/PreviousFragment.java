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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.zhouyu.nft.R;
import com.zhouyu.nft.activity.IntonedDetailsActivity;
import com.zhouyu.nft.adapter.CollectionItemAdapter;
import com.zhouyu.nft.api.GXCallback;
import com.zhouyu.nft.api.YzApi;
import com.zhouyu.nft.bean.PresellBean;

import java.util.List;

public class PreviousFragment extends Fragment {

    RecyclerView recyclerView;
    int page = 1;
    CollectionItemAdapter marketAdapter;
    SmartRefreshLayout mRefreshLayout;

    Context mContext;

    public PreviousFragment(Context context) {
        mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_previous, container, false);


        recyclerView = inflate.findViewById(R.id.recyclerView);

        requestData(true, null);

        mRefreshLayout = inflate.findViewById(R.id.refreshLayout);
        //下拉刷新
        mRefreshLayout.setOnRefreshListener(refreshLayout -> {
            requestData(true, refreshLayout);
        });
        //上拉加载更多
        mRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            requestData(false, refreshLayout);
        });
        return inflate;
    }

    private void requestData(boolean refresh, RefreshLayout cancel) {
        if (refresh) {
            page = 1;
        } else {
            ++page;
        }
        YzApi.getPrevious(mContext, page, new GXCallback<PresellBean>() {
            @Override
            public void onSuccess(PresellBean response, int id) {
                List<PresellBean.RecordsBean> records = response.getRecords();
                if (recyclerView == null) {
                    return;
                }
                if (marketAdapter == null) {
                    recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
                    marketAdapter = new CollectionItemAdapter(mContext, records);
                    marketAdapter.setOnClickListener(gid -> startActivity(new Intent(mContext, IntonedDetailsActivity.class).putExtra("gid", gid).putExtra("type","0")));
                    recyclerView.setAdapter(marketAdapter);
                } else {
                    if (refresh) {
                        marketAdapter.refreshData(records);
                        if (cancel != null) {
                            cancel.finishRefresh(1000);
                        }
                    } else {
                        marketAdapter.addData(records);
                        if (cancel != null) {
                            cancel.finishLoadMore(1000);
                        }
                    }
                }
            }
        });
    }
}
