package com.zhouyu.nft.activity;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zhouyu.nft.R;
import com.zhouyu.nft.adapter.BankCardAdapter;
import com.zhouyu.nft.adapter.ChainMessageAdapter;
import com.zhouyu.nft.base.BaseActivity;
import com.zhouyu.nft.view.SlideRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class BankCardActivity extends BaseActivity implements View.OnClickListener {

    ImageView iv_back;
    TextView add_bankcard;
    LinearLayout ll_default;
    SlideRecyclerView recycler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_card_avtivity);

        iv_back=findViewById(R.id.iv_back);
        add_bankcard=findViewById(R.id.add_bankcard);
        ll_default=findViewById(R.id.ll_default);
        recycler=findViewById(R.id.recycler);

        List<String> list=new ArrayList<>();
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
        recycler.setLayoutManager(new LinearLayoutManager(this));
        BankCardAdapter bankCardAdapter=new BankCardAdapter(this,list);
        recycler.setAdapter(bankCardAdapter);
        iv_back.setOnClickListener(this);


        iv_back.setOnClickListener(this);
        add_bankcard.setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.iv_back:
                finish();
                break;
            case R.id.add_bankcard:
                startActivity(new Intent(BankCardActivity.this,AddBankCardActivity.class));
                break;
        }
    }
}