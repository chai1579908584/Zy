package com.zhouyu.nft.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.zhouyu.nft.R;
import com.zhouyu.nft.activity.IntonedDetailsActivity;
import com.zhouyu.nft.adapter.MarkerAdapter;
import com.zhouyu.nft.api.GXCallback;
import com.zhouyu.nft.api.YzApi;
import com.zhouyu.nft.bean.PresellBean;

import java.util.List;


public class BoutiqueFragment extends Fragment {

    Context mContext;
    String bid;
    public BoutiqueFragment(Context context,String bid){
        mContext=context;
        this.bid=bid;
    }

    SmartRefreshLayout mRefreshLayout;
    RecyclerView recycler;
    int page=1;
    MarkerAdapter markerAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_boutique, container, false);

        recycler =inflate. findViewById(R.id.recycler);

        requestData(true,null);

        mRefreshLayout =inflate. findViewById(R.id.refreshLayout);
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
        YzApi.getAllListByBrand(mContext, page,bid,new GXCallback<PresellBean>() {
            @Override
            public void onSuccess(PresellBean response, int id) {
                List<PresellBean.RecordsBean> records = response.getRecords();
                if (recycler == null) {
                    return;
                }
                if (markerAdapter == null) {
                    recycler.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
                    markerAdapter = new MarkerAdapter(mContext,records);
                    markerAdapter.setOnClickListener(gid -> startActivity(new Intent(mContext, IntonedDetailsActivity.class).putExtra("gid",gid)
                            .putExtra("type","0")));
                    recycler.setAdapter(markerAdapter);
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
