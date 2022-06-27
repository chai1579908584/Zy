package com.zhouyu.nft.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.zhouyu.nft.R;
import com.zhouyu.nft.api.GXCallback;
import com.zhouyu.nft.api.YzApi;
import com.zhouyu.nft.base.BaseActivity;
import com.zhouyu.nft.bean.InRegardToBean;
import com.zhouyu.nft.util.GlideUtil;
import com.zhouyu.nft.util.ParamsConfigs;
import com.zhouyu.nft.util.ToastUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class InRegardToActivity extends BaseActivity implements View.OnClickListener {

    ImageView iv_back, ewm;
    TextView use, intimity, e_mail;
    String url;
    int MY_PERMISSIONS_REQUEST_SAVE_PICTRUE = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_regard_to);

        iv_back = findViewById(R.id.iv_back);
        use = findViewById(R.id.use);
        intimity = findViewById(R.id.intimity);
        ewm = findViewById(R.id.ewm);
        e_mail = findViewById(R.id.e_mail);

        iv_back.setOnClickListener(this);
        use.setOnClickListener(this);
        intimity.setOnClickListener(this);
        ewm.setOnClickListener(this);

        getData();

    }

    private void getData() {
        YzApi.getInRegardTo(this, new GXCallback<InRegardToBean>() {
            @Override
            public void onSuccess(InRegardToBean response, int id) {
                url = response.getQrcodeUrl();
                if (ewm != null && e_mail != null) {
                    Glide.with(InRegardToActivity.this)
                            .load(url)
                            .into(ewm);
                    e_mail.setText(response.getContactEmail());
                }
            }
        });
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.use:
                startActivity(new Intent(mContext, FingerpostActivity.class)
                        .putExtra("XY", ParamsConfigs.SERVICE_XY)
                        .putExtra("title", "用户服务协议")
                );
                break;
            case R.id.intimity:
                startActivity(new Intent(mContext, FingerpostActivity.class)
                        .putExtra("XY", ParamsConfigs.PRIVACY_XY)
                        .putExtra("title", "用户隐私协议")
                );
                break;
            case R.id.ewm:
                if (url != null && !url.isEmpty()) {
                    requestMyPermissions();
                }
                break;
        }
    }

    private void requestMyPermissions() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            //没有授权，编写申请权限代码
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_SAVE_PICTRUE);
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_SAVE_PICTRUE);
        } else {
            saveImage(createViewBitmap(ewm));
        }
    }

    /**
     * API29 中的最新保存图片到相册的方法
     */
    private void saveImage(Bitmap toBitmap) {
        //开始一个新的进程执行保存图片的操作
        Uri insertUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new ContentValues());
        //使用use可以自动关闭流
        try {
            OutputStream outputStream = getContentResolver().openOutputStream(insertUri, "rw");
            if (toBitmap.compress(Bitmap.CompressFormat.JPEG, 90, outputStream)) {
               ToastUtils.show("保存成功");
            } else {
                ToastUtils.show("保存失败");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    //将要存为图片的view传进来 生成bitmap对象
    public Bitmap createViewBitmap(View v) {
        Bitmap bitmap = Bitmap.createBitmap(v.getWidth(), v.getHeight(),
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        v.draw(canvas);
        return bitmap;
    }


}