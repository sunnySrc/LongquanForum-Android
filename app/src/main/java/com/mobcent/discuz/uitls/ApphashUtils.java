package com.mobcent.discuz.uitls;

import android.util.Log;

import java.security.MessageDigest;

/**
 * Created by $Createdbymynameon on 2017/5/5.
 */

public class ApphashUtils {

    //MD5加密
    public String appHashs() {

        long time = System.currentTimeMillis();
        Log.i("TAG","时间戳 time="+time);
        String a = String.valueOf(time);
        String s=a.substring(0,5);
        Log.i("TAG","s="+s);
        String s1=s+"appbyme_key";
        Log.i("TAG","s1="+s1);
        return s1;
        //时间戳 time=1493965462750
        //MD5 md5=4BE1024F81F2A5F004668BD850486453F2E811F8       .toUpperCase()
        //        4be1024f81f2a5f004668bd850486453f2e811f8
        //                   1b11e9d59a465bf46d15504a144f705a
        //1F2A5F00   MD5 md5=2d43bdb9bdda23b5419eef99cca88a366600dc2a
        //return md_password;
    }
            /**
             * 加密
             * @param plaintext 明文
             * @return ciphertext 密文
             */
            public final static String  encrypt(String plaintext) {
                char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                        'a', 'b', 'c', 'd', 'e', 'f' };
                try {
                    byte[] btInput = plaintext.getBytes();
                    // 获得MD5摘要算法的 MessageDigest 对象
                    MessageDigest mdInst = MessageDigest.getInstance("MD5");
                    // 使用指定的字节更新摘要
                    mdInst.update(btInput);
                    // 获得密文
                    byte[] md = mdInst.digest();
                    // 把密文转换成十六进制的字符串形式
                    int j = md.length;
                    char str[] = new char[j * 2];
                    int k = 0;
                    for (int i = 0; i < j; i++) {
                        byte byte0 = md[i];
                        str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                        str[k++] = hexDigits[byte0 & 0xf];
                    }
                    return new String(str);
                } catch (Exception e) {
                    return null;
                }
    }
}
