package com.zhouyu.nft.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.zhouyu.nft.R;
import com.zhouyu.nft.adapter.BrandHomeAdapter;
import com.zhouyu.nft.adapter.BrandListAdapter;
import com.zhouyu.nft.adapter.HomeGoodsAdapter;
import com.zhouyu.nft.api.GXCallback;
import com.zhouyu.nft.api.YzApi;
import com.zhouyu.nft.base.BaseActivity;
import com.zhouyu.nft.bean.BrandBean;
import com.zhouyu.nft.bean.PresellBean;

import java.util.ArrayList;
import java.util.List;

public class BrandListActivity extends BaseActivity implements View.OnClickListener {

    ImageView iv_back;
    RecyclerView recycler;
    SmartRefreshLayout mRefreshLayout;
    int page=1;
    BrandListAdapter brandListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_list);

        iv_back=findViewById(R.id.iv_back);
        recycler=findViewById(R.id.recycler);


        iv_back.setOnClickListener(this);

        iv_back.setOnClickListener(this);

        requestData(true,null);

        mRefreshLayout = findViewById(R.id.refreshLayout);
        //下拉刷新
        mRefreshLayout.setOnRefreshListener(refreshLayout -> {
            requestData(true, refreshLayout);
        });
        //上拉加载更多
        mRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            requestData(false, refreshLayout);
        });
    }

    private void requestData(boolean refresh, RefreshLayout cancel) {
        if (refresh) {
            page = 1;
        } else {
            ++page;
        }
        YzApi.getBrand(mContext, page, new GXCallback<BrandBean>() {
            @Override
            public void onSuccess(BrandBean response, int id) {
                if (recycler == null) {
                    return;
                }
                List<BrandBean.RecordsBean> records = response.getRecords();
                if (brandListAdapter == null) {
                    recycler.setLayoutManager(new LinearLayoutManager(BrandListActivity.this));
                    brandListAdapter=new BrandListAdapter(BrandListActivity.this,records);
                    brandListAdapter.setOnClickListener(bid -> startActivity(new Intent(mContext, BrandHomeActivity.class)
                            .putExtra("bid",bid)));
                    recycler.setAdapter(brandListAdapter);
                } else {
                    if (refresh) {
                        brandListAdapter.refreshData(records);
                        if (cancel != null) {
                            cancel.finishRefresh(1000);
                        }
                    } else {
                        brandListAdapter.addData(records);
                        if (cancel != null) {
                            cancel.finishLoadMore(1000);
                        }
                    }
                }
            }
        });
    }

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