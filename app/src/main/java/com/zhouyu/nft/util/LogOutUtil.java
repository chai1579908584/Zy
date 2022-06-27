package com.zhouyu.nft.util;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.zhouyu.nft.activity.LoginActivity;
import com.zhouyu.nft.api.GXCallback;
import com.zhouyu.nft.api.YzApi;
import com.zhouyu.nft.base.BaseActivity;

import okhttp3.Call;

public class LogOutUtil {

    public static void logOut(Context mContext){
        YzApi.getLogout(mContext, new GXCallback<String>() {
            @Override
            public void onSuccess(String response, int id) {
                SpUtil.clearSp(mContext);
                Intent intent=new Intent(mContext, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                mContext.startActivity(intent);
                BaseActivity.dismissDialog();
            }
        });
    }

    public static void logOutNoData(Context mContext){
        SpUtil.clearSp(mContext);
        Intent intent=new Intent(mContext, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        mContext.startActivity(intent);
        BaseActivity.dismissDialog();
    }
}
