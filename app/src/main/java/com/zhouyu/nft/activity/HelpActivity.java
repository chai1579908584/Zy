package com.zhouyu.nft.activity;

import static com.zhouyu.nft.activity.ChangeMessageActivity.Compression;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zhouyu.nft.R;
import com.zhouyu.nft.adapter.AddImageAdapter;
import com.zhouyu.nft.api.GXCallback;
import com.zhouyu.nft.api.GXResponse;
import com.zhouyu.nft.api.YzApi;
import com.zhouyu.nft.base.BaseActivity;
import com.zhouyu.nft.bean.PresellBean;
import com.zhouyu.nft.bean.UpDataBean;
import com.zhouyu.nft.util.ToastUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import top.zibin.luban.OnCompressListener;

public class HelpActivity extends BaseActivity implements View.OnClickListener {

    ImageView iv_back;
    RecyclerView recycler;
    TextView select,sure;
    EditText ed_content;

    List<File> fileList=new ArrayList<>();
    File file;
    AddImageAdapter adapter;
    private final int REQUEST_CODE_IMAGE=101;
    private int position=0;
    String imageUrl="";
    String type="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        iv_back=findViewById(R.id.iv_back);
        recycler=findViewById(R.id.recycler);
        select=findViewById(R.id.select);
        sure=findViewById(R.id.sure);
        ed_content=findViewById(R.id.ed_content);

        setData();

        iv_back.setOnClickListener(this);
        select.setOnClickListener(this);
        sure.setOnClickListener(this);

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

    @SuppressLint("InlinedApi")
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

                    Uri uri = imageNames.getItemAt(i).getUri();
                    file=uri2File(uri);
                    Compression(getApplicationContext(), file, new OnCompressListener() {
                        @Override
                        public void onStart() {
                        }
                        @SuppressLint("NotifyDataSetChanged")
                        @Override
                        public void onSuccess(File file2) {
                            if (fileList.size()<9)
                            {
                                fileList.add(file2);
                                adapter.notifyDataSetChanged();
                            }
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
            case R.id.select:
                setPopupWindow();
                break;
            case R.id.sure:
                String content = ed_content.getText().toString();
                if (type.isEmpty()||content.isEmpty())
                {
                    ToastUtils.show(this,"请填写信息");
                    return;
                }
                showDialog();
                if (fileList.size()>0)
                {
                    upData();
                }else {
                    getData();
                }
                break;
        }
    }

    private void getData() {
        YzApi.getHelp(this, ed_content.getText().toString(), imageUrl, type, new GXCallback<String>() {
            @Override
            public void onSuccess(String response, int id) {
                dismissDialog();
                ToastUtils.show(HelpActivity.this,"提交成功");
                finish();
            }
        });
    }

    private void upData() {
        YzApi.getUpData(fileList.get(position), "2", new GXCallback<UpDataBean>() {
            @Override
            public void onSuccess(UpDataBean response, int id) {
                if (position+1<fileList.size())
                {
                    position++;
                    imageUrl=imageUrl+response.getImgUrl()+",";
                    upData();
                }else {
                    imageUrl=imageUrl+response.getImgUrl();
                    getData();
                }
            }
        });
    }


    private void setPopupWindow() {
        View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.popupwindow_question, null, false);
        TextView autonym=view.findViewById(R.id.autonym);
        TextView function=view.findViewById(R.id.function);
        TextView product=view.findViewById(R.id.product);
        TextView rest=view.findViewById(R.id.rest);
        PopupWindow mPopupWindow = new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        autonym.setOnClickListener(v -> { select.setText(autonym.getText().toString());type="1"; mPopupWindow.dismiss();});
        function.setOnClickListener(v -> {select.setText(function.getText().toString());type="2";mPopupWindow.dismiss();});
        product.setOnClickListener(v -> {select.setText(product.getText().toString());type="3";mPopupWindow.dismiss();});
        rest.setOnClickListener(v -> {select.setText(rest.getText().toString());type="20";mPopupWindow.dismiss();});
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setFocusable(true);
        mPopupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mPopupWindow.getContentView().measure(0, 0);
        mPopupWindow.showAsDropDown(select);
    }
}