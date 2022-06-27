package com.zhouyu.nft.bean;

import java.io.Serializable;

public class RealNameMessageBean implements Serializable {


    /**
     * certifyId :
     * idCard :
     * name :
     */

    private String certifyId;
    private String idCard;
    private String name;
    private String isRealName;

    public String getIsRealName() {
        return isRealName;
    }

    public void setIsRealName(String isRealName) {
        this.isRealName = isRealName;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCertifyId() {
        return certifyId;
    }

    public void setCertifyId(String certifyId) {
        this.certifyId = certifyId;
    }
}
