package com.zhouyu.nft.activity;


import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.zhouyu.nft.R;
import com.zhouyu.nft.base.BaseActivity;

public class FuseActivity extends BaseActivity implements View.OnClickListener{

    ImageView iv_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fuse);

        iv_back=findViewById(R.id.iv_back);

        iv_back.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
           case  R.id.iv_back:
               finish();
               break;

        }
    }
}