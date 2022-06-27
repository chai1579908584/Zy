package com.zhouyu.nft.bean;

import java.io.Serializable;
import java.util.List;

public class BankCodeBean implements Serializable {

    /**
     * dictLabel : 兴业银行
     * dictValue : 03090000
     * children : []
     */

    private String dictLabel;
    private String dictValue;
    private List<?> children;

    public String getDictLabel() {
        return dictLabel;
    }

    public void setDictLabel(String dictLabel) {
        this.dictLabel = dictLabel;
    }

    public String getDictValue() {
        return dictValue;
    }

    public void setDictValue(String dictValue) {
        this.dictValue = dictValue;
    }

    public List<?> getChildren() {
        return children;
    }

    public void setChildren(List<?> children) {
        this.children = children;
    }
}
