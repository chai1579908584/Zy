package com.zhouyu.nft.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.zhouyu.nft.R;
import com.zhouyu.nft.api.GXCallback;
import com.zhouyu.nft.api.YzApi;
import com.zhouyu.nft.base.BaseActivity;
import com.zhouyu.nft.bean.UpDataBean;
import com.zhouyu.nft.bean.UserInfo;
import com.zhouyu.nft.util.GlideUtil;
import com.zhouyu.nft.util.SpUtil;
import com.zhouyu.nft.util.ToastUtils;

import java.io.File;

import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

public class ChangeMessageActivity extends BaseActivity implements View.OnClickListener {

    private final int CHOOSE_PHOTO=101;
    ImageView iv_back;
    ImageView head_image;
    LinearLayout ll_man,ll_woman;
    ImageView man,woman;
    TextView sure;
    EditText name,signature,age;
    File file;
    String headUrl="";
    String sex="1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_message);

        head_image=findViewById(R.id.head_image);
        iv_back=findViewById(R.id.iv_back);
        ll_man=findViewById(R.id.ll_man);
        ll_woman=findViewById(R.id.ll_woman);
        man=findViewById(R.id.man);
        woman=findViewById(R.id.woman);
        sure=findViewById(R.id.sure);
        name=findViewById(R.id.name);
        age=findViewById(R.id.age);
        signature=findViewById(R.id.signature);

        head_image.setOnClickListener(this);
        iv_back.setOnClickListener(this);
        ll_man.setOnClickListener(this);
        ll_woman.setOnClickListener(this);
        sure.setOnClickListener(this);

        setData();
    }

    private void setData() {
        UserInfo userInfo= SpUtil.readData(this);
        headUrl=userInfo.getHeadImg();
        GlideUtil.GlideCir(this,headUrl,head_image,300);
        name.setText(userInfo.getNick());
        signature.setText(userInfo.getIntroduce());
        if ("1".equals(userInfo.getSex()))
        {
            sex="1";
            man.setImageResource(R.mipmap.ic_checked);
            woman.setImageResource(R.mipmap.ic_unchecked);
        }else {
            sex="0";
            woman.setImageResource(R.mipmap.ic_checked);
            man.setImageResource(R.mipmap.ic_unchecked);
        }
        age.setText(userInfo.getAge());
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.iv_back:
                finish();
                break;
            case R.id.head_image:
                sharePhoto();
                break;
            case R.id.ll_man:
                sex="1";
                man.setImageResource(R.mipmap.ic_checked);
                woman.setImageResource(R.mipmap.ic_unchecked);
                break;
            case R.id.ll_woman:
                sex="0";
                woman.setImageResource(R.mipmap.ic_checked);
                man.setImageResource(R.mipmap.ic_unchecked);
                break;
            case R.id.sure:
                getData();
                break;
        }
    }

    private void getData() {
         YzApi.getChangeMessage(this, headUrl, age.getText().toString(), signature.getText().
                 toString(), name.getText().toString(), sex, new GXCallback<String>() {
             @Override
             public void onSuccess(String response, int id) {
                 ToastUtils.show(ChangeMessageActivity.this,"修改成功");
                 finish();
             }
         });
    }

    /**
     * 选图片
     */
    private void sharePhoto() {
//判断是否授权这里以一个权限为例
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
//没有授权进行权限申请
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }else {
            openAlbum();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // requestCode识别，找到我自己定义的requestCode
        if (requestCode==1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openAlbum();
            } else {
                ToastUtils.show(this,"请开启相册权限");
            }
        }
    }

    private void openAlbum() {
        Intent intent = new Intent("android.intent.action.PICK");
        intent.setType("image/*");
        startActivityForResult(intent, CHOOSE_PHOTO); // 打开相册
    }

    @SuppressLint("CheckResult")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==CHOOSE_PHOTO&&data!=null)
        {
            Uri uri=data.getData();
            file= uri2File(uri);
            Compression(getApplicationContext(), file, new OnCompressListener() {
                @Override
                public void onStart() {
                }
                @Override
                public void onSuccess(File file2) {
                    file=file2;
                    upData(file);
                }
                @Override
                public void onError(Throwable e) {
                }
            });
        }
    }

    //图片压缩
    public static void Compression(Context content, File file, OnCompressListener compressListener) {
        String path= file.getPath();
        Luban.with(content)
                .load(path)
                .ignoreBy(60)
                .setTargetDir(getPath(content))
                .setCompressListener(compressListener).launch();
    }
    private static String getPath(Context content) {
        return content.getCacheDir().getPath();
    }

    private void upData(File file) {
        YzApi.getUpData(file, "1", new GXCallback<UpDataBean>() {
            @Override
            public void onSuccess(UpDataBean response, int id) {
                GlideUtil.GlideCir(ChangeMessageActivity.this,response.getPath(),head_image,300);
                headUrl=response.getImgUrl();
            }
        });
    }

    private File uri2File(Uri uri) {
        String img_path;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor actualimagecursor = managedQuery(uri, proj, null,
                null, null);
        if (actualimagecursor == null) {
            img_path = uri.getPath();
        } else {
            int actual_image_column_index = actualimagecursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            actualimagecursor.moveToFirst();
            img_path = actualimagecursor
                    .getString(actual_image_column_index);
        }
        return new File(img_path);
    }
}