package com.zhouyu.nft.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import androidx.annotation.NonNull;


import com.zhouyu.nft.R;
import com.zhouyu.nft.base.BaseActivity;

import java.util.Timer;
import java.util.TimerTask;

public class GuidePageActivity extends BaseActivity {

    private int second=0;
    private Timer timer;

    Handler handler=new Handler(Looper.myLooper()){
        @SuppressLint({"ShowToast", "SetTextI18n"})
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.arg1==0)
            {
                if (timer!=null)
                {
                   timer.cancel();
                }
                Intent intent=new Intent(GuidePageActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guidepage);
    }

    @Override
    protected void onResume() {
        super.onResume();
        timer=new Timer();
        TimerTask task=new TimerTask() {
            @Override
            public void run() {
                Message message=new Message();
                message.arg1=second;
                handler.sendMessage(message);
                second--;
            }
        };
        timer.schedule(task,1000,1000);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (timer!=null)
        {
            timer.cancel();
            timer=null;
        }
    }
}