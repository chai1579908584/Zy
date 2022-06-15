package com.zhouyu.nft.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.zhouyu.nft.R;
import com.zhouyu.nft.activity.AuthenticationActivity;
import com.zhouyu.nft.activity.CollectionOfRecordsActivity;
import com.zhouyu.nft.activity.ChangeMessageActivity;
import com.zhouyu.nft.activity.HelpActivity;
import com.zhouyu.nft.activity.LoginActivity;
import com.zhouyu.nft.activity.SetActivity;
import com.zhouyu.nft.activity.WalletActivity;
import com.zhouyu.nft.api.GXCallback;
import com.zhouyu.nft.api.YzApi;
import com.zhouyu.nft.bean.UserInfo;
import com.zhouyu.nft.util.GlideUtil;
import com.zhouyu.nft.util.SpUtil;

public class MeFragment extends Fragment implements View.OnClickListener{

    ImageView head_image;
    TextView record,wallet,real_name,name,age,inviteCode,introduce,set,help;
    LinearLayout ll_change;

    Context mContext;
    public MeFragment(Context context){
        mContext=context;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_me, container, false);

        head_image=inflate.findViewById(R.id.head_image);
        record=inflate.findViewById(R.id.record);
        wallet=inflate.findViewById(R.id.wallet);
        real_name=inflate.findViewById(R.id.real_name);
        ll_change=inflate.findViewById(R.id.ll_change);
        name=inflate.findViewById(R.id.name);
        age=inflate.findViewById(R.id.age);
        inviteCode=inflate.findViewById(R.id.inviteCode);
      //  walletAdress=inflate.findViewById(R.id.walletAdress);
        introduce=inflate.findViewById(R.id.introduce);
        set=inflate.findViewById(R.id.set);
        help=inflate.findViewById(R.id.help);

        record.setOnClickListener(this);
        wallet.setOnClickListener(this);
        real_name.setOnClickListener(this);
        ll_change.setOnClickListener(this);
        set.setOnClickListener(this);
        help.setOnClickListener(this);

        return inflate;
    }

    @SuppressLint({"UseCompatLoadingForDrawables", "SetTextI18n"})
    private void setMessage() {
        UserInfo userInfo= SpUtil.readData(mContext);
        GlideUtil.GlideCir(mContext,userInfo.getHeadImg(),head_image,300);
        name.setText(userInfo.getNick());
        age.setText(userInfo.getAge());
        Drawable drawable;
        if ("1".equals(userInfo.getSex()))
        {
            drawable = getResources().getDrawable(R.mipmap.man);
        }else {
            drawable = getResources().getDrawable(R.mipmap.woman);
        }
        age.setCompoundDrawablesWithIntrinsicBounds(drawable,null,null,null);
        String inviteCodeStr = userInfo.getInviteCode();
        if (inviteCodeStr==null||inviteCodeStr.isEmpty())
        {
            inviteCode.setVisibility(View.GONE);
        }else {
            inviteCode.setText("邀请码:"+userInfo.getInviteCode());
        }
       // walletAdress.setText(userInfo.getWalletAdress());
        introduce.setText(userInfo.getIntroduce());
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.record:
                jumpClass(CollectionOfRecordsActivity.class);
                break;
            case R.id.wallet:
                jumpClass(WalletActivity.class);
                break;
            case R.id.real_name:
                jumpClass(AuthenticationActivity.class);
                break;
            case R.id.ll_change:
                jumpClass(ChangeMessageActivity.class);
                break;
            case R.id.set:
                jumpClass(SetActivity.class);
                break;
            case R.id.help:
                jumpClass(HelpActivity.class);
                break;
        }
    }

    private void jumpClass(Class<?> classJ) {
        startActivity(new Intent(mContext, classJ));
    }

    @Override
    public void onResume() {
        super.onResume();
        UserInfo userInfo = SpUtil.readData(mContext);
        String token=userInfo.getToken();
        if (token==null||token.isEmpty())
        {
            setMessage();
        }else {
            getMessage();
        }
    }

    private void getMessage() {
        YzApi.getMessage(mContext, new GXCallback<UserInfo>() {
            @Override
            public void onSuccess(UserInfo response, int id) {
                SpUtil.writeData(mContext,response);
                setMessage();
            }
        });
    }
}
