package com.zhouyu.nft.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.zhouyu.nft.R;
import com.zhouyu.nft.base.BaseActivity;
import com.zhouyu.nft.bean.BannerBean;
import com.zhouyu.nft.bean.UserInfo;
import com.zhouyu.nft.util.AppContextUtils;
import com.zhouyu.nft.util.HmacShaUtil;
import com.zhouyu.nft.util.JavaScriptInterface;
import com.zhouyu.nft.util.ParamsConfigs;
import com.zhouyu.nft.util.SpUtil;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class HFiveActivity extends BaseActivity implements View.OnClickListener, JavaScriptInterface.CallbackListener {

    ImageView iv_back;
    TextView tv_title;
    WebView webView;
    String linkUrl;
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

    @SuppressLint({"JavascriptInterface", "SetJavaScriptEnabled"})
    private void setData() {
        BannerBean banner = (BannerBean) getIntent().getSerializableExtra("banner");
        tv_title.setText(banner.getTitle());
        WebSettings webSettings = webView.getSettings();
        webView.setHorizontalScrollBarEnabled(false);//水平不显示滚动条
        JavaScriptInterface jsInterface=new JavaScriptInterface(this,this);
        webView.addJavascriptInterface(jsInterface, "android");
        // 设置与Js交互的权限
        webSettings.setJavaScriptEnabled(true);
        // 设置允许JS弹窗
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        //访问网页
        linkUrl=banner.getLinkUrl();
        webView.loadUrl(linkUrl);

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

    /**
     * 判断是否是我们的域名
     *
     */
    public boolean isOurUrl(){
        return linkUrl.contains("app.zhouyunft.com");
    }

    @Override
    public void h5Callback(String methodName, String params) {
        runOnUiThread(() -> webView.loadUrl("javascript:" + methodName + "('" + params + "')"));
    }
}