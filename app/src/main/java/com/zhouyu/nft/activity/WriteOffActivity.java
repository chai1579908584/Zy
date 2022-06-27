package com.zhouyu.nft.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhouyu.nft.R;
import com.zhouyu.nft.base.BaseActivity;

public class WriteOffActivity extends BaseActivity implements View.OnClickListener {

    ImageView iv_back;
    TextView sure;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_off);

        iv_back=findViewById(R.id.iv_back);
        sure=findViewById(R.id.sure);

        sure.setOnClickListener(this);
        iv_back.setOnClickListener(this);

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.iv_back:
                finish();
                break;
            case R.id.sure:
                startActivity(new Intent(WriteOffActivity.this,WriteOffPhoneActivity.class));
                break;
        }
    }
}