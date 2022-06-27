package com.zhouyu.nft.bean;

import java.io.Serializable;
import java.util.List;

public class HomeBean implements Serializable {


    private List<BrandListBean> brandList;
    private List<PresellBean.RecordsBean> goodsList;

    public List<BrandListBean> getBrandList() {
        return brandList;
    }

    public void setBrandList(List<BrandListBean> brandList) {
        this.brandList = brandList;
    }

    public List<PresellBean.RecordsBean> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<PresellBean.RecordsBean> goodsList) {
        this.goodsList = goodsList;
    }

    public static class BrandListBean implements Serializable {
        /**
         * showImg :
         * bid : 0
         */

        private String showImg;
        private String bid;

        public String getShowImg() {
            return showImg;
        }

        public void setShowImg(String showImg) {
            this.showImg = showImg;
        }

        public String getBid() {
            return bid;
        }

        public void setBid(String bid) {
            this.bid = bid;
        }
    }
}
