package com.zhouyu.nft.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zhouyu.nft.R;
import com.zhouyu.nft.adapter.BrandListAdapter;
import com.zhouyu.nft.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class BrandListActivity extends BaseActivity implements View.OnClickListener {

    ImageView iv_back;
    RecyclerView recycler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_list);

        iv_back=findViewById(R.id.iv_back);
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
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
        recycler.setLayoutManager(new LinearLayoutManager(this));
        BrandListAdapter brandListAdapter=new BrandListAdapter(this,list);
        brandListAdapter.setOnClickListener(new BrandListAdapter.SetOnClick() {
            @Override
            public void onClick(String id) {

            }
        });
        recycler.setAdapter(brandListAdapter);
        iv_back.setOnClickListener(this);

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