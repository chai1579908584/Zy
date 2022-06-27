package com.zhouyu.nft.api;

import com.zhy.http.okhttp.callback.Callback;

import okhttp3.Call;
import okhttp3.Response;

public class GXResponse<T> extends Callback {

    public int code;
    public String msg;
    public T data;

    @Override
    public Object parseNetworkResponse(Response response, int id) throws Exception {
        return null;
    }

    @Override
    public void onError(Call call, Exception e, int id) {

    }

    @Override
    public void onResponse(Object response, int id) {

    }
}
