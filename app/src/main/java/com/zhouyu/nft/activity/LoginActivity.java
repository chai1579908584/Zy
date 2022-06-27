package com.zhouyu.nft.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
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
import com.zhouyu.nft.util.ParamsConfigs;
import com.zhouyu.nft.util.SpUtil;
import com.zhouyu.nft.util.ToastUtils;

import java.util.Timer;
import java.util.TimerTask;

import okhttp3.Call;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    TextView text_code;
    EditText ed_phone,ed_code;
    EditText ed_inviteCode;
//    String codeStr;
//    String codeImg;
    ImageView rel_patient_care_agreement,logo;
    TextView login,use,intimity;

    private boolean hasAgreeProtocol = false;//是否勾选

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
                    text_code.setText("重新获取");
                    text_code.setOnClickListener(LoginActivity.this);
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
        setContentView(R.layout.activity_login);

        makeStatusBarTransparent(LoginActivity.this);

        text_code = findViewById(R.id.text_code);
        ed_phone = findViewById(R.id.ed_phone);
        ed_code = findViewById(R.id.ed_code);
        ed_inviteCode = findViewById(R.id.ed_inviteCode);
        rel_patient_care_agreement = findViewById(R.id.rel_patient_care_agreement);
        login = findViewById(R.id.login);
        use=findViewById(R.id.use);
        intimity=findViewById(R.id.intimity);
        logo=findViewById(R.id.logo);


        use.setOnClickListener(this);
        intimity.setOnClickListener(this);
        text_code.setOnClickListener(this);
        rel_patient_care_agreement.setOnClickListener(this);
        login.setOnClickListener(this);

        logo.setOnLongClickListener(v -> {
            if(ParamsConfigs.LOGIN_DEBUG){//开发环境长按标题切环境
                Intent intent = new Intent(LoginActivity.this, EnvActivity.class);
                startActivity(intent);
            }
            return true;
        });

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.text_code:
                String phone = ed_phone.getText().toString();
                if (!phone.isEmpty())
                {
//                    text_code.setOnClickListener(null);
//                    YzApi.getGraphic(this, phone, new GXCallback<GraphicBean>() {
//                        @Override
//                        public void onSuccess(GraphicBean response, int id) {
//                                codeStr = response.getCapUid();
//                                codeImg = response.getImg();
//                                CodePopupWindow codePopupWindow = new CodePopupWindow();
//                                codePopupWindow.initPopupWindow(LoginActivity.this, codeImg);
//                                codePopupWindow.setOnClickListener(new CodePopupWindow.SetOnClick() {
//                                    @Override
//                                    public void onClick(String codeStr) {
//                                        getCode(codeStr,phone);
//                                    }
//                                    @Override
//                                    public void onCancel() {
//                                        text_code.setOnClickListener(LoginActivity.this);
//                                    }
//                                });
//                        }
//                    });
                    if (phone.length()!=11)
                    {
                        ToastUtils.show(LoginActivity.this,"手机号错误，请重新输入");
                        return;
                    }
                    getCode(phone);
                }else {
                    ToastUtils.show(LoginActivity.this,"请输入手机号");
                }
                break;
            case R.id.rel_patient_care_agreement:
                if(hasAgreeProtocol){
                    rel_patient_care_agreement.setImageResource(R.mipmap.ic_unchecked);
                }else{
                    rel_patient_care_agreement.setImageResource(R.mipmap.ic_checked);
                }
                hasAgreeProtocol = !hasAgreeProtocol;
                break;
            case R.id.login:
                String phoneStr = ed_phone.getText().toString();
                String codeStr=ed_code.getText().toString();
                String inviteCodeStr = ed_inviteCode.getText().toString();
                if (phoneStr.isEmpty()||codeStr.isEmpty())
                {
                    ToastUtils.show(LoginActivity.this,"请完善信息");
                    return;
                }
                if (phoneStr.length()!=11)
                {
                    ToastUtils.show(LoginActivity.this,"手机号错误，请重新输入");
                    return;
                }
                if (!hasAgreeProtocol)
                {
                    ToastUtils.show(LoginActivity.this,"请同意协议");
                    return;
                }
                showDialog();
                YzApi.getLogin(this, codeStr, inviteCodeStr, phoneStr, new GXCallback<UserInfo>() {
                    @Override
                    public void onSuccess(UserInfo response, int id) {
                            SpUtil.writeToken(LoginActivity.this,response.getToken());
                            Intent intent = new Intent();
                            //在activity之外开启activity时得用FLAG_ACTIVITY_NEW_TASK
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            intent.setClass(getApplicationContext(), HomeActivity.class);
                            startActivity(intent);
                            dismissDialog();
                            ToastUtils.show(LoginActivity.this,"登录成功");
                            finish();
                    }
                });
                break;
            case R.id.use:
                startActivity(new Intent(mContext, FingerpostActivity.class)
                        .putExtra("XY", ParamsConfigs.SERVICE_XY)
                        .putExtra("title","用户服务协议")
                );
                break;
            case R.id.intimity:
                startActivity(new Intent(mContext, FingerpostActivity.class)
                        .putExtra("XY", ParamsConfigs.PRIVACY_XY)
                        .putExtra("title","用户隐私协议")
                );
                break;
        }
    }


    private void getCode(String phone) {
        text_code.setOnClickListener(null);
        showDialog();
        YzApi.getCode(LoginActivity.this, phone, new GXCallback<String>() {
            @Override
            public void onSuccess(String response, int id) {
                ToastUtils.show(LoginActivity.this, "验证码已发送");
                dismissDialog();
                startTime();
            }
            @Override
            public void onError(Call call, Exception e, int id) {
                super.onError(call, e, id);
                text_code.setOnClickListener(LoginActivity.this);
            }
            @Override
            public void onFailure(GXResponse<String> response, int id) {
                super.onFailure(response, id);
                text_code.setOnClickListener(LoginActivity.this);
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




    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}