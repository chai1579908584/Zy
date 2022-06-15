package com.zhouyu.nft.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zhouyu.nft.R;
import com.zhouyu.nft.activity.OutReceptionDetailsActivity;
import com.zhouyu.nft.adapter.SellRecordAdapter;

public class ReceptionFragment extends Fragment  {
    RecyclerView recyclerView;

    Context mContext;
    public ReceptionFragment(Context context){
        mContext=context;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_reception, container, false);

        recyclerView=inflate.findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        SellRecordAdapter sellRecordAdapter=new SellRecordAdapter(mContext);
        sellRecordAdapter.setOnClickListener(id -> startActivity(new Intent(mContext, OutReceptionDetailsActivity.class)
                .putExtra("id",id).putExtra("type","2")));
        recyclerView.setAdapter(sellRecordAdapter);
        return inflate;
    }
}
