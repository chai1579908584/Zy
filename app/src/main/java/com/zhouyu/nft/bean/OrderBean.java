package com.zhouyu.nft.bean;

import java.io.Serializable;

public class OrderBean implements Serializable {


    /**
     * orderId : 3001
     * type:"2"
     */

    private String orderId;
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
