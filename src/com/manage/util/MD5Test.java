package com.manage.util;

import java.math.BigInteger;
import java.security.MessageDigest;

public class MD5Test {
    public static void main(String[] args) {
        String md5 = getMD5("wxb");
        System.out.println(md5);
    }
    
    public static String getMD5(String str) {
        try {
            // 生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 计算md5函数
            md.update(str.getBytes());
            System.out.println("aaaaaaaaaaaaaaa:"+str);
            System.out.println("aaaaaaaaaaaaaaa:"+md.digest());
            // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
            return new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
            e.printStackTrace();
            return str;
        }
    }
}
