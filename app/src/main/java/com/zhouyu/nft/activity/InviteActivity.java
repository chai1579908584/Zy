package com.zhouyu.nft.activity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.zhouyu.nft.R;
import com.zhouyu.nft.base.BaseActivity;
import com.zhouyu.nft.util.ToastUtils;
import com.zhouyu.nft.util.WeChatShare;

public class InviteActivity extends BaseActivity implements View.OnClickListener {

    ImageView iv_back;
    TextView hy,pyq;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite);

        makeStatusBarTransparent(this);
        iv_back=findViewById(R.id.iv_back);
        hy=findViewById(R.id.hy);
        pyq=findViewById(R.id.pyq);

        iv_back.setOnClickListener(this);
        hy.setOnClickListener(this);
        pyq.setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.iv_back:
                finish();
                break;
            case R.id.hy:
//                分享到对话:
//                SendMessageToWX.Req.WXSceneSession
//                分享到朋友圈:
//                SendMessageToWX.Req.WXSceneTimeline ;
//                分享到收藏:
//                SendMessageToWX.Req.WXSceneFavorite
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.lose);
                WeChatShare.sharePicture(this,bitmap, SendMessageToWX.Req.WXSceneSession);
                break;
            case R.id.pyq:
                Bitmap bitmapPyq = BitmapFactory.decodeResource(getResources(),R.mipmap.lose);
                WeChatShare.sharePicture(this,bitmapPyq, SendMessageToWX.Req.WXSceneTimeline);
                break;
        }
    }

}