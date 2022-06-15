package com.zhouyu.nft.bean;

import java.io.Serializable;

public class UpDataBean implements Serializable {


    /**
     * imgUrl : /headImg/335d32f9-e561-4622-a88d-df020fbf1e86.jpeg
     * path : https://zy-static-dev.zhouyunft.com/headImg/335d32f9-e561-4622-a88d-df020fbf1e86.jpeg
     */

    private String imgUrl;
    private String path;

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
