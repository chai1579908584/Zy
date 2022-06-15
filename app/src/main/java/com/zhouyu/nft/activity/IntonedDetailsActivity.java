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
import com.zhouyu.nft.util.PayPopupWindow;

public class IntonedDetailsActivity extends BaseActivity implements View.OnClickListener {

    ImageView iv_back;
    TextView examine,consignment,buy,change_price;
    WebView wv_webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intoned_details);
        makeStatusBarTransparent(IntonedDetailsActivity.this);

        examine=findViewById(R.id.examine);
        iv_back=findViewById(R.id.iv_back);
        wv_webView=findViewById(R.id.wv_webView);
        consignment=findViewById(R.id.consignment);
        buy=findViewById(R.id.buy);
        change_price=findViewById(R.id.change_price);

        examine.setOnClickListener(this);
        iv_back.setOnClickListener(this);
        consignment.setOnClickListener(this);
        buy.setOnClickListener(this);
        change_price.setOnClickListener(this);

        WebSettings webSettings = wv_webView.getSettings();

        // 设置与Js交互的权限
        webSettings.setJavaScriptEnabled(true);
        // 设置允许JS弹窗
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        //访问网页
        wv_webView.loadUrl("https://zy-demo.zhouyunft.com/index.html#/");
        wv_webView.setWebViewClient(new WebViewClient(){
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
            case R.id.examine:
                startActivity(new Intent(IntonedDetailsActivity.this,ChainMessageActivity.class));
                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.consignment:
                startActivity(new Intent(IntonedDetailsActivity.this,ConsignmentActivity.class));
                break;
            case R.id.buy:
                PayPopupWindow payPopupWindow=new PayPopupWindow();
                payPopupWindow.initPopupWindow(this);
                break;
            case R.id.change_price:
                startActivity(new Intent(IntonedDetailsActivity.this,ChangePriceActivity.class));
                break;
        }
    }
}