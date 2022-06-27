package com.zhouyu.nft.util;



import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.zhouyu.nft.R;
import com.zhouyu.nft.view.PwdEditText;

public class PwdPopupWindow {

    @SuppressLint({"WrongConstant", "SetTextI18n"})
    public void initPopupWindow(Context context, String priceStr){
        View contentView = LayoutInflater.from(context).inflate(R.layout.pwd_popupwindow, null, false);
        ImageView cancel=contentView.findViewById(R.id.cancel);
        TextView price=contentView.findViewById(R.id.price);
        PwdEditText pet_pwd=contentView.findViewById(R.id.pet_pwd);

        //实例化PopupWindow并设置宽高
        PopupWindow popupWindow = new PopupWindow(contentView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);

        cancel.setOnClickListener(v -> popupWindow.dismiss());
        price.setText(AmountUtil.changeF2Y(priceStr));
        pet_pwd.setOnInputFinishListener(password -> {
            setOnClick.onClick(password);
            popupWindow.dismiss();
        });
        popupWindow.setOutsideTouchable(true);

        popupWindow.setFocusable(true);

        popupWindow.setSoftInputMode(PopupWindow.INPUT_METHOD_NEEDED);

        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        //点击外部消失
        popupWindow.setOutsideTouchable(true);
        //设置可以点击
        popupWindow.setTouchable(true);
        View rootview = LayoutInflater.from(context).inflate(R.layout.activity_login, null);
        popupWindow.showAtLocation(rootview, Gravity.CENTER, 0, 0);

    }

    SetOnClick setOnClick;
    public void setOnClickListener(SetOnClick setOnClick) {
        this.setOnClick = setOnClick;
    }
    public interface SetOnClick{
        void onClick(String pwdStr);
    }
}
