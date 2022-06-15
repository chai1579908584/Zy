package com.zhouyu.nft.bean;

import java.io.Serializable;

public class GraphicBean implements Serializable {

        /**
         * capUid :
         * img :
         */
        private String capUid;
        private String img;

        public String getCapUid() {
            return capUid;
        }

        public void setCapUid(String capUid) {
            this.capUid = capUid;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }
}
