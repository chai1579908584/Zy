package com.zhouyu.nft.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.zhouyu.nft.R;


public class BoutiqueFragment extends Fragment {

    Context mContext;
    public BoutiqueFragment(Context context){
        mContext=context;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_boutique, container, false);

        TextView viewById = inflate.findViewById(R.id.home);

        return inflate;
    }


}
