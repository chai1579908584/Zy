package com.zhouyu.nft.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zhouyu.nft.R;
import com.zhouyu.nft.bean.BankCodeBean;
import com.zhouyu.nft.util.GlideUtil;

import java.util.List;

public class BankCardCodeAdapter extends RecyclerView.Adapter<BankCardCodeAdapter.My>{

    private final Context mContext;
    private final List<BankCodeBean> messageList;

    public BankCardCodeAdapter(Context context, List<BankCodeBean> messageList){
        mContext=context;
        this.messageList=messageList;
    }
    @NonNull
    @Override
    public My onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_bankcardcode, parent, false);

        return new My(view);
    }

    @Override
    public void onBindViewHolder(@NonNull My holder, int position) {
        BankCodeBean bankCodeBean = messageList.get(position);
        holder.bankcard_name.setText(bankCodeBean.getDictLabel());
        holder.itemView.setOnClickListener(v -> setOnClick.onClick(bankCodeBean.getDictValue(),bankCodeBean.getDictLabel()));
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    static class My extends RecyclerView.ViewHolder {

        TextView bankcard_name;
        public My(@NonNull View itemView) {
            super(itemView);
            bankcard_name=itemView.findViewById(R.id.bankcard_name);
        }
    }

    SetOnClick setOnClick;
    public void setOnClickListener(SetOnClick setOnClick) {
        this.setOnClick = setOnClick;
    }
    public interface SetOnClick{
        void onClick(String code,String name);
    }
}
