package com.zhouyu.nft.bean;

import java.io.Serializable;
import java.util.List;

public class BrandDetailBean implements Serializable {


    /**
     * name : 宙域
     * logoUrl : https://zy-static-dev.zhouyunft.com/systemImg/c4723b52-ff52-46ec-98b9-4175ebf0ecd0.png
     * backgroundUrl : https://zy-static-dev.zhouyunft.com/systemImg/f4ecd971-8812-4f56-be33-661fd4fd9113.png
     * seriesNum : 1
     * nftNum : 5
     * nftPublishNum : 1200
     * introduce : 大发大发大发大发大发大发大发大发大发大发
     * showImg :
     * seriesList : [{"seriesName":"水浒系列","backgroundUrl":"https://zy-static-dev.zhouyunft.com/systemImg/6fdda34b-e9d8-4898-8636-d3eb8c355522.png","introduce":"水浒108好汉","sid":10001},{"seriesName":"aa","backgroundUrl":"https://zy-static-dev.zhouyunft.com/systemImg/038f3060-9f5c-4c79-a480-9ed4d25a83ff.png","introduce":"ffff","sid":10004},{"seriesName":"123456789","backgroundUrl":"https://zy-static-dev.zhouyunft.com/systemImg/31742d01-63c9-4bfd-9d50-40d8dd76d6ab.jpg","introduce":"1234567890123456789012345678901234567890123456789","sid":11001},{"seriesName":"123456789","backgroundUrl":"https://zy-static-dev.zhouyunft.com/systemImg/31742d01-63c9-4bfd-9d50-40d8dd76d6ab.jpg","introduce":"1234567890123456789012345678901234567890123456789","sid":21001},{"seriesName":"123456789","backgroundUrl":"https://zy-static-dev.zhouyunft.com/systemImg/31742d01-63c9-4bfd-9d50-40d8dd76d6ab.jpg","introduce":"1234567890123456789012345678901234567890123456789","sid":21002},{"seriesName":"123456789","backgroundUrl":"https://zy-static-dev.zhouyunft.com/systemImg/31742d01-63c9-4bfd-9d50-40d8dd76d6ab.jpg","introduce":"1234567890123456789012345678901234567890123456789","sid":21003},{"seriesName":"123456789","backgroundUrl":"https://zy-static-dev.zhouyunft.com/systemImg/31742d01-63c9-4bfd-9d50-40d8dd76d6ab.jpg","introduce":"1234567890123456789012345678901234567890123456789","sid":21004},{"seriesName":"123456789","backgroundUrl":"https://zy-static-dev.zhouyunft.com/systemImg/31742d01-63c9-4bfd-9d50-40d8dd76d6ab.jpg","introduce":"1234567890123456789012345678901234567890123456789","sid":21005}]
     * bid : 1
     */

    private String name;
    private String logoUrl;
    private String backgroundUrl;
    private String seriesNum;
    private String nftNum;
    private String nftPublishNum;
    private String introduce;
    private String showImg;
    private String bid;
    private List<SeriesListBean> seriesList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getBackgroundUrl() {
        return backgroundUrl;
    }

    public void setBackgroundUrl(String backgroundUrl) {
        this.backgroundUrl = backgroundUrl;
    }

    public String getSeriesNum() {
        return seriesNum;
    }

    public void setSeriesNum(String seriesNum) {
        this.seriesNum = seriesNum;
    }

    public String getNftNum() {
        return nftNum;
    }

    public void setNftNum(String nftNum) {
        this.nftNum = nftNum;
    }

    public String getNftPublishNum() {
        return nftPublishNum;
    }

    public void setNftPublishNum(String nftPublishNum) {
        this.nftPublishNum = nftPublishNum;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

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

    public List<SeriesListBean> getSeriesList() {
        return seriesList;
    }

    public void setSeriesList(List<SeriesListBean> seriesList) {
        this.seriesList = seriesList;
    }

    public static class SeriesListBean implements Serializable {
        /**
         * seriesName : 水浒系列
         * backgroundUrl : https://zy-static-dev.zhouyunft.com/systemImg/6fdda34b-e9d8-4898-8636-d3eb8c355522.png
         * introduce : 水浒108好汉
         * sid : 10001
         */

        private String seriesName;
        private String backgroundUrl;
        private String introduce;
        private String sid;

        public String getSeriesName() {
            return seriesName;
        }

        public void setSeriesName(String seriesName) {
            this.seriesName = seriesName;
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

        public String getSid() {
            return sid;
        }

        public void setSid(String sid) {
            this.sid = sid;
        }
    }
}
