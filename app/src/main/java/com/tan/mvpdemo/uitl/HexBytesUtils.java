package com.tan.mvpdemo.uitl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * 数据类型转换函数集
 * <p>
 * <br> Author: 叶青
 * <br> Version: 1.0.0
 * <br> Date: 2016年12月11日
 */
public class HexBytesUtils {

    /**
     * 把16进制字符串String转换成字节数组byte[]
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-12-22,下午5:48:18
     * <br> UpdateTime: 2016-12-22,下午5:48:18
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param hex
     *         十六进制串
     */
    public static byte[] hexStringToByte(String hex) {
        int len = (hex.length() / 2);
        byte[] result = new byte[len];
        char[] achar = hex.toCharArray();
        for (int i = 0; i < len; i++) {
            int pos = i * 2;
            result[i] = (byte) (toByte(achar[pos]) << 4 | toByte(achar[pos + 1]));
        }
        return result;
    }

    private static byte toByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

    /**
     * 十六进制String转二进制压缩成bcd码
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-12-22,下午5:48:18
     * <br> UpdateTime: 2016-12-22,下午5:48:18
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param str
     *         十六进制串
     */
    public static byte[] hex2byte(String str) { // 字符串转二进制
        int len = str.length();
        String stemp;
        if (len % 2 != 0) {
            len += 1;
        }
        byte bt[] = new byte[len / 2];
        for (int n = 0; n < len / 2; n++) {
            stemp = str.substring(n * 2, n * 2 + 2);
            bt[n] = (byte) (Integer.parseInt(stemp, 16));
        }
        return bt;
    }

    /**
     * 把byte[]转换成16进制String
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-2-24,上午11:49:48
     * <br> UpdateTime: 2016-2-24,上午11:49:48
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param bytes
     *         字节数组
     */
    public static String bytesToHexString(byte[] bytes, int len) {
        StringBuilder sb = new StringBuilder(len);
        String sTemp;
        for (int i = 0; i < len; i++) {
            sTemp = Integer.toHexString(0xFF & bytes[i]);
            if (sTemp.length() < 2)
                sb.append(0);
            sb.append(sTemp.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * 把byte[]转换成16进制String
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-12-22,下午5:48:18
     * <br> UpdateTime: 2016-12-22,下午5:48:18
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param bytes
     *         字节数组
     */
    public static String bytesToHexString(byte[] bytes) {
        if (bytes != null) {
            StringBuilder sb = new StringBuilder(bytes.length);
            String sTemp;
            for (byte aBArray : bytes) {
                sTemp = Integer.toHexString(0xFF & aBArray);
                if (sTemp.length() < 2)
                    sb.append(0);
                sb.append(sTemp.toUpperCase());
            }
            return sb.toString();
        } else {
            return "";
        }

    }

    /**
     * byte转换成16进制字符串
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-12-22,下午5:48:18
     * <br> UpdateTime: 2016-12-22,下午5:48:18
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param b
     *         字节
     */
    public static String byteToHexString(byte b) {
        StringBuilder sb = new StringBuilder();
        String sTemp;
        sTemp = Integer.toHexString(0xFF & b);
        if (sTemp.length() < 2)
            sb.append(0);
        sb.append(sTemp.toUpperCase());
        return sb.toString();
    }

    /**
     * 把byte[]转换为Object
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-12-22,下午5:48:18
     * <br> UpdateTime: 2016-12-22,下午5:48:18
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param bytes
     *         bytes数组
     *
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static Object bytesToObject(byte[] bytes) throws IOException, ClassNotFoundException {
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        ObjectInputStream oi = new ObjectInputStream(in);
        Object o = oi.readObject();
        oi.close();
        return o;
    }

    /**
     * 把可序列化对象转换成字节数组
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-12-22,下午5:48:18
     * <br> UpdateTime: 2016-12-22,下午5:48:18
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param s
     *         可序列化对象Serializable
     */
    public static byte[] objectToBytes(Serializable s) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream ot = new ObjectOutputStream(out);
        ot.writeObject(s);
        ot.flush();
        ot.close();
        return out.toByteArray();
    }

    /**
     * 把可序列化对象Serializable转换成String
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-12-22,下午5:48:18
     * <br> UpdateTime: 2016-12-22,下午5:48:18
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param s
     *         可序列化对象Serializable
     *
     * @throws IOException
     */
    public static String objectToHexString(Serializable s) throws IOException {
        return bytesToHexString(objectToBytes(s));
    }

    /**
     * 16进制转Object
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-12-22,下午5:48:18
     * <br> UpdateTime: 2016-12-22,下午5:48:18
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param hex
     *         16进制字符串
     *
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static Object hexStringToObject(String hex) throws IOException, ClassNotFoundException {
        return bytesToObject(hexStringToByte(hex));
    }

    // *************************************Java 中 byte[] 和 int 之间的转换源码：

    /**
     * 字节数组转转换成整形int
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-11-24,下午4:27:05
     * <br> UpdateTime: 2016-11-24,下午4:27:05
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param b
     *         bytes
     */
    public static int bytes2int(byte[] b) {
        int temp = 0;
        int a1, a2;
        a1 = (int) (b[0]);
        a2 = (int) (b[1]);
        if (a1 < 0)
            a1 += 256;
        if (a2 < 0)
            a2 += 256;
        temp = a1 << 8;
        temp |= a2 | a1;
        return temp;
    }

    /**
     * 整形int转转换成字节数组
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-11-24,下午4:27:05
     * <br> UpdateTime: 2016-11-24,下午4:27:05
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param i
     *         整形数字
     */
    public static byte[] int2bytes(int i) {
        byte[] result = new byte[4];
        result[0] = (byte) ((i >> 24) & 0xFF);
        result[1] = (byte) ((i >> 16) & 0xFF);
        result[2] = (byte) ((i >> 8) & 0xFF);
        result[3] = (byte) (i & 0xFF);
        return result;
    }

    // *********************************Java 中 byte 和 int 之间的转换源码：

    /**
     * byte 与 int 的相互转换
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-10-27,下午4:29:29
     * <br> UpdateTime: 2016-10-27,下午4:29:29
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param i
     *         整形数字
     */
    public static byte intToByte(int i) {
        return (byte) i;
    }

    /**
     * Java 中 byte 和 int 之间的转换源码：
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-10-27,下午4:29:43
     * <br> UpdateTime: 2016-10-27,下午4:29:43
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param b
     *         字节
     */
    public static int byteToInt(byte b) {
        // Java 总是把 byte 当做有符处理；我们可以通过将其和 0xFF 进行二进制与得到它的无符值
        return b & 0xFF;
    }

    /**
     * 16进制字符串转换为byte
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-3-21,下午2:16:38
     * <br> UpdateTime: 2016-3-21,下午2:16:38
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param str
     *         16进制字符串
     */
    public static byte hexStr2Byte(String str) {
        return (byte) Integer.parseInt(str.substring(0, 2), 16);
    }

    /**
     * 16进制字符串转换为byte数组
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-3-21,下午2:16:38
     * <br> UpdateTime: 2016-3-21,下午2:16:38
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param str
     *         16进制字符串
     */
    public static byte[] hexStr2Bytes(String str) {
        int len = str.length() / 2;
        byte[] mbytes = new byte[len];
        for (int i = 0; i < len; i++) {
            try {
                mbytes[i] = (byte) Integer.parseInt(str.substring(i * 2, i * 2 + 2), 16);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mbytes;
    }

    // *****************************使用頻率不高****************************

    /**
     * BCD码转为10进制串(阿拉伯数据)
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-11-24,下午4:25:24
     * <br> UpdateTime: 2016-11-24,下午4:25:24
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param bytes
     *         BCD码
     *
     * @return 10进制串
     */
    public static String bcd2Str(byte[] bytes) {
        StringBuilder temp = new StringBuilder(bytes.length * 2);

        for (byte aByte : bytes) {
            temp.append((byte) ((aByte & 0xf0) >>> 4));
            temp.append((byte) (aByte & 0x0f));
        }
        return temp.toString().substring(0, 1).equalsIgnoreCase("0") ? temp.toString().substring(1) : temp.toString();
    }

    /**
     * ASC转换成String
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-2-24,上午11:49:48
     * <br> UpdateTime: 2016-2-24,上午11:49:48
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param b
     *         ASC
     */
    public static String ASC2String(byte[] b) {
        String trackData = "";
        for (byte aB : b) {
            trackData += (char) (aB & 0XFF);
        }
        // String.toUpperCase 返回大写字母
        return trackData.toUpperCase();
    }

    /**
     * ASC转换成String
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-2-24,上午11:49:48
     * <br> UpdateTime: 2016-2-24,上午11:49:48
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param str
     *         字符串
     */
    public static byte[] String2ASC(String str) { // 二行制转字符串
        return str.getBytes();
    }

    /**
     * 10进制字符串串转为BCD码
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-11-24,下午4:25:42
     * <br> UpdateTime: 2016-11-24,下午4:25:42
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param str
     *         10进制字符串串
     *
     * @return BCD码
     */
    public static byte[] str2bcd(String str) {
        if (str.length() % 2 != 0) {
            str = "0" + str;
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        char[] cs = str.toCharArray();
        for (int i = 0; i < cs.length; i += 2) {
            int high = cs[i] - 48;
            int low = cs[i + 1] - 48;
            baos.write(high << 4 | low);
        }
        return baos.toByteArray();
    }

    /**
     * BCD字节转换字符串
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-11-24,下午4:25:42
     * <br> UpdateTime: 2016-11-24,下午4:25:42
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param b
     *         :BCD字节
     *
     * @return 字符串
     */
    public static String bcd2string(byte[] b) {
        StringBuilder sb = new StringBuilder();
        for (byte aB : b) {
            int h = ((aB & 0xff) >> 4) + 48;
            sb.append((char) h);
            int l = (aB & 0x0f) + 48;
            sb.append((char) l);
        }
        return sb.toString();
    }

    /**
     * ASC码字符串 转换为十六进制字符串
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-11-24,下午4:25:42
     * <br> UpdateTime: 2016-11-24,下午4:25:42
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param str
     *         :ASC码字符串
     *
     * @return 补齐的字符串。串长不足16的倍数补齐“F”
     */
    public static String add16FF(String str) {
        int iLen, iAddLen;
        str = str.trim();
        iLen = str.length();
        iLen %= 16;
        iAddLen = 16 - iLen;
        String strAdd = "";
        for (int i = 0; i < iAddLen; i++)
            strAdd += "F";
        return str + strAdd;
    }

    /**
     * asc码 转换为十进制数值
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-2-24,上午11:49:48
     * <br> UpdateTime: 2016-2-24,上午11:49:48
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param a
     *         asc码
     */
    public static byte asscii2Num(byte a) {
        byte x;
        x = a;
        if (x >= 0x30 && x <= 0x39)
            x = (byte) (x - 0x30);
        else if (x >= 0x41 && x <= 0x46)
            x = (byte) (x - 0x37);
        else if (x >= 0x61 && x <= 0x66)
            x = (byte) (x - 0x57);
        else
            x = 0;
        return x;
    }

    /**
     * Byte转Bit
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-2-24,上午11:49:56
     * <br> UpdateTime: 2016-2-24,上午11:49:56
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param b
     *         byte
     */
    public static String byteToBit(byte b) {
        return "" + (byte) ((b >> 7) & 0x1) + (byte) ((b >> 6) & 0x1) + (byte) ((b >> 5) & 0x1) + (byte) ((b >> 4) & 0x1) + (byte) ((b >> 3) & 0x1) + (byte) ((b >> 2) & 0x1) + (byte) ((b >> 1) & 0x1)
                + (byte) ((b) & 0x1);
    }

    /**
     * Bit转Byte
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-2-24,上午11:49:48
     * <br> UpdateTime: 2016-2-24,上午11:49:48
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param byteStr
     *         字符串
     */
    public static byte bitToByte(String byteStr) {
        int re, len;
        if (null == byteStr) {
            return 0;
        }
        len = byteStr.length();
        if (len != 4 && len != 8) {
            return 0;
        }
        if (len == 8) {// 8 bit处理
            if (byteStr.charAt(0) == '0') {// 正数
                re = Integer.parseInt(byteStr, 2);
            } else {// 负数
                re = Integer.parseInt(byteStr, 2) - 256;
            }
        } else {// 4 bit处理
            re = Integer.parseInt(byteStr, 2);
        }
        return (byte) re;
    }

    /**
     * 把byte[]转换成16进制String
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-12-22,下午5:48:18
     * <br> UpdateTime: 2016-12-22,下午5:48:18
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param bytes
     *         字节数组
     */
    public static String bytes2HexString(byte[] bytes) {
        if (bytes != null) {
            StringBuilder sb = new StringBuilder(bytes.length);
            String sTemp;
            for (byte aBArray : bytes) {
                sTemp = Integer.toHexString(0xFF & aBArray);
                if (sTemp.length() < 2)
                    sb.append(0);
                sb.append(sTemp.toUpperCase());
            }
            return sb.toString();
        } else {
            return "";
        }
    }

    /**
     * byte转换成16进制字符串
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-12-22,下午5:48:18
     * <br> UpdateTime: 2016-12-22,下午5:48:18
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param b
     *         字节
     */
    public static String byte2HexString(byte b) {
        StringBuilder sb = new StringBuilder();
        String sTemp;
        sTemp = Integer.toHexString(0xFF & b);
        if (sTemp.length() < 2)
            sb.append(0);
        sb.append(sTemp.toUpperCase());
        return sb.toString();
    }

    /**
     * 把16进制字符串String转换成字节数组byte[]
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-12-22,下午5:48:18
     * <br> UpdateTime: 2016-12-22,下午5:48:18
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param str
     *         十六进制串
     */
    public static byte[] hexString2Bytes(String str) { // 字符串转二进制
        int len = str.length();
        String stemp;
        if (len % 2 != 0) {
            len += 1;
        }
        byte bt[] = new byte[len / 2];
        for (int n = 0; n < len / 2; n++) {
            stemp = str.substring(n * 2, n * 2 + 2);
            bt[n] = (byte) (Integer.parseInt(stemp, 16));
        }
        return bt;
    }
}
