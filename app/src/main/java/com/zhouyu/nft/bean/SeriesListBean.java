package com.zhouyu.nft.bean;

import java.io.Serializable;

public class SeriesListBean implements Serializable {


    /**
     * seriesName : 穆桂英人物系列穆桂英
     * bid : 18001
     * backgroundUrl : https://zy-static-dev.zhouyunft.com/systemImg/3443d4f3-3b6c-48b4-a57e-18cf8fc196bb.jpg
     * introduce : 这是系列的简介，这是系列的简介，这是系列的简介，这是系列的简介，这是系列的简介，这是系列的简介，这是
     * type : 1
     * sid : 14001
     */

    private String seriesName;
    private String bid;
    private String backgroundUrl;
    private String introduce;
    private String type;
    private String sid;

    public String getSeriesName() {
        return seriesName;
    }

    public void setSeriesName(String seriesName) {
        this.seriesName = seriesName;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getBackgroundUrl() {
        return backgroundUrl;
    }

    public void setBackgroundUrl(String backgroundUrl) {
        this.backgroundUrl = backgroundUrl;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }
}
