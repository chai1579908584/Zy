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
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.zhouyu.nft.R;
import com.zhouyu.nft.activity.IntonedDetailsActivity;
import com.zhouyu.nft.activity.PayActivity;
import com.zhouyu.nft.adapter.HomeGoodsAdapter;
import com.zhouyu.nft.adapter.UnpaidAdapter;
import com.zhouyu.nft.api.GXCallback;
import com.zhouyu.nft.api.YzApi;
import com.zhouyu.nft.bean.PresellBean;
import com.zhouyu.nft.bean.UnpaidBean;

import java.util.List;

public class UnpaidFragment extends Fragment  {

    Context mContext;
    public UnpaidFragment(Context context){
        mContext=context;
    }
    SmartRefreshLayout mRefreshLayout;
    RecyclerView recycler;
    UnpaidAdapter unpaidAdapter;
    int page=1;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_unpaid, container, false);
        mRefreshLayout=inflate.findViewById(R.id.refreshLayout);
        recycler=inflate.findViewById(R.id.recycler);


        requestData(true,null);

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
        YzApi.getMyOrder(mContext, page, "1",new GXCallback<UnpaidBean>() {
            @Override
            public void onSuccess(UnpaidBean response, int id) {
                List<UnpaidBean.RecordsBean> records = response.getRecords();
                if (recycler == null) {
                    return;
                }
                if (unpaidAdapter == null) {
                    recycler.setLayoutManager(new LinearLayoutManager(mContext));
                    unpaidAdapter=new UnpaidAdapter(mContext,records);
                    unpaidAdapter.setOnClickListener(order -> startActivity(new Intent(mContext, PayActivity.class).putExtra("order",order)));
                    recycler.setAdapter(unpaidAdapter);
                } else {
                    if (refresh) {
                        unpaidAdapter.refreshData(records);
                        if (cancel != null) {
                            cancel.finishRefresh(1000);
                        }
                    } else {
                        unpaidAdapter.addData(records);
                        if (cancel != null) {
                            cancel.finishLoadMore(1000);
                        }
                    }
                }
            }
        });
    }
}
