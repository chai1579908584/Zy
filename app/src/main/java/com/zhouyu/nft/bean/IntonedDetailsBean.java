package com.zhouyu.nft.bean;

import java.io.Serializable;
import java.util.List;

public class IntonedDetailsBean implements Serializable {


    /**
     * brandName :
     * brandlogo :
     * gid : 0
     * imgUrl :
     * productDetailImg : []
     * productName :
     * productType :
     * pubTime :
     * rightsDesc :
     * saleNum :
     * salePrice :
     * seriesName :
     * subImgUrl :
     * type :
     */

    private String brandName;
    private String brandlogo;
    private int gid;
    private String imgUrl;
    private String productName;
    private String productType;
    private String pubTime;
    private String rightsDesc;
    private String saleNum;
    private String salePrice;
    private String seriesName;
    private String subImgUrl;
    private String type;
    private List<?> productDetailImg;

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getBrandlogo() {
        return brandlogo;
    }

    public void setBrandlogo(String brandlogo) {
        this.brandlogo = brandlogo;
    }

    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getPubTime() {
        return pubTime;
    }

    public void setPubTime(String pubTime) {
        this.pubTime = pubTime;
    }

    public String getRightsDesc() {
        return rightsDesc;
    }

    public void setRightsDesc(String rightsDesc) {
        this.rightsDesc = rightsDesc;
    }

    public String getSaleNum() {
        return saleNum;
    }

    public void setSaleNum(String saleNum) {
        this.saleNum = saleNum;
    }

    public String getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(String salePrice) {
        this.salePrice = salePrice;
    }

    public String getSeriesName() {
        return seriesName;
    }

    public void setSeriesName(String seriesName) {
        this.seriesName = seriesName;
    }

    public String getSubImgUrl() {
        return subImgUrl;
    }

    public void setSubImgUrl(String subImgUrl) {
        this.subImgUrl = subImgUrl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<?> getProductDetailImg() {
        return productDetailImg;
    }

    public void setProductDetailImg(List<?> productDetailImg) {
        this.productDetailImg = productDetailImg;
    }
}
