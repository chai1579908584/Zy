package com.zhouyu.nft.activity;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhouyu.nft.R;
import com.zhouyu.nft.base.BaseActivity;

public class WalletActivity extends BaseActivity implements View.OnClickListener {

    ImageView iv_back;
    RelativeLayout rl_bankcard;
    TextView withdraw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);

        iv_back=findViewById(R.id.iv_back);
        rl_bankcard=findViewById(R.id.rl_bankcard);
        withdraw=findViewById(R.id.withdraw);

        iv_back.setOnClickListener(this);
        rl_bankcard.setOnClickListener(this);
        withdraw.setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.iv_back:
                finish();
                break;
            case R.id.rl_bankcard:
                startActivity(new Intent(WalletActivity.this,BankCardActivity.class));
                break;
            case R.id.withdraw:
                startActivity(new Intent(WalletActivity.this,WithdrawActivity.class));
                break;
        }
    }
}