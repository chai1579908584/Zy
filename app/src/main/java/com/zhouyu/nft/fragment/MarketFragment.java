package com.zhouyu.nft.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.zhouyu.nft.R;
import com.zhouyu.nft.activity.SearchActivity;
import com.zhouyu.nft.util.ScreenPopupWindow;

public class MarketFragment extends Fragment implements View.OnClickListener{


    ImageView search_pinpai;
    TextView search;
    Context mContext;
    public MarketFragment(Context context){
        mContext=context;
    }

    RecyclerView recycler;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_market, container, false);

        search_pinpai=inflate.findViewById(R.id.search_pinpai);
        search=inflate.findViewById(R.id.search);
        recycler=inflate.findViewById(R.id.recycler);

        search_pinpai.setOnClickListener(this);
        search.setOnClickListener(this);

//        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
//        recycler.setLayoutManager(staggeredGridLayoutManager);
//        MarkerAdapter markerAdapter=new MarkerAdapter(mContext);
//        markerAdapter.setOnClickListener(id -> startActivity(new Intent(mContext, IntonedDetailsActivity.class)));
//        recycler.setAdapter(markerAdapter);

        return inflate;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.search_pinpai:
//                ScreenPopupWindow screenPopupWindow=new ScreenPopupWindow();
//                screenPopupWindow.setOnClickListener((framerType, showType, collectionType) ->
//                        Log.e("sssssssssssssss","framerType:"+framerType+"||showType:"+showType+"||collectionType:"+collectionType));
//                screenPopupWindow.initPopupWindow(mContext);
                break;
            case R.id.search:
               // startActivity(new Intent(mContext, SearchActivity.class).putExtra("type","2"));
                break;

        }
    }
}
