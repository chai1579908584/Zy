package com.zhouyu.nft.activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zhouyu.nft.R;
import com.zhouyu.nft.adapter.FuseRecordAdapter;
import com.zhouyu.nft.adapter.SellRecordAdapter;
import com.zhouyu.nft.base.BaseActivity;

public class FuseRecordActivity extends BaseActivity implements View.OnClickListener {

    ImageView iv_back;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fuse_record);

        iv_back=findViewById(R.id.iv_back);
        recyclerView=findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        FuseRecordAdapter fuseRecordAdapter=new FuseRecordAdapter(this);
        fuseRecordAdapter.setOnClickListener(id -> startActivity(new Intent(FuseRecordActivity.this,FuseActivity.class).putExtra("id",id)));
        recyclerView.setAdapter(fuseRecordAdapter);

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