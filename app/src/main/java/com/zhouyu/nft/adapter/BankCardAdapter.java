package com.zhouyu.nft.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zhouyu.nft.R;
import com.zhouyu.nft.bean.BankCardBean;

import java.util.List;

public class BankCardAdapter extends RecyclerView.Adapter<BankCardAdapter.My>{

    private final Context mContext;
    private List<BankCardBean> response;

    public BankCardAdapter(Context context, List<BankCardBean> response){
        mContext=context;
        this.response=response;
    }


    @SuppressLint("NotifyDataSetChanged")
    public void refreshData(List<BankCardBean> response){
        if (response!=null&&response.size()>0)
        {
            this.response.clear();
            this.response.addAll(response);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public My onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_bankcard, parent, false);

        return new My(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull My holder, int position) {

        BankCardBean bankCardBean = response.get(position);

        if ("2".equals(bankCardBean.getCardType()))
        {
            holder.number.setText(bankCardBean.getCardText()+"     贷记卡");
        }else {
            holder.number.setText(bankCardBean.getCardText()+"     储蓄卡");
        }

        if ("1".equals(bankCardBean.getIsDefault()))
        {
            holder.moRen.setVisibility(View.VISIBLE);
        }else {
            holder.moRen.setVisibility(View.GONE);
        }

        holder.default_text.setOnClickListener(v -> setOnClick.onDefault(bankCardBean.getBankId()));
        holder.delete_text.setOnClickListener(v -> setOnClick.onDelete(bankCardBean.getBankId()));
    }

    @Override
    public int getItemCount() {
        return response.size();
    }

    static class My extends RecyclerView.ViewHolder {
        TextView number,default_text,delete_text;
        ImageView moRen;
        public My(@NonNull View itemView) {
            super(itemView);
            number=itemView.findViewById(R.id.number);
            default_text=itemView.findViewById(R.id.default_text);
            delete_text=itemView.findViewById(R.id.delete_text);
            moRen=itemView.findViewById(R.id.moRen);
        }
    }

    SetOnClick setOnClick;
    public void setOnClickListener(SetOnClick setOnClick) {
        this.setOnClick = setOnClick;
    }
    public interface SetOnClick{
        void onDefault(String id);
        void onDelete(String id);
    }
}
