package com.zhouyu.nft.api;

import android.content.Context;
import android.util.Log;

import com.zhouyu.nft.bean.BankCardBean;
import com.zhouyu.nft.bean.BankCodeBean;
import com.zhouyu.nft.bean.BannerBean;
import com.zhouyu.nft.bean.BrandBean;
import com.zhouyu.nft.bean.BrandDetailBean;
import com.zhouyu.nft.bean.CalenderBean;
import com.zhouyu.nft.bean.GraphicBean;
import com.zhouyu.nft.bean.HomeBean;
import com.zhouyu.nft.bean.InRegardToBean;
import com.zhouyu.nft.bean.InformationBean;
import com.zhouyu.nft.bean.IntonedDetailBean;
import com.zhouyu.nft.bean.OrderBean;
import com.zhouyu.nft.bean.OrderDetailBean;
import com.zhouyu.nft.bean.PresellBean;
import com.zhouyu.nft.bean.RealNameBean;
import com.zhouyu.nft.bean.RealNameMessageBean;
import com.zhouyu.nft.bean.SeriesListBean;
import com.zhouyu.nft.bean.UnpaidBean;
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
import java.util.List;
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
                .addHeader("cid", "200000")
                .addHeader("ts", "" + System.currentTimeMillis())
                .addHeader("token", token)
                .addHeader("nonce", nonce)
                .addHeader("signature", HmacShaUtil.getHmacSha1(strSign, ParamsConfigs.HMAC_SHA1_KEY))
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .content(jsonStr.toString())
                .url(url);
    }

    /**
     * ???????????????
     *
     */
    public static void getGraphic(Context context,String phone, GXCallback<GraphicBean> callback) {
        String url = ParamsConfigs.EXPLOIT_USER+"/user/captcha";
        Map<String,String> map=new HashMap<>();
        map.put("phone",phone);
        JSONObject jsonObject=new JSONObject(map);
        AddHeader(context,jsonObject,url).build().execute(callback);
    }

    /**
     * ???????????????
     *
     */
    public static void getCode(Context context,String phone, GXCallback<String> callback) {
        String url = ParamsConfigs.EXPLOIT_USER+"/user/sendRegSmsCode";
        Map<String,String> map=new HashMap<>();
        map.put("phone",phone);
        map.put("type","1");
        JSONObject jsonObject=new JSONObject(map);
        AddHeader(context,jsonObject,url)
                .build()
                .execute(callback);
    }

    /**
     * ???????????????(??????token??????????????????)
     *
     */
    public static void getLogoutCode(Context context,String phone, GXCallback<String> callback) {
        String url = ParamsConfigs.EXPLOIT_USER+"/user/sendSmsCode";
        Map<String,String> map=new HashMap<>();
        map.put("phone",phone);
        map.put("type","1");
        JSONObject jsonObject=new JSONObject(map);
        AddHeader(context,jsonObject,url)
                .build()
                .execute(callback);
    }

    /**
     * ??????
     *
     */
    public static void getLogout(Context context ,String code, GXCallback<String> callback) {
        String url = ParamsConfigs.EXPLOIT_USER+"/user/destroyAccount";
        Map<String,String> map=new HashMap<>();
        map.put("code",code);
        JSONObject jsonObject=new JSONObject(map);
        AddHeader(context,jsonObject,url)
                .build()
                .execute(callback);
    }

    /**
     * ??????
     *
     */
    public static void getLogin(Context context,String code,String inviteCode,String phone, GXCallback<UserInfo> callback) {
        String url = ParamsConfigs.EXPLOIT_USER+"/user/register";
        Log.e("sssssssssssssssss",url);
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
     * ??????????????????
     *
     */
    public static void getSetPwd(Context context ,String code,String pwd,String confirmPwd, GXCallback<String> callback) {
        String url = ParamsConfigs.EXPLOIT_USER+"/user/updateUserPwd";
        Map<String,String> map=new HashMap<>();
        map.put("code",code);
        map.put("pwd",pwd);
        map.put("confirmPwd",confirmPwd);
        JSONObject jsonObject=new JSONObject(map);
        AddHeader(context,jsonObject,url)
                .build()
                .execute(callback);
    }

    /**
     * ???????????????positionType	???banner????????? 1?????? 2?????? 3?????????
     *
     */
    public static void getBanner(Context context, String positionType, GXCallback<List<BannerBean>> callback) {
        String url = ParamsConfigs.EXPLOIT_USER+"/index/getBannerList";
        Map<String,String> map=new HashMap<>();
        map.put("positionType",positionType);
        JSONObject jsonObject=new JSONObject(map);
        AddHeader(context,jsonObject,url)
                .build()
                .execute(callback);
    }

    /**
     * ????????????
     *
     */
    public static void getInformation(Context context,int pageNo, GXCallback<InformationBean> callback) {
        String url = ParamsConfigs.EXPLOIT_USER+"/user/getNewsInfo";
        Map<String,String> map=new HashMap<>();
        map.put("pageNo",pageNo+"");
        map.put("pageSize","10");
        JSONObject jsonObject=new JSONObject(map);
        AddHeader(context,jsonObject,url)
                .build()
                .execute(callback);
    }

    /**
     * ????????????
     *
     */
    public static void getInRegardTo(Context context, GXCallback<InRegardToBean> callback) {
        String url = ParamsConfigs.EXPLOIT_USER+"/user/getContact";
        Map<String,String> map=new HashMap<>();
        JSONObject jsonObject=new JSONObject(map);
        AddHeader(context,jsonObject,url)
                .build()
                .execute(callback);
    }

    /**
     * ????????????
     *
     */
    public static void getUpData(File file,String type, GXCallback<UpDataBean> callback) {
        String url = ParamsConfigs.EXPLOIT_USER+"/user/upload/file";
        OkHttpUtils.post()
                .addParams("extName",file.getName())
                .addParams("type",type)
                .addFile("file",file.getName(),file)
                .url(url)
                .build()
                .execute(callback);
    }

    /**
     * ??????????????????
     *
     */
    public static void getChangeMessage(Context context,String headImg,String age,String introduce,String nick,String sex, GXCallback<String> callback) {
        String url = ParamsConfigs.EXPLOIT_USER+"/user/updateUserInfo";
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
     * ??????????????????
     *
     */
    public static void getMessage(Context context, GXCallback<UserInfo> callback) {
        String url = ParamsConfigs.EXPLOIT_USER+"/user/getUserInfo";
        Map<String,String> map=new HashMap<>();
        JSONObject jsonObject=new JSONObject(map);
        AddHeader(context,jsonObject,url)
                .build()
                .execute(callback);
    }

    /**
     * ????????????
     *
     */
    public static void getLogout(Context context, GXCallback<String> callback) {
        String url = ParamsConfigs.EXPLOIT_USER+"/user/logout";
        Map<String,String> map=new HashMap<>();
        JSONObject jsonObject=new JSONObject(map);
        AddHeader(context,jsonObject,url)
                .build()
                .execute(callback);
    }

    /**
     * ????????????
     *
     */
    public static void getHelp(Context context,String content,String imgUrls,String type, GXCallback<String> callback) {
        String url = ParamsConfigs.EXPLOIT_USER+"/user/help/advise";
        Map<String,String> map=new HashMap<>();
        map.put("content",content);
        map.put("imgUrls",imgUrls);
        map.put("type",type);
        JSONObject jsonObject=new JSONObject(map);
        AddHeader(context,jsonObject,url)
                .build()
                .execute(callback);
    }

    /**
     * ??????????????????certifyId
     *
     */
    public static void getCertifyId(Context context,String name,String idCard,String metaInfo, GXCallback<RealNameBean> callback) {
        String url = ParamsConfigs.EXPLOIT_USER+"/realName/realName";
        Map<String,String> map=new HashMap<>();
        map.put("name",name);
        map.put("idCard",idCard);
        map.put("metaInfo",metaInfo);
        JSONObject jsonObject=new JSONObject(map);
        AddHeader(context,jsonObject,url)
                .build()
                .execute(callback);
    }

    /**
     * ??????????????????????????????
     *
     */
    public static void getRealNameMessage(Context context, GXCallback<RealNameMessageBean> callback) {
        String url = ParamsConfigs.EXPLOIT_USER+"/realName/getCertified";
        Map<String,String> map=new HashMap<>();
        JSONObject jsonObject=new JSONObject(map);
        AddHeader(context,jsonObject,url)
                .build()
                .execute(callback);
    }

    /**
     * ??????????????????
     *
     */
    public static void getRealNameMessage(Context context,String certifyId, GXCallback<String> callback) {
        String url = ParamsConfigs.EXPLOIT_USER+"/realName/result";
        Map<String,String> map=new HashMap<>();
        map.put("certifyId",certifyId);
        JSONObject jsonObject=new JSONObject(map);
        AddHeader(context,jsonObject,url)
                .build()
                .execute(callback);
    }

    /**
     * ?????????????????????
     *
     */
    public static void getBankCard(Context context, GXCallback<List<BankCardBean>> callback) {
        String url = ParamsConfigs.EXPLOIT_USER+"/wallet/getBankCardList";
        Map<String,String> map=new HashMap<>();
        JSONObject jsonObject=new JSONObject(map);
        AddHeader(context,jsonObject,url)
                .build()
                .execute(callback);
    }

    /**
     * ?????????????????????&code???
     *
     */
    public static void getBankCode(Context context, GXCallback<List<BankCodeBean>> callback) {
        String url = ParamsConfigs.EXPLOIT_USER+"/wallet/getBankCode";
        Map<String,String> map=new HashMap<>();
        JSONObject jsonObject=new JSONObject(map);
        AddHeader(context,jsonObject,url)
                .build()
                .execute(callback);
    }

    /**
     * ??????????????????????????????
     *
     */
    public static void getCodeForBindBankCard(Context context,String bankCode,String cardNum,String cardType,String phone, GXCallback<String> callback) {
        String url = ParamsConfigs.EXPLOIT_USER+"/wallet/getCodeForBindBankCard";
        Map<String,String> map=new HashMap<>();
        map.put("bankCode",bankCode);
        map.put("cardNum",cardNum);
        map.put("phone",phone);
        map.put("cardType",cardType);
        JSONObject jsonObject=new JSONObject(map);
        AddHeader(context,jsonObject,url)
                .build()
                .execute(callback);
    }

    /**
     * ??????????????????????????????&???????????????
     *
     */
    public static void getBindBankCard(Context context,String bankCode,String cardNum,String cardType,String phone,String uuid,String code, GXCallback<String> callback) {
        String url = ParamsConfigs.EXPLOIT_USER+"/wallet/bindBankCard";
        Map<String,String> map=new HashMap<>();
        map.put("bankCode",bankCode);
        map.put("cardNum",cardNum);
        map.put("phone",phone);
        map.put("cardType",cardType);
        map.put("uuid",uuid);
        map.put("code",code);
        JSONObject jsonObject=new JSONObject(map);
        AddHeader(context,jsonObject,url)
                .build()
                .execute(callback);
    }

    /**
     * ???????????????
     *
     */
    public static void getDeleteBankCard(Context context,String bankId, GXCallback<String> callback) {
        String url = ParamsConfigs.EXPLOIT_USER+"/wallet/deleteBankCardId";
        Map<String,String> map=new HashMap<>();
        map.put("bankId",bankId);
        JSONObject jsonObject=new JSONObject(map);
        AddHeader(context,jsonObject,url)
                .build()
                .execute(callback);
    }

    /**
     * ?????????????????????
     *
     */
    public static void getDefaultBankCard(Context context,String bankId, GXCallback<String> callback) {
        String url = ParamsConfigs.EXPLOIT_USER+"/wallet/setDefaultBankCard";
        Map<String,String> map=new HashMap<>();
        map.put("bankId",bankId);
        JSONObject jsonObject=new JSONObject(map);
        AddHeader(context,jsonObject,url)
                .build()
                .execute(callback);
    }

    /**
     * ??????????????????
     *
     */
    public static void getPresell(Context context,int pageNo, GXCallback<PresellBean> callback) {
        String url = ParamsConfigs.EXPLOIT_GOOD+"/goods/getPresaleList";
        Map<String,String> map=new HashMap<>();
        map.put("pageNo",pageNo+"");
        map.put("pageSize","10");
        JSONObject jsonObject=new JSONObject(map);
        AddHeader(context,jsonObject,url)
                .build()
                .execute(callback);
    }

    /**
     * ??????????????????
     *
     */
    public static void getSale(Context context,int pageNo, GXCallback<PresellBean> callback) {
        String url = ParamsConfigs.EXPLOIT_GOOD+"/goods/getsaleList";
        Map<String,String> map=new HashMap<>();
        map.put("pageNo",pageNo+"");
        map.put("pageSize","10");
        JSONObject jsonObject=new JSONObject(map);
        AddHeader(context,jsonObject,url)
                .build()
                .execute(callback);
    }

    /**
     * ??????????????????????????????
     *
     */
    public static void getPrevious(Context context,int pageNo, GXCallback<PresellBean> callback) {
        String url = ParamsConfigs.EXPLOIT_GOOD+"/goods/getExpsaleList";
        Map<String,String> map=new HashMap<>();
        map.put("pageNo",pageNo+"");
        map.put("pageSize","10");
        JSONObject jsonObject=new JSONObject(map);
        AddHeader(context,jsonObject,url)
                .build()
                .execute(callback);
    }

    /**
     * ??????????????????
     *
     */
    public static void getMyProductList(Context context,int pageNo, GXCallback<PresellBean> callback) {
        String url = ParamsConfigs.EXPLOIT_GOOD+"/goods/getMyProductList";
        Map<String,String> map=new HashMap<>();
        map.put("pageNo",pageNo+"");
        map.put("pageSize","10");
        JSONObject jsonObject=new JSONObject(map);
        AddHeader(context,jsonObject,url)
                .build()
                .execute(callback);
    }

    /**
     * ???????????????????????????????????????&????????????
     *
     */
    public static void getIntonedClassify(Context context,int pageNo,String productType, GXCallback<PresellBean> callback) {
        String url = ParamsConfigs.EXPLOIT_GOOD+"/goods/getProductList";
        Map<String,String> map=new HashMap<>();
        map.put("pageNo",pageNo+"");
        map.put("pageSize","10");
        map.put("productType",productType);
        JSONObject jsonObject=new JSONObject(map);
        AddHeader(context,jsonObject,url)
                .build()
                .execute(callback);
    }

    /**
     * ????????????????????????????????????
     *
     */
    public static void getIntonedList(Context context,int pageNo, GXCallback<PresellBean> callback) {
        String url = ParamsConfigs.EXPLOIT_GOOD+"/goods/getProductList";
        Map<String,String> map=new HashMap<>();
        map.put("pageNo",pageNo+"");
        map.put("pageSize","10");
        JSONObject jsonObject=new JSONObject(map);
        AddHeader(context,jsonObject,url)
                .build()
                .execute(callback);
    }

    /**
     * ?????????????????????????????????
     *
     */
    public static void getShai(Context context,int pageNo,String brandId,String productType,String seriesId, GXCallback<PresellBean> callback) {
        String url = ParamsConfigs.EXPLOIT_GOOD+"/goods/getProductList";
        Map<String,String> map=new HashMap<>();
        map.put("pageNo",pageNo+"");
        map.put("pageSize","10");
        map.put("brandId",brandId);
        map.put("productType",productType);
        map.put("seriesId",seriesId);
        JSONObject jsonObject=new JSONObject(map);
        AddHeader(context,jsonObject,url)
                .build()
                .execute(callback);
    }

    /**
     * ????????????
     *
     */
    public static void getSeriesListByBid(Context context, GXCallback<List<SeriesListBean>> callback) {
        String url = ParamsConfigs.EXPLOIT_GOOD+"/brand/getSeriesListByBid";
        Map<String,String> map=new HashMap<>();
        JSONObject jsonObject=new JSONObject(map);
        AddHeader(context,jsonObject,url)
                .build()
                .execute(callback);
    }

    /**
     * ??????????????????????????????
     *
     */
    public static void getAllListByBrand(Context context,int pageNo, String brandId,GXCallback<PresellBean> callback) {
        String url = ParamsConfigs.EXPLOIT_GOOD+"/goods/getAllListByBrand";
        Map<String,String> map=new HashMap<>();
        map.put("pageNo",pageNo+"");
        map.put("pageSize","10");
        map.put("brandId",brandId);
        JSONObject jsonObject=new JSONObject(map);
        AddHeader(context,jsonObject,url)
                .build()
                .execute(callback);
    }

    /**
     * ??????????????????
     */
    public static void getSearchIntoned(Context context,String productName,int pageNo, GXCallback<PresellBean> callback) {
        String url = ParamsConfigs.EXPLOIT_GOOD+"/goods/getProductList";
        Map<String,String> map=new HashMap<>();
        map.put("pageNo",pageNo+"");
        map.put("pageSize","10");
        map.put("productName",productName);
        JSONObject jsonObject=new JSONObject(map);
        AddHeader(context,jsonObject,url)
                .build()
                .execute(callback);
    }

    /**
     * ??????????????????
     *
     */
    public static void getHomeMessage(Context context, GXCallback<HomeBean> callback) {
        String url = ParamsConfigs.EXPLOIT_GOOD+"/goods/index/recommend";
        Map<String,String> map=new HashMap<>();
        JSONObject jsonObject=new JSONObject(map);
        AddHeader(context,jsonObject,url)
                .build()
                .execute(callback);
    }

    /**
     * ??????????????????
     *
     */
    public static void getIntonedDetail(Context context,String gid, GXCallback<IntonedDetailBean> callback) {
        String url = ParamsConfigs.EXPLOIT_GOOD+"/goods/getDetail";
        Map<String,String> map=new HashMap<>();
        map.put("gid",gid);
        JSONObject jsonObject=new JSONObject(map);
        AddHeader(context,jsonObject,url)
                .build()
                .execute(callback);
    }

    /**
     * ??????????????????
     *
     */
    public static void getBrand(Context context,int pageNo ,GXCallback<BrandBean> callback) {
        String url = ParamsConfigs.EXPLOIT_GOOD+"/brand/getBrandList";
        Map<String,String> map=new HashMap<>();
        map.put("pageNo",pageNo+"");
        map.put("pageSize","10");
        JSONObject jsonObject=new JSONObject(map);
        AddHeader(context,jsonObject,url)
                .build()
                .execute(callback);
    }

    /**
     * ????????????
     *
     */
    public static void getSearchBrand(Context context,int page ,String name,GXCallback<BrandBean> callback) {
        String url = ParamsConfigs.EXPLOIT_GOOD+"/brand/getBrandList";
        Map<String,String> map=new HashMap<>();
        map.put("pageNo",page+"");
        map.put("pageSize","10");
        map.put("name",name);
        JSONObject jsonObject=new JSONObject(map);
        AddHeader(context,jsonObject,url)
                .build()
                .execute(callback);
    }

    /**
     * ??????????????????
     *
     */
    public static void getBrandDetail(Context context,String brandId, GXCallback<BrandDetailBean> callback) {
        String url = ParamsConfigs.EXPLOIT_GOOD+"/brand/getBrandDetail";
        Map<String,String> map=new HashMap<>();
        map.put("brandId",brandId);
        JSONObject jsonObject=new JSONObject(map);
        AddHeader(context,jsonObject,url)
                .build()
                .execute(callback);
    }

    /**
     * ??????????????????
     *
     */
    public static void getCalender(Context context,int pageNo,GXCallback<CalenderBean> callback) {
        String url = ParamsConfigs.EXPLOIT_GOOD+"/goods/getSaleCalendar";
        Map<String,String> map=new HashMap<>();
        map.put("pageNo",pageNo+"");
        map.put("pageSize","10");
        JSONObject jsonObject=new JSONObject(map);
        AddHeader(context,jsonObject,url)
                .build()
                .execute(callback);
    }

    /**
     * ????????????
     *
     */
    public static void getOrder(Context context,String productId, GXCallback<OrderBean> callback) {
        String url = ParamsConfigs.EXPLOIT_ORDER+"/order/createOrder";
        Map<String,String> map=new HashMap<>();
        map.put("productId",productId);
        JSONObject jsonObject=new JSONObject(map);
        AddHeader(context,jsonObject,url)
                .build()
                .execute(callback);
    }

    /**
     * ??????????????????
     *
     */
    public static void getOrderDetail(Context context,String orderId, GXCallback<OrderDetailBean> callback) {
        String url = ParamsConfigs.EXPLOIT_ORDER+"/order/getMyOrderDetail";
        Map<String,String> map=new HashMap<>();
        map.put("orderId",orderId);
        JSONObject jsonObject=new JSONObject(map);
        AddHeader(context,jsonObject,url)
                .build()
                .execute(callback);
    }

    /**
     * ??????????????????
     *
     */
    public static void getMyOrder(Context context,int pageNo,String status, GXCallback<UnpaidBean> callback) {
        String url = ParamsConfigs.EXPLOIT_ORDER+"/order/getMyOrder";
        Map<String,String> map=new HashMap<>();
        map.put("status",status);
        map.put("pageNo",pageNo+"");
        map.put("pageSize","10");
        JSONObject jsonObject=new JSONObject(map);
        AddHeader(context,jsonObject,url)
                .build()
                .execute(callback);
    }


    /**
     * ??????????????????
     *
     */
    public static void getPay(Context context,String bankId,String orderId, String password,String type,GXCallback<String> callback) {
        String url = ParamsConfigs.EXPLOIT_ORDER+"/order/pay/pay";
        Map<String,String> map=new HashMap<>();
        map.put("bankId",bankId);
        map.put("orderId",orderId);
        map.put("password",password);
        map.put("type",type);
        JSONObject jsonObject=new JSONObject(map);
        AddHeader(context,jsonObject,url)
                .build()
                .execute(callback);
    }

}
