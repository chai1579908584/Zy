package com.zhouyu.nft.bean;

import java.io.Serializable;
import java.util.List;

public class BrandBean implements Serializable {


    /**
     * records : [{"name":"宙域","logoUrl":"https://zy-static-dev.zhouyunft.com/systemImg/c4723b52-ff52-46ec-98b9-4175ebf0ecd0.png","backgroundUrl":"https://zy-static-dev.zhouyunft.com/systemImg/f4ecd971-8812-4f56-be33-661fd4fd9113.png","seriesNum":3,"nftNum":5,"nftPublishNum":1230,"introduce":"大发大发大发大发大发大发大发大发大发大发","showImg":"","seriesList":[],"bid":1},{"name":"朱迪","logoUrl":"https://zy-static-dev.zhouyunft.com/systemImg/11615bd6-5024-4ac8-8cd6-70baf64340dd.jpg","backgroundUrl":"https://zy-static-dev.zhouyunft.com/systemImg/f0306052-a12b-4ff4-8c91-d93d92f35e70.jpg","seriesNum":2,"nftNum":1,"nftPublishNum":100,"introduce":"dds","showImg":"","seriesList":[],"bid":10001},{"name":"轻关品牌","logoUrl":"https://zy-static-dev.zhouyunft.com/systemImg/196a1716-6c3b-420b-ade6-c6a25d9e275b.png","backgroundUrl":"https://zy-static-dev.zhouyunft.com/systemImg/5b40e4e6-ce3a-47d2-ad91-5f2b2cf231d0.png","seriesNum":2,"nftNum":3,"nftPublishNum":10200,"introduce":"轻关222222222222","showImg":"","seriesList":[],"bid":11001},{"name":"品牌11","logoUrl":"https://zy-static-dev.zhouyunft.com/systemImg/8ae174a5-3572-4fcd-8e4c-ba38dee244e3.jpg","backgroundUrl":"https://zy-static-dev.zhouyunft.com/systemImg/7bd62678-86e1-489d-9158-ab9d1ce21f1d.jpg","seriesNum":0,"nftNum":0,"nftPublishNum":0,"introduce":"这是品牌简介11","showImg":"","seriesList":[],"bid":12001},{"name":"宙域NFT","logoUrl":"https://zy-static-dev.zhouyunft.com/systemImg/2e35260b-c592-4c3e-9e80-5bc478240e66.png","backgroundUrl":"https://zy-static-dev.zhouyunft.com/systemImg/079a65d9-ae00-4e8a-95b8-f8bd862c328d.png","seriesNum":1,"nftNum":1,"nftPublishNum":1000,"introduce":" 宙域科技以数字藏品版权确权、数字藏品实体化、数字藏品生态化为目标，立志打造一个全新的沉浸式虚拟科技\u201c元宇宙\u201d，构建全球领先的数字藏品服务平台。","showImg":"","seriesList":[],"bid":13004},{"name":"阿里","logoUrl":"https://zy-static-dev.zhouyunft.com/systemImg/7dc9310c-ce5d-4ee0-868b-bc93aa922a55.jpg","backgroundUrl":"https://zy-static-dev.zhouyunft.com/systemImg/9ccad6e6-8a3d-419f-b92e-1fe2014d1649.jpg","seriesNum":1,"nftNum":2,"nftPublishNum":55,"introduce":"这是品牌的简介","showImg":"","seriesList":[],"bid":18001}]
     * total : 6
     * pages : 1
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
         * name : 宙域
         * logoUrl : https://zy-static-dev.zhouyunft.com/systemImg/c4723b52-ff52-46ec-98b9-4175ebf0ecd0.png
         * backgroundUrl : https://zy-static-dev.zhouyunft.com/systemImg/f4ecd971-8812-4f56-be33-661fd4fd9113.png
         * seriesNum : 3
         * nftNum : 5
         * nftPublishNum : 1230
         * introduce : 大发大发大发大发大发大发大发大发大发大发
         * showImg :
         * seriesList : []
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
        private List<?> seriesList;

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

        public List<?> getSeriesList() {
            return seriesList;
        }

        public void setSeriesList(List<?> seriesList) {
            this.seriesList = seriesList;
        }
    }
}
