package com.zhouyu.nft.bean;

import java.io.Serializable;

public class BankCardBean implements Serializable {

    /**
     * bankCode :
     * bankId : 0
     * cardText :
     * cardType : 0
     * isDefault : 0
     */

    private String bankCode;
    private String bankId;
    private String cardText;
    private String cardType;
    private String isDefault;

    public String getBankCode() {
        return bankCode;
    }

    public String getBankId() {
        return bankId;
    }

    public String getCardText() {
        return cardText;
    }

    public String getCardType() {
        return cardType;
    }

    public String getIsDefault() {
        return isDefault;
    }
}
