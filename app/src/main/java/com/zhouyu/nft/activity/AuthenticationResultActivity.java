package com.zhouyu.nft.activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhouyu.nft.R;
import com.zhouyu.nft.base.BaseActivity;

public class AuthenticationResultActivity extends BaseActivity {

    ImageView imageType;
    TextView textType,textMore;
    int type=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication_result);

        Intent intent = getIntent();
        type = intent.getIntExtra("type",0);
        initView();
    }

    private void initView() {
        imageType = findViewById(R.id.imageType);
        textType = findViewById(R.id.textType);
        textMore = findViewById(R.id.textMore);
        if (type==0)
        {
            imageType.setBackgroundResource(R.mipmap.lose);
            textType.setText("未通过实名认证");
            textMore.setVisibility(View.GONE);
        }
    }



}