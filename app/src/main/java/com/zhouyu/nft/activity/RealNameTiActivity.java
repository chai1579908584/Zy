package com.zhouyu.nft.activity;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.aliyun.aliyunface.api.ZIMCallback;
import com.aliyun.aliyunface.api.ZIMFacade;
import com.aliyun.aliyunface.api.ZIMFacadeBuilder;
import com.aliyun.aliyunface.api.ZIMResponse;
import com.zhouyu.nft.R;
import com.zhouyu.nft.api.GXCallback;
import com.zhouyu.nft.api.YzApi;
import com.zhouyu.nft.base.BaseActivity;
import com.zhouyu.nft.bean.RealNameBean;
import com.zhouyu.nft.util.SpUtil;
import com.zhouyu.nft.util.ToastUtils;

public class RealNameTiActivity extends BaseActivity implements View.OnClickListener {

    ImageView iv_back;
    TextView next;
    String name,idCard;
    String certifyId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_real_name_ti);

        iv_back=findViewById(R.id.iv_back);
        next=findViewById(R.id.next);

        Intent intent = getIntent();
        name=intent.getStringExtra("name");
        idCard=intent.getStringExtra("idCard");

        iv_back.setOnClickListener(this);
        next.setOnClickListener(this);
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
                getId();
                break;
        }
    }

    private void getId() {
        showDialog();
        ZIMFacade.install(this);
        String metaInfo = ZIMFacade.getMetaInfos(this);
        YzApi.getCertifyId(this, name, idCard, metaInfo, new GXCallback<RealNameBean>() {
            @Override
            public void onSuccess(RealNameBean response, int id) {
                certifyId=response.getCertifyId();
                faceRecognition(certifyId);
                dismissDialog();
            }
        });
    }

    public void faceRecognition(String certifyId){
        ZIMFacade zimFacade = ZIMFacadeBuilder.create(RealNameTiActivity.this);
        zimFacade.verify(certifyId, true, response -> {
            if (null != response && 1000 == response.code) {
                // 认证成功
                showDialog();
                YzApi.getRealNameMessage(RealNameTiActivity.this, certifyId, new GXCallback<String>() {
                    @Override
                    public void onSuccess(String response, int id) {
                        dismissDialog();
                        startActivity(new Intent(RealNameTiActivity.this,RealNameSuccessActivity.class));
                        finish();
                    }
                });
            } else {
                // 认证失败
                ToastUtils.show(response.msg);
            }
            return true;
        });
    }
}