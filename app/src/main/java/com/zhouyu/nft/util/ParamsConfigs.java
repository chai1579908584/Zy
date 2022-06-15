package com.zhouyu.nft.util;


/**
 * description:变量
 * 2022/6/7.
 */
public class ParamsConfigs {

    //请求数据code 1000=成功
    public static final String DATA_SUCCESS="1000";

    //开发环境
    public static final String EXPLOIT="https://zyapi.dev.zhouyunft.com/";
    //测试环境
    public static final String TEXT="https://zyapi.test.zhouyunft.com/";
    //线上环境
    public static final String ONLINE="https://zyapi.zhouyunft.com/";
    //服务串
    public static final String SERVE="user/gateway/";
    //使用环境
    public static final String EXPLOIT_USE=EXPLOIT+SERVE;

    //4位的整数
    //1001：宙域 iOS app//1002：宙域 Android app//2001：宙域运营管理平台//3001：宙域公众号//4001：宙域小程序//5001：宙域门户网站
    public static final String REQUEST_APP_ID = "1002";

    public static final String HMAC_SHA1_KEY = "06W2bq4OJtNRrIJX";//签名加密key


}
