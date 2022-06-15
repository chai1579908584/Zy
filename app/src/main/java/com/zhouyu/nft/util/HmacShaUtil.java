package com.zhouyu.nft.util;


import cn.hutool.crypto.digest.HMac;
import cn.hutool.crypto.digest.HmacAlgorithm;

public class HmacShaUtil {
    public static String getHmacSha1(String src, String key){
        byte[] keyByte = key.getBytes();
        HMac mac = new HMac(HmacAlgorithm.HmacSHA1, keyByte);
        String macHex1 = mac.digestHex(src);
        return macHex1;
    }

}
