package com.zhouyu.nft.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.text.TextUtils;

import com.zhouyu.nft.R;


public class DialogUtil {

    /***
     * 获取一个耗时等待对话框
     */
    public static ProgressDialog getWaitDialog(Context context, String message) {
        ProgressDialog waitDialog = new ProgressDialog(context, R.style.Theme_AppCompat_AlertDialog);
        if (!TextUtils.isEmpty(message)) {
            waitDialog.setMessage(message);
        }
        return waitDialog;
    }

}
