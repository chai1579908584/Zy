package com.zhouyu.nft.bean;


import java.io.Serializable;

public class BannerBean implements Serializable {

        /**
         * title : 12331
         * introduce : null
         * bannerUrl : /systemImg/e91e469c-7e76-4d85-8394-03e7c8770e90.jpg
         * linkUrl : 123123
         */

        private String title;
        private Object introduce;
        private String bannerUrl;
        private String linkUrl;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Object getIntroduce() {
            return introduce;
        }

        public void setIntroduce(Object introduce) {
            this.introduce = introduce;
        }

        public String getBannerUrl() {
            return bannerUrl;
        }

        public void setBannerUrl(String bannerUrl) {
            this.bannerUrl = bannerUrl;
        }

        public String getLinkUrl() {
            return linkUrl;
        }

        public void setLinkUrl(String linkUrl) {
            this.linkUrl = linkUrl;
        }
}
