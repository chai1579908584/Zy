package com.zhouyu.nft.activity;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.zhouyu.nft.R;
import com.zhouyu.nft.adapter.InformationAdapter;
import com.zhouyu.nft.api.GXCallback;
import com.zhouyu.nft.api.YzApi;
import com.zhouyu.nft.base.BaseActivity;
import com.zhouyu.nft.bean.BannerBean;
import com.zhouyu.nft.bean.InformationBean;
import com.zhouyu.nft.bean.UserInfo;
import com.zhouyu.nft.util.ParamsConfigs;
import com.zhouyu.nft.util.SpUtil;
import com.zhouyu.nft.util.ToastUtils;

import java.util.List;

public class InformationActivity extends BaseActivity implements View.OnClickListener {

    ImageView iv_back;
    RecyclerView recyclerView;
    SmartRefreshLayout mRefreshLayout;
    int page = 1;
    InformationAdapter informationAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        recyclerView = findViewById(R.id.recyclerView);
        iv_back = findViewById(R.id.iv_back);

        iv_back.setOnClickListener(this);

        requestData(true,null);

        mRefreshLayout = findViewById(R.id.refreshLayout);
        //下拉刷新
        mRefreshLayout.setOnRefreshListener(refreshLayout -> {
            requestData(true,refreshLayout);
        });

        //上拉加载更多
        mRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            requestData(false,refreshLayout);
        });
    }

    private void requestData(boolean refresh,RefreshLayout cancel) {
        if (refresh) {
            page = 1;
        } else {
            ++page;
        }
        YzApi.getInformation(this, page, new GXCallback<InformationBean>() {
            @Override
            public void onSuccess(InformationBean response, int sid) {
                List<InformationBean.RecordsBean> records = response.getRecords();
                if (recyclerView==null)
                {
                    return;
                }
                if (informationAdapter==null)
                {
                    recyclerView.setLayoutManager(new LinearLayoutManager(InformationActivity.this));
                    informationAdapter = new InformationAdapter(InformationActivity.this, records);
                    informationAdapter.setOnClickListener((linkUrl, title) -> {
                        BannerBean bannerBean=new BannerBean();
                        bannerBean.setLinkUrl(linkUrl);
                        bannerBean.setTitle(title);
                        startActivity(new Intent(mContext, HFiveActivity.class).putExtra("banner",bannerBean));
                    });
                    recyclerView.setAdapter(informationAdapter);
                }else
                {
                    if (refresh){
                        informationAdapter.refreshData(records);
                        if (cancel!=null)
                        {
                            cancel.finishRefresh(1000);
                        }
                    }else {
                        informationAdapter.addData(records);
                        if (cancel!=null)
                        {
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
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
        }
    }
}