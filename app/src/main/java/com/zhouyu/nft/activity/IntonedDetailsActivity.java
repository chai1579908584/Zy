package com.zhouyu.nft.activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.zhouyu.nft.R;
import com.zhouyu.nft.adapter.DetailImageAdapter;
import com.zhouyu.nft.api.GXCallback;
import com.zhouyu.nft.api.GXResponse;
import com.zhouyu.nft.api.YzApi;
import com.zhouyu.nft.base.BaseActivity;
import com.zhouyu.nft.bean.IntonedDetailBean;
import com.zhouyu.nft.bean.OrderBean;
import com.zhouyu.nft.bean.UserInfo;
import com.zhouyu.nft.util.AmountUtil;
import com.zhouyu.nft.util.GlideUtil;
import com.zhouyu.nft.util.ParamsConfigs;
import com.zhouyu.nft.util.SpUtil;

import java.util.Timer;
import java.util.TimerTask;

import okhttp3.Call;

public class IntonedDetailsActivity extends BaseActivity implements View.OnClickListener {

    ImageView iv_back, image, brandHead;
    TextView tvSeries, tvType, tvPublisher, tvPlatform, tvTime, pay_hint, hint_message;
    // TextView tvNum,examine,tvName,tvAddress;
    TextView consignment, buy, change_price, tvPrice, rightsDesc;
    TextView name, label, limit, brandName, num;
    //    TextView isMessage;
//    LinearLayout isLlMessage;
    RelativeLayout rl_buy, rl_want_buy, rl_brand;
    LinearLayout ll_want_js, ll_yjs;
    RecyclerView imageRecycler;
    WebView wv_webView;
    String gid;

    //判断那个页面进来的  0.首页藏品&全部藏品（区分售罄和预售）&数字藏品1.集市2.我的藏品（判断是否寄售）
    String type;

    IntonedDetailBean intonedDetailBean;

    private int second = 1;
    private Timer timer;

    Handler handler = new Handler(Looper.myLooper()) {
        @SuppressLint({"ShowToast", "SetTextI18n"})
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.arg1 == 0) {
                wv_webView.setVisibility(View.VISIBLE);
                if (timer != null) {
                    timer.cancel();
                    timer = null;
                }
            }
        }
    };
    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intoned_details);
        makeStatusBarTransparent(IntonedDetailsActivity.this);

        gid = getIntent().getStringExtra("gid");
        type = getIntent().getStringExtra("type");

        //       examine = findViewById(R.id.examine);
        iv_back = findViewById(R.id.iv_back);
        wv_webView = findViewById(R.id.wv_webView);
        consignment = findViewById(R.id.consignment);
        buy = findViewById(R.id.buy);
        change_price = findViewById(R.id.change_price);
        tvSeries = findViewById(R.id.tvSeries);
        tvType = findViewById(R.id.tvType);
        tvPublisher = findViewById(R.id.tvPublisher);
        tvPlatform = findViewById(R.id.tvPlatform);
        tvTime = findViewById(R.id.tvTime);
        tvPrice = findViewById(R.id.tvPrice);
        rl_buy = findViewById(R.id.rl_buy);
        rl_want_buy = findViewById(R.id.rl_want_buy);
        ll_want_js = findViewById(R.id.ll_want_js);
        ll_yjs = findViewById(R.id.ll_yjs);
        pay_hint = findViewById(R.id.pay_hint);
        hint_message = findViewById(R.id.hint_message);
        image = findViewById(R.id.image);
        name = findViewById(R.id.name);
        label = findViewById(R.id.label);
        limit = findViewById(R.id.limit);
        imageRecycler = findViewById(R.id.imageRecycler);
        brandHead = findViewById(R.id.brandHead);
        brandName = findViewById(R.id.brandName);
        num = findViewById(R.id.num);
        rl_brand = findViewById(R.id.rl_brand);
        rightsDesc = findViewById(R.id.rightsDesc);

//        tvNum = findViewById(R.id.tvNum);
//        tvName = findViewById(R.id.tvName);
//        tvAddress = findViewById(R.id.tvAddress);
//
//        isMessage = findViewById(R.id.isMessage);
//        isLlMessage = findViewById(R.id.isLlMessage);
//
//        examine.setOnClickListener(this);
        iv_back.setOnClickListener(this);
        consignment.setOnClickListener(this);
        change_price.setOnClickListener(this);
        brandHead.setOnClickListener(this);

//        if ("0".equals(type))
//        {
//            isMessage.setVisibility(View.GONE);
//            isLlMessage.setVisibility(View.GONE);
//        }
        //判断那个页面进来的  0.首页藏品&全部藏品（区分售罄和预售）&数字藏品1.集市2.我的藏品（判断是否寄售）
        switch (type) {
            case "1":
                rl_buy.setVisibility(View.GONE);
                rl_want_buy.setVisibility(View.VISIBLE);
                ll_want_js.setVisibility(View.GONE);
                ll_yjs.setVisibility(View.GONE);
                pay_hint.setText("收藏提示");
                hint_message.setText("本数宇藏品版权由发行方或原作创作者拥有，除另行取得版权拥有者书面同意外，用户不得将数字藏品用于任何商业用途。请远离非理性炒作，防苑欺诈风险。");
                rl_brand.setVisibility(View.GONE);
                break;
            case "2":
                rl_buy.setVisibility(View.GONE);
                rl_want_buy.setVisibility(View.GONE);
                ll_want_js.setVisibility(View.VISIBLE);
                ll_yjs.setVisibility(View.GONE);
                pay_hint.setText("收藏提示");
                hint_message.setText("本数宇藏品版权由发行方或原作创作者拥有，除另行取得版权拥有者书面同意外，用户不得将数字藏品用于任何商业用途。请远离非理性炒作，防苑欺诈风险。");
                rl_brand.setVisibility(View.GONE);
                break;
            default:
                rl_buy.setVisibility(View.VISIBLE);
                rl_want_buy.setVisibility(View.GONE);
                ll_want_js.setVisibility(View.GONE);
                ll_yjs.setVisibility(View.GONE);
                rl_brand.setVisibility(View.VISIBLE);
                break;
        }
        getData();
    }

    private void getData() {
        YzApi.getIntonedDetail(this, gid, new GXCallback<IntonedDetailBean>() {
            @Override
            public void onSuccess(IntonedDetailBean response, int id) {
                intonedDetailBean = response;
                setData();
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void setData() {
        name.setText(intonedDetailBean.getProductName());
        label.setText(intonedDetailBean.getSeriesName());
        limit.setText("限量:" + intonedDetailBean.getSaleNum());
        tvSeries.setText("《" + intonedDetailBean.getSeriesName() + "》");
        GlideUtil.GlideCirHead(this, intonedDetailBean.getBrandlogo(), brandHead, 100);
        brandName.setText(intonedDetailBean.getBrandName());
        num.setText("藏品 " + intonedDetailBean.getNftNum());
        rightsDesc.setText(intonedDetailBean.getRightsDesc());
        Glide.with(this)
                .load(intonedDetailBean.getProductImg())
                .into(image);
        switch (intonedDetailBean.getDisplayFormat()) {
            case "1":
                tvType.setText("3D");
                setWebView();
                image.setVisibility(View.GONE);
                break;
            case "2":
                tvType.setText("视频");
                image.setVisibility(View.VISIBLE);
                break;
            case "3":
                tvType.setText("音频");
                image.setVisibility(View.VISIBLE);
                break;
            default:
                tvType.setText("图片");
                image.setVisibility(View.VISIBLE);
                break;
        }
        switch (intonedDetailBean.getType()) {
            case "1":
                buy.setText(intonedDetailBean.getPubTime().substring(5, 16) + "开售");
                break;
            case "3":
                buy.setText("已售罄");
                buy.setTextColor(0xffffffff);
                buy.setBackgroundResource(R.drawable.shape_corner_c3);
                break;
            default:
                buy.setText("立即购买");
                buy.setOnClickListener(this);
                break;
        }
        tvPublisher.setText(intonedDetailBean.getBrandName());
        tvPlatform.setText(intonedDetailBean.getPlatform());
        tvTime.setText(intonedDetailBean.getPubTime().substring(0, 10));
        tvPrice.setText(AmountUtil.changeF2Y(intonedDetailBean.getSalePrice()));

        imageRecycler.setLayoutManager(new LinearLayoutManager(mContext) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        DetailImageAdapter detailImageAdapter = new DetailImageAdapter(mContext, intonedDetailBean.getProductDetailImg());
        imageRecycler.setAdapter(detailImageAdapter);
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void setWebView() {
        wv_webView.setVisibility(View.GONE);
        wv_webView.setVisibility(View.INVISIBLE);
        WebSettings webSettings = wv_webView.getSettings();
        // 设置与Js交互的权限
        webSettings.setJavaScriptEnabled(true);
        // 设置允许JS弹窗
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        //访问网页
        String url="";
        if (ParamsConfigs.EXPLOIT.equals(ParamsConfigs.ENV))
        {
            //开发环境
            url=ParamsConfigs.THREE_D;
        }else {
            //测试环境
            url=ParamsConfigs.THREE_T;
        }
        wv_webView.loadUrl(url + "?imgurl=" + intonedDetailBean.getProductImg());

        wv_webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //使用WebView加载显示url
                if (url.startsWith("http://") || url.startsWith("https://")) {
                    view.loadUrl(url);
                    wv_webView.stopLoading();
                    return true;
                }
                return false;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                timer = new Timer();
                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        Message message = new Message();
                        message.arg1 = second;
                        handler.sendMessage(message);
                        second--;
                    }
                };
                timer.schedule(task, 1000, 1000);
            }
        });
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.examine:
//                startActivity(new Intent(IntonedDetailsActivity.this, ChainMessageActivity.class));
//                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.consignment:
                startActivity(new Intent(IntonedDetailsActivity.this, ConsignmentActivity.class));
                break;
            case R.id.buy:
                isSure();
                break;
            case R.id.change_price:
                startActivity(new Intent(IntonedDetailsActivity.this, ChangePriceActivity.class));
                break;
            case R.id.brandHead:
                startActivity(new Intent(IntonedDetailsActivity.this, BrandHomeActivity.class).putExtra("bid", intonedDetailBean.getBid()));
                break;
        }
    }

    private void isSure() {
        UserInfo userInfo = SpUtil.readData(mContext);
        if (!"1".equals(userInfo.getIsRealName())) {
            isAutonym();
            return;
        }

        getOrder();
    }

    private void getOrder() {
        showDialog();
        YzApi.getOrder(this, intonedDetailBean.getGid(), new GXCallback<OrderBean>() {
            @Override
            public void onSuccess(OrderBean response, int id) {
                dismissDialog();
                if ("2".equals(response.getType())) {
                    isOrder(response.getOrderId());
                } else {
                    startActivity(new Intent(IntonedDetailsActivity.this, PayActivity.class).putExtra("order", response.getOrderId()));
                }
            }
        });
    }

    private void isAutonym() {
        //添加取消
        //添加"Yes"按钮
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setMessage("请实名后购买")
                .setPositiveButton("去实名", (dialogInterface, i) -> {
                    startActivity(new Intent(IntonedDetailsActivity.this, AuthenticationActivity.class));
                })
                .setNegativeButton("暂不实名", (dialogInterface, i) -> {
                })
                .create();
        alertDialog.show();
    }

    private void isOrder(String order) {
        //添加取消
        //添加"Yes"按钮
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setMessage("有未支付的订单")
                .setPositiveButton("去支付", (dialogInterface, i) -> {
                    startActivity(new Intent(IntonedDetailsActivity.this, PayActivity.class).putExtra("order", order));
                })
                .setNegativeButton("暂不支付", (dialogInterface, i) -> {
                })
                .create();
        alertDialog.show();
    }

}