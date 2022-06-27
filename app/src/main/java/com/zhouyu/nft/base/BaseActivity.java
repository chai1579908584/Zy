package com.zhouyu.nft.base;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.zhouyu.nft.R;
import com.zhouyu.nft.util.LoadingDialog;
import com.zhouyu.nft.util.StatusBarUtils;
import com.zhouyu.nft.util.ToastUtils;


public abstract class BaseActivity extends AppCompatActivity implements BaseView{

    public static boolean hasActivityResumed = false;

    protected AppCompatActivity mContext;
    private boolean mResumed;
    private boolean mStopped = true;

    private long mDoubleBackExitTime = 0;
    private String mDoubleBackExitText = "再按一次退出";
    private boolean mEnableDoubleBackExit = false;

    private BackListener mBackListener;

    private View backView;
    private View loadingView;
    private View errorView;
    private View emptyView;

    public static Dialog dialog;

    private ProgressDialog mDialog;

    protected void beforeBind(){}

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        loadingView = findViewById(R.id.layout_loading);

        StatusBarUtils.setLightStatusBar(mContext);

        beforeBind();

    }

    public static void makeStatusBarTransparent(Activity activity) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            return;
        }
        Window window = activity.getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            int option = window.getDecorView().getSystemUiVisibility() | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
            window.getDecorView().setSystemUiVisibility(option);
            window.setStatusBarColor(Color.TRANSPARENT);
        } else {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    @LayoutRes public int getLayoutEmpty(){
        return 0;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    public void showBackView(){
        if (backView != null) {
            backView.setVisibility(View.VISIBLE);
        }
    }

    public void hideBackView(){
        if (backView != null) {
            backView.setVisibility(View.GONE);
        }
    }

    public void showLoadingView() {
        hideBaseView();
        showBackView();
        if (loadingView != null) {
            loadingView.setVisibility(View.VISIBLE);
        }
    }

    public void hideLoadingView() {
        hideBackView();
        if (loadingView != null) {
            loadingView.setVisibility(View.GONE);
        }
    }

    public void showErrorView() {
        hideBaseView();
        showBackView();
        if (errorView != null) {
            errorView.setVisibility(View.VISIBLE);
        }
    }

    public void hideErrorView() {
        hideBackView();
        if (errorView != null) {
            errorView.setVisibility(View.GONE);
        }
    }

    public void showEmptyView() {
        hideBaseView();
        if (emptyView != null) {
            emptyView.setVisibility(View.VISIBLE);
        }
    }

    public void hideEmptyView() {
        if (emptyView != null) {
            emptyView.setVisibility(View.GONE);
        }
    }

    public void hideBaseView(){
        hideLoadingView();
        hideErrorView();
        hideEmptyView();
        hideBackView();
    }

//    @LayoutRes
//    protected abstract int getLayoutId();

    /**
     * 需要接收事件 重写该方法 并返回true
     */
    protected boolean regEvent() {
        return false;
    }

    /**
     * 点击重试
     */
    protected void onRetry() {}

    @Override
    protected void onStart() {
        super.onStart();
        mStopped = false;
    }

    @Override
    protected void onStop() {
        super.onStop();
        mStopped = true;
    }


    @Override
    protected void onPause() {
        super.onPause();
        mResumed = false;
        hasActivityResumed = false;
    }


    /**
     * 是否启用 “再按一次退出”
     *
     * @param enable
     */
    public void setEnableDoubleBackExit(boolean enable) {
        mEnableDoubleBackExit = enable;
    }


    @Override
    public void onBackPressed() {
        if (mBackListener != null && mBackListener.onBackPressed()) {
            return;
        }
        // 再按一次退出程序
        if (mEnableDoubleBackExit) {
            long curTime = System.currentTimeMillis();
            if (curTime - mDoubleBackExitTime > 2000) {
                ToastUtils.show(mContext,mDoubleBackExitText);
                mDoubleBackExitTime = curTime;
                return;
            }
        }
        super.onBackPressed();
    }

    public boolean isActivityResumed() {
        return mResumed;
    }

    public boolean isActivityStopped() {
        return mStopped;
    }

//    public boolean isDestroy(){
//        return isFinishing() || isDestroyed();
//    }

    public void setBackListener(BackListener listener) {
        mBackListener = listener;
    }

    public interface BackListener {
        boolean onBackPressed();
    }

    public void showDialog(){
       //加载弹窗
        LoadingDialog.Builder loadBuilder = new LoadingDialog.Builder(this)
                .setCancelable(true)//返回键是否可点击
                .setCancelOutside(false);//窗体外是否可点击
        dialog = loadBuilder.create();
        dialog.show();//显示弹窗
    }

    public static void dismissDialog(){
        if (dialog!=null)
        {
            dialog.dismiss();
        }
    }


//    public ProgressDialog showWaitDialog(@StringRes int message) {
//        return showWaitDialog(getString(message));
//    }
//
//    public ProgressDialog showWaitDialog(String message) {
//        return showWaitDialog(message, true);
//    }
//
//    public ProgressDialog showWaitDialog(String message, boolean cancelable) {
//        if (mDialog == null) {
//            mDialog = DialogUtil.getWaitDialog(this, message);
//        }
//        mDialog.setCancelable(cancelable);
//        mDialog.setCanceledOnTouchOutside(false);
//        mDialog.setMessage(message);
//        mDialog.show();
//        return mDialog;
//    }

    public void hideWaitDialog() {
        if (mDialog != null) {
            try {
                mDialog.dismiss();
                mDialog = null;
            } catch (Exception e) {
            }
        }
    }

}
