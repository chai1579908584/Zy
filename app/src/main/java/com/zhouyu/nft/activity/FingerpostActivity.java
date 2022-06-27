package com.zhouyu.nft.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhouyu.nft.R;
import com.zhouyu.nft.base.BaseActivity;
import com.zhouyu.nft.bean.BannerBean;

public class FingerpostActivity extends BaseActivity implements View.OnClickListener {

    ImageView iv_back;
    TextView tv_title;
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hfive);

        iv_back=findViewById(R.id.iv_back);
        tv_title=findViewById(R.id.tv_title);
        webView=findViewById(R.id.webView);
        setData();

        iv_back.setOnClickListener(this);
    }

    private void setData() {
        Intent intent = getIntent();
        String xy = intent.getStringExtra("XY");
        String title = intent.getStringExtra("title");
        tv_title.setText(title);
        WebSettings webSettings = webView.getSettings();
        webView.setHorizontalScrollBarEnabled(false);//水平不显示滚动条
        // 设置与Js交互的权限
        webSettings.setJavaScriptEnabled(true);
        // 设置允许JS弹窗
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        //访问网页
        webView.loadUrl(xy);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //使用WebView加载显示url
                view.loadUrl(url);
                //返回true
                return true;
            }
        });
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.iv_back:
                finish();
                break;
        }
    }
}