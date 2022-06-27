package com.zhouyu.nft.activity;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zhouyu.nft.R;
import com.zhouyu.nft.adapter.BankCardCodeAdapter;
import com.zhouyu.nft.api.GXCallback;
import com.zhouyu.nft.api.GXResponse;
import com.zhouyu.nft.api.YzApi;
import com.zhouyu.nft.base.BaseActivity;
import com.zhouyu.nft.bean.BankCodeBean;
import com.zhouyu.nft.bean.RealNameMessageBean;
import com.zhouyu.nft.util.ToastUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.Call;

public class AddBankCardActivity extends BaseActivity implements View.OnClickListener {

    ImageView iv_back;
    TextView name,text_code,bankcard_type,sure;
    EditText cardNum,phone,code;
    LinearLayout ll_select;

    String codeType="";
    List<BankCodeBean> codeList=new ArrayList<>();
    private int second=60;
    private Timer timer;
    String uuid="";

    Handler handler=new Handler(Looper.myLooper()){
        @SuppressLint({"ShowToast", "SetTextI18n"})
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.arg1==0)
            {
                if (timer!=null)
                {
                    timer.cancel();
                    text_code.setText("重新获取");
                    text_code.setOnClickListener(AddBankCardActivity.this);
                    second=60;
                }
            }else {
                text_code.setText(second+"秒");
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bank_card);

        iv_back=findViewById(R.id.iv_back);
        name=findViewById(R.id.name);
        text_code=findViewById(R.id.text_code);
        cardNum=findViewById(R.id.cardNum);
        phone=findViewById(R.id.phone);
        code=findViewById(R.id.code);
        ll_select=findViewById(R.id.ll_select);
        bankcard_type=findViewById(R.id.bankcard_type);
        sure=findViewById(R.id.sure);

        iv_back.setOnClickListener(this);
        text_code.setOnClickListener(this);
        ll_select.setOnClickListener(this);
        sure.setOnClickListener(this);

        getData();

    }
    private void getData() {
        YzApi.getRealNameMessage(this, new GXCallback<RealNameMessageBean>() {
            @Override
            public void onSuccess(RealNameMessageBean response, int id) {
                if ("0".equals(response.getIsRealName()))
                {
                    authentication();
                }else {
                    if (name!=null)
                    {
                        name.setText(response.getName());
                    }
                }
            }
        });

        YzApi.getBankCode(this, new GXCallback<List<BankCodeBean>>() {
            @Override
            public void onSuccess(List<BankCodeBean> response, int id) {
                codeList.addAll(response);
            }
        });
    }

    private void authentication() {
        //添加取消
        //添加"Yes"按钮
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setMessage("请实名认证后操作")
                .setPositiveButton("去认证", (dialogInterface, i) -> {
                    startActivity(new Intent(AddBankCardActivity.this,AuthenticationActivity.class));
                    finish();
                })
                .setNegativeButton("暂不认证", (dialogInterface, i) -> {
                    finish();
                })
                .create();
        alertDialog.show();
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.iv_back:
                finish();
                break;
            case R.id.ll_select:
                if (codeList!=null&&codeList.size()>0)
                {
                    setPopupWindow();
                }
                break;
            case R.id.text_code:
                String cardNumStr = cardNum.getText().toString();
                String phoneStr = phone.getText().toString();
                if (codeType.isEmpty())
                {
                    ToastUtils.show("请选择所属银行");
                    return;
                }
                if (cardNumStr.isEmpty()||phoneStr.isEmpty())
                {
                    ToastUtils.show("请完善信息");
                    return;
                }
                text_code.setOnClickListener(null);
                showDialog();
                YzApi.getCodeForBindBankCard(this, codeType, cardNumStr, "1",phoneStr, new GXCallback<String>() {
                    @Override
                    public void onSuccess(String response, int id) {
                        ToastUtils.show("验证码已发送");
                        uuid=response;
                        dismissDialog();
                        startTime();
                    }
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        super.onError(call, e, id);
                        text_code.setOnClickListener(AddBankCardActivity.this);
                    }

                    @Override
                    public void onFailure(GXResponse<String> response, int id) {
                        super.onFailure(response, id);
                        text_code.setOnClickListener(AddBankCardActivity.this);
                    }
                });
                break;
            case R.id.sure:
                String cardNumStrS = cardNum.getText().toString();
                String phoneStrS = phone.getText().toString();
                String codeStrS = code.getText().toString();
                if (cardNumStrS.isEmpty()||phoneStrS.isEmpty()||codeStrS.isEmpty()||codeType.isEmpty())
                {
                    ToastUtils.show("请完善信息");
                    return;
                }
                showDialog();
                YzApi.getBindBankCard(this, codeType, cardNumStrS, "1", phoneStrS, uuid, codeStrS, new GXCallback<String>() {
                    @Override
                    public void onSuccess(String response, int id) {
                        dismissDialog();
                        ToastUtils.show("绑定银行卡成功");
                        finish();
                    }
                });
                break;
        }
    }

    private void startTime() {
        timer=new Timer();
        TimerTask task=new TimerTask() {
            @Override
            public void run() {
                Message message=new Message();
                message.arg1=second;
                handler.sendMessage(message);
                second--;
            }
        };
        timer.schedule(task,1000,1000);
    }


    private void setPopupWindow() {
        View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.popupwindow_recycler_bankcode, null, false);
        PopupWindow mPopupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT,600);
        RecyclerView recyclerView = view.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        BankCardCodeAdapter bankCardCodeAdapter=new BankCardCodeAdapter(this,codeList);
        bankCardCodeAdapter.setOnClickListener((code, name) -> {
            bankcard_type.setText(name);
            bankcard_type.setTextColor(0xff222629);
            codeType=code;
            mPopupWindow.dismiss();
        });
        recyclerView.setAdapter(bankCardCodeAdapter);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setFocusable(true);
        mPopupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mPopupWindow.getContentView().measure(0, 0);
        mPopupWindow.showAsDropDown(bankcard_type);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (timer!=null)
        {
            timer.cancel();
            timer=null;
        }
    }
}