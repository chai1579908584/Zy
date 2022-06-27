package com.zhouyu.nft.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.zhouyu.nft.R;
import com.zhouyu.nft.adapter.BrandHomeAdapter;
import com.zhouyu.nft.adapter.HomeGoodsAdapter;
import com.zhouyu.nft.adapter.MarkerAdapter;
import com.zhouyu.nft.adapter.SearchAdapter;
import com.zhouyu.nft.api.GXCallback;
import com.zhouyu.nft.api.YzApi;
import com.zhouyu.nft.base.BaseActivity;
import com.zhouyu.nft.bean.BrandBean;
import com.zhouyu.nft.bean.PresellBean;
import com.zhouyu.nft.util.SpUtil;
import com.zhouyu.nft.util.ToastUtils;
import com.zhouyu.nft.view.FlowLayout;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends BaseActivity implements View.OnClickListener {

    ImageView iv_back, delete,wu_message;
    EditText search;
    TextView sure;
    RecyclerView recycler;
    RelativeLayout rl_delete;
    List<String> searchData = new ArrayList<>();
    SmartRefreshLayout mRefreshLayout;
    int page=1;
    BrandHomeAdapter brandHomeAdapter;
    HomeGoodsAdapter homeGoodsAdapter;
    MarkerAdapter markerAdapter;

    FlowLayout flow;

    String type;
    String searchStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        iv_back = findViewById(R.id.iv_back);
        delete = findViewById(R.id.delete);
        search = findViewById(R.id.search);
        sure = findViewById(R.id.sure);
        recycler = findViewById(R.id.recycler);
        rl_delete = findViewById(R.id.rl_delete);
        wu_message = findViewById(R.id.wu_message);
        flow = findViewById(R.id.xun_rage);

        iv_back.setOnClickListener(this);
        delete.setOnClickListener(this);
        sure.setOnClickListener(this);


        mRefreshLayout = findViewById(R.id.refreshLayout);
        //下拉刷新
        mRefreshLayout.setOnRefreshListener(refreshLayout -> {
            setType(searchStr,true,refreshLayout);
        });
        //上拉加载更多
        mRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            setType(searchStr,false,refreshLayout);
        });

        type = getIntent().getStringExtra("type");

        if ("3".equals(type)) {
            search.setHint("搜索品牌");

        } else {
            search.setHint("搜索藏品");
        }

        searchData = SpUtil.readSearchData(this);

        setRecycler();

    }

    private void setRecycler() {
        flow.removeAllViews();
        FlowLayout.MarginLayoutParams pa = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        pa.setMargins(0, 20, 40, 0);
        for (int i = 0; i < searchData.size(); i++) {
            TextView item = new TextView(this);
            item.setText(searchData.get(i));
            item.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 12);
            item.setBackgroundResource(R.drawable.shape_corner_d8);
            item.setPadding(40, 10, 40, 10);
            flow.addView(item, pa);
            item.setOnClickListener(v -> {
                String str = item.getText().toString();
                searchData.add(0, item.getText().toString());
                SpUtil.searchData(SearchActivity.this, searchData);
                rl_delete.setVisibility(View.GONE);
                searchStr=str;
                search.setText(searchStr);
                switch (type)
                {
                    case "2":
                        getMarket(str,true,null);
                        break;
                    case "3":
                        getBrand(str,true,null);
                        break;
                    default:
                        getCollection(str,true,null);
                        break;
                }
                closeKeyBord(this);
            });
        }
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.delete:
                SpUtil.deleteSearchData(this);
                searchData = SpUtil.readSearchData(this);
                setRecycler();
                break;
            case R.id.sure:
                searchStr = search.getText().toString();
                if (searchStr.isEmpty()) {
                    ToastUtils.show(this, "请输入要搜索的内容");
                    return;
                }
                searchData.add(0, searchStr);
                SpUtil.searchData(this, searchData);
                rl_delete.setVisibility(View.GONE);
                setType(searchStr,true,null);
                closeKeyBord(this);
                break;
        }

    }

    /**
     * 自动关闭软键盘
     * @param activity
     */
    public static void closeKeyBord(Activity activity) {
        InputMethodManager imm =  (InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if(imm != null) {
            imm.hideSoftInputFromWindow(activity.getWindow().getDecorView().getWindowToken(), 0);
        }
    }
    private void setType(String searchStr,boolean refresh, RefreshLayout cancel){
        switch (type)
        {
            case "2":
                getMarket(searchStr,refresh,cancel);
                break;
            case "3":
                getBrand(searchStr,refresh,cancel);
                break;
            default:
                getCollection(searchStr,refresh,cancel);
                break;
        }
    }
    private void getMarket(String name,boolean refresh, RefreshLayout cancel) {
        if (refresh) {
            page = 1;
        } else {
            ++page;
        }
        YzApi.getSearchIntoned(mContext, name,page, new GXCallback<PresellBean>() {
            @Override
            public void onSuccess(PresellBean response, int id) {
                if (recycler == null) {
                    return;
                }
                List<PresellBean.RecordsBean> records = response.getRecords();
                if (refresh&&records.size()==0) {
                    recycler.setVisibility(View.GONE);
                    wu_message.setVisibility(View.VISIBLE);
                } else {
                    recycler.setVisibility(View.VISIBLE);
                    wu_message.setVisibility(View.GONE);
                }
                if (markerAdapter == null) {
                    StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL) {
                        @Override
                        public boolean canScrollVertically() {
                            return false;
                        }
                    };
                    recycler.setLayoutManager(staggeredGridLayoutManager);
                    markerAdapter = new MarkerAdapter(mContext, records);
                    markerAdapter.setOnClickListener(gid -> startActivity(new Intent(mContext, IntonedDetailsActivity.class).putExtra("gid", gid)
                            .putExtra("type", "0")));
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

    private void getBrand(String name,boolean refresh, RefreshLayout cancel) {
        if (refresh) {
            page = 1;
        } else {
            ++page;
        }
        YzApi.getSearchBrand(this, page,name, new GXCallback<BrandBean>() {
            @Override
            public void onSuccess(BrandBean response, int id) {
                if (recycler == null) {
                    return;
                }
                List<BrandBean.RecordsBean> records = response.getRecords();
                if (refresh&&records.size()==0) {
                    recycler.setVisibility(View.GONE);
                    wu_message.setVisibility(View.VISIBLE);
                } else {
                    recycler.setVisibility(View.VISIBLE);
                    wu_message.setVisibility(View.GONE);
                }
                if (brandHomeAdapter == null) {
                    recycler.setLayoutManager(new LinearLayoutManager(SearchActivity.this));
                    brandHomeAdapter=new BrandHomeAdapter(mContext,records);
                    brandHomeAdapter.setOnClickListener(bid -> startActivity(new Intent(mContext, BrandHomeActivity.class)
                            .putExtra("bid",bid)));
                    recycler.setAdapter(brandHomeAdapter);
                } else {
                    if (refresh) {
                        brandHomeAdapter.refreshData(records);
                        if (cancel != null) {
                            cancel.finishRefresh(1000);
                        }
                    } else {
                        brandHomeAdapter.addData(records);
                        if (cancel != null) {
                            cancel.finishLoadMore(1000);
                        }
                    }
                }
            }
        });
    }

    private void getCollection(String name,boolean refresh, RefreshLayout cancel) {
        if (refresh) {
            page = 1;
        } else {
            ++page;
        }
        YzApi.getSearchIntoned(mContext,name, page, new GXCallback<PresellBean>() {
            @Override
            public void onSuccess(PresellBean response, int id) {
                if (recycler == null) {
                    return;
                }
                List<PresellBean.RecordsBean> records = response.getRecords();
                if (refresh&&records.size()==0) {
                    recycler.setVisibility(View.GONE);
                    wu_message.setVisibility(View.VISIBLE);
                } else {
                    recycler.setVisibility(View.VISIBLE);
                    wu_message.setVisibility(View.GONE);
                }
                if (homeGoodsAdapter == null) {
                    StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL) {
                        @Override
                        public boolean canScrollVertically() {
                            return false;
                        }
                    };
                    recycler.setLayoutManager(staggeredGridLayoutManager);
                    markerAdapter = new MarkerAdapter(mContext, records);
                    markerAdapter.setOnClickListener(gid -> startActivity(new Intent(mContext, IntonedDetailsActivity.class).putExtra("gid", gid)
                            .putExtra("type", "0")));
                    recycler.setAdapter(markerAdapter);
                } else {
                    if (refresh) {
                        homeGoodsAdapter.refreshData(records);
                        if (cancel != null) {
                            cancel.finishRefresh(1000);
                        }
                    } else {
                        homeGoodsAdapter.addData(records);
                        if (cancel != null) {
                            cancel.finishLoadMore(1000);
                        }
                    }
                }
            }
        });
    }

}