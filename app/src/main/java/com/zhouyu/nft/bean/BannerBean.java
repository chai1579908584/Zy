package com.zhouyu.nft.bean;


import java.io.Serializable;

public class BannerBean implements Serializable {


    /**
     * bannerUrl :
     * introduce :
     * linkUrl :
     * title :
     */

    private String bannerUrl;
    private String introduce;
    private String linkUrl;
    private String title;

    public String getBannerUrl() {
        return bannerUrl;
    }

    public void setBannerUrl(String bannerUrl) {
        this.bannerUrl = bannerUrl;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
