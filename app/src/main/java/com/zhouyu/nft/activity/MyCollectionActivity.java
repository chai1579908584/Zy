package com.zhouyu.nft.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.zhouyu.nft.R;
import com.zhouyu.nft.adapter.CollectionItemAdapter;
import com.zhouyu.nft.api.GXCallback;
import com.zhouyu.nft.api.YzApi;
import com.zhouyu.nft.base.BaseActivity;
import com.zhouyu.nft.bean.PresellBean;

import java.util.List;

import okhttp3.Call;

public class MyCollectionActivity extends BaseActivity implements View.OnClickListener {

    ImageView iv_back,wu_message;
    RecyclerView recyclerView;
    int page = 1;
    CollectionItemAdapter marketAdapter;
    SmartRefreshLayout mRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_collection);

        iv_back = findViewById(R.id.iv_back);
        recyclerView = findViewById(R.id.recyclerView);
        wu_message = findViewById(R.id.wu_message);

        requestData(true, null);

        mRefreshLayout = findViewById(R.id.refreshLayout);
        //下拉刷新
        mRefreshLayout.setOnRefreshListener(refreshLayout -> {
            requestData(true, refreshLayout);
        });
        //上拉加载更多
        mRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            requestData(false, refreshLayout);
        });

        iv_back.setOnClickListener(this);
    }

    private void requestData(boolean refresh, RefreshLayout cancel) {
        if (refresh) {
            page = 1;
        } else {
            ++page;
        }
        YzApi.getMyProductList(mContext, page, new GXCallback<PresellBean>() {
            @Override
            public void onSuccess(PresellBean response, int id) {
                List<PresellBean.RecordsBean> records = response.getRecords();
                if (recyclerView == null) {
                    return;
                }
                if (marketAdapter == null) {
                    if (records.size()==0)
                    {
                        wu_message.setVisibility(View.VISIBLE);
                        return;
                    }
                    recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
                    marketAdapter = new CollectionItemAdapter(mContext, records);
                    marketAdapter.setOnClickListener(id1 -> {
                    });
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

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.iv_back:
                finish();
                break;
        }
    }
}