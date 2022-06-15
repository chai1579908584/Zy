package com.zhouyu.nft.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.zhouyu.nft.R;
import com.zhouyu.nft.base.BaseActivity;

public class WriteOffActivity extends BaseActivity implements View.OnClickListener {

    ImageView iv_back;
    ImageView rel_patient_care_agreement;
    private boolean hasAgreeProtocol = false;//是否勾选
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_off);

        iv_back=findViewById(R.id.iv_back);
        rel_patient_care_agreement=findViewById(R.id.rel_patient_care_agreement);

        iv_back.setOnClickListener(this);
        rel_patient_care_agreement.setOnClickListener(this);

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.iv_back:
                finish();
                break;
            case R.id.rel_patient_care_agreement:
                if(hasAgreeProtocol){
                    rel_patient_care_agreement.setImageResource(R.mipmap.ic_unchecked);
                }else{
                    rel_patient_care_agreement.setImageResource(R.mipmap.ic_checked);
                }
                hasAgreeProtocol = !hasAgreeProtocol;
                break;
        }
    }
}