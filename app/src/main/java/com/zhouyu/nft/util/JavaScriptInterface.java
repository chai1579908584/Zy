package com.zhouyu.nft.util;

import android.content.Context;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;

import com.google.gson.Gson;
import com.zhouyu.nft.MyApplication;
import com.zhouyu.nft.activity.HFiveActivity;
import com.zhouyu.nft.bean.H5GetComParamBean;
import com.zhouyu.nft.bean.UserInfo;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class JavaScriptInterface {

    private CallbackListener listener ;
    public interface CallbackListener{
        void h5Callback(String methodName,String params);
    }

    Context context;
    public JavaScriptInterface(Context context,CallbackListener listener) {
        this.listener = listener;
        this.context=context;
    }
    /**
     * 获取公共参数
     * @param str js端的回调方法 （参数就是 公共参数的 Json 串）
     */
    @JavascriptInterface
    public void getComParam(String str){
        if(!((HFiveActivity)context).isOurUrl()){
            return;
        }
        H5GetComParamBean bean = new Gson().fromJson(str, H5GetComParamBean.class);
        if(null == bean){
            return;
        }
        Map publicParamsMap = createHeaderMap();
        String params = new Gson().toJson(publicParamsMap);
        if(null != listener){
            listener.h5Callback(bean.getCallback(),params);
        }
    }

    /**
     * 公参传入头中
     * @return
     */
    private Map<String,String> createHeaderMap(){
        String token="";
        UserInfo userInfo= SpUtil.readData(context);
        if (userInfo.getToken()!=null)
        {
            token = userInfo.getToken();
        }
        Map<String,String> headerMap = new HashMap<>();
        headerMap.put("appid", ParamsConfigs.REQUEST_APP_ID);
        headerMap.put("appver", Objects.requireNonNull(AppContextUtils.getPackageInfo(context)).versionName
                + "." + Objects.requireNonNull(AppContextUtils.getPackageInfo(context)).versionCode);
        headerMap.put("deviceid",AppContextUtils.getDeviceId(context));
        headerMap.put("cid","100000");
        headerMap.put("ts","" + System.currentTimeMillis());
        headerMap.put("uid", HmacShaUtil.getHmacSha1("", ParamsConfigs.HMAC_SHA1_KEY));
        headerMap.put("token",token);
        return headerMap;
    }


}
