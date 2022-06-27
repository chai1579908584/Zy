package com.zhouyu.nft.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
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
import com.zhouyu.nft.util.LogOutUtil;
import com.zhouyu.nft.util.SpUtil;
import com.zhouyu.nft.util.ToastUtils;

import java.util.Timer;
import java.util.TimerTask;

import okhttp3.Call;

public class WriteOffPhoneActivity extends BaseActivity implements View.OnClickListener {

    ImageView iv_back;
    TextView sure,phone,gainCode;
    EditText ed_code;
    String phoneStr;
    private int second=60;
    private Timer timer;

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
                    gainCode.setOnClickListener(WriteOffPhoneActivity.this);
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
        setContentView(R.layout.activity_write_offphone);

        iv_back=findViewById(R.id.iv_back);
        sure=findViewById(R.id.sure);
        phone=findViewById(R.id.phone);
        gainCode=findViewById(R.id.gainCode);
        ed_code=findViewById(R.id.ed_code);

        UserInfo userInfo = SpUtil.readData(this);
        phoneStr=userInfo.getPhone();
        if (phoneStr!=null&&!phoneStr.isEmpty())
        {
            phone.setText(phoneStr);
        }else {
            phone.setText("你还没有绑定手机号");
        }

        iv_back.setOnClickListener(this);
        sure.setOnClickListener(this);
        gainCode.setOnClickListener(this);
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
                LogOut();
                break;
        }
    }

    private void LogOut() {
        String codeStr = ed_code.getText().toString();
        if (codeStr.isEmpty())
        {
            ToastUtils.show("请输入验证码");
            return;
        }
        showDialog();
        YzApi.getLogout(this, codeStr, new GXCallback<String>() {
            @Override
            public void onSuccess(String response, int id) {
                ToastUtils.show(WriteOffPhoneActivity.this, "注销成功");
                LogOutUtil.logOut(WriteOffPhoneActivity.this);
            }
        });
    }

    private void getCode() {
        gainCode.setOnClickListener(null);
        showDialog();
        YzApi.getLogoutCode(this, phoneStr, new GXCallback<String>() {
            @Override
            public void onSuccess(String response, int id) {
                ToastUtils.show(WriteOffPhoneActivity.this, "验证码已发送");
                dismissDialog();
                startTime();
            }
            @Override
            public void onError(Call call, Exception e, int id) {
                super.onError(call, e, id);
                gainCode.setOnClickListener(WriteOffPhoneActivity.this);
            }
            @Override
            public void onFailure(GXResponse<String> response, int id) {
                super.onFailure(response, id);
                gainCode.setOnClickListener(WriteOffPhoneActivity.this);
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