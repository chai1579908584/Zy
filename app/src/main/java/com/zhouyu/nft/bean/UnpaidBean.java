package com.zhouyu.nft.bean;

import java.io.Serializable;
import java.util.List;

public class UnpaidBean implements Serializable {


    /**
     * records : [{"orderId":3005,"uid":31003,"productName":"武松","payType":null,"orderNo":"YZ202206233005","orderPrice":1100,"productPrice":1100,"productImg":null,"productNum":"1","fee":0,"productId":10001,"status":1,"cancelTime":"2022-06-23 16:17:29","overTime":-82824},{"orderId":3006,"uid":31003,"productName":"武松","payType":null,"orderNo":"YZ202206233006","orderPrice":5000,"productPrice":5000,"productImg":null,"productNum":"1","fee":0,"productId":10001,"status":1,"cancelTime":"2022-06-23 16:23:57","overTime":-82436},{"orderId":3007,"uid":31003,"productName":"银河007","payType":null,"orderNo":"YZ202206233007","orderPrice":12200,"productPrice":12200,"productImg":null,"productNum":"1","fee":0,"productId":18001,"status":1,"cancelTime":"2022-06-23 17:11:10","overTime":-79603},{"orderId":3008,"uid":31003,"productName":"银河007","payType":null,"orderNo":"YZ202206233008","orderPrice":12200,"productPrice":12200,"productImg":null,"productNum":"1","fee":0,"productId":18001,"status":1,"cancelTime":"2022-06-23 19:14:03","overTime":-72230},{"orderId":5002,"uid":31003,"productName":"穆桂英藏品","payType":null,"orderNo":"YZ202206245002","orderPrice":999,"productPrice":999,"productImg":"https://zy-static-dev.zhouyunft.comhttps://zy-static-dev.zhouyunft.com/systemImg/44d7b4e8-e259-4860-a810-125fb47b080a.jpg","productNum":"1","fee":0,"productId":19001,"status":1,"cancelTime":"2022-06-24 14:26:41","overTime":-3072}]
     * total : 0
     * pages : 10
     */

    private int total;
    private int pages;
    private List<RecordsBean> records;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public List<RecordsBean> getRecords() {
        return records;
    }

    public void setRecords(List<RecordsBean> records) {
        this.records = records;
    }

    public static class RecordsBean implements Serializable {
        /**
         * orderId : 3005
         * uid : 31003
         * productName : 武松
         * payType : null
         * orderNo : YZ202206233005
         * orderPrice : 1100.0
         * productPrice : 1100.0
         * productImg : null
         * productNum : 1
         * fee : 0.0
         * productId : 10001
         * status : 1
         * cancelTime : 2022-06-23 16:17:29
         * overTime : -82824
         */

        private String orderId;
        private String uid;
        private String productName;
        private String payType;
        private String orderNo;
        private String orderPrice;
        private String productPrice;
        private String productImg;
        private String productNum;
        private String fee;
        private String productId;
        private String status;
        private String cancelTime;
        private String overTime;

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
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

        public String getProductImg() {
            return productImg;
        }

        public void setProductImg(String productImg) {
            this.productImg = productImg;
        }

        public String getProductNum() {
            return productNum;
        }

        public void setProductNum(String productNum) {
            this.productNum = productNum;
        }

        public String getFee() {
            return fee;
        }

        public void setFee(String fee) {
            this.fee = fee;
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

        public String getOverTime() {
            return overTime;
        }

        public void setOverTime(String overTime) {
            this.overTime = overTime;
        }
    }
}
