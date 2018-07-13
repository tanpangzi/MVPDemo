package com.tan.mvpdemo.uitl;

import com.tan.mvpdemo.BaseApplication;
import com.tan.mvpdemo.bean.UserInfoLoginBean;

import java.security.MessageDigest;
import java.util.HashMap;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;


/**
 * 加密算法
 * <p>
 * <br> Author: tanjun
 * <br> Version: 3.0.0
 * <br> Date: 2018/5/31
 */
public class ThreeDES {

    /**
     * 加密
     */
    public static String encrypt(String src, String key) throws Exception {
        DESedeKeySpec dks = new DESedeKeySpec(key.getBytes("UTF-8"));
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("desede");
        SecretKey securekey = keyFactory.generateSecret(dks);

        Cipher cipher = Cipher.getInstance("desede/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, securekey);
        byte[] b = cipher.doFinal(src.getBytes("utf-8"));
        return HexBytesUtils.bytesToHexString(b);
    }

    /**
     * 解密
     */
    public static String decrypt(String src, String key) throws Exception {

        DESedeKeySpec dks = new DESedeKeySpec(key.getBytes("UTF-8"));
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("desede");
        SecretKey securekey = keyFactory.generateSecret(dks);

        // --Chipher对象解密
        Cipher cipher = Cipher.getInstance("desede/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, securekey);
        byte[] retByte = cipher.doFinal(src.getBytes("UTF-8"));
        return new String(retByte);
    }

    /**
     * post请求的参数加密
     * <p>
     * <br> Version: 3.0.0
     * <br> CreateAuthor: 叶青
     * <br> CreateTime: 2017/2/15 13:31
     * <br> UpdateAuthor: 叶青
     * <br> UpdateTime: 2017/2/15 13:31
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param params
     *         post请求的参数
     */
    public static HashMap<String, String> paramsEncrypt(HashMap<String, String> params) {
        //3DES加密用的秘钥
        //String secret_key = "abc45678901234567890ABCD";
        String secret_key = "fasdljo34984305FASADW4R3";
        try {
            String jsonStr = JsonUtil.map2JsonObjectStr(params);
            String threeDESECB = encrypt(jsonStr, secret_key);
            params.clear();
            //传输密文
            params.put("encoding", threeDESECB);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return params;
    }

    /**
     * 添加post请求的固定参数
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016年12月31日,上午5:00:05
     * <br> UpdateTime: 2016年12月31日,上午5:00:05
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @return 含固定参数的HashMap
     */
    public static HashMap<String, String> getPostHeadMap() {
        HashMap<String, String> map = new HashMap<>();
        String postTime = System.currentTimeMillis() + "";
        //请求发送时间
        map.put(Constant.SERVER_POST_TIME_KEY, postTime);

        //40位的SHA签名
        try {
            //Md5签名用的秘钥
            String key_present = "aFaj81aXawkj8XNOF3GFCUn2q903LN8U";
            StringBuilder sb = new StringBuilder();
            sb.append(key_present);
            sb.append(postTime);
            sb.append(key_present);
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] bytes = md.digest(sb.toString().getBytes("UTF-8"));
            String sign = HexBytesUtils.bytes2HexString(bytes);
            map.put(Constant.SERVER_SIGN_KEY, sign);
        } catch (Exception e) {
            e.printStackTrace();
            map.put(Constant.SERVER_SIGN_KEY, "");
        }

        UserInfoLoginBean bean = BaseApplication.getInstance().getUserInfoBean();
        if (bean == null) {
            map.put("userId", "");
        } else {
            map.put("userId", bean.getUserId());
        }
        return map;
    }
}
