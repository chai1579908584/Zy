package com.zhouyu.nft.activity;

import android.annotation.SuppressLint;
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
import com.zhouyu.nft.adapter.CalenderAdapter;
import com.zhouyu.nft.api.GXCallback;
import com.zhouyu.nft.api.YzApi;
import com.zhouyu.nft.base.BaseActivity;
import com.zhouyu.nft.bean.CalenderBean;

import java.util.List;

public class CalendarActivity extends BaseActivity implements View.OnClickListener {

    ImageView iv_back;
    SmartRefreshLayout mRefreshLayout;
    RecyclerView recyclerView;
    int page = 1;

    CalenderAdapter calenderAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        iv_back = findViewById(R.id.iv_back);
        recyclerView = findViewById(R.id.recyclerView);

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

    private void requestData(boolean refresh, RefreshLayout cancel) {
        if (refresh) {
            page = 1;
        } else {
            ++page;
        }
        YzApi.getCalender(mContext, page, new GXCallback<CalenderBean>() {
            @Override
            public void onSuccess(CalenderBean response, int id) {
                List<CalenderBean.RecordsBean> records = response.getRecords();
                if (recyclerView == null||records.size()==0) {
                    if (cancel != null) {
                        cancel.finishRefresh(1000);
                        cancel.finishLoadMore(1000);
                    }
                    return;
                }
                if (calenderAdapter == null) {
                    recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
                    calenderAdapter = new CalenderAdapter(mContext,records);
                    calenderAdapter.setOnClickListener(gid -> startActivity(new Intent(mContext, IntonedDetailsActivity.class).putExtra("gid",gid)
                            .putExtra("type","0")));
                    recyclerView.setAdapter(calenderAdapter);
                } else {
                    if (refresh) {
                        calenderAdapter.refreshData(records);
                        if (cancel != null) {
                            cancel.finishRefresh(1000);
                        }
                    } else {
                        calenderAdapter.addData(records);
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
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
        }
    }
}