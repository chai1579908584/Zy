package com.zhouyu.nft.api;

import android.content.Context;
import android.util.Log;

import com.zhouyu.nft.bean.BannerBean;
import com.zhouyu.nft.bean.GraphicBean;
import com.zhouyu.nft.bean.InformationBean;
import com.zhouyu.nft.bean.PresellBean;
import com.zhouyu.nft.bean.UpDataBean;
import com.zhouyu.nft.bean.UserInfo;
import com.zhouyu.nft.util.AppContextUtils;
import com.zhouyu.nft.util.HmacShaUtil;
import com.zhouyu.nft.util.ParamsConfigs;
import com.zhouyu.nft.util.SpUtil;
import com.zhouyu.nft.util.StringUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.PostStringBuilder;

import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import okhttp3.MediaType;

public class YzApi {

    public static PostStringBuilder AddHeader(Context context,JSONObject jsonStr,String url){

        String token="";
        String nonce = UUID.randomUUID().toString();
        String strSign = StringUtils.sorkValueResult(jsonStr.toString()) + nonce;

        UserInfo userInfo= SpUtil.readData(context);
        if (userInfo.getToken()!=null)
        {
            token = userInfo.getToken();
        }
        Log.e("ssssssssssUpDData",jsonStr+token);

//        return OkHttpUtils.post()
//                .addHeader("appid", ParamsConfigs.REQUEST_APP_ID)
//                .addHeader("appver", Objects.requireNonNull(AppContextUtils.getPackageInfo(context)).versionName
//                + "." + Objects.requireNonNull(AppContextUtils.getPackageInfo(context)).versionCode)
//                .addHeader("deviceid",AppContextUtils.getDeviceId(context))
//                .addHeader("cid","100000")
//                .addHeader("ts","" + System.currentTimeMillis())
//                .addHeader("token", token)
//                .addHeader("nonce", UUID.randomUUID().toString())
//                .addHeader("signature",HmacShaUtil.getHmacSha1(jsonStr+UUID.randomUUID().toString(),ParamsConfigs.HMAC_SHA1_KEY));

        return OkHttpUtils.postString()
                .addHeader("appid", ParamsConfigs.REQUEST_APP_ID)
                .addHeader("appver", Objects.requireNonNull(AppContextUtils.getPackageInfo(context)).versionName
                        + "." + Objects.requireNonNull(AppContextUtils.getPackageInfo(context)).versionCode)
                .addHeader("deviceid", AppContextUtils.getDeviceId(context))
                .addHeader("cid", "100000")
                .addHeader("ts", "" + System.currentTimeMillis())
                .addHeader("token", token)
                .addHeader("nonce", nonce)
                .addHeader("signature", HmacShaUtil.getHmacSha1(strSign, ParamsConfigs.HMAC_SHA1_KEY))
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .content(jsonStr.toString())
                .url(url);
    }

    /**
     * 获取图形码
     *
     */
    public static void getGraphic(Context context,String phone, GXCallback<GraphicBean> callback) {
        String url = ParamsConfigs.EXPLOIT_USE+"user/captcha";
        Map<String,String> map=new HashMap<>();
        map.put("phone",phone);
        JSONObject jsonObject=new JSONObject(map);
        AddHeader(context,jsonObject,url).build().execute(callback);
    }

    /**
     * 获取验证码
     *
     */
    public static void getCode(Context context,String phone, GXCallback<String> callback) {
        String url = ParamsConfigs.EXPLOIT_USE+"user/sendRegSmsCode";
        Map<String,String> map=new HashMap<>();
        map.put("phone",phone);
        map.put("type","1");
        JSONObject jsonObject=new JSONObject(map);
        AddHeader(context,jsonObject,url)
                .build()
                .execute(callback);
    }

    /**
     * 注册&登录
     *
     */
    public static void getLogin(Context context,String code,String inviteCode,String phone, GXCallback<UserInfo> callback) {
        String url = ParamsConfigs.EXPLOIT_USE+"user/register";
        Map<String,String> map=new HashMap<>();
        map.put("code",code);
        map.put("inviteCode",inviteCode);
        map.put("phone",phone);
        JSONObject jsonObject=new JSONObject(map);
        AddHeader(context,jsonObject,url)
                .build()
                .execute(callback);
    }

    /**
     * 获取轮播图positionType	：banner图位置 1首页 2藏馆 3品牌馆
     *
     */
    public static void getBanner(Context context,String positionType, GXCallback<BannerBean> callback) {
        String url = ParamsConfigs.EXPLOIT_USE+"index/getBannerList";
        Map<String,String> map=new HashMap<>();
        map.put("positionType",positionType);
        JSONObject jsonObject=new JSONObject(map);
        AddHeader(context,jsonObject,url)
                .build()
                .execute(callback);
    }

    /**
     * 获取资讯
     *
     */
    public static void getInformation(Context context,int pageNo, GXCallback<InformationBean> callback) {
        String url = ParamsConfigs.EXPLOIT_USE+"user/getNewsInfo";
        Map<String,String> map=new HashMap<>();
        map.put("pageNo",pageNo+"");
        map.put("pageSize","10");
        JSONObject jsonObject=new JSONObject(map);
        AddHeader(context,jsonObject,url)
                .build()
                .execute(callback);
    }

    /**
     * 上传图片
     *
     */
    public static void getUpData(File file,String type, GXCallback<UpDataBean> callback) {
        String url = ParamsConfigs.EXPLOIT_USE+"user/upload/file";
        OkHttpUtils.post()
                .addParams("extName",file.getName())
                .addParams("type",type)
                .addFile("file",file.getName(),file)
                .url(url)
                .build()
                .execute(callback);
    }

    /**
     * 修改个人信息
     *
     */
    public static void getChangeMessage(Context context,String headImg,String age,String introduce,String nick,String sex, GXCallback<String> callback) {
        String url = ParamsConfigs.EXPLOIT_USE+"user/updateUserInfo";
        Map<String,String> map=new HashMap<>();
        map.put("headImg",headImg);
        map.put("age",age);
        map.put("introduce",introduce);
        map.put("nick",nick);
        map.put("sex",sex);
        JSONObject jsonObject=new JSONObject(map);
        AddHeader(context,jsonObject,url)
                .build()
                .execute(callback);
    }

    /**
     * 获取个人信息
     *
     */
    public static void getMessage(Context context, GXCallback<UserInfo> callback) {
        String url = ParamsConfigs.EXPLOIT_USE+"user/getUserInfo";
        Map<String,String> map=new HashMap<>();
        JSONObject jsonObject=new JSONObject(map);
        AddHeader(context,jsonObject,url)
                .build()
                .execute(callback);
    }

    /**
     * 获取预售列表
     *
     */
    public static void getPresell(Context context,int pageNo, GXCallback<PresellBean> callback) {
        String url = ParamsConfigs.EXPLOIT_USE+"goods/getPresaleList";
        Map<String,String> map=new HashMap<>();
        map.put("pageNo",pageNo+"");
        map.put("pageSize","10");
        JSONObject jsonObject=new JSONObject(map);
        AddHeader(context,jsonObject,url)
                .build()
                .execute(callback);
    }
}
