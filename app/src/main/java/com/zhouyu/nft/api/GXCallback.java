package com.zhouyu.nft.api;


import android.util.Log;


import com.google.gson.reflect.TypeToken;
import com.zhouyu.nft.MyApplication;
import com.zhouyu.nft.R;
import com.zhouyu.nft.util.GsonUtils;
import com.zhouyu.nft.util.ToastUtils;
import com.zhy.http.okhttp.callback.Callback;
import com.zhy.http.okhttp.utils.Platform;

import org.json.JSONObject;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.Call;
import okhttp3.Response;
import timber.log.Timber;


public abstract class GXCallback<T>  extends Callback<GXResponse<T>> {

    /** 请求成功 **/
    public static final int CODE_SUCCESS = 1000;
    /** token已过期 **/
    public static final int CODE_TOKEN_EXPIRED = 3;

    private Type type;
    private String body;

    public GXCallback() {
        this.type = parseType();
    }

    protected Type parseType(){

        try {
            Type argument = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
            return TypeToken.getParameterized(GXResponse.class, argument).getType();
        }catch (Exception e){
            Timber.e(e);
        }
        return new TypeToken<GXResponse<String>>(){}.getType();
    }

    public Type getType() {
        return type;
    }

    @Override
    public boolean validateReponse(Response response, int id) {
        boolean validateReponse = super.validateReponse(response, id);
        if (!validateReponse) {
            if (response.body() != null) {
                try {
                    body = response.body().string();
                }catch (Exception e){
                    Timber.e(e);
                }
            }
        }
        return validateReponse;
    }

    @Override
    public GXResponse<T> parseNetworkResponse(Response response, final int id) throws Exception {

        assert response.body() != null;
        String data = response.body().string();
        JSONObject json = new JSONObject(data);
        int code = json.getInt("code");
        //String msg = json.getString("msg");

        Log.e("sssssssssss",data);
        if (code == CODE_SUCCESS) {
            return GsonUtils.getGson().fromJson(data, type);
        }else{
            GXResponse<String> o = GsonUtils.getGson().fromJson(data, new TypeToken<GXResponse<String>>() {}.getType());
            sendFailureCallback(o, id);
        }
        return null;
    }

    protected void sendFailureCallback(final GXResponse<String> response, final int id){
        Platform.get().execute(new Runnable() {
            @Override
            public void run() {
                onFailure(response, id);
            }
        });
    }

    @Override
    public void onError(Call call, Exception e, int id) {
        Timber.e(e);
        ToastUtils.show(MyApplication.getContext(), String.valueOf(R.string.network_not_connected));
    }

    @Override
    public void onResponse(GXResponse<T> response, int id) {
        if (response != null) {
            if (response.code == CODE_SUCCESS && response.data != null) {
                onSuccess(response.data, id);
            }else{
                Timber.e("response failure code=%s, data=%s", response.code, response.data);
            }
        }else{
            Timber.w("response is null!!!");
        }
    }

    public abstract void onSuccess(T response, int id);

    public void onFailure(GXResponse<String> response, int id){
        ToastUtils.show(MyApplication.getContext(),response.msg+"(错误码:"+response.code+")");
    }
}
