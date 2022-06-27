package com.zhouyu.nft.activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhouyu.nft.R;
import com.zhouyu.nft.api.GXCallback;
import com.zhouyu.nft.api.YzApi;
import com.zhouyu.nft.base.BaseActivity;
import com.zhouyu.nft.bean.BankCardBean;
import com.zhouyu.nft.bean.OrderDetailBean;
import com.zhouyu.nft.util.GlideUtil;
import com.zhouyu.nft.util.PwdPopupWindow;
import com.zhouyu.nft.util.ToastUtils;
import com.zhouyu.nft.view.PwdEditText;

import java.util.ArrayList;
import java.util.List;

public class PayActivity extends BaseActivity implements View.OnClickListener {

    ImageView image, ling_pay, bankcard_pay, iv_back;
    TextView name, surePay,price,orderNum;
    String type = "4";
    String order;

    List<BankCardBean> bankCardMessage;
    OrderDetailBean orderDetailStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);

        order = getIntent().getStringExtra("order");

        iv_back = findViewById(R.id.iv_back);
        image = findViewById(R.id.image);
        name = findViewById(R.id.name);
        ling_pay = findViewById(R.id.ling_pay);
        bankcard_pay = findViewById(R.id.bankcard_pay);
        surePay = findViewById(R.id.surePay);
        price = findViewById(R.id.price);
        orderNum = findViewById(R.id.orderNum);

        iv_back.setOnClickListener(this);
        ling_pay.setOnClickListener(this);
        bankcard_pay.setOnClickListener(this);
        surePay.setOnClickListener(this);

        getData();
    }

    private void getData() {
        showDialog();
        YzApi.getOrderDetail(this, order, new GXCallback<OrderDetailBean>() {
            @Override
            public void onSuccess(OrderDetailBean orderDetail, int id) {
                dismissDialog();
                orderDetailStr=orderDetail;
                setData();
            }
        });
    }
    private void getBankCard() {
        YzApi.getBankCard(PayActivity.this, new GXCallback<List<BankCardBean>>() {
            @Override
            public void onSuccess(List<BankCardBean> response, int id) {
                bankCardMessage=response;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getBankCard();
    }

    @SuppressLint("SetTextI18n")
    private void setData() {
        GlideUtil.GlideCir(this,orderDetailStr.getProductImg(),image,30);
        name.setText(orderDetailStr.getProductName());
        orderNum.setText(orderDetailStr.getOrderNo());
        price.setText("￥"+orderDetailStr.getOrderPrice());
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.ling_pay:
                ling_pay.setImageResource(R.mipmap.select_pay);
                bankcard_pay.setImageResource(R.mipmap.unselect);
                type = "4";
                break;
            case R.id.bankcard_pay:
                ling_pay.setImageResource(R.mipmap.unselect);
                bankcard_pay.setImageResource(R.mipmap.select_pay);
                type = "1";
                break;
            case R.id.surePay:
                switch (type)
                {
                    case "1":
                        if (bankCardMessage!=null&&bankCardMessage.size()>0)
                        {
                            PwdPopupWindow popupWindow=new PwdPopupWindow();
                            popupWindow.setOnClickListener(this::pay);
                            popupWindow.initPopupWindow(this,orderDetailStr.getOrderPrice());
                        }else {
                            isBankCard();
                        }
                        break;
                    case "4":
                        //先判断零钱是否充足，目前没有判断
                        PwdPopupWindow popupWindow=new PwdPopupWindow();
                        popupWindow.setOnClickListener(this::pay);
                        popupWindow.initPopupWindow(this,orderDetailStr.getOrderPrice());
                        break;
                }
                break;
        }
    }
    private void isBankCard() {
        //添加取消
        //添加"Yes"按钮
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setMessage("暂无银行卡")
                .setPositiveButton("去绑定", (dialogInterface, i) -> {
                    startActivity(new Intent(PayActivity.this,AddBankCardActivity.class));
                })
                .setNegativeButton("暂不绑定", (dialogInterface, i) -> {
                })
                .create();
        alertDialog.show();
    }

    private void pay(String pwd){
        String bank="";
        if (bankCardMessage!=null&&bankCardMessage.size()>0)
        {
            bank=bankCardMessage.get(0).getBankId();
        }
        YzApi.getPay(this, bank, order, pwd, type, new GXCallback<String>() {
            @Override
            public void onSuccess(String response, int id) {
                ToastUtils.show("支付成功");
            }
        });
    }
}