package com.zhouyu.nft.util;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.zhouyu.nft.R;


public class PayPopupWindow {

    int type=1;

    public void initPopupWindow(Context context) {
        //要在布局中显示的布局
        View contentView = LayoutInflater.from(context).inflate(R.layout.popup_layout, null, false);
        ImageView cancel = contentView.findViewById(R.id.cancel);
        ImageView ling_pay = contentView.findViewById(R.id.ling_pay);
//        ImageView vx_pay = contentView.findViewById(R.id.vx_pay);
//        ImageView zfb_pay = contentView.findViewById(R.id.zfb_pay);
        ImageView bankcard_pay = contentView.findViewById(R.id.bankcard_pay);
        //实例化PopupWindow并设置宽高
        PopupWindow popupWindow = new PopupWindow(contentView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        //点击外部消失
        popupWindow.setOutsideTouchable(true);
        //设置可以点击
        popupWindow.setTouchable(true);
        View rootview = LayoutInflater.from(context).inflate(R.layout.activity_main, null);
        popupWindow.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);

        cancel.setOnClickListener(v -> popupWindow.dismiss());
        ling_pay.setOnClickListener(v -> {
            ling_pay.setImageResource(R.mipmap.select_pay);
         //   vx_pay.setImageResource(R.mipmap.unselect);
         //   zfb_pay.setImageResource(R.mipmap.unselect);
            bankcard_pay.setImageResource(R.mipmap.unselect);
            type=1;
        });
//        vx_pay.setOnClickListener(v -> {
//            ling_pay.setImageResource(R.mipmap.unselect);
//            vx_pay.setImageResource(R.mipmap.select_pay);
//            zfb_pay.setImageResource(R.mipmap.unselect);
//            bankcard_pay.setImageResource(R.mipmap.unselect);
//            type=2;
//        });
//        zfb_pay.setOnClickListener(v -> {
//            ling_pay.setImageResource(R.mipmap.unselect);
//            vx_pay.setImageResource(R.mipmap.unselect);
//            zfb_pay.setImageResource(R.mipmap.select_pay);
//            bankcard_pay.setImageResource(R.mipmap.unselect);
//            type=3;
//        });
        bankcard_pay.setOnClickListener(v -> {
            ling_pay.setImageResource(R.mipmap.unselect);
         //   vx_pay.setImageResource(R.mipmap.unselect);
         //   zfb_pay.setImageResource(R.mipmap.unselect);
            bankcard_pay.setImageResource(R.mipmap.select_pay);
            type=4;
        });
    }
}
