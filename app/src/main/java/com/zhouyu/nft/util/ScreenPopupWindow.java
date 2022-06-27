package com.zhouyu.nft.util;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.zhouyu.nft.R;
import com.zhouyu.nft.adapter.AddImageAdapter;
import com.zhouyu.nft.adapter.ScreenAdapter;
import com.zhouyu.nft.bean.BrandDetailBean;
import com.zhouyu.nft.bean.SeriesListBean;
import com.zhouyu.nft.view.FlowLayout;

import java.util.ArrayList;
import java.util.List;


public class ScreenPopupWindow {

    String framerType = "";
    String showType = "-1";
    String collectionType = "";
    List<TextView> textViews = new ArrayList<>();
    List<SeriesListBean> message = new ArrayList<>();
    List<SeriesListBean> zhouYu = new ArrayList<>();
    List<SeriesListBean> brandParty = new ArrayList<>();
    Context context;
    View contentView;
    FlowLayout flow1;

    int FRAMER = 1;
    int SHOW = 2;

    public void initPopupWindow(Context context, List<SeriesListBean> message) {
        this.context=context;
        this.message=message;
        SeriesListBean seriesListBeanMsg=new SeriesListBean();
        seriesListBeanMsg.setSeriesName("全部");
        seriesListBeanMsg.setSid("");
        seriesListBeanMsg.setBid("");
        for (int i = 0; i < message.size(); i++) {
            SeriesListBean seriesListBean = message.get(i);
            if ("0".equals(seriesListBean.getType()))
            {
                zhouYu.add(seriesListBean);
            }else {
                brandParty.add(seriesListBean);
            }
        }
        message.add(0,seriesListBeanMsg);
        zhouYu.add(0,seriesListBeanMsg);
        brandParty.add(0,seriesListBeanMsg);
        //要在布局中显示的布局
        contentView = LayoutInflater.from(context).inflate(R.layout.popupwindow_screen, null, false);
        //实例化PopupWindow并设置宽高
        TextView framerAll = contentView.findViewById(R.id.framerAll);
        TextView framer_zy = contentView.findViewById(R.id.framer_zy);
        TextView framer_pin = contentView.findViewById(R.id.framer_pin);
        List<TextView> tvList = new ArrayList<>();
        framerAll.setOnClickListener(v -> {
            tvList.clear();
            tvList.add(framerAll);
            tvList.add(framer_zy);
            tvList.add(framer_pin);
            setType(tvList, -1, FRAMER);
            setData(message);
        });
        framer_zy.setOnClickListener(v -> {
            tvList.clear();
            tvList.add(framerAll);
            tvList.add(framer_zy);
            tvList.add(framer_pin);
            setType(tvList, 0, FRAMER);
            setData(zhouYu);
        });
        framer_pin.setOnClickListener(v -> {
            tvList.clear();
            tvList.add(framerAll);
            tvList.add(framer_zy);
            tvList.add(framer_pin);
            setType(tvList, 1, FRAMER);
            setData(brandParty);

        });

        TextView show_all = contentView.findViewById(R.id.show_all);
        TextView show_three = contentView.findViewById(R.id.show_three);
        TextView show_video = contentView.findViewById(R.id.show_video);
        TextView show_music = contentView.findViewById(R.id.show_music);
        TextView show_image = contentView.findViewById(R.id.show_image);
        show_all.setOnClickListener(v -> {
            tvList.clear();
            tvList.add(show_all);
            tvList.add(show_image);
            tvList.add(show_three);
            tvList.add(show_video);
            tvList.add(show_music);
            setType(tvList, -1, SHOW);
        });
        show_image.setOnClickListener(v -> {
            tvList.clear();
            tvList.add(show_all);
            tvList.add(show_image);
            tvList.add(show_three);
            tvList.add(show_video);
            tvList.add(show_music);
            setType(tvList, 0, SHOW);
        });
        show_three.setOnClickListener(v -> {
            tvList.clear();
            tvList.add(show_all);
            tvList.add(show_image);
            tvList.add(show_three);
            tvList.add(show_video);
            tvList.add(show_music);
            setType(tvList, 1, SHOW);
        });
        show_video.setOnClickListener(v -> {
            tvList.clear();
            tvList.add(show_all);
            tvList.add(show_image);
            tvList.add(show_three);
            tvList.add(show_video);
            tvList.add(show_music);
            setType(tvList, 2, SHOW);
        });
        show_music.setOnClickListener(v -> {
            tvList.clear();
            tvList.add(show_all);
            tvList.add(show_image);
            tvList.add(show_three);
            tvList.add(show_video);
            tvList.add(show_music);
            setType(tvList, 3, SHOW);
        });

        PopupWindow popupWindow = new PopupWindow(contentView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        contentView.findViewById(R.id.sure).setOnClickListener(v -> {
            setOnClick.onClick(framerType, showType, collectionType);
            popupWindow.dismiss();
        });
        ImageView cancel = contentView.findViewById(R.id.cancel);

        if (message != null && message.size() > 0) {
            setData(message);
        }

        cancel.setOnClickListener(v -> popupWindow.dismiss());
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        //点击外部消失
        popupWindow.setOutsideTouchable(true);
        //设置可以点击
        popupWindow.setTouchable(true);
        View rootview = LayoutInflater.from(context).inflate(R.layout.activity_main, null);
        popupWindow.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);

    }

    public void setData(List<SeriesListBean> message) {
        textViews.clear();
        flow1 = contentView.findViewById(R.id.xun_rage);
        flow1.removeAllViews();
        FlowLayout.MarginLayoutParams pa = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        pa.setMargins(0, 20, 40, 0);
        for (int i = 0; i < message.size(); i++) {
            TextView item = new TextView(context);
            item.setText(message.get(i).getSeriesName());
            item.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 12);
            item.setBackgroundResource(R.drawable.shape_corner_biankuang_989898);
            item.setPadding(50, 10, 50, 10);
            flow1.addView(item, pa);
            if (i==0)
            {
                item.setBackgroundResource(R.drawable.shape_corner_jianbian);
                item.setTextColor(0xffffffff);
            }
            textViews.add(item);
            int finalI = i;

            item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    framerType=message.get(finalI).getBid();
                    collectionType=message.get(finalI).getSid();
                    for (int i1 = 0; i1 < textViews.size(); i1++) {
                        TextView textView = textViews.get(i1);
                        textView.setBackgroundResource(R.drawable.shape_corner_biankuang_989898);
                        textView.setTextColor(0xff989898);
                    }
                    item.setBackgroundResource(R.drawable.shape_corner_jianbian);
                    item.setTextColor(0xffffffff);
                }
            });
        }
    }

    private void setType(List<TextView> tvList, int position, int type) {
        position++;
        for (int i = 0; i < tvList.size(); i++) {
            TextView textView = tvList.get(i);
            if (position == i) {
                textView.setBackgroundResource(R.drawable.shape_corner_jianbian);
                textView.setTextColor(0xffffffff);
            } else {
                textView.setBackgroundResource(R.drawable.shape_corner_biankuang_989898);
                textView.setTextColor(0xff989898);
            }
        }
        if (type == FRAMER) {
            framerType = (position-1) + "";
            if (type==0)
            {
                setOnClick.onSelect(message);
            }else if (type==1)
            {
                setOnClick.onSelect(zhouYu);
            }else {
                setOnClick.onSelect(brandParty);
            }
        } else {
            showType = position + "";
        }
    }

    SetOnClick setOnClick;

    public void setOnClickListener(SetOnClick setOnClick) {
        this.setOnClick = setOnClick;
    }
    public interface SetOnClick {
        void onClick(String framerType, String showType, String collectionType);
        void onSelect(List<SeriesListBean> message);
    }
}
