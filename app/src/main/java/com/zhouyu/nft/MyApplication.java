package com.zhouyu.nft;

import android.app.Application;
import android.content.Context;

public class MyApplication extends Application {


    public static Context context;//全局上下文

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    //获取全局的上下文
    public static Context getContext(){
        return context;
    }
}
