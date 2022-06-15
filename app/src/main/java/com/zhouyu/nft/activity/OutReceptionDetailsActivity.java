package com.zhouyu.nft.activity;


import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhouyu.nft.R;
import com.zhouyu.nft.base.BaseActivity;

public class OutReceptionDetailsActivity extends BaseActivity implements View.OnClickListener {

    ImageView iv_back;
    TextView tv_title,role;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outreception_details);

        iv_back=findViewById(R.id.iv_back);
        tv_title=findViewById(R.id.tv_title);
        role=findViewById(R.id.role);

        String type = getIntent().getStringExtra("type");
        String id = getIntent().getStringExtra("id");
        if ("1".equals(type))
        {
            tv_title.setText("转出记录");
            role.setText("接收人");
        }else {
            tv_title.setText("接收记录");
            role.setText("赠送人");
        }

        iv_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.iv_back:
                finish();
                break;
        }
    }
}