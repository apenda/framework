package com.apenda.framework.common.util;

import com.baomidou.mybatisplus.core.toolkit.AES;

/**
 * AES加密工具类
 *
 * @author rui.zhou
 * @date 2021年09月11日 11:48
 */
public class AesUtils {

    /**
     * 随机生成16位密钥
     *
     * @return String
     */
    public static String createRandomKey () {
        return AES.generateRandomKey();
    }

    /**
     * 加密
     *
     * @param data 需加密的数据
     * @param key 密钥
     * @return String
     */
    public static String encrypt (String data, String key) {
        return AES.encrypt(data, key);
    }

    public static void main(String[] args) {
        // --mpw.key=32c39ef981a0366ee
        String key = AesUtils.createRandomKey();
        System.out.println("key = " + key);

        // mysql_1
        String url1 = "jdbc:mysql://localhost:3306/test?useUnicode=true&useSSL=false&characterEncoding=utf8&allowPublicKeyRetrieval=true";
        String username1 = "test";
        String password1 = "test";

        // mysql_2
        String url2 = "jdbc:mysql://localhost:3307/test?useUnicode=true&useSSL=false&characterEncoding=utf8&allowPublicKeyRetrieval=true";
        String username2 = "test";
        String password2 = "test";

        String encrypt = AesUtils.encrypt(url1, key);
        System.out.println("e = " + encrypt);
    }
}
