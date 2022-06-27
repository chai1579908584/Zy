package com.zhouyu.nft.activity;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhouyu.nft.R;
import com.zhouyu.nft.base.BaseActivity;

public class CollectionOfRecordsActivity extends BaseActivity implements View.OnClickListener {

    ImageView iv_back;
    TextView buy,sell,donation,airdrop,mystery,fuse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection_of_records);

        iv_back=findViewById(R.id.iv_back);
        buy=findViewById(R.id.buy);
        sell=findViewById(R.id.sell);
        donation=findViewById(R.id.donation);
        airdrop=findViewById(R.id.airdrop);
        mystery=findViewById(R.id.mystery);
        fuse=findViewById(R.id.fuse);


        iv_back.setOnClickListener(this);
        buy.setOnClickListener(this);
        sell.setOnClickListener(this);
        donation.setOnClickListener(this);
        airdrop.setOnClickListener(this);
        mystery.setOnClickListener(this);
        fuse.setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.iv_back:
                finish();
                break;
            case R.id.buy:
                startActivity(new Intent(CollectionOfRecordsActivity.this,BuyRecordActivity.class));
                break;
            case R.id.sell:
//                startActivity(new Intent(CollectionOfRecordsActivity.this,SellRecordActivity.class));
                startActivity(new Intent(mContext, NoActivity.class)
                        .putExtra("name","出售记录"));
                break;
            case R.id.donation:
//                startActivity(new Intent(CollectionOfRecordsActivity.this,GiveRecordActivity.class));
                startActivity(new Intent(mContext, NoActivity.class)
                        .putExtra("name","转增记录"));
                break;
            case R.id.airdrop:
//                startActivity(new Intent(CollectionOfRecordsActivity.this,AirDropRecordActivity.class));
                startActivity(new Intent(mContext, NoActivity.class)
                        .putExtra("name","空投记录"));
                break;
            case R.id.mystery:
//                startActivity(new Intent(CollectionOfRecordsActivity.this,MysteryRecordActivity.class));
                startActivity(new Intent(mContext, NoActivity.class)
                        .putExtra("name","盲盒记录"));
                break;
            case R.id.fuse:
//                startActivity(new Intent(CollectionOfRecordsActivity.this,FuseRecordActivity.class));
                startActivity(new Intent(mContext, NoActivity.class)
                        .putExtra("name","融合记录"));
                break;
        }
    }
}