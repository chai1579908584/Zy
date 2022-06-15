package com.zhouyu.nft.util;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhouyu.nft.R;

public class CodePopupWindow {


    @SuppressLint("WrongConstant")
    public void initPopupWindow(Context context, String codeImg){
        View contentView = LayoutInflater.from(context).inflate(R.layout.code_popupwindow, null, false);
        TextView cancel=contentView.findViewById(R.id.cancel);
        TextView sure=contentView.findViewById(R.id.sure);
        EditText ed_code=contentView.findViewById(R.id.ed_code);
        ImageView image_code=contentView.findViewById(R.id.image_code);

        //实例化PopupWindow并设置宽高
        PopupWindow popupWindow = new PopupWindow(contentView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        popupWindow.setOutsideTouchable(true);

        popupWindow.setFocusable(true);

        popupWindow.setSoftInputMode(PopupWindow.INPUT_METHOD_NEEDED);

        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        ed_code.setFocusable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        //点击外部消失
        popupWindow.setOutsideTouchable(true);
        //设置可以点击
        popupWindow.setTouchable(true);
        View rootview = LayoutInflater.from(context).inflate(R.layout.activity_login, null);
        popupWindow.showAtLocation(rootview, Gravity.CENTER, 0, 0);

        Glide.with(context)
                .load(codeImg)
                .into(image_code);

        cancel.setOnClickListener(v -> {
            popupWindow.dismiss();
            setOnClick.onCancel();
        });
        sure.setOnClickListener(v -> {
            String codeStr = ed_code.getText().toString();
            setOnClick.onClick(codeStr);
            popupWindow.dismiss();
        });
    }

    SetOnClick setOnClick;
    public void setOnClickListener(SetOnClick setOnClick) {
        this.setOnClick = setOnClick;
    }
    public interface SetOnClick{
        void onClick(String codeStr);
        void onCancel();
    }
}
