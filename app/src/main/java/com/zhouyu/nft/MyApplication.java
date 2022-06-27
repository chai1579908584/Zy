package com.zhouyu.nft;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

import com.tencent.bugly.crashreport.CrashReport;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.zhouyu.nft.util.ParamsConfigs;
import com.zhouyu.nft.util.SpUtil;

public class MyApplication extends Application {


    @SuppressLint("StaticFieldLeak")
    public static Context context;//全局上下文

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        //初始化Bugly
        CrashReport.initCrashReport(getApplicationContext(), "20580505be", false);
    }

    //获取全局的上下文
    public static Context getContext(){
        return context;
    }
}
