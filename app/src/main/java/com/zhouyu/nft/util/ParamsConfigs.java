package com.zhouyu.nft.util;


import com.zhouyu.nft.MyApplication;

/**
 * description:变量
 * 2022/6/7.
 */
public class ParamsConfigs {

    //请求数据code 1000=成功
    public static final int DATA_SUCCESS=1000;

    //开发环境
    public static final String EXPLOIT="https://zyapi-dev.zhouyunft.com";
    //测试环境
    public static final String TEXT="https://zyapi-test.zhouyunft.com";
    //线上环境
    public static final String ONLINE="https://zyapi.zhouyunft.com";
    //用户服务串
    public static  String SERVE="/user/gateway/";
    //藏品服务串
    public static  String GOOD="/goods/gateway/";
    //订单服务串
    public static  String ORDER="/order/gateway/";

    //测试开发环境
   // public static String ENV=getEnv();

    //正式环境
    public static String ENV=TEXT;

    //设置登录页是否可以切换切换环境，为false时不能切换环境用于上线***
    public final static boolean LOGIN_DEBUG = false;

    public static String getEnv(){
        return SpUtil.readEnv(MyApplication.context,EXPLOIT);
    }

    //使用环境用户服务串
    public static  String EXPLOIT_USER=ENV+SERVE;
    //使用环境藏品服务串
    public static  String EXPLOIT_GOOD=ENV+GOOD;
    //使用环境藏品服务串
    public static  String EXPLOIT_ORDER=ENV+ORDER;

    //新手指南
    public static final String FINGER_POST="https://zy-static.zhouyunft.com/h5/guide.html";
    //用户服务协议
    public static final String SERVICE_XY="https://zy-static.zhouyunft.com/h5/service.html";
    //用户隐私协议
    public static final String PRIVACY_XY="https://zy-static.zhouyunft.com/h5/privacy.html";

    //4位的整数
    //1001：宙域 iOS app//1002：宙域 Android app//2001：宙域运营管理平台//3001：宙域公众号//4001：宙域小程序//5001：宙域门户网站
    public static final String REQUEST_APP_ID = "1002";

    //签名加密key
    public static final String HMAC_SHA1_KEY = "06W2bq4OJtNRrIJX";

    //是否需要实名认证表示:包含代表不需要实名
    public static final String IS_REAL_NAME = "nftcer=0";

    //这个是开发详情页里面的3d链接：
    public static final String THREE_D="https://app-dev.zhouyunft.com/#/3d";

    //这个是测试详情页里面的3d链接：
    public static final String THREE_T="https://app-test.zhouyunft.com/#/3d";

    //开发欣赏全屏的3d地址：
    public static final String THREE_Q="https://app-dev.zhouyunft.com/#/3dfull";
}
