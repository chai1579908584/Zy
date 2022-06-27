package com.zhouyu.nft.bean;

import java.io.Serializable;

public class OrderDetailBean implements Serializable {


    /**
     * id : 3001
     * uid : 31003
     * productName : 武松
     * payType : null
     * orderNo : YZ202206233001
     * orderPrice : 100.0
     * productPrice : 100.0
     * productId : 10001
     * status : 1
     * cancelTime : 2022-06-23 15:54:20
     * productImg :
     */

    private String id;
    private String uid;
    private String productName;
    private String payType;
    private String orderNo;
    private String orderPrice;
    private String productPrice;
    private String productId;
    private String status;
    private String cancelTime;
    private String productImg;

    public String getProductImg() {
        return productImg;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(String orderPrice) {
        this.orderPrice = orderPrice;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCancelTime() {
        return cancelTime;
    }

    public void setCancelTime(String cancelTime) {
        this.cancelTime = cancelTime;
    }
}
