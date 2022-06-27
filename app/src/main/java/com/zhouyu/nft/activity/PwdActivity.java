package com.zhouyu.nft.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.zhouyu.nft.R;
import com.zhouyu.nft.api.GXCallback;
import com.zhouyu.nft.api.GXResponse;
import com.zhouyu.nft.api.YzApi;
import com.zhouyu.nft.base.BaseActivity;
import com.zhouyu.nft.bean.UserInfo;
import com.zhouyu.nft.util.MD5Util;
import com.zhouyu.nft.util.SpUtil;
import com.zhouyu.nft.util.ToastUtils;

import java.util.Timer;
import java.util.TimerTask;

import okhttp3.Call;

public class PwdActivity extends BaseActivity implements View.OnClickListener {

    ImageView iv_back;
    TextView phone,gainCode,sure;
    EditText code,newPwd,surePwd;
    private int second=60;
    private Timer timer;

    String phoneStr;
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
                    gainCode.setText("重新获取");
                    gainCode.setOnClickListener(PwdActivity.this);
                    second=60;
                }
            }else {
                gainCode.setText(second+"秒");
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pwd);

        iv_back=findViewById(R.id.iv_back);
        phone=findViewById(R.id.phone);
        gainCode=findViewById(R.id.gainCode);
        sure=findViewById(R.id.sure);
        code=findViewById(R.id.code);
        newPwd=findViewById(R.id.newPwd);
        surePwd=findViewById(R.id.surePwd);

        UserInfo userInfo = SpUtil.readData(this);
        phoneStr=userInfo.getPhone();
        if (phoneStr!=null&&!phoneStr.isEmpty())
        {
            phone.setText(phoneStr);
        }else {
            phone.setText("你还没有绑定手机号");
        }

        iv_back.setOnClickListener(this);
        gainCode.setOnClickListener(this);
        sure.setOnClickListener(this);

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.iv_back:
                finish();
                break;
            case R.id.gainCode:
                if (phoneStr==null||phoneStr.isEmpty())
                {
                    ToastUtils.show(mContext,"你还没有绑定手机号");
                    return;
                }
                getCode();
                break;
            case R.id.sure:
                String codeStr = code.getText().toString();
                String newPwdStr = newPwd.getText().toString();
                String surePwdStr = surePwd.getText().toString();
                if (phoneStr==null||phoneStr.isEmpty()||codeStr.isEmpty()||newPwdStr.isEmpty()||surePwdStr.isEmpty()||newPwdStr.length()!=6)
                {
                    ToastUtils.show(mContext,"请完善信息");
                    return;
                }
                if (!newPwdStr.equals(surePwdStr))
                {
                    ToastUtils.show(mContext,"两次密码不一致");
                    return;
                }
                showDialog();
                YzApi.getSetPwd(this, codeStr, MD5Util.encrypt(newPwdStr), MD5Util.encrypt(surePwdStr), new GXCallback<String>() {
                    @Override
                    public void onSuccess(String response, int id) {
                        dismissDialog();
                        ToastUtils.show(PwdActivity.this,"设置成功");
                        finish();
                    }
                });
                break;
        }
    }

    private void getCode() {
        gainCode.setOnClickListener(null);
        showDialog();
        YzApi.getLogoutCode(this, phoneStr, new GXCallback<String>() {
            @Override
            public void onSuccess(String response, int id) {
                ToastUtils.show(PwdActivity.this, "验证码已发送");
                dismissDialog();
                startTime();
            }
            @Override
            public void onError(Call call, Exception e, int id) {
                super.onError(call, e, id);
                gainCode.setOnClickListener(PwdActivity.this);
            }
            @Override
            public void onFailure(GXResponse<String> response, int id) {
                super.onFailure(response, id);
                gainCode.setOnClickListener(PwdActivity.this);
            }
        });
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