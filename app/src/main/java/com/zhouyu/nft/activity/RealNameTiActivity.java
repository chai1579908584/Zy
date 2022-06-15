package com.zhouyu.nft.activity;


import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.aliyun.aliyunface.api.ZIMCallback;
import com.aliyun.aliyunface.api.ZIMFacade;
import com.aliyun.aliyunface.api.ZIMFacadeBuilder;
import com.aliyun.aliyunface.api.ZIMResponse;
import com.zhouyu.nft.R;
import com.zhouyu.nft.base.BaseActivity;

public class RealNameTiActivity extends BaseActivity implements View.OnClickListener {

    ImageView iv_back;
    TextView next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_real_name_ti);

        iv_back=findViewById(R.id.iv_back);
        next=findViewById(R.id.next);

        iv_back.setOnClickListener(this);
        next.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.iv_back:
                finish();
                break;
            case R.id.next:
                faceRecognition();
                break;
        }
    }

    public void faceRecognition(){
                String certifyId = "这里填写从服务端获取的CertifyID";
                ZIMFacade zimFacade = ZIMFacadeBuilder.create(RealNameTiActivity.this);
                zimFacade.verify(certifyId, true, new ZIMCallback() {
                    @Override
                    public boolean response(ZIMResponse response) {
                        if (null != response && 1000 == response.code) {
                            // 认证成功
                        } else {
                            // 认证失败
                        }
                        return true;
                    }
        });
    }
}