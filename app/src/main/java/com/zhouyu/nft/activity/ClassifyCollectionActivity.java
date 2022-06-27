package com.zhouyu.nft.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.zhouyu.nft.R;
import com.zhouyu.nft.adapter.HomeGoodsAdapter;
import com.zhouyu.nft.adapter.MarkerAdapter;
import com.zhouyu.nft.api.GXCallback;
import com.zhouyu.nft.api.YzApi;
import com.zhouyu.nft.base.BaseActivity;
import com.zhouyu.nft.bean.PresellBean;

import java.util.List;

public class ClassifyCollectionActivity extends BaseActivity implements View.OnClickListener{

    ImageView iv_back;
    TextView search,tv_title;

    SmartRefreshLayout mRefreshLayout;
    RecyclerView recyclerHot;
    int page=1;
    MarkerAdapter markerAdapter;
    String type;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classify_collection);
        iv_back=findViewById(R.id.iv_back);
        search=findViewById(R.id.search);
        tv_title=findViewById(R.id.tv_title);

        type = getIntent().getStringExtra("type");
        switch (type)
        {
            case "1":
                tv_title.setText("3D藏品");
                break;
            case "2":
                tv_title.setText("视频藏品");
                break;
            case "3":
                tv_title.setText("音频藏品");
                break;
            default:
                tv_title.setText("图片藏品");
                break;
        }

        recyclerHot = findViewById(R.id.recyclerHot);

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

        iv_back.setOnClickListener(this);
        search.setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.iv_back:
                finish();
                break;
            case R.id.search:
                startActivity(new Intent(ClassifyCollectionActivity.this,SearchActivity.class).putExtra("type","1"));
                break;
        }
    }
    private void requestData(boolean refresh, RefreshLayout cancel) {
        if (refresh) {
            page = 1;
        } else {
            ++page;
        }
        YzApi.getIntonedClassify(mContext, page, type,new GXCallback<PresellBean>() {
            @Override
            public void onSuccess(PresellBean response, int id) {
                List<PresellBean.RecordsBean> records = response.getRecords();
                if (recyclerHot == null) {
                    return;
                }
                if (markerAdapter == null) {
                    recyclerHot.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
                    markerAdapter = new MarkerAdapter(mContext,records);
                    markerAdapter.setOnClickListener(gid -> startActivity(new Intent(mContext, IntonedDetailsActivity.class).putExtra("gid",gid)
                            .putExtra("type","0")));
                    recyclerHot.setAdapter(markerAdapter);
                } else {
                    if (refresh) {
                        markerAdapter.refreshData(records);
                        if (cancel != null) {
                            cancel.finishRefresh(1000);
                        }
                    } else {
                        markerAdapter.addData(records);
                        if (cancel != null) {
                            cancel.finishLoadMore(1000);
                        }
                    }
                }
            }
        });
    }
}