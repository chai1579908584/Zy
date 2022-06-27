package com.zhouyu.nft.activity;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zhouyu.nft.R;
import com.zhouyu.nft.adapter.BankCardAdapter;
import com.zhouyu.nft.adapter.ChainMessageAdapter;
import com.zhouyu.nft.api.GXCallback;
import com.zhouyu.nft.api.YzApi;
import com.zhouyu.nft.base.BaseActivity;
import com.zhouyu.nft.bean.BankCardBean;
import com.zhouyu.nft.util.ToastUtils;
import com.zhouyu.nft.view.SlideRecyclerView;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

public class BankCardActivity extends BaseActivity implements View.OnClickListener {

    ImageView iv_back;
    TextView add_bankcard;
    LinearLayout ll_default;
    SlideRecyclerView recycler;
    BankCardAdapter bankCardAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_card_avtivity);

        iv_back=findViewById(R.id.iv_back);
        add_bankcard=findViewById(R.id.add_bankcard);
        ll_default=findViewById(R.id.ll_default);
        recycler=findViewById(R.id.recycler);


        iv_back.setOnClickListener(this);
        add_bankcard.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getData();
    }

    private void getData() {
        YzApi.getBankCard(this, new GXCallback<List<BankCardBean>>() {
            @Override
            public void onSuccess(List<BankCardBean> response, int id) {
                if (bankCardAdapter!=null)
                {
                    bankCardAdapter.refreshData(response);
                }else {
                    setData(response);
                }
            }
        });
    }

    private void setData(List<BankCardBean> response) {
        if (response==null||response.size()==0)
        {
            ll_default.setVisibility(View.VISIBLE);
            return;
        }
        recycler.setLayoutManager(new LinearLayoutManager(this));
        bankCardAdapter=new BankCardAdapter(this,response);
        bankCardAdapter.setOnClickListener(new BankCardAdapter.SetOnClick() {
            @Override
            public void onDefault(String id) {
                setDefault(id);
            }
            @Override
            public void onDelete(String id) {
                setDelete(id);
            }
        });
        recycler.setAdapter(bankCardAdapter);
        iv_back.setOnClickListener(this);
        ll_default.setVisibility(View.GONE);
    }

    private void setDelete(String id) {
     YzApi.getDeleteBankCard(this, id, new GXCallback<String>() {
         @SuppressLint("NotifyDataSetChanged")
         @Override
         public void onSuccess(String response, int id) {
             ToastUtils.show("删除成功");
             getData();
         }
     });
    }

    private void setDefault(String id) {
        YzApi.getDefaultBankCard(this, id, new GXCallback<String>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onSuccess(String response, int id) {
                ToastUtils.show("设置成功");
                getData();
            }
        });
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