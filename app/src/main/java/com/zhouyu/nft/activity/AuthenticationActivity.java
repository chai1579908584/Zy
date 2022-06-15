package com.zhouyu.nft.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhouyu.nft.R;
import com.zhouyu.nft.base.BaseActivity;


public class AuthenticationActivity extends BaseActivity implements View.OnClickListener{


    ImageView iv_back;
    TextView name,id_card,next;
    
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
                startActivity(new Intent(AuthenticationActivity.this,RealNameTiActivity.class));
                break;
        }
    }
}