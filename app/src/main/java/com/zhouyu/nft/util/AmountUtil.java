package com.zhouyu.nft.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class AmountUtil {
    public static String changeF2Y(String fen) {
        if (fen!=null&&!fen.isEmpty())
        {
            BigDecimal fenAmt = new BigDecimal(fen);
            BigDecimal yuanAmt = fenAmt.divide(new BigDecimal(100)).setScale(2, RoundingMode.DOWN);
            return "￥"+yuanAmt.toPlainString();
        }else {
            return "";
        }
    }
}
