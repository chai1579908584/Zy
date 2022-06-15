package com.zhouyu.nft.api;


import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.zhouyu.nft.base.BaseView;
import com.zhouyu.nft.loadmore.LoadMoreAdapter;

import org.jetbrains.annotations.Nullable;

import java.lang.ref.WeakReference;

import okhttp3.Call;

public abstract class GXCallbackWrapper<T> extends GXCallback<T>{

    private boolean mRefresh;
    private WeakReference<BaseView> mBaseView;
    private WeakReference<SwipeRefreshLayout> mRefreshLayout;
  //  private WeakReference<RecyclerAdapter> mAdapter;
    private WeakReference<LoadMoreAdapter> mLoadMoreAdapter;
    private boolean isList;

    private RecyclerView.AdapterDataObserver adapterDataObserver;

    public GXCallbackWrapper(BaseView baseView) {
        this(baseView, true, null, null);
        isList = false;
    }

    public GXCallbackWrapper(@Nullable BaseView baseView, boolean refresh, @Nullable SwipeRefreshLayout refreshLayout, @Nullable LoadMoreAdapter loadMoreAdapter) {
        this.mRefresh = refresh;
        if (baseView != null) {
            this.mBaseView = new WeakReference<>(baseView);
        }
        if (refreshLayout != null) {
            this.mRefreshLayout = new WeakReference<>(refreshLayout);
        }
//        if (adapter != null) {
//            this.mAdapter = new WeakReference<>(adapter);
//            registerEmptyObserver();
//        }
        if (loadMoreAdapter != null) {
            this.mLoadMoreAdapter = new WeakReference<>(loadMoreAdapter);
        }
        isList = true;
    }

    public GXCallbackWrapper(boolean refresh, @Nullable SwipeRefreshLayout refreshLayout, @Nullable LoadMoreAdapter loadMoreAdapter) {
        this.mRefresh = refresh;
        if (refreshLayout != null) {
            this.mRefreshLayout = new WeakReference<>(refreshLayout);
        }
//        if (adapter != null) {
//            this.mAdapter = new WeakReference<>(adapter);
//            registerEmptyObserver();
//        }
        if (loadMoreAdapter != null) {
            this.mLoadMoreAdapter = new WeakReference<>(loadMoreAdapter);
        }
        isList = true;
    }
//
//    /**
//     *  显示空数据视图
//     */
//    protected void registerEmptyObserver(){
//        if (mAdapter != null && mAdapter.get() != null && mBaseView != null && mBaseView.get() != null) {
//            RecyclerAdapter adapter = mAdapter.get();
//
//            adapterDataObserver = new RecyclerView.AdapterDataObserver() {
//                @Override
//                public void onChanged() {
//                    super.onChanged();
//                    if (adapter.isEmpty() && mBaseView != null && mBaseView.get() != null) {
//                        BaseView baseView = mBaseView.get();
//                        baseView.showEmptyView();
//                    }
//                }
//            };
//
//            adapter.registerAdapterDataObserver(adapterDataObserver);
//        }
//    }
//
//    protected void unregisterAdapterDataObserver(){
//        if (mAdapter != null && mAdapter.get() != null && adapterDataObserver != null){
//            RecyclerAdapter adapter = mAdapter.get();
//            adapter.unregisterAdapterDataObserver(adapterDataObserver);
//            adapterDataObserver = null;
//        }
//    }

    @Override
    public void onAfter(int id) {
        super.onAfter(id);
        onFinish();
    }

    public void onFinish(){
        finishRefresh();
    //    unregisterAdapterDataObserver();
    }

    protected void finishRefresh(){
        if (mRefreshLayout != null && mRefreshLayout.get() != null) {
            mRefreshLayout.get().setRefreshing(false);
        }
    }

    protected void setRefreshEnabled(boolean enabled){
        if (mRefreshLayout != null && mRefreshLayout.get() != null) {
            mRefreshLayout.get().setEnabled(enabled);
        }
    }

    @Override
    public void onError(Call call, Exception e, int id) {
        super.onError(call, e, id);
        // 第一页加载失败
     //   boolean isEmptyList = mAdapter != null && mAdapter.get() != null && mAdapter.get().isEmpty();
//        if ((!isList || isEmptyList) && mBaseView != null && mBaseView.get() != null) {
//            mBaseView.get().showErrorView();
//        }

        // 加载失败
        if (!mRefresh  && mLoadMoreAdapter != null && mLoadMoreAdapter.get() != null) {
            mLoadMoreAdapter.get().setLoadFailed(true);
        }
    }

    @Override
    public void onSuccess(T response, int id) {
        if (mBaseView != null) {
            BaseView baseView = mBaseView.get();
            if (baseView != null) {
                baseView.hideBaseView();
            }
        }
    }

    public void setHasMore(boolean hasMore){
        if (mLoadMoreAdapter != null && mLoadMoreAdapter.get() != null) {
            mLoadMoreAdapter.get().setLoadMoreEnabled(hasMore);
        }
    }

}
