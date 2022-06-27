package com.zhouyu.nft.util;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.widget.Toast;

import com.zhouyu.nft.MyApplication;

public class ToastUtils {

    private static final Handler handler = new Handler(Looper.getMainLooper());

    /**
     * 是否在 UI 线程
     */
    public static boolean isUiThread() {
        return Looper.getMainLooper().getThread() == Thread.currentThread();
    }

    /**
     * 在 UI 线程执行 {@link Runnable}
     */
    public static void runOnUiThread(Runnable run) {
        if (isUiThread()) {
            run.run();
        } else {
            handler.post(run);
        }
    }

    public static void show(Context context, String str) {
        show(str, Toast.LENGTH_SHORT , context);
    }
    public static void show( String str) {
        show(str, Toast.LENGTH_SHORT , MyApplication.context);
    }


    public static void show(String str, int time ,Context context) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        runOnUiThread(() -> Toast.makeText(context, str, time).show());
    }

    public static void showMessage(Context context,String message){
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
    }


}
