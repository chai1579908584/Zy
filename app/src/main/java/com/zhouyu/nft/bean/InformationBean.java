package com.zhouyu.nft.bean;

import java.io.Serializable;
import java.util.List;

public class InformationBean implements Serializable {

    /**
     * pages : 0
     * records : [{"content":"","imgUrl":"","linkUrl":"","pubTime":"","title":""}]
     * total : 0
     */

    private int pages;
    private int total;
    private List<RecordsBean> records;

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<RecordsBean> getRecords() {
        return records;
    }

    public void setRecords(List<RecordsBean> records) {
        this.records = records;
    }

    public static class RecordsBean implements Serializable {
        /**
         * content :
         * imgUrl :
         * linkUrl :
         * pubTime :
         * title :
         */

        private String content;
        private String imgUrl;
        private String linkUrl;
        private String pubTime;
        private String title;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public String getLinkUrl() {
            return linkUrl;
        }

        public void setLinkUrl(String linkUrl) {
            this.linkUrl = linkUrl;
        }

        public String getPubTime() {
            return pubTime;
        }

        public void setPubTime(String pubTime) {
            this.pubTime = pubTime;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
