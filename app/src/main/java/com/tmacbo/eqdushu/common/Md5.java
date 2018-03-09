package com.tmacbo.eqdushu.common;

import java.security.MessageDigest;

/**
 * MD5签名工具
 *
 * @author liu_kf
 */
public class Md5 {
    /**
     * 根据传入的字节数组得到对应的MD5签名
     *
     * @param strTemp
     *
     * @return
     */
    public final static String MD5(byte[] strTemp) {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
                'e', 'f'};
        try {
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(strTemp);
            byte[] md = mdTemp.digest();
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }

            return new String(str).toUpperCase();
        } catch (Exception e) {
            return null;
        }
    }
}
