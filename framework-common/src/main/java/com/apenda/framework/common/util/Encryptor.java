package com.apenda.framework.common.util;

/**
 * 加密机 cypherator / Cipherator / Cipher / Cipherable
 *
 * @author rui.zhou
 * @date 2021/09/17 11:46
 **/
public interface Encryptor {

    /**
     * 加密
     *
     * @param value 待加密数据
     * @return String
     */
    String encrypt(String value);

    /**
     * 加密
     *
     * @param value 待加密数据
     * @return String
     */
    String sensitive(String value);

    /**
     * 解密
     *
     * @param value 待解密数据
     * @return String
     */
    String decrypt(String value);

}
