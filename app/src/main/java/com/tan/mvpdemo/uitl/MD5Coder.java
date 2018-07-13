package com.tan.mvpdemo.uitl;

import android.text.TextUtils;

import java.security.MessageDigest;

/**
 * MD5 工具
 * <p>
 * <br> Author: 叶青
 * <br> Version: 1.0.0
 * <br> Date: 2016年12月11日
 */
public class MD5Coder {

    /**
     * MD5加密
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-1-22,下午1:09:54
     * <br> UpdateTime: 2016-1-22,下午1:09:54
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param str
     *         需要进行MD5加密的明文
     *
     * @return str MD5加密后的密文
     */
    public static String md5Encrypt(String str) {
        // 保持编码为UTF-8
        if (TextUtils.isEmpty(str)) {
            return "";
        }

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte b[] = md.digest();
            int i;
            StringBuilder buf = new StringBuilder("");
            for (byte aB : b) {
                i = aB;
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            return buf.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
