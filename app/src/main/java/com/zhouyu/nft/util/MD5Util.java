package com.zhouyu.nft.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
    /**
     * 字符串加密
     *
     * @param raw 字符串
     * @return 加密后的字符串
     */
    public static String encrypt(String raw) {
        String encryptString = raw;
        try {
            // 创建一个MD5算法对象
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 给算法对象加载待加密的原始数据
            md.update(raw.getBytes());
            // 调用digest方法完成哈希计算
            byte[] digests = md.digest();
            int md5Str;
            StringBuilder buf = new StringBuilder();
            for (byte digest : digests) {
                md5Str = digest;
                if (md5Str < 0) {
                    md5Str += 256;
                }
                if (md5Str < 16) {
                    buf.append("0");
                }
                buf.append(Integer.toHexString(md5Str)); // 把字节数组逐位转换为十六进制数
            }
            encryptString = buf.toString(); // 拼装加密字符串
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return encryptString.toUpperCase(); // 输出大写的加密串
    }
}
