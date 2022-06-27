package com.zhouyu.nft.bean;

import java.io.Serializable;

public class InRegardToBean implements Serializable {


    /**
     * contactEmail :
     * qrcodeUrl :
     */

    private String contactEmail;
    private String qrcodeUrl;

    public String getQrcodeUrl() {
        return qrcodeUrl;
    }

    public void setQrcodeUrl(String qrcodeUrl) {
        this.qrcodeUrl = qrcodeUrl;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }
}
