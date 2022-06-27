package com.zhouyu.nft.bean;

/**
 * description:获取公共参数
 * 2019/5/30.
 * auth:lihe
 */

public class H5GetComParamBean {
    //js端的回调方法 （参数就是 公共参数的 Json 串）
    private String callback;

    public String getCallback() {
        return callback;
    }

    public void setCallback(String callback) {
        this.callback = callback;
    }
}
