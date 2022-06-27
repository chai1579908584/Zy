package com.zhouyu.nft.bean;

import java.io.Serializable;

public class UserInfo implements Serializable {

        /**
         * age :
         * daoNum :
         * fansNum :
         * followNum :
         * headImg :
         * introduce :
         * inviteCode :
         * nick :
         * rcToken :
         * sex :
         * token :
         * walletAdress :
         */
        private String age;
        private String daoNum;
        private String fansNum;
        private String followNum;
        private String headImg;
        private String introduce;
        private String inviteCode;
        private String nick;
        private String rcToken;
        private String sex;
        private String token;
        private String walletAdress;
        private String isRealName;
        private String phone;
        private String nftNum;

    public String getNftNum() {
        return nftNum;
    }

    public void setNftNum(String nftNum) {
        this.nftNum = nftNum;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIsRealName() {
        return isRealName;
    }

    public void setIsRealName(String isRealName) {
        this.isRealName = isRealName;
    }

    public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public String getDaoNum() {
            return daoNum;
        }

        public void setDaoNum(String daoNum) {
            this.daoNum = daoNum;
        }

        public String getFansNum() {
            return fansNum;
        }

        public void setFansNum(String fansNum) {
            this.fansNum = fansNum;
        }

        public String getFollowNum() {
            return followNum;
        }

        public void setFollowNum(String followNum) {
            this.followNum = followNum;
        }

        public String getHeadImg() {
            return headImg;
        }

        public void setHeadImg(String headImg) {
            this.headImg = headImg;
        }

        public String getIntroduce() {
            return introduce;
        }

        public void setIntroduce(String introduce) {
            this.introduce = introduce;
        }

        public String getInviteCode() {
            return inviteCode;
        }

        public void setInviteCode(String inviteCode) {
            this.inviteCode = inviteCode;
        }

        public String getNick() {
            return nick;
        }

        public void setNick(String nick) {
            this.nick = nick;
        }

        public String getRcToken() {
            return rcToken;
        }

        public void setRcToken(String rcToken) {
            this.rcToken = rcToken;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getWalletAdress() {
            return walletAdress;
        }

        public void setWalletAdress(String walletAdress) {
            this.walletAdress = walletAdress;
        }

}
