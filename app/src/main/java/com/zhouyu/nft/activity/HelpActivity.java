package com.zhouyu.nft.activity;

import static com.zhouyu.nft.activity.ChangeMessageActivity.Compression;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.zhouyu.nft.R;
import com.zhouyu.nft.adapter.AddImageAdapter;
import com.zhouyu.nft.base.BaseActivity;
import com.zhouyu.nft.util.ToastUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import top.zibin.luban.OnCompressListener;

public class HelpActivity extends BaseActivity implements View.OnClickListener {

    ImageView iv_back;
    RecyclerView recycler;

    List<File> fileList=new ArrayList<>();
    File file;
    AddImageAdapter adapter;
    private final int REQUEST_CODE_IMAGE=101;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        iv_back=findViewById(R.id.iv_back);
        recycler=findViewById(R.id.recycler);

        setData();

        iv_back.setOnClickListener(this);

    }

    private void setData() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,3,GridLayoutManager.VERTICAL,false);
        recycler.setLayoutManager(gridLayoutManager);
        adapter=new AddImageAdapter(this,fileList);
        adapter.setOnClickListener(new AddImageAdapter.SetOnClick() {
            @Override
            public void onClick() {
                sharePhoto();
            }
            @Override
            public void onClickItem(int position) {
                ToastUtils.show(HelpActivity.this,"删除"+position);
            }
        });
        recycler.setAdapter(adapter);
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

    private void openAlbum() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select Picture"), REQUEST_CODE_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==REQUEST_CODE_IMAGE && data != null){
            ClipData imageNames = data.getClipData();
            if (imageNames!=null)
            {
                for (int i = 0; i < imageNames.getItemCount(); i++) {
                    if (fileList.size()>=9)
                    {
                        return;
                    }
                    Uri uri = imageNames.getItemAt(i).getUri();
                    file=uri2File(uri);
                    Compression(getApplicationContext(), file, new OnCompressListener() {
                        @Override
                        public void onStart() {
                        }
                        @SuppressLint("NotifyDataSetChanged")
                        @Override
                        public void onSuccess(File file2) {
                            fileList.add(file2);
                            adapter.notifyDataSetChanged();
                        }
                        @Override
                        public void onError(Throwable e) {
                        }
                    });
                }
            }
        }
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

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.iv_back:
                finish();
                break;
        }
    }
}