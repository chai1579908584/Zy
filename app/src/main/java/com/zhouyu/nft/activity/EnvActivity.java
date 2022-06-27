package com.zhouyu.nft.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhouyu.nft.R;
import com.zhouyu.nft.base.BaseActivity;
import com.zhouyu.nft.util.ParamsConfigs;
import com.zhouyu.nft.util.SpUtil;
import com.zhouyu.nft.util.ToastUtils;

public class EnvActivity extends BaseActivity implements View.OnClickListener {

    ImageView iv_back;
    TextView official,exploit,test;
    Drawable drawable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_env);

        iv_back=findViewById(R.id.iv_back);
        official=findViewById(R.id.official);
        exploit=findViewById(R.id.exploit);
        test=findViewById(R.id.test);

        iv_back.setOnClickListener(this);
        exploit.setOnClickListener(this);
        test.setOnClickListener(this);
        official.setOnClickListener(this);

        drawable = getResources().getDrawable(R.mipmap.dui);

        switch (ParamsConfigs.ENV)
        {
            case ParamsConfigs.EXPLOIT:
                exploit.setCompoundDrawablesWithIntrinsicBounds(null,null,drawable,null);
                break;
            case ParamsConfigs.TEXT:
                test.setCompoundDrawablesWithIntrinsicBounds(null,null,drawable,null);
                break;
            case ParamsConfigs.ONLINE:
                official.setCompoundDrawablesWithIntrinsicBounds(null,null,drawable,null);
                break;
        }
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.iv_back:
                finish();
                break;
            case R.id.exploit:
                exploit.setCompoundDrawablesWithIntrinsicBounds(null,null,drawable,null);
                test.setCompoundDrawablesWithIntrinsicBounds(null,null,null,null);
                official.setCompoundDrawablesWithIntrinsicBounds(null,null,null,null);
                SpUtil.writeEnv(this,ParamsConfigs.EXPLOIT);
                ToastUtils.show(this,"开发环境");
                reStartApp();
                break;
            case R.id.test:
                exploit.setCompoundDrawablesWithIntrinsicBounds(null,null,null,null);
                test.setCompoundDrawablesWithIntrinsicBounds(null,null,drawable,null);
                official.setCompoundDrawablesWithIntrinsicBounds(null,null,null,null);
                SpUtil.writeEnv(this,ParamsConfigs.TEXT);
                ToastUtils.show(this,"测试环境");
                reStartApp();
                break;
            case R.id.official:
                exploit.setCompoundDrawablesWithIntrinsicBounds(null,null,null,null);
                test.setCompoundDrawablesWithIntrinsicBounds(null,null,null,null);
                official.setCompoundDrawablesWithIntrinsicBounds(null,null,drawable,null);
                SpUtil.writeEnv(this,ParamsConfigs.ONLINE);
                ToastUtils.show(this,"正式环境");
                reStartApp();
                break;
        }
    }

    public void reStartApp() {//重启应用
        new Handler().postDelayed(() -> {
            Intent LaunchIntent=mContext.getPackageManager().getLaunchIntentForPackage(mContext.getPackageName());
            LaunchIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            mContext.startActivity(LaunchIntent);
            System.exit(0);
        }, 100);
    }
}