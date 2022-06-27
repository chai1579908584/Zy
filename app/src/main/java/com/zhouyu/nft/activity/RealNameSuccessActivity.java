package com.zhouyu.nft.activity;


import android.os.Bundle;
import android.widget.ImageView;

import com.zhouyu.nft.R;
import com.zhouyu.nft.base.BaseActivity;

public class RealNameSuccessActivity extends BaseActivity {

    ImageView iv_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_real_name_success);

        iv_back=findViewById(R.id.iv_back);

        iv_back.setOnClickListener(v -> finish());
    }

}