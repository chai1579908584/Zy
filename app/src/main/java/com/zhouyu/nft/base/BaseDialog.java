package com.zhouyu.nft.base;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;

import java.lang.ref.WeakReference;

public class BaseDialog extends Dialog {

    private final WeakReference<Activity> mActivityRef;

    public BaseDialog(@NonNull Activity context) {
        super(context);
        mActivityRef = new WeakReference<>(context);
        onInit();
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mActivityRef.clear();
    }

    public boolean isActivityFinish(){
        return mActivityRef == null || mActivityRef.get() == null || mActivityRef.get().isFinishing();
    }

    public Activity getActivity(){
        if (isActivityFinish()) {
            return null;
        }
        return mActivityRef.get();
    }

    @Override
    public void show() {
        if(!isActivityFinish()){
            super.show();
            onShow();
        }
    }

    protected void onInit(){
        centerAndTransparent();
    }

    protected void onShow(){
        matchScreen();
    }

    protected void trasparent(){
        // 透明背景
        getWindow().setBackgroundDrawable(new ColorDrawable(ResourcesCompat.getColor(getContext().getResources(), android.R.color.transparent, null)));
    }

    protected void centerAndTransparent(){
        trasparent();

        // 位置居中
        Window window = getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.CENTER;
        wlp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        window.setAttributes(wlp);
    }

    protected void bottomAndTransparent(){
        trasparent();

        // 底部
        Window window = getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.BOTTOM;
        wlp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        window.setAttributes(wlp);
    }

    protected void matchScreen(){
        // 使宽匹配屏幕
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getWindow().setAttributes(lp);
    }

}
