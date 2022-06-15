package com.zhouyu.nft.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.zhouyu.nft.R;
import com.zhouyu.nft.adapter.SearchAdapter;
import com.zhouyu.nft.base.BaseActivity;
import com.zhouyu.nft.util.SpUtil;
import com.zhouyu.nft.util.ToastUtils;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends BaseActivity implements View.OnClickListener {

    ImageView iv_back,delete;
    EditText search;
    TextView sure;
    RecyclerView recycler_record;
    List<String> searchData=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        iv_back=findViewById(R.id.iv_back);
        delete=findViewById(R.id.delete);
        search=findViewById(R.id.search);
        sure=findViewById(R.id.sure);
        recycler_record=findViewById(R.id.recycler_record);

        iv_back.setOnClickListener(this);
        delete.setOnClickListener(this);
        sure.setOnClickListener(this);


        searchData= SpUtil.readSearchData(this);

        setRecycler();

        recycler_record.setLayoutManager(new StaggeredGridLayoutManager(3,  StaggeredGridLayoutManager.VERTICAL));

    }

    private void setRecycler() {
        SearchAdapter searchAdapter=new SearchAdapter(this,searchData);
        searchAdapter.setOnClickListener(position -> {
            searchData.add(0,searchData.get(position));
            SpUtil.searchData(SearchActivity.this,searchData);
            setRecycler();
        });
        recycler_record.setAdapter(searchAdapter);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.iv_back:
                finish();
                break;
            case R.id.delete:
                SpUtil.deleteSearchData(this);
                searchData= SpUtil.readSearchData(this);
                setRecycler();
                break;
            case R.id.sure:
                String searchStr = search.getText().toString();
                if (searchStr.isEmpty())
                {
                    ToastUtils.show(this,"请输入要搜索的内容");
                    return;
                }
                searchData.add(0,searchStr);
                SpUtil.searchData(this,searchData);
                setRecycler();
                break;
        }

    }


}