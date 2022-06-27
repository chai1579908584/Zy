package com.zhouyu.nft.activity;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhouyu.nft.R;
import com.zhouyu.nft.base.BaseActivity;
import com.zhouyu.nft.util.LogOutUtil;
import com.zhouyu.nft.util.SpUtil;

public class SetActivity extends BaseActivity implements View.OnClickListener {

    ImageView iv_back;
    TextView set_pwd,set_agreement,set_writeoff,set_logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);

        iv_back=findViewById(R.id.iv_back);
        set_pwd=findViewById(R.id.set_pwd);
        set_agreement=findViewById(R.id.set_agreement);
//        set_relation=findViewById(R.id.set_relation);
        set_writeoff=findViewById(R.id.set_writeoff);
        set_logout=findViewById(R.id.set_logout);


        iv_back.setOnClickListener(this);
        set_pwd.setOnClickListener(this);
        set_agreement.setOnClickListener(this);
//        set_relation.setOnClickListener(this);
        set_writeoff.setOnClickListener(this);
        set_logout.setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.iv_back:
                finish();
                break;
            case R.id.set_pwd:
                startActivity(new Intent(SetActivity.this,PwdActivity.class));
                break;
            case R.id.set_agreement:
                startActivity(new Intent(SetActivity.this,InRegardToActivity.class));
                break;
//            case R.id.set_relation:
//                break;
            case R.id.set_writeoff:
                startActivity(new Intent(SetActivity.this,WriteOffActivity.class));
                break;
            case R.id.set_logout:
                logout();
                break;
        }
    }

    private void logout() {
        //添加取消
        //添加"Yes"按钮
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setTitle("退出登录")
                .setMessage("您确定要退出吗？")
                .setPositiveButton("确定", (dialogInterface, i) -> {
                    showDialog();
                    LogOutUtil.logOut(mContext);
                })
                .setNegativeButton("取消", (dialogInterface, i) -> {
                })
                .create();
        alertDialog.show();
    }


}