package com.gfx.vms.base.util;

import org.apache.commons.lang3.StringUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author tony
 * @date 2018/9/6
 * @Description: md5对字符串进行加密
 */
public class MD5Util {

    public static String MD5(String plainString) {
        if (StringUtils.isNotBlank(plainString)) {
            try {
                MessageDigest messageDigest = MessageDigest.getInstance("MD5");
                messageDigest.update(plainString.getBytes());
                //拿到加密后的字节数组
                byte[] bytes = messageDigest.digest();

                StringBuffer sb = new StringBuffer();
                for (byte aByte : bytes) {
                    String hex = Integer.toHexString(0xff & aByte);
                    if (hex.length() == 1)
                        sb.append('0');
                    sb.append(hex);
                }
                return sb.toString();

            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
        return "";
    }
}
