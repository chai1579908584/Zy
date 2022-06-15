package com.zhouyu.nft.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zhouyu.nft.R;
import com.zhouyu.nft.adapter.BrandHomeAdapter;
import com.zhouyu.nft.adapter.ChainMessageAdapter;
import com.zhouyu.nft.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class ChainMessageActivity extends BaseActivity implements View.OnClickListener {

    ImageView iv_back;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chain_message);

        iv_back=findViewById(R.id.iv_back);
        recyclerView=findViewById(R.id.recycler);


        List<String> list=new ArrayList<>();
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ChainMessageAdapter chainMessageAdapter=new ChainMessageAdapter(this,list);
        recyclerView.setAdapter(chainMessageAdapter);
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