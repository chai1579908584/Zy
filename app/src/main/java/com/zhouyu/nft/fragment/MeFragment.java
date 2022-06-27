package com.zhouyu.nft.fragment;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.zhouyu.nft.R;
import com.zhouyu.nft.activity.AuthenticationActivity;
import com.zhouyu.nft.activity.CollectionOfRecordsActivity;
import com.zhouyu.nft.activity.ChangeMessageActivity;
import com.zhouyu.nft.activity.FingerpostActivity;
import com.zhouyu.nft.activity.HelpActivity;
import com.zhouyu.nft.activity.InviteActivity;
import com.zhouyu.nft.activity.MyCollectionActivity;
import com.zhouyu.nft.activity.NoActivity;
import com.zhouyu.nft.activity.SetActivity;
import com.zhouyu.nft.activity.WalletActivity;
import com.zhouyu.nft.api.GXCallback;
import com.zhouyu.nft.api.GXResponse;
import com.zhouyu.nft.api.YzApi;
import com.zhouyu.nft.bean.UserInfo;
import com.zhouyu.nft.util.GlideUtil;
import com.zhouyu.nft.util.LogOutUtil;
import com.zhouyu.nft.util.ParamsConfigs;
import com.zhouyu.nft.util.SpUtil;
import com.zhouyu.nft.util.ToastUtils;

public class MeFragment extends Fragment implements View.OnClickListener{

    ImageView head_image;
    TextView record,wallet,real_name,name,age,inviteCode,introduce,set,help,invite,fingerpost,num,fuse,luckyvalue;
    RelativeLayout rl_change;
    LinearLayout llMyCollection;

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
        rl_change=inflate.findViewById(R.id.rl_change);
        name=inflate.findViewById(R.id.name);
        age=inflate.findViewById(R.id.age);
        inviteCode=inflate.findViewById(R.id.inviteCode);
      //  walletAdress=inflate.findViewById(R.id.walletAdress);
        introduce=inflate.findViewById(R.id.introduce);
        set=inflate.findViewById(R.id.set);
        help=inflate.findViewById(R.id.help);
        invite=inflate.findViewById(R.id.invite);
        fingerpost=inflate.findViewById(R.id.fingerpost);
        llMyCollection=inflate.findViewById(R.id.llMyCollection);
        num=inflate.findViewById(R.id.num);
        fuse=inflate.findViewById(R.id.fuse);
        luckyvalue=inflate.findViewById(R.id.luckyvalue);

        record.setOnClickListener(this);
        wallet.setOnClickListener(this);
        real_name.setOnClickListener(this);
        rl_change.setOnClickListener(this);
        set.setOnClickListener(this);
        help.setOnClickListener(this);
        invite.setOnClickListener(this);
        fingerpost.setOnClickListener(this);
        llMyCollection.setOnClickListener(this);
        fuse.setOnClickListener(this);
        luckyvalue.setOnClickListener(this);

        return inflate;
    }

    @SuppressLint({"UseCompatLoadingForDrawables", "SetTextI18n"})
    private void setMessage() {
        UserInfo userInfo= SpUtil.readData(mContext);
        GlideUtil.GlideCirHead(mContext,userInfo.getHeadImg(),head_image,300);
        name.setText(userInfo.getNick());
        age.setText(userInfo.getAge());
        Drawable drawable;
        if ("1".equals(userInfo.getSex()))
        {
            drawable = getResources().getDrawable(R.mipmap.man);
            age.setCompoundDrawablesWithIntrinsicBounds(drawable,null,null,null);
        }else if ("0".equals(userInfo.getSex())){
            drawable = getResources().getDrawable(R.mipmap.woman);
            age.setCompoundDrawablesWithIntrinsicBounds(drawable,null,null,null);
        }else {
            age.setCompoundDrawablesWithIntrinsicBounds(null,null,null,null);
        }
        String inviteCodeStr = userInfo.getInviteCode();
        if (inviteCodeStr==null||inviteCodeStr.isEmpty())
        {
            inviteCode.setVisibility(View.GONE);
        }else {
            inviteCode.setText("邀请码:"+inviteCodeStr);
            inviteCode.setVisibility(View.VISIBLE);
            inviteCode.setOnClickListener(v -> copy(inviteCodeStr));
        }
       // walletAdress.setText(userInfo.getWalletAdress());
        String introduceStr = userInfo.getIntroduce();
        if (introduceStr.isEmpty())
        {
            introduceStr="请填写您的个人信息...";
        }
        introduce.setText(introduceStr);
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
            case R.id.rl_change:
                jumpClass(ChangeMessageActivity.class);
                break;
            case R.id.set:
                jumpClass(SetActivity.class);
                break;
            case R.id.help:
                jumpClass(HelpActivity.class);
                break;
            case R.id.invite:
                jumpClass(InviteActivity.class);
                break;
            case R.id.fingerpost:
                startActivity(new Intent(mContext, FingerpostActivity.class)
                        .putExtra("XY", ParamsConfigs.FINGER_POST)
                        .putExtra("title","新手指南")
                );
                break;
            case R.id.llMyCollection:
                jumpClass(MyCollectionActivity.class);
                break;
            case R.id.fuse:
                startActivity(new Intent(mContext, NoActivity.class)
                        .putExtra("name","融合"));
                break;
            case R.id.luckyvalue:
                startActivity(new Intent(mContext, NoActivity.class)
                        .putExtra("name","幸运值"));
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
        if (token!=null&&!token.isEmpty())
        {
            getMessage();
        }
    }

    private void getMessage() {
        YzApi.getMessage(mContext, new GXCallback<UserInfo>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onSuccess(UserInfo response, int id) {
                SpUtil.writeData(mContext,response);
                String numStr=response.getNftNum();
                if (numStr!=null&&!numStr.isEmpty())
                {
                    num.setText("共拥有"+response.getNftNum()+"件藏品");
                }else {
                    num.setText("共拥有0件藏品");
                }
                setMessage();
            }
            @Override
            public void onFailure(GXResponse<String> response, int id) {
                super.onFailure(response, id);
                if (1001==response.code)
                {
                    LogOutUtil.logOutNoData(mContext);
                }
            }
        });
    }

    //复制
    private void copy(String data) {
        // 获取系统剪贴板
        ClipboardManager clipboard = (ClipboardManager)mContext.getSystemService(Context.CLIPBOARD_SERVICE);
        // 创建一个剪贴数据集，包含一个普通文本数据条目（需要复制的数据）,其他的还有
        // newHtmlText、
        // newIntent、
        // newUri、
        // newRawUri
        ClipData clipData = ClipData.newPlainText(null, data);

        // 把数据集设置（复制）到剪贴板
        clipboard.setPrimaryClip(clipData);
        ToastUtils.show(mContext,"复制成功");
    }
}
