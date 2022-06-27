package com.zhouyu.nft.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.zhouyu.nft.bean.SeriesListBean;
import com.zhouyu.nft.util.ScreenPopupWindow;
import com.zhouyu.nft.util.ToastUtils;

import java.util.List;

import okhttp3.Call;

public class AllCollectionActivity extends BaseActivity implements View.OnClickListener{

    ImageView iv_back,screen,noneMessage;
    TextView search;

    SmartRefreshLayout mRefreshLayout;
    RecyclerView recyclerHot;
    int page=1;
    MarkerAdapter markerAdapter;

    List<SeriesListBean> message;
    String framerTypeStr="";
    String showTypeStr="";
    String collectionTypeStr="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_collection);
        iv_back=findViewById(R.id.iv_back);
        search=findViewById(R.id.search);
        screen=findViewById(R.id.screen);
        noneMessage=findViewById(R.id.noneMessage);

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
        screen.setOnClickListener(this);

        getData();
    }

    private void getData() {
         YzApi.getSeriesListByBid(this, new GXCallback<List<SeriesListBean>>() {
             @Override
             public void onSuccess(List<SeriesListBean> response, int id) {
                 message=response;
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
            case R.id.search:
                startActivity(new Intent(AllCollectionActivity.this,SearchActivity.class).putExtra("type","1"));
                break;
            case R.id.screen:
                getSeries();
                break;

        }
    }
    private void getSeries(){
        ScreenPopupWindow screenPopupWindow=new ScreenPopupWindow();
        screenPopupWindow.setOnClickListener(new ScreenPopupWindow.SetOnClick() {
            @Override
            public void onClick(String framerType, String showType, String collectionType) {
                framerTypeStr=framerType;
                showTypeStr=showType;
                collectionTypeStr=collectionType;
                requestData(true,null);
            }
            @Override
            public void onSelect(List<SeriesListBean> message) {
                screenPopupWindow.setData(message);
            }
        });
        screenPopupWindow.initPopupWindow(mContext,message);
    }
    private void requestData(boolean refresh, RefreshLayout cancel) {
        if (refresh) {
            page = 1;
        } else {
            ++page;
        }
        YzApi.getShai(mContext, page,framerTypeStr,showTypeStr,collectionTypeStr,new GXCallback<PresellBean>() {
            @Override
            public void onSuccess(PresellBean response, int id) {
                List<PresellBean.RecordsBean> records = response.getRecords();
                if (recyclerHot == null) {
                    return;
                }
                if (records.size()==0)
                {
                    if (cancel != null) {
                        cancel.finishRefresh(1000);
                        cancel.finishLoadMore(1000);
                    }
                    if (page==1)
                    {
                        mRefreshLayout.setVisibility(View.GONE);
                        noneMessage.setVisibility(View.VISIBLE);
                    }
                    return;
                }
                mRefreshLayout.setVisibility(View.VISIBLE);
                noneMessage.setVisibility(View.GONE);
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