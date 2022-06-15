package com.zhouyu.nft.activity;


import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.zhouyu.nft.R;
import com.zhouyu.nft.api.GXCallback;
import com.zhouyu.nft.api.YzApi;
import com.zhouyu.nft.base.BaseActivity;
import com.zhouyu.nft.bean.InformationBean;
import com.zhouyu.nft.loadmore.LoadMoreAdapter;

public class InformationActivity extends BaseActivity implements View.OnClickListener {

    ImageView iv_back;
    RecyclerView recyclerView;
    SwipeRefreshLayout mRefreshLayout;
    LoadMoreAdapter mLoadMore;
    int page=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

       // mRefreshLayout=findViewById(R.id.refresh_layout);
        iv_back=findViewById(R.id.iv_back);

        iv_back.setOnClickListener(this);
        // 下拉刷新
//        mRefreshLayout.setColorSchemeResources(R.color.black);
//        mRefreshLayout.setOnRefreshListener(() -> requestData(true));
   //     requestData(true);

    }

    private void requestData(boolean refresh) {
        if (refresh)
        {
            page=1;
        }else {
            ++page;
        }
        YzApi.getInformation(this, page, new GXCallback<InformationBean>() {
            @Override
            public void onSuccess(InformationBean response, int id) {

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