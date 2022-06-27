package com.zhouyu.nft.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhouyu.nft.R;
import com.zhouyu.nft.api.GXCallback;
import com.zhouyu.nft.api.GXResponse;
import com.zhouyu.nft.api.YzApi;
import com.zhouyu.nft.base.BaseActivity;
import com.zhouyu.nft.bean.RealNameMessageBean;
import com.zhouyu.nft.bean.UserInfo;
import com.zhouyu.nft.util.LogOutUtil;
import com.zhouyu.nft.util.SpUtil;
import com.zhouyu.nft.util.ToastUtils;


public class AuthenticationActivity extends BaseActivity implements View.OnClickListener{


    ImageView iv_back;
    EditText name,id_card;
    TextView next;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);

        initView();

    }

    private void initView() {
        iv_back=findViewById(R.id.iv_back);
        name=findViewById(R.id.name);
        id_card=findViewById(R.id.id_card);
        next=findViewById(R.id.next);

        iv_back.setOnClickListener(this);
        next.setOnClickListener(this);
        getData();
    }

    private void getData() {
        YzApi.getRealNameMessage(this, new GXCallback<RealNameMessageBean>() {
            @Override
            public void onSuccess(RealNameMessageBean response, int id) {
                if ("1".equals(response.getIsRealName()))
                {
                    name.setText(response.getName());
                    id_card.setText(response.getIdCard());
                    ToastUtils.show(AuthenticationActivity.this,"你已实名认证");
                    next.setText("已认证");
                    next.setOnClickListener(null);
                    name.setInputType(InputType.TYPE_NULL);
                    id_card.setInputType(InputType.TYPE_NULL);
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        UserInfo userInfo = SpUtil.readData(mContext);
        String token=userInfo.getToken();
        if (token!=null&&!token.isEmpty())
        {
            getMessage();
        }
    }

    private void getMessage() {
        YzApi.getMessage(mContext, new GXCallback<UserInfo>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onSuccess(UserInfo response, int id) {
                SpUtil.writeData(mContext,response);
            }
            @Override
            public void onFailure(GXResponse<String> response, int id) {
                super.onFailure(response, id);
                if (1001==response.code)
                {
                    LogOutUtil.logOutNoData(mContext);
                }
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
            case R.id.next:
                if (name.getText().toString().isEmpty()||id_card.getText().toString().isEmpty())
                {
                    ToastUtils.show(this,"请完善信息");
                    return;
                }
                startActivity(new Intent(AuthenticationActivity.this,RealNameTiActivity.class)
                        .putExtra("name",name.getText().toString())
                        .putExtra("idCard",id_card.getText().toString()));
                break;
        }
    }
}