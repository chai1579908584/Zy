package com.zhouyu.nft.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.zhouyu.nft.R;
import com.zhouyu.nft.activity.IntonedDetailsActivity;


public class MysteryFragment extends Fragment implements View.OnClickListener{

    Context mContext;
    TextView home;
    public MysteryFragment(Context context){
        mContext=context;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_mystery, container, false);

         home = inflate.findViewById(R.id.home);

         home.setOnClickListener(this);
        return inflate;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
        }
    }
}
